package nc.vo.pubapp.pattern.tool.generate;

import nc.mddb.constant.ElementConstant;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.pub.Constructor;

/**
 * VO属性方法的生成
 * 
 * @since 6.0
 * @version 2008-9-27 下午05:00:12
 * @author 钟鸣
 */
public class VOFieldGenerator {

  /**
   * 读取根据VO的class元数据，并生成相应的get/set/is方法
   * 
   * @param clazz 元数据对应的VO类
   * @return 代码字符串
   */
  public String generate(Class<? extends ISuperVO> clazz) {
    ISuperVO vo = Constructor.construct(clazz);
    IVOMeta voMeta = vo.getMetaData();
    IAttributeMeta[] attributes = voMeta.getAttributes();

    StringBuffer buffer = new StringBuffer();
    // 生成字段名称常量
    buffer.append(this.getColumnNameConst(attributes));

    String className = clazz.getSimpleName();
    // 生成get/set方法
    for (IAttributeMeta attribute : attributes) {
      // 伪列不需要生成get/set方法
      if (attribute.getName().equals(PseudoColumnAttribute.PSEUDOCOLUMN)) {
        continue;
      }
      // dr不需要生成get/set方法
      else if (attribute.getName().equals(ElementConstant.KEY_DR)) {
        continue;
      }
      buffer.append(this.generate(className, attribute));
    }
    return buffer.toString();
  }

  private void appendSpecialInfo(IAttributeMeta attribute, StringBuffer buffer) {
    if (attribute.isSerializable() && !attribute.isPersistence()) {
      buffer.append("（可持续化字段，可以远程传递值，但不能保存到数据库）");
    }
    else if (!attribute.isSerializable()) {
      buffer.append("（临时字段，不可远程传递值，不能保存到数据库）");
    }
  }

  private String generate(String className, IAttributeMeta attribute) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(this.generateGetterDoc(attribute));
    buffer.append(this.generateGetter(className, attribute));
    buffer.append("\r\n");
    buffer.append(this.generateSetterDoc(attribute));
    buffer.append(this.generateSetter(className, attribute));
    buffer.append("\r\n");
    return buffer.toString();
  }

  private String generateGetter(String className, IAttributeMeta attribute) {
    String name = this.getName(attribute.getName());

    StringBuffer buffer = new StringBuffer();

    buffer.append("public ");
    buffer.append(this.getType(attribute));
    buffer.append(" get");
    buffer.append(name);
    buffer.append(" ");
    buffer.append("(");
    buffer.append(") {");
    buffer.append("\r\n");
    buffer.append("return ");
    buffer.append("(");
    buffer.append(this.getType(attribute));
    buffer.append(")");
    buffer.append(" this.getAttributeValue( ");
    buffer.append(className);
    buffer.append(".");
    buffer.append(attribute.getName().toUpperCase());
    buffer.append(");");
    buffer.append("\r\n } \r\n");

    return buffer.toString();
  }

  private String generateGetterDoc(IAttributeMeta attribute) {
    StringBuffer buffer = new StringBuffer();

    buffer.append("/** ");
    buffer.append("\r\n");
    buffer.append("* 获取");
    buffer.append(this.getAttributeLabel(attribute));
    this.appendSpecialInfo(attribute, buffer);
    buffer.append("\r\n");
    buffer.append("*");
    buffer.append("\r\n");
    buffer.append("* @return ");
    buffer.append(this.getAttributeLabel(attribute));
    buffer.append("\r\n");
    if (attribute.getJavaType() == JavaType.UFStringEnum
        || attribute.getJavaType() == JavaType.UFFlag) {
      String name = this.getClassName(attribute.getEnumClass());
      buffer.append("* @see ");
      buffer.append(name);
      buffer.append("\r\n");
    }
    buffer.append("*/");
    buffer.append("\r\n");

    return buffer.toString();
  }

  private String generateSetter(String className, IAttributeMeta attribute) {
    String name = this.getName(attribute.getName());

    StringBuffer buffer = new StringBuffer();
    buffer.append("public void set");
    buffer.append(name);
    buffer.append(" ");
    buffer.append("( ");
    buffer.append(this.getType(attribute));
    buffer.append(" ");
    buffer.append(attribute.getName());
    buffer.append(") {");
    buffer.append("\r\n");
    buffer.append("this.setAttributeValue( ");
    buffer.append(className);
    buffer.append(".");
    buffer.append(attribute.getName().toUpperCase());
    buffer.append(",");
    buffer.append(attribute.getName());
    buffer.append(");");
    buffer.append("\r\n } \r\n");

    return buffer.toString();
  }

  private String generateSetterDoc(IAttributeMeta attribute) {
    StringBuffer buffer = new StringBuffer();
    buffer.append("/** ");
    buffer.append("\r\n");
    buffer.append("* 设置");
    buffer.append(this.getAttributeLabel(attribute));
    this.appendSpecialInfo(attribute, buffer);
    buffer.append("\r\n");
    buffer.append("*");
    buffer.append("\r\n");
    buffer.append("* @param ");
    buffer.append(null == attribute.getColumn() ? attribute.getName()
        : attribute.getColumn().getName());
    buffer.append(" ");
    buffer.append(this.getAttributeLabel(attribute));
    buffer.append("\r\n");
    if (attribute.getJavaType() == JavaType.UFStringEnum
        || attribute.getJavaType() == JavaType.UFFlag) {
      String name = this.getClassName(attribute.getEnumClass());
      buffer.append("* @see ");
      buffer.append(name);
      buffer.append("\r\n");
    }
    buffer.append("*/");
    buffer.append("\r\n");

    return buffer.toString();
  }

  private String getAttributeLabel(IAttributeMeta attribute) {
    String name = null;
    if (attribute.getColumn() != null) {
      name = attribute.getColumn().getLabel();
    }
    else {
      name = attribute.toString();
    }
    return name;
  }

  private String getClassName(Class<?> clazz) {
    String name = clazz.getName();
    int index = name.lastIndexOf(".");
    if (index > 0) {
      name = name.substring(index + 1);
    }
    return name;
  }

  private String getColumnNameConst(IAttributeMeta[] attributes) {
    StringBuffer buffer = new StringBuffer();
    for (IAttributeMeta attribute : attributes) {
      // 伪列不需要生成get/set方法
      if (attribute.getName().equals(PseudoColumnAttribute.PSEUDOCOLUMN)) {
        continue;
      }
      // dr不需要生成get/set方法
      else if (attribute.getName().equals(ElementConstant.KEY_DR)) {
        continue;
      }
      // 中文名称
      buffer.append("/**");
      buffer.append("\r\n");
      buffer.append("*");
      buffer.append(this.getAttributeLabel(attribute));
      this.appendSpecialInfo(attribute, buffer);
      buffer.append("\r\n");
      buffer.append("*/");
      buffer.append("\r\n");
      buffer.append("public static final String ");
      buffer.append(attribute.getName().toUpperCase());
      buffer.append("=");
      buffer.append("\"");
      buffer.append(attribute.getName());
      buffer.append("\";");
      buffer.append("\r\n");
    }
    return buffer.toString();
  }

  private String getName(String name) {
    StringBuffer buffer = new StringBuffer();
    int length = name.length();
    String str = name.substring(0, 1);
    str = str.toUpperCase();
    buffer.append(str);
    if (length > 1) {
      buffer.append(name.substring(1, length));
    }
    return buffer.toString();
  }

  private String getType(IAttributeMeta attribute) {
    JavaType type = attribute.getJavaType();
    String ret = null;
    if (type == JavaType.UFDouble) {
      ret = "UFDouble";
    }
    else if (type == JavaType.String) {
      ret = "String";
    }
    else if (type == JavaType.Integer) {
      ret = "Integer";
    }
    else if (type == JavaType.UFBoolean) {
      ret = "UFBoolean";
    }
    else if (type == JavaType.UFDate) {
      ret = "UFDate";
    }
    else if (type == JavaType.UFDateTime) {
      ret = "UFDateTime";
    }
    else if (type == JavaType.UFLiteralDate) {
      ret = "UFLiteralDate";
    }
    else if (type == JavaType.UFTime) {
      ret = "UFTime";
    }
    else if (type == JavaType.BigDecimal) {
      ret = "UFDouble";
    }
    else if (type == JavaType.UFStringEnum) {
      ret = "String";
    }
    else if (type == JavaType.UFFlag) {
      ret = "Integer";
    }
    else {
      ExceptionUtils.unSupported();
    }
    return ret;
  }

}

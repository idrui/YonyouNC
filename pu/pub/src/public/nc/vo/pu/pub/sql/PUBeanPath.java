package nc.vo.pu.pub.sql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.type.IRefType;
import nc.md.model.type.IType;
import nc.md.util.MDUtil;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 元数据路径： 形式为：.t.f;t.f
 *
 * @since 6.0
 * @version 2011-6-15 下午09:05:41
 * @author yangb
 */

public class PUBeanPath implements Serializable {

  public static final String DOT = ".";

  public static final String RootPath = ".";

  private static final long serialVersionUID = 1291937021380620059L;

  // 是否扩展表属性
  private boolean isExtTableAttr = false;

  // 路径中的最后部分，不包括.
  private String lastPath;

  // 不带开始.的路径信息
  private String strNotStartDotPath;
  
  // 开始.之后的路径信息
  private String strNotStartPath;

  // 父路径
  private String strParentPath;

  // 原始的路径，可能以.开始
  private String strPath;

  // 转换后的路径，主要针对扩展表，转换后其路径为.扩展表名
  private String strTranPath;

  /**
   * @param path 元数据路径
   * @param exttable 扩展表名 可为null
   */
  PUBeanPath(IBean bean, String path) {
    if (StringUtil.isSEmptyOrNull(path)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0","04008001-0640")/*@res "元数据路径错误"*/);
    }
    this.strPath = path.trim();
    if (this.strPath.equals(PUBeanPath.DOT)) {
      this.initPath(this.strPath, null);
      return;
    }
    this.strNotStartDotPath = this.strPath;
    if (this.strPath.startsWith(PUBeanPath.DOT)) {
      this.strNotStartDotPath = this.strPath.substring(1);
    }
    // strNotStartPath
    int firstDot = this.strNotStartDotPath.indexOf(PUBeanPath.DOT);
    if(firstDot > 0){
      this.setStrNotStartPath(this.strNotStartDotPath.substring(firstDot + 1));
    }
    IAttribute attr = bean.getAttributeByPath(this.strNotStartPath);
    if (attr == null || MDUtil.isCollectionType(attr.getDataType())
        || attr.getTable()==null||!attr.mappingExtendTable()) {
      this.initPath(path, null);
    }
    else {
      this.initPath(path, attr.getTable().getName());
    }
  }

  /**
   * @param path 元数据路径
   * @param exttable 扩展表名 可为null
   */
  PUBeanPath(String path, String exttable) {
    if (StringUtil.isSEmptyOrNull(path)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0","04008001-0640")/*@res "元数据路径错误"*/);
    }
    this.initPath(path, exttable);
  }

  /**
   * @param bean
   * @return
   */
  public List<PUJoinInfo> fetchAllParentTablesByMetaPath(IBean bean) {
    List<PUJoinInfo> linfo = new ArrayList<PUJoinInfo>();
    this.fetchAllParentTablesByMetaPath(bean, linfo);
    return linfo;
  }

  public String getLastPath() {
    return this.lastPath;
  }

  public String getNotStartDotPath() {
    return this.strNotStartDotPath;
  }

  public String getParentPath() {
    return this.strParentPath;
  }

  public String getPath() {
    return this.strPath;
  }

  public String getTranPath() {
    return this.strTranPath;
  }

  public boolean isExtTableAttr() {
    return this.isExtTableAttr;
  }

  public boolean isRootPath() {
    if (StringUtil.isSEmptyOrNull(this.strNotStartDotPath)
        || this.strPath.equals(PUBeanPath.RootPath)) {
      return true;
    }
    return false;
  }

  /**
   * @return
   */
  private void fetchAllParentTablesByMetaPath(IBean bean,
      List<PUJoinInfo> lPUJoinInfos) {
    if (StringUtil.isSEmptyOrNull(this.strNotStartDotPath)) {
      return;
    }
    IAttribute attr = bean.getAttributeByPath(this.strNotStartPath);
    PUJoinInfo info = null;
    if (this.isExtTableAttr) {
      info = new PUJoinInfo();
      info.setLefttable(attr.getOwnerBean().getTable().getName());
      info.setLeftfield(attr.getTable().getPrimaryKeyName());
      info.setRighttable(attr.getTable().getName());
      info.setRightfield(attr.getTable().getPrimaryKeyName());
      info.setAttrPath(this.strTranPath);
      lPUJoinInfos.add(0, info);
    }
    info = this.getJoinTableInfoByPath(this.strNotStartDotPath, attr);
    if (info != null) {
      lPUJoinInfos.add(0, info);
    }
    if (StringUtil.isSEmptyOrNull(this.strParentPath)) {
      return;
    }
    new PUBeanPath(bean, this.strParentPath).fetchAllParentTablesByMetaPath(bean,
        lPUJoinInfos);
  }

  private PUJoinInfo getJoinTableInfoByPath(String attrMetaPath, IAttribute attr) {
    if (StringUtil.isSEmptyOrNull(attrMetaPath) || attr == null) {
      return null;
    }

    IType type = attr.getDataType();
    PUJoinInfo info = null;
    if (MDUtil.isCollectionType(type)) {
      // IBean ibean = (IBean) ((ICollectionType) type).getElementType();
      info = new PUJoinInfo();
      info.setLefttable(attr.getOwnerBean().getTable().getName());
      info.setLeftfield(attr.getOwnerBean().getTable().getPrimaryKeyName());
      info.setRighttable(attr.getTable().getName());
      info.setRightfield(attr.getOwnerBean().getTable().getPrimaryKeyName());
    }
    else if (MDUtil.isRefType(type)) {
      IBean ibean = ((IRefType) type).getRefType();
      info = new PUJoinInfo();
      info.setLefttable(attr.getTable().getName());
      info.setLeftfield(attr.getColumn().getName());
      info.setRighttable(ibean.getTable().getName());
      info.setRightfield(ibean.getTable().getPrimaryKeyName());
    }
    if (info != null) {
      info.setAttrPath(attrMetaPath);
    }
    return info;
  }

  /**
   * @param path 元数据路径
   * @param exttable 扩展表名 可为null
   */
  private void initPath(String path, String exttable) {
    this.strPath = path.toString();
    if (this.strPath.equals(PUBeanPath.DOT)) {
      this.strNotStartDotPath = "";
      this.strTranPath = this.strPath;
      this.strParentPath = "";
      this.lastPath = "";
      return;
    }
    // strNotStartDotPath
    if (this.strPath.startsWith(PUBeanPath.DOT)) {
      this.strNotStartDotPath = this.strPath.substring(1);
    }
    else {
      this.strNotStartDotPath = this.strPath;
    }
    // strNotStartPath
    int firstDot = this.strNotStartDotPath.indexOf(PUBeanPath.DOT);
    if(firstDot > 0){
      this.setStrNotStartPath(this.strNotStartDotPath.substring(firstDot + 1));
    }
    // strParentPath lastPath
    int pos = this.strNotStartDotPath.lastIndexOf(PUBeanPath.DOT);
    if (pos > 0) {
      this.strParentPath = this.strNotStartDotPath.substring(0, pos);
      this.lastPath = this.strNotStartDotPath.substring(pos + 1);
    }
    else {
      this.strParentPath = PUBeanPath.DOT;
      this.lastPath = this.strNotStartDotPath;
    }
    // strTranPath
    if (StringUtil.isSEmptyOrNull(exttable)) {
      this.strTranPath = this.strNotStartDotPath;
      this.isExtTableAttr = false;
    }
    else {
      this.isExtTableAttr = true;
      if (StringUtil.isSEmptyOrNull(this.strParentPath)
          || this.strParentPath.equals(PUBeanPath.DOT)) {
        this.strTranPath = exttable;
      }
      else {
        this.strTranPath = this.strParentPath + PUBeanPath.DOT + exttable;
      }
    }
  }

  public String getStrNotStartPath() {
    return this.strNotStartPath;
  }

  public void setStrNotStartPath(String strNotStartPath) {
    this.strNotStartPath = strNotStartPath;
  }
}
package nc.vo.pu.pub.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.ic.pub.db.SqlIn;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.marpuclass.MarPuClassVO;
import nc.vo.ic.icreport.FilterItemEqOrIn;
import nc.vo.ic.pub.cache.BDDefaultPKGetValue;
import nc.vo.ic.pub.define.ICView;
import nc.vo.ic.pub.define.ViewField;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.JavaType;
import nc.vo.pub.SuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMeta;

/**
 * <p>
 * <li>完全重构Sql的View,其特点：</li>
 * <li>可以自由制定表别名 可以自由连接表，如双键连接</li>
 * <li>既可以与查询对话框的产生的IQueryScheme配合使用</li>
 * <li>也可以与传统对话框产生的ConditionVO配合使用</li>
 * </p>
 * 
 * @since 6.0
 * @version 2011-6-10 上午09:48:00
 * @author yangb
 */

public class PUBeanSqlView extends ICView {

  private static final long serialVersionUID = 2011061009484300001L;

  // 实体
  private IBean bean;

  private PUFromWhereSQL fromWhere;

  // 在增加select field 或 where 时是否自动关联实体
  private boolean isAutoJoin = false;

  // 元数据路径正则表达式
  private final Pattern pt = Pattern
      .compile("(\\.?[A-Za-z]{1}\\w*)?(\\.[A-Za-z]{1}\\w*)+");

  // "(\\.?\\w+)?(\\.\\w+)+");

  public PUBeanSqlView() {
    super();
  }

  public PUBeanSqlView(IBean beanentry) {
    this(beanentry, null);
  }

  public PUBeanSqlView(IBean beanentry, PUFromWhereSQL fromWhere) {
    this.bean = beanentry;
    this.fromWhere = fromWhere;
    this.initThisView();
  }

  public PUBeanSqlView(Class<? extends SuperVO> clazz) {
    this(clazz, null);
  }

  public PUBeanSqlView(Class<? extends SuperVO> clazz, PUFromWhereSQL fromWhere) {
    this.bean = this.getBeanByVO(clazz);
    this.fromWhere = fromWhere;
    this.initThisView();
  }

  public PUBeanSqlView(Class<? extends SuperVO> clazz, Map<String, String> mapalias, String join) {
    this.bean = this.getBeanByVO(clazz);
    this.fromWhere = new PUFromWhereSQL(this.bean, join, null, mapalias, true);
    this.initThisView();
  }

  /**
   * <p>
   * 设置视图的表达式字段，表达式的变量以元数据路径形式存在<br>
   * 如：t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * 其中:如果变量就使用当前实体的属性，需要在当前属性前加.;如.x1;表示当前实体<br>
   * 如果是关联实体的属性,则可直接如：t.f1,也可.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express 表达式
   * @param fieldalias 表达式别名
   */
  public void addExpress(String express, String fieldalias, JavaType type) {
    this.addViewFields(new ViewField[] {
      this.changeViewField(express, fieldalias, type, this.isAutoJoin())
    });
  }

  /**
   * @param attrpathexp 元数据路径表达式
   */
  public void addHavingConditonByAttrPathExp(String attrpathexp) {
    this.addHavingWherePart(this.changeExpress(attrpathexp, this.isAutoJoin()));
  }

  /**
   * 设置视图的选择字段单个字段处理 jinjya
   */
  public void addSelectField(String attrPath, String field, String fieldalia) {
    String newattrpath = attrPath;
    if (StringUtil.isSEmptyOrNull(attrPath)) {
      newattrpath = PUBeanPath.RootPath;
    }
    this.addViewField(this.getViewAlias(newattrpath), field, fieldalia,
        this.getFieldType(attrPath, field));
  }

  /**
   * 设置视图的选择字段
   */
  public void addSelectFields(String attrPath, String[] fields,
      String[] fieldalias) {
    String alias = this.getViewAlias(attrPath);
    for (int i = 0; i < fields.length; i++) {
      this.addViewField(alias, fields[i], fieldalias[i],
          this.getFieldType(attrPath, fields[i]));
    }
  }

  /**
   * 批加SELECT字段，默认为根节点
   * 
   * @author jinjya
   * @param fields
   * @param fieldalias
   */
  public void addSelectFields(String[] fields, String[] fieldalias) {
    String alias = this.getViewAlias(PUBeanPath.RootPath);
    for (int i = 0; i < fields.length; i++) {
      this.addViewField(alias, fields[i], fieldalias[i],
          this.getFieldType(PUBeanPath.RootPath, fields[i]));
    }
  }

  /**
   * @param attrPath 元数据路径
   * @param cond 操作符+操作符右边的表达式
   */
  public void addWhere(String attrPath, String cond) {
    if (this.isAutoJoin()) {
      this.fromWhere.addJoinByAttrPath(attrPath, false);
    }
    this.fromWhere.addWhere(attrPath, cond);
  }

  /**
   * @param attrpathexp 元数据路径表达式
   */
  public void addWhereByAttrPathExp(String attrpathexp) {
    this.fromWhere.addWhere(this.changeExpress(attrpathexp, this.isAutoJoin()));
  }

  @Override
  final public void addWherePart(String partwhere) {
    this.fromWhere.addWhere(partwhere);
  }

  /**
   * @param attrPath 元数据路径
   * @return alias
   */
  public String getAlias(String attrPath) {
    if (StringUtil.isSEmptyOrNull(attrPath)) {
      return null;
    }
    return this.fromWhere.getTableAliasByAttrpath(new PUBeanPath(this.bean,
        attrPath).getTranPath());
  }

  /**
   * @param attrPath 元数据路径
   * @param String field
   * @return alias.field
   */
  public String getAliasAndField(String attrPath, String field) {
    String alias = this.getAlias(attrPath);
    if (alias == null) {
      return field;
    }
    return alias + PUFromWhereSQL.DOT + field;
  }

  public JavaType getFieldType(String attrPath, String field) {
    if (StringUtil.isSEmptyOrNull(attrPath) || StringUtil.isSEmptyOrNull(field)) {
      return null;
    }
    PUBeanPath path = new PUBeanPath(this.bean, attrPath);
    IAttribute ar =
        this.bean.getAttributeByPath(StringUtil.isSEmptyOrNull(path
            .getNotStartDotPath()) ? field : path.getNotStartDotPath()
            + PUFromWhereSQL.DOT + field);
    if (ar == null) {
      return null;
    }
    return BDDefaultPKGetValue.attributeToJavaType(ar);
  }

  @Override
  public String getViewSql() {
    StringBuilder bf = new StringBuilder(" select ");
    bf.append(this.getSelectFieldsPart());
    bf.append(" from ");
    bf.append(this.getViewFromPart());
    bf.append(" where ");
    bf.append(this.getViewWherePart());
    if (this.isGroup()) {
      bf.append(" group by ");
      bf.append(this.getGroupFieldsPart());
      if (!StringUtil.isSEmptyOrNull(this.getHaving())) {
        bf.append(" having ");
        bf.append(this.getHaving());
      }
    }
    if (this.isOrder()) {
      bf.append(" order by ");
      bf.append(this.getOrderFieldsPart());
    }
    return bf.toString();
  }

  public boolean isAutoJoin() {
    return this.isAutoJoin;
  }

  /**
   * 动态处理表连接及Where,如果存在“物料分类”，默认该分类及其子分类
   * 
   * @param conds 条件 要求fieldcode 应当是元数据路径
   */
  public void processFromAndWhere(ConditionVO[] conds) {
	  	if(ValueCheckUtil.isNullORZeroLength(conds))
	       return;
	    List<ConditionVO> condList = new ArrayList<ConditionVO>();
	    for(ConditionVO vo : conds){
	      String fieldcode = vo.getFieldCode();
	      if (fieldcode == null) {
	        continue;
	      }
	      // 特殊处理物料分类
	      if (fieldcode.indexOf(MarBasClassVO.PK_MARBASCLASS)>-1
	          && !StringUtil.isSEmptyOrNull(vo.getValue())) {
	        this.dealMaterialClass(fieldcode, vo);
	        continue;
	      }
	       // 特殊处理物料采购分类
        if (fieldcode.indexOf(MarPuClassVO.PK_MARPUCLASS)>-1
            && !StringUtil.isSEmptyOrNull(vo.getValue())) {
          this.dealPUMaterialClass(fieldcode, vo);
          continue;
        }
	      condList.add(vo);
	    }
	    if(!ValueCheckUtil.isNullORZeroLength(condList))
	    	this.fromWhere.processFromAndWhere(CollectionUtils.listToArray(condList));
  }

  /**
   * 处理物料分类及下级
   * 
   * @param materialBasClass
   * @param vo
   */
  private void dealMaterialClass(String materialBasClass, ConditionVO vo) {
    String[] values = FilterItemEqOrIn.getValues(vo);
    this.setMutiMaterialClass(materialBasClass, values);
  }
  

  /**
   * 处理物料采购分类及下级
   * 
   * @param materialBasClass
   * @param vo
   */
  private void dealPUMaterialClass(String materialBasClass, ConditionVO vo) {
    String[] values = FilterItemEqOrIn.getValues(vo);
    this.setPUMutiMaterialClass(materialBasClass, values);
  }
  
  /**
   * 动态处理表连接及Where
   * 
   * @param mapconds key---元数据路径 value 条件
   */
  public void processFromAndWhere(Map<String, List<ConditionVO>> mapconds) {
    this.fromWhere.processFromAndWhere(mapconds);
  }

  public void setAutoJoin(boolean isAutoJoin) {
    this.isAutoJoin = isAutoJoin;
  }

  /**
   * 添加非末级物料基本分类条件，查询本级及本级以下的物料基本分类
   * <p>
   * 不管isAutoJoinn是否预置，都会强制关联
   * </p>
   * 
   * @param attrPath 物料-物料基本分类元数据路径如：cmaterialoid.pk_marbasclass
   * @param pk_docs 物料基本分类PK
   */
  public void setMutiMaterialClass(String attrPath, String[] pk_docs) {
    String[] pk_classes = this.queryMaterilClass(pk_docs);
    if (ValueCheckUtil.isNullORZeroLength(pk_classes)) {
      return;
    }
    this.fromWhere.addJoinByAttrPath(attrPath, false);
    PUBeanPath path = new PUBeanPath(this.bean, attrPath);
    String whereSql =
        SqlIn.formInSQL(
            this.getAliasAndField(path.getParentPath(), path.getLastPath()),
            pk_classes);
    this.fromWhere.addWhere(whereSql);
  }

  /**
   * 添加非末级物料采购分类条件，查询本级及本级以下的物料采购分类
   * <p>
   * 不管isAutoJoinn是否预置，都会强制关联
   * </p>
   * 
   * @param attrPath 物料-物料采购分类元数据路径如：cmaterialoid.pk_marbasclass
   * @param pk_docs 物料采购分类PK
   */
  public void setPUMutiMaterialClass(String attrPath, String[] pk_docs) {
    this.fromWhere.addJoinByAttrPath(attrPath, false);
    PUBeanPath path = new PUBeanPath(this.bean, attrPath);
    String whereSql =
        SqlIn.formInSQL(
            this.getAliasAndField(path.getParentPath(), path.getLastPath()),
            pk_docs);
    this.fromWhere.addWhere(whereSql);
  }
  
  /**
   * 设置物料分类按级次汇总
   * 通过表连接实现
   * 
   * @param String maclasspath 物料分类元数据路径
   * @param String selclfield 选择的物料分类上汇总依据字段
   * @param int ilevel 汇总的级次
   * 
   */
  public void addMaterialClassGroup(String maclasspath, String innercodealias,
      int ilevel) {
    this.fromWhere.addJoinByAttrPath(maclasspath, true);
    String tabalias = this.fromWhere.getTableAliasByAttrpath(maclasspath);
    int innerlen = ilevel * 4;
    this.addExpressField(" substring(" + tabalias + "."
        + MarBasClassVO.INNERCODE + ",1," + innerlen + ")", innercodealias,
        JavaType.String);
  }

  /**
   * 设置物料分类按级次汇总
   * 通过表连接实现
   * 
   * @param String maclasspath 物料分类元数据路径
   * @param String selclfield 选择的物料分类上汇总依据字段
   * @param int ilevel 汇总的级次
   * 
   */
  public void addMaterialClassGroup(String maclasspath, String selclfield,
      String selclalias, int ilevel) {
    this.fromWhere.addJoinByAttrPath(maclasspath, true);
    String tabalias = this.fromWhere.getTableAliasByAttrpath(maclasspath);
    MarBasClassVO baseClassVO = new MarBasClassVO();
    int innerlen = ilevel * 4;
    final String inneralias = "macl";
    StringBuilder bf = new StringBuilder(" left outer join ");
    bf.append(baseClassVO.getTableName() + " " + inneralias);
    bf.append(" on ( ");
    bf.append(" substring(" + tabalias + "." + MarBasClassVO.INNERCODE + ",1,"
        + innerlen + " ) ");
    bf.append(" = ");
    bf.append(inneralias + "." + MarBasClassVO.INNERCODE);
    bf.append(" ) ");
    this.fromWhere.addJoin(bf.toString(), maclasspath + "."
        + MarBasClassVO.PK_PARENT, inneralias);
    this.addExpressField(inneralias + "." + selclfield, selclalias,
        JavaType.String);
  }

  @Override
  @Deprecated
  final public void setViewFields(ViewField[] fields) {
    super.setViewFields(fields);
  }

  /**
   * 设置视图的汇总字段
   */
  public void setViewSumFields(String attrPath, String[] fields) {
    ViewField[] viewfields = new ViewField[fields.length];
    for (int i = 0; i < viewfields.length; i++) {
      viewfields[i] =
          new ViewField(fields[i], "sum("
              + this.getAliasAndField(attrPath, fields[i]) + ")",
              JavaType.UFDouble);
    }
    this.setViewSumFields(viewfields);
  }

  /**
   * 设置视图的汇总字段
   */
  public void setViewSumFields(String[] attrPaths, String[] fields) {
    ViewField[] viewfields = new ViewField[attrPaths.length];
    for (int i = 0; i < viewfields.length; i++) {
      viewfields[i] =
          new ViewField(fields[i], "sum("
              + this.getAliasAndField(attrPaths[i], fields[i]) + ")",
              JavaType.UFDouble);
    }
    this.setViewSumFields(viewfields);
  }

  /**
   * <p>
   * 设置视图的表达式字段，表达式的变量以元数据路径形式存在<br>
   * 如：t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * 其中:如果变量就使用当前实体的属性，需要在当前属性前加.;如.x1;表示当前实体<br>
   * 如果是关联实体的属性,则可直接如：t.f1,也可.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express 表达式
   * @param fieldalias 表达式别名
   */
  public void setViewSumFieldsByAttrExp(String[] express, String[] fieldalias) {
    ViewField[] vfs = new ViewField[express.length];
    for (int i = 0; i < vfs.length; i++) {
      vfs[i] =
          this.changeViewField(express[i], fieldalias[i], JavaType.UFDouble,
              this.isAutoJoin());
    }
    this.setViewSumFields(vfs);
  }

  @Override
  @Deprecated
  final public void setWhere(String where) {
    this.fromWhere.addWhere(where);
  }

  private IBean getBeanByVO(Class<? extends SuperVO> clazz) {
    IBean beanentry = null;
    try {
      beanentry =
          MDBaseQueryFacade.getInstance().getBeanByFullClassName(
              clazz.getName());
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    return beanentry;
  }

  /**
   * 获得档案通用查询接口
   * 
   * @param classTypeID
   * @return
   */
  private IGeneralAccessor getGeneralAccessor(String classTypeID) {
    if (classTypeID == null) {
      return null;
    }
    return GeneralAccessorFactory.getAccessor(classTypeID);
  }

  /**
   * 查询表别名
   * 
   * @param attrPath
   * @return
   */
  private String getViewAlias(String attrPath) {
    String alias = null;
    if (StringUtil.isSEmptyOrNull(attrPath)) {
      alias = this.getBeanTableAlias();
    }
    else {
      alias = this.getAlias(attrPath);
    }
    if (alias == null && this.isAutoJoin()) {
      this.fromWhere.addJoinByAttrPath(attrPath, false);
      alias = this.getAlias(attrPath);
    }
    return alias;
  }

  /**
   * 根据物料基本分类PK查询该分类所有下级物料基本分类PK
   * 
   * @param pk_docs
   */
  private String[] queryMaterilClass(String[] pk_docs) {
    String pk_org = AppContext.getInstance().getPkGroup();
    IGeneralAccessor ass =
        this.getGeneralAccessor(IBDMetaDataIDConst.MARBASCLASS);
    List<IBDData> bdDataList = new ArrayList<IBDData>();
    for (String pk_doc : pk_docs) {
      List<IBDData> dblist = ass.getChildDocs(pk_org, pk_doc, true);
      // 当物料分类为组织级的，用Pk_group查组织级的物料及其下级时，会查不到，应该根据自己所属的组织查询
      if (dblist == null || dblist.size() == 0) {
        IBDData bd = ass.getDocByPk(pk_doc);
        dblist = ass.getChildDocs(bd.getPk_org(), pk_doc, true);
      }
      bdDataList.addAll(dblist);
    }
    if (ValueCheckUtil.isNullORZeroLength(bdDataList)) {
      return pk_docs;
    }   
    List<String> pkList = new ArrayList<String>();
    for (IBDData bdData : bdDataList) {
      pkList.add(bdData.getPk());
    }
    return pkList.toArray(new String[0]);
  }

  /**
   * 设置一些固定的表连接，这些连接不要保证在外部传入已存在的FromWhere不存在，以避免发生冲突 如: <br>
   * <code>
   * left join table2 t2 on(t1.f1=t2.f1 and t1.f2=t2.f2)
   * inner join table3 t3 on(t1.f3=t3.f3)
   * </code>
   * 
   * @return
   */
  final protected void addJoin(String join) {
    this.fromWhere.addJoin(join);
  }

  /**
   * 一些特殊的实体连接，元数据上无法表达这些连接时使用 对于这些实体，元数据路径表达为 rightbean_pkfield.f*
   */
  protected void addJoin(String[] thisfields, VOMeta rightbean,
      String[] rightfields, boolean isleft) {
    StringBuilder bf =
        new StringBuilder(isleft ? " left join " : " inner join ");
    String righttable =
        rightbean.getPrimaryAttribute().getColumn().getTable().getName();
    bf.append(righttable + " ");
    bf.append(righttable + " on(");
    for (int i = 0; i < thisfields.length; i++) {
      bf.append(this.getBeanTableAlias() + "." + thisfields[i]);
      bf.append(" = ");
      bf.append(righttable + "." + rightfields[i]);
    }
    bf.append(") ");
    this.fromWhere.addJoin(bf.toString());
  }

  /**
   * @param attrMetaPath 元数据路径
   * @param isleft 是否左连接，如果是，则元数据路径上的所有表都做左连接
   */
  final protected void addJoinByAttrPath(String attrPath, boolean isleft) {
    this.fromWhere.addJoinByAttrPath(attrPath, isleft);
  }

  /**
   * 设置一些固定的条件，这些条件必须保证表连接存在才能设置 如: <br>
   * <code>
   * t.dr=0 and t.field1='ss'
   * </code>
   * 
   * @return
   */
  final protected void addWhere(String where) {
    this.fromWhere.addWhere(where);
  }

  /**
   * <p>
   * 将元数据表达式自动转换为Sql表达式，其中，元数据表达式的变量以元数据路径形式存在<br>
   * 如：t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * 其中:如果变量就使用当前实体的属性，需要在当前属性前加.;如.x1;表示当前实体<br>
   * 如果是关联实体的属性,则可直接如：t.f1,也可.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express 表达式
   * @param fieldalias 表达式别名
   * @param boolean isAddjoin 如果当前视图未关联表达式需要的表，是否自动关联
   */
  final protected String changeExpress(String express, boolean isAddjoin) {
    if (StringUtil.isSEmptyOrNull(express)) {
      return null;
    }
    String alias = null;
    PUBeanPath PUBeanPath = null;
    Matcher mt = this.pt.matcher(express);
    StringBuffer sf = new StringBuffer();
    while (mt.find()) {
      String path = mt.group();
      if (StringUtil.isSEmptyOrNull(path)) {
        continue;
      }
      PUBeanPath = new PUBeanPath(this.bean, path);
      if (PUBeanPath.isExtTableAttr()) {
        alias = this.fromWhere.getTableAliasByAttrpath(PUBeanPath.getTranPath());
      }
      else {
        alias = this.getAlias(PUBeanPath.getParentPath());
      }
      if (alias == null && isAddjoin) {
        if (PUBeanPath.isExtTableAttr()) {
          this.addJoinByAttrPath(PUBeanPath.getNotStartDotPath(), false);
          alias =
              this.fromWhere.getTableAliasByAttrpath(PUBeanPath.getTranPath());
        }
        else {
          this.addJoinByAttrPath(PUBeanPath.getParentPath(), false);
          alias = this.getAlias(PUBeanPath.getParentPath());
        }
      }
      if (alias == null) {
        continue;
      }
      String stemp = alias + PUFromWhereSQL.DOT + PUBeanPath.getLastPath();
      // 替换到当前字符串
      mt.appendReplacement(sf, stemp);
    }
    // 追加后面部分字符串
    mt.appendTail(sf);
    return sf.toString();
  }

  /**
   * <p>
   * 将元数据表达式自动转换为ViewField，其中，元数据表达式的变量以元数据路径形式存在<br>
   * 如：coalese(m1.m2.m5,.fx)<br>
   * 其中:如果变量就使用当前实体的属性，需要在当前属性前加.;如.x1;表示当前实体<br>
   * 如果是关联实体的属性,则可直接如：t.f1,也可.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express 表达式
   * @param fieldalias 表达式别名
   */
  final protected ViewField changeViewField(String express, String fieldalias) {
    return this.changeViewField(express, fieldalias, null, false);
  }

  /**
   * <p>
   * 将元数据表达式自动转换为ViewField，其中，元数据表达式的变量以元数据路径形式存在<br>
   * 如：t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * 其中:如果变量就使用当前实体的属性，需要在当前属性前加.;如.x1;表示当前实体<br>
   * 如果是关联实体的属性,则可直接如：t.f1,也可.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express 表达式
   * @param fieldalias 表达式别名
   * @param JavaType type 表达式类型
   * @param boolean isAddjoin 如果当前视图未关联表达式需要的表，是否自动关联
   */
  final protected ViewField changeViewField(String express, String fieldalias,
      JavaType type, boolean isAddjoin) {
    if (StringUtil.isSEmptyOrNull(express)) {
      return null;
    }
    String newexpress = this.changeExpress(express, isAddjoin);
    return new ViewField(fieldalias, newexpress, type == null ? JavaType.Object
        : type);
  }

  final protected String getBeanTableAlias() {
    return this.fromWhere.getBeanTableAlias();
  }

  /**
   * 返回视图不带from的全部表连接
   * 
   * @return
   */
  public String getViewFromPart() {
    return this.fromWhere.getFrom();
  }

  /**
   * 返回视图不带Where的全部条件
   * 
   * @return
   */
  public String getViewWherePart() {
    return this.fromWhere.getWhere();
  }

  /**
   * 返回想设置固定表别名，这些表别名不要保证在外部传入已存在的FromWhere不存在，以避免发生冲突<br>
   * 如:<br>
   * <code>
   * Map<String,String> map = new Hash<String,String>();
   * map.put(bean.cmaterialid,materialbas);
   * map.put(bean.pk_org,stock);
   * </code>
   * 
   * @return
   */
  protected Map<String, String> initFixAlias() {
    return null;
  }

  /**
   * 设置一些固定的表连接，这些连接不要保证在外部传入已存在的FromWhere不存在，以避免发生冲突 如: <br>
   * <code>
   * left join table2 t2 on(t1.f1=t2.f1 and t1.f2=t2.f2)
   * inner join table3 t3 on(t1.f3=t3.f3)
   * </code>
   * 
   * @return
   */
  protected void initFixTableJoin() {
    return;
  }

  /**
   * 设置一些固定的条件，这些条件必须保证表连接存在才能设置 如: <br>
   * <code>
   * t.dr=0 and t.field1='ss'
   * </code>
   * 
   * @return
   */
  protected void initFixWhere() {
    return;
  }

  final protected void initThisView() {
    if (this.fromWhere == null) {
      this.fromWhere = new PUFromWhereSQL(this.bean);
    }
    Map<String, String> mapAlias = this.initFixAlias();
    this.fromWhere.setFixTableAliasMap(mapAlias);

    if (StringUtil.isSEmptyOrNull(this.fromWhere.getFrom())) {
      this.fromWhere.addJoin(this.fromWhere.getBeanTable() + " "
          + this.fromWhere.getBeanTableAlias());
    }
    this.initFixTableJoin();
    this.initFixWhere();
  }

  final protected void addJoin(String joinstr, String PUBeanPath,
      String tablealias) {
    this.fromWhere.addJoin(joinstr, PUBeanPath, tablealias);
  }

  final IBean getBean() {
    return this.bean;
  }

  final protected PUFromWhereSQL getPUFromWhereSQL() {
    if (this.fromWhere == null)
      this.fromWhere = new PUFromWhereSQL(getBean());
    return this.fromWhere;
  }

}

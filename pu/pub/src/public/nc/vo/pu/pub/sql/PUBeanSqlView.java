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
 * <li>��ȫ�ع�Sql��View,���ص㣺</li>
 * <li>���������ƶ������ �����������ӱ���˫������</li>
 * <li>�ȿ������ѯ�Ի���Ĳ�����IQueryScheme���ʹ��</li>
 * <li>Ҳ�����봫ͳ�Ի��������ConditionVO���ʹ��</li>
 * </p>
 * 
 * @since 6.0
 * @version 2011-6-10 ����09:48:00
 * @author yangb
 */

public class PUBeanSqlView extends ICView {

  private static final long serialVersionUID = 2011061009484300001L;

  // ʵ��
  private IBean bean;

  private PUFromWhereSQL fromWhere;

  // ������select field �� where ʱ�Ƿ��Զ�����ʵ��
  private boolean isAutoJoin = false;

  // Ԫ����·��������ʽ
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
   * ������ͼ�ı��ʽ�ֶΣ����ʽ�ı�����Ԫ����·����ʽ����<br>
   * �磺t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * ����:���������ʹ�õ�ǰʵ������ԣ���Ҫ�ڵ�ǰ����ǰ��.;��.x1;��ʾ��ǰʵ��<br>
   * ����ǹ���ʵ�������,���ֱ���磺t.f1,Ҳ��.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express ���ʽ
   * @param fieldalias ���ʽ����
   */
  public void addExpress(String express, String fieldalias, JavaType type) {
    this.addViewFields(new ViewField[] {
      this.changeViewField(express, fieldalias, type, this.isAutoJoin())
    });
  }

  /**
   * @param attrpathexp Ԫ����·�����ʽ
   */
  public void addHavingConditonByAttrPathExp(String attrpathexp) {
    this.addHavingWherePart(this.changeExpress(attrpathexp, this.isAutoJoin()));
  }

  /**
   * ������ͼ��ѡ���ֶε����ֶδ��� jinjya
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
   * ������ͼ��ѡ���ֶ�
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
   * ����SELECT�ֶΣ�Ĭ��Ϊ���ڵ�
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
   * @param attrPath Ԫ����·��
   * @param cond ������+�������ұߵı��ʽ
   */
  public void addWhere(String attrPath, String cond) {
    if (this.isAutoJoin()) {
      this.fromWhere.addJoinByAttrPath(attrPath, false);
    }
    this.fromWhere.addWhere(attrPath, cond);
  }

  /**
   * @param attrpathexp Ԫ����·�����ʽ
   */
  public void addWhereByAttrPathExp(String attrpathexp) {
    this.fromWhere.addWhere(this.changeExpress(attrpathexp, this.isAutoJoin()));
  }

  @Override
  final public void addWherePart(String partwhere) {
    this.fromWhere.addWhere(partwhere);
  }

  /**
   * @param attrPath Ԫ����·��
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
   * @param attrPath Ԫ����·��
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
   * ��̬��������Ӽ�Where,������ڡ����Ϸ��ࡱ��Ĭ�ϸ÷��༰���ӷ���
   * 
   * @param conds ���� Ҫ��fieldcode Ӧ����Ԫ����·��
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
	      // ���⴦�����Ϸ���
	      if (fieldcode.indexOf(MarBasClassVO.PK_MARBASCLASS)>-1
	          && !StringUtil.isSEmptyOrNull(vo.getValue())) {
	        this.dealMaterialClass(fieldcode, vo);
	        continue;
	      }
	       // ���⴦�����ϲɹ�����
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
   * �������Ϸ��༰�¼�
   * 
   * @param materialBasClass
   * @param vo
   */
  private void dealMaterialClass(String materialBasClass, ConditionVO vo) {
    String[] values = FilterItemEqOrIn.getValues(vo);
    this.setMutiMaterialClass(materialBasClass, values);
  }
  

  /**
   * �������ϲɹ����༰�¼�
   * 
   * @param materialBasClass
   * @param vo
   */
  private void dealPUMaterialClass(String materialBasClass, ConditionVO vo) {
    String[] values = FilterItemEqOrIn.getValues(vo);
    this.setPUMutiMaterialClass(materialBasClass, values);
  }
  
  /**
   * ��̬��������Ӽ�Where
   * 
   * @param mapconds key---Ԫ����·�� value ����
   */
  public void processFromAndWhere(Map<String, List<ConditionVO>> mapconds) {
    this.fromWhere.processFromAndWhere(mapconds);
  }

  public void setAutoJoin(boolean isAutoJoin) {
    this.isAutoJoin = isAutoJoin;
  }

  /**
   * ��ӷ�ĩ�����ϻ���������������ѯ�������������µ����ϻ�������
   * <p>
   * ����isAutoJoinn�Ƿ�Ԥ�ã�����ǿ�ƹ���
   * </p>
   * 
   * @param attrPath ����-���ϻ�������Ԫ����·���磺cmaterialoid.pk_marbasclass
   * @param pk_docs ���ϻ�������PK
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
   * ��ӷ�ĩ�����ϲɹ�������������ѯ�������������µ����ϲɹ�����
   * <p>
   * ����isAutoJoinn�Ƿ�Ԥ�ã�����ǿ�ƹ���
   * </p>
   * 
   * @param attrPath ����-���ϲɹ�����Ԫ����·���磺cmaterialoid.pk_marbasclass
   * @param pk_docs ���ϲɹ�����PK
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
   * �������Ϸ��ఴ���λ���
   * ͨ��������ʵ��
   * 
   * @param String maclasspath ���Ϸ���Ԫ����·��
   * @param String selclfield ѡ������Ϸ����ϻ��������ֶ�
   * @param int ilevel ���ܵļ���
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
   * �������Ϸ��ఴ���λ���
   * ͨ��������ʵ��
   * 
   * @param String maclasspath ���Ϸ���Ԫ����·��
   * @param String selclfield ѡ������Ϸ����ϻ��������ֶ�
   * @param int ilevel ���ܵļ���
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
   * ������ͼ�Ļ����ֶ�
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
   * ������ͼ�Ļ����ֶ�
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
   * ������ͼ�ı��ʽ�ֶΣ����ʽ�ı�����Ԫ����·����ʽ����<br>
   * �磺t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * ����:���������ʹ�õ�ǰʵ������ԣ���Ҫ�ڵ�ǰ����ǰ��.;��.x1;��ʾ��ǰʵ��<br>
   * ����ǹ���ʵ�������,���ֱ���磺t.f1,Ҳ��.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express ���ʽ
   * @param fieldalias ���ʽ����
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
   * ��õ���ͨ�ò�ѯ�ӿ�
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
   * ��ѯ�����
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
   * �������ϻ�������PK��ѯ�÷��������¼����ϻ�������PK
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
      // �����Ϸ���Ϊ��֯���ģ���Pk_group����֯�������ϼ����¼�ʱ����鲻����Ӧ�ø����Լ���������֯��ѯ
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
   * ����һЩ�̶��ı����ӣ���Щ���Ӳ�Ҫ��֤���ⲿ�����Ѵ��ڵ�FromWhere�����ڣ��Ա��ⷢ����ͻ ��: <br>
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
   * һЩ�����ʵ�����ӣ�Ԫ�������޷������Щ����ʱʹ�� ������Щʵ�壬Ԫ����·�����Ϊ rightbean_pkfield.f*
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
   * @param attrMetaPath Ԫ����·��
   * @param isleft �Ƿ������ӣ�����ǣ���Ԫ����·���ϵ����б���������
   */
  final protected void addJoinByAttrPath(String attrPath, boolean isleft) {
    this.fromWhere.addJoinByAttrPath(attrPath, isleft);
  }

  /**
   * ����һЩ�̶�����������Щ�������뱣֤�����Ӵ��ڲ������� ��: <br>
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
   * ��Ԫ���ݱ��ʽ�Զ�ת��ΪSql���ʽ�����У�Ԫ���ݱ��ʽ�ı�����Ԫ����·����ʽ����<br>
   * �磺t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * ����:���������ʹ�õ�ǰʵ������ԣ���Ҫ�ڵ�ǰ����ǰ��.;��.x1;��ʾ��ǰʵ��<br>
   * ����ǹ���ʵ�������,���ֱ���磺t.f1,Ҳ��.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express ���ʽ
   * @param fieldalias ���ʽ����
   * @param boolean isAddjoin �����ǰ��ͼδ�������ʽ��Ҫ�ı��Ƿ��Զ�����
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
      // �滻����ǰ�ַ���
      mt.appendReplacement(sf, stemp);
    }
    // ׷�Ӻ��沿���ַ���
    mt.appendTail(sf);
    return sf.toString();
  }

  /**
   * <p>
   * ��Ԫ���ݱ��ʽ�Զ�ת��ΪViewField�����У�Ԫ���ݱ��ʽ�ı�����Ԫ����·����ʽ����<br>
   * �磺coalese(m1.m2.m5,.fx)<br>
   * ����:���������ʹ�õ�ǰʵ������ԣ���Ҫ�ڵ�ǰ����ǰ��.;��.x1;��ʾ��ǰʵ��<br>
   * ����ǹ���ʵ�������,���ֱ���磺t.f1,Ҳ��.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express ���ʽ
   * @param fieldalias ���ʽ����
   */
  final protected ViewField changeViewField(String express, String fieldalias) {
    return this.changeViewField(express, fieldalias, null, false);
  }

  /**
   * <p>
   * ��Ԫ���ݱ��ʽ�Զ�ת��ΪViewField�����У�Ԫ���ݱ��ʽ�ı�����Ԫ����·����ʽ����<br>
   * �磺t.t1.f1+coalese(m1.m2.m5,m1.m2.f1)+.fx<br>
   * ����:���������ʹ�õ�ǰʵ������ԣ���Ҫ�ڵ�ǰ����ǰ��.;��.x1;��ʾ��ǰʵ��<br>
   * ����ǹ���ʵ�������,���ֱ���磺t.f1,Ҳ��.t.f1 <br>
   * </p>
   * /**
   * 
   * @param express ���ʽ
   * @param fieldalias ���ʽ����
   * @param JavaType type ���ʽ����
   * @param boolean isAddjoin �����ǰ��ͼδ�������ʽ��Ҫ�ı��Ƿ��Զ�����
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
   * ������ͼ����from��ȫ��������
   * 
   * @return
   */
  public String getViewFromPart() {
    return this.fromWhere.getFrom();
  }

  /**
   * ������ͼ����Where��ȫ������
   * 
   * @return
   */
  public String getViewWherePart() {
    return this.fromWhere.getWhere();
  }

  /**
   * ���������ù̶����������Щ�������Ҫ��֤���ⲿ�����Ѵ��ڵ�FromWhere�����ڣ��Ա��ⷢ����ͻ<br>
   * ��:<br>
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
   * ����һЩ�̶��ı����ӣ���Щ���Ӳ�Ҫ��֤���ⲿ�����Ѵ��ڵ�FromWhere�����ڣ��Ա��ⷢ����ͻ ��: <br>
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
   * ����һЩ�̶�����������Щ�������뱣֤�����Ӵ��ڲ������� ��: <br>
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

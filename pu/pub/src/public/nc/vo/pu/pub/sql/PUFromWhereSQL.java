package nc.vo.pu.pub.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.md.util.MDUtil;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.SuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * FromWhereSQL�ļ�װ���࣬�����˼򵥵��߼�
 * 
 * @since 6.0
 * @version 2011-6-9 ����11:53:28
 * @author yangb
 */

public class PUFromWhereSQL implements FromWhereSQL {

  public static final char DOT = '.';

  private static final long serialVersionUID = 2011060123480002L;

  // ʵ��
  private IBean bean;

  // �̶��ı���� attrmetapath---tablealias ���ܴ��ڱ�����ͻ
  private Map<String, String> fixTableAliasMap;

  private boolean isJoinStyle = true;

  private StringBuilder sqlJoin = new StringBuilder();

  private StringBuilder sqlWhere = new StringBuilder();

  // ����� attrmetapath---tablealias
  private Map<String, String> tableAliasMap = new HashMap<String, String>();

  // ����������� tablename--count;
  private Map<String, Integer> tableCountMap = new HashMap<String, Integer>();

  /**
   * @param bean
   */
  public PUFromWhereSQL(IBean beanentry) {
    this(beanentry, null, null, null, true);
  }

  /**
   * @param bean
   * @param from ����From
   * @param where ����where ���Ҳ���and��ͷ
   * @param style From�Ƿ�Join���Ĭ����join���
   * @param tableAliasMap �ѹ����ı���� key--Ԫ����·�� value--�����
   */
  public PUFromWhereSQL(IBean beanentry, String from, String where,
      Map<String, String> tableAliasMap, boolean style) {
    this.init(beanentry, from, where, tableAliasMap, style);
  }

  /**
   * @param beanvo
   */
  public PUFromWhereSQL(SuperVO beanvo) {
    this(beanvo, null, null, null, true);
  }

  /**
   * @param bean
   * @param from ����From
   * @param where ����where ���Ҳ���and��ͷ
   * @param style From�Ƿ�Join���Ĭ����join���
   * @param tableAliasMap �ѹ����ı���� key--Ԫ����·�� value--�����
   */
  public PUFromWhereSQL(SuperVO beanvo, String from, String where,
      Map<String, String> tableAliasMap, boolean style) {
    IBean beanentry = null;
    try {
      beanentry =
        MDBaseQueryFacade.getInstance().getBeanByFullClassName(
            beanvo.getClass().getName());
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    this.init(beanentry, from, where, tableAliasMap, style);
  }

  /**
   * @param attrPath Ԫ����·��
   * @param cond ������+�������ұߵı��ʽ
   */
  public void addJoinAndWhere(String attrPath, String cond) {
    if (StringUtil.isSEmptyOrNull(attrPath) || StringUtil.isSEmptyOrNull(cond)) {
      return;
    }
    PUBeanPath path = new PUBeanPath(this.getBean(), attrPath);
    if (!path.isRootPath()) {
      if ("dr".equalsIgnoreCase(path.getLastPath())
          || "ts".equalsIgnoreCase(path.getLastPath())) {
        this.addJoinByAttrPath(path.getParentPath(), false);
      }
      else {
        this.addJoinByAttrPath(path.getNotStartDotPath(), false);
      }
    }
    this.addWhere(path.getNotStartDotPath(), cond);
  }

  /**
   * @param attrMetaPath Ԫ����·��
   * @param isleft �Ƿ������ӣ�����ǣ���Ԫ����·���ϵ����б���������
   */
  public void addJoinByAttrPath(String attrMetaPath, boolean isleft) {

    if (StringUtil.isSEmptyOrNull(attrMetaPath)) {
      return;
    }
    PUBeanPath path = new PUBeanPath(this.getBean(), attrMetaPath);
    if (path.isRootPath()) {
      return;
    }
    IAttribute ar =
      this.getBean().getAttributeByPath(path.getStrNotStartPath());
    if (ar == null
        || !StringUtil.isSEmptyOrNull(this.getTableAliasByAttrpath(path
            .getTranPath()))) {
      return;
    }

    List<PUJoinInfo> lPUJoinInfos =
      path.fetchAllParentTablesByMetaPath(this.getBean());
    String leftalias = this.getBeanTableAlias();
    for (PUJoinInfo info : lPUJoinInfos) {
      if (StringUtil.isStringEqual(info.getLefttable(), this.getBeanTable())) {
        info.setLeftalias(this.getBeanTableAlias());
      }
      else {
        info.setLeftalias(leftalias);
      }
      String newrightalias =
        this.getNewTableAlias(info.getAttrPath(), info.getRighttable());
      if (StringUtil.isSEmptyOrNull(newrightalias)) {
        leftalias = this.getTableAliasByAttrpath(info.getAttrPath());
        continue;
      }
      info.setRightalias(newrightalias);
      info.setBleft(isleft);
      this.sqlJoin.append(info.getJoinString(false).toString());
      leftalias = newrightalias;
    }
  }

  /**
   * @param alias �����
   * @param cond
   */
  public void addWhere(String alias, ConditionVO cond) {
    if (cond == null) {
      return;
    }
    String stempbak = cond.getFieldCode();
    if (!StringUtil.isSEmptyOrNull(alias)) {
      String stemp = null;
      stemp = cond.getFieldCode();
      int pos = stemp.lastIndexOf(PUFromWhereSQL.DOT);
      if (pos < 0) {
        stemp = alias + PUFromWhereSQL.DOT + stemp;
      }
      else {
        stemp = alias + PUFromWhereSQL.DOT + stemp.substring(pos + 1);
      }
      cond.setFieldCode(stemp);
      String val = cond.getValue();
      // ��Щ��ѯ����ֵΪnull�����ӷǿ��ж���������ֿ�ָ���쳣���� �� fieldcode is not null...
      if (null != val) {
        val = val.trim();
        cond.setValue(val);
      }
    }
    this.addWhere(cond.getSQLStr());
    cond.setFieldCode(stempbak);
  }

  /**
   * @ru
   * @param attrPath Ԫ����·��
   * @param cond ������+�������ұߵı��ʽ
   */
  public void addWhere(String attrPath, String cond) {
    if (StringUtil.isSEmptyOrNull(attrPath) || StringUtil.isSEmptyOrNull(cond)) {
      return;
    }
    PUBeanPath path = new PUBeanPath(this.getBean(), attrPath);
    if (path.isRootPath()) {
      this.addWhere(this.getTableAliasByAttrpath(PUBeanPath.RootPath), path
          .getLastPath(), cond);
      return;
    }
    if ("dr".equalsIgnoreCase(path.getLastPath())
        || "ts".equalsIgnoreCase(path.getLastPath())) {
      if (StringUtil.isSEmptyOrNull(path.getParentPath())) {
        this.addWhere(this.getTableAliasByAttrpath(PUBeanPath.RootPath), path
            .getLastPath(), cond);
        return;
      }
      PUBeanPath parentpath = new PUBeanPath(this.getBean(), path.getParentPath());
      this.addWhere(this.getTableAliasByAttrpath(parentpath.getTranPath()),
          path.getLastPath(), cond);
    }
    else {
      if (path.isExtTableAttr()) {
        this.addWhere(this.getTableAliasByAttrpath(path.getTranPath()), path
            .getLastPath(), cond);
        return;
      }
      if (StringUtil.isSEmptyOrNull(path.getParentPath())) {
        this.addWhere(this.getTableAliasByAttrpath(PUBeanPath.RootPath), path
            .getLastPath(), cond);
        return;
      }
      PUBeanPath parentpath = new PUBeanPath(this.getBean(), path.getParentPath());
      this.addWhere(this.getTableAliasByAttrpath(parentpath.getTranPath()),
          path.getLastPath(), cond);
    }
  }

  /**
   * @param alias �����
   * @param field �ֶ�
   * @param cond ������+�������ұߵı��ʽ
   */
  public void addWhere(String alias, String field, String cond) {
    this.addWhere((StringUtil.isSEmptyOrNull(alias) ? "" : alias)
        + PUFromWhereSQL.DOT + field + cond);
  }

  @Override
  public String getFrom() {
    return this.sqlJoin.toString();
  }

  @Override
  public String getTableAliasByAttrpath(String attrpath) {
    if (StringUtil.isSEmptyOrNull(attrpath)) {
      return null;
    }
    return this.tableAliasMap.get(attrpath);
  }

  @Override
  public String getWhere() {
    return this.sqlWhere.toString();
  }

  final public boolean isJoinStyle() {
    return this.isJoinStyle;
  }

  /**
   * ��������Ӽ�Where
   * 
   * @param conds ���� Ҫ��fieldcode Ӧ����Ԫ����·��
   */
  public void processFromAndWhere(ConditionVO[] conds) {
    Map<String, List<ConditionVO>> mapconds =
      new HashMap<String, List<ConditionVO>>();
    IAttribute attr = null;
    List<ConditionVO> lvos = null;
    for (ConditionVO cond : conds) {
      PUBeanPath path = new PUBeanPath(this.getBean(), cond.getFieldCode());
      attr = this.getBean().getAttributeByPath(path.getStrNotStartPath());
      if (attr == null) {
        continue;
      }
      String key = null;
      if (path.isExtTableAttr() || MDUtil.isCollectionType(attr.getDataType())) {
        key = cond.getFieldCode();
      }
      else {
        key = path.getParentPath();
      }
      lvos = mapconds.get(key);
      if (lvos == null) {
        lvos = new ArrayList<ConditionVO>();
        mapconds.put(key, lvos);
      }
      lvos.add(cond);
    }
    this.processFromAndWhere(mapconds);
  }

  /**
   * ��������Ӽ�Where
   * 
   * @param mapconds key---Ԫ����·�� value ����
   */
  public void processFromAndWhere(Map<String, List<ConditionVO>> mapconds) {
    if (ValueCheckUtil.isNullORZeroLength(mapconds)) {
      return;
    }
    for (Map.Entry<String, List<ConditionVO>> en : mapconds.entrySet()) {
      List<ConditionVO> lconds = en.getValue();
      PUBeanPath path = new PUBeanPath(this.getBean(), en.getKey());
      this.addJoinByAttrPath(path.getNotStartDotPath(), false);
      String tablealias = this.getTableAliasByAttrpath(path.getTranPath());
      for (ConditionVO cond : lconds) {
        this.addWhere(tablealias, cond);
      }
    }
  }

  private String getNewTableAlias(String attrmetaname, String tablename) {
    String alias = this.getTableAliasByAttrpath(attrmetaname);
    if (StringUtil.isSEmptyOrNull(alias)) {
      alias = this.getFixTableAlias(attrmetaname);
      if (StringUtil.isSEmptyOrNull(alias)) {
        Integer count = this.tableCountMap.get(tablename);
        if (count == null) {
          this.tableCountMap.put(tablename, Integer.valueOf(1));
          alias = tablename;
        }
        else {
          alias = tablename + count.toString();
          count = Integer.valueOf(count.intValue() + 1);
          this.tableCountMap.put(tablename, count);
        }
      }
      this.tableAliasMap.put(attrmetaname, alias);
      return alias;
    }
    return null;
  }

  final protected void addJoin(String join) {
    if (StringUtil.isSEmptyOrNull(join)) {
      return;
    }
    this.sqlJoin.append(join);
  }

  final protected void addWhere(String where) {
    if (StringUtil.isSEmptyOrNull(where)) {
      return;
    }
    // �������С�ڵ���0��˵����һ����������ȥ����ʼ��and��or
    if (this.sqlWhere.length() <= 0) {
      String con = where.toString().trim();
      if(PUSqlUtil.isStartWithAndOr(con)){
        int index = con.indexOf(" ");
        this.sqlWhere.append(con.substring(index + 1));
      }else{
        this.sqlWhere.append(con);
      }
    }
    else {
      PUSqlUtil.andTowWhere(this.sqlWhere, where);
    }
  }

  final protected IBean getBean() {
    return this.bean;
  }

  final protected String getBeanTable() {
    return this.bean.getTable().getName();
  }

  protected String getBeanTableAlias() {
    return this.getTableAliasByAttrpath(PUBeanPath.RootPath);
  }

  protected String getFixTableAlias(String attrpath) {
    if (StringUtil.isSEmptyOrNull(attrpath)) {
      return null;
    }
    if (this.fixTableAliasMap == null) {
      return null;
    }
    return this.fixTableAliasMap.get(attrpath);
  }

  final protected void init(IBean beanentry, String from, String where,
      Map<String, String> aliasMap, boolean style) {
    this.setBean(beanentry);
    this.setJoinStyle(style);
    this.addJoin(from);
    this.addWhere(where);
    if (aliasMap != null) {
      this.tableAliasMap = aliasMap;
    }
    //
    if (this.bean != null) {
      if (this.tableAliasMap.isEmpty()) {
        this.tableAliasMap.put(PUBeanPath.RootPath, this.getBeanTable());
      }
//      if (this.sqlWhere.length() <= 0) {
//        this.addWhere("1=1 ");
//      }
    }
  }

  final protected void setBean(IBean bean) {
    this.bean = bean;
  }

  final protected void addJoin(String joinstr, String PUBeanPath,
      String tablealias) {
    this.addJoin(joinstr);
    this.tableAliasMap.put(PUBeanPath, tablealias);
  }

  final protected void setFixTableAliasMap(Map<String, String> fixtablealiasMap) {
    if (ValueCheckUtil.isNullORZeroLength(fixtablealiasMap)) {
      return;
    }
    String tempwhere = this.sqlWhere.toString();
    String tempjoin = this.sqlJoin.toString();
    for (Map.Entry<String, String> en : fixtablealiasMap.entrySet()) {
      String alias = this.getTableAliasByAttrpath(en.getKey());
      if (StringUtil.isSEmptyOrNull(alias)) {
        continue;
      }
      if (!alias.equals(en.getValue())) {
        if (!StringUtil.isSEmptyOrNull(tempjoin)) {
          tempjoin = PUSqlUtil.replaceTableAliasForFrom(tempjoin, alias, en.getValue());
        }
        if (!StringUtil.isSEmptyOrNull(tempwhere)) {
          tempwhere = PUSqlUtil.replaceTableAliasForWhere(tempwhere, alias, en.getValue());
        }
      }
      this.tableAliasMap.put(en.getKey(), en.getValue());
    }
    if (!StringUtil.isSEmptyOrNull(tempjoin)) {
      this.sqlJoin = new StringBuilder(tempjoin);
    }
    if (!StringUtil.isSEmptyOrNull(tempwhere)) {
      this.sqlWhere = new StringBuilder(tempwhere);
    }
    this.fixTableAliasMap = fixtablealiasMap;
    if (StringUtil.isSEmptyOrNull(this.sqlJoin.toString())) {
      String alias = this.fixTableAliasMap.get(PUBeanPath.RootPath);
      if (StringUtil.isSEmptyOrNull(alias)) {
        return;
      }
      this.tableAliasMap.put(PUBeanPath.RootPath, alias);
    }
  }

  final protected void setJoinStyle(boolean isJoinStyle) {
    this.isJoinStyle = isJoinStyle;
  }

  // void setAliasMap(String attrpath, String alias) {
  // if (StringUtil.isSEmptyOrNull(alias)) {
  // return;
  // }
  // this.tableAliasMap.put(attrpath, alias);
  // }
}

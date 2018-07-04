package nc.vo.pu.margin;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.tool.BillHelper;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 用于创建尾差处理器的工厂默认实现
 * 
 * @since 6.0
 * @version 2012-4-22 上午8:11:51
 * @author zhaoyha
 */

public abstract class AbstractPuMarginProcessFactory implements
    IPuMarginProcessFactory {

  private AggregatedValueObject[] curBillVos;

  private SuperVO[] curItemVos;

  private SuperVO[] curSiblingVos;

  private String srcBilltype;

  private AggregatedValueObject[] srcBillVos;

  private CircularlyAccessibleValueObject[] srcItemVos;

  /**
   * 构造一个缺省的尾差处理器的工厂
   * 
   * @param curBilltype 当前要处理尾差的单据类型
   * @param srcBilltype 来源单据类型
   * @param curBillVos 当前要处理尾差的单据VO数组
   * @param srcBillVos 来源单据VO数组（可为空)
   */
  public AbstractPuMarginProcessFactory(String srcBilltype,
      AggregatedValueObject[] curBillVos, AggregatedValueObject[] srcBillVos) {
    super();
    this.srcBilltype = srcBilltype;
    this.curBillVos = curBillVos;
    this.srcBillVos = srcBillVos;
  }

  @Override
  public List<IPuMarginProcess> createMarginProcess() {
    // V61需求只要求数量作第一次转单时进行尾差处理，如果后续版本要求多次尾差处理，则在此处改一下即可
    return this.createFirstTimeMarginProcess();
  }

  public AggregatedValueObject[] getCurBillVos() {
    return this.curBillVos;
  }

  /**
   * 当前单据要倒挤的行VO数组
   * 
   * @return
   */
  public SuperVO[] getCurItemVos() {
    return this.curItemVos;
  }

  /**
   * 当前单据要倒挤的行VO对应的兄弟VO数组
   * 
   * @return
   */
  public SuperVO[] getCurSiblingVos() {
    return this.curSiblingVos;
  }

  public String getSrcBilltype() {
    return this.srcBilltype;
  }

  public AggregatedValueObject[] getSrcBillVos() {
    return this.srcBillVos;
  }

  /**
   * 当前倒挤的单据行VO对应的来源单据行VO
   * 
   * @return
   */
  public CircularlyAccessibleValueObject[] getSrcItemVos() {
    return this.srcItemVos;
  }

  private void getCurNameFromCurSrcMap(Set<String> selectNameSet,
      Map<String, String> curSrcMap) {
    for (Map.Entry<String, String> entry : curSrcMap.entrySet()) {
      selectNameSet.add(entry.getKey());
    }
  }

  private Set<String> getQueryCurSiblingItemVOSelectField(
      List<IPuMarginCondition> margincondList) {
    Set<String> selectNameSet = new HashSet<String>();
    for (IPuMarginCondition cond : margincondList) {
      this.getCurNameFromCurSrcMap(selectNameSet, cond.curSrcCondJudgeField());
      this.getCurNameFromCurSrcMap(selectNameSet, cond.curSrcMarginField());
    }
    return selectNameSet;
  }

  private Set<String> getQuerySrcItemVOSelectField(
      List<IPuMarginCondition> margincondList) {
    Set<String> selectNameSet = new HashSet<String>();
    for (IPuMarginCondition cond : margincondList) {
      this.getSrcNameFromCurSrcMap(selectNameSet, cond.curSrcCondJudgeField());
      this.getSrcNameFromCurSrcMap(selectNameSet, cond.curSrcMarginField());
    }
    return selectNameSet;
  }

  private void getSrcNameFromCurSrcMap(Set<String> selectNameSet,
      Map<String, String> curSrcMap) {
    for (Map.Entry<String, String> entry : curSrcMap.entrySet()) {
      selectNameSet.add(entry.getValue());
    }
  }

  /**
   * V61需求只要求数量作第一次转单时进行尾差处，一般情况第一次转单可以不用走数据库查询
   */
  protected abstract List<IPuMarginProcess> createFirstTimeMarginProcess();

  /**
   * 查询当前要倒挤单据的兄弟VO，并放到this.curSiblingVos上，使用{@link #getCurSiblingVos()}获得
   * 
   * @param curItemClazz 当前要倒挤的单据VO类
   * @param margincondList 查询条件列表（多个倒挤器的），为了一次全部将要查的字段查询出
   * @param curNumField 当前单据主数量字段名称
   * @param curSrcBIDFieldName 当前单据来源BID字段名称
   * @param curSrcHIDFieldName 当前单据来源HID字段名称
   */
  protected <E extends ISuperVO> void queryCurSiblingVO(Class<E> curItemClazz,
      List<IPuMarginCondition> margincondList, String curNumField,
      String curSrcBIDFieldName, String curSrcHIDFieldName) {
    // 多次倒挤时使用
    Set<String> selectNames =
        this.getQueryCurSiblingItemVOSelectField(margincondList);
    IVOMeta metaData = Constructor.construct(curItemClazz).getMetaData();
    selectNames.add(metaData.getPrimaryAttribute().getName());// 加入主键
    selectNames.add(curNumField);// 加入主数量字段
    selectNames.add(curSrcBIDFieldName);// 加入来源单据BID字段
    selectNames.add(curSrcHIDFieldName);// 加入来源单据HID字段
    HashSet<String> srcBIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcBIDFieldName);
    HashSet<String> srcHIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcHIDFieldName);// 提高查询效率
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    IDExQueryBuilder idqb =
        new IDExQueryBuilder(PUTempTable.tmp_po_pub_03.name());
    condition.append(idqb.buildSQL(curSrcBIDFieldName,
        srcBIDSet.toArray(new String[srcBIDSet.size()])));
    condition.append(" and ");
    idqb = new IDExQueryBuilder(PUTempTable.tmp_po_pub_04.name());
    condition.append(idqb.buildSQL(curSrcHIDFieldName,
        srcHIDSet.toArray(new String[srcHIDSet.size()])));
    VOQuery<E> voq =
        new VOQuery<E>(curItemClazz, selectNames.toArray(new String[selectNames
            .size()]));
    this.setCurSiblingVos((SuperVO[]) voq.query(condition.toString(), null));
  }

  /**
   * 查询当来源行VO，并放到#this.srcItemVos上，使用{@link #getSrcItemVos()}获得
   * 
   * @param srcItemclazz 来源VO类
   * @param margincondList 查询条件列表（多个倒挤器的），为了一次全部将要查的上游字段查询出
   * @param srcNumField 来源单据主数量字段名称
   * @param curSrcIDField 当前单据来源ID（一般是来源BID）字段名称
   */
  protected <E extends ISuperVO> void querySrcItemVO(Class<E> srcItemclazz,
      List<IPuMarginCondition> margincondList, String srcNumField,
      String curSrcIDField) {
    // 多次倒挤时使用
    Set<String> selectNames = this.getQuerySrcItemVOSelectField(margincondList);
    IVOMeta metaData = Constructor.construct(srcItemclazz).getMetaData();
    selectNames.add(metaData.getPrimaryAttribute().getName());// 加入主键
    selectNames.add(srcNumField);// 加入主数量字段
    HashSet<String> srcIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcIDField);
    VOQuery<E> voq =
        new VOQuery<E>(srcItemclazz, selectNames.toArray(new String[selectNames
            .size()]));
    this.setSrcItemVos((CircularlyAccessibleValueObject[]) voq.query(srcIDSet
        .toArray(new String[srcIDSet.size()])));
  }

  /**
   * 当前单据要倒挤的行VO数组
   * 
   * @param curItemVos
   */
  protected void setCurItemVos(SuperVO[] curItemVos) {
    this.curItemVos = curItemVos;
  }

  protected void setCurSiblingVos(SuperVO[] curSiblingVos) {
    this.curSiblingVos = curSiblingVos;
  }

  /**
   * 存在来源聚合VO情况下，使用来源聚合VO表体设置倒挤来源单据明细VO，可避免重新查询
   * 
   */
  protected void setSrcItemVoBySrcBillBody() {
    if (!ArrayUtils.isEmpty(this.getSrcBillVos())) {
      this.setSrcItemVos(AggVOUtil.getCombinItemVOs(this.getSrcBillVos()));
    }
  }

  /**
   * 存在来源聚合VO情况下，使用来源聚合VO表头设置倒挤来源单据明细VO，可避免重新查询
   */
  protected void setSrcItemVoBySrcBillHead() {
    if (!ArrayUtils.isEmpty(this.getSrcBillVos())) {
      List<ISuperVO> poVoList =
          new BillHelper((IBill[]) this.getSrcBillVos()).getParentList();
      this.setSrcItemVos((CircularlyAccessibleValueObject[]) new ListToArrayTool<ISuperVO>()
          .convertToArray(poVoList));
    }
  }

  /**
   * 当前倒挤的单据行VO对应的来源单据行VO
   * 
   * @param srcItemVos
   */
  protected void setSrcItemVos(CircularlyAccessibleValueObject[] srcItemVos) {
    this.srcItemVos = srcItemVos;
  }

}

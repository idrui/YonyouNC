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
 * ���ڴ���β������Ĺ���Ĭ��ʵ��
 * 
 * @since 6.0
 * @version 2012-4-22 ����8:11:51
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
   * ����һ��ȱʡ��β������Ĺ���
   * 
   * @param curBilltype ��ǰҪ����β��ĵ�������
   * @param srcBilltype ��Դ��������
   * @param curBillVos ��ǰҪ����β��ĵ���VO����
   * @param srcBillVos ��Դ����VO���飨��Ϊ��)
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
    // V61����ֻҪ����������һ��ת��ʱ����β�����������汾Ҫ����β������ڴ˴���һ�¼���
    return this.createFirstTimeMarginProcess();
  }

  public AggregatedValueObject[] getCurBillVos() {
    return this.curBillVos;
  }

  /**
   * ��ǰ����Ҫ��������VO����
   * 
   * @return
   */
  public SuperVO[] getCurItemVos() {
    return this.curItemVos;
  }

  /**
   * ��ǰ����Ҫ��������VO��Ӧ���ֵ�VO����
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
   * ��ǰ�����ĵ�����VO��Ӧ����Դ������VO
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
   * V61����ֻҪ����������һ��ת��ʱ����β���һ�������һ��ת�����Բ��������ݿ��ѯ
   */
  protected abstract List<IPuMarginProcess> createFirstTimeMarginProcess();

  /**
   * ��ѯ��ǰҪ�������ݵ��ֵ�VO�����ŵ�this.curSiblingVos�ϣ�ʹ��{@link #getCurSiblingVos()}���
   * 
   * @param curItemClazz ��ǰҪ�����ĵ���VO��
   * @param margincondList ��ѯ�����б�����������ģ���Ϊ��һ��ȫ����Ҫ����ֶβ�ѯ��
   * @param curNumField ��ǰ�����������ֶ�����
   * @param curSrcBIDFieldName ��ǰ������ԴBID�ֶ�����
   * @param curSrcHIDFieldName ��ǰ������ԴHID�ֶ�����
   */
  protected <E extends ISuperVO> void queryCurSiblingVO(Class<E> curItemClazz,
      List<IPuMarginCondition> margincondList, String curNumField,
      String curSrcBIDFieldName, String curSrcHIDFieldName) {
    // ��ε���ʱʹ��
    Set<String> selectNames =
        this.getQueryCurSiblingItemVOSelectField(margincondList);
    IVOMeta metaData = Constructor.construct(curItemClazz).getMetaData();
    selectNames.add(metaData.getPrimaryAttribute().getName());// ��������
    selectNames.add(curNumField);// �����������ֶ�
    selectNames.add(curSrcBIDFieldName);// ������Դ����BID�ֶ�
    selectNames.add(curSrcHIDFieldName);// ������Դ����HID�ֶ�
    HashSet<String> srcBIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcBIDFieldName);
    HashSet<String> srcHIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcHIDFieldName);// ��߲�ѯЧ��
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
   * ��ѯ����Դ��VO�����ŵ�#this.srcItemVos�ϣ�ʹ��{@link #getSrcItemVos()}���
   * 
   * @param srcItemclazz ��ԴVO��
   * @param margincondList ��ѯ�����б�����������ģ���Ϊ��һ��ȫ����Ҫ��������ֶβ�ѯ��
   * @param srcNumField ��Դ�����������ֶ�����
   * @param curSrcIDField ��ǰ������ԴID��һ������ԴBID���ֶ�����
   */
  protected <E extends ISuperVO> void querySrcItemVO(Class<E> srcItemclazz,
      List<IPuMarginCondition> margincondList, String srcNumField,
      String curSrcIDField) {
    // ��ε���ʱʹ��
    Set<String> selectNames = this.getQuerySrcItemVOSelectField(margincondList);
    IVOMeta metaData = Constructor.construct(srcItemclazz).getMetaData();
    selectNames.add(metaData.getPrimaryAttribute().getName());// ��������
    selectNames.add(srcNumField);// �����������ֶ�
    HashSet<String> srcIDSet =
        CirVOUtil.getDistinctFieldSet(this.getCurItemVos(), curSrcIDField);
    VOQuery<E> voq =
        new VOQuery<E>(srcItemclazz, selectNames.toArray(new String[selectNames
            .size()]));
    this.setSrcItemVos((CircularlyAccessibleValueObject[]) voq.query(srcIDSet
        .toArray(new String[srcIDSet.size()])));
  }

  /**
   * ��ǰ����Ҫ��������VO����
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
   * ������Դ�ۺ�VO����£�ʹ����Դ�ۺ�VO�������õ�����Դ������ϸVO���ɱ������²�ѯ
   * 
   */
  protected void setSrcItemVoBySrcBillBody() {
    if (!ArrayUtils.isEmpty(this.getSrcBillVos())) {
      this.setSrcItemVos(AggVOUtil.getCombinItemVOs(this.getSrcBillVos()));
    }
  }

  /**
   * ������Դ�ۺ�VO����£�ʹ����Դ�ۺ�VO��ͷ���õ�����Դ������ϸVO���ɱ������²�ѯ
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
   * ��ǰ�����ĵ�����VO��Ӧ����Դ������VO
   * 
   * @param srcItemVos
   */
  protected void setSrcItemVos(CircularlyAccessibleValueObject[] srcItemVos) {
    this.srcItemVos = srcItemVos;
  }

}

package nc.vo.pu.m25.pub;

import java.util.HashSet;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.calculator.InvoiceCalculator;
import nc.vo.pu.m25.calculator.data.InvoiceRelationItemForCal;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽���ϵ����(����VO���㣬�����ǿ�Ƭ)
 * </ul>
 * <p>
 * TODO tianft ��Կ�Ƭ��vo����һ����������ά�����Ǻܷ��㣬�����Ժ�������ع���һ��
 */
public class VORelationCalculate {
  /**
   * @version 6.0
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 ����05:19:30
   */
  private class InvoiceVODataSet extends VODataSetForCal {

    private InvoiceHeaderVO headerVO = null;

    private InvoiceItemVO itemVO = null;

    private IRelationForItems relaItem = null;

    public InvoiceVODataSet(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
        IRelationForItems item) {
      super(itemVO, item);
      this.headerVO = headerVO;
      this.itemVO = itemVO;
      this.relaItem = item;
    }

    @Override
    public Object getAttributeValue(String key) {
      return super.getAttributeValue(key);
    }

    @Override
    public UFDate getBillDate() {
      return this.headerVO.getDbilldate();
    }

    @Override
    public String getCastunitid() {
      String value = this.itemVO.getCastunitid();
      return value;
    }

    @Override
    public String getCcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CCURRENCYID);
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    }

    @Override
    public String getCqtunitid() {
      return this.itemVO.getCastunitid();
    }

    @Override
    public UFDouble getNexchangerate() {
      return this.headerVO.getNexchangerate();
    }

    @Override
    public UFDouble getNglobalexchgrate() {
      return this.headerVO.getNglobalexchgrate();
    }

    @Override
    public UFDouble getNgroupexchgrate() {
      return this.headerVO.getNgroupexchgrate();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (VORelationCalculate.this.getKeys() != null
          && VORelationCalculate.this.getKeys().contains(key)) {
        return true;
      }
      if (this.relaItem.getNitemdiscountrateKey().equals(key)) {
        return true;
      }
      else if (this.relaItem.getCastunitidKey().equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }

    /**
     * ��������������ȡ��ͷ�ֶ�ֵ
     * <p>
     * <b>����˵��</b>
     * 
     * @param itemKey
     * @return <p>
     * @since 6.0
     * @author tianft
     * @time 2010-3-30 ����07:40:59
     */
    private String getHeadItemStringValue(String itemKey) {
      Object value = this.headerVO.getAttributeValue(itemKey);
      return ValueUtils.getString(value);
    }
  }

  /** ǿ��ʹ�ù̶�������,һ��Ϊ��ǿ�ƴ�����λ������ **/
  private UFBoolean bForceFixChgRate = UFBoolean.FALSE;

  /** �Ƿ�ǿ����˰���� **/
  private boolean forceNoTaxPrior = false;

  /** ӳ���itemkey������hasItemKey() */
  private HashSet<String> keys = null;

  public void calculate(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
      String itemKey) {
    IRelationForItems item = new InvoiceRelationItemForCal();
    ScaleUtils scale = new ScaleUtils(headerVO.getPk_group());
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new InvoiceVODataSet(headerVO, itemVO, item);
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();// ��������ʵ��
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
    String pk_group = headerVO.getPk_group();
    // ��ȡ���Ų���
    int globalPara = PubSysParamUtil.getNC002IntValue();
    int groupPara = PubSysParamUtil.getNC001IntValue(pk_group);
    // �Ƿ�����ȫ�ֱ�λ��
    cond.setGlobalLocalCurrencyEnable(globalPara != PubSysParamUtil.GLOBAL_DISABLE);
    // ȫ�ֱ�λ�Ҽ��㷽ʽ
    cond.setOrigCurToGlobalMoney(globalPara == PubSysParamUtil.GLOBAL_ORIG_BASE);
    // �Ƿ����ü��ű�λ��
    cond.setGroupLocalCurrencyEnable(groupPara != PubSysParamUtil.GROUP_DISABLE);
    // ���ű�λ�Ҽ��㷽ʽ
    cond.setOrigCurToGroupMoney(groupPara == PubSysParamUtil.GROUP_ORIG_BASE);
    // ֻ��������û�е�λ�ĵ���ʱ������Ĳ���
    if (InvoiceItemVO.NASTNUM.equals(itemKey)
        || InvoiceItemVO.NNUM.equals(itemKey)) {
      if (itemVO.getNastorigprice() == null
          || itemVO.getNastorigtaxprice() == null) {
        cond.setCalLocalPior(true);
        if (InvoiceItemVO.NNUM.equals(itemKey)) {
          cond.setUnitPriorType(Condition.MAIN_PRIOR);
        }
      }
    }

    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    if (this.bForceFixChgRate.booleanValue()) {
      cond.setIsFixNqtunitrate(true);
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(true);
    }
    else {
      cond.setIsFixNqtunitrate(this.IsFixNchangerate(itemVO));
      cond.setIsFixNchangerate(cond.getIsFixNqtunitrate());
    }
    // �Ƿ�ǿ����˰
    if (this.isForceNoTaxPrior()) {
      // ������˰����
      cond.setIsTaxOrNet(false);
    }
    else {
      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(this.IsTaxPrior(headerVO.getPk_purchaseorg()));
    }
    // �����Ƿ����ɹ���־
    InvoiceVO ivo = new InvoiceVO();
    ivo.setParentVO(headerVO);
    cond.setInternational(InvoiceCalculator
        .isInternationalPur(new BillHelper<InvoiceVO>(ivo)));

    tool.calculate(cond, itemKey);
  }

  /**
   * ֻ������������
   * 
   * @param headerVO
   * @param itemVO
   * @param itemKey
   */
  public void calculateOnlyNum(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
      String itemKey) {
    IRelationForItems item = new InvoiceRelationItemForCal();
    ScaleUtils scale = new ScaleUtils(headerVO.getPk_group());
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new InvoiceVODataSet(headerVO, itemVO, item);
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();// ��������ʵ��
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    cond.setIsFixNqtunitrate(true);
    InvoiceVO ivo = new InvoiceVO();
    ivo.setParentVO(headerVO);
    cond.setInternational(InvoiceCalculator
        .isInternationalPur(new BillHelper<InvoiceVO>(ivo)));
    tool.calculateOnlyNumAssNumQtNum(cond, itemKey);
  }

  /**
   * @return keys
   */
  public HashSet<String> getKeys() {
    if (this.keys == null) {
      this.keys = new HashSet<String>();
      // ��Ʊ����
      this.keys.add(InvoiceHeaderVO.DBILLDATE);
      // ����
      this.keys.add(InvoiceHeaderVO.NEXCHANGERATE);

      this.keys.add(InvoiceHeaderVO.NGLOBALEXCHGRATE);
      this.keys.add(InvoiceHeaderVO.NGROUPEXCHGRATE);

    }
    return this.keys;
  }

  public boolean isForceNoTaxPrior() {
    return this.forceNoTaxPrior;
  }

  /**
   * @param forceFixChgRate Ҫ���õ� �Ƿ�ǿ��ʹ�ù̶�������
   */
  public void setBForceFixChgRate(UFBoolean forceFixChgRate) {
    this.bForceFixChgRate = forceFixChgRate;
  }

  public void setForceNoTaxPrior(boolean noTaxPrior) {
    this.forceNoTaxPrior = noTaxPrior;
  }

  /**
   * @param keys Ҫ���õ� keys
   */
  public void setKeys(HashSet<String> keys) {
    this.keys = keys;
  }

  /**
   * ����������������ѯ�Ƿ�̶�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 ����04:47:38
   */
  private boolean IsFixNchangerate(InvoiceItemVO itemVO) {
    if (this.bForceFixChgRate.booleanValue()) {
      return true;
    }
    String pk_material = itemVO.getPk_material();
    String castunitid = itemVO.getCastunitid();

    if (StringUtil.isEmptyWithTrim(pk_material)
        || StringUtil.isEmptyWithTrim(castunitid)) {
      return true;
    }

    if (castunitid.equals(itemVO.getCunitid())) {
      return true;
    }

    boolean isFixedChangeRate = false;

    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            castunitid);
    return isFixedChangeRate;
  }

  /**
   * ���������������Ƿ�˰����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 ����05:20:13
   */
  private boolean IsTaxPrior(String pk_purchaseorg) {
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }
}

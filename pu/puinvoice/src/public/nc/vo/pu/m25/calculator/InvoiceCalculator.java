package nc.vo.pu.m25.calculator;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-7-12 ����07:41:51
 * @author �����
 */

public class InvoiceCalculator {

  private IDataSetForCal data;

  /** ǿ��ʹ�ù̶�������,һ��Ϊ��ǿ�ƴ�����λ������ **/
  private UFBoolean forceFixChgRate = UFBoolean.FALSE;

  /** �Ƿ�ǿ����˰���� **/
  private boolean forceNoTaxPrior = false;

  /** key-valueֵ�� */
  private IKeyValue keyValue;

  public InvoiceCalculator(IDataSetForCal data, IKeyValue keyValue) {
    this.data = data;
    this.keyValue = keyValue;
  }

  /**
   * �ж�һ�ŷ�Ʊ�Ƿ����ɹ�
   * 
   * @param bill ��Ʊ��ֻ��Ҫ��ͷ���ݣ�
   * @return true ����ɹ���false ���ڲɹ�
   */
  public static boolean isInternationalPur(IKeyValue bill) {
    return BuySellFlagEnum.IMPORT.value().equals(
        bill.getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG));
  }

  public void calculate(String itemKey, int row) {
    String pk_group =
        this.keyValue.getHeadValue(InvoiceHeaderVO.PK_GROUP).toString();
    ScaleUtils scale = new ScaleUtils(pk_group);
    // currentRow = row;
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    Calculator tool = new Calculator(this.data, scale);//
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();// ��������ʵ��
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
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
      if (this.keyValue.getBodyValue(row, InvoiceItemVO.NASTORIGPRICE) == null
          || this.keyValue.getBodyValue(row, InvoiceItemVO.NASTORIGTAXPRICE) == null) {
        cond.setCalLocalPior(true);
        if (InvoiceItemVO.NNUM.equals(itemKey)) {
          cond.setUnitPriorType(Condition.MAIN_PRIOR);
        }
      }
    }
    // ���õ����۷�ʽ������
    cond.setIsChgPriceOrDiscount(true);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    cond.setIsFixNqtunitrate(this.IsFixNchangerate(row));
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(this.IsTaxPrior(this.keyValue.getHeadValue(
        InvoiceHeaderVO.PK_PURCHASEORG).toString()));
    // �����Ƿ����ɹ���־
    cond.setInternational(InvoiceCalculator.isInternationalPur(this.keyValue));
    tool.calculate(cond, itemKey);
  }

  public UFBoolean getForceFixChgRate() {
    return this.forceFixChgRate;
  }

  public boolean isForceNoTaxPrior() {
    return this.forceNoTaxPrior;
  }

  public void setForceFixChgRate(UFBoolean forceFixChgRate) {
    this.forceFixChgRate = forceFixChgRate;
  }

  public void setForceNoTaxPrior(boolean forceNoTaxPrior) {
    this.forceNoTaxPrior = forceNoTaxPrior;
  }

  /**
   * �Ƿ�̶�������
   * 
   * @param keyValue
   * @param row
   * @return
   */
  private boolean IsFixNchangerate(int row) {
    if (this.forceFixChgRate.booleanValue()) {
      return true;
    }
    String pk_material =
        this.keyValue.getBodyValue(row, InvoiceItemVO.PK_MATERIAL).toString();
    String castunitid =
        this.keyValue.getBodyValue(row, InvoiceItemVO.CASTUNITID).toString();
    String unitid =
        this.keyValue.getBodyValue(row, InvoiceItemVO.CUNITID).toString();

    if (StringUtils.isBlank(pk_material) || StringUtils.isBlank(castunitid)) {
      return true;
    }

    if (castunitid.equals(unitid)) {
      return true;
    }

    boolean isFixedChangeRate = false;

    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            castunitid);
    return isFixedChangeRate;
  }

  private boolean IsTaxPrior(String pk_purchaseorg) {
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }

}

package nc.vo.pu.m27.pub;

import java.io.Serializable;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ļ�����Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����02:28:52
 */
public class SettleEnvironment implements Serializable {
  /**
   * �����ҵ�����࣬���ɹ����㣬���Ļ��ܽ��㣩
   * 
   * @since 6.0
   * @version 2011-4-7 ����10:51:04
   * @author zhaoyha
   */
  public static enum MatchBusiSystem {
    /** �ɹ����� **/
    po,
    /** ���Ļ��ܽ��� **/
    voi_consume
  }

  /**
   * �����ִ��������ģ�⻹������ִ��
   * 
   * @since 6.0
   * @version 2011-3-19 ����09:43:11
   * @author zhaoyha
   */
  public static enum MatchExecType {
    /** ģ�� **/
    mock,
    /** ����ִ�� **/
    real
  }

  private static final long serialVersionUID = 1L;

  /**
   * �����ִ��������ģ�⻹������ִ��<br>
   * ģ����ֻ����δ�־û��Ļ�����㵥�����ڷ�̯���ã�<br>
   * ����ִ�У�����ɱ��ν���
   */
  private MatchExecType execType = MatchExecType.real;

  /** ����PO04�������뷢Ʊ�ĵ����ݲ���� */
  private boolean invoicePriceOverOder = false;

  /** ����PO75������ʱ��Ʊ��������Ƿ����ɱ� */
  private boolean isReasonWasteIntoCost = false;

  /** �Զ����㷢Ʊ��ⵥ����VO */
  private InvoiceStockOptionableVO m_autoMatchInvoiceStockOptionableVO = null;

  /** �Զ����������Ʊ�Գ����VO */
  private RBInvoiceOptionableVO m_autoMatchRBInvoiceOptionableVO = null;

  /** �Զ����������ⵥ�Գ����VO */
  private RBStockOptionableVO m_autoMatchRBStockOptionableVO = null;

  /** "����###����ⵥ���ν����������ڷ�Ʊ�ı��ν������������������ϵ��ֹ�������" */
  private boolean m_bAllowStockBeyondInvoice = false;

  /**
   * �Զ�����ʱ����Ҫƥ����ɣ��ֹ�����ʱ����ȫ��ƥ�����
   * ��浥���Ƿ���Ҫȫ��ƥ����ɣ��˲���ʵ�ʺ�m_bAllowStockBeyondInvoice����
   */
  private boolean m_bStockHaveToMatchAll = false;

  /** �ɱ�Ҫ��VO[] */
  private CostfactorVO[] m_costFactorVOs = null;

  /** ���ϵĵ�λ��� */
  private HashMap<String, UFDouble> m_hmapMaterialUnitVolume = null;

  /** ���ϵĵ�λ���� */
  private HashMap<String, UFDouble> m_hmapMaterialUnitWeight = null;

  /** ��½���� */
  private UFDate m_loginDate = null;

  /** ��½�� */
  private String m_loginOperator = null;

  /** ������֯ */
  private String m_pkFinanceOrg = null;

  /** ���㷽ʽ */
  private EnumSettleType m_settleType;

  /** ��λ���С��λ */
  private int m_unitVolumneDigit = 2;

  /** ��λ����С��λ */
  private int m_unitWeightDigit = 2;

  /*
   * SettleEnvironment �Ĺ�����
   * @param settleType ��ϵͳ�Ľ��㷽ʽ��
   */
  public SettleEnvironment(String pkOrg, EnumSettleType settleType) {
    super();
    this.m_pkFinanceOrg = pkOrg;
    this.setSettleType(settleType);

    // ��ȡ�����е�Ĭ�Ͻ������
    if (pkOrg != null) {
      SuperVO[] vos = PUSysParamUtil.getPO86_v(pkOrg);
      this.setAutoMatchRBStockOptionableVO((RBStockOptionableVO) vos[0]);
      this.setAutoMatchRBInvoiceOptionableVO((RBInvoiceOptionableVO) vos[1]);
      this.setAutoMatchInvoiceStockOptionableVO((InvoiceStockOptionableVO) vos[2]);
    }
  }

  /**
   * ���ݿ�����VO�жϣ���ǰ���н�������ࣨ�ǲɹ����㻹�����Ļ��ܽ��㣩
   * 
   * @param ssVos
   * @return
   */
  public static MatchBusiSystem getMatchBusiSystem(StockSettleVO[] ssVos) {
    if (ArrayUtils.isEmpty(ssVos)) {
      return MatchBusiSystem.po;
    }
    for (StockSettleVO ssVo : ssVos) {
      if (ICBillType.VmiSum.getCode().equals(ssVo.getCbilltypecode())) {
        return MatchBusiSystem.voi_consume;
      }
    }
    return MatchBusiSystem.po;
  }

  public InvoiceStockOptionableVO getAutoMatchInvoiceStockOptionableVO() {
    return this.m_autoMatchInvoiceStockOptionableVO;
  }

  public RBInvoiceOptionableVO getAutoMatchRBInvoiceOptionableVO() {
    return this.m_autoMatchRBInvoiceOptionableVO;
  }

  public RBStockOptionableVO getAutoMatchRBStockOptionableVO() {
    return this.m_autoMatchRBStockOptionableVO;
  }

  /** ��ǰ������֯�µ����гɱ�Ҫ����ͼVO **/
  public CostfactorViewVO[] getCostFactorViews() {
    if (ArrayUtils.isEmpty(this.getCostFactorVOs())) {
      return null;
    }
    return new BillToViewConvertor<CostfactorVO, CostfactorViewVO>(
        CostfactorViewVO.class).convert(this.getCostFactorVOs());
  }

  /** ��ǰ������֯�µ����гɱ�Ҫ�� **/
  public CostfactorVO[] getCostFactorVOs() {
    if (null == this.m_costFactorVOs) {// ע������һ��Ҫ�ÿ����о���������Ч������
      try {
        this.m_costFactorVOs =
            NCLocator.getInstance().lookup(ICostFactorQueryService.class)
                .queryCostfacotorIn(this.m_pkFinanceOrg);
        if (null == this.m_costFactorVOs) {
          this.m_costFactorVOs = new CostfactorVO[0];// ������ʶ�Ѿ���ѯ������Ҫ�ٲ���
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.m_costFactorVOs;
  }

  public MatchExecType getExecType() {
    return this.execType;
  }

  public UFDate getLoginDate() {
    return this.m_loginDate;
  }

  public String getLoginOperator() {
    return this.m_loginOperator;
  }

  /**
   * �õ���������֯�ı�λ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   * @since 6.0
   * @author wangyf
   * @time 2010-6-3 ����10:39:12
   */
  public String getOrgCurr() {
    return OrgUnitPubService.queryOrgCurrByPk(this.m_pkFinanceOrg);
  }

  public EnumSettleType getSettleType() {
    return this.m_settleType;
  }

  /**
   * �������ϵ�SRCID���õ��䵥λ���
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param sPK_srcMaterial
   * @return <p>
   * @author wangyf
   * @time 2010-2-25 ����03:22:42
   */
  public UFDouble getUnitVolume(String sPK_srcMaterial) {
    if (this.m_hmapMaterialUnitVolume == null || sPK_srcMaterial == null) {
      return null;
    }

    return this.m_hmapMaterialUnitVolume.get(sPK_srcMaterial);
  }

  public int getUnitVolumneDigit() {
    return this.m_unitVolumneDigit;
  }

  /**
   * �������ϵ�SRCID���õ��䵥λ����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param sPK_srcMaterial
   * @return <p>
   * @author wangyf
   * @time 2010-2-25 ����03:22:42
   */
  public UFDouble getUnitWeight(String sPK_srcMaterial) {
    if (this.m_hmapMaterialUnitWeight == null || sPK_srcMaterial == null) {
      return null;
    }

    return this.m_hmapMaterialUnitWeight.get(sPK_srcMaterial);
  }

  public int getUnitWeightDigit() {
    return this.m_unitWeightDigit;
  }

  public boolean isAllowStockBeyondInvoice() {
    return this.m_bAllowStockBeyondInvoice;
  }

  public boolean isInvoicePriceOverOder() {
    return this.invoicePriceOverOder;
  }

  public boolean isReasonWasteIntoCost() {
    return this.isReasonWasteIntoCost;
  }

  public boolean isStockHaveToMatchAll() {
    return this.m_bStockHaveToMatchAll;
  }

  public void setAllowStockBeyondInvoice(boolean confirm) {
    this.m_bAllowStockBeyondInvoice = confirm;
  }

  public void setAutoMatchInvoiceStockOptionableVO(
      InvoiceStockOptionableVO autoMatchInvoiceStockOptionableVO) {
    this.m_autoMatchInvoiceStockOptionableVO =
        autoMatchInvoiceStockOptionableVO;
  }

  public void setAutoMatchRBInvoiceOptionableVO(
      RBInvoiceOptionableVO autoMatchOptionableVO) {
    this.m_autoMatchRBInvoiceOptionableVO = autoMatchOptionableVO;
  }

  public void setAutoMatchRBStockOptionableVO(
      RBStockOptionableVO autoMatchRBStockOptionableVO) {
    this.m_autoMatchRBStockOptionableVO = autoMatchRBStockOptionableVO;
  }

  public void setCostFactorVOs(CostfactorVO[] costFactorVOs) {
    this.m_costFactorVOs = costFactorVOs;
  }

  public void setExecType(MatchExecType execType) {
    this.execType = execType;
  }

  public void setHmapUnitVolume(HashMap<String, UFDouble> hmapMaterialUnitVolume) {
    this.m_hmapMaterialUnitVolume = hmapMaterialUnitVolume;
  }

  public void setHmapUnitWeight(HashMap<String, UFDouble> hmapMaterialUnitWeight) {
    this.m_hmapMaterialUnitWeight = hmapMaterialUnitWeight;
  }

  public void setInvoicePriceOverOder(boolean invoicePriceOverOder) {
    this.invoicePriceOverOder = invoicePriceOverOder;
  }

  public void setLoginDate(UFDate loginDate) {
    this.m_loginDate = loginDate;
  }

  public void setLoginOperator(String loginOperator) {
    this.m_loginOperator = loginOperator;
  }

  public void setReasonWasteIntoCost(boolean isReasonWasteIntoCost) {
    this.isReasonWasteIntoCost = isReasonWasteIntoCost;
  }

  public void setSettleType(EnumSettleType settleType) {
    this.m_settleType = settleType;
  }

  public void setStockHaveToMatchAll(boolean stockHaveToMatchAll) {
    this.m_bStockHaveToMatchAll = stockHaveToMatchAll;
  }

  public void setUnitVolumneDigit(int unitVolumneDigit) {
    this.m_unitVolumneDigit = unitVolumneDigit;
  }

  public void setUnitWeightDigit(int unitWeightDigit) {
    this.m_unitWeightDigit = unitWeightDigit;
  }

}

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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算的环境信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 下午02:28:52
 */
public class SettleEnvironment implements Serializable {
  /**
   * 结算的业务种类，（采购结算，消耗汇总结算）
   * 
   * @since 6.0
   * @version 2011-4-7 上午10:51:04
   * @author zhaoyha
   */
  public static enum MatchBusiSystem {
    /** 采购结算 **/
    po,
    /** 消耗汇总结算 **/
    voi_consume
  }

  /**
   * 结算的执行类型是模拟还是真正执行
   * 
   * @since 6.0
   * @version 2011-3-19 下午09:43:11
   * @author zhaoyha
   */
  public static enum MatchExecType {
    /** 模拟 **/
    mock,
    /** 真正执行 **/
    real
  }

  private static final long serialVersionUID = 1L;

  /**
   * 结算的执行类型是模拟还是真正执行<br>
   * 模拟则只返回未持久化的货物结算单（用于分摊费用）<br>
   * 正在执行，则完成本次结算
   */
  private MatchExecType execType = MatchExecType.real;

  /** 参数PO04，订单与发票的单价容差控制 */
  private boolean invoicePriceOverOder = false;

  /** 参数PO75：结算时发票合理损耗是否进入成本 */
  private boolean isReasonWasteIntoCost = false;

  /** 自动结算发票入库单规则VO */
  private InvoiceStockOptionableVO m_autoMatchInvoiceStockOptionableVO = null;

  /** 自动结算红兰发票对冲规则VO */
  private RBInvoiceOptionableVO m_autoMatchRBInvoiceOptionableVO = null;

  /** 自动结算红兰入库单对冲规则VO */
  private RBStockOptionableVO m_autoMatchRBStockOptionableVO = null;

  /** "物料###：入库单本次结算数量大于发票的本次结算数量，继续本物料的手工结算吗？" */
  private boolean m_bAllowStockBeyondInvoice = false;

  /**
   * 自动结算时不需要匹配完成，手工结算时必须全部匹配完成
   * 库存单据是否需要全部匹配完成，此参数实际和m_bAllowStockBeyondInvoice配套
   */
  private boolean m_bStockHaveToMatchAll = false;

  /** 成本要素VO[] */
  private CostfactorVO[] m_costFactorVOs = null;

  /** 物料的单位体积 */
  private HashMap<String, UFDouble> m_hmapMaterialUnitVolume = null;

  /** 物料的单位重量 */
  private HashMap<String, UFDouble> m_hmapMaterialUnitWeight = null;

  /** 登陆日期 */
  private UFDate m_loginDate = null;

  /** 登陆人 */
  private String m_loginOperator = null;

  /** 财务组织 */
  private String m_pkFinanceOrg = null;

  /** 结算方式 */
  private EnumSettleType m_settleType;

  /** 单位体积小数位 */
  private int m_unitVolumneDigit = 2;

  /** 单位重量小数位 */
  private int m_unitWeightDigit = 2;

  /*
   * SettleEnvironment 的构造子
   * @param settleType 在系统的结算方式中
   */
  public SettleEnvironment(String pkOrg, EnumSettleType settleType) {
    super();
    this.m_pkFinanceOrg = pkOrg;
    this.setSettleType(settleType);

    // 读取参数中的默认结算规则
    if (pkOrg != null) {
      SuperVO[] vos = PUSysParamUtil.getPO86_v(pkOrg);
      this.setAutoMatchRBStockOptionableVO((RBStockOptionableVO) vos[0]);
      this.setAutoMatchRBInvoiceOptionableVO((RBInvoiceOptionableVO) vos[1]);
      this.setAutoMatchInvoiceStockOptionableVO((InvoiceStockOptionableVO) vos[2]);
    }
  }

  /**
   * 根据库存结算VO判断，当前进行结算的种类（是采购结算还是消耗汇总结算）
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

  /** 当前财务组织下的所有成本要素视图VO **/
  public CostfactorViewVO[] getCostFactorViews() {
    if (ArrayUtils.isEmpty(this.getCostFactorVOs())) {
      return null;
    }
    return new BillToViewConvertor<CostfactorVO, CostfactorViewVO>(
        CostfactorViewVO.class).convert(this.getCostFactorVOs());
  }

  /** 当前财务组织下的所有成本要素 **/
  public CostfactorVO[] getCostFactorVOs() {
    if (null == this.m_costFactorVOs) {// 注意这里一定要用空来判决，否则有效率问题
      try {
        this.m_costFactorVOs =
            NCLocator.getInstance().lookup(ICostFactorQueryService.class)
                .queryCostfacotorIn(this.m_pkFinanceOrg);
        if (null == this.m_costFactorVOs) {
          this.m_costFactorVOs = new CostfactorVO[0];// 用来标识已经查询过，不要再查了
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
   * 得到本财务组织的本位币
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   * @since 6.0
   * @author wangyf
   * @time 2010-6-3 上午10:39:12
   */
  public String getOrgCurr() {
    return OrgUnitPubService.queryOrgCurrByPk(this.m_pkFinanceOrg);
  }

  public EnumSettleType getSettleType() {
    return this.m_settleType;
  }

  /**
   * 根据物料的SRCID，得到其单位体积
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sPK_srcMaterial
   * @return <p>
   * @author wangyf
   * @time 2010-2-25 下午03:22:42
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
   * 根据物料的SRCID，得到其单位重量
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sPK_srcMaterial
   * @return <p>
   * @author wangyf
   * @time 2010-2-25 下午03:22:42
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

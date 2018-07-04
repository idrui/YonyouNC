package nc.impl.pu.m21.action.rule.maintain;

import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-9 下午12:28:55
 * @author wuxla
 */

public class ApDataValueSetter {

  private Map<String, UFDouble> creditboundMap;

  /**
   * 订单对应的采购发票对应的应付单的已核销本币金额
   */
  private UFDouble nInvoiceVerifyMny;

  /**
   * 所有已审批订单表体的累计已核销本币付款金额
   */
  private UFDouble nOrderVerifyMny;

  /**
   * 已审批订单的累计入库数量×订单含税单价
   */
  private UFDouble nPUOrderOperMny;

  /**
   * 所有已审批订单的累计本币付款总额
   */
  private UFDouble nPUOrderPayMny;

  /**
   * 已审批订单的本币价税合计
   */
  private UFDouble nPUOrdertaxmny;

  /**
   * 已审批委外订单的累计入库数量×订单含税单价
   */
  private UFDouble nSCOperMny;

  /**
   * 已审批委外订单的本币价税合计
   */
  private UFDouble nSCTaxmny;

  /**
   * 该采购订单表头供应商所有应付单（不包括暂估应付）的未付款金额
   */
  private UFDouble nUnPayMny;

  private Map<String, UFDouble[]> orderApDataMap;

  private Map<String, UFDouble> orderPayMnyMap;

  private Map<String, UFDouble> payVerifyMap;

  private String pk_apfinanceorg;

  private ScaleUtils scale = new ScaleUtils(InvocationInfoProxy.getInstance()
      .getGroupId());

  private Map<String, UFDouble[]> scApDataMap;

  private Map<String, UFDouble> unPayMnyMap;

  private Map<String, UFDouble> verifyMnyMap;

  public Map<String, UFDouble> getCreditboundMap() {
    return this.creditboundMap;
  }

  public Map<String, UFDouble[]> getOrderApDataMap() {
    return this.orderApDataMap;
  }

  public Map<String, UFDouble> getOrderPayMnyMap() {
    return this.orderPayMnyMap;
  }

  public Map<String, UFDouble> getPayVerifyMap() {
    return this.payVerifyMap;
  }

  public Map<String, UFDouble[]> getScApDataMap() {
    return this.scApDataMap;
  }

  public Map<String, UFDouble> getUnPayMnyMap() {
    return this.unPayMnyMap;
  }

  public Map<String, UFDouble> getVerifyMnyMap() {
    return this.verifyMnyMap;
  }

  public void setApData(ApDataVO vo) {
    this.init(vo);

    String ccurrencyid = vo.getCcurrencyid();

    // 订单应付：即该采购订单表头供应商的订单未付款金额。具体算法为该供应商在当前订单的采购组织范围中的：
    // 所有（已审批订单的本币价税合计－该订单对应的采购发票对应的应付单的已核销本币金额）。
    // 自制采购入库单生成的发票或者自制采购发票形成的应付，和采购入库单确认应付形成的应付单，以及自制应付单本币价税合计不计算入订单应付。
    UFDouble nOrderApMny = MathTool.add(this.nPUOrdertaxmny, this.nSCTaxmny);
    nOrderApMny = MathTool.sub(nOrderApMny, this.nInvoiceVerifyMny);
    nOrderApMny = this.scale.adjustMnyScale(nOrderApMny, ccurrencyid);
    vo.setNorderap(nOrderApMny);
    // 业务应付：该采购订单表头供应商的所有订单已入库数量形成的未付款金额。具体算法为该供应商在当前订单的采购组织范围中的：
    // 所有（已审批订单的累计入库数量×订单含税单价－该订单对应的采购发票对应的应付单的已核销本币金额）。
    // 自制采购发票形成的应付以及自制应付单本币价税合计不计算入业务应付。
    UFDouble nOperMny = MathTool.add(this.nPUOrderOperMny, this.nSCOperMny);
    nOperMny = MathTool.sub(nOperMny, this.nInvoiceVerifyMny);
    nOperMny = this.scale.adjustMnyScale(nOperMny, ccurrencyid);
    vo.setNoperationap(nOperMny);
    // 财务应付：该采购订单表头供应商所有应付单（不包括暂估应付）的未付款金额。
    // 具体算法为该采购订单表体应付组织范围内的该供应商的应付单的本币贷方余额。
    // 自制采购入库单生成的发票或者自制采购发票以及自制应付单本币价税合计要计入财务应付。
    this.nUnPayMny = this.scale.adjustMnyScale(this.nUnPayMny, ccurrencyid);
    vo.setNfinanceap(this.nUnPayMny);
    // 订单付款：该采购订单表头供应商的所有订单已付款金额。
    // 具体算法为该供应商在当前订单的采购组织范围中的：所有已审批订单表头的累计本币付款总额。
    this.nPUOrderPayMny =
        this.scale.adjustMnyScale(this.nPUOrderPayMny, ccurrencyid);
    vo.setNorderpaymny(this.nPUOrderPayMny);

    // 付款未核销金额：该供应商的所有订单已付款未核销金额。
    // 具体算法为该供应商在当前订单的采购组织范围中的：订单表头累计本币付款总额－所有已审批订单表体的累计已核销本币付款金额。
    UFDouble nunverifymny =
        MathTool.sub(this.nPUOrderPayMny, this.nOrderVerifyMny);
    nunverifymny = this.scale.adjustMnyScale(nunverifymny, ccurrencyid);
    vo.setNunverifymny(nunverifymny);

    // 供应商信用：取该采购订单表头在供应商档案财务组织页签的信用额度。
    UFDouble ncreditbound = this.creditboundMap.get(this.pk_apfinanceorg);
    vo.setNcreditbound(ncreditbound);
  }

  public void setCreditboundMap(Map<String, UFDouble> creditboundMap) {
    this.creditboundMap = creditboundMap;
  }

  public void setOrderApDataMap(Map<String, UFDouble[]> orderApDataMap) {
    this.orderApDataMap = orderApDataMap;
  }

  public void setOrderPayMnyMap(Map<String, UFDouble> orderPayMnyMap) {
    this.orderPayMnyMap = orderPayMnyMap;
  }

  public void setPayVerifyMap(Map<String, UFDouble> payVerifyMap) {
    this.payVerifyMap = payVerifyMap;
  }

  public void setScApDataMap(Map<String, UFDouble[]> scApDataMap) {
    this.scApDataMap = scApDataMap;
  }

  public void setUnPayMnyMap(Map<String, UFDouble> unPayMnyMap) {
    this.unPayMnyMap = unPayMnyMap;
  }

  public void setVerifyMnyMap(Map<String, UFDouble> verifyMnyMap) {
    this.verifyMnyMap = verifyMnyMap;
  }

  private void init(ApDataVO vo) {
    this.pk_apfinanceorg = vo.getPk_apfinanceorg();
    this.initOrderApData();
    this.initSCApData();
    this.initOrderPayData();
    this.initInvoiceVerifyData();
    this.initUnPayMny();
    this.initOrderVerifyMny();
  }

  private void initInvoiceVerifyData() {
    if (null == this.verifyMnyMap) {
      return;
    }
    this.nInvoiceVerifyMny = this.verifyMnyMap.get(this.pk_apfinanceorg);
  }

  private void initOrderApData() {
    if (null == this.orderApDataMap) {
      return;
    }
    UFDouble[] orderApData = this.orderApDataMap.get(this.pk_apfinanceorg);
    if (ArrayUtils.isEmpty(orderApData)) {
      return;
    }
    this.nPUOrdertaxmny = orderApData[0];
    this.nPUOrderOperMny = orderApData[1];

    // this.nOrderVerifyMny = orderApData[2];
  }

  private void initOrderPayData() {
    if (null == this.orderPayMnyMap) {
      return;
    }
    this.nPUOrderPayMny = this.orderPayMnyMap.get(this.pk_apfinanceorg);
  }

  private void initOrderVerifyMny() {
    if (null == this.payVerifyMap) {
      return;
    }
    this.nOrderVerifyMny = this.payVerifyMap.get(this.pk_apfinanceorg);
  }

  private void initSCApData() {
    if (null == this.scApDataMap) {
      return;
    }

    UFDouble[] scApData = this.scApDataMap.get(this.pk_apfinanceorg);
    if (ArrayUtils.isEmpty(scApData)) {
      return;
    }

    this.nSCTaxmny = scApData[0];
    this.nSCOperMny = scApData[1];
  }

  private void initUnPayMny() {
    if (null == this.unPayMnyMap) {
      this.nUnPayMny = UFDouble.ZERO_DBL;
      return;
    }
    this.nUnPayMny = MathTool.nvl(this.unPayMnyMap.get(this.pk_apfinanceorg));
  }

}

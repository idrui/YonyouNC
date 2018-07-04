package nc.vo.pu.m25.vochange;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.pub.InvoicePlanPriceSetter;
import nc.vo.pu.m25.pub.InvoiceQueryPriceHandler;
import nc.vo.pu.m25.pub.InvoiceTypeSetter;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceOppTaxFlagFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceOrgVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.m25.vochange.processor.InvoiceMarginProcessor;
import nc.vo.pu.m25.vochange.processor.InvoiceOrderInfoProcessor;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 采购发票作为下游的vo交换处理类
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */

public abstract class InvoiceChangeVOAdjustor implements IChangeVOAdjust {

  protected InvoiceOrderInfoProcessor invoiceOrderInfoProcessor = null;

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    InvoiceVO[] resultVOs = (InvoiceVO[]) ArrayUtil.convertArrayType(destVOs);
    this.checkUserDef(resultVOs);
    // 如果是虚拟发票的VO交换，则不进行处理
    if (UFBoolean.TRUE.equals(srcVOs[0].getParentVO().getAttributeValue(
        InvoiceHeaderVO.BVIRTUAL))) {
      // 虚拟发票时的特殊处理
      return this.processVirtureInvoice(resultVOs);
    }
    return this.doDefaultAfterChange(resultVOs);
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 交换前处理
    return srcVOs;
  }

  private void checkUserDef(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    UserDefCheckUtils.check(vos, new Class[] {
      InvoiceHeaderVO.class, InvoiceItemVO.class
    });
  }

  /**
   * 转单后的默认处理，子类有需求可以覆写此方法
   * 
   * @param resultVOs
   * @return
   */
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] resultVOs) {
    // 设置业务日期
    this.setBusiDate(resultVOs);
    // 转单时的联动计算
    this.reCalculateFromSource(resultVOs);
    // 倒挤处理,根据采购订单倒挤，要保证是最后一次拉单并且币种和价格与订单相同
    new InvoiceMarginProcessor().processMargin(resultVOs);
    // 如果汇率有变化，重新取
    new InvoiceExchangeProcessor().resetExchangeByQuery(resultVOs);
    // 如果来源或者源头有采购订单，供应商和付款单位取订单上的信息
    this.getInvoiceOrderInfoProcessor().fillDataByOrder(resultVOs);
    // 根据订单未找到付款单位的话，默认取供应商的值
    this.setPayUnit(resultVOs);
    // 计划价
    this.setPlanPrice(resultVOs);
    // 询价处理
    this.queryPriceForVOs(resultVOs);
    // 设置VAT相关信息
    this.setDefaultVatInfo(resultVOs);
    // 设置银行账户和账号信息。
    this.setBankAccInfo(resultVOs);
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    this.setInvoiceType(resultVOs);
    return resultVOs;
  }

  /**
   * 提供订单信息处理的扩展，子类可重写，提供特殊的订单处理器
   * 
   * @return 订单信息处理器。
   */
  protected InvoiceOrderInfoProcessor getInvoiceOrderInfoProcessor() {
    if (this.invoiceOrderInfoProcessor == null) {
      this.invoiceOrderInfoProcessor = new InvoiceOrderInfoProcessor();
    }
    return this.invoiceOrderInfoProcessor;
  }

  /**
   * 虚拟发票时的特殊处理
   * 
   * @param resultVOs
   * @return
   */
  protected InvoiceVO[] processVirtureInvoice(InvoiceVO[] resultVOs) {
    // 设置业务日期
    this.setBusiDate(resultVOs);
    // 如果来源或者源头有采购订单，供应商和付款单位取订单上的信息
    this.getInvoiceOrderInfoProcessor().fillDataByOrder(resultVOs);
    // 根据订单未找到付款单位的话，默认取供应商的值
    this.setPayUnit(resultVOs);
    // 设置VAT相关信息
    this.setDefaultVatInfo(resultVOs);
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    this.setInvoiceType(resultVOs);
    return resultVOs;
  }

  /**
   * 询价处理
   * 
   * @param retvos
   */
  protected void queryPriceForVOs(InvoiceVO[] retvos) {
    // 询价处理
    new InvoiceQueryPriceHandler(retvos).handleQueryPrice();
  }

  protected void reCalculateFromSource(InvoiceVO[] resultVOs) {
    // 从主数量重新进行计算，多次转单后数量不同，只有多次转单时才会联动计算
    InvoiceVOUtil.reCalculateBasedNum(resultVOs);
  }

  /**
   * 设置银行账户和账号信息。
   * 
   * @param vos 发票VO数组
   */
  protected void setBankAccInfo(InvoiceVO[] vos) {
    Set<String> pk_suppliers = new HashSet<String>();
    for (InvoiceVO vo : vos) {
      if (StringUtils.isNotEmpty(vo.getParentVO().getPk_supplier())) {
        pk_suppliers.add(vo.getParentVO().getPk_supplier());
      }
    }
    if (pk_suppliers.isEmpty()) {
      return;
    }

    Map<String, UFBoolean> freeCustInfo =
        SupplierPubService.getFreeCust(pk_suppliers.toArray(new String[0]));

    Map<String, String[]> bankAccInfo =
        SupplierPubService.getDefaultBankAccbasVOBySupplier(pk_suppliers
            .toArray(new String[pk_suppliers.size()]));
    if (bankAccInfo.isEmpty()) {
      return;
    }
    String[] accInfo = null;
    UFBoolean isFreeCust = UFBoolean.FALSE;
    for (InvoiceVO vo : vos) {
      String pk_supplier = vo.getParentVO().getPk_supplier();
      if (freeCustInfo != null && freeCustInfo.size() != 0) {
        isFreeCust = freeCustInfo.get(pk_supplier);
      }
      // mengjian 如果是散户管理供应商直接带入
      if (UFBoolean.TRUE.equals(isFreeCust)) {
        continue;
      }
      accInfo = bankAccInfo.get(pk_supplier);
      if (accInfo != null && vo.getParentVO().getPk_bankaccbas() == null) {
        vo.getParentVO().setPk_bankaccbas(accInfo[0]);
        vo.getParentVO().setVbankaccount(accInfo[1]);
      }
    }
  }

  /**
   * 设置业务日期
   * 
   * @param retvos
   */
  protected void setBusiDate(InvoiceVO[] retvos) {
    UFDate busiDate = AppContext.getInstance().getBusiDate();
    for (InvoiceVO vo : retvos) {
      vo.getParentVO().setDbilldate(busiDate);
      vo.getParentVO().setDarrivedate(busiDate);
    }
  }

  /**
   * 设置VAT相关信息（逆向征税标志、供应商VAT码、组织VAT码）
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param retvos
   */
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(retvos);
    InvoiceOppTaxFlagFillRule opptaxrule = new InvoiceOppTaxFlagFillRule(bills);
    InvoiceSupplierVatCodeFillRule supvatrule =
        new InvoiceSupplierVatCodeFillRule(bills);
    InvoiceOrgVatCodeFillRule orgvatrule = new InvoiceOrgVatCodeFillRule(bills);
    opptaxrule.prepare();
    supvatrule.prepare();
    orgvatrule.prepare();
    opptaxrule.process();
    supvatrule.process();
    orgvatrule.process();
  }

  /**
   * 发票类型
   * 区分普通采购和进出口采购
   * 进出口单据交换后处理类重写该方法
   * add by liangchen1 NC631需求
   * 
   * @param retvos
   */
  protected void setInvoiceType(InvoiceVO[] retvos) {
    // 设置发票归属
    new InvoiceTypeSetter(InvoicePuImportEnum.PUINVOICE).setInvocieType(retvos);
  }

  /**
   * 付款单位默认取供应商的值，放在根据订单找付款单位的规则后用
   * 
   * @param retvos
   */
  protected void setPayUnit(InvoiceVO[] retvos) {
    for (InvoiceVO vo : retvos) {
      if (StringUtils.isEmpty(vo.getParentVO().getPk_paytosupplier())) {
        vo.getParentVO().setPk_paytosupplier(vo.getParentVO().getPk_supplier());
      }
    }
  }

  /**
   * 计划价
   * 
   * @param retvos
   */
  protected void setPlanPrice(InvoiceVO[] retvos) {
    // 设置计划价
    new InvoicePlanPriceSetter().setPlanPrice(retvos);
  }

}

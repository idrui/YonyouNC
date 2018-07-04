package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.itf.org.IOrgConst;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.m25.vochange.processor.VMIInvoiceCountrySetter;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * vo交换处理，包括交换前和交换后的处理
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */

public class ChangeVOAdjustor50To25 extends InvoiceChangeVOAdjustor {

  /**
   * 来源单据信息补充
   * 
   * @param vos
   */
  private void setDefaultSourceInfo(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // 单位与数量
        if (item.getCastunitid() == null
            || MathTool.equals(item.getNastnum(), UFDouble.ZERO_DBL)) {
          item.setCastunitid(item.getCunitid());
          item.setVchangerate("1.0000/1.0000");
          item.setNastnum(item.getNnum());
        }

      }
    }
  }

  /**
   * 默认值处理
   */
  private void setDefaultValue(InvoiceVO[] vos) {
    this.setPurchaseOrg(vos);
    this.setDefaultSourceInfo(vos);

  }

  /**
   * @param vos
   */
  private void setPurchaseOrg(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Set<String> orgSet = new HashSet<String>();
    for (InvoiceVO vo : vos) {
      if (vo == null || vo.getParentVO() == null) {
        continue;
      }
      if (orgSet.contains(vo.getParentVO().getPk_org())) {
        vo.getParentVO().setPk_purchaseorg(vo.getParentVO().getPk_org());
        vo.getParentVO().setPk_purchaseorg_v(vo.getParentVO().getPk_org_v());
      }
      else {
        // 设置采购组织
        if (OrgUnitPubService.isTypeOf(vo.getParentVO().getPk_org(),
            IOrgConst.PURCHASEORGTYPE)) {
          vo.getParentVO().setPk_purchaseorg(vo.getParentVO().getPk_org());
          vo.getParentVO().setPk_purchaseorg_v(vo.getParentVO().getPk_org_v());
          orgSet.add(vo.getParentVO().getPk_org());
        }
      }
    }
  }

  @Override
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] retvos) {
    // 默认值
    this.setDefaultValue(retvos);
    // 设置业务日期
    this.setBusiDate(retvos);
    // 从主数量重新进行计算，多次转单后数量不同
    InvoiceVOUtil.reCalculateBasedNum(retvos);
    // 设置发票的VAT信息（包括税等）
    this.setDefaultVatInfo(retvos);
    // 根据订单未找到付款单位的话，默认取供应商的值
    this.setPayUnit(retvos);
    // 如果汇率有变化，重新取
    new InvoiceExchangeProcessor().resetExchangeByQuery(retvos);
    // 计划价
    this.setPlanPrice(retvos);
    // 询价
    this.queryPriceForVOs(retvos);
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    this.setInvoiceType(retvos);
    return retvos;
  }
  
  @Override
  protected void queryPriceForVOs(InvoiceVO[] retvos) {
    String fiorg = null;
    for (InvoiceVO vo : retvos) {
      fiorg = vo.getParentVO().getPk_org();
      if (fiorg != null) {
        break;
      }
    }
    PriceSource[] PO83 = PUSysParamUtil.getPO83(fiorg);
    // 如果最顶层是询订单价或库存采购入库单价，则不再去询价，取消耗汇总的价格
    if (PriceSource.OrderPice == PO83[0] || 
        PriceSource.PurchaseInPrice == PO83[0]) {
      return;
    }
    super.queryPriceForVOs(retvos);
  }

  @Override
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(retvos);
    List<ICountrySetter> csetterLst =
        new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
    csetterLst.add(new VMIInvoiceCountrySetter());
    IPURemoteCallCombinator rccrule =
        new InvoiceVatDefaultValueFillRule(bills, csetterLst);
    rccrule.prepare();
    rccrule.process();
    // 从无税单价发起一下联动计算，保证价格不变
    VORelationCalculate calculate = new VORelationCalculate();
    for (int i = 0; i < retvos.length; i++) {
      InvoiceHeaderVO header = retvos[i].getParentVO();
      InvoiceItemVO[] items = retvos[i].getChildrenVO();
      for (InvoiceItemVO item : items) {
        // 改变税率重新计算
        calculate.calculate(header, item, InvoiceItemVO.NPRICE);
      }
    }

  }
}

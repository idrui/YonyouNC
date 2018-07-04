package nc.vo.pu.m25.vochange;

import java.util.Map;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * vo交换处理，包括交换前和交换后的处理
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */

public class ChangeVOAdjustor21To25 extends InvoiceChangeVOAdjustor {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 填充费用信息，用于转单的联动计算
    this.fillFeeDataByOrder((OrderVO[]) srcVOs, (InvoiceVO[]) destVOs);
    return super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
  }

  /**
   * 对于费用来说，依据本币价税合计联动计算
   * 
   * @param vos
   */
  public void reCalculateByNumOrTaxMny(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    VORelationCalculate cal = new VORelationCalculate();
    // 强制使用固定换算率，否则为浮动换算率时，从主数量计算，数量不会变
    cal.setBForceFixChgRate(UFBoolean.TRUE);
    for (InvoiceVO vo : vos) {
      InvoiceItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (InvoiceItemVO item : items) {
        if (!MathTool.equals(item.getNnum(), item.getNsourcenum())) {
          // 费用的转单联动，从本币价税合计开始
          if (item.getFrowtype() != null
              && InvoiceRowType.FEE_ROW == item.getFrowtype().intValue()) {
            // 先从主数量联动，只计算数量相关
            cal.calculateOnlyNum(vo.getParentVO(), item, InvoiceItemVO.NNUM);
            cal.calculate(vo.getParentVO(), item, InvoiceItemVO.NTAXMNY);
          }
          // 不是费用的从数量发起联动结算
          else {
            cal.calculate(vo.getParentVO(), item, InvoiceItemVO.NNUM);
          }
        }
      }
    }
  }

  /**
   * 根据来源订单填充费用信息
   * 
   * @param orders
   * @param invoices
   */
  private void fillFeeDataByOrder(OrderVO[] orders, InvoiceVO[] invoices) {
    Map<String, OrderItemVO> orderMap = AggVOUtil.createItemMap(orders);
    for (InvoiceVO vo : invoices) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (orderMap.containsKey(item.getPk_order_b())) {
          OrderItemVO orderItem = orderMap.get(item.getPk_order_b());
          // 认为是费用的转单，并且不是第一次转单
          if (!MathTool.isZero(orderItem.getNfeemny())) {
            // 费用标志
            item.setFrowtype(Integer.valueOf(InvoiceRowType.FEE_ROW));
            // 费用金额
            item.setNtaxmny(MathTool.sub(orderItem.getNtaxmny(),
                orderItem.getNfeemny()));
          }
        }
      }
    }
  }

  @Override
  protected void reCalculateFromSource(InvoiceVO[] resultVOs) {
    // 联动计算,需对费用处理
    this.reCalculateByNumOrTaxMny(resultVOs);
  }
}

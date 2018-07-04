package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.BatchServices;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.rule.OrderBatchCodeFieldMap;
import nc.vo.pu.m21.rule.OrderBatchCodeItemAdapter;
import nc.vo.pubapp.bill.SplitBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-23 ÉÏÎç08:39:19
 * @author wuxla
 */

public class BatchCodeAfterRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    SplitBill<OrderVO> splitBill = new SplitBill<OrderVO>();
    splitBill.appendKey(OrderItemVO.PK_ARRVSTOORG);
    if (ArrayUtils.isEmpty(originVOs)) {
      OrderVO[] insertVOs = splitBill.split(vos);
      this.setBatchCodeForInsert(insertVOs);
    }
    else {
      OrderVO[] updateVOs = splitBill.split(vos);
      OrderVO[] originUpdateVOs = splitBill.split(originVOs);
      this.setBatchCodeForUpdate(updateVOs, originUpdateVOs);
    }
  }

  private void setBatchCodeForInsert(OrderVO[] vos) {
    OrderVO[] batchCodeVOs = OrderVOUtil.getBatchCodeVO(vos);
    if (ArrayUtils.isEmpty(batchCodeVOs)) {
      return;
    }
    batchCodeVOs = OrderVOUtil.getHaveBatchCodeVO(batchCodeVOs);
    if (ArrayUtils.isEmpty(batchCodeVOs)) {
      return;
    }
    OrderBatchCodeFieldMap map = new OrderBatchCodeFieldMap();
    BatchServices.batchProcessAfterSaveForInsert(batchCodeVOs,
        OrderBatchCodeItemAdapter.class, map);
  }

  private void setBatchCodeForUpdate(OrderVO[] vos, OrderVO[] originVOs) {
    OrderVO[] originBatchCodeVOs = OrderVOUtil.getBatchCodeVO(originVOs);
    OrderVO[] batchVOs = OrderVOUtil.getBatchCodeVO(vos);
    if (ArrayUtils.isEmpty(originBatchCodeVOs) && ArrayUtils.isEmpty(batchVOs)) {
      return;
    }
    batchVOs = OrderVOUtil.getHaveBatchCodeVO(batchVOs);
    originBatchCodeVOs = OrderVOUtil.getHaveBatchCodeVO(originBatchCodeVOs);
    if (ArrayUtils.isEmpty(batchVOs) && ArrayUtils.isEmpty(originBatchCodeVOs)) {
      return;
    }
    OrderBatchCodeFieldMap map = new OrderBatchCodeFieldMap();
    BatchServices.batchProcessAfterSaveForUpdate(batchVOs, originBatchCodeVOs,
        OrderBatchCodeItemAdapter.class, map);
  }
}

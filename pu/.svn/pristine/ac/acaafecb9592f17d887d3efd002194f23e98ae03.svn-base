package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.BatchServices;
import nc.vo.ic.batch.BatchcodeRelation;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pubapp.bill.SplitBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单批量编码前规则
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:55:33
 * @author luojw
 */

public class BatchCodeBeforeRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    BatchcodeRelation relation = this.getBatchcodeRelation();
    SplitBill<OrderVO> splitBill = new SplitBill<OrderVO>();
    splitBill.appendKey(OrderItemVO.PK_ARRVSTOORG);
    OrderVO[] splitVOs = splitBill.split(vos);
    OrderVO[] batchCodeVOs = OrderVOUtil.getBatchCodeVO(splitVOs);
    if (ArrayUtils.isEmpty(batchCodeVOs)) {
      return;
    }
    OrderVO[] autoVOs = this.getAutoGeneVOs(batchCodeVOs);
    if (!ArrayUtils.isEmpty(autoVOs)) {
      BatchServices.generateBatchcode(autoVOs, relation);
    }
  }

  private OrderVO[] getAutoGeneVOs(OrderVO[] vos) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (StringUtil.isEmptyWithTrim(itemVO.getVbatchcode())) {
          itemList.add(itemVO);
        }
      }
      if (itemList.size() > 0) {
        OrderVO cloneVO = (OrderVO) vo.clone();
        cloneVO.setBVO(itemList.toArray(new OrderItemVO[itemList.size()]));
        list.add(cloneVO);
      }
    }
    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }

    return null;
  }

  private BatchcodeRelation getBatchcodeRelation() {
    BatchcodeRelation relation = new BatchcodeRelation();
    relation.setBizflowKeyPos(OrderHeaderVO.PK_BUSITYPE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCbizoptorKeyPos(OrderHeaderVO.CEMPLOYEEID,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCdeptKeyPos(OrderHeaderVO.PK_DEPT,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCpurorgKeyPos(OrderHeaderVO.PK_ORG,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCreceiptdeptKeyPos(OrderHeaderVO.PK_INVCSUPLLIER,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCustomerKeyPos(OrderHeaderVO.PK_RECVCUSTOMER,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setDbilldateKeyPos(OrderHeaderVO.DBILLDATE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setDplanarrvdateKeyPos(OrderItemVO.DPLANARRVDATE,
        BatchcodeRelation.POS_IN_BODY);
    relation.setMaterialKeyPos(OrderItemVO.PK_MATERIAL,
        BatchcodeRelation.POS_IN_BODY);
    relation.setPkgroup(OrderHeaderVO.PK_GROUP);
    relation.setPkorg(OrderHeaderVO.PK_ORG);
    relation.setProviderKeyPos(OrderHeaderVO.PK_SUPPLIER,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setStoorgKeyPos(OrderItemVO.PK_ARRVSTOORG,
        BatchcodeRelation.POS_IN_BODY);
    relation.setTransorgKeyPos(OrderItemVO.PK_FLOWSTOCKORG,
        BatchcodeRelation.POS_IN_BODY);
    relation.setTranspmodeKeyPos(OrderHeaderVO.PK_TRANSPORTTYPE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setWarehouseKeyPos(OrderItemVO.PK_RECVSTORDOC,
        BatchcodeRelation.POS_IN_BODY);
    return relation;
  }

  // private void setBatchCodeForInsert(OrderVO[] vos) {
  // OrderBatchCodeFieldMap map = new OrderBatchCodeFieldMap();
  // BatchServices.batchProcessBeforeSaveForInsAndDel(vos,
  // OrderBatchCodeItemAdapter.class, map);
  // }

  // private void setBatchCodeForUpdate(OrderVO[] batchVOs,
  // OrderVO[] originBatchCodeVOs) {
  // OrderBatchCodeFieldMap map = new OrderBatchCodeFieldMap();
  // BatchServices.batchProcessBeforeSaveForUpdate(batchVOs, originBatchCodeVOs,
  // OrderBatchCodeItemAdapter.class, map);
  // }

}

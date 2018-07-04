package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.BatchServices;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.ic.batch.BatchcodeRelation;
import nc.vo.pu.m21.entity.AggItemReceivePlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.pub.util.ArrayUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-26 ÉÏÎç11:17:54
 * @author wuxla
 */

public class RPBatchCodeBeforeRule implements ICompareRule<BatchOperateVO> {

  private OrderVO vo;

  public RPBatchCodeBeforeRule(OrderVO vo) {
    this.vo = vo;
  }

  @Override
  public void process(BatchOperateVO[] vos, BatchOperateVO[] originVOs) {
    // ReceivePlanBatchCodeFieldMap map = new ReceivePlanBatchCodeFieldMap();
    Object[] addVOs = vos[0].getAddObjs();
    Object[] upVOs = vos[0].getUpdObjs();
    // Object[] orginupVOs = originVOs[0].getUpdObjs();
    this.addBatchCode(addVOs);
    this.updateBatchCode(upVOs);
  }

  private void addBatchCode(Object[] addVOs) {
    if (ArrayUtils.isEmpty(addVOs)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = ArrayUtil.convertArrayType(addVOs);
    rpVOs = OrderReceivePlanUtils.getBatchCodeRPVOs(rpVOs);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    AggItemReceivePlanVO[] aggVOs =
        OrderReceivePlanUtils.getAggItemRPVO(rpVOs, this.vo);
    if (ArrayUtils.isEmpty(aggVOs)) {
      return;
    }
    BatchcodeRelation relation = this.getBatchcodeRelation();
    BatchServices.generateBatchcode(aggVOs, relation);

    // BatchServices.batchProcessBeforeSaveForInsAndDel(aggVOs,
    // ReceivePlanBatchCodeItemAdapter.class, map);
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
    relation.setDplanarrvdateKeyPos(OrderReceivePlanVO.DPLANARRVDATE,
        BatchcodeRelation.POS_IN_BODY);
    relation.setMaterialKeyPos(OrderReceivePlanVO.PK_MATERIAL,
        BatchcodeRelation.POS_IN_BODY);
    relation.setPkgroup(OrderHeaderVO.PK_GROUP);
    relation.setPkorg(OrderHeaderVO.PK_ORG);
    relation.setProviderKeyPos(OrderHeaderVO.PK_SUPPLIER,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setStoorgKeyPos(OrderReceivePlanVO.PK_ARRVSTOORG,
        BatchcodeRelation.POS_IN_BODY);
    relation.setTransorgKeyPos(OrderReceivePlanVO.PK_FLOWSTOCKORG,
        BatchcodeRelation.POS_IN_BODY);
    relation.setTranspmodeKeyPos(OrderHeaderVO.PK_TRANSPORTTYPE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setWarehouseKeyPos(OrderReceivePlanVO.PK_RECVSTORDOC,
        BatchcodeRelation.POS_IN_BODY);
    return relation;
  }

  private void updateBatchCode(Object[] upVOs) {
    if (ArrayUtils.isEmpty(upVOs)) {
      return;
    }
    OrderReceivePlanVO[] rpVOs = ArrayUtil.convertArrayType(upVOs);
    // OrderReceivePlanVO[] orginrpVOs = ArrayUtil.convertArrayType(orginupVOs);
    rpVOs = OrderReceivePlanUtils.getBatchCodeRPVOs(rpVOs);
    // orginrpVOs = OrderReceivePlanUtils.getBatchCodeRPVOs(orginrpVOs);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    BatchcodeRelation relation = this.getBatchcodeRelation();
    AggItemReceivePlanVO[] aggVOs =
        OrderReceivePlanUtils.getAggItemRPVO(rpVOs, this.vo);
    if (ArrayUtils.isEmpty(aggVOs)) {
      return;
    }
    BatchServices.generateBatchcode(aggVOs, relation);

    // AggItemReceivePlanVO[] aggVOs =
    // OrderReceivePlanUtils.getAggItemRPVO(rpVOs, this.vo);
    // AggItemReceivePlanVO[] orgaggVOs =
    // OrderReceivePlanUtils.getAggItemRPVO(orginrpVOs, this.vo);
    // BatchServices.batchProcessBeforeSaveForUpdate(aggVOs, orgaggVOs,
    // ReceivePlanBatchCodeItemAdapter.class, map);
  }

}

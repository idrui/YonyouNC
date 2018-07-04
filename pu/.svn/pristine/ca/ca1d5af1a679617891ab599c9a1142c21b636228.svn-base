package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.BatchServices;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.AggItemReceivePlanVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.rule.ReceivePlanBatchCodeFieldMap;
import nc.vo.pu.m21.rule.ReceivePlanBatchCodeItemAdapter;
import nc.vo.pu.pub.util.ArrayUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-26 ÉÏÎç11:19:05
 * @author wuxla
 */

public class RPBatchCodeAfterRule implements ICompareRule<BatchOperateVO> {

  private OrderVO vo;

  public RPBatchCodeAfterRule(OrderVO vo) {
    this.vo = vo;
  }

  @Override
  public void process(BatchOperateVO[] vos, BatchOperateVO[] originVOs) {
    ReceivePlanBatchCodeFieldMap map = new ReceivePlanBatchCodeFieldMap();
    Object[] addVOs = vos[0].getAddObjs();
    Object[] upVOs = vos[0].getUpdObjs();
    this.addBatchCode(addVOs, map);
    Object[] orginupVOs = originVOs[0].getUpdObjs();
    this.updateBatchCode(upVOs, orginupVOs, map);
  }

  private void addBatchCode(Object[] addVOs, ReceivePlanBatchCodeFieldMap map) {
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
    BatchServices.batchProcessAfterSaveForInsert(aggVOs,
        ReceivePlanBatchCodeItemAdapter.class, map);
  }

  private void updateBatchCode(Object[] upVOs, Object[] orginupVOs,
      ReceivePlanBatchCodeFieldMap map) {
    if (ArrayUtils.isEmpty(upVOs)) {
      return;
    }
    OrderReceivePlanVO[] rpVOs = ArrayUtil.convertArrayType(upVOs);
    OrderReceivePlanVO[] orginrpVOs = ArrayUtil.convertArrayType(orginupVOs);
    rpVOs = OrderReceivePlanUtils.getBatchCodeRPVOs(rpVOs);
    orginrpVOs = OrderReceivePlanUtils.getBatchCodeRPVOs(orginrpVOs);
    AggItemReceivePlanVO[] aggVOs =
        OrderReceivePlanUtils.getAggItemRPVO(rpVOs, this.vo);
    AggItemReceivePlanVO[] orgaggVOs =
        OrderReceivePlanUtils.getAggItemRPVO(orginrpVOs, this.vo);
    BatchServices.batchProcessAfterSaveForUpdate(aggVOs, orgaggVOs,
        ReceivePlanBatchCodeItemAdapter.class, map);
  }
}

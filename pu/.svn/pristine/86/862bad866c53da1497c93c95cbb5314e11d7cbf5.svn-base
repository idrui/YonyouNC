package nc.bs.pu.m23.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * 
 * @description
 * 退货单到货待检量检查
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class ChkBackArWaitNumRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    for (ArriveVO aggVO : voArray) {
      // 退货单到货待检量检查
      this.chkBackArWaitNum(aggVO);
    }
  }

  private void chkBackArWaitNum(ArriveVO aggVO) {

    // 到货单没有退货标志，不做到货待检量检查
    if (!ValueUtils.getBoolean(aggVO.getHVO().getBisback())) {
      return;
    }

    // 委外订单不检查
    if (ArrivePublicUtil.isArriveFromSC(aggVO)) {
      return;
    }

    // // TODO hanbin 获取来源订单行是否有退货标志
    // String[] poBidArray = (String[]) AggVOUtil.getDistinctItemFieldArray(
    // new AggregatedValueObject[] { aggVO }, ArriveItemVO.CSOURCEBID,
    // String.class);
    //
    // // <采购订单行ID,采购订单行VO>
    // Map<String, CircularlyAccessibleValueObject> poBidToPOVOMap = new
    // HashMap<String, CircularlyAccessibleValueObject>();

    // TODO hanbin 等待库存提供接口

  }

}

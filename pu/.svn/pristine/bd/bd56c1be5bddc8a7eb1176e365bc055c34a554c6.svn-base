package nc.bs.pu.m422x.query.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            出库申请单参照查询物资需求申请单时，计算可申请出库主数量
 * @scene
 *      出库申请单参照查询物资需求申请单
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-12-16 下午02:37:28
 * @author wuxla
 */
public class CanOutreqNumCalcRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StoreReqAppVO vo : vos) {
      for (StoreReqAppItemVO itemVO : vo.getBVO()) {
        itemVO.setNcanoutreqnum(MathTool.sub(itemVO.getNnum(),
            itemVO.getNaccuoutreqnum()));
      }
    }
  }

}

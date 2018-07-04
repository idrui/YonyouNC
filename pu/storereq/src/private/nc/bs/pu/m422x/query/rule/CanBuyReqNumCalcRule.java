package nc.bs.pu.m422x.query.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 查询时计算可请购主数量
 * 
 * @since 6.0
 * @version 2012-5-31 上午08:19:08
 * @author lixyp
 */
public class CanBuyReqNumCalcRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StoreReqAppVO vo : vos) {
      for (StoreReqAppItemVO storeReqAppItemVO : vo.getBVO()) {
        // 主数量 = 主数量 - 可请购主数量 - 可入库主数量。
        storeReqAppItemVO.setNcanbuyreqnnum(MathTool.sub(
            MathTool.sub(storeReqAppItemVO.getNnum(),
                storeReqAppItemVO.getNaccumbuyreqnum()),
            storeReqAppItemVO.getNaccuoutnum()));
      }
    }
  }

}

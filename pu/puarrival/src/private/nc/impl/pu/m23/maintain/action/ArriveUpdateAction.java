package nc.impl.pu.m23.maintain.action;

import nc.bs.pu.m23.maintain.ArriveUpdateBP;
import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的修改保存对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class ArriveUpdateAction {

  /**
   * 方法功能描述：到货单的修改保存的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午11:14:57
   */
  public ArriveVO[] updateArrive(ArriveVO[] voArray, ArrivalUIToBSEnv env) {
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(
            ArriveActionPlugInPoint.ArriveUpdateAction);
    // 加锁单据 +检查TS
    BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(voArray);

    // 获得修改前VO
    ArriveVO[] originVOArray = tool.getOriginBills();

    // 调用BP
    ArriveUpdateBP bp = new ArriveUpdateBP(env);
    ArriveVO[] ret = bp.updateArrive(voArray, originVOArray);
    processer.after(ret);
    return ret;
  }

}

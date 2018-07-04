package nc.impl.pu.m23.maintain.action;

import nc.bs.pu.m23.maintain.ArriveDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m23.entity.ArriveVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的删除对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class ArriveDeleteAction {

  /**
   * 方法功能描述：到货单的删除的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午11:14:57
   */
  public void deleteArrive(ArriveVO[] voArray) {
    // 加锁单据 +检查TS
    new BillTransferTool<ArriveVO>(voArray);
    // 调用BP
    new ArriveDeleteBP(null).deleteArrive(voArray);
  }
}

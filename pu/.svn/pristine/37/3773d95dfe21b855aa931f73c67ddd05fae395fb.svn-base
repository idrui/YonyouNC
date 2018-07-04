/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:03:05
 */
package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pu.m20.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午10:03:05
 */
public class PraybillUpdateAction {

  public PraybillVO[] update(PraybillVO[] Vos) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] returnVos = null;
      AroundProcesser<PraybillVO> processer =
          new AroundProcesser<PraybillVO>(ActionPlugInPoint.UpdateAction);
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      origVos = tool.getOriginBills();
      returnVos = new PraybillUpdateBP().update(Vos, origVos, false);
      processer.after(Vos);

      return returnVos;
    }
    return null;
  }

}

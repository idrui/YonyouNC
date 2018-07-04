package nc.impl.pu.m24.action;

import nc.bs.pu.m24.maintain.PricestlUpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m24.entity.PricestlVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:46:51
 */
public class PricestlUpdateAction {

  public PricestlVO[] update(PricestlVO[] Vos) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PricestlVO[] origVos = null;
      PricestlVO[] updateVos = null;
      PricestlVO[] returnVos = null;
      BillTransferTool<PricestlVO> tool = new BillTransferTool<PricestlVO>(Vos);
      origVos = tool.getOriginBills();
      // 补全差异VO
      updateVos = tool.getClientFullInfoBill();
      returnVos = new PricestlUpdateBP().update(updateVos, origVos);
      // 获取返回前台的差异VO
      returnVos = tool.getBillForToClient(returnVos);
      return returnVos;
    }
    return null;
  }

}

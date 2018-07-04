package nc.impl.pu.m24.action;

import nc.bs.pu.m24.maintain.PricestlUpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m24.entity.PricestlVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥�޸ı���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 ����02:46:51
 */
public class PricestlUpdateAction {

  public PricestlVO[] update(PricestlVO[] Vos) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PricestlVO[] origVos = null;
      PricestlVO[] updateVos = null;
      PricestlVO[] returnVos = null;
      BillTransferTool<PricestlVO> tool = new BillTransferTool<PricestlVO>(Vos);
      origVos = tool.getOriginBills();
      // ��ȫ����VO
      updateVos = tool.getClientFullInfoBill();
      returnVos = new PricestlUpdateBP().update(updateVos, origVos);
      // ��ȡ����ǰ̨�Ĳ���VO
      returnVos = tool.getBillForToClient(returnVos);
      return returnVos;
    }
    return null;
  }

}

/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����10:15:03
 */
package nc.impl.pu.m422x.action;

import nc.bs.pu.m422x.maintain.StoreReqAppDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ɾ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 ����10:15:03
 */
public class StoreReqAppDeleteAction {

  public void delete(StoreReqAppVO[] vos) {
    BillTransferTool<StoreReqAppVO> tool =
        new BillTransferTool<StoreReqAppVO>(vos);
    StoreReqAppVO[] fullVOs = tool.getClientFullInfoBill();

    new StoreReqAppDeleteBP().delete(fullVOs);
  }

}

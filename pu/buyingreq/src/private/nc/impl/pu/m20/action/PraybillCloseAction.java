/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 ����10:04:04
 */
package nc.impl.pu.m20.action;

import java.util.Set;

import nc.bs.pu.m20.maintain.PraybillCloseBP;
import nc.bs.pu.m20.maintain.PraybillCloseRowBP;
import nc.bs.pu.m20.maintain.PraybillOpenBP;
import nc.bs.pu.m20.maintain.PraybillOpenRowBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * �빺���򿪹ر�Action
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺��������
 * <li>�빺�������ر�
 * <li>�빺���д�
 * <li>�빺���йر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-15 ����10:29:13
 */
public class PraybillCloseAction {

  /**
   * �빺�������رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param Vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] closeBill(PraybillVO[] Vos) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      origVos = tool.getOriginBills();
      returnVos = new PraybillCloseBP().closeBill(Vos, origVos);
      // ��ȡ����ǰ̨�Ĳ���VO
      // returnVos = tool.getBillForToClient(returnVos);
      return returnVos;
    }
    return null;
  }

  /**
   * �빺���йرա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param Vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] closeBillRow(PraybillVO[] Vos, Set<String> closeRow) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      origVos = tool.getOriginBills();
      this.setOpenCloseRow(closeRow, Vos, UFBoolean.TRUE);
      returnVos = new PraybillCloseRowBP().closeBillRow(Vos, origVos);
      return returnVos;
    }
    return null;
  }

  /**
   * �빺�������򿪡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param Vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] openBill(PraybillVO[] Vos) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      origVos = tool.getOriginBills();
      returnVos = new PraybillOpenBP().openBill(Vos, origVos);
      return returnVos;
    }
    return null;
  }

  /**
   * �빺���д򿪡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param Vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] openBillRow(PraybillVO[] Vos, Set<String> openRow) {
    if (!ArrayUtils.isEmpty(Vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      origVos = tool.getOriginBills();
      this.setOpenCloseRow(openRow, Vos, UFBoolean.FALSE);
      returnVos = new PraybillOpenRowBP().openBillRow(Vos, origVos);
      return returnVos;
    }
    return null;
  }

  private void setOpenCloseRow(Set<String> closePk, PraybillVO[] updateVos,
      UFBoolean closeFlag) {

    for (int i = 0, len = updateVos.length; i < len; i++) {
      PraybillItemVO[] items = updateVos[i].getBVO();
      updateVos[i].getHVO().setStatus(VOStatus.UPDATED);
      for (PraybillItemVO item : items) {
        if (closePk.contains(item.getPk_praybill_b())) {
          item.setStatus(VOStatus.UPDATED);
          item.setBrowclose(closeFlag);
        }
      }
    }
  }
}

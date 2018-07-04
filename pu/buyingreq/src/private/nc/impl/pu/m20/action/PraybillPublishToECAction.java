package nc.impl.pu.m20.action;

import java.util.HashSet;

import nc.bs.pu.m20.maintain.PraybillPublishToEcBP;
import nc.bs.pu.m20.maintain.PraybillUnPublishToEcBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * �빺����������������Action
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������������
 * <li>ȡ����������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-15 ����10:29:13
 */
public class PraybillPublishToECAction {

  /**
   * �빺����������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] publishToEC(PraybillVO[] vos) {
    if (!ArrayUtils.isEmpty(vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] updateVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(vos);
      origVos = tool.getOriginBills();
      // ��ȫ����VO
      updateVos = tool.getClientFullInfoBill();
      this.setPublishRow(vos, updateVos, UFBoolean.TRUE);
      returnVos = new PraybillPublishToEcBP().publishToEc(updateVos, origVos);
      String[] msgs = this.getMsg(returnVos);
      // ��ȡ����ǰ̨�Ĳ���VO
      returnVos = tool.getBillForToClient(returnVos);
      this.setMsg(msgs, returnVos);

      return returnVos;
    }
    return null;
  }

  /**
   * �빺��ȡ����������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:44:17
   */
  public PraybillVO[] unPublishToEC(PraybillVO[] vos) {
    if (!ArrayUtils.isEmpty(vos)) {
      PraybillVO[] origVos = null;
      PraybillVO[] updateVos = null;
      PraybillVO[] returnVos = null;
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(vos);
      origVos = tool.getOriginBills();
      // ��ȫ����VO
      updateVos = tool.getClientFullInfoBill();
      this.setPublishRow(vos, updateVos, UFBoolean.FALSE);
      returnVos =
          new PraybillUnPublishToEcBP().unPublishToEc(updateVos, origVos);
      String[] msgs = this.getMsg(returnVos);
      // ��ȡ����ǰ̨�Ĳ���VO
      returnVos = tool.getBillForToClient(returnVos);
      this.setMsg(msgs, returnVos);
      return returnVos;
    }
    return null;
  }

  private HashSet<String> getItemPK(PraybillVO vo) {
    HashSet<String> pks = new HashSet<String>();
    PraybillItemVO[] items = vo.getBVO();
    for (PraybillItemVO item : items) {
      pks.add(item.getPk_praybill_b());
    }
    return pks;
  }

  private String[] getMsg(PraybillVO[] vos) {
    String[] msgs = new String[vos.length];
    for (int i = 0, len = vos.length; i < len; i++) {
      msgs[i] = vos[i].getMsg();
    }
    return msgs;
  }

  private void setMsg(String[] msgs, PraybillVO[] vos) {
    for (int i = 0, len = vos.length; i < len; i++) {
      vos[i].setMsg(msgs[i]);
    }

  }

  private void setPublishRow(PraybillVO[] closeVOs, PraybillVO[] updateVos,
      UFBoolean publishFlag) {

    for (int i = 0, len = closeVOs.length; i < len; i++) {
      PraybillItemVO[] items = updateVos[i].getBVO();
      updateVos[i].getHVO().setStatus(VOStatus.UPDATED);
      HashSet<String> closePk = this.getItemPK(closeVOs[i]);
      for (PraybillItemVO item : items) {
        if (closePk.contains(item.getPk_praybill_b())) {
          item.setStatus(VOStatus.UPDATED);
          item.setBpublishtoec(publishFlag);
        }
      }
    }
  }
}

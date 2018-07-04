package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pu.m20.rule.ReviseNumCheckRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.ApprovingEditCheckRule;
import nc.vo.pu.pub.rule.ApprovingEditSendMsgRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���޶�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-22 ����04:13:56
 */
public class PraybillRAction {

  /**
   * ���������������빺���޶�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:14:12
   */
  public PraybillVO[] revise(PraybillVO[] orderVos, PraybillContext ptx) {
    if (ArrayUtils.isEmpty(orderVos)) {
      return null;
    }

    BillTransferTool<PraybillVO> tool =
        new BillTransferTool<PraybillVO>(orderVos);
    PraybillVO[] voOrginal = tool.getOriginBills();

    // ִ�����°�Ĳɹ���������
    PraybillVO[] voRevised = this.saveNewVO(orderVos, voOrginal, ptx);

    // ������ʷ��¼
    this.setOrignialVO(voOrginal);

    // ִ����ʷ��������
    this.saveHistoryVO(voOrginal);

    return voRevised;

  }

  /**
   * �����������������þ�VO��ֵ��ʹ������ʷ��¼��
   * <p>
   * <b>����˵��</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:14:32
   */
  public void setOrignialVO(PraybillVO[] voOrginal) {
    if (ArrayUtils.isEmpty(voOrginal)) {
      return;
    }

    for (int i = 0; i < voOrginal.length; ++i) {
      // ͷ
      PraybillHeaderVO voHead = voOrginal[i].getHVO();
      String pk_praybill = voHead.getPk_praybill();
      voHead.setStatus(VOStatus.NEW);
      voHead.setPk_praybill(null);
      voHead.setBislatest(UFBoolean.FALSE);
      voHead.setTs(null);
      voHead.setPk_srcpraybill(pk_praybill);

      // ��
      PraybillItemVO[] voItems = voOrginal[i].getBVO();
      for (int j = 0; j < voItems.length; ++j) {
        voItems[j].setStatus(VOStatus.NEW);
        voItems[j].setPk_praybill_b(null);
        voItems[j].setPk_praybill(null);
        voItems[j].setTs(null);
      }
    }
  }

  /**
   * ����������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:15:52
   */
  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer,
      PraybillContext ptx) {
    // �޶������Ϸ��Լ�飬�޶����������ԭ����ͬ�������Ҳ���С�ں�������
    processer.addAfterRule(new ReviseNumCheckRule(ptx
        .getPrayNumToleranceConfirm()));
  }

  /**
   * �������������� ����ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:16:02
   */
  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {

    // �����Ϸ��Լ��
    processer.addBeforeRule(new ChkPrayVONotNullRule());

    // ֧���������޸ģ��޶����ĵ��ݣ�����Ƿ�Ӧ���ɵ�ǰ�������޸ģ��޶���
    processer.addBeforeFinalRule(new ApprovingEditCheckRule<PraybillVO>(
        POBillType.PrayBill));

    // ֧���������޸ģ��޶����ĵ��ݣ���̨�������Ѿ����������������˷�����Ϣ
    processer.addBeforeFinalRule(new ApprovingEditSendMsgRule<PraybillVO>());

  }

  /**
   * ����������������ʷ�빺�����档
   * <p>
   * <b>����˵��</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:16:38
   */
  private void saveHistoryVO(PraybillVO[] voOrginal) {
    if (!ArrayUtils.isEmpty(voOrginal)) {
      new BillInsert<PraybillVO>().insert(voOrginal);
    }
  }

  /**
   * �����������������°���빺�����档
   * <p>
   * <b>����˵��</b>
   * 
   * @param voSaved
   * @param voOrginal
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 ����04:16:14
   */
  private PraybillVO[] saveNewVO(PraybillVO[] voSaved, PraybillVO[] voOrginal,
      PraybillContext ptx) {
    if (ArrayUtils.isEmpty(voSaved)) {
      return null;
    }

    // ����
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(null);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, ptx);

    PraybillVO[] vos = voSaved;
    // ִ�����°�Ĳɹ���������
    processer.before(vos, voOrginal);
    vos = new PraybillUpdateBP().update(vos, voOrginal, true);

    processer.after(vos, voOrginal);

    return vos;
  }

}

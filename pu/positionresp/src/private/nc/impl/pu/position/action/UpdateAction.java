package nc.impl.pu.position.action;

import nc.bs.pu.position.maintain.UpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ƻ���(�ɹ���)���������޸ı��涯����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ���(�ɹ���)���������޸ı���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����03:16:25
 */
public class UpdateAction {
  public UpdateAction() {
    // ȱʡ���췽��
  }

  /**
   * ���������������ƻ���(�ɹ���)���������޸ı��涯����ڷ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   *          ��Ҫ����ļƻ���(�ɹ���)������������
   * @return �����ļƻ���(�ɹ���)�����������飬������VO
   */
  public PositionVO[] update(PositionVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PositionVO> transferTool =
        new BillTransferTool<PositionVO>(bills);
    PositionVO[] upbills = transferTool.getClientFullInfoBill();
    PositionVO[] originBills = transferTool.getOriginBills();
    TimeLog.info("��ȫǰ̨VO������޸�ǰVO�����������ʱ���");/* -=notranslate=- */

    TimeLog.logStart();

    TimeLog.info("���ʱ���������Դ");/* -=notranslate=- */

    CompareAroundProcesser<PositionVO> compareProcesser =
        new CompareAroundProcesser<PositionVO>(null);

    TimeLog.logStart();
    compareProcesser.before(upbills, originBills);
    TimeLog.info("���ø��±���BPǰִ��ҵ�����");/* -=notranslate=- */

    UpdateBP action = new UpdateBP();
    PositionVO[] ret = action.update(upbills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info("���ø��±���BP��ִ��ҵ�����");/* -=notranslate=- */

    TimeLog.logStart();
    ret = transferTool.getBillForToClient(ret);
    TimeLog.info("��֯����ֵ,����������VO");/* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("ҵ����־");/* -=notranslate=- */

    return ret;
  }

}

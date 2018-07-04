package nc.impl.pu.position.action;

import nc.bs.pu.position.maintain.InsertBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ƻ���(�ɹ���)���������������涯��
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ���(�ɹ���)����������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����03:16:08
 */
public class InsertAction {
  /**
   * ���������������ƻ���(�ɹ���)������������������ڷ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   *          ��Ҫ��������ļƻ���(�ɹ���)������������
   * @return �����ļƻ���(�ɹ���)�����������飬������VO����
   */
  public PositionVO[] insert(PositionVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PositionVO> transferTool =
        new BillTransferTool<PositionVO>(bills);
    TimeLog.info("����ǰ̨VO����֯����ֵʱʹ��"); /* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("���ʱ���������Դ"); /* -=notranslate=- */

    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("������������BPǰִ��ҵ�����"); /* -=notranslate=- */

    TimeLog.logStart();
    InsertBP bp = new InsertBP();
    PositionVO[] vos = bp.insert(bills);
    TimeLog.info("������������BP�����б���"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("������������BP��ִ��ҵ�����"); /* -=notranslate=- */

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("��֯����ֵ,����������VO"); /* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("ҵ����־"); /* -=notranslate=- */
    return vos;
  }

}

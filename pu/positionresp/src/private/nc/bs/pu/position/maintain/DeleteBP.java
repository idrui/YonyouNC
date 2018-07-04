package nc.bs.pu.position.maintain;

import nc.bs.pu.position.maintain.rule.DelTRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ƻ���(�ɹ���)��������ɾ��BP
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ���(�ɹ���)��������ɾ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����02:57:58
 */
public class DeleteBP {
  /**
   * DeleteBP �Ĺ�����
   */
  public DeleteBP() {
    // ȱʡ���췽��
  }

  /**
   * ���������������ƻ���(�ɹ���)��������ɾ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����02:52:23
   */
  public void delete(PositionVO[] bills) {
    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    // ����ִ��ǰҵ�����
    this.addBeforeRule();

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("ɾ��ǰִ��ҵ�����");/* -=notranslate=- */

    TimeLog.logStart();
    BillDelete<PositionVO> bo = new BillDelete<PositionVO>();
    bo.delete(bills);
    TimeLog.info("д���ݿ⣬ɾ������");/* -=notranslate=- */

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("ɾ����ִ��ҵ�����");/* -=notranslate=- */
  }

  /**
   * �����������������º����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����01:35:56
   */
  private void addAfterRule(AroundProcesser<PositionVO> processer) {

    processer.addBeforeRule(new DelTRule());
  }

  /**
   * ������������������ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����01:35:56
   */
  private void addBeforeRule() {
    // û�й���
  }
}

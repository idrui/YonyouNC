/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 ����03:40:54
 */
package nc.pubitf.pu.m21.arap.mf3;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������д�����Ĳ����ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-28 ����03:40:54
 */
public interface IOrderWriteBackParaForF3Verify extends IWriteBackPubPara {
  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 ����08:39:33
   */
  public UFDate getBillDate();

  /**
   * ��������������ԭ�ұ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 ����01:46:31
   */
  public String getCurrency();

  /**
   * ���������������ۼ��Ѻ������Ҹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 ����04:00:17
   */
  public UFDouble getDiffVerifyMny();

  /**
   * ��������������ԭ�ҽ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 ����08:38:03
   */
  public UFDouble getDiffVerifyOrgMny();
}

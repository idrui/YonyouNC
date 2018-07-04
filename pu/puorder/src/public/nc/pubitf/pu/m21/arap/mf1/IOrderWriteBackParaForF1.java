/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 ����03:45:25
 */
package nc.pubitf.pu.m21.arap.mf1;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������д�����ۼ��Ѻ������ҿ�Ʊ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-8 ����03:45:25
 */
public interface IOrderWriteBackParaForF1 extends IWriteBackPubPara {
  /**
   * ����������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 ����08:39:03
   */
  UFDate getBillDate();

  /**
   * ��������������ԭ�ұ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 ����01:46:04
   */
  String getCurrency();

  /**
   * �����������������ҽ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 ����08:36:27
   */
  UFDouble getDiffMny();

  /**
   * ��������������ԭ�ҽ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 ����08:36:47
   */
  UFDouble getDiffOrgMny();

  /**
   * �Ƿ����
   * 
   * @return ����Ǻ���Ϊtrue�����ȡ������Ϊfalse
   */
  boolean isVerify();
}

/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 ����08:44:28
 */
package nc.pubitf.pu.m21.ic.m45;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����������д�����ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-5 ����08:44:28
 */
public interface IOrderWriteBackPara extends IWriteBackPubPara {

  /**
   * ���ε����ӱ�ID
   * <p>
   * ��ɹ������ĵ����ƻ�ID
   **/
  public String getBBID();

  /** �Ƿ�ͬʱ�ر�(���������) **/
  public boolean isClose();

  /** �Ƿ��û�ȷ�����������ݲ����ʾ **/
  public boolean isNumUserConfirm();

  /** �Ƿ��˻�(��) **/
  public boolean isReturn();
}

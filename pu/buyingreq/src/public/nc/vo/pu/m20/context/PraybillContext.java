/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-25 ����11:41:55
 */
package nc.vo.pu.m20.context;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���������ʱǰ̨����̨�Ļ�����Ϣ��һ����ƽ̨������userObj���⴫
 * <ul>
 * <li>��̨�����Ҫ�û�ȷ�ϵı�־
 * <li>�����ű��Ƿ���ǰ̨�ֹ�������־
 * </ul>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2012-5-22 ����11:41:55
 */
public class PraybillContext implements Serializable {
  private static final long serialVersionUID = 8369670137222113733L;

  /**
   * �빺�������ݲ����
   */
  private UFBoolean PrayNumToleranceConfirm = UFBoolean.FALSE;

  public UFBoolean getPrayNumToleranceConfirm() {
    return this.PrayNumToleranceConfirm;
  }

  public void setPrayNumToleranceConfirm(UFBoolean prayNumToleranceConfirm) {
    this.PrayNumToleranceConfirm = prayNumToleranceConfirm;
  }

}

package nc.vo.pu.m27.pub;

import nc.vo.pub.ValidationException;

/**
 * <p>
 * <b>�����еĻ����쳣</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-1-26 ����05:03:15
 */
public class MatchValidationException extends ValidationException {
  /**
     * 
     */
  private static final long serialVersionUID = -225413112004968254L;

  /**
   * MatchValidationException �Ĺ�����
   * 
   * @param sMsg
   */
  public MatchValidationException(String sMsg) {
    super(sMsg);
  }
}

package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

/**
 * <p>
 * <b>����У���������ȷ��Ҫ���н������ʱ���ж������Ƿ���Ͻ�Ҫ�����������</b>
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
 * @time 2010-1-4 ����02:57:24
 */
public interface IMatchCheckRule {

  /**
   * ��麯��
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param mmData ��������
   * @param settleEnvironment ���㻷����Ϣ
   * @throws ValidationException <p>
   * @author wangyf
   * @time 2010-1-4 ����02:59:11
   */
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException;

}

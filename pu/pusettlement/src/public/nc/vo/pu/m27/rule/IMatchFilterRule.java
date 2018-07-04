package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

public interface IMatchFilterRule {

  /**
   * ����������ǰ���˿ɽ�������
   * 
   * @param mmData ����ǰ�Ľ�������
   * @param settleEnvironment ���㻷����Ϣ
   * @return ���˺�Ľ�����Ϣ
   * @throws ValidationException
   */
  public void filter(MatchMergeData mmData, SettleEnvironment settleEnvironment);
}

package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

public interface IMatchFilterRule {

  /**
   * 在真正结算前过滤可结算数据
   * 
   * @param mmData 过滤前的结算数据
   * @param settleEnvironment 结算环境信息
   * @return 过滤后的结算信息
   * @throws ValidationException
   */
  public void filter(MatchMergeData mmData, SettleEnvironment settleEnvironment);
}

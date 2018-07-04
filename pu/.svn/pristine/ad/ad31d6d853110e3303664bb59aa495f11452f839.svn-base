package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

/**
 * <p>
 * <b>公共结算规则</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-1-6 下午03:01:03
 */
public abstract class MatchRule implements IMatchFilterRule, IMatchCheckRule {

  @Override
  public abstract void check(MatchMergeData mmData,
      SettleEnvironment settleEnvironment) throws ValidationException;

  @Override
  public void filter(MatchMergeData mmData, SettleEnvironment settleEnvironment) {
    // do nothing
  }

}

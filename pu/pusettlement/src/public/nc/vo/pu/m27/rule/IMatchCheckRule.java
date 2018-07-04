package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

/**
 * <p>
 * <b>数据校验规则。数据确定要进行结算操作时，判断数据是否符合将要结算的条件。</b>
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
 * @time 2010-1-4 下午02:57:24
 */
public interface IMatchCheckRule {

  /**
   * 检查函数
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param mmData 结算数据
   * @param settleEnvironment 结算环境信息
   * @throws ValidationException <p>
   * @author wangyf
   * @time 2010-1-4 下午02:59:11
   */
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException;

}

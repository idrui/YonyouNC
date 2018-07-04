/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 下午09:02:35
 */
package nc.impl.pu.settle;

import nc.impl.pu.settle.action.AutoSettleRuleQueryAction;
import nc.impl.pu.settle.action.AutoSettleRuleSaveActioin;
import nc.itf.pu.settle.IAutoSettleRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算规则实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 下午09:02:35
 */
public class AutoSettleRuleImpl implements IAutoSettleRule {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.settle.IAutoSettleRule#queryAutoSettleRule(java.lang.String[])
   */
  @Override
  public SuperVO[] queryAutoSettleRule(String[] pks) throws BusinessException {
    try {
      return new AutoSettleRuleQueryAction().queryAutoSettleRule(pks);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.settle.IAutoSettleRule#addAutoSettleRule(java.lang.Object[])
   */
  @Override
  public SuperVO[] saveAutoSettleRule(SuperVO[] rules) throws BusinessException {
    try {
      return new AutoSettleRuleSaveActioin().saveAutoSettleRule(rules);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}

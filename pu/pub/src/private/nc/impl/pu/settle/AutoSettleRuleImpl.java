/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 ����09:02:35
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ��������ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 ����09:02:35
 */
public class AutoSettleRuleImpl implements IAutoSettleRule {

  /**
   * ���෽����д
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
   * ���෽����д
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

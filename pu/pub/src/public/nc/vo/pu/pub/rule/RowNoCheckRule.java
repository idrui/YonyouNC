package nc.vo.pu.pub.rule;

import nc.bs.uif2.validation.ValidationException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行号检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-25 下午08:32:46
 */
public class RowNoCheckRule {

  /**
   * 方法功能描述：行号为空检查，为负数检查，重复行号检查
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param key
   *          表体行号的key
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 上午10:28:49
   */
  public void checkRowNo(AggregatedValueObject[] vos, String key) {
    for (AggregatedValueObject vo : vos) {
      CircularlyAccessibleValueObject[] children = vo.getChildrenVO();

      if (ArrayUtils.isEmpty(children)) {
        continue;
      }

      try {
        VORowNoUtils.validateRowNo(children, key);
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
  }
}

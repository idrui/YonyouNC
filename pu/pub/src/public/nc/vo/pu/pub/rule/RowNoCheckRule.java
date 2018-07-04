package nc.vo.pu.pub.rule;

import nc.bs.uif2.validation.ValidationException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�кż��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-25 ����08:32:46
 */
public class RowNoCheckRule {

  /**
   * ���������������к�Ϊ�ռ�飬Ϊ������飬�ظ��кż��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param key
   *          �����кŵ�key
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 ����10:28:49
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

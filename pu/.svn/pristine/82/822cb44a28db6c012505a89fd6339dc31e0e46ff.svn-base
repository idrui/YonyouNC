/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午09:25:52
 */
package nc.vo.pu.pub.rule;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查传入数组的完整性
 * <li>数组元素是否有空值，表头，表体完整性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-26 上午09:25:52
 */
public class ItemNotNullCheckRule {

  /**
   * 方法功能描述：检查表体是否为空
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 上午10:29:54
   */
  public void checkItem(AggregatedValueObject[] vos) {
    for (AggregatedValueObject vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0085")/*
                                                                     * @res
                                                                     * "传入的数组中有空值 ！"
                                                                     */);
      }
      else if (!ArrayUtils.isEmpty(vo.getChildrenVO())) {
        for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
          if (item.getStatus() != VOStatus.DELETED) {
            return;
          }
        }
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0086")/*
                                                                     * @res
                                                                     * "传入的数组中有的表体为空，请输入明细！"
                                                                     */);
      }
      // else if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
      // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
      // .getNCLangRes().getStrByID("4004000_0", "04004000-0086")/*
      // * @res
      // * "传入的数组中有的表体为空，请输入明细！"
      // */);
      // }
      // else {
      // if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
      // return;
      // }
      // List<CircularlyAccessibleValueObject> list =
      // new ArrayList<CircularlyAccessibleValueObject>();
      // for (CircularlyAccessibleValueObject body : vo.getChildrenVO()) {
      // if (body != null && body.getStatus() != VOStatus.DELETED) {
      // list.add(body);
      // }
      // }
      //
      // if (list.size() == 0) {
      // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
      // .getNCLangRes().getStrByID("4004000_0", "04004000-0086")/*
      // * @res
      // * "传入的数组中有的表体为空，请输入明细！"
      // */);
      // }
      // }
    }
  }
}

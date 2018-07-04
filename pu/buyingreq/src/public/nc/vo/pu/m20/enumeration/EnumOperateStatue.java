/**
 * $$文件说明$$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-02-04 13:39:48
 */
package nc.vo.pu.m20.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-02-04 13:39:48
 */

public class EnumOperateStatue extends NCMDEnum {
  /** 新增 **/
  public static final EnumOperateStatue ADD = MDEnum.valueOf(
      EnumOperateStatue.class, Integer.valueOf(0));

  /** 删除 **/
  public static final EnumOperateStatue DELETE = MDEnum.valueOf(
      EnumOperateStatue.class, Integer.valueOf(2));

  /** 修改 **/
  public static final EnumOperateStatue MODIFY = MDEnum.valueOf(
      EnumOperateStatue.class, Integer.valueOf(1));

  /**
   * EnumOperateStatue 的构造子
   * 
   * @param enumvalue
   */
  public EnumOperateStatue(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * 方法功能描述：取得枚举的int值
   * <p>
   * <b>参数说明</b>
   * 
   * @return 枚举int值
   *         <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-26 下午02:03:18
   */
  @Override
  public int toInt() {
    return ((Integer) this.value()).intValue();
  }

  /**
   * 方法功能描述：取得枚举的Integer值
   * <p>
   * <b>参数说明</b>
   * 
   * @return 枚举Integer值
   *         <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-26 下午02:03:18
   */
  @Override
  public Integer toInteger() {
    return (Integer) this.value();
  }

}

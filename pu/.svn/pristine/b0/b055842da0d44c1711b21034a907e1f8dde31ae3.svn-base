package nc.vo.pu.pub.util;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可以根据key来获取和设置属性值的对象
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 上午09:28:51
 */
public interface IKeyValue {
  /**
   * <p>
   * <b>本类主要完成以下功能：</b>
   * <ul>
   * <li>行状态枚举类
   * </ul>
   * <p>
   * <p>
   * 
   * @version 6.0
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午09:58:39
   */
  public enum RowStatus {
    /**
     * 删除
     */
    DELETED,

    /**
     * 新增
     */
    NEW,

    /**
     * 无改变
     */
    UNCHANGED,

    /**
     * 更新
     */
    UPDATED,
  }

  /**
   * 方法功能描述：获得表体某行的某个属性的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 行
   * @param itemKey 属性的主健
   * @return 属性值
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-31 上午09:38:39
   */
  public Object getBodyValue(int row, String itemKey);

  /**
   * 方法功能描述：获得表头或表尾某个属性的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemKey 属性的主键
   * @return 属性值
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-31 上午09:33:30
   */
  public Object getHeadValue(String itemKey);

  /**
   * 方法功能描述：获得表体的行数量。
   * <p>
   * <b>参数说明</b>
   * 
   * @return 表体行数
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-20 下午10:00:18
   */
  public int getItemCount();

  /**
   * 方法功能描述：获得某个表体的行数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param tableCode 表体的编码
   * @return 某个表体的行数
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-20 下午10:01:04
   */
  public int getItemCount(String tableCode);

  /**
   * 方法功能描述：返回指定行的状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 行
   * @return 行状态
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午09:59:57
   */
  public RowStatus getRowStatus(int row);

  /**
   * 方法功能描述：设置表体某行的某个属性的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 行
   * @param itemKey 属性的主健
   * @param value 要设置的属性值
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-31 上午09:40:35
   */
  public void setBodyValue(int row, String itemKey, Object value);

  /**
   * 方法功能描述：设置表体某行的某个属性的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 行
   * @param itemKey 属性的主健
   * @param value 要设置的属性值
   * @param tableCode 要设置的属性所在的表
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-31 上午09:47:48
   */
  public void setBodyValue(int row, String itemKey, Object value,
      String tableCode);

  /**
   * 方法功能描述：设置表头或表尾某个属性的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemKey 属性的主健
   * @param value 要设置的属性值
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-31 上午09:34:21
   */
  public void setHeadValue(String itemKey, Object value);
}

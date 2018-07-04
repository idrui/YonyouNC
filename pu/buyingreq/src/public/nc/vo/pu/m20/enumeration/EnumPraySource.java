/**
 * $$文件说明$$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-26 18:32:01
 */
package nc.vo.pu.m20.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产计划订单 MPO
 * <li>主生产计划 MRP
 * <li>生产订单 MO
 * <li>库存计划订单 ICPO
 * <li>销售订单 SO
 * <li>调拨订单 M5X
 * <li>资产工单 M4B36
 * <li>自制 MANUAL
 * <li>物资备料表M4D26
 * <li>离散生产订单M55C2
 * <li>需求平衡汇总 INV9
 * <li>出口合同 5720
 * <li>资产配置申请 4A08
 * <li>维修计划 4B32
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-01-26 18:32:01
 */

public class EnumPraySource extends NCMDEnum {

  // 库存计划订单 ICPO
  public static final EnumPraySource ICPO = MDEnum.valueOf(
      EnumPraySource.class, Integer.valueOf(3));

  // 请购单 M20
  public static final EnumPraySource M20 = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(9));

  // 资产工单 M4B36
  public static final EnumPraySource M4B36 = MDEnum.valueOf(
      EnumPraySource.class, Integer.valueOf(6));

  // 物资备料表
  public static final EnumPraySource M4D26 = MDEnum.valueOf(
      EnumPraySource.class, Integer.valueOf(8));

  // 调拨订单 M5X
  public static final EnumPraySource M5X = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(5));

  // 自制 MANUAL
  public static final EnumPraySource MANUAL = MDEnum.valueOf(
      EnumPraySource.class, Integer.valueOf(7));

  // 生产订单 MO
  public static final EnumPraySource MO = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(2));

  // 生产计划订单 MPO
  public static final EnumPraySource MPO = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(0));

  // 主生产计划 MRP
  public static final EnumPraySource MPS = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(1));

  // 销售订单 SO
  public static final EnumPraySource SO = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(4));
  
  // 离散生产订单 55C2
  public static final EnumPraySource M55C2 = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(10));

  // 需求汇总平衡 INV9
  public static final EnumPraySource INV9= MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(11));
  
  // 出口合同 5720
  public static final EnumPraySource M5720 = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(12));

  // 资产配置申请 4A08
  public static final EnumPraySource M4A08 = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(13));

  // 维修计划 4B32
  public static final EnumPraySource M4B32 = MDEnum.valueOf(EnumPraySource.class,
      Integer.valueOf(14));

  /**
   * EnumPraySource 的构造子
   * 
   * @param enumvalue
   */
  public EnumPraySource(IEnumValue enumvalue) {
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

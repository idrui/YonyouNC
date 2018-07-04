/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-1 上午09:07:15
 */
package nc.vo.pu.pub.rule;

import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购精度处理的接口，vo和ui的精度处理都可以实现此接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-11-1 上午09:07:15
 */
public interface IScaleProcessor {

  /**
   * 方法功能描述：vo和ui的精度处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale
   *          - 数量金额等普通的精度处理器
   * @param totalScale
   *          - 表头合计的精度处理器
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-11-1 上午09:08:54
   */
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale);

  /**
   * 单表的精度处理
   * 
   * @param scale
   * @param totalScale
   */
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale);

}

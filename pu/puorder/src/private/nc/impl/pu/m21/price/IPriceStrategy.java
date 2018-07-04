package nc.impl.pu.m21.price;

import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询价时的价格策略
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午03:38:58
 */
public interface IPriceStrategy {

  /**
   * 方法功能描述：询价。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午03:49:38
   */
  public void queryPrice();

  /**
   * 方法功能描述：物料档案的采购组织页签的属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param puvoMap 物料档案的采购组织页签的属性（key-物料的PK，value-采购组织页签属性对象）
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午10:36:57
   */
  public void setMaterialPuVO(Map<String, MaterialPuVO> puvoMap);

  /**
   * 方法功能描述：设置价格优先策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pp
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-24 下午09:28:39
   */
  public void setPricePiority(PricePriority pp);

  /**
   * 方法功能描述：设置询价参数数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @param params 询价参数集合
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午04:02:36
   */
  public void setQueryParameter(OrderPriceQueryParam param);
}

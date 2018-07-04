package nc.impl.pu.m21.price;

import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询价策略的抽象类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午04:03:27
 */
public abstract class AbstractPriceStrategy implements IPriceStrategy {
  private OrderPriceQueryParam param;

  private PricePriority pp;

  private Map<String, MaterialPuVO> puvoMap;

  @Override
  public void setMaterialPuVO(Map<String, MaterialPuVO> puvoMap) {
    this.puvoMap = puvoMap;
  }

  @Override
  public void setPricePiority(PricePriority pp) {
    this.pp = pp;
  }

  @Override
  public void setQueryParameter(OrderPriceQueryParam param) {
    this.param = param;
  }

  protected Map<String, MaterialPuVO> getMaterialPuVO() {
    return this.puvoMap;
  }

  protected PricePriority getPricePriority() {
    return this.pp;
  }

  protected OrderPriceQueryParam getQueryParameter() {
    return this.param;
  }

}

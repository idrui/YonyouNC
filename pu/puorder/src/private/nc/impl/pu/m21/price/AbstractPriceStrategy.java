package nc.impl.pu.m21.price;

import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ�۲��Եĳ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����04:03:27
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

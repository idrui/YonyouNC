/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-22 ����12:49:28
 */
package nc.pubimpl.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽���ϵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-22 ����02:38:50
 */
public class RelationCalculateRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.relationCalculate(vos);
  }

  /**
   * ��˰���Ȼ�����˰����
   */
  private UFBoolean isTaxPricePriorToPrice(String pk_org) {
    UFBoolean flag = UFBoolean.TRUE;
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = UFBoolean.FALSE;
    }
    return flag;
  }

  private void relationCalculate(OrderVO[] orderVOs) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    RelationCalculate relationCalculate = new RelationCalculate();
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO) {
        continue;
      }
      String pk_org = orderVO.getHVO().getPk_org();
      UFBoolean isTaxPricePriorToPrice = map.get(pk_org);
      if (isTaxPricePriorToPrice == null) {
        isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);
        map.put(pk_org, isTaxPricePriorToPrice);
      }
      if (isTaxPricePriorToPrice.booleanValue()) {
        relationCalculate.calculate(orderVO, OrderItemVO.NQTORIGTAXPRICE);
      }
      else {
        relationCalculate.calculate(orderVO, OrderItemVO.NQTORIGPRICE);
      }
    }
  }
}

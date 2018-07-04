package nc.bs.pu.m27.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.sc.M61PUServices;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 为采购结算服务的一些单据对采购合同信息（号）冗余的公共规则<br>
 * 现在只服务于：采购入财务、委外入财务。
 * 采购发票、期初暂估未使用该规则
 * 
 * @since 6.0
 * @version 2011-3-7 下午09:03:01
 * @author zhaoyha
 */
public class ContranctInfoRedundanceRule<E extends AbstractBill> implements
    IRule<E> {
  public final String PK_ORDER_B = "pk_order_b";

  public final String VCTCODE = "vctcode";

  private String orderTrantypeField = null;

  private IBillType orderType = null;

  /**
   * @param orderTrantypeField 订单交易类型字段名称
   * @param orderType 委外订单或采购订单类型
   */
  public ContranctInfoRedundanceRule(String orderTrantypeField,
      IBillType orderType) {
    super();
    this.orderTrantypeField = orderTrantypeField;
    this.orderType = orderType;
  }

  @Override
  public void process(AbstractBill[] vos) {
    Set<String> orderBIdSet = this.getOrderBID(vos);
    if (CollectionUtils.isEmpty(orderBIdSet)) {
      return;
    }
    Map<String, String> orderBIdCtMap = this.getOrderCtcode(orderBIdSet);
    if (MapUtils.isEmpty(orderBIdCtMap)) {
      return;
    }
    for (AbstractBill vo : vos) {
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        String pk_order_b = (String) item.getAttributeValue(this.PK_ORDER_B);
        item.setAttributeValue(this.VCTCODE, orderBIdCtMap.get(pk_order_b));
      }
    }
  }

  private Set<String> getOrderBID(AbstractBill[] vos) {
    Set<String> orderBIdSet = new HashSet<String>();
    for (AbstractBill vo : vos) {
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        String ctcode = (String) item.getAttributeValue(this.VCTCODE);
        String pk_order_b = (String) item.getAttributeValue(this.PK_ORDER_B);
        String ordertrantype =
            (String) item.getAttributeValue(this.orderTrantypeField);
        // 已经有合同号就不再处理
        if (StringUtils.isNotBlank(ctcode)
            || StringUtils.isBlank(pk_order_b)
            || !this.orderType.getCode().equals(
                PfUtilBaseTools.getRealBilltype(ordertrantype))) {
          continue;
        }
        orderBIdSet.add(pk_order_b);
      }
    }
    return orderBIdSet;
  }

  private Map<String, String> getOrderCtcode(Set<String> orderBIdSet) {
    String[] orderBIds = orderBIdSet.toArray(new String[orderBIdSet.size()]);
    try {
      if (SCBillType.Order.getCode().equals(this.orderType.getCode())) {
        return M61PUServices.getCtCodeByBID(orderBIds);
      }
      else if (POBillType.Order.getCode().equals(this.orderType.getCode())) {
        return NCLocator.getInstance().lookup(IOrderPubQuery.class)
            .getCtCodeByBID(orderBIds);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}

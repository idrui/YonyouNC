package nc.vo.pu.m21.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款计划视图VO
 * 
 * @since 6.0
 * @version 2010-12-31 下午02:29:15
 * @author wuxla
 */

public class PayPlanViewVO extends AbstractPayPlanViewVO {

  private static final long serialVersionUID = -5551091472067601422L;

  public static AggPayPlanVO[] getAggPayPlanVO(AbstractDataView[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    List<OrderHeaderVO> heads = new ArrayList<OrderHeaderVO>();
    List<PayPlanVO> items = new ArrayList<PayPlanVO>();

    for (AbstractDataView view : views) {
      heads.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
      items.add((PayPlanVO) view.getVO(PayPlanVO.class));
    }

    BillComposite<AggPayPlanVO> bc =
        new BillComposite<AggPayPlanVO>(AggPayPlanVO.class);
    AggPayPlanVO vo = new AggPayPlanVO();
    bc.append(vo.getMetaData().getParent(),
        heads.toArray(new OrderHeaderVO[heads.size()]));
    bc.append(vo.getMetaData().getVOMeta(PayPlanVO.class),
        items.toArray(new PayPlanVO[items.size()]));
    return bc.composite();
  }

  /** 最终关闭 getter 方法 */
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFINALCLOSE);
  }

  /** 冻结 getter 方法 */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFROZEN);
  }

  /** 本币币种 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(AbstractPayPlanVO.CCURRENCYID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  /** 单据状态 getter 方法 */
  public Integer getForderstatus() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.FORDERSTATUS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(AggPayPlanVO.class);
  }

  /** 累计付款申请本币金额 getter 方法 */
  public UFDouble getNaccumpayappmny() {
    return (UFDouble) this.getAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY);
  }

  /** 预付款限额 getter 方法 */
  public UFDouble getNorgprepaylimit() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT);
  }

  /** 应付财务组织 getter 方法 */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(AbstractPayPlanVO.PK_FINANCEORG_V);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_GROUP);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** 付款计划 getter 方法 */
  public String getPk_order_payplan() {
    return (String) this.getAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG_V);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_SUPPLIER);
  }

  /** 订单编号 getter 方法 */
  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderHeaderVO.DBILLDATE, dbilldate);
  }

  /** 累计付款申请本币金额 setter 方法 */
  public void setNaccumpayappmny(UFDouble naccumpayappmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY, naccumpayappmny);
  }

  /** 累计付款申请金额 setter 方法 */
  public void setNaccumpayapporgmny(UFDouble naccumpayapporgmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPORGMNY,
        naccumpayapporgmny);
  }

  /** 累计付款本币金额 setter 方法 */
  public void setNaccumpaymny(UFDouble naccumpaymny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYMNY, naccumpaymny);
  }

  /** 累计付款金额 setter 方法 */
  public void setNaccumpayorgmny(UFDouble naccumpayorgmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYORGMNY, naccumpayorgmny);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderHeaderVO.PK_GROUP, pk_group);
  }

  /** 付款计划 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(PayPlanVO.PK_ORDER, pk_order);
  }

  /** 付款计划 setter 方法 */
  public void setPk_order_payplan(String pk_order_payplan) {
    this.setAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN, pk_order_payplan);
  }

  /** 采购组织版本信息 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG, pk_org);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 订单编号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vbillcode);
  }
}

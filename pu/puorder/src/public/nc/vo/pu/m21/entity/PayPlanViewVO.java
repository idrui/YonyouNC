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
 * ����ƻ���ͼVO
 * 
 * @since 6.0
 * @version 2010-12-31 ����02:29:15
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

  /** ���չر� getter ���� */
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFINALCLOSE);
  }

  /** ���� getter ���� */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFROZEN);
  }

  /** ���ұ��� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(AbstractPayPlanVO.CCURRENCYID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  /** ����״̬ getter ���� */
  public Integer getForderstatus() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.FORDERSTATUS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(AggPayPlanVO.class);
  }

  /** �ۼƸ������뱾�ҽ�� getter ���� */
  public UFDouble getNaccumpayappmny() {
    return (UFDouble) this.getAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY);
  }

  /** Ԥ�����޶� getter ���� */
  public UFDouble getNorgprepaylimit() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT);
  }

  /** Ӧ��������֯ getter ���� */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(AbstractPayPlanVO.PK_FINANCEORG_V);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_GROUP);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** ����ƻ� getter ���� */
  public String getPk_order_payplan() {
    return (String) this.getAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN);
  }

  /** �ɹ���֯�汾��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG_V);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_SUPPLIER);
  }

  /** ������� getter ���� */
  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderHeaderVO.DBILLDATE, dbilldate);
  }

  /** �ۼƸ������뱾�ҽ�� setter ���� */
  public void setNaccumpayappmny(UFDouble naccumpayappmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY, naccumpayappmny);
  }

  /** �ۼƸ��������� setter ���� */
  public void setNaccumpayapporgmny(UFDouble naccumpayapporgmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPORGMNY,
        naccumpayapporgmny);
  }

  /** �ۼƸ���ҽ�� setter ���� */
  public void setNaccumpaymny(UFDouble naccumpaymny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYMNY, naccumpaymny);
  }

  /** �ۼƸ����� setter ���� */
  public void setNaccumpayorgmny(UFDouble naccumpayorgmny) {
    this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYORGMNY, naccumpayorgmny);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderHeaderVO.PK_GROUP, pk_group);
  }

  /** ����ƻ� setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(PayPlanVO.PK_ORDER, pk_order);
  }

  /** ����ƻ� setter ���� */
  public void setPk_order_payplan(String pk_order_payplan) {
    this.setAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN, pk_order_payplan);
  }

  /** �ɹ���֯�汾��Ϣ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG, pk_org);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** ������� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vbillcode);
  }
}

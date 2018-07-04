package nc.vo.pu.report.view.order;

import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-9-16 下午05:00:50
 * @author wuxla
 */

public class ReceivePlanRptViewVO extends AbstractDataView {

  private static final long serialVersionUID = -4478837054046219443L;

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ReceivePlanRptViewMeta.class);
  }

  public String getPk_arrive_b() {
    return (String) this.getAttributeValue(ReceivePlanRptViewMeta.PK_ARRIVE_B);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** 到货计划子子表 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** 到货计划 getter 方法 */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_BB1);
  }

  public void setArr_crowno(String arr_crowno) {
    this.setAttributeValue(ReceivePlanRptViewMeta.ARR_CROWNO, arr_crowno);
  }

  public void setArr_dbilldate(UFDate arr_dbilldate) {
    this.setAttributeValue(ReceivePlanRptViewMeta.ARR_DBILLDATE, arr_dbilldate);
  }

  public void setArr_nnum(UFDouble arr_nnum) {
    this.setAttributeValue(ReceivePlanRptViewMeta.ARR_NNUM, arr_nnum);
  }

  public void setArr_vbillcode(String arr_vbillcode) {
    this.setAttributeValue(ReceivePlanRptViewMeta.ARR_VBILLCODE, arr_vbillcode);
  }

  public void setIc_crowno(String ic_crowno) {
    this.setAttributeValue(ReceivePlanRptViewMeta.IC_CROWNO, ic_crowno);
  }

  public void setIc_dbilldate(UFDate ic_dbilldate) {
    this.setAttributeValue(ReceivePlanRptViewMeta.IC_DBILLDATE, ic_dbilldate);
  }

  public void setIc_nnum(UFDouble ic_nnum) {
    this.setAttributeValue(ReceivePlanRptViewMeta.IC_NNUM, ic_nnum);
  }

  public void setIc_vbillcode(String ic_vbillcode) {
    this.setAttributeValue(ReceivePlanRptViewMeta.IC_VBILLCODE, ic_vbillcode);
  }

  public void setPk_arrive_b(String pk_arrive_b) {
    this.setAttributeValue(ReceivePlanRptViewMeta.PK_ARRIVE_B, pk_arrive_b);
  }

  public void setVplancode(String vplancode) {
    this.setAttributeValue(ReceivePlanRptViewMeta.VPLANCODE, vplancode);
  }

}

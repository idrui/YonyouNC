package nc.vo.pu.m23.entity;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 到货单的主子表视图VO
 * 
 * @author hanbin 2010-01-11 下午02:22:28
 */
public class ArriveViewVO extends AbstractDataView {

  private static final long serialVersionUID = 1103635999361521032L;

  public ArriveVO changeToBill() {
    ArriveVO bill = new ArriveVO();
    bill.setParent(this.getHVO());
    ArriveItemVO[] items = new ArriveItemVO[1];
    items[0] = this.getBVO();
    bill.setChildrenVO(items);
    return bill;
  }

  /** 退货 getter 方法 */
  public UFBoolean getBisback() {
    return (UFBoolean) this.getAttributeValue(ArriveHeaderVO.BISBACK);
  }

  /** 赠品 getter 方法 */
  public UFBoolean getBpresent() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENT);
  }

  public ArriveItemVO getBVO() {
    return (ArriveItemVO) this.getVO(ArriveItemVO.class);
  }

  /** 到货日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  public ArriveHeaderVO getHVO() {
    return (ArriveHeaderVO) this.getVO(ArriveHeaderVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(ArriveVO.class);
  }

  /** 累计报检主数量 getter 方法 */
  public UFDouble getNaccumchecknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMCHECKNUM);
  }

  /** 合格主数量 getter 方法 */
  public UFDouble getNelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NELIGNUM);
  }

  /** 到货主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNUM);
  }

  /** 赠品主数量 getter 方法 */
  public UFDouble getNpresentnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRESENTNUM);
  }

  /** 到货单明细 getter 方法 */
  public String getPk_arriveorder_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRIVEORDER_B);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_B);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** 到货单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  public void setBVO(ArriveItemVO item) {
    this.setVO(item);
  }

  public void setHVO(ArriveHeaderVO head) {
    this.setVO(head);
  }
}

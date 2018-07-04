package nc.vo.pu.m21.rule;

import nc.vo.ic.batch.AbstractBillItemAdapter;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-4-22 ÏÂÎç07:03:52
 * @author wuxla
 */

public class OrderBatchCodeItemAdapter extends AbstractBillItemAdapter {
  private static final long serialVersionUID = 5855308311597321221L;

  public OrderBatchCodeItemAdapter() {
    super();
  }

  public OrderBatchCodeItemAdapter(OrderItemVO vo) {
    super(vo);
  }

  @Override
  public String getBid() {
    return this.getOrderItemVO().getPk_order_b();
  }

  @Override
  public String getBillType() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getCmaterialvid() {
    return this.getOrderItemVO().getPk_material();
  }

  @Override
  public String getCrowNo() {
    return this.getOrderItemVO().getCrowno();
  }

  @Override
  public String getHid() {
    return this.getOrderItemVO().getPk_order();
  }

  @Override
  public String getTableName() {
    return PUEntity.M21_B_TABLE;
  }

  @Override
  public String getVbillcode() {
    return null;
  }

  @Override
  public void setBid(String bid) {
    //
  }

  @Override
  public void setBillType(String type) {
    //
  }

  @Override
  public void setCmaterialvid(String vid) {
    //
  }

  @Override
  public void setCrowNo(String rowno) {
    //
  }

  @Override
  public void setHid(String hid) {
    //
  }

  @Override
  public void setVbillcode(String code) {
    //
  }

  private OrderItemVO getOrderItemVO() {
    return (OrderItemVO) this.getVo();
  }

}

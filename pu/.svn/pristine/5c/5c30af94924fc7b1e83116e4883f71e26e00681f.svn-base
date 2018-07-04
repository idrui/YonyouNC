package nc.vo.pu.m21.rule;

import nc.vo.ic.batch.AbstractBillItemAdapter;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-4-26 ÉÏÎç11:05:05
 * @author wuxla
 */

public class ReceivePlanBatchCodeItemAdapter extends AbstractBillItemAdapter {

  private static final long serialVersionUID = 8900264559136517931L;

  public ReceivePlanBatchCodeItemAdapter() {
    super();
  }

  public ReceivePlanBatchCodeItemAdapter(OrderReceivePlanVO vo) {
    super(vo);
  }

  @Override
  public String getBid() {
    return this.getReveivePlanVO().getPk_order_bb1();
  }

  @Override
  public String getBillType() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getCmaterialvid() {
    return this.getReveivePlanVO().getPk_material();
  }

  @Override
  public String getCrowNo() {
    return null;
  }

  @Override
  public String getHid() {
    return this.getReveivePlanVO().getPk_order_b();
  }

  @Override
  public String getTableName() {
    return PUEntity.M21_BB1_TABLE;
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

  private OrderReceivePlanVO getReveivePlanVO() {
    return (OrderReceivePlanVO) this.getVo();
  }

}

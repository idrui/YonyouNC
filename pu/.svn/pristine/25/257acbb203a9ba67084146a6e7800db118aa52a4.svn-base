package nc.vo.pu.m24.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单单据VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 下午05:06:15
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m24.entity.PricestlHeaderVO")
public class PricestlVO extends AbstractBill {

  private static final long serialVersionUID = -198180883187512658L;

  /** 当前用户是否确认过提示信息 **/
  private boolean isUserConfirm;

  /** 提示信息 **/
  private String msg;

  public PricestlItemBVO[] getBBVO() {
    return (PricestlItemBVO[]) this.getChildren(PricestlItemBVO.class);
  }

  public PricestlItemVO[] getBVO() {
    return (PricestlItemVO[]) this.getChildren(PricestlItemVO.class);
  }

  public PricestlHeaderVO getHVO() {
    return (PricestlHeaderVO) this.getParent();
  }

  /** 当前用户是否确认过提示信息 **/
  public boolean getIsUserConfirm() {
    return this.isUserConfirm;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(PricestlVOMeta.class);
    return billMeta;
  }

  /** 提示信息 **/
  public String getMsg() {
    return this.msg;
  }

  public void setBBVO(PricestlItemBVO[] voaChildren) {
    this.setChildren(PricestlItemBVO.class, voaChildren);
  }

  public void setBVO(PricestlItemVO[] voaChildren) {
    this.setChildren(PricestlItemVO.class, voaChildren);
  }

  public void setHVO(PricestlHeaderVO voaParent) {
    this.setParent(voaParent);
  }

  /** 当前用户是否确认过提示信息 **/
  public void setIsUserConfirm(boolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  /** 提示信息 **/
  public void setMsg(String msg) {
    this.msg = msg;
  }

}

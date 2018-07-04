/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午06:59:46
 */
package nc.vo.pu.m20.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单单据VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 下午06:59:46
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m20.entity.PraybillHeaderVO")
public class PraybillVO extends AbstractBill {

  private static final long serialVersionUID = -198180883187512658L;

  /** 当前用户是否确认过提示信息 **/
  private boolean isUserConfirm;

  /** 提示信息 **/
  private String msg;

  public PraybillItemVO[] getBVO() {
    return (PraybillItemVO[]) this.getChildrenVO();
  }

  public PraybillHeaderVO getHVO() {
    return (PraybillHeaderVO) this.getParent();
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
        BillMetaFactory.getInstance().getBillMeta(PraybillVOMeta.class);
    return billMeta;
  }

  /** 提示信息 **/
  public String getMsg() {
    return this.msg;
  }

  public void setBVO(PraybillItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(PraybillHeaderVO voaParent) {
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

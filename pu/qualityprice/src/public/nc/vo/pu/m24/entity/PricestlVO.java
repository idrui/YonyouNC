package nc.vo.pu.m24.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥����VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 ����05:06:15
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m24.entity.PricestlHeaderVO")
public class PricestlVO extends AbstractBill {

  private static final long serialVersionUID = -198180883187512658L;

  /** ��ǰ�û��Ƿ�ȷ�Ϲ���ʾ��Ϣ **/
  private boolean isUserConfirm;

  /** ��ʾ��Ϣ **/
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

  /** ��ǰ�û��Ƿ�ȷ�Ϲ���ʾ��Ϣ **/
  public boolean getIsUserConfirm() {
    return this.isUserConfirm;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(PricestlVOMeta.class);
    return billMeta;
  }

  /** ��ʾ��Ϣ **/
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

  /** ��ǰ�û��Ƿ�ȷ�Ϲ���ʾ��Ϣ **/
  public void setIsUserConfirm(boolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  /** ��ʾ��Ϣ **/
  public void setMsg(String msg) {
    this.msg = msg;
  }

}

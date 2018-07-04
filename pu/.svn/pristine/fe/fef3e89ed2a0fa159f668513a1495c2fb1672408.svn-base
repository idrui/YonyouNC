/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 上午11:59:46
 */
package nc.pubitf.pu.m21.so.m30;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmf.coop.entity.AbstractCoopDataEntity;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>协同订单数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-26 上午11:59:46
 */
public class OrderCoopDataEntity extends AbstractCoopDataEntity<OrderVO> {

  private static final long serialVersionUID = 5192443914242981062L;

  private String billType;

  /**
   * OrderCoopDataEntity 的构造子
   * 
   * @param bill
   */
  public OrderCoopDataEntity(OrderVO bill) {
    super(bill);
    this.setBillType(POBillType.Order.getCode());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getBillType()
   */
  @Override
  public String getBillType() {
    return this.billType;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getCoopInnerCustomerPK()
   */
  @Override
  public String getCoopInnerCustomerPK() {
    return this.getBill().getHVO().getPk_supplier();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getCoopPkOrg()
   */
  @Override
  public String getCoopPkOrg() {
    return this.getBill().getHVO().getPk_org();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getFinancialOrgPK()
   */
  @Override
  public String getFinancialOrgPK() {
    return this.getBill().getBVO()[0].getPk_psfinanceorg();
  }

  @Override
  public String getPk_transtype() {
    return this.getBill().getHVO().getCtrantypeid();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getSrcBodyPKFieldName()
   */
  @Override
  public String getSrcBodyPKFieldName() {
    return OrderItemVO.CSOURCEBID;
  }

  @Override
  public String getSrcDocID() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getSrcHeadPKFieldName()
   */
  @Override
  public String getSrcHeadPKFieldName() {
    return OrderItemVO.CSOURCEID;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#getTransType()
   */
  @Override
  public String getTransType() {
    return this.getBill().getHVO().getVtrantypecode();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#setBillType(java.lang.String)
   */
  @Override
  public void setBillType(String billType) {
    this.billType = billType;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#setCoopInnerCustomerPK(java.lang.String)
   */
  @Override
  public void setCoopInnerCustomerPK(String coopInnerCustomerPK) {
    this.getBill().getHVO().setPk_supplier(coopInnerCustomerPK);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#setCoopPkOrg(java.lang.String)
   */
  @Override
  public void setCoopPkOrg(String coopPkOrg) {
    this.getBill().getHVO().setPk_org(coopPkOrg);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#setFinancialOrgPK(java.lang.String)
   */
  @Override
  public void setFinancialOrgPK(String financialPK) {
    String pk_psfinanceorg_v = null;
    pk_psfinanceorg_v = OrgUnitPubService.getOrgVid(financialPK);
    OrderItemVO[] itemVOs = this.getBill().getBVO();
    for (OrderItemVO itemVO : itemVOs) {
      itemVO.setPk_psfinanceorg(financialPK);
      itemVO.setPk_psfinanceorg_v(pk_psfinanceorg_v);
    }
  }

  @Override
  public void setPk_transtype(String pk_transtype) {
    this.getBill().getHVO().setCtrantypeid(pk_transtype);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.scmf.coop.entity.AbstractCoopDataEntity#setTransType(java.lang.String)
   */
  @Override
  public void setTransType(String transType) {
    this.getBill().getHVO().setVtrantypecode(transType);
  }

}

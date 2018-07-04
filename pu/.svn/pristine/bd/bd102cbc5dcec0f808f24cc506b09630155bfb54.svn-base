/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 下午07:48:48
 */
package nc.vo.pu.m422x.entity;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单聚合VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 下午07:48:48
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m422x.entity.StoreReqAppHeaderVO")
public class StoreReqAppVO extends AbstractBill {

  private static final long serialVersionUID = -3077249229044777149L;

  public StoreReqAppVO() {
    super();
  }

  public static StoreReqAppCloseVO[] getCloseVO(StoreReqAppVO[] vos) {
    return new BillToViewConvertor<StoreReqAppVO, StoreReqAppCloseVO>(
        StoreReqAppCloseVO.class).convert(vos);
  }

  public StoreReqAppItemVO[] getBVO() {
    return (StoreReqAppItemVO[]) this.getChildrenVO();
  }

  public StoreReqAppHeaderVO getHVO() {
    return (StoreReqAppHeaderVO) this.getParent();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getMetaData()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(StoreReqAppVOMeta.class);
    return billMeta;
  }

  public void setBVO(StoreReqAppItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(StoreReqAppHeaderVO voaParent) {
    this.setParent(voaParent);
  }
}

package nc.vo.pu.m23.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的聚合VO类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:34:53
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m23.entity.ArriveHeaderVO")
public class ArriveVO extends AbstractBill {

  private static final long serialVersionUID = 5129911354579472630L;

  public ArriveItemVO[] getBVO() {
    return (ArriveItemVO[]) this.getChildrenVO();
  }

  public ArriveHeaderVO getHVO() {
    return (ArriveHeaderVO) this.getParent();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(ArriveVOMeta.class);
    return billMeta;
  }

  public void setBVO(ArriveItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(ArriveHeaderVO voaParent) {
    this.setParent(voaParent);
  }
}

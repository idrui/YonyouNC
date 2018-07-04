package nc.vo.pu.m27.entity;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 结算视图VO
 * 
 * @since 6.0
 * @version 2011-5-7 下午02:37:12
 * @author wuxla
 */

public class SettleBillViewVO extends AbstractDataView {
  private static final long serialVersionUID = -36727757818458683L;

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 结算日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(SettleBillVO.class);
  }

  /** 金额 getter 方法 */
  public UFDouble getNmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NMONEY);
  }

  /** 单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NPRICE);
  }

  /** 结算数量 getter 方法 */
  public UFDouble getNsettlenum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NSETTLENUM);
  }

  /** 结算单号 **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.VBILLCODE);
  }

  /** 发票号 getter 方法 */
  public String getVinvoicecode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICECODE);
  }

  /** 库存单据号 getter 方法 */
  public String getVstockcode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKCODE);
  }
}

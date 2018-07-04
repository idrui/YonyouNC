package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 上午11:19:53
 * @author wuxla
 */

public class OrderApprovedViewVO extends AbstractDataView {

  public static class OrderApprovedViewVOMeta extends DataViewMeta {
    public OrderApprovedViewVOMeta() {
      init();
    }

    private void init() {
      // 订单主键、采购订单号、采购组织id、采购部门id、采购员id、订单日期
      // 供应商发货地址id、发票方id、付款协议id、备注,运输方式
      add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.PK_ORDER, OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_ORG,
        OrderHeaderVO.PK_DEPT, OrderHeaderVO.CEMPLOYEEID,
        OrderHeaderVO.DBILLDATE, OrderHeaderVO.PK_DELIVERADD,
        OrderHeaderVO.PK_INVCSUPLLIER, OrderHeaderVO.PK_PAYTERM,
        OrderHeaderVO.VMEMO, OrderHeaderVO.PK_TRANSPORTTYPE, OrderHeaderVO.TS
      });
    }
  }

  private static final long serialVersionUID = -6829820471510269930L;

  /** 采购员 getter 方法 */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CEMPLOYEEID);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {

    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderApprovedViewVOMeta.class);
  }

  /** 供应商发货地址 getter 方法 */
  public String getPk_deliveradd() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DELIVERADD);
  }

  /** 采购部门最新版本 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG);
  }

  /** 付款协议 getter 方法 */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_PAYTERM);
  }

  /** 运输方式 getter 方法 */
  public String getPk_transporttype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_TRANSPORTTYPE);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderHeaderVO.TS);
  }

  /** 订单编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderHeaderVO.VMEMO);
  }
}

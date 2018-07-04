package nc.pubitf.pu.m21.ic.report;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 给库存，借入备查簿查询采购订单的视图VO
 * 
 * @since 6.0
 * @version 2011-12-6 上午11:27:10
 * @author zhaoyha
 */
public class OrderViewForICBorrowMemo extends AbstractDataView {

  /**
   * 借入备查簿查询采购订单的视图VO元数据
   * 
   * @since 6.0
   * @version 2011-12-6 下午2:05:24
   * @author zhaoyha
   */
  public static class OrderViewForICBorrowMemoMeta extends DataViewMeta {

    /**
     *
     */
    public OrderViewForICBorrowMemoMeta() {
      super();
      // 先加item字段，作为主实体
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORDER, OrderItemVO.PK_ORDER_B,
        OrderItemVO.PK_ARRVSTOORG, OrderItemVO.PK_MATERIAL,
        OrderItemVO.PK_SRCMATERIAL, OrderItemVO.PK_BATCHCODE,
        OrderItemVO.VBATCHCODE, OrderItemVO.CUNITID, OrderItemVO.CASTUNITID,
        OrderItemVO.VCHANGERATE, OrderItemVO.NNUM, OrderItemVO.NASTNUM,
        OrderItemVO.CPROJECTID, OrderItemVO.CQUALITYLEVELID,
        OrderItemVO.CPRODUCTORID, OrderItemVO.VFREE1, OrderItemVO.VFREE2,
        OrderItemVO.VFREE3, OrderItemVO.VFREE4, OrderItemVO.VFREE5,
        OrderItemVO.VFREE6, OrderItemVO.VFREE7, OrderItemVO.VFREE8,
        OrderItemVO.VFREE9, OrderItemVO.VFREE10, OrderItemVO.CSOURCEBID,
        OrderItemVO.CSOURCEID, OrderItemVO.CSOURCETYPECODE,
        OrderItemVO.PK_RECVSTORDOC
      });

      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.PK_ORDER, OrderHeaderVO.PK_GROUP,
        OrderHeaderVO.CEMPLOYEEID, OrderHeaderVO.PK_SUPPLIER,
        OrderHeaderVO.TAUDITTIME, OrderHeaderVO.DBILLDATE,
        OrderHeaderVO.CTRANTYPEID, OrderHeaderVO.VTRANTYPECODE,
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_DEPT, OrderHeaderVO.PK_DEPT_V
      });

      this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);

    }
  }

  private static final long serialVersionUID = 1532240153406712535L;

  /** 采购订单单据类型 **/
  public String getBillType() {
    return POBillType.Order.getCode();
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 采购员 getter 方法 */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CEMPLOYEEID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(OrderItemVO.CQUALITYLEVELID);
  }

  /** 来源单据明细 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEBID);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCETYPECODE);
  }

  /** 订单类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CTRANTYPEID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderViewForICBorrowMemoMeta.class);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 收货库存组织最新版本 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** 批次号主键 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(OrderItemVO.PK_BATCHCODE);
  }

  /** 采购部门最新版本 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT_V);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_GROUP);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 收货仓库 getter 方法 */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** 物料信息 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SUPPLIER);
  }

  /** 审批日期 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TAUDITTIME);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** 订单编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /** 订单类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VTRANTYPECODE);
  }

}

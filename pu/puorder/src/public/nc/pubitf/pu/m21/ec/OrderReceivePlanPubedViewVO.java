package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:09:47
 * @author wuxla
 */

public class OrderReceivePlanPubedViewVO extends AbstractDataView {

  public static class OrderReceivePlanPubedViewVOMeta extends DataViewMeta {
    public OrderReceivePlanPubedViewVOMeta() {
      this.init();
    }

    private void init() {
      // 到货计划主键,到货计划号、订单主键、订单号、订单行号、订单行主键、产品主键
      // 自由辅助属性id、批次号、单位主键、发货数量、发货日期、计划
      // 到货日期、收货组织主键、收货地址主键、运输
      // 工具主键、车牌号、联系人、联系方式、备注
      // 订单ts,订单表体ts,到货计划ts,主数据量,主单位
      this.add(OrderReceivePlanVO.class, new String[] {
        OrderReceivePlanVO.PK_ORDER_BB1, OrderReceivePlanVO.VBILLCODE,
        OrderReceivePlanVO.VECBILLCODE, OrderReceivePlanVO.PK_ORDER,
        OrderReceivePlanVO.CROWNOBB1, OrderReceivePlanVO.PK_ORDER_B,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.VFREE1,
        OrderReceivePlanVO.VFREE2, OrderReceivePlanVO.VFREE3,
        OrderReceivePlanVO.VFREE4, OrderReceivePlanVO.VFREE5,
        OrderReceivePlanVO.VFREE6, OrderReceivePlanVO.VFREE7,
        OrderReceivePlanVO.VFREE8, OrderReceivePlanVO.VFREE9,
        OrderReceivePlanVO.VFREE10, OrderReceivePlanVO.VBATCHCODE,
        OrderReceivePlanVO.CASTUNITID, OrderReceivePlanVO.NASTNUM,
        OrderReceivePlanVO.DSENDDATE, OrderReceivePlanVO.DPLANARRVDATE,
        OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.VCHANGERATE,
        OrderReceivePlanVO.VQTUNITRATE, OrderReceivePlanVO.PK_RECEIVEADDRESS,
        OrderReceivePlanVO.VEHICLETYPE, OrderReceivePlanVO.VEHICLELICENSE,
        OrderReceivePlanVO.VLINKPSN, OrderReceivePlanVO.VLINKTYPE,
        OrderReceivePlanVO.VMEMO, OrderReceivePlanVO.TS,
        OrderReceivePlanVO.PK_ORG, OrderReceivePlanVO.PK_ARRVSTOORG_V,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.PK_ORG_V,
        OrderReceivePlanVO.PK_SRCMATERIAL, OrderReceivePlanVO.NNUM,
        OrderReceivePlanVO.CUNITID, OrderReceivePlanVO.TS
      });
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.TS, OrderItemVO.CROWNO, OrderItemVO.CPRODUCTORID,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_REQDEPT_V, OrderItemVO.PK_REQDEPT,
        OrderItemVO.PK_RECVSTORDOC
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.TS, OrderHeaderVO.PK_DEPT_V, OrderHeaderVO.PK_DEPT
      });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER_B,
          OrderItemVO.class, OrderItemVO.PK_ORDER_B);
      // meta.add(OrderHeaderVO.class, new String[] {
      // OrderHeaderVO.DBILLDATE
      // });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = -7137164073277805712L;

  private UFDateTime orderBTs;

  private UFDateTime orderTs;

  // 采购部门
  private String pk_dept;

  // 采购部门
  private String pk_dept_v;

  // 结算财务组织
  private String pk_psfinanceorg;

  // 结算财务组织
  private String pk_psfinanceorg_v;

  // 收货仓库
  private String pk_recvstordoc;

  // 需求部门
  private String pk_reqdept;

  // 需求部门
  private String pk_reqdept_v;

  private String vordercode;

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CASTUNITID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 订单行号 getter 方法 */
  public String getCrownobb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CROWNOBB1);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CUNITID);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DPLANARRVDATE);
  }

  /**
   * 获取发货日期
   * 
   * @return 发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DSENDDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderReceivePlanPubedViewVOMeta.class);
  }

  /** 发货数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NNUM);
  }

  /** 订单表体ts getter 方法 */
  public UFDateTime getOrderBTs() {
    return this.orderBTs;
  }

  /** 到货计划ts getter 方法 */
  public UFDateTime getOrderReceivePlanTs() {
    return (UFDateTime) this.getAttributeValue(OrderReceivePlanVO.TS);
  }

  /** 订单ts getter 方法 */
  public UFDateTime getOrderTs() {
    return this.orderTs;
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG);
  }

  /** 收货库存组织版本信息 getter 方法 */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG_V);
  }

  /**
   * 采购部门最新信息
   * 
   * @return
   */
  public String getPk_dept() {
    return this.pk_dept;
  }

  /**
   * 采购部门
   * 
   * @return
   */
  public String getPk_dept_v() {
    return this.pk_dept_v;
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_MATERIAL);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** 到货计划子子表 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** 到货计划 getter 方法 */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_BB1);
  }

  /** 采购组织原始信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG_V);
  }

  /**
   * 结算财务组织最新信息
   * 
   * @return
   */
  public String getPk_psfinanceorg() {
    return this.pk_psfinanceorg;
  }

  /**
   * 结算财务组织
   * 
   * @return
   */
  public String getPk_psfinanceorg_v() {
    return this.pk_psfinanceorg_v;
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS);
  }

  /**
   * 收货仓库
   * 
   * @return
   */
  public String getPk_recvstordoc() {
    return this.pk_recvstordoc;
  }

  /**
   * 需求部门
   * 
   * @return
   */
  public String getPk_reqdept() {
    return this.pk_reqdept;
  }

  /**
   * 需求部门最新信息
   * 
   * @return
   */
  public String getPk_reqdept_v() {
    return this.pk_reqdept_v;
  }

  /** 物料信息 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_SRCMATERIAL);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderReceivePlanVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBATCHCODE);
  }

  /** 到货计划号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VCHANGERATE);
  }

  /**
   * 获取EC发货单号
   * 
   * @return EC发货单号
   */
  public String getVecbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VECBILLCODE);
  }

  /**
   * 获取车牌号
   * 
   * @return 车牌号
   */
  public String getVehiclelicense() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLELICENSE);
  }

  /**
   * 获取运输工具
   * 
   * @return 运输工具
   */
  public String getVehicletype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLETYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE9);
  }

  /**
   * 获取联系人
   * 
   * @return 联系人
   */
  public String getVlinkpsn() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKPSN);
  }

  /**
   * 获取联系方式
   * 
   * @return 联系方式
   */
  public String getVlinktype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKTYPE);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VMEMO);
  }

  /**
   * 订单号
   * 
   * @return
   */
  public String getVordercode() {
    return this.vordercode;
  }

  /** 报价单位换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VQTUNITRATE);
  }

  public void setOrderBTs(UFDateTime orderBTs) {
    this.orderBTs = orderBTs;
  }

  public void setOrderTs(UFDateTime orderTs) {
    this.orderTs = orderTs;
  }

  /**
   * 采购部门最新信息
   * 
   * @param pk_dept
   */
  public void setPk_dept(String pk_dept) {
    this.pk_dept = pk_dept;
  }

  /**
   * 采购部门最新信息
   * 
   * @param pk_dept_v
   */
  public void setPk_dept_v(String pk_dept_v) {
    this.pk_dept_v = pk_dept_v;
  }

  /** 物料版本信息 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderReceivePlanVO.PK_MATERIAL, pk_material);
  }

  /**
   * 结算财务组织最新信息
   * 
   * @param pk_psfinanceorg
   */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.pk_psfinanceorg = pk_psfinanceorg;
  }

  /**
   * 结算财务组织
   * 
   * @param pk_psfinanceorg_v
   */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.pk_psfinanceorg_v = pk_psfinanceorg_v;
  }

  /**
   * 收货仓库
   * 
   * @param pk_recvstordoc
   */
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.pk_recvstordoc = pk_recvstordoc;
  }

  /**
   * 需求部门
   * 
   * @param pk_reqdept
   */
  public void setPk_reqdept(String pk_reqdept) {
    this.pk_reqdept = pk_reqdept;
  }

  /**
   * 需求部门最新信息
   * 
   * @param pk_reqdept_v
   */
  public void setPk_reqdept_v(String pk_reqdept_v) {
    this.pk_reqdept_v = pk_reqdept_v;
  }

  public void setVordercode(String vordercode) {
    this.vordercode = vordercode;
  }
}

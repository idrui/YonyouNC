package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:05:55
 * @author wuxla
 */

public class OrderReceivePlanViewECVO extends AbstractDataView {
  public static class OrderReceivePlanViewECVOMeta extends DataViewMeta {
    public OrderReceivePlanViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 到货计划主键,到货计划号、订单主键、订单号、订单行号、订单行主键、产品主键
      // 自由辅助属性id、批次号、单位主键、发货数量、发货日期、计划
      // 到货日期、收货组织主键、收货地址主键、运输
      // 工具主键、车牌号、联系人、联系方式、备注
      // 订单ts,订单表体ts,到货计划ts
      // 修改成订单表体信息
      // 订单主键、订单号、订单行号、订单行主键、批次号、产品主键 数量、累计到货计划主数量,收货组织主键、收货地址主键
      // mengjian by 20150124 新增：结算财务组织，收货仓库，需求部门
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORDER, OrderItemVO.CROWNO, OrderItemVO.PK_ORDER_B,
        OrderItemVO.PK_MATERIAL, OrderItemVO.PK_ARRVSTOORG, OrderItemVO.VFREE1,
        OrderItemVO.VFREE2, OrderItemVO.VFREE3, OrderItemVO.VFREE4,
        OrderItemVO.VFREE5, OrderItemVO.VFREE6, OrderItemVO.VFREE7,
        OrderItemVO.VFREE8, OrderItemVO.VFREE9, OrderItemVO.VFREE10,
        OrderItemVO.VBATCHCODE, OrderItemVO.CUNITID, OrderItemVO.CASTUNITID,
        OrderItemVO.NNUM, OrderItemVO.NACCUMRPNUM, OrderItemVO.PK_ARRVSTOORG,
        OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.TS, OrderItemVO.PK_ORG,
        OrderItemVO.VCHANGERATE, OrderItemVO.CPRODUCTORID,
        OrderItemVO.CPROJECTID, OrderItemVO.PK_ARRVSTOORG_V,
        OrderItemVO.PK_ORG_V, OrderItemVO.PK_SRCMATERIAL,
        OrderItemVO.VQTUNITRATE, OrderItemVO.CQTUNITID, OrderItemVO.NASTNUM,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_REQDEPT_V, OrderItemVO.PK_REQDEPT,
        OrderItemVO.PK_RECVSTORDOC
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.TS, OrderHeaderVO.PK_DEPT_V, OrderHeaderVO.PK_DEPT
      });
      this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = -131675506592535226L;

  private UFDateTime orderTs;

  private String vordercode;

  /** 辅单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** 订单行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CROWNO);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderReceivePlanViewECVOMeta.class);
  }

  /** 累计到货计划主数量 getter 方法 */
  public UFDouble getNaccumrpnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMRPNUM);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 订单表体ts getter 方法 */
  public UFDateTime getOrderBTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** 订单ts getter 方法 */
  public UFDateTime getOrderTs() {
    return this.orderTs;
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** 收货库存组织版本信息 getter 方法 */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** 采购部门版本信息 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT_V);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** 订单表体主键 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG_V);
  }

  /** 财务组织 getter 方法 */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** 财务组织版本信息 getter 方法 */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG_V);
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEADDRESS);
  }

  /** 收货仓库 getter 方法 */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** 需求部门 getter 方法 */
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT);
  }

  /** 需求部门版本信息 getter 方法 */
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT_V);
  }

  /** 物料信息 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
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

  /**
   * 订单号
   * 
   * @return
   */
  public String getVordercode() {
    return this.vordercode;
  }

  /** 报价换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }

  public void setOrderTs(UFDateTime orderTs) {
    this.orderTs = orderTs;
  }

  public void setVordercode(String vordercode) {
    this.vordercode = vordercode;
  }

}

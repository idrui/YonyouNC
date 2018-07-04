package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 上午11:43:05
 * @author wuxla
 */

public class OrderReceivePlanECVO extends AbstractDataView {

  public static class OrderReceivePlanECVOMeta extends DataViewMeta {
    public OrderReceivePlanECVOMeta() {
      this.init();
    }

    private void init() {
      // 订单子表、到货计划号、物料id、自由辅助属性id、批次号、
      // 计划到货日期、收货库存组织id、收货地址id、发货地址id、
      // 发货时间、运输工具id、车牌号、联系人名称、联系方式、备注
      // 单位、数量、主单位、主数量、报价单位、报价数量、订单主键
      this.add(OrderReceivePlanVO.class, new String[] {
        OrderReceivePlanVO.PK_ORDER_B, OrderReceivePlanVO.VBILLCODE,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.VFREE1,
        OrderReceivePlanVO.VFREE2, OrderReceivePlanVO.VFREE3,
        OrderReceivePlanVO.VFREE4, OrderReceivePlanVO.VFREE5,
        OrderReceivePlanVO.VFREE6, OrderReceivePlanVO.VFREE7,
        OrderReceivePlanVO.VFREE8, OrderReceivePlanVO.VFREE9,
        OrderReceivePlanVO.VFREE10, OrderReceivePlanVO.VBATCHCODE,
        OrderReceivePlanVO.CASTUNITID, OrderReceivePlanVO.DPLANARRVDATE,
        OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.PK_RECEIVEADDRESS,
        OrderReceivePlanVO.DSENDDATE, OrderReceivePlanVO.VEHICLETYPE,
        OrderReceivePlanVO.VEHICLELICENSE, OrderReceivePlanVO.VLINKPSN,
        OrderReceivePlanVO.VLINKTYPE, OrderReceivePlanVO.VMEMO,
        OrderReceivePlanVO.VCHANGERATE, OrderReceivePlanVO.CQTUNITID,
        OrderReceivePlanVO.CUNITID, OrderReceivePlanVO.NASTNUM,
        OrderReceivePlanVO.VQTUNITRATE, OrderReceivePlanVO.NQTUNITNUM,
        OrderReceivePlanVO.NNUM, OrderReceivePlanVO.PK_ORDER
      });
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.FISACTIVE,
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.BFINALCLOSE
      });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER_B,
          OrderItemVO.class, OrderItemVO.PK_ORDER_B);
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = 5140784264143293098L;

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CASTUNITID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CQTUNITID);
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
        OrderReceivePlanECVOMeta.class);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NNUM);
  }

  /** 报价数量 getter 方法 */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NQTUNITNUM);
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_MATERIAL);
  }

  /** 订单主键 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** 订单子表pk getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS);
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

  /** 报价换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VQTUNITRATE);
  }

}

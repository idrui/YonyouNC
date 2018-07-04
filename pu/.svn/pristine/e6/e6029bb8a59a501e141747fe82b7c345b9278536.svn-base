package nc.vo.pu.report.queryinfo.praybill;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.enumeration.PrayBillGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

/**
 * 请购单查询自由报表查询参数类
 * (1)订单执行时，需要隐藏的字段：
 * 供应商 ， 对应来源订单表头供应商
 * 订单日期 ， 对应来源订单表头订单日期
 * 订单号 ， 对应来源订单表头订单号
 * 计划到货日期 对应来源订单表体计划到货日期
 * 到货日期 对应到货单表头到货日期
 * 入库日期 对应入库单表体入库日期。
 * (2)按照物料汇总时，需要隐藏的字段：
 * 请购单号 对应请购单表头请购单号
 * 库存组织 对应请购单的主组织
 * 计划员 对应请购单表头的计划员
 * 需求日期 对应请购单表体需求日期
 * 建议订货日期 对应请购单建议订货日期
 * 建议供应商
 * 采购组织
 * 供应商 对应来源订单表头供应商
 * 订单日期 对应来源订单表头订单日期
 * 订单号 对应来源订单表头订单号
 * 入库日期 对应入库单表体入库日期。
 * 
 * @since 6.0
 * @version 2011-2-22 上午09:59:55
 * @author liuchx
 */

public class PrayBillQryInfoPara extends PuQueryInfoPara {

  public static final String CMATERIALOID = PUEntity.M20_B_TABLE + "." + "pk_srcmaterial.code";

  public static final String CMATERIALOID_NAME = PUEntity.M20_B_TABLE + "." + "pk_srcmaterial.name";

  public static final String mainOrg = PUEntity.M20_H_TABLE + "."
      + PraybillHeaderVO.PK_ORG;

  // 查询模板 汇总口径
  public static final String QUERY_GROUP_KEY = "grouptype";

  // 元数据路径
  public static final String SRCMATERIAL = "pk_praybill_b."
      + PraybillItemVO.PK_SRCMATERIAL;

  private static final long serialVersionUID = 4553061134405425021L;

  private String bsc;

  public String getBsc() {
    return this.bsc;
  }

  /**
   * 需要隐藏的字段
   */
  @Override
  public String[] getHideKeys() {
    Integer groupType = Integer.valueOf(this.getGroupcondtion());
    /** 物料 **/
    if (PrayBillGroupEnum.MAR.value().equals(groupType)) {
      // (2)按照物料汇总时，需要隐藏的字段：
      // * 请购单号 对应请购单表头请购单号
      // * 库存组织 对应请购单的主组织
      // * 计划员 对应请购单表头的计划员
      // * 需求日期 对应请购单表体需求日期
      // * 建议订货日期 对应请购单建议订货日期
      // * 建议供应商
      // * 采购组织
      // * 供应商 对应来源订单表头供应商
      // * 订单日期 对应来源订单表头订单日期
      // * 订单号 对应来源订单表头订单号
      // * 入库日期 对应入库单表体入库日期。
      return new String[] {

        PraybillHeaderVO.VBILLCODE,// 单据号。
        "this.pk_org_v.name", "this.pk_planpsn.name", PraybillItemVO.DREQDATE,
        PraybillItemVO.DSUGGESTDATE, PraybillItemVO.PK_SUGGESTSUPPLIER,
        "this.pk_purchaseorg_v.name", "this.order_pk_supplier.name",
        "order_dbilldate", "order_vbillcode", "arrive_dbilldate",
        OrderItemVO.DPLANARRVDATE, "PURIN_DBILLDATE"
      };
    }
    // 请购单执行汇总
    if (PrayBillGroupEnum.EXEGROUP.value().equals(groupType)) {
      return new String[] {
        // * (1)订单执行时，需要隐藏的字段：
        // * 供应商 ， 对应来源订单表头供应商
        // * 订单日期 ， 对应来源订单表头订单日期
        // * 订单号 ， 对应来源订单表头订单号
        // * 计划到货日期 对应来源订单表体计划到货日期
        // * 到货日期 对应到货单表头到货日期
        // * 入库日期 对应入库单表体入库日期。

        "order_vbillcode",// 订单号
        "this.order_pk_supplier.name", "order_dbilldate", "order_vbillcode",
        "arrive_dbilldate", OrderItemVO.DPLANARRVDATE, "PURIN_DBILLDATE"

      };
    }
    /** 明细 **/
    return new String[] {};
  }

  public void setBsc(String bsc) {
    this.bsc = bsc;
  }

}

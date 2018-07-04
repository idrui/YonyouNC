package nc.vo.pu.report.view.order;

import java.sql.Types;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * @since 6.0
 * @version 2011-9-14 下午07:37:05
 * @author wuxla
 */

public class ReceivePlanRptViewMeta extends DataViewMeta {
  public static final String ARR_CROWNO = "arr_crowno";

  public static final String ARR_DBILLDATE = "arr_dbilldate";

  public static final String ARR_NNUM = "arr_nnum";

  public static final String ARR_VBILLCODE = "arr_vbillcode";

  /**
   * 行号
   */
  public static final String[] B21ITEMS = new String[] {
    OrderItemVO.CROWNO
  };

  /**
   * 到货计划号
   * 物料编码
   * 物料名称
   * 规格
   * 型号
   * 自由辅助属性
   * 批次号
   * 单位
   * 数量
   * 主单位
   * 主数量
   * 计划到货日期
   * 收货库存组织
   * 收货仓库
   * 主键等
   */
  public static final String[] BB2ITEMS = new String[] {
    OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.PK_SRCMATERIAL,
    OrderReceivePlanVO.VFREE1, OrderReceivePlanVO.VFREE2,
    OrderReceivePlanVO.VFREE3, OrderReceivePlanVO.VFREE4,
    OrderReceivePlanVO.VFREE5, OrderReceivePlanVO.VFREE6,
    OrderReceivePlanVO.VFREE7, OrderReceivePlanVO.VFREE8,
    OrderReceivePlanVO.VFREE9, OrderReceivePlanVO.VFREE10,
    OrderReceivePlanVO.VBATCHCODE, OrderReceivePlanVO.CASTUNITID,
    OrderReceivePlanVO.NASTNUM, OrderReceivePlanVO.CUNITID,
    OrderReceivePlanVO.NNUM, OrderReceivePlanVO.DPLANARRVDATE,
    OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.PK_ARRVSTOORG_V,
    OrderReceivePlanVO.PK_RECVSTORDOC, OrderReceivePlanVO.PK_ORDER,
    OrderReceivePlanVO.PK_ORDER_B, OrderReceivePlanVO.PK_ORDER_BB1
  };

  /**
   * 采购组织、 供应商、 订单日期 、订单编号、 对方订单号 、 采购员、 采购部门
   */
  public static final String[] H21ITEMS = new String[] {
    OrderHeaderVO.PK_ORG, OrderHeaderVO.PK_ORG_V, OrderHeaderVO.PK_SUPPLIER,
    OrderHeaderVO.DBILLDATE, OrderHeaderVO.VBILLCODE,
    OrderHeaderVO.VCOOPORDERCODE, OrderHeaderVO.CEMPLOYEEID,
    OrderHeaderVO.PK_DEPT, OrderHeaderVO.PK_DEPT_V
  };

  public static final String IC_CROWNO = "ic_crowno";

  public static final String IC_DBILLDATE = "ic_dbilldate";

  public static final String IC_NNUM = "ic_nnum";

  public static final String IC_VBILLCODE = "ic_vbillcode";

  public static final String PK_ARRIVE_B = "pk_arrive_b";

  /**
   * 临时表名
   */
  public static final String RPRPTTABLE = "t_pu_rprpt";

  public static final String VPLANCODE = "vplancode";

  private RptExtAttribute[] extattrs;

  public ReceivePlanRptViewMeta() {
    this.init();
  }

  public JavaType[] getJavaType() {
    JavaType[] javaTypes = new JavaType[this.extattrs.length];
    for (int i = 0; i < this.extattrs.length; ++i) {
      javaTypes[i] = this.extattrs[i].getJavaType();
    }
    return javaTypes;
  }

  private void addExtAttribute() {
    for (RptExtAttribute attr : this.extattrs) {
      Attribute attribute = new Attribute(attr.getName(), null, null);
      attribute.setJavaType(attr.getJavaType());
      attribute.setCustom(false);
      attribute.setStatic(false);
      attribute.setPersistence(false);
      attribute.setSerializable(true);
      this.add(attribute);
    }
  }

  private void init() {
    this.add(OrderReceivePlanVO.class, ReceivePlanRptViewMeta.BB2ITEMS);
    this.add(OrderItemVO.class, ReceivePlanRptViewMeta.B21ITEMS);
    this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER_B,
        OrderItemVO.class, OrderItemVO.PK_ORDER_B);

    this.add(OrderHeaderVO.class, ReceivePlanRptViewMeta.H21ITEMS);
    this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
        OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);

    this.initExtAttrs();

    this.addExtAttribute();
  }

  private void initExtAttrs() {
    // 到货单号
    // 到货日期
    // 到货单行号
    // 到货主数量
    // 入库单号
    // 入库日期
    // 入库单行号
    // 入库主数量
    this.extattrs = new RptExtAttribute[10];
    this.extattrs[0] =
        new RptExtAttribute(ReceivePlanRptViewMeta.VPLANCODE, Types.VARCHAR,
            JavaType.String);
    this.extattrs[1] =
        new RptExtAttribute(ReceivePlanRptViewMeta.PK_ARRIVE_B, Types.VARCHAR,
            JavaType.String);
    this.extattrs[2] =
        new RptExtAttribute(ReceivePlanRptViewMeta.ARR_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[3] =
        new RptExtAttribute(ReceivePlanRptViewMeta.ARR_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[4] =
        new RptExtAttribute(ReceivePlanRptViewMeta.ARR_CROWNO, Types.VARCHAR,
            JavaType.String);
    this.extattrs[5] =
        new RptExtAttribute(ReceivePlanRptViewMeta.ARR_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[6] =
        new RptExtAttribute(ReceivePlanRptViewMeta.IC_VBILLCODE, Types.VARCHAR,
            JavaType.String);
    this.extattrs[7] =
        new RptExtAttribute(ReceivePlanRptViewMeta.IC_DBILLDATE, Types.VARCHAR,
            JavaType.UFDate);
    this.extattrs[8] =
        new RptExtAttribute(ReceivePlanRptViewMeta.IC_CROWNO, Types.VARCHAR,
            JavaType.String);
    this.extattrs[9] =
        new RptExtAttribute(ReceivePlanRptViewMeta.IC_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
  }
}

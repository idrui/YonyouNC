/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 上午10:39:17
 */
package nc.vo.pu.m21.pub;

import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.FromWhereParseUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对于订单查询条件的处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 上午10:39:17
 */
public class QueryCondManager {

  private static QueryCondManager instance = new QueryCondManager();

  private QueryCondManager() {
    // 私有
  }

  public static QueryCondManager getInstance() {
    return QueryCondManager.instance;
  }

  /**
   * 方法功能描述：到货单查询的From部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromPart
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-8 下午04:51:43
   */
  public String getFromPartFor23(String fromPart) {
    return this.getFromPartFor45(fromPart);
  }

  /**
   * 方法功能描述：采购发票查询的From部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromPart
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-8 下午04:52:25
   */
  public String getFromPartFor25(String fromPart) {
    FromWhereParseUtil utils = new FromWhereParseUtil(fromPart);
    String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);

    if (null == poOrderB) {
      return " inner join po_order_b po_order_b "
          + " on po_order.pk_order = po_order_b.pk_order ";
    }

    return null;
  }

  /**
   * 方法功能描述：销售订单参照协同采购订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromPart
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-29 下午01:35:13
   */
  public String getFromPartFor30(String fromPart) {
    FromWhereParseUtil utils = new FromWhereParseUtil(fromPart);
    String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);

    if (null == poOrderB) {
      return " inner join po_order_b po_order_b "
          + " on po_order.pk_order = po_order_b.pk_order ";
    }

    return null;
  }

  /**
   * 方法功能描述：入库单查询的From部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromPart
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-8 下午04:43:46
   */
  public String getFromPartFor45(String fromPart) {
    // 表的别名
    FromWhereParseUtil utils = new FromWhereParseUtil(fromPart);
    String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
    String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
    // 在途状态
    String poOrderBB = utils.getTableAlias(PUEntity.M21_BB_TABLE);
    String potrantype = utils.getTableAlias(PUEntity.M21_TRANTYPE_TABLE);
    String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);

    StringBuilder sb = new StringBuilder();

    if (null == poOrderB) {
      sb.append(" inner join po_order_b po_order_b "
          + " on po_order.pk_order=po_order_b.pk_order ");
    }

    poOrderB =
        QueryUtil.getInstance().getTableName(PUEntity.M21_B_TABLE, poOrderB);

    if (null == potrantype) {
      sb.append(" inner join po_potrantype po_potrantype "
          + " on po_order.vtrantypecode=po_potrantype.vtrantypecode ");
    }

    if (null == bdMaterial) {
      sb.append(" inner join bd_material bd_material on " + poOrderB
          + ".pk_material=bd_material.pk_material ");
    }

    if (null == poOrderBB1) {
      sb.append(" left join po_order_bb1 po_order_bb1 on " + poOrderB
          + ".pk_order_b=po_order_bb1.pk_order_b ");
    }

    if (null == poOrderBB) {
      sb.append(" inner join po_order_bb po_order_bb on " + poOrderB
          + ".pk_order_b=po_order_bb.pk_order_b ");
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;

  }

  /**
   * 运输单查询
   * 
   * @param fromPart查询对话框拼接的from部分
   * @return
   */
  public String getFromPartFor4804(String fromPart) {
    // 表的别名
    FromWhereParseUtil utils = new FromWhereParseUtil(fromPart);
    String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
    String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
    // 在途状态
    String poOrderBB = utils.getTableAlias(PUEntity.M21_BB_TABLE);
    String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);

    StringBuilder sb = new StringBuilder();

    if (null == poOrderB) {
      sb.append(" inner join po_order_b po_order_b "
          + " on po_order.pk_order=po_order_b.pk_order ");
    }

    poOrderB =
        QueryUtil.getInstance().getTableName(PUEntity.M21_B_TABLE, poOrderB);

    if (null == bdMaterial) {
      sb.append(" inner join bd_material bd_material on " + poOrderB
          + ".pk_material=bd_material.pk_material ");
    }

    if (null == poOrderBB1) {
      sb.append(" left join po_order_bb1 po_order_bb1 on " + poOrderB
          + ".pk_order_b=po_order_bb1.pk_order_b ");
    }

    if (null == poOrderBB) {
      sb.append(" inner join po_order_bb po_order_bb on " + poOrderB
          + ".pk_order_b=po_order_bb.pk_order_b ");
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * 方法功能描述：期初暂估单查询的from部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromPart
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-13 下午02:27:13
   */
  public String getFromPartFor4T(String fromPart) {
    // 表的别名
    FromWhereParseUtil utils = new FromWhereParseUtil(fromPart);
    String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
    String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
    String poOrderBB = utils.getTableAlias(PUEntity.M21_BB_TABLE);
    String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);
    String bdStordoc = utils.getTableAlias(PUEntity.STORDOC_TABLE);
    // 在途状态
    // String potrantype = utils.getTableAlias(PUEntity.M21_TRANTYPE_TABLE);
    StringBuilder sb = new StringBuilder();
    // if (null == potrantype) {
    // sb
    // .append(" inner join po_potrantype po_potrantype
    // on po_order.vtrantypecode=po_potrantype.vtrantypecode ");
    // }
    if (null == poOrderB) {
      sb.append(" inner join po_order_b po_order_b on "
          + " po_order.pk_order = po_order_b.pk_order ");
    }

    poOrderB =
        QueryUtil.getInstance().getTableName(PUEntity.M21_B_TABLE, poOrderB);

    if (null == bdMaterial) {
      sb.append(" inner join bd_material on " + poOrderB
          + ".pk_material = bd_material.pk_material ");
    }

    if (null == bdStordoc) {
      sb.append(" left outer join bd_stordoc on " + poOrderB
          + ".pk_recvstordoc = bd_stordoc.pk_stordoc ");
    }

    if (null == poOrderBB1) {
      sb.append(" left join po_order_bb1 on " + poOrderB
          + ".pk_order_b = po_order_bb1.pk_order_b ");
    }

    if (null == poOrderBB) {
      sb.append(" inner join po_order_bb po_order_bb on " + poOrderB
          + ".pk_order_b = po_order_bb.pk_order_b ");
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * 方法功能描述：为到货单查询的公共部分。
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param potrantype
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-29 下午06:39:52
   */
  public String getPubCondFor23(String poOrderB, String poOrderBB,
      String potrantype) {
    StringBuilder sb = new StringBuilder();

    // 未到货关闭
    sb.append(poOrderB + ".barriveclose='N' ");

    sb.append(" and ");

    // 交易类型需要有到货环节
    sb.append(potrantype + ".barrive= 'Y' ");

    sb.append(" and ");
    sb.append(poOrderBB);
    sb.append(".");
    sb.append(StatusOnWayItemVO.FONWAYSTATUS);
    sb.append(" = ");
    sb.append(OnwayStatus.STATUS_ARRIVE.toInteger());

    return sb.toString();
  }

  /**
   * 方法功能描述：为入库单(拉式)和到货查询的公共where条件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param potrantype
   * @param bdMaterial
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-29 下午06:40:34
   */
  public String getPubCondFor45_23(String po_order, String poOrderB,
      String poOrderBB, String bdMaterial) {

    SqlBuilder sb = new SqlBuilder();
    sb.append(" " + po_order + " .dr=0 ");
    sb.append(" and ");

    sb.append(poOrderB + ".dr=0 ");
    sb.append(" and " + poOrderBB + ".dr=0 ");
    // 最新版
    sb.append(" and " + po_order + ".bislatest = 'Y' ");
    // 非冻结
    sb.append(" and po_order.bfrozen = 'N' ");

    sb.append(" and ");

    // sb.append("(");
    // 交易类型不需要输出,则订单为审核态
    // sb.append(potrantype + ".boutput = 'N' ");
    // sb.append(" and po_order.forderstatus= 3 ");
    sb.append(" po_order.forderstatus = 3 ");
    // sb.append(" or ");
    // // 或交易类型需要输出,则订单为输出状态
    // sb.append(potrantype + ".boutput = 'Y' ");
    // sb.append("  and po_order.forderstatus= 5 ");
    // sb.append(" ) ");

    // sb.append(" and ");

    // sb.append(" ( ");
    // // 交易类型不需要确认,则订单行为自由态
    // sb.append(potrantype + ".bconfirm = 'N' ");
    // sb.append(" and ");
    // sb.append(poOrderB + ".forderrowstatus= 0 ");
    // sb.append(" or ");
    // // 或交易类型需要确认,则订单为确认态
    // sb.append(potrantype + ".bconfirm = 'Y' ");
    // sb.append(" and ");
    // sb.append(poOrderB + ".forderrowstatus= 1 ");
    // sb.append(") ");

    // sb.append(" and ");
    // sb.append(poOrderBB);
    // sb.append(".");
    // sb.append(StatusOnWayItemVO.NONWAYNUM);
    // sb.append(" > 0 ");

    // 非劳务非折扣
    sb.append(" and ");
    sb.append(bdMaterial + ".fee='N' ");
    sb.append(" and ");
    sb.append(bdMaterial + ".discountflag='N' ");

    return sb.toString();
  }

  public String getPubCondFor45_23Pull(String poOrderBB) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" and ");
    sb.append(poOrderBB);
    sb.append(".");
    sb.append(StatusOnWayItemVO.NONWAYNUM);
    sb.append(" > 0 ");
    return sb.toString();

  }

  /**
   * 方法功能描述：为入库单查询的公共部分。
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-29 下午06:25:48
   */
  public String getPubCondFor45_4T(String poOrderB, String poOrderBB) {
    // 未入库关闭
    StringBuilder sb = new StringBuilder();

    sb.append(poOrderB + ".bstockclose='N' ");

    sb.append(" and ");
    sb.append(poOrderBB);
    sb.append(".");
    sb.append(StatusOnWayItemVO.FONWAYSTATUS);
    sb.append(" = ");
    sb.append(OnwayStatus.STATUS_STORE.toInteger());

    return sb.toString();
  }

  /**
   * 方法功能描述：为期初暂估单提供查询的where部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param bdMaterial
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-13 下午02:30:12
   */
  public String getPubCondFor4T(String poOrderB) {
    SqlBuilder sb = new SqlBuilder();
    // sb.append(" po_order.dr = 0 ");
    // sb.append(" and ");
    //
    // sb.append(poOrderB + ".dr=0 ");
    // 最新版
    // sb.append(" and po_order.bislatest = 'Y' ");

    // sb.append(" and ");

    // 交易类型不需要输出,则订单为审核态
    // sb.append(" po_order.forderstatus = 3 ");

    // sb.append(" and ");
    // 表体激活
    sb.append(poOrderB + ".fisactive = " + EnumActive.ACTIVE.value());
    sb.append(" and ");
    // 未入库关闭
    // sb.append(poOrderB + ".bstockclose = 'N' ");
    // sb.append(" and ");
    // 非赠品
    sb.append(poOrderB + ".blargess = 'N' ");

    // 资产仓不进行暂估和结算
    // sb.append(" and ");
    // sb.append(" coalesce(" + bdStordoc + ".iscapitalstor,'N') = 'N' ");

    // 非劳务非折扣
    // sb.append(" and ");
    // sb.append(bdMaterial + ".fee = 'N' ");
    // sb.append(" and ");
    // sb.append(bdMaterial + ".discountflag = 'N' ");

    return sb.toString();
  }

  /**
   * 方法功能描述：为退货单查询的公共部分
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param potrantype
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-12 下午05:19:08
   */
  public String getPubCondForBack23(String potrantype) {
    // 交易类型需要有到货环节
    String cond = potrantype + ".barrive= 'Y' ";
    return cond;
  }

}

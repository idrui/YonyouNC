package nc.bs.pu.m21.query.pu;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.pu.rule.CanBackArriveNumCalcRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为退货单提供的查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-31 下午12:13:58
 */
public class OrderQueryFor23BackBP {
  /**
   * 方法功能描述：为退货单参照生成提供查询
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   *          查询条件
   * @param isLazy
   *          懒加载
   * @return 符合条件的采购订单
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-31 下午01:15:28
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    // 根据ID查询订单VO
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(cond, isLazy);

    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.BACK23);
    this.addRule(processer);
    queryVos = processer.after(queryVos);

    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 计算可退货数量
    processer.addAfterRule(new CanBackArriveNumCalcRule());
  }

  /**
   * 方法功能描述：Where条件
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param potrantype
   * @param bdMaterial
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 上午11:03:55
   */
  // private String getWherePart(String poOrderB, String poOrderBB,
  // String potrantype, String bdMaterial) {
  // StringBuilder sb = new StringBuilder();
  // QueryCondManager manager = QueryCondManager.getInstance();
  // String pubcond =
  // manager.getPubCondFor45_23(poOrderB, poOrderBB, bdMaterial);
  // sb.append(pubcond);
  //
  // // 退货不检查到货关闭。即使到货关闭也应该能查询到。
  // String pubcondFor23 = manager.getPubCondForBack23(potrantype);
  // sb.append(" and " + pubcondFor23);
  //
  // // 退货不检查到货计划
  // // 退货不检查订单在途状态
  //
  // // 如果是普通订单，则累计到货数量 >累计入库数量+累计退货数量
  // sb.append(" and (");
  // sb.append("(");
  // sb.append(poOrderB + ".nnum > 0 and coalesce(");
  // sb.append(poOrderB + ".naccumarrvnum,0) - coalesce(" + poOrderB
  // + ".nbackarrvnum,0) - coalesce(" + poOrderB + ".naccumstorenum,0) > 0 ");
  // // 如果是退货订单，则订单数量的绝对值 > 累计退库数量 + 累计退货数量
  // sb.append(") or (");
  // sb.append(poOrderB + ".nnum < 0 and ");
  // sb.append(poOrderB + ".nnum  + coalesce(" + poOrderB
  // + ".nbackarrvnum,0) + coalesce(" + poOrderB + ".nbackstorenum,0) < 0 ");
  // sb.append(")) ");
  //
  //
  // return sb.toString();
  // }

  /**
   * 方法功能描述：构造SQL语句
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 上午11:02:41
   */
  // private String getWholeSql(String cond) {
  // // 别名
  // FromWhereParseUtil utils = new FromWhereParseUtil(cond);
  // String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
  // String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
  // // 在途状态
  // String poOrderBB = utils.getTableAlias(PUEntity.M21_BB_TABLE);
  // String potrantype = utils.getTableAlias(PUEntity.M21_TRANTYPE_TABLE);
  // String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);
  //
  // // 如果有别名使用别名，否则使用表名
  // QueryUtil qutils = QueryUtil.getInstance();
  // poOrderB = qutils.getTableName(PUEntity.M21_B_TABLE, poOrderB);
  // poOrderBB1 = qutils.getTableName(PUEntity.M21_BB1_TABLE, poOrderBB1);
  // // 在途状态
  // poOrderBB = qutils.getTableName(PUEntity.M21_BB_TABLE, poOrderBB);
  // potrantype = qutils.getTableName(PUEntity.M21_TRANTYPE_TABLE, potrantype);
  // bdMaterial = qutils.getTableName(PUEntity.Material_TABLE, bdMaterial);
  //
  // // 替换
  // String replacedCond = qutils.getReplacedCond(cond, poOrderB, poOrderBB1);
  //
  // StringBuilder sb = new StringBuilder();
  // sb.append("select po_order.pk_order," + poOrderB + ".pk_order_b,"
  // + poOrderBB1 + ".pk_order_bb1, " + poOrderBB + ".pk_order_bb");
  // sb.append(replacedCond);
  // sb.append(" and ");
  // sb.append(this.getWherePart(poOrderB, poOrderBB, potrantype, bdMaterial));
  //
  // return sb.toString();
  //
  // }

}

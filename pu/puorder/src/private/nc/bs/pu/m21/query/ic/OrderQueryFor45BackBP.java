/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 上午11:18:15
 */
package nc.bs.pu.m21.query.ic;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.ic.rule.CanBackStockNumCalcRule;
import nc.bs.pu.m21.query.ic.rule.StorePrivilegeChkRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为退库单提供的拉单查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 上午11:18:15
 */
public class OrderQueryFor45BackBP {
  /**
   * 方法功能描述：为退库单参照生成提供查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 下午04:14:32
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    // 根据ID查询订单VO
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(cond, isLazy);
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.BACK45);
    this.addRule(processer);
    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 计算可入库数量
    processer.addAfterRule(new CanBackStockNumCalcRule());
    // 按库管员权限过滤
    processer.addAfterRule(new StorePrivilegeChkRule());
  }

  /**
   * 方法功能描述：Where条件
   * <p>
   * <b>参数说明</b>
   * 
   * @param poOrderB
   * @param poOrderBB1
   * @param potrantype
   * @param bdMaterial
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 上午10:58:55
   */
  // private String getWherePart(String poOrderB, String potrantype,
  // String bdMaterial) {
  // StringBuilder sb = new StringBuilder();
  // QueryCondManager manager = QueryCondManager.getInstance();
  // String pubcond =
  // manager.getPubCondFor45_23(poOrderB, potrantype, bdMaterial);
  // sb.append(pubcond);
  //
  // // 退库不检查到货计划
  // // 退库不检查订单在途状态
  //
  // // 如果是普通订单，则累计入库数量 > 累计退库数量
  // sb.append(" and (");
  // sb.append("(");
  // sb.append(poOrderB + ".nnum > 0 and coalesce(");
  // sb.append(poOrderB + ".naccumstorenum,0) > coalesce(" + poOrderB
  // + ".nbackstorenum,0)");
  // // 如果是退货订单，则订单数量的绝对值 > 累计退库数量 + 累计退货数量
  // sb.append(") or (");
  // sb.append(poOrderB + ".nnum < 0 and ");
  // sb.append(poOrderB + ".nnum  + coalesce(" + poOrderB
  // + ".nbackarrvnum,0) + coalesce(" + poOrderB + ".nbackstorenum,0) < 0 ");
  // sb.append(")) ");
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
   * @time 2010-5-9 上午10:56:39
   */
  // private String getWholeSql(String cond) {
  // // 别名
  // FromWhereParseUtil utils = new FromWhereParseUtil(cond);
  // String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
  // String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
  // String potrantype = utils.getTableAlias(PUEntity.M21_TRANTYPE_TABLE);
  // String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);
  //
  // // 如果有别名使用别名，否则使用表名
  // QueryUtil quitls = QueryUtil.getInstance();
  // poOrderB = quitls.getTableName(PUEntity.M21_B_TABLE, poOrderB);
  // poOrderBB1 = quitls.getTableName(PUEntity.M21_BB1_TABLE, poOrderBB1);
  // potrantype = quitls.getTableName(PUEntity.M21_TRANTYPE_TABLE, potrantype);
  // bdMaterial = quitls.getTableName(PUEntity.Material_TABLE, bdMaterial);
  //
  // StringBuilder sb = new StringBuilder();
  // sb.append("select po_order.pk_order," + poOrderB + ".pk_order_b,"
  // + poOrderBB1 + ".pk_order_bb1 ");
  // sb.append(cond);
  // sb.append(" and ");
  // sb.append(this.getWherePart(poOrderB, potrantype, bdMaterial));
  //
  // return sb.toString();
  // }

}

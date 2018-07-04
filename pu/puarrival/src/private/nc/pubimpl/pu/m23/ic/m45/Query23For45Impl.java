package nc.pubimpl.pu.m23.ic.m45;

import java.util.ArrayList;
import java.util.List;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m23.ic.m45.IQuery23For45;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArriveTrans45QueryUtil;
import nc.vo.pu.m23.utils.BatchSynchronizerM23;
import nc.vo.pu.m23.utils.FillBillInfoFor23;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给库存的采购入库单的查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:57:12
 */
public class Query23For45Impl implements IQuery23For45 {

  @Override
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getCompleteSql(queryScheme);
      ArriveVO[] vos = this.queryArriveBySql(sql);
      FillBillInfoFor23.fillBillInfo(vos);
      return BatchSynchronizerM23.synchBatchCodeData(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  private String getCompleteSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String htname = qrySchemeProcessor.getMainTableAlias();
    String btname =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_arriveorder_b.pk_arriveorder_b");
    StringBuffer from = new StringBuffer();
    // 关联采购订单子表，用于判断是否入库关闭
    from.append(" inner join po_order_b");
    from.append(" on " + btname + ".pk_order_b = po_order_b.pk_order_b ");

    // 关联到货单子子表，用于控制入库
    String bbtable = "(select pk_arriveorder_b,pk_arriveorder_bb";
    bbtable +=
        " from po_arriveorder_bb where  upper(isnull(bcanstore,'Y')) = 'Y'";
    bbtable += " and isnull(nnum,0) - isnull(naccumstorenum,0)> 0 and dr = 0)";
    from.append(" left join " + bbtable + " po_arriveorder_bb");
    from.append(" on " + btname + ".pk_arriveorder_b");
    from.append(" = po_arriveorder_bb.pk_arriveorder_b");
    qrySchemeProcessor.appendFrom(from.toString());
    StringBuilder where = new StringBuilder();
    where.append(" and " + htname + ".fbillstatus = 3 ");// 审核状态
    where.append(" and upper(isnull(" + htname + ".bisback,'N')) = 'N'");// 是否退货=N
    where.append(" and " + btname + ".nnum > 0 ");
    where.append(" and (isnull(" + btname + ".nnum,0)");// 到货主数量-累计入库数量>0
    where.append(" -isnull(" + btname + ".naccumstorenum,0))>0 ");
    where.append(" and upper(isnull(" + btname + ".bfaflag,'N')) = 'N' ");// 生成资产卡片标识=N
    where.append(" and upper(isnull(po_order_b.bstockclose,'N')) = 'N'");// 入库关闭=N
    qrySchemeProcessor.appendWhere(where.toString());

    StringBuilder sql = new StringBuilder();
    sql.append(" select " + htname + ".pk_arriveorder, ");// 增加选择片段
    sql.append(btname + ".pk_arriveorder_b,");// 增加选择片段
    sql.append(" po_arriveorder_bb.pk_arriveorder_bb");// 增加选择片段
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    return sql.toString();
  }

  // @Override
  // public ArriveVO[] queryArrive(String whereSql) throws BusinessException {
  // // 获得完整sql
  // String sql = this.getCompleteSql(whereSql);
  // ArriveVO[] bills = null;
  // try {
  // DataAccessUtils utils = new DataAccessUtils();
  // IRowSet rowset = utils.query(sql);
  // List<String> hids = new ArrayList<String>();
  // List<String> itemids = new ArrayList<String>();
  // List<String> bb1ids = new ArrayList<String>();
  // while (rowset.next()) {
  // String hid = rowset.getString(0);
  // if (StringUtils.isNotEmpty(hid) && !hids.contains(hid)) {
  // hids.add(hid);
  // }
  // String bid = rowset.getString(1);
  // if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
  // itemids.add(bid);
  // }
  // String bbid = rowset.getString(2);
  // if (StringUtils.isNotEmpty(bbid) && !bb1ids.contains(bbid)) {
  // bb1ids.add(bbid);
  // }
  // }
  // if (hids.size() == 0 || itemids.size() == 0) {
  // return null;
  // }
  //
  // // 1、查询ArriveHeaderVO
  // VOQuery<ArriveHeaderVO> query1 =
  // new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
  // ArriveHeaderVO[] headers = query1.query(hids.toArray(new String[0]));
  //
  // // 2、查询ArriveItemVO
  // VOQuery<ArriveItemVO> query2 =
  // new VOQuery<ArriveItemVO>(ArriveItemVO.class);
  // ArriveItemVO[] bitems = query2.query(itemids.toArray(new String[0]));
  //
  // // 3、查询ArriveBbVO
  // ArriveBbVO[] bbItems = null;
  // if (bb1ids.size() > 0) {
  // VOQuery<ArriveBbVO> query3 = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
  // bbItems = query3.query(bb1ids.toArray(new String[0]));
  // }
  // // 把离散的表头、表体数据结合子子表数据，构造可以入库的聚合VO
  // ArriveTrans45QueryUtil tranQueryUtil =
  // new ArriveTrans45QueryUtil(headers, bitems, bbItems);
  // bills = tranQueryUtil.combine();
  // }
  // catch (Exception ex) {
  // ExceptionUtils.marsh(ex);
  // }
  // return bills;
  // }

  // private String getCompleteSql(String fAndW) {
  // FromWhereParseUtil util = new FromWhereParseUtil(fAndW);
  // // 获得表的别名
  // String htname = util.getTableAlias("po_arriveorder");
  // if (StringUtils.isEmpty(htname)) {
  // htname = "po_arriveorder";
  // }
  // String btname = util.getTableAlias("po_arriveorder_b");
  // if (StringUtils.isEmpty(btname)) {
  // btname = "po_arriveorder_b";
  // }
  //
  // StringBuilder sql = new StringBuilder();
  // sql.append(" select po_arriveorder.pk_arriveorder,");// 增加选择片段
  // sql.append(" po_arriveorder_b.pk_arriveorder_b,");// 增加选择片段
  // sql.append(" po_arriveorder_bb.pk_arriveorder_bb");// 增加选择片段
  // sql.append(fAndW);
  // // 增加固定条件
  // sql.append(" and po_arriveorder.dr = 0 ");
  // sql.append(" and po_arriveorder_b.dr = 0 ");
  // sql.append(" and po_arriveorder.fbillstatus = 3 ");// 审核状态
  // sql.append(" and upper(isnull(po_arriveorder.bisback,'N')) = 'N'");//
  // 是否退货=N
  // sql.append(" and po_arriveorder_b.nnum > 0 ");
  // sql.append(" and (isnull(po_arriveorder_b.nnum,0)");// 到货主数量-累计入库数量>0
  // sql.append(" -isnull(po_arriveorder_b.naccumstorenum,0))>0 ");
  // sql.append(" and upper(isnull(po_arriveorder_b.bfaflag,'N')) = 'N' ");//
  // 生成资产卡片标识=N
  // sql.append(" and upper(isnull(po_order_b.bstockclose,'N')) = 'N'");//
  // 入库关闭=N
  // String finalSql = sql.toString();
  // finalSql = StringUtils.replace(finalSql, "po_arriveorder.", htname + ".");
  // finalSql = StringUtils.replace(finalSql, "po_arriveorder_b.", btname +
  // ".");
  // return finalSql;
  // }
  // @Override
  // public String getWhereSQL() {
  // String superFrom = super.getFromPart();
  // FromWhereParseUtil util = new FromWhereParseUtil(superFrom);
  // String btname = util.getTableAlias("po_arriveorder_b");
  // if (StringUtils.isEmpty(btname)) {
  // btname = "po_arriveorder_b";
  // }
  //
  // StringBuffer from = new StringBuffer(superFrom);
  // // 关联采购订单子表，用于判断是否入库关闭
  // from.append(" left join po_order_b");
  // from.append(" on po_arriveorder_b.pk_order_b = po_order_b.pk_order_b ");
  //
  // // 关联到货单子子表，用于控制入库
  // String bbtable = "(select pk_arriveorder_b,pk_arriveorder_bb";
  // bbtable +=
  // " from po_arriveorder_bb where  upper(isnull(bcanstore,'Y')) = 'Y'";
  // bbtable += " and isnull(nnum,0) - isnull(naccumstorenum,0)> 0 and dr = 0)";
  // from.append(" left join " + bbtable + " po_arriveorder_bb");
  // from.append(" on po_arriveorder_b.pk_arriveorder_b");
  // from.append(" = po_arriveorder_bb.pk_arriveorder_b");
  //
  // String where = super.getWherePart();
  // String finalSql = " from " + from.toString() + " where " + where;
  // finalSql = StringUtils.replace(finalSql, "po_arriveorder_b.", btname +
  // ".");
  // return finalSql;
  // }
  private ArriveVO[] queryArriveBySql(String wholeSql) {
    ArriveVO[] bills = null;
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    IRowSet rowset = utils.query(wholeSql);
    List<String> hids = new ArrayList<String>();
    List<String> itemids = new ArrayList<String>();
    List<String> bb1ids = new ArrayList<String>();
    while (rowset.next()) {
      String hid = rowset.getString(0);
      if (StringUtils.isNotEmpty(hid) && !hids.contains(hid)) {
        hids.add(hid);
      }
      String bid = rowset.getString(1);
      if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
        itemids.add(bid);
      }
      String bbid = rowset.getString(2);
      if (StringUtils.isNotEmpty(bbid) && !bb1ids.contains(bbid)) {
        bb1ids.add(bbid);
      }
    }
    if (hids.size() == 0 || itemids.size() == 0) {
      return null;
    }

    // 1、查询ArriveHeaderVO
    VOQuery<ArriveHeaderVO> query1 =
        new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
    ArriveHeaderVO[] headers =
        query1.query(hids.toArray(new String[hids.size()]));

    // 2、查询ArriveItemVO
    VOQuery<ArriveItemVO> query2 =
        new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] bitems =
        query2.query(itemids.toArray(new String[itemids.size()]));

    // 3、查询ArriveBbVO
    ArriveBbVO[] bbItems = null;
    if (bb1ids.size() > 0) {
      VOQuery<ArriveBbVO> query3 = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      bbItems = query3.query(bb1ids.toArray(new String[bb1ids.size()]));
    }
    // 把离散的表头、表体数据结合子子表数据，构造可以入库的聚合VO
    ArriveTrans45QueryUtil tranQueryUtil =
        new ArriveTrans45QueryUtil(headers, bitems, bbItems);
    bills = tranQueryUtil.combine();
    return bills;
  }

}

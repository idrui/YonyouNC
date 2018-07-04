package nc.pubimpl.pu.m23.pu.m21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m23.pu.m21.IQuery23For21;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给采购订单补货的查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:57:12
 */
public class Query23For21Impl implements IQuery23For21 {

  @Override
  public ArriveVO[] queryBackArrive(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getCompleteSql(queryScheme);
      return this.queryArriveBySql(sql);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public Map<String, UFBoolean> queryHaveArrive(String[] bids)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_10.name());
      String hidsql = builder.buildSQL(" and " + ArriveItemVO.PK_ORDER_B, bids);

      SqlBuilder sql = new SqlBuilder();
      sql.append("select " + ArriveItemVO.PK_ORDER_B + ",");
      sql.append("count(*) from " + PUEntity.M23_B_TABLE);
      sql.append(" where ");
      String pk_group = BSContext.getInstance().getGroupID();
      sql.append(ArriveItemVO.PK_GROUP, pk_group);

      sql.append(hidsql);
      sql.append(" and " + ArriveItemVO.DR, 0);
      sql.append(" group by " + ArriveItemVO.PK_ORDER_B);

      DataAccessUtils util = new DataAccessUtils();
      String[][] values =
          util.query(sql.toString()).toTwoDimensionStringArray();
      if (ArrayUtils.isEmpty(values)) {
        return null;
      }
      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (String[] value : values) {
        if (MathTool.compareTo(new UFDouble(value[1]), UFDouble.ZERO_DBL) > 0) {
          map.put(value[0], UFBoolean.TRUE);
        }
        else {
          map.put(value[0], UFBoolean.FALSE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public Map<String, UFBoolean> queryHaveArriveByBBid(String[] bbids)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_11.name());
      String hidsql =
          builder.buildSQL(" and " + ArriveItemVO.PK_ORDER_BB1, bbids);

      SqlBuilder sql = new SqlBuilder();
      sql.append("select " + ArriveItemVO.PK_ORDER_BB1 + ",");
      sql.append("count(*) from " + PUEntity.M23_B_TABLE);
      sql.append(" where ");
      String pk_group = BSContext.getInstance().getGroupID();
      sql.append(ArriveItemVO.PK_GROUP, pk_group);

      sql.append(hidsql);
      sql.append(" and " + ArriveItemVO.DR, 0);
      sql.append(" group by " + ArriveItemVO.PK_ORDER_BB1);

      DataAccessUtils util = new DataAccessUtils();
      String[][] values =
          util.query(sql.toString()).toTwoDimensionStringArray();
      if (ArrayUtils.isEmpty(values)) {
        return null;
      }
      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (String[] value : values) {
        if (MathTool.compareTo(new UFDouble(value[1]), UFDouble.ZERO_DBL) > 0) {
          map.put(value[0], UFBoolean.TRUE);
        }
        else {
          map.put(value[0], UFBoolean.FALSE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private ArriveVO[] dealCanreplnum(ArriveVO[] bills) {
    if (ArrayUtils.isEmpty(bills)) {
      return null;
    }
    for (ArriveVO vo : bills) {
      for (ArriveItemVO item : vo.getBVO()) {
        UFDouble nnum = item.getNnum();
        UFDouble replnum = item.getNaccumreplnum();
        // 可补货数量 = ABS(到货数量) - 累计补货数量
        UFDouble ncanreplnum = MathTool.sub(nnum.abs(), replnum);
        item.setNcanreplnum(ncanreplnum);
      }
    }
    return bills;
  }

  private String getCompleteSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String htname = qrySchemeProcessor.getMainTableAlias();
    String btname =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_arriveorder_b.pk_arriveorder_b");
    qrySchemeProcessor.appendCurrentGroup();
    StringBuffer where = new StringBuffer();
    // 审核状态
    where.append(" and " + htname + ".fbillstatus = 3 ");
    // 来源单据类型=21
    where.append(" and " + btname + ".csourcetypecode = '21' ");
    // 是否赠品=N
    where.append(" and " + btname + ".bpresent = 'N' ");
    // 是否退货=Y
    where.append(" and " + htname + ".bisback = 'Y' ");
    // 累计补货数量 + 退货数量 < 0
    where.append(" and isnull(" + btname + ".naccumreplnum,0) + isnull("
        + btname + ".nnum,0) < 0 ");
    // 退货是否基于订单 = N
    where.append(" and isnull(" + btname + ".bbackreforder,'N') = 'N' ");
    qrySchemeProcessor.appendWhere(where.toString());
    StringBuilder sql = new StringBuilder();
    // 增加选择片段
    sql.append(" select " + btname + ".pk_arriveorder_b ");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    return sql.toString();
  }

  private ArriveVO[] queryArriveBySql(String wholeSql) {
    ArriveVO[] bills = null;
    // 查子表主键BID
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    IRowSet rowset = utils.query(wholeSql);
    String[] arriveIdArray = rowset.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(arriveIdArray)) {
      return null;
    }

    // 根据BID查询视图VO
    ViewQuery<ArriveViewVO> query =
        new ViewQuery<ArriveViewVO>(ArriveViewVO.class);
    ArriveViewVO[] viewVOArray = query.query(arriveIdArray);

    List<ArriveHeaderVO> headList = new ArrayList<ArriveHeaderVO>();
    List<ArriveItemVO> itemList = new ArrayList<ArriveItemVO>();
    for (ArriveViewVO view : viewVOArray) {
      headList.add(view.getHVO());
      itemList.add(view.getBVO());
    }
    BillComposite<ArriveVO> bc = new BillComposite<ArriveVO>(ArriveVO.class);
    ArriveVO tempVO = new ArriveVO();
    bc.append(tempVO.getMetaData().getParent(),
        headList.toArray(new ArriveHeaderVO[headList.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(ArriveItemVO.class),
        itemList.toArray(new ArriveItemVO[itemList.size()]));
    bills = bc.composite();

    // 处理可补货数量
    bills = this.dealCanreplnum(bills);
    return bills;
  }
}

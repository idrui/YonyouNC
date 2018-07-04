package nc.pubimpl.pu.m21.ec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.bs.pu.m21.query.price.cal.OrderPriceQuery;
import nc.impl.pu.m21.action.OrderReceivePlanSaveAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.mapping.VORowSetMap;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.IDQueryCondition;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.ec.IOrderQueryForEC;
import nc.pubitf.pu.m21.ec.LinkQueryCondVO;
import nc.pubitf.pu.m21.ec.OnConfirmOrderQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderApprovedQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderApprovedViewVO;
import nc.pubitf.pu.m21.ec.OrderExecDetailHeadVO;
import nc.pubitf.pu.m21.ec.OrderExecDetailQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderExecDetailViewVO;
import nc.pubitf.pu.m21.ec.OrderItemApprovedVO;
import nc.pubitf.pu.m21.ec.OrderItemPubedVO;
import nc.pubitf.pu.m21.ec.OrderMatViewECVO;
import nc.pubitf.pu.m21.ec.OrderPublishedQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderPublishedViewVO;
import nc.pubitf.pu.m21.ec.OrderQueryForECUtil;
import nc.pubitf.pu.m21.ec.OrderReceivePlanECVO;
import nc.pubitf.pu.m21.ec.OrderReceivePlanPubedQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderReceivePlanPubedViewVO;
import nc.pubitf.pu.m21.ec.OrderReceivePlanQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderReceivePlanViewECVO;
import nc.pubitf.pu.m21.ec.OrderReleasedOverQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderReleasedOverViewVO;
import nc.pubitf.pu.m21.ec.SupplyDetailQueryVO;
import nc.pubitf.pu.m21.ec.SupplyDetailVO;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.ec.QueryForECUtil;
import nc.vo.pu.ec.enumeration.BillSrcEnum;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.MetaTool;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 电子商务查询服务实现类
 * 
 * @since 6.0
 * @version 2011-3-15 下午06:01:10
 * @author wuxla
 */

public class OrderQueryForECImpl implements IOrderQueryForEC {
  @Override
  public OrderVO[] linkQueryOrderVOByConds(LinkQueryCondVO condVo)
      throws BusinessException {
    String sql = this.getLinkQueryOrderVOByCondsSql(condVo);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] bids = rowset.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }

    //
    IOrderPubQuery pubQuery =
        NCLocator.getInstance().lookup(IOrderPubQuery.class);
    return pubQuery.queryOrderVOByBids(bids);
  }

  @Override
  public OrderApprovedViewVO[] queryApprovedOrder(
      OrderApprovedQueryCondVO condVO) throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      QueryForECUtil.checkDateCond(condVO);
      String sql = this.createApprovedOrder(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }
      ViewQuery<OrderApprovedViewVO> query =
          new ViewQuery<OrderApprovedViewVO>(OrderApprovedViewVO.class);
      OrderApprovedViewVO[] vos = query.query(hids);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderPriceData[] queryLatestPrice(String purchaseOrg, String[] moids,
      UFDate startDate, UFDate endDate, String pk_currery)
      throws BusinessException {
    OrderPriceQuery orderPriceQuery = new OrderPriceQuery();
    return orderPriceQuery.queryLatestPrice(new String[] {
      purchaseOrg
    }, null, null, new String[] {
      pk_currery
    }, moids, startDate, endDate);

  }

  @Override
  public OrderReceivePlanPubedViewVO[] queryMaintainedAndPublishedRecPlans(
      OrderReceivePlanPubedQueryCondVO condVO, String srcType)
      throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      String sql = this.createRPSCMMain(condVO, srcType);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] bb1ids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(bb1ids)) {
        return null;
      }
      ViewQuery<OrderReceivePlanPubedViewVO> query =
          new ViewQuery<OrderReceivePlanPubedViewVO>(
              OrderReceivePlanPubedViewVO.class);
      OrderReceivePlanPubedViewVO[] vos = query.query(bb1ids);

      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }

      MapList<String, OrderReceivePlanPubedViewVO> mapList =
          new MapList<String, OrderReceivePlanPubedViewVO>();
      for (OrderReceivePlanPubedViewVO vo : vos) {
        mapList.put(vo.getPk_order(), vo);
      }

      String[] hids =
          mapList.keySet().toArray(new String[mapList.keySet().size()]);
      VOQuery<OrderHeaderVO> headQuery =
          new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, new String[] {
            OrderHeaderVO.PK_ORDER, OrderHeaderVO.VBILLCODE,
            OrderHeaderVO.DBILLDATE, OrderHeaderVO.TS, OrderHeaderVO.PK_DEPT,
            OrderHeaderVO.PK_DEPT_V
          });
      VOQuery<OrderItemVO> bodyQuery =
          new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
            OrderItemVO.PK_ORDER, OrderItemVO.TS, OrderItemVO.PK_PSFINANCEORG,
            OrderItemVO.PK_PSFINANCEORG_V, OrderItemVO.PK_RECVSTORDOC,
            OrderItemVO.PK_REQSTOORG_V, OrderItemVO.PK_REQSTOORG,
            OrderItemVO.PK_REQDEPT_V, OrderItemVO.PK_REQDEPT,
            OrderItemVO.PK_RECEIVEADDRESS
          });
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_20.name());
      String hidCond = builder.buildSQL(" and " + OrderHeaderVO.PK_ORDER, hids);
      OrderHeaderVO[] headVOs =
          headQuery.query(hidCond, " order by " + OrderHeaderVO.DBILLDATE);
      OrderItemVO[] bodyVOs =
          bodyQuery.query(hidCond, " order by " + OrderItemVO.TS);

      List<OrderReceivePlanPubedViewVO> list =
          new ArrayList<OrderReceivePlanPubedViewVO>();
      for (OrderItemVO bodyVO : bodyVOs) {
        UFDateTime orderBTs = bodyVO.getTs();
        List<OrderReceivePlanPubedViewVO> tempList =
            mapList.get(bodyVO.getPk_order());
        for (OrderReceivePlanPubedViewVO vo : tempList) {
          vo.setOrderBTs(orderBTs);
          vo.setPk_psfinanceorg(bodyVO.getPk_psfinanceorg());
          vo.setPk_psfinanceorg_v(bodyVO.getPk_psfinanceorg_v());
          vo.setPk_reqdept(bodyVO.getPk_reqdept());
          vo.setPk_reqdept_v(bodyVO.getPk_reqdept_v());
          vo.setPk_recvstordoc(bodyVO.getPk_recvstordoc());
        }
      }
      for (OrderHeaderVO headVO : headVOs) {
        String vordercode = headVO.getVbillcode();
        UFDateTime orderTs = headVO.getTs();
        List<OrderReceivePlanPubedViewVO> tempList =
            mapList.get(headVO.getPk_order());
        for (OrderReceivePlanPubedViewVO vo : tempList) {
          vo.setVordercode(vordercode);
          vo.setOrderTs(orderTs);
          vo.setPk_dept(headVO.getPk_dept());
          vo.setPk_dept_v(headVO.getPk_dept_v());
        }
        list.addAll(tempList);
      }

      return list.toArray(new OrderReceivePlanPubedViewVO[list.size()]);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryOnConfirmOrder(OnConfirmOrderQueryCondVO condVO)
      throws BusinessException {
    try {
      String sql = this.getOnConfirmOrderQuerySql(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }

      BillQuery<OrderVO> billQuery = new BillQuery<OrderVO>(OrderVO.class);
      OrderVO[] vos = billQuery.query(hids);
      return vos;

      // List<OrderVO> tempVos = new ArrayList<OrderVO>();
      // for (OrderVO vo : vos) {
      // List<OrderItemVO> tempItems = new ArrayList<OrderItemVO>();
      // for (OrderItemVO itemVo : vo.getBVO()) {
      // if (EnumActive.ACTIVE.toInteger().equals(itemVo.getFisactive())) {
      // tempItems.add(itemVo);
      // }
      // }
      // if (!tempItems.isEmpty()) {
      // vo.setBVO(tempItems.toArray(new OrderItemVO[tempItems.size()]));
      // tempVos.add(vo);
      // }
      // }
      // if (tempVos.isEmpty()) {
      // return null;
      // }
      // return tempVos.toArray(new OrderVO[tempVos.size()]);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderExecDetailHeadVO[] queryOrderExecDetail(
      OrderExecDetailQueryCondVO condVO) throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      QueryForECUtil.checkDateCond(condVO);
      String sql = this.createExecDetail(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);

      List<String> hids = new LinkedList<String>();
      while (rowset.next()) {
        hids.add(rowset.getString(0));
      }
      if (hids.isEmpty()) {
        return null;
      }

      ViewQuery<OrderExecDetailHeadVO> query =
          new ViewQuery<OrderExecDetailHeadVO>(OrderExecDetailHeadVO.class);
      return query.query(hids.toArray(new String[hids.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderExecDetailViewVO[] queryOrderExecDetailByHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createHidSql(hids);
      EfficientViewQuery<OrderExecDetailViewVO> query =
          new EfficientViewQuery<OrderExecDetailViewVO>(
              OrderExecDetailViewVO.class);
      OrderExecDetailViewVO[] vos = query.query(sql);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderItemApprovedVO[] queryOrderItemApprovedVOByHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createHidSql(hids);
      EfficientViewQuery<OrderItemApprovedVO> query =
          new EfficientViewQuery<OrderItemApprovedVO>(OrderItemApprovedVO.class);
      OrderItemApprovedVO[] pubedVOs = query.query(sql);
      return pubedVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderItemPubedVO[] queryOrderItemPubedVOByHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createHidSql(hids);
      EfficientViewQuery<OrderItemPubedVO> query =
          new EfficientViewQuery<OrderItemPubedVO>(OrderItemPubedVO.class);
      OrderItemPubedVO[] pubedVOs = query.query(sql);
      return pubedVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderMatViewECVO[] queryOrderItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException {
    try {
      String pk_org = queryECVO.getPk_org();
      String[] pk_materials = queryECVO.getPk_materials();
      String pk_supplier = queryECVO.getPk_supplier();
      UFDate beginDate = queryECVO.getBeginDate();
      UFDate endDate = queryECVO.getEndDate();
      QueryForECUtil.checkMatAndSupplierCond(pk_materials, pk_supplier,
          beginDate, endDate);
      String sql =
          this.createMatSql(pk_org, pk_materials, pk_supplier, beginDate,
              endDate);
      EfficientViewQuery<OrderMatViewECVO> query =
          new EfficientViewQuery<OrderMatViewECVO>(OrderMatViewECVO.class);
      OrderMatViewECVO[] matVOs = query.query(sql);
      return matVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderItemVO[] queryOrderItemsForEC(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      String where = this.createQueryItemsForECSql(bids);
      VOQuery<OrderItemVO> query = new VOQuery<OrderItemVO>(OrderItemVO.class);
      return query.query(where, null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(bids)) {
        return null;
      }
      ViewQuery<OrderViewVO> query =
          new ViewQuery<OrderViewVO>(OrderViewVO.class);
      OrderViewVO[] views = query.query(bids);
      OrderVO[] vos = OrderViewVO.getOrderVO(views);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderPublishedViewVO[] queryPublishOrder(
      OrderPublishedQueryCondVO condVO) throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      QueryForECUtil.checkDateCond(condVO);
      String sql = this.createPublishOrder(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }
      ViewQuery<OrderPublishedViewVO> query =
          new ViewQuery<OrderPublishedViewVO>(OrderPublishedViewVO.class);
      OrderPublishedViewVO[] vos = query.query(hids);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, List<String>> queryReceivePlanCode(String[] hids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(hids)) {
      return null;
    }
    try {
      EfficientViewQuery<OrderReceivePlanECVO> query =
          new EfficientViewQuery<OrderReceivePlanECVO>(
              OrderReceivePlanECVO.class);
      String sql = this.createRPSql(hids);

      OrderReceivePlanECVO[] vos = query.query(sql);
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }

      MapList<String, String> mapList = new MapList<String, String>();
      for (OrderReceivePlanECVO vo : vos) {
        mapList.put(vo.getPk_order(), vo.getVbillcode());
      }
      return mapList.toMap();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderReceivePlanViewECVO[] queryReceivePlansForSupplierMaintain(
      OrderReceivePlanQueryCondVO condVO) throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      String sql = this.createRPSupplierMain(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] bb1ids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(bb1ids)) {
        return null;
      }

      ViewQuery<OrderReceivePlanViewECVO> query =
          new ViewQuery<OrderReceivePlanViewECVO>(
              OrderReceivePlanViewECVO.class);
      OrderReceivePlanViewECVO[] vos = query.query(bb1ids);

      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      MapList<String, OrderReceivePlanViewECVO> mapList =
          new MapList<String, OrderReceivePlanViewECVO>();
      for (OrderReceivePlanViewECVO vo : vos) {
        mapList.put(vo.getPk_order(), vo);
      }
      String[] hids =
          mapList.keySet().toArray(new String[mapList.keySet().size()]);
      VOQuery<OrderHeaderVO> headQuery =
          new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, new String[] {
            OrderHeaderVO.PK_ORDER, OrderHeaderVO.VBILLCODE,
            OrderHeaderVO.DBILLDATE, OrderHeaderVO.TS
          });
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_21.name());
      String hidCond = builder.buildSQL(" and " + OrderHeaderVO.PK_ORDER, hids);
      OrderHeaderVO[] headVOs =
          headQuery.query(hidCond, " order by " + OrderHeaderVO.DBILLDATE);

      List<OrderReceivePlanViewECVO> list =
          new ArrayList<OrderReceivePlanViewECVO>();

      for (OrderHeaderVO headVO : headVOs) {
        String vordercode = headVO.getVbillcode();
        UFDateTime orderTs = headVO.getTs();
        List<OrderReceivePlanViewECVO> tempList =
            mapList.get(headVO.getPk_order());
        for (OrderReceivePlanViewECVO vo : tempList) {
          vo.setVordercode(vordercode);
          vo.setOrderTs(orderTs);
        }
        list.addAll(tempList);
      }
      return list.toArray(new OrderReceivePlanViewECVO[list.size()]);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderReceivePlanECVO[] queryReceivePlanVOsByBId(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createBidSql(bids);
      EfficientViewQuery<OrderReceivePlanECVO> query =
          new EfficientViewQuery<OrderReceivePlanECVO>(
              OrderReceivePlanECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderReceivePlanECVO[] queryReceivePlanVOsByHId(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createRPSql(hids);
      EfficientViewQuery<OrderReceivePlanECVO> query =
          new EfficientViewQuery<OrderReceivePlanECVO>(
              OrderReceivePlanECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderReleasedOverViewVO[] queryReleasedOverOrder(
      OrderReleasedOverQueryCondVO condVO) throws BusinessException {
    try {
      QueryForECUtil.checkSupplierCond(condVO);
      QueryForECUtil.checkDateCond(condVO);
      String sql = this.createReleasedOverOrder(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);

      List<String> hids = new LinkedList<String>();
      while (rowset.next()) {
        hids.add(rowset.getString(0));
      }
      if (hids.isEmpty()) {
        return null;
      }
      ViewQuery<OrderReleasedOverViewVO> query =
          new ViewQuery<OrderReleasedOverViewVO>(OrderReleasedOverViewVO.class);
      OrderReleasedOverViewVO[] vos =
          query.query(hids.toArray(new String[hids.size()]));
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderReceivePlanVO[] queryRPVOsByIds(String[] bb1ids)
      throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(bb1ids)) {
        return null;
      }
      VOQuery<OrderReceivePlanVO> query =
          new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
      return query.query(bb1ids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public SupplyDetailVO[] querySupplyDetail(SupplyDetailQueryVO condVO)
      throws BusinessException {
    try {
      // 查询条件中除供应商主键外，其它都不是必输项
      QueryForECUtil.checkSupplierCond(condVO);
      String sql = this.createSupplyDetailSql(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] ids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(ids)) {
        return null;
      }
      SupplyDetailVO[] vos = this.queryViewForSupplyDetail(ids);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] save(OrderVO[] orderVos) throws BusinessException {
    try {
      OrderContext ctx = new OrderContext();
      BillTransferTool<OrderVO> transferTool =
          new BillTransferTool<OrderVO>(orderVos);
      OrderVO[] originVos = transferTool.getOriginBills();
      OrderVO[] clientFullVOs = transferTool.getClientFullInfoBill();
      OrderSaveBP bp = new OrderSaveBP(ctx);
      OrderVO[] savedVOs = bp.save(clientFullVOs, originVos);
      return savedVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Object[] saveReveivePlanVO(BatchOperateVO batchVO, OrderVO orderVO)
      throws BusinessException {
    if (null == batchVO) {
      return null;
    }

    try {
      return new OrderReceivePlanSaveAction().batchSave(batchVO, orderVO,
          UFBoolean.FALSE);
    }
    catch (Exception e) {
      Throwable ex = ExceptionUtils.unmarsh(e);
      if (ex instanceof AskNumException) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4004030_0",
                "04004030-0352");
        ExceptionUtils.marsh(new Exception(message));
      }
      else {
        ExceptionUtils.marsh(e);
      }
    }

    return null;
  }

  private String createApprovedOrder(OrderApprovedQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getApprovedWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor
        .appendWhere(" and " + hname + "." + OrderHeaderVO.FORDERSTATUS + " = "
            + POEnumBillStatus.APPROVE.toInt());
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + OrderHeaderVO.PK_ORDER);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_24.name());
    String hidCond =
        builder.buildSQL(" and po_order_bb1." + OrderReceivePlanVO.PK_ORDER_B,
            bids);

    SqlBuilder sql = new SqlBuilder();
    sql.append(hidCond);
    sql.append(" and po_order_bb1." + OrderReceivePlanVO.DR, 0);
    return sql.toString();
  }

  private String createExecDetail(OrderExecDetailQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B + "."
        + OrderItemVO.PK_ORG);

    SqlBuilder wherePart = new SqlBuilder();
    // qrySchemeProcessor
    // .appendFrom(" inner join po_order_b on po_order_b.pk_order = po_order.pk_order ");
    wherePart.append(OrderQueryForECUtil.getExecDetailWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor
        .appendWhere(" and " + hname + "." + OrderHeaderVO.FORDERSTATUS + " = "
            + POEnumBillStatus.APPROVE.toInt());
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + OrderHeaderVO.PK_ORDER);
    sql.append(", " + hname + "." + OrderHeaderVO.VBILLCODE);
    sql.append(", " + hname + "." + OrderHeaderVO.DBILLDATE);
    sql.append(fromwherePart);
    sql.append(" order by ");
    sql.append(hname);
    sql.append(".");
    sql.append(OrderHeaderVO.VBILLCODE);
    sql.append(",");
    sql.append(hname);
    sql.append(".");
    sql.append(OrderHeaderVO.DBILLDATE);
    return sql.toString();
  }

  private String createHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_22.name());
    String hidCond = builder.buildSQL(" and " + OrderItemVO.PK_ORDER, hids);

    SqlBuilder sql = new SqlBuilder();
    sql.append(hidCond);
    // wuxla 向单 去掉关闭条件
    // sql.append(" and " + OrderItemVO.FISACTIVE, "=",
    // EnumActive.ACTIVE.toInt());
    return sql.toString();
  }

  private String createMatSql(String pk_org, String[] pk_materials,
      String pk_supplier, UFDate beginDate, UFDate endDate) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_23.name());
    String matHidCond =
        builder.buildSQL(" and " + OrderItemVO.PK_MATERIAL, pk_materials);
    SqlBuilder sql = new SqlBuilder();
    sql.append(matHidCond);
    sql.append(" and po_order." + PUQueryConst.SUPPLIER, pk_supplier);
    if (pk_org != null) {
      sql.append(" and po_order." + PUQueryConst.PK_ORG, pk_org);
    }
    sql.append(" and po_order." + OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());

    // 向单 wuxla 去掉关闭和冻结条件
    // sql.append(" and po_order." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    // sql.append(" and po_order." + OrderHeaderVO.BFINALCLOSE,
    // UFBoolean.FALSE);
    // sql.append(" and po_order_b." + OrderItemVO.FISACTIVE,
    // EnumActive.ACTIVE.toInt());
    sql.append(" and po_order." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and po_order." + OrderHeaderVO.DBILLDATE, ">=",
        beginDate.toString());
    sql.append(" and po_order." + OrderHeaderVO.DBILLDATE, "<=",
        endDate.toString());
    return sql.toString();
  }

  private String createPublishOrder(OrderPublishedQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getPubedWherePart(qrySchemeProcessor,
        condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());

    String hecname =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.BPUBLISH);
    qrySchemeProcessor.appendWhere(" and " + hecname + "."
        + OrderHeaderVO.BPUBLISH + " = 'Y' ");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + OrderHeaderVO.PK_ORDER);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createQueryItemsForECSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_64.name());
    String hidCond =
        builder.buildSQL(" and po_order_b." + OrderItemVO.PK_ORDER_B, bids);

    SqlBuilder sql = new SqlBuilder();
    sql.append(hidCond);
    sql.append(" and po_order_b." + OrderItemVO.FISACTIVE,
        EnumActive.ACTIVE.toInt());
    return sql.toString();
  }

  private QueryScheme createQueryScheme() {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M21_H_TABLE + " " + PUEntity.M21_H_TABLE);
    SqlBuilder where = new SqlBuilder();
    where.append(PUEntity.M21_H_TABLE + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M21_H_TABLE);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String orderbeanid = null;
    try {
      orderbeanid =
          MetaUtils.getBusinessEntityByBillType(POBillType.Order.getCode())
              .getID();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    QueryScheme qs = new QueryScheme();
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, orderbeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    return qs;
  }

  private String createReleasedOverOrder(OrderReleasedOverQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getPubedWherePart(qrySchemeProcessor,
        condVO));
    // wuxla 向单 去掉关闭和冻结条件
    // wherePart.append(" and po_order." + OrderHeaderVO.BFROZEN,
    // UFBoolean.FALSE);
    // wherePart.append(" and po_order." + OrderHeaderVO.BFINALCLOSE,
    // UFBoolean.FALSE);
    // wherePart.append(" and po_order_b." + OrderItemVO.FISACTIVE,
    // EnumActive.ACTIVE.toInt());
    qrySchemeProcessor.appendWhere(wherePart.toString());
    if (condVO.getMatnameCond() == null) { // 如果不等于空的话，在构造物料条件时会join订单B表。
      qrySchemeProcessor
          .appendFrom(" inner join po_order_b on po_order_b.pk_order = po_order.pk_order ");
    }
    String hecname =
        qrySchemeProcessor
            .getTableAliasOfAttribute(OrderHeaderVO.BRELEASEDOVER);
    qrySchemeProcessor.appendWhere(" and " + hecname + "."
        + OrderHeaderVO.BRELEASEDOVER + " = 'Y' ");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + OrderHeaderVO.PK_ORDER);
    sql.append(", " + hname + "." + OrderHeaderVO.VBILLCODE);
    sql.append(", " + hname + "." + OrderHeaderVO.DBILLDATE);
    sql.append(fromwherePart);
    sql.append(" order by ");
    sql.append(hname);
    sql.append(".");
    sql.append(OrderHeaderVO.VBILLCODE);
    sql.append(",");
    sql.append(hname);
    sql.append(".");
    sql.append(OrderHeaderVO.DBILLDATE);
    return sql.toString();
  }

  private String createRPSCMMain(OrderReceivePlanPubedQueryCondVO condVO,
      String srcType) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getRPSCMMaintainWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    if (BillSrcEnum.SCM.code().equals(srcType)) {
      qrySchemeProcessor
          .appendWhere(" and isnull(po_order_bb1.vecbillcode,'~') = '~' ");
    }
    else if (BillSrcEnum.EC.code().equals(srcType)) {
      qrySchemeProcessor
          .appendWhere(" and po_order_bb1.vecbillcode not like 'EC%' ");
    }

    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    String bb1TableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + bb1TableAlias + "."
        + OrderReceivePlanVO.PK_ORDER_BB1);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createRPSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_24.name());
    String hidCond =
        builder.buildSQL(" and po_order." + OrderReceivePlanVO.PK_ORDER, hids);

    SqlBuilder sql = new SqlBuilder();
    sql.append(hidCond);
    sql.append(" and po_order." + OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
    sql.append(" and po_order_b." + OrderItemVO.FISACTIVE,
        EnumActive.ACTIVE.toInt());
    sql.append(" and po_order_bb1." + OrderReceivePlanVO.DR, 0);
    return sql.toString();
  }

  private String createRPSupplierMain(OrderReceivePlanQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getRPSupplierMaintainWherePart(
        qrySchemeProcessor, condVO));
    // 可入库数量>0
    wherePart
        .append(" and isnull(po_order_b.nnum,0)-isnull(po_order_b.naccumrpnum,0)>0 ");

    qrySchemeProcessor.appendWhere(wherePart.toString());
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + PUEntity.M21_B_TABLE + "."
        + OrderItemVO.PK_ORDER_B);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createSupplyDetailSql(SupplyDetailQueryVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getSupplyDetailWherePart(
        qrySchemeProcessor, condVO));

    qrySchemeProcessor.appendWhere(wherePart.toString());
    String hname = qrySchemeProcessor.getMainTableAlias();
    qrySchemeProcessor.appendWhere(" and " + hname + "."
        + OrderHeaderVO.FORDERSTATUS + " = 3 ");

    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    String bTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    sql.append("select distinct " + bTableAlias + "." + OrderItemVO.PK_ORDER_B);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String getLinkQueryOrderVOByCondsSql(LinkQueryCondVO condVo) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String becname =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_schedule");
    String hecname =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.BPUBLISH);

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getLinkQueryByCondWherePart(
        qrySchemeProcessor, condVo));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor.appendWhere(" and " + becname + ".pk_schedule = '~'");
    qrySchemeProcessor.appendWhere(" and " + hecname + "."
        + OrderHeaderVO.BRELEASEDOVER + " = 'Y' ");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + PUEntity.M21_B_TABLE + "."
        + OrderItemVO.PK_ORDER_B);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String getOnConfirmOrderQuerySql(OnConfirmOrderQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();
    String hecname =
        qrySchemeProcessor
            .getTableAliasOfAttribute(OrderHeaderVO.BRELEASEDOVER);

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getOnConfirmOrderQueryWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor.appendWhere(" and " + hecname + "."
        + OrderHeaderVO.BRELEASEDOVER + " = '" + UFBoolean.TRUE + "'");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + OrderHeaderVO.PK_ORDER);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private SupplyDetailVO[] queryMainVOForSupplyDetail(String[] ids) {
    StringBuffer sql = new StringBuffer();
    sql.append("select ")
        .append(
            " pk_org,pk_material,cunitid,sum(nnum) nnum,sum(norigtaxmny) norigtaxmny, ")
        .append(
            " sum(naccumarrvnum) naccumarrvnum, sum(naccumstorenum) naccumstorenum ,sum(nbackarrvnum) nbackarrvnum,sum(nbackstorenum) nbackstorenum,sum(naccuminvoicenum) naccuminvoicenum,  ")
        .append(" sum(naccuminvoicemny) naccuminvoicemny, ")
        .append(" nexchangerate from po_order_b ");
    IDQueryCondition tool = new IDQueryCondition();
    IAttributeMeta primaryKey =
        MetaTool.getVOMeta(OrderItemVO.class).getPrimaryAttribute();
    String idSQL = tool.build(primaryKey, ids);
    sql.append(idSQL).append(" and isnull(po_order_b.dr,0) =0 ")
        .append(" group by pk_org,pk_material,cunitid,nexchangerate ");
    DataAccessUtils dao = new DataAccessUtils();
    IRowSet rowset = dao.query(sql.toString());
    String[] names =
        new String[] {
          OrderItemVO.PK_ORG, OrderItemVO.PK_MATERIAL, OrderItemVO.CUNITID,
          OrderItemVO.NNUM, OrderItemVO.NORIGTAXMNY, OrderItemVO.NACCUMARRVNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NBACKARRVNUM,
          OrderItemVO.NBACKSTORENUM, OrderItemVO.NACCUMINVOICENUM,
          OrderItemVO.NACCUMINVOICEMNY, OrderItemVO.NEXCHANGERATE
        };
    VORowSetMap<OrderItemVO> bo =
        new VORowSetMap<OrderItemVO>(OrderItemVO.class, names);
    ISuperVO[] orderItemVOs = bo.convert(rowset);
    int size = orderItemVOs.length;
    SupplyDetailVO[] views = Constructor.construct(SupplyDetailVO.class, size);
    for (int i = 0; i < size; i++) {
      views[i].setVO(orderItemVOs[i]);
    }
    return views;
  }

  private SupplyDetailVO[] queryViewForSupplyDetail(String[] ids) {
    int length = ids.length;
    if (length == 0) {
      return Constructor.construct(SupplyDetailVO.class, 0);
    }
    SupplyDetailVO[] views = this.queryMainVOForSupplyDetail(ids);
    return views;
  }

}

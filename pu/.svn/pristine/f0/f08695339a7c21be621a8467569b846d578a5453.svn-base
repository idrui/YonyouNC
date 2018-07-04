package nc.bs.pu.report.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.MetaData;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.report.enumeration.OrderExeStatContent;
import nc.vo.pu.report.enumeration.OrderExeStatType;
import nc.vo.pu.report.queryinfo.order.OrderExecDetailQueryView;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.view.order.OrderExecDetailViewVO;
import nc.vo.pu.report.view.order.RptMetaDataUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单执行明细查询BP
 * 
 * @since 6.0
 * @version 2011-9-16 上午09:45:19
 * @author wuxla
 */

public class OrderExecDetailRptBP {
  private boolean arrive;

  private boolean icpurin;

  private boolean invoice;

  private boolean status;

  private OrderExecDetailQueryView sqlview;
  
  public DataSet getExecDetailDataSet(SmartContext context) {
    ConditionVO[] conds =
        (ConditionVO[]) context.getAttribute(PUOrderQryInfoPara.EXEC_COND);
    this.sqlview = new OrderExecDetailQueryView(conds, context);
    Integer stattype =
        (Integer) context.getAttribute(PUOrderQryInfoPara.STATTYPE);
    String value =
        (String) context.getAttribute(PUOrderQryInfoPara.STATCONTENT);
    this.init(value);
    OrderExecDetailViewVO[] views = null;
    // 采购订单行执行明细
    if (stattype != null) {
      if (OrderExeStatType.EXECLIST.toInt() == stattype.intValue()) {
        views = this.orderExecList(value);
      }
      else if (OrderExeStatType.EXECGATHER.toInt() == stattype.intValue()) {
        // 采购订单行执行汇总
        views = this.orderExecGather(value);
      }
    }
    DataSet dataset = this.getDataSet(views);
    return dataset;
  }

  private DataSet getDataSet(OrderExecDetailViewVO[] views) {
    DataViewMeta viewmeta =
        (DataViewMeta) new OrderExecDetailViewVO().getMetaData();
    MetaData meta = RptMetaDataUtil.getMetaData(viewmeta);
    String[] attrs = new OrderExecDetailViewVO().getAttributeNames();
    DataSet ds = RptMetaDataUtil.getDataSet(meta, views, attrs);
    return ds;
  }

  private OrderExecDetailViewVO getExecDomVONeedSetOnwayCode(
      List<OrderExecDetailViewVO> list, int fonstatus) {
    if (list == null) {
      return null;
    }

    OrderExecDetailViewVO view = null;
    for (int i = 0; i < list.size(); ++i) {
      view = list.get(i);
      if (OnwayStatus.STATUS_CONFIRM.toInt() == fonstatus) {
        if (null == view.getConfirm_nnum()) {
          return view;
        }
      }
      else if (OnwayStatus.STATUS_SENDOUT.toInt() == fonstatus) {
        if (null == view.getOutput_vbillcode()) {
          return view;
        }
      }
      else if (OnwayStatus.STATUS_SHIP.toInt() == fonstatus) {
        if (null == view.getLoad_vbillcode()) {
          return view;
        }
      }
      else if (OnwayStatus.STATUS_COMEIN.toInt() == fonstatus) {
        if (null == view.getCustom_vbillcode()) {
          return view;
        }
      }
      else if (OnwayStatus.STATUS_GETOUT.toInt() == fonstatus) {
        if (null == view.getOutcustom_vbillcode()) {
          return view;
        }
      }
    }
    view = new OrderExecDetailViewVO();
    list.add(view);
    return view;
  }

  private void getExecListAddArr(List<OrderExecDetailViewVO> list,
      List<ArriveViewVO> arrviewList) {
    if (null == arrviewList) {
      return;
    }
    OrderExecDetailViewVO view = list.get(0);

    for (int i = 0; i < arrviewList.size(); ++i) {
      if (i == 0) {
        this.setExecListArrData(view, arrviewList.get(0));
        continue;
      }
      OrderExecDetailViewVO newView = (OrderExecDetailViewVO) view.clone();
      this.setOrderValueNull(newView);
      this.setArrValueNull(newView);
      this.setExecListArrData(newView, arrviewList.get(i));
      list.add(newView);
    }
  }

  private void getExecListAddIc(List<OrderExecDetailViewVO> list,
      List<PurchaseInViewVO> icviewList) {
    if (null == icviewList) {
      return;
    }
    if (this.arrive) {
      MapList<String, PurchaseInViewVO> mapList =
          new MapList<String, PurchaseInViewVO>();
      MapList<String, PurchaseInViewVO> backMapList =
          new MapList<String, PurchaseInViewVO>();
      for (PurchaseInViewVO icview : icviewList) {
        UFBoolean bisback =
            (UFBoolean) icview
                .getAttributeValue(PurchaseInHeadVO.FREPLENISHFLAG);
        String csourcebid =
            (String) icview.getAttributeValue(MetaNameConst.CSOURCEBILLBID);
        String cfirstbid =
            (String) icview.getAttributeValue(MetaNameConst.CFIRSTBILLBID);
        if (UFBoolean.FALSE.equals(bisback)) {
          mapList.put(csourcebid + cfirstbid, icview);
        }
        else {
          backMapList.put(cfirstbid + cfirstbid, icview);
        }
      }

      List<OrderExecDetailViewVO> newList =
          new ArrayList<OrderExecDetailViewVO>();
      for (OrderExecDetailViewVO view : list) {
        String pk_arrive_b = view.getPk_arrive_b();
        String pk_order_b = view.getPk_order_b();
        if (null == pk_arrive_b) {
          String key = pk_order_b + pk_order_b;
          List<PurchaseInViewVO> icList = mapList.get(key);
          List<PurchaseInViewVO> backList = backMapList.get(key);
          if (null == icList && null == backList) {
            newList.add(view);
          }
          else {
            List<PurchaseInViewVO> allList = new ArrayList<PurchaseInViewVO>();
            if (icList != null) {
              allList.addAll(icList);
            }
            if (backList != null) {
              allList.addAll(backList);
            }
            for (int i = 0; i < allList.size(); ++i) {
              OrderExecDetailViewVO newView =
                  (OrderExecDetailViewVO) view.clone();
              if (i > 0) {
                this.setOrderValueNull(newView);
                this.setArrValueNull(newView);
              }
              PurchaseInViewVO icview = allList.get(i);
              this.setExecListIcData(newView, icview);
              newList.add(newView);
            }
          }
        }
        else {
          String arrvkey = pk_arrive_b + pk_order_b;

          List<PurchaseInViewVO> icList = mapList.get(arrvkey);
          if (null == icList) {
            newList.add(view);
          }
          else {
            for (int i = 0; i < icList.size(); ++i) {
              OrderExecDetailViewVO newView =
                  (OrderExecDetailViewVO) view.clone();
              if (i > 0) {
                this.setOrderValueNull(newView);
                this.setArrValueNull(newView);
              }
              PurchaseInViewVO icview = icList.get(i);
              this.setExecListIcData(newView, icview);
              newList.add(newView);
            }
          }
          String backkey = pk_order_b + pk_order_b;
          List<PurchaseInViewVO> backicList = backMapList.get(backkey);
          if (backicList != null) {
            for (PurchaseInViewVO backview : backicList) {
              OrderExecDetailViewVO newView =
                  (OrderExecDetailViewVO) view.clone();
              this.setOrderValueNull(newView);
              this.setAllArrValueNull(newView);
              this.setExecListIcData(newView, backview);
              newList.add(newView);
            }
            backMapList.remove(backkey);
          }
        }
      }
      list.clear();
      list.addAll(newList);
    }
    else {
      OrderExecDetailViewVO view = list.get(0);
      for (int i = 0; i < icviewList.size(); ++i) {
        if (i == 0) {
          this.setExecListIcData(view, icviewList.get(0));
          continue;
        }
        OrderExecDetailViewVO newView = (OrderExecDetailViewVO) view.clone();
        this.setOrderValueNull(newView);
        this.setICValueNull(newView);
        this.setExecListIcData(newView, icviewList.get(i));
        list.add(newView);
      }
    }
  }

  private void getExecListAddInvoice(List<OrderExecDetailViewVO> list,
      List<InvoiceViewVO> invoiceviewList) {
    if (null == invoiceviewList) {
      return;
    }
    if (this.icpurin) {
      MapList<String, InvoiceViewVO> mapList =
          new MapList<String, InvoiceViewVO>();
      for (InvoiceViewVO invoiceView : invoiceviewList) {
        String csourcebid =
            (String) invoiceView.getAttributeValue(InvoiceItemVO.CSOURCEBID);
        String cfirstbid =
            (String) invoiceView.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
        mapList.put(csourcebid + cfirstbid, invoiceView);
      }

      List<OrderExecDetailViewVO> newList =
          new ArrayList<OrderExecDetailViewVO>();
      for (OrderExecDetailViewVO view : list) {
        String pk_order_b = view.getPk_order_b();
        String pk_ic_b = view.getPk_ic_b();
        String key =
            pk_ic_b != null ? pk_ic_b + pk_order_b : pk_order_b + pk_order_b;
        List<InvoiceViewVO> invoiceList = mapList.get(key);
        if (null == invoiceList) {
          newList.add(view);
        }
        else {
          for (int i = 0; i < invoiceList.size(); ++i) {
            OrderExecDetailViewVO newView =
                (OrderExecDetailViewVO) view.clone();
            if (i > 0) {
              this.setOrderValueNull(newView);
              this.setICValueNull(newView);
            }
            InvoiceViewVO invoiceView = invoiceList.get(i);
            this.setExeclistInvoiceData(newView, invoiceView);
            newList.add(newView);
          }
        }
      }
      list.clear();
      list.addAll(newList);
    }
    else {
      OrderExecDetailViewVO view = list.get(0);
      for (int i = 0; i < invoiceviewList.size(); ++i) {
        if (i == 0) {
          this.setExeclistInvoiceData(view, invoiceviewList.get(0));
          continue;
        }
        OrderExecDetailViewVO newView = (OrderExecDetailViewVO) view.clone();
        this.setOrderValueNull(newView);
        this.setExeclistInvoiceData(newView, invoiceviewList.get(i));
        list.add(newView);
      }
    }

    if (0 == list.size()) {
      return;
    }

    List<String> invoiceBidList = new ArrayList<String>();
    for (OrderExecDetailViewVO view : list) {
      String pk_invoice_b = view.getPk_invoice_b();
      if (pk_invoice_b != null) {
        invoiceBidList.add(pk_invoice_b);
      }
    }

    if (invoiceBidList.size() == 0) {
      return;
    }
    String[] invoicebids =
        invoiceBidList.toArray(new String[invoiceBidList.size()]);
    Map<String, UFDouble> apMap =
        ArapServicesForPUUtil.getVerifyMnyByInvoiceBid(invoicebids);
    if (apMap == null) {
      return;
    }
    for (OrderExecDetailViewVO view : list) {
      String pk_invoice_b = view.getPk_invoice_b();
      if (pk_invoice_b != null) {
        UFDouble nacccancelinvmny = apMap.get(pk_invoice_b);
        view.setNacccancelinvmny(nacccancelinvmny);
      }
    }
  }

  private void getExecListAddStatus(List<OrderExecDetailViewVO> list,
      List<OrderExecDetailViewVO> statusList) {
    if (null == statusList) {
      return;
    }

    OrderExecDetailViewVO view = list.get(0);
    for (int i = 0; i < statusList.size(); ++i) {
      if (i == 0) {
        this.setExecListStatusData(view, statusList.get(0));
        continue;
      }
      OrderExecDetailViewVO newView = (OrderExecDetailViewVO) view.clone();
      this.setOrderValueNull(newView);
      this.setExecListStatusData(newView, statusList.get(i));
      list.add(newView);
    }
  }

  private MapList<String, ArriveViewVO> getExecListArrView(String bidsCond) {
    // 到货单号、 到货日期、到货主数量、退货主数量、质检主数量、到货赠品数量、合格品数量
    String[] arratts =
        new String[] {
          ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.DBILLDATE,
          ArriveHeaderVO.BISBACK, ArriveItemVO.NNUM,
          ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NPRESENTNUM,
          ArriveItemVO.NELIGNUM, ArriveItemVO.PK_ORDER_B,
          ArriveItemVO.PK_ARRIVEORDER_B
        };
    String bidsql = " and " + ArriveItemVO.PK_ORDER_B + bidsCond;
    EfficientViewQuery<ArriveViewVO> arrquery =
        new EfficientViewQuery<ArriveViewVO>(ArriveViewVO.class, arratts);
    ArriveViewVO[] arrviews = arrquery.query(bidsql);
    if (ArrayUtils.isEmpty(arrviews)) {
      return null;
    }
    MapList<String, ArriveViewVO> mapList = new MapList<String, ArriveViewVO>();
    for (ArriveViewVO arrview : arrviews) {
      mapList.put(arrview.getPk_order_b(), arrview);
    }
    return mapList;
  }

  private MapList<String, PurchaseInViewVO> getExecListICView(String bidsCond) {
    // 收货库存组织、 入库仓库、 入库单号、 入库日期、 入库物料编码、 入库物料名称、 入库主单位、 入库赠品数量、
    // 入库主数量、 入库单价、 入库金额、 退库主数量、
    String[] icatrs =
        new String[] {
          ICPubMetaNameConst.PK_ORG_V, ICPubMetaNameConst.PK_ORG,
          ICPubMetaNameConst.CWAREHOUSEID, ICPubMetaNameConst.VBILLCODE,
          ICPubMetaNameConst.DBILLDATE, ICPubMetaNameConst.CMATERIALVID,
          PurchaseInHeadVO.FREPLENISHFLAG, ICPubMetaNameConst.CMATERIALOID,
          ICPubMetaNameConst.CUNITID, PurchaseInBodyVO.FLARGESS,
          ICPubMetaNameConst.NNUM, PurchaseInBodyVO.NORIGPRICE,
          PurchaseInBodyVO.NORIGMNY, MetaNameConst.CSOURCEBILLBID,
          MetaNameConst.CFIRSTBILLBID, MetaNameConst.CGENERALBID
        };
    String bidsql = " and " + MetaNameConst.CFIRSTBILLBID + bidsCond;

    EfficientViewQuery<PurchaseInViewVO> arrquery =
        new EfficientViewQuery<PurchaseInViewVO>(PurchaseInViewVO.class, icatrs);
    PurchaseInViewVO[] icviews = arrquery.query(bidsql);
    if (ArrayUtils.isEmpty(icviews)) {
      return null;
    }

    MapList<String, PurchaseInViewVO> mapList =
        new MapList<String, PurchaseInViewVO>();
    for (PurchaseInViewVO icview : icviews) {
      mapList.put(
          (String) icview.getAttributeValue(MetaNameConst.CFIRSTBILLBID),
          icview);
    }
    return mapList;
  }

  private MapList<String, InvoiceViewVO> getExecListInvocieView(String bidsCond) {
    // 发票号、 发票日期、 发票币种、 发票主数量、 发票本币价税合计、 发票本币付款金额
    String[] invocieattrs =
        new String[] {
          InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.DBILLDATE,
          InvoiceHeaderVO.CORIGCURRENCYID, InvoiceItemVO.NNUM,
          InvoiceItemVO.NTAXMNY, InvoiceItemVO.PK_ORDER_B,
          InvoiceItemVO.CSOURCEBID, InvoiceItemVO.CFIRSTBID,
          InvoiceItemVO.PK_INVOICE_B
        };

    String bidsql = " and " + InvoiceItemVO.PK_ORDER_B + bidsCond;

    SqlBuilder invoicesql = new SqlBuilder();
    invoicesql.append("select " + InvoiceItemVO.PK_INVOICE_B);
    invoicesql.append(" from " + PUEntity.M25_B_TAB + " " + PUEntity.M25_B_TAB);
    invoicesql.append(" where ");
    invoicesql.append(PUEntity.M25_B_TAB + "." + InvoiceItemVO.DR, 0);
    invoicesql.append(bidsql);
    DataAccessUtils util = new DataAccessUtils();
    String[] invoicebids =
        util.query(invoicesql.toString()).toOneDimensionStringArray();
    Set<String> bidset = new HashSet<String>();
    if (!ArrayUtils.isEmpty(invoicebids)) {
      bidset.addAll(Arrays.asList(invoicebids));
    }

    String settlebidsql =
        " and " + PUEntity.SettleBill_B_TAB + "."
            + SettleBillItemVO.PK_STOCKORDER_B + bidsCond;
    SqlBuilder settlesql = new SqlBuilder();
    settlesql.append("select " + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_INVOICE_B);
    settlesql.append("," + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_STOCK_B);
    settlesql.append("," + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_STOCKORDER_B);
    settlesql.append(" from " + PUEntity.SettleBill_B_TAB + "  "
        + PUEntity.SettleBill_B_TAB);
    settlesql.append(" where ");
    settlesql.append(PUEntity.SettleBill_B_TAB + "." + SettleBillItemVO.DR, 0);
    settlesql.append(settlebidsql);
    settlesql.append(" and " + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.FROWTYPE,
        EnumMatchRowType.StockInvoiceMatch.toInteger());
    // settlesql.appendIDIsNotNull(" and " + PUEntity.SettleBill_B_TAB + "."
    // + SettleBillItemVO.PK_INVOICE_B);

    String[][] settelvalues =
        util.query(settlesql.toString()).toTwoDimensionStringArray();
    Map<String, String> stockMap = new HashMap<String, String>();
    Map<String, String> orderMap = new HashMap<String, String>();
    if (settelvalues != null && settelvalues.length > 0) {
      for (String[] settle : settelvalues) {
        String pk_invocie_b = settle[0];
        bidset.add(pk_invocie_b);
        stockMap.put(pk_invocie_b, settle[1]);
        orderMap.put(pk_invocie_b, settle[2]);
      }
    }

    if (bidset.size() == 0) {
      return null;
    }

    String[] allbids = bidset.toArray(new String[bidset.size()]);
    EfficientViewQuery<InvoiceViewVO> arrquery =
        new EfficientViewQuery<InvoiceViewVO>(InvoiceViewVO.class, invocieattrs);
    InvoiceViewVO[] invoiceviews = arrquery.query(allbids);

    if (ArrayUtils.isEmpty(invoiceviews)) {
      return null;
    }

    MapList<String, InvoiceViewVO> mapList =
        new MapList<String, InvoiceViewVO>();
    for (InvoiceViewVO icview : invoiceviews) {
      String pk_order_b =
          (String) icview.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
      if (pk_order_b != null) {
        mapList.put(pk_order_b, icview);
        continue;
      }
      String pk_invoice_b =
          (String) icview.getAttributeValue(InvoiceItemVO.PK_INVOICE_B);
      pk_order_b = orderMap.get(pk_invoice_b);
      icview.setAttributeValue(InvoiceItemVO.PK_ORDER_B, pk_order_b);
      String pk_stockbid = stockMap.get(pk_invoice_b);
      if (pk_stockbid != null) {
        icview.setAttributeValue(InvoiceItemVO.CSOURCEBID, pk_stockbid);
      }
      mapList.put(pk_order_b, icview);
    }

    return mapList;
  }

  private MapList<String, OrderExecDetailViewVO> getExecListStatus(
      String bidsCond) {
    // 确认主数量、 发货单号、 发货日期、 发货主数量、 装运单号、 装运日期、 报关单号
    // 报关日期、 出关单号、 出关日期
    String[] attrs =
        new String[] {
          StatusOnWayItemVO.PK_ORDER_B, StatusOnWayItemVO.PK_ORDER_BB,
          StatusOnWayItemVO.VBILLCODE, StatusOnWayItemVO.NONWAYNUM,
          StatusOnWayItemVO.DBILLDATE, StatusOnWayItemVO.FONWAYSTATUS
        };

    String cond = StatusOnWayItemVO.PK_ORDER_B + bidsCond;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + cond);
    sql.append(" and " + StatusOnWayItemVO.DR, 0);
    sql.append(" and " + StatusOnWayItemVO.ISOPERATED, UFBoolean.TRUE);

    int[] constatus =
        new int[] {
          OnwayStatus.STATUS_CONFIRM.toInt(),
          OnwayStatus.STATUS_COMEIN.toInt(), OnwayStatus.STATUS_GETOUT.toInt(),
          OnwayStatus.STATUS_OUTPUT.toInt(),
          OnwayStatus.STATUS_SENDOUT.toInt(), OnwayStatus.STATUS_SHIP.toInt()
        };
    sql.append(" and " + StatusOnWayItemVO.FONWAYSTATUS, constatus);

    VOQuery<StatusOnWayItemVO> query =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class, attrs);
    String orderby =
        " order by " + StatusOnWayItemVO.PK_ORDER_B + ","
            + StatusOnWayItemVO.FONWAYSTATUS + ","
            + StatusOnWayItemVO.DBILLDATE + "," + StatusOnWayItemVO.VBILLCODE;
    StatusOnWayItemVO[] vos = query.query(sql.toString(), orderby);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    MapList<String, OrderExecDetailViewVO> mapList =
        new MapList<String, OrderExecDetailViewVO>();

    for (StatusOnWayItemVO vo : vos) {
      this.setExecListStatusData(mapList, vo);
    }
    return mapList;
  }

  private String[] getOrderBids() {
    String sql = this.sqlview.getViewSql();
    DataAccessUtils util = new DataAccessUtils();
    String[] bids = util.query(sql.toString()).toOneDimensionStringArray();
    return bids;
  }

  private void getOrderExecGatherAddArr(String bidsCond,
      Map<String, OrderItemVO> itemMap,
      Map<String, OrderExecDetailViewVO> viewMap) {
    String bidsql =
        PUEntity.M23_B_TABLE + "." + ArriveItemVO.PK_ORDER_B + bidsCond;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(PUEntity.M23_B_TABLE + "." + ArriveItemVO.PK_ORDER_B + ",");
    sql.append(PUEntity.M23_B_TABLE + "." + ArriveItemVO.BPRESENT + ",");
    sql.append("sum(isnull(" + PUEntity.M23_B_TABLE + "." + ArriveItemVO.NNUM
        + ",0)),");
    sql.append("sum(isnull(" + PUEntity.M23_B_TABLE + "."
        + ArriveItemVO.NACCUMCHECKNUM + ",0)),");
    sql.append("sum(isnull(" + PUEntity.M23_B_TABLE + "."
        + ArriveItemVO.NPRESENTNUM + ",0)),");
    sql.append("sum(isnull(" + PUEntity.M23_B_TABLE + "."
        + ArriveItemVO.NELIGNUM + ",0))");
    sql.append(" from " + PUEntity.M23_B_TABLE + " " + PUEntity.M23_B_TABLE);
    sql.append(" inner join " + PUEntity.M23_H_TABLE + " "
        + PUEntity.M23_H_TABLE);
    sql.append(" on " + PUEntity.M23_H_TABLE + "."
        + ArriveHeaderVO.PK_ARRIVEORDER);
    sql.append(" = " + PUEntity.M23_B_TABLE + "." + ArriveItemVO.PK_ARRIVEORDER);
    sql.append(" where ");
    sql.append(bidsql);
    sql.append(" and " + PUEntity.M23_B_TABLE + "." + ArriveItemVO.DR, 0);
    // 因为没有到货无法退货，所以这个条件能满足要求
    sql.append(" and " + PUEntity.M23_H_TABLE + "." + ArriveHeaderVO.BISBACK,
        UFBoolean.FALSE);
    sql.append(" group by " + PUEntity.M23_B_TABLE + "."
        + ArriveItemVO.PK_ORDER_B);
    sql.append("," + PUEntity.M23_B_TABLE + "." + ArriveItemVO.BPRESENT);

    DataAccessUtils util = new DataAccessUtils();
    String[][] values = util.query(sql.toString()).toTwoDimensionStringArray();
    if (0 == values.length) {
      return;
    }

    for (String[] value : values) {
      String pk_order_b = value[0];
      OrderExecDetailViewVO view = viewMap.get(pk_order_b);
      OrderItemVO itemVO = itemMap.get(pk_order_b);
      view.setNbackarrvnum(itemVO.getNbackarrvnum());

      UFDouble naccumchecknum = new UFDouble(value[3]);
      view.setQc_nnum(MathTool.add(naccumchecknum, view.getQc_nnum()));

      UFDouble naccuarrvnum = new UFDouble(value[2]);
      UFDouble npresentnum = new UFDouble(value[4]);
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(value[1]))) {
        view.setArrive_blargessnum(MathTool.add(npresentnum,
            view.getArrive_blargessnum()));
        // view.setArrive_nnum(null);
      }
      else {
        view.setArrive_nnum(MathTool.add(naccuarrvnum, view.getArrive_nnum()));
      }
      UFDouble nelignum = new UFDouble(value[5]);
      view.setNelignum(MathTool.add(nelignum, view.getNelignum()));
    }
  }

  @SuppressWarnings("deprecation")
  private void getOrderExecGatherAddIC(String bidsCond,
      Map<String, OrderItemVO> itemMap,
      Map<String, OrderExecDetailViewVO> viewMap) {
    String m45_b = new PurchaseInBodyVO().getTableName();
    String m45_h = new PurchaseInHeadVO().getTableName();
    String bidsql = m45_b + "." + MetaNameConst.CFIRSTBILLBID + bidsCond;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(m45_b + "." + MetaNameConst.CFIRSTBILLBID + ",");
    sql.append(m45_b + "." + PurchaseInBodyVO.FLARGESS + ",");
    sql.append("sum(isnull(" + ICPubMetaNameConst.NNUM + ",0)),");
    sql.append("sum(isnull(" + PurchaseInBodyVO.NORIGMNY + ",0))");
    sql.append(" from " + m45_b + " " + m45_b);
    sql.append(" inner join " + m45_h + " " + m45_h);
    sql.append(" on " + m45_h + "." + MetaNameConst.CGENERALHID);
    sql.append(" = " + m45_b + "." + MetaNameConst.CGENERALHID);
    sql.append(" where ");
    sql.append(bidsql);
    sql.append(" and " + m45_b + ".dr", 0);
    // 入库才能退库，所以该条件能满足条件
    sql.append(" and " + m45_h + "." + PurchaseInHeadVO.FREPLENISHFLAG,
        UFBoolean.FALSE);
    sql.append(" group by ");
    sql.append(m45_b + "." + MetaNameConst.CFIRSTBILLBID + ",");
    sql.append(m45_b + "." + PurchaseInBodyVO.FLARGESS);

    DataAccessUtils util = new DataAccessUtils();
    String[][] values = util.query(sql.toString()).toTwoDimensionStringArray();
    if (0 == values.length) {
      return;
    }

    for (String[] value : values) {
      String pk_order_b = value[0];
      OrderExecDetailViewVO view = viewMap.get(pk_order_b);
      OrderItemVO itemVO = itemMap.get(pk_order_b);

      view.setArrvstoorg_v(itemVO.getPk_arrvstoorg_v());
      view.setArrvstoorg(itemVO.getPk_arrvstoorg());

      view.setNbackstorenum(itemVO.getNbackstorenum());
      // 为设置精度需要加上单位
      view.setIc_cunitid(itemVO.getCunitid());
      UFDouble nnum = new UFDouble(value[2]);
      UFDouble accicnnum = MathTool.add(nnum, view.getIc_nnum());
      if (UFBoolean.valueOf(value[1]).booleanValue()) {
        view.setIc_blargessnum(MathTool.add(view.getIc_blargessnum(), nnum));
        // view.setIc_nnum(null);
      }
      else {
        view.setIc_nnum(accicnnum);
      }
      UFDouble ic_norigmny =
          MathTool.add(new UFDouble(value[3]), view.getIc_norigmny());
      view.setIc_norigmny(ic_norigmny);
      UFDouble ic_norigprice = UFDouble.ZERO_DBL;
      if (MathTool.compareTo(accicnnum, UFDouble.ZERO_DBL) > 0) {
        ic_norigprice = ic_norigmny.div(accicnnum);
      }
      view.setIc_norigprice(ic_norigprice);
    }
  }

  private void getOrderExecGatherAddInvoice(Map<String, OrderItemVO> itemMap,
      Map<String, OrderExecDetailViewVO> viewMap) {
    for (Entry<String, OrderExecDetailViewVO> entry : viewMap.entrySet()) {
      String key = entry.getKey();
      OrderExecDetailViewVO view = entry.getValue();
      OrderItemVO itemVO = itemMap.get(key);
      view.setInvoice_nnum(itemVO.getNaccuminvoicenum());
      view.setInvoice_ntaxmny(itemVO.getNaccuminvoicemny());
      view.setNacccancelinvmny(itemVO.getNacccancelinvmny());
    }
  }

  private void getOrderExecGatherAddStatus(String bidsCond,
      Map<String, OrderExecDetailViewVO> viewMap) {
    String bidsql = StatusOnWayItemVO.PK_ORDER_B + bidsCond;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(StatusOnWayItemVO.PK_ORDER_B + ",");
    sql.append(StatusOnWayItemVO.FONWAYSTATUS + ",");
    sql.append("sum(" + StatusOnWayItemVO.NONWAYNUM + ")");
    sql.append(" from " + PUEntity.M21_BB_TABLE);
    sql.append(" where ");
    sql.append(bidsql);
    sql.append(" and " + StatusOnWayItemVO.DR, 0);
    sql.append(" and " + StatusOnWayItemVO.ISOPERATED, UFBoolean.TRUE);
    int[] constatus = new int[] {
      OnwayStatus.STATUS_CONFIRM.toInt(), OnwayStatus.STATUS_SENDOUT.toInt()
    };
    sql.append(" and " + StatusOnWayItemVO.FONWAYSTATUS, constatus);
    sql.append(" group by " + StatusOnWayItemVO.PK_ORDER_B + ","
        + StatusOnWayItemVO.FONWAYSTATUS);

    DataAccessUtils util = new DataAccessUtils();
    String[][] values = util.query(sql.toString()).toTwoDimensionStringArray();
    if (0 == values.length) {
      return;
    }

    for (String[] value : values) {
      String pk_order_b = value[0];
      OrderExecDetailViewVO view = viewMap.get(pk_order_b);
      String onway = value[1];
      if (null == onway) {
        continue;
      }
      int fonwaystatus = Integer.valueOf(onway).intValue();
      UFDouble onwaynum = new UFDouble(value[2]);
      if (OnwayStatus.STATUS_CONFIRM.toInt() == fonwaystatus) {
        view.setConfirm_nnum(onwaynum);
      }
      else if (OnwayStatus.STATUS_SENDOUT.toInt() == fonwaystatus) {
        view.setOutput_nnum(onwaynum);
      }
    }
  }

  private Map<String, OrderItemVO> getOrderItemMap(String[] bids) {
    String[] attrs =
        new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.NACCUMARRVNUM,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NACCUMSTORENUM,
          OrderItemVO.NBACKSTORENUM, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCCANCELINVMNY,
          OrderItemVO.PK_ARRVSTOORG, OrderItemVO.PK_ARRVSTOORG_V,
          OrderItemVO.CUNITID
        };
    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, attrs);
    OrderItemVO[] orderItemVOs = query.query(bids);
    Map<String, OrderItemVO> map = new HashMap<String, OrderItemVO>();
    for (OrderItemVO itemVO : orderItemVOs) {
      map.put(itemVO.getPk_order_b(), itemVO);
    }
    return map;
  }

  private void init(String value) {
    if (null == value) {
      return;
    }

    this.status =
        value.indexOf(OrderExeStatContent.STATUS.toInteger().toString()) >= 0;
    this.arrive =
        value.indexOf(OrderExeStatContent.PUARRIVE.toInteger().toString()) >= 0;
    this.icpurin =
        value.indexOf(OrderExeStatContent.ICPURIN.toInteger().toString()) >= 0;
    this.invoice =
        value.indexOf(OrderExeStatContent.PUINVOICE.toInteger().toString()) >= 0;
  }

  private OrderExecDetailViewVO[] orderExecGather(String value) {
    String[] bids = this.getOrderBids();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }

    ViewQuery<OrderExecDetailViewVO> query =
        new ViewQuery<OrderExecDetailViewVO>(OrderExecDetailViewVO.class);
    OrderExecDetailViewVO[] views = query.query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    this.setMatClass(views);
    if (null == value) {
      return views;
    }
    return this.orderExecGatherByContents(bids, views);
  }

  private OrderExecDetailViewVO[] orderExecGatherByContents(String[] bids,
      OrderExecDetailViewVO[] views) {
    Map<String, OrderItemVO> itemMap = this.getOrderItemMap(bids);
    Map<String, OrderExecDetailViewVO> viewMap =
        new HashMap<String, OrderExecDetailViewVO>();
    for (OrderExecDetailViewVO view : views) {
      viewMap.put(view.getPk_order_b(), view);
    }
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_01.name());
    String bidsCond = builder.buildSQL(" ", bids);
    if (this.status) {
      this.getOrderExecGatherAddStatus(bidsCond, viewMap);
    }
    if (this.arrive) {
      this.getOrderExecGatherAddArr(bidsCond, itemMap, viewMap);
    }
    if (this.icpurin) {
      this.getOrderExecGatherAddIC(bidsCond, itemMap, viewMap);
    }
    if (this.invoice) {
      this.getOrderExecGatherAddInvoice(itemMap, viewMap);
    }

    // 这样会有效率问题，想其他办法
    List<OrderExecDetailViewVO> list = new ArrayList<OrderExecDetailViewVO>();
    for (String bid : bids) {
      list.add(viewMap.get(bid));
    }
    return list.toArray(new OrderExecDetailViewVO[list.size()]);
  }

  private OrderExecDetailViewVO[] orderExecList(String value) {
    String[] bids = this.getOrderBids();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }

    ViewQuery<OrderExecDetailViewVO> query =
        new ViewQuery<OrderExecDetailViewVO>(OrderExecDetailViewVO.class);
    OrderExecDetailViewVO[] views = query.query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    this.setMatClass(views);

    if (null == value) {
      return views;
    }

    return this.orderExecListByContents(bids, views);
  }

  private OrderExecDetailViewVO[] orderExecListByContents(String[] bids,
      OrderExecDetailViewVO[] views) {

    IDExQueryBuilder idBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_02.name());
    String bidsCond = idBuilder.buildSQL(" ", bids);
    MapList<String, OrderExecDetailViewVO> statusMap = null;
    if (this.status) {
      statusMap = this.getExecListStatus(bidsCond);
    }

    MapList<String, ArriveViewVO> arrMap = null;
    if (this.arrive) {
      arrMap = this.getExecListArrView(bidsCond);
    }

    MapList<String, PurchaseInViewVO> icMap = null;
    if (this.icpurin) {
      icMap = this.getExecListICView(bidsCond);
    }
    MapList<String, InvoiceViewVO> invoiceMap = null;
    if (this.invoice) {
      invoiceMap = this.getExecListInvocieView(bidsCond);
    }

    return this.orderExecListCombine(bids, views, statusMap, arrMap, icMap,
        invoiceMap);
  }

  private OrderExecDetailViewVO[] orderExecListCombine(String[] bids,
      OrderExecDetailViewVO[] views,
      MapList<String, OrderExecDetailViewVO> statusMap,
      MapList<String, ArriveViewVO> arrMap,
      MapList<String, PurchaseInViewVO> icMap,
      MapList<String, InvoiceViewVO> invoiceMap) {

    MapList<String, OrderExecDetailViewVO> mapList =
        new MapList<String, OrderExecDetailViewVO>();

    for (OrderExecDetailViewVO view : views) {
      String pk_order_b = view.getPk_order_b();
      List<OrderExecDetailViewVO> list = new ArrayList<OrderExecDetailViewVO>();
      list.add(view);
      if (statusMap != null) {
        List<OrderExecDetailViewVO> statusList = statusMap.get(pk_order_b);
        this.getExecListAddStatus(list, statusList);
      }
      if (arrMap != null) {
        List<ArriveViewVO> arrviewList = arrMap.get(pk_order_b);
        this.getExecListAddArr(list, arrviewList);
      }

      if (icMap != null) {
        List<PurchaseInViewVO> icviewList = icMap.get(pk_order_b);
        this.getExecListAddIc(list, icviewList);
      }

      if (invoiceMap != null) {
        List<InvoiceViewVO> invoiceList = invoiceMap.get(pk_order_b);
        this.getExecListAddInvoice(list, invoiceList);
      }

      mapList.putAll(pk_order_b, list);
    }

    // 效率问题
    List<OrderExecDetailViewVO> list = new ArrayList<OrderExecDetailViewVO>();
    for (String bid : bids) {
      list.addAll(mapList.get(bid));
    }

    OrderExecDetailViewVO[] queryviews =
        list.toArray(new OrderExecDetailViewVO[list.size()]);
    return queryviews;
  }

  private void setAllArrValueNull(OrderExecDetailViewVO newView) {
    newView.setConfirm_nnum(null);
    newView.setOutput_vbillcode(null);
    newView.setOutput_nnum(null);
    newView.setOutput_dbilldate(null);
    newView.setLoad_dbilldate(null);
    newView.setLoad_vbillcode(null);
    newView.setCustom_dbilldate(null);
    newView.setCustom_vbillcode(null);
    newView.setOutcustom_dbilldate(null);
    newView.setOutcustom_vbillcode(null);
    String pk_arrive_b = newView.getPk_arrive_b();
    if (null == pk_arrive_b) {
      return;
    }
    newView.setArrive_vbillcode(null);
    newView.setArrive_dbilldate(null);
    newView.setArrive_blargessnum(null);
    newView.setArrive_nnum(null);
    newView.setNbackarrvnum(null);
    newView.setQc_nnum(null);
    newView.setNelignum(null);
  }

  private void setArrValueNull(OrderExecDetailViewVO newView) {
    String pk_arrive_b = newView.getPk_arrive_b();
    if (null == pk_arrive_b) {
      return;
    }
    newView.setArrive_blargessnum(null);
    newView.setArrive_nnum(null);
    newView.setNbackarrvnum(null);
    newView.setQc_nnum(null);
    newView.setNelignum(null);
  }

  private void setExecListArrData(OrderExecDetailViewVO view,
      ArriveViewVO arrview) {
    // 到货单号、 到货日期、到货主数量、退货主数量、质检主数量、到货赠品数量、合格品数量
    view.setArrive_vbillcode(arrview.getVbillcode());
    view.setArrive_dbilldate(arrview.getDbilldate());
    UFDouble nnum = arrview.getNnum();
    if (UFBoolean.TRUE.equals(arrview.getBisback())) {
      nnum = MathTool.oppose(nnum);
      view.setNbackarrvnum(nnum);
      view.setArrive_nnum(null);
    }
    else {
      view.setArrive_nnum(nnum);
      view.setNbackarrvnum(null);
    }
    view.setQc_nnum(arrview.getNaccumchecknum());
    UFDouble npresentnum = arrview.getNpresentnum();
    if (MathTool.compareTo(npresentnum, UFDouble.ZERO_DBL) > 0) {
      view.setArrive_blargessnum(npresentnum);
      view.setArrive_nnum(null);
    }
    view.setNelignum(arrview.getNelignum());
    view.setPk_arrive_b(arrview.getPk_arriveorder_b());
  }

  private void setExecListIcData(OrderExecDetailViewVO view,
      PurchaseInViewVO icview) {
    // 收货库存组织、 入库仓库、 入库单号、 入库日期、 入库物料编码、 入库物料名称、 入库主单位、 入库赠品数量、
    // 入库主数量、 入库单价、 入库金额、 退库主数量
    view.setArrvstoorg((String) icview
        .getAttributeValue(ICPubMetaNameConst.PK_ORG));
    view.setArrvstoorg_v((String) icview
        .getAttributeValue(ICPubMetaNameConst.PK_ORG_V));
    view.setCwarehouseid((String) icview
        .getAttributeValue(ICPubMetaNameConst.CWAREHOUSEID));
    view.setIc_vbillcode((String) icview
        .getAttributeValue(ICPubMetaNameConst.VBILLCODE));
    view.setIc_dbilldate((UFDate) icview
        .getAttributeValue(ICPubMetaNameConst.DBILLDATE));
    view.setIc_cmaterialvid((String) icview
        .getAttributeValue(ICPubMetaNameConst.CMATERIALVID));
    view.setIc_cmaterialoid((String) icview
        .getAttributeValue(ICPubMetaNameConst.CMATERIALOID));
    view.setIc_cunitid((String) icview
        .getAttributeValue(ICPubMetaNameConst.CUNITID));
    UFDouble nnum =
        (UFDouble) icview.getAttributeValue(ICPubMetaNameConst.NNUM);
    if (UFBoolean.TRUE.equals(icview
        .getAttributeValue(PurchaseInHeadVO.FREPLENISHFLAG))) {
      nnum = MathTool.oppose(nnum);
      view.setNbackstorenum(nnum);
      view.setIc_nnum(null);
    }
    else {
      if (UFBoolean.TRUE.equals(icview
          .getAttributeValue(PurchaseInBodyVO.FLARGESS))) {
        view.setIc_blargessnum(nnum);
      }
      else {
        view.setIc_nnum(nnum);
        view.setNbackstorenum(null);
      }
    }

    view.setIc_norigprice((UFDouble) icview
        .getAttributeValue(PurchaseInBodyVO.NORIGPRICE));
    view.setIc_norigmny((UFDouble) icview
        .getAttributeValue(PurchaseInBodyVO.NORIGMNY));
    view.setPk_ic_b((String) icview
        .getAttributeValue(MetaNameConst.CGENERALBID));
  }

  private void setExeclistInvoiceData(OrderExecDetailViewVO view,
      InvoiceViewVO invoiceView) {
    // 发票号、发票日期、 发票币种、 发票主数量、 发票本币价税合计、 发票本币付款金额
    view.setInvoice_vbillcode((String) invoiceView
        .getAttributeValue(InvoiceHeaderVO.VBILLCODE));
    view.setInvoice_dbilldate((UFDate) invoiceView
        .getAttributeValue(InvoiceHeaderVO.DBILLDATE));
    view.setInvoice_corigcurrencyid((String) invoiceView
        .getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID));
    view.setInvoice_nnum((UFDouble) invoiceView
        .getAttributeValue(InvoiceItemVO.NNUM));
    view.setInvoice_ntaxmny((UFDouble) invoiceView
        .getAttributeValue(InvoiceItemVO.NTAXMNY));
    view.setPk_invoice_b((String) invoiceView
        .getAttributeValue(InvoiceItemVO.PK_INVOICE_B));
  }

  private void setExecListStatusData(
      MapList<String, OrderExecDetailViewVO> mapList, StatusOnWayItemVO statusVO) {
    String pk_order_b = statusVO.getPk_order_b();
    List<OrderExecDetailViewVO> list = mapList.get(pk_order_b);
    if (null == list) {
      list = new ArrayList<OrderExecDetailViewVO>();
      list.add(new OrderExecDetailViewVO());
      mapList.putAll(pk_order_b, list);
    }

    Integer onway = statusVO.getFonwaystatus();
    int fonstatus = onway != null ? onway.intValue() : -1;

    OrderExecDetailViewVO view =
        this.getExecDomVONeedSetOnwayCode(list, fonstatus);
    if (OnwayStatus.STATUS_CONFIRM.toInt() == fonstatus) {
      view.setConfirm_nnum(statusVO.getNonwaynum());
    }
    else if (OnwayStatus.STATUS_SENDOUT.toInt() == fonstatus) {
      view.setOutput_vbillcode(statusVO.getVbillcode());
      view.setOutput_nnum(statusVO.getNonwaynum());
      view.setOutput_dbilldate(statusVO.getDbilldate());
    }
    else if (OnwayStatus.STATUS_SHIP.toInt() == fonstatus) {
      view.setLoad_dbilldate(statusVO.getDbilldate());
      view.setLoad_vbillcode(statusVO.getVbillcode());
    }
    else if (OnwayStatus.STATUS_COMEIN.toInt() == fonstatus) {
      view.setCustom_dbilldate(statusVO.getDbilldate());
      view.setCustom_vbillcode(statusVO.getVbillcode());
    }
    else if (OnwayStatus.STATUS_GETOUT.toInt() == fonstatus) {
      view.setOutcustom_dbilldate(statusVO.getDbilldate());
      view.setOutcustom_vbillcode(statusVO.getVbillcode());
    }
  }

  private void setExecListStatusData(OrderExecDetailViewVO view,
      OrderExecDetailViewVO statusVO) {
    // 确认主数量、 发货单号、 发货日期、 发货主数量、 装运单号、 装运日期、 报关单号
    // 报关日期、 出关单号、 出关日期
    view.setConfirm_nnum(statusVO.getConfirm_nnum());
    view.setOutput_vbillcode(statusVO.getOutput_vbillcode());
    view.setOutput_nnum(statusVO.getOutput_nnum());
    view.setOutput_dbilldate(statusVO.getOutput_dbilldate());
    view.setLoad_dbilldate(statusVO.getLoad_dbilldate());
    view.setLoad_vbillcode(statusVO.getLoad_vbillcode());
    view.setCustom_dbilldate(statusVO.getCustom_dbilldate());
    view.setCustom_vbillcode(statusVO.getCustom_vbillcode());
    view.setOutcustom_dbilldate(statusVO.getOutcustom_dbilldate());
    view.setOutcustom_vbillcode(statusVO.getOutcustom_vbillcode());
  }

  private void setICValueNull(OrderExecDetailViewVO newView) {
    newView.setIc_blargessnum(null);
    newView.setIc_nnum(null);
    newView.setIc_norigmny(null);
    newView.setNbackstorenum(null);
  }

  private void setMatClass(OrderExecDetailViewVO[] views) {
    Set<String> set = new HashSet<String>();
    for (OrderExecDetailViewVO view : views) {
      String pk_material =
          (String) view.getAttributeValue(OrderItemVO.PK_MATERIAL);
      set.add(pk_material);
    }
    String[] mats = set.toArray(new String[set.size()]);
    Map<String, String> map = MaterialPubService.queryMaterialBaseClassPk(mats);
    for (OrderExecDetailViewVO view : views) {
      String pk_material =
          (String) view.getAttributeValue(OrderItemVO.PK_MATERIAL);
      view.setPk_marbasclass(map.get(pk_material));
    }
  }

  private void setOrderValueNull(OrderExecDetailViewVO newView) {
    newView.setAttributeValue(OrderItemVO.NNUM, null);
    newView.setAttributeValue(OrderItemVO.NORIGMNY, null);
    newView.setAttributeValue(OrderItemVO.NORIGTAXMNY, null);
    // newView.setAttributeValue(OrderItemVO.NORIGTAX, null);
    newView.setAttributeValue(OrderItemVO.NMNY, null);
    newView.setAttributeValue(OrderItemVO.NTAXMNY, null);
    newView.setAttributeValue(OrderItemVO.NTAX, null);
  }
}

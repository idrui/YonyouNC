package nc.bs.pu.report.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.MetaData;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.report.enumeration.ReceivePlanEnum;
import nc.vo.pu.report.queryinfo.order.OrderReceivePlanQueryView;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.view.order.ReceivePlanRptViewVO;
import nc.vo.pu.report.view.order.RptMetaDataUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-9-19 上午08:57:27
 * @author wuxla
 */

public class OrderReceivePlanRptBP {

  private OrderReceivePlanQueryView sqlview;

  public DataSet getReceivePlanDataSet(SmartContext context) {
    ConditionVO[] conds =
        (ConditionVO[]) context.getAttribute(PUOrderQryInfoPara.RP_COND);
    this.sqlview = new OrderReceivePlanQueryView(conds, context);
    Integer rpgroupcond =
        (Integer) context.getAttribute(PUOrderQryInfoPara.RP_GROUPCONDITION);
    UFBoolean bfinishrecv = null;
    Object obj = context.getAttribute(PUOrderQryInfoPara.RP_FINISHRECV);
    if (obj instanceof UFBoolean) {
      bfinishrecv = (UFBoolean) obj;
    }
    ReceivePlanRptViewVO[] views = null;
    if (rpgroupcond != null) {
      // 到货计划明细
      if (ReceivePlanEnum.PLAN_DETAIL.toInt() == rpgroupcond.intValue()) {
        views = this.rpList(bfinishrecv);
      }
      else if (ReceivePlanEnum.PLAN_GATHER.toInt() == rpgroupcond.intValue()) {
        // 到货计划汇总
        views = this.rpGather(bfinishrecv);
      }
    }
    DataSet dataset = this.getDataSet(views);
    return dataset;
  }

  /**
   * 到货单号
   * 到货日期
   * 到货单行号
   * 到货主数量
   */
  private MapList<String, ArriveViewVO> getArrMap(String bidsCond) {
    String[] attrs =
        new String[] {
          ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.DBILLDATE,
          ArriveItemVO.CROWNO, ArriveItemVO.NNUM, ArriveItemVO.PK_ORDER_BB1,
          ArriveItemVO.PK_ARRIVEORDER_B
        };
    String bidsql = " and " + ArriveItemVO.PK_ORDER_BB1 + bidsCond;
    EfficientViewQuery<ArriveViewVO> arrquery =
        new EfficientViewQuery<ArriveViewVO>(ArriveViewVO.class, attrs);
    ArriveViewVO[] arrviews = arrquery.query(bidsql);
    if (ArrayUtils.isEmpty(arrviews)) {
      return null;
    }
    MapList<String, ArriveViewVO> mapList = new MapList<String, ArriveViewVO>();
    for (ArriveViewVO arrview : arrviews) {
      String pk_order_bb1 =
          (String) arrview.getAttributeValue(ArriveItemVO.PK_ORDER_BB1);
      mapList.put(pk_order_bb1, arrview);
    }
    return mapList;
  }

  private DataSet getDataSet(ReceivePlanRptViewVO[] views) {
    DataViewMeta viewmeta =
        (DataViewMeta) new ReceivePlanRptViewVO().getMetaData();
    MetaData meta = RptMetaDataUtil.getMetaData(viewmeta);
    String[] attrs = new ReceivePlanRptViewVO().getAttributeNames();
    DataSet ds = RptMetaDataUtil.getDataSet(meta, views, attrs);
    return ds;
  }

  private MapList<String, PurchaseInViewVO> getICMap(String bidsCond) {
    String[] attrs =
        new String[] {
          ICPubMetaNameConst.VBILLCODE, ICPubMetaNameConst.DBILLDATE,
          ICPubMetaNameConst.NNUM, ICPubMetaNameConst.CROWNO,
          PurchaseInBodyVO.CORDER_BB1ID, MetaNameConst.CSOURCEBILLBID,
          MetaNameConst.CFIRSTBILLBID
        };
    String bidsql = " and " + PurchaseInBodyVO.CORDER_BB1ID + bidsCond;
    EfficientViewQuery<PurchaseInViewVO> arrquery =
        new EfficientViewQuery<PurchaseInViewVO>(PurchaseInViewVO.class, attrs);
    PurchaseInViewVO[] icviews = arrquery.query(bidsql);
    if (ArrayUtils.isEmpty(icviews)) {
      return null;
    }
    MapList<String, PurchaseInViewVO> mapList =
        new MapList<String, PurchaseInViewVO>();
    for (PurchaseInViewVO arrview : icviews) {
      String pk_order_bb1 =
          (String) arrview.getAttributeValue(PurchaseInBodyVO.CORDER_BB1ID);
      mapList.put(pk_order_bb1, arrview);
    }
    return mapList;
  }

  private String[] getOrderBbids(UFBoolean bfinishrecv) {
    if (bfinishrecv != null) {
      this.sqlview.setFinishrecv(bfinishrecv);
    }
    String sql = this.sqlview.getViewSql();
    DataAccessUtils util = new DataAccessUtils();
    String[] bids = util.query(sql).toOneDimensionStringArray();
    return bids;
  }

  private void getRpListAddArr(List<ReceivePlanRptViewVO> list,
      List<ArriveViewVO> arrviewList) {
    if (null == arrviewList) {
      return;
    }

    ReceivePlanRptViewVO view = list.get(0);
    for (int i = 0; i < arrviewList.size(); ++i) {
      if (i == 0) {
        this.setRpListArrData(view, arrviewList.get(0));
        continue;
      }
      ReceivePlanRptViewVO newView = (ReceivePlanRptViewVO) view.clone();
      this.setOrderValueNull(newView);
      this.setArrValueNull(newView);
      this.setRpListArrData(newView, arrviewList.get(i));
      list.add(newView);
    }
  }

  private void getRpListAddIC(List<ReceivePlanRptViewVO> list,
      List<PurchaseInViewVO> icviewList) {
    if (null == icviewList) {
      return;
    }
    MapList<String, PurchaseInViewVO> mapList =
        new MapList<String, PurchaseInViewVO>();
    for (PurchaseInViewVO icview : icviewList) {
      String csourcebid =
          (String) icview.getAttributeValue(MetaNameConst.CSOURCEBILLBID);
      String cfirstbid =
          (String) icview.getAttributeValue(MetaNameConst.CFIRSTBILLBID);
      String cbbid =
          (String) icview.getAttributeValue(PurchaseInBodyVO.CORDER_BB1ID);
      mapList.put(cbbid + csourcebid + cfirstbid, icview);
    }

    List<ReceivePlanRptViewVO> newList = new ArrayList<ReceivePlanRptViewVO>();
    for (ReceivePlanRptViewVO view : list) {
      String pk_order_bb1 = view.getPk_order_bb1();
      String pk_order_b = view.getPk_order_b();
      String pk_arrive_b = view.getPk_arrive_b();
      String key =
          pk_arrive_b != null ? pk_order_bb1 + pk_arrive_b + pk_order_b
              : pk_order_bb1 + pk_order_b + pk_order_b;
      List<PurchaseInViewVO> icList = mapList.get(key);
      if (null == icList) {
        newList.add(view);
      }
      else {
        for (int i = 0; i < icList.size(); ++i) {
          ReceivePlanRptViewVO newView = (ReceivePlanRptViewVO) view.clone();
          if (i > 0) {
            this.setOrderValueNull(newView);
            this.setArrValueNull(newView);
          }
          PurchaseInViewVO icview = icList.get(i);
          this.setRpListICData(newView, icview);
          newList.add(newView);
        }

      }
    }
    list.clear();
    list.addAll(newList);
  }

  private ReceivePlanRptViewVO[] rpGather(UFBoolean bfinishrecv) {
    String[] bb1ids = this.getOrderBbids(bfinishrecv);
    if (ArrayUtils.isEmpty(bb1ids)) {
      return null;
    }

    ViewQuery<ReceivePlanRptViewVO> query =
        new ViewQuery<ReceivePlanRptViewVO>(ReceivePlanRptViewVO.class);
    ReceivePlanRptViewVO[] views = query.query(bb1ids);
    if (ArrayUtils.isEmpty(bb1ids)) {
      return null;
    }
    this.setRpGatherCodeAndAccNum(bb1ids, views);
    return views;
  }

  private ReceivePlanRptViewVO[] rpList(UFBoolean bfinishrecv) {
    String[] bb1ids = this.getOrderBbids(bfinishrecv);
    if (ArrayUtils.isEmpty(bb1ids)) {
      return null;
    }

    ViewQuery<ReceivePlanRptViewVO> query =
        new ViewQuery<ReceivePlanRptViewVO>(ReceivePlanRptViewVO.class);
    ReceivePlanRptViewVO[] views = query.query(bb1ids);
    if (ArrayUtils.isEmpty(bb1ids)) {
      return null;
    }

    this.setPlanCode(bb1ids, views);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_03.name());
    String bidsCond = builder.buildSQL(" ", bb1ids);
    MapList<String, ArriveViewVO> arrMap = this.getArrMap(bidsCond);
    MapList<String, PurchaseInViewVO> icMap = this.getICMap(bidsCond);
    return this.rpListCombine(views, arrMap, icMap);
  }

  private ReceivePlanRptViewVO[] rpListCombine(ReceivePlanRptViewVO[] views,
      MapList<String, ArriveViewVO> arrMap,
      MapList<String, PurchaseInViewVO> icMap) {
    MapList<String, ReceivePlanRptViewVO> mapList =
        new MapList<String, ReceivePlanRptViewVO>();
    for (ReceivePlanRptViewVO view : views) {
      String pk_order_bb1 = view.getPk_order_bb1();
      List<ReceivePlanRptViewVO> list = new ArrayList<ReceivePlanRptViewVO>();
      list.add(view);
      if (arrMap != null) {
        List<ArriveViewVO> arrviewList = arrMap.get(pk_order_bb1);
        this.getRpListAddArr(list, arrviewList);
      }

      if (icMap != null) {
        List<PurchaseInViewVO> icList = icMap.get(pk_order_bb1);
        this.getRpListAddIC(list, icList);
      }
      mapList.putAll(pk_order_bb1, list);
    }
    List<ReceivePlanRptViewVO> list = new ArrayList<ReceivePlanRptViewVO>();
    for (Entry<String, List<ReceivePlanRptViewVO>> entry : mapList.entrySet()) {
      list.addAll(entry.getValue());
    }
    return list.toArray(new ReceivePlanRptViewVO[list.size()]);
  }

  private void setArrValueNull(ReceivePlanRptViewVO newView) {
    newView.setArr_nnum(null);
  }

  private void setOrderValueNull(ReceivePlanRptViewVO newView) {
    newView.setAttributeValue(OrderReceivePlanVO.NNUM, null);
    newView.setAttributeValue(OrderReceivePlanVO.NASTNUM, null);
  }

  private void setPlanCode(String[] bb1ids, ReceivePlanRptViewVO[] views) {
    String[] attrs = new String[] {
      OrderReceivePlanVO.PK_ORDER_BB1, OrderReceivePlanVO.VBILLCODE
    };
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class, attrs);
    OrderReceivePlanVO[] vos = query.query(bb1ids);

    Map<String, String> map = new HashMap<String, String>();
    for (OrderReceivePlanVO vo : vos) {
      map.put(vo.getPk_order_bb1(), vo.getVbillcode());
    }

    for (ReceivePlanRptViewVO view : views) {
      view.setVplancode(map.get(view.getPk_order_bb1()));
    }
  }

  private void setRpGatherCodeAndAccNum(String[] bb1ids,
      ReceivePlanRptViewVO[] views) {
    String[] attrs =
        new String[] {
          OrderReceivePlanVO.PK_ORDER_BB1, OrderReceivePlanVO.VBILLCODE,
          OrderReceivePlanVO.NACCUMARRVNUM, OrderReceivePlanVO.NACCUMSTORENUM
        };
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class, attrs);
    OrderReceivePlanVO[] vos = query.query(bb1ids);

    Map<String, OrderReceivePlanVO> map =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO vo : vos) {
      map.put(vo.getPk_order_bb1(), vo);
    }

    for (ReceivePlanRptViewVO view : views) {
      OrderReceivePlanVO vo = map.get(view.getPk_order_bb1());
      if (null == vo) {
        continue;
      }
      view.setVplancode(vo.getVbillcode());
      view.setArr_nnum(vo.getNaccumarrvnum());
      view.setIc_nnum(vo.getNaccumstorenum());
    }
  }

  private void setRpListArrData(ReceivePlanRptViewVO view, ArriveViewVO arrView) {
    view.setArr_crowno((String) arrView.getAttributeValue(ArriveItemVO.CROWNO));
    view.setArr_dbilldate(arrView.getDbilldate());
    view.setArr_nnum(arrView.getNnum());
    view.setArr_vbillcode(arrView.getVbillcode());
    view.setPk_arrive_b(arrView.getPk_arriveorder_b());
  }

  private void setRpListICData(ReceivePlanRptViewVO view,
      PurchaseInViewVO icView) {
    view.setIc_crowno((String) icView
        .getAttributeValue(ICPubMetaNameConst.CROWNO));
    view.setIc_dbilldate((UFDate) icView
        .getAttributeValue(ICPubMetaNameConst.DBILLDATE));
    view.setIc_nnum((UFDouble) icView
        .getAttributeValue(ICPubMetaNameConst.NNUM));
    view.setIc_vbillcode((String) icView
        .getAttributeValue(ICPubMetaNameConst.VBILLCODE));
  }

}

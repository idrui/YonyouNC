package nc.bs.pu.m21.alert;

import java.util.Map;
import java.util.TimeZone;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pu.alert.PUAlertConst;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.alert.PuAlertUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 未到货完成及即将收货的订单预警类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>未到货完成及即将收货的订单预警
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-8 下午02:57:22
 */
public class OrderArrAlert implements IPreAlertPlugin {
  static class OrderArrAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = 8688423592276081121L;

    public OrderArrAlertDataSource(String[][] values) {
      super(values);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        OrderHeaderVO.VBILLCODE, OrderItemVO.CROWNO, OrderHeaderVO.CEMPLOYEEID,
        OrderHeaderVO.PK_SUPPLIER, OrderItemVO.PK_MATERIAL,
        OrderArrAlert.PK_MATERIAL_NAME, OrderItemVO.CUNITID, OrderItemVO.NNUM,
        OrderArrAlert.NNOTRECEIVENUM, OrderItemVO.DPLANARRVDATE,
        OrderArrAlert.DELAYDATE
      };
    }
  }

  /**
   * 逾期天数
   */
  public static final String DELAYDATE = "delaydate";

  /**
   * 未交货数量
   */
  public static final String NNOTRECEIVENUM = "nnotreceivenum";

  /**
   * 物料名称
   */
  public static final String PK_MATERIAL_NAME = "pk_material_name";

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {
    try {
      return this.getPreAlertObject(context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String[][] getData(String[] pk_orgs, int iDays, UFDate dLoginDate) {
    // 计划到货日期=5号，iDays=2；则应在5号前两天，即3号预警
    // 如果当前日期>=4号，则预警；否则不。
    String sAlertLastDate = dLoginDate.getDateAfter(iDays).toString();

    StringBuffer sql = new StringBuffer();
    // -------------未收货完全的订单行
    sql.append(" SELECT vbillcode, crowno, cemployeeid, po_order.pk_supplier, po_order_b.pk_material, po_order_b.pk_material,po_order_b.cunitid, ");
    sql.append("  po_order_b.nnum, ");
    sql.append(" CASE");
    sql.append("  WHEN barrive = 'Y' THEN po_order_b.nnum - coalesce(po_order_b.naccumarrvnum,0.0)");
    sql.append("  ELSE po_order_b.nnum - coalesce(po_order_b.naccumstorenum, 0.0) ");
    sql.append(" END as nnotreceivenum");
    sql.append(",po_order_b.dplanarrvdate, null delaydate");
    sql.append(" FROM po_potrantype,po_order,po_order_b");
    // 计划到货日期
    sql.append(" WHERE po_order.ctrantypeid = po_potrantype.ctrantypeid AND po_order_b.pk_order=po_order.pk_order ");

    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_48.name());
    sql.append(builder.buildSQL(" and po_order.pk_org", pk_orgs));

    sql.append(" AND (po_order_b.dplanarrvdate < '" + sAlertLastDate + "') ");
    // 未关闭
    sql.append(" AND " + OrderAlertConst.SQL_PO_HEAD + " AND "
        + OrderAlertConst.SQL_PO_BODY);
    sql.append(" AND " + OrderAlertConst.SQL_PO_BODY_ACTIVE);
    // 有到货环节，未完全到货；无到货环节，未完全入库
    sql.append(" AND (");
    sql.append(" (" + OrderAlertConst.SQL_PO_STATUS_ARRV_YES + " AND "
        + OrderAlertConst.SQL_PO_BODY_NOTFULLARRV + " )");
    sql.append(" OR (");
    sql.append("    (" + OrderAlertConst.SQL_PO_STATUS_ARRV_NO + " AND "
        + OrderAlertConst.SQL_PO_STATUS_STORE_YES + ")");
    sql.append("    AND " + OrderAlertConst.SQL_PO_BODY_NOTFULLSTORE + ") ");
    sql.append(" )");
    // 无到货计划
    sql.append(" AND " + OrderAlertConst.SQL_PO_BODY_RP_NO);
    // 业务类型不按到货计划收货
    sql.append(" AND " + OrderAlertConst.SQL_PO_STATUS_RP_NO);

    // 联结
    sql.append(" UNION ALL ");

    // -------------未收货完全的到货计划
    sql.append("SELECT po_order.vbillcode, crowno, cemployeeid, po_order.pk_supplier, po_order_b.pk_material,po_order_b.pk_material, po_order_b.cunitid, ");
    sql.append("  po_order_bb1.nnum, ");
    sql.append(" CASE");
    sql.append("  WHEN barrive = 'Y' THEN po_order_bb1.nnum - coalesce(po_order_bb1.naccumarrvnum,0.0)");
    sql.append("  ELSE po_order_bb1.nnum - coalesce(po_order_bb1.naccumstorenum, 0.0) ");
    sql.append(" END as nnotreceivenum");
    sql.append(",po_order_bb1.dplanarrvdate, null delaydate");
    sql.append(" FROM po_potrantype,po_order,po_order_bb1,po_order_b");
    sql.append(" WHERE po_order.ctrantypeid = po_potrantype.ctrantypeid AND po_order_bb1.pk_order = po_order.pk_order");

    IDExQueryBuilder builder2 =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_49.name());

    sql.append(builder2.buildSQL(" and po_order.pk_org", pk_orgs));

    sql.append(" AND po_order_bb1.pk_order_b = po_order_b.pk_order_b AND ");
    // 未关闭
    sql.append(OrderAlertConst.SQL_PO_HEAD + " AND "
        + OrderAlertConst.SQL_PO_BODY);
    sql.append(" AND " + OrderAlertConst.SQL_PO_BB1);
    // sql.append(" AND " + OrderPubVO.SQL_PO_BODY_ACTIVE);
    // 计划到货日期
    sql.append(" AND (po_order_bb1.dplanarrvdate < '" + sAlertLastDate + "') ");
    // 有到货环节，未完全到货；无到货环节，未完全入库
    sql.append(" AND (");
    sql.append(" (" + OrderAlertConst.SQL_PO_STATUS_ARRV_YES + " AND "
        + OrderAlertConst.SQL_PO_BODY_NOTFULLARRV + " AND "
        + OrderAlertConst.SQL_PO_BB1_NOTFULLARRV + " )");
    sql.append(" OR (");
    sql.append("    (" + OrderAlertConst.SQL_PO_STATUS_ARRV_NO + " AND "
        + OrderAlertConst.SQL_PO_STATUS_STORE_YES + ")");
    sql.append(" and " + OrderAlertConst.SQL_PO_BODY_NOTFULLSTORE);
    sql.append("  AND " + OrderAlertConst.SQL_PO_BB1_NOTFULLSTORE + ") ");
    sql.append(" )");
    // 订单有到货计划
    sql.append(" AND " + OrderAlertConst.SQL_PO_BODY_RP_YES);

    String[][] datas =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (String[] data : datas) {
      data[9] = new UFDate(data[9]).toStdString(zone);
      data[10] = String.valueOf(dLoginDate.getDaysAfter(new UFDate(data[9])));
    }
    return datas;
  }

  private String[][] getDatas(PreAlertContext context) {
    UFDate loginDate = AppContext.getInstance().getBusiDate();
    Object odays = context.getKeyMap().get(PUAlertConst.DAYS);
    String[] pk_orgs = context.getPk_orgs();

    if (ObjectUtil.isEmptyWithTrim(odays) || pk_orgs == null
        || pk_orgs.length == 0) {
      return null;
    }

    return this.getData(pk_orgs, Integer.parseInt(odays.toString()), loginDate);
  }

  private OrderArrAlertDataSource getDataSource(String[][] values) {
    OrderArrAlertDataSource datasource = new OrderArrAlertDataSource(values);
    if (values != null && values.length > 0) {
      Map<String, String[]> psnMap = PuAlertUtil.getPsnNameMap(values, 2);
      Map<String, String[]> supMap = PuAlertUtil.getSupNameMap(values, 3);
      Map<String, String[]> matMap = PuAlertUtil.getMaterialNameMap(values, 5);
      Map<String, String[]> measdocMap = PuAlertUtil.getMeasdocMap(values, 6);
      for (int j = 0; j < values.length; ++j) {
        for (int i = 0; i < values[j].length; ++i) {
          if (StringUtils.isEmpty(values[j][i])) {
            continue;
          }
          if (i == 2) {
            String[] names = psnMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderHeaderVO.CEMPLOYEEID, names[0], j);
              datasource.putData2(OrderHeaderVO.CEMPLOYEEID, names[1], j);
              datasource.putData3(OrderHeaderVO.CEMPLOYEEID, names[2], j);
              datasource.putData4(OrderHeaderVO.CEMPLOYEEID, names[3], j);
              datasource.putData5(OrderHeaderVO.CEMPLOYEEID, names[4], j);
              datasource.putData6(OrderHeaderVO.CEMPLOYEEID, names[5], j);
            }
          }
          if (i == 3) {
            String[] names = supMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderHeaderVO.PK_SUPPLIER, names[0], j);
              datasource.putData2(OrderHeaderVO.PK_SUPPLIER, names[1], j);
              datasource.putData3(OrderHeaderVO.PK_SUPPLIER, names[2], j);
              datasource.putData4(OrderHeaderVO.PK_SUPPLIER, names[3], j);
              datasource.putData5(OrderHeaderVO.PK_SUPPLIER, names[4], j);
              datasource.putData6(OrderHeaderVO.PK_SUPPLIER, names[5], j);
            }
          }
          if (i == 4) {
            String[] names = matMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderArrAlert.PK_MATERIAL_NAME, names[0], j);
              datasource.putData2(OrderArrAlert.PK_MATERIAL_NAME, names[1], j);
              datasource.putData3(OrderArrAlert.PK_MATERIAL_NAME, names[2], j);
              datasource.putData4(OrderArrAlert.PK_MATERIAL_NAME, names[3], j);
              datasource.putData5(OrderArrAlert.PK_MATERIAL_NAME, names[4], j);
              datasource.putData6(OrderArrAlert.PK_MATERIAL_NAME, names[5], j);
              datasource.putData(OrderItemVO.PK_MATERIAL, names[6], j);
            }
          }
          if (i == 6) {
            String[] names = measdocMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderItemVO.CUNITID, names[0], j);
              datasource.putData2(OrderItemVO.CUNITID, names[1], j);
              datasource.putData3(OrderItemVO.CUNITID, names[2], j);
              datasource.putData4(OrderItemVO.CUNITID, names[3], j);
              datasource.putData5(OrderItemVO.CUNITID, names[4], j);
              datasource.putData6(OrderItemVO.CUNITID, names[5], j);
            }
          }
        }
      }
    }

    return datasource;
  }

  private PreAlertObject getPreAlertObject(PreAlertContext context) {
    PreAlertObject retObj = new PreAlertObject();
    String[][] values = this.getDatas(context);
    if (null == values || values.length == 0) {
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    else {
      retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
      OrderArrAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }

}

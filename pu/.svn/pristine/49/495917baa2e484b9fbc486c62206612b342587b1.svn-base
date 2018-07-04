package nc.bs.pu.m21.alert;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.alert.PuAlertUtil;
import nc.vo.pu.pub.alert.PuAlertVfreeUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * 采购预警-截至当前日期前一天未收货完成的到货计划预警类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>截至当前日期前一天未收货完成的到货计划预警
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-8 下午02:57:22
 */
public class PoRPNotArrvAlert implements IPreAlertPlugin {

  static class PoRPNotArrvAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = 3705053911023866593L;

    public PoRPNotArrvAlertDataSource(String[][] values) {
      super(values);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_SUPPLIER, OrderItemVO.CROWNO,
        OrderItemVO.PK_MATERIAL, PoRPNotArrvAlert.PK_MATERIAL_NAME,
        PoRPNotArrvAlert.MATERIALSPEC, PoRPNotArrvAlert.MATERIALTYPE,
        OrderReceivePlanVO.VFREE1, OrderReceivePlanVO.VFREE2,
        OrderReceivePlanVO.VFREE3, OrderReceivePlanVO.VFREE4,
        OrderReceivePlanVO.VFREE5, OrderReceivePlanVO.VFREE6,
        OrderReceivePlanVO.VFREE7, OrderReceivePlanVO.VFREE8,
        OrderReceivePlanVO.VFREE9, OrderReceivePlanVO.VFREE10,
        OrderReceivePlanVO.VBATCHCODE, OrderReceivePlanVO.CASTUNITID,
        OrderReceivePlanVO.NASTNUM, OrderReceivePlanVO.VCHANGERATE,
        OrderReceivePlanVO.CUNITID, OrderReceivePlanVO.DPLANARRVDATE,
        OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.PK_RECVSTORDOC,
        OrderReceivePlanVO.NNUM, PoRPNotArrvAlert.NACCUMNUM,
        PoRPNotArrvAlert.NCANRECVNUM
      };
    }
  }

  /**
   * 规格
   */
  public static final String MATERIALSPEC = "materialspec";

  /**
   * 型号
   */
  public static final String MATERIALTYPE = "materialtype";

  /**
   * 已收货主数量
   */
  public static final String NACCUMNUM = "naccumnum";

  /**
   * 未收货主数量
   */
  public static final String NCANRECVNUM = "ncanrecvnum";

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

  private String[][] getData(String[] pk_orgs, UFDate dLoginDate) {
    String sLoginDate = dLoginDate.toString();

    StringBuffer sql = new StringBuffer();
    sql.append("SELECT po_order.vbillcode,po_order.pk_supplier,po_order_b.crowno,po_order_b.pk_material,null,null,null,");
    sql.append(" po_order_bb1.vfree1,po_order_bb1.vfree2,po_order_bb1.vfree3,po_order_bb1.vfree4,po_order_bb1.vfree5,");
    sql.append(" po_order_bb1.vfree6,po_order_bb1.vfree7,po_order_bb1.vfree8,po_order_bb1.vfree9,po_order_bb1.vfree10,");
    sql.append(" po_order_bb1.vbatchcode,po_order_bb1.castunitid,po_order_bb1.nastnum,po_order_bb1.vchangerate,");
    sql.append("po_order_bb1.cunitid,po_order_bb1.dplanarrvdate,po_order_bb1.pk_arrvstoorg,po_order_bb1.pk_recvstordoc,po_order_bb1.nnum,");
    sql.append(" po_order_bb1.naccumarrvnum,po_order_bb1.naccumstorenum,po_potrantype.barrive ");
    sql.append(" from po_order_bb1 ");
    sql.append(" inner join po_order_b on po_order_b.pk_order_b=po_order_bb1.pk_order_b ");
    sql.append(" inner join po_order on po_order.pk_order=po_order_b.pk_order ");
    sql.append(" inner join po_potrantype on po_order.ctrantypeid=po_potrantype.ctrantypeid");
    sql.append(" WHERE ");
    sql.append(OrderAlertConst.SQL_PO_HEAD);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_52.name());
    sql.append(builder.buildSQL(" AND po_order.pk_org ", pk_orgs));
    sql.append(" AND " + OrderAlertConst.SQL_PO_BODY);
    sql.append(" AND " + OrderAlertConst.SQL_PO_BB1);
    sql.append(" AND " + OrderAlertConst.SQL_PO_AUDIT_OR_OUTPUT);
    sql.append(" AND " + OrderAlertConst.SQL_PO_BODY_ACTIVE);
    sql.append(" AND po_order_bb1.dplanarrvdate<'" + sLoginDate + "'");
    sql.append(" AND (");
    sql.append("  (" + OrderAlertConst.SQL_PO_STATUS_ARRV_YES);
    sql.append(" and " + OrderAlertConst.SQL_PO_BODY_NOTFULLARRV);
    sql.append("  AND " + OrderAlertConst.SQL_PO_BB1_NOTFULLARRV);
    sql.append("  )");
    sql.append("  OR ");
    sql.append("  (" + OrderAlertConst.SQL_PO_STATUS_ARRV_NO);
    sql.append("    AND" + OrderAlertConst.SQL_PO_STATUS_STORE_YES);
    sql.append(" and " + OrderAlertConst.SQL_PO_BODY_NOTFULLSTORE);
    sql.append("    AND " + OrderAlertConst.SQL_PO_BB1_NOTFULLSTORE);
    sql.append("  ))");

    String[][] datas =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (String[] data : datas) {
      data[22] = new UFDate(data[22]).toStdString(zone);

      // 入库
      if (!UFBoolean.valueOf(data[28]).booleanValue()) {
        data[26] = data[27];
      }
      UFDouble ncanrecnum =
          MathTool.sub(new UFDouble(data[25]), new UFDouble(data[26]));
      data[27] = ncanrecnum.toString();
    }

    return datas;
  }

  private String[][] getDatas(PreAlertContext context) {
    UFDate loginDate = AppContext.getInstance().getBusiDate();
    String[] pk_orgs = context.getPk_orgs();

    if (ObjectUtil.isEmptyWithTrim(loginDate) || pk_orgs == null
        || pk_orgs.length == 0) {
      return null;
    }
    return this.getData(pk_orgs, loginDate);
  }

  private PoRPNotArrvAlertDataSource getDataSource(String[][] values) {
    PoRPNotArrvAlertDataSource datasource =
        new PoRPNotArrvAlertDataSource(values);
    this.setVfree(datasource, values);
    if (values != null && values.length > 0) {
      Map<String, String[]> supMap = PuAlertUtil.getSupNameMap(values, 1);
      Map<String, String[]> matMap = PuAlertUtil.getMaterialNameMap(values, 3);
      Map<String, String[]> measdocMap =
          PuAlertUtil.getMeasdocMap(values, 18, 21);
      Map<String, String[]> orgMap = PuAlertUtil.getOrgNameMap(values, 23);
      Map<String, String[]> stordocMap = PuAlertUtil.getStocDocMap(values, 24);
      for (int j = 0; j < values.length; ++j) {
        for (int i = 0; i < values[j].length; ++i) {
          if (StringUtils.isEmpty(values[j][i])) {
            continue;
          }
          if (i == 1) {
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
          if (i == 3) {
            String[] names = matMap.get(values[j][i]);
            if (names != null) {
              datasource
                  .putData(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[0], j);
              datasource.putData2(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[1],
                  j);
              datasource.putData3(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[2],
                  j);
              datasource.putData4(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[3],
                  j);
              datasource.putData5(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[4],
                  j);
              datasource.putData6(PoRPNotArrvAlert.PK_MATERIAL_NAME, names[5],
                  j);
              datasource.putData(OrderReceivePlanVO.PK_MATERIAL, names[6], j);
              datasource.putData(PoRPNotArrvAlert.MATERIALSPEC, names[7], j);
              datasource.putData(PoRPNotArrvAlert.MATERIALTYPE, names[8], j);
            }
          }
          if (i == 18) {
            String[] names = measdocMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderReceivePlanVO.CASTUNITID, names[0], j);
              datasource.putData2(OrderReceivePlanVO.CASTUNITID, names[1], j);
              datasource.putData3(OrderReceivePlanVO.CASTUNITID, names[2], j);
              datasource.putData4(OrderReceivePlanVO.CASTUNITID, names[3], j);
              datasource.putData5(OrderReceivePlanVO.CASTUNITID, names[4], j);
              datasource.putData6(OrderReceivePlanVO.CASTUNITID, names[5], j);
            }
          }

          if (i == 21) {
            String[] names = measdocMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderReceivePlanVO.CUNITID, names[0], j);
              datasource.putData2(OrderReceivePlanVO.CUNITID, names[1], j);
              datasource.putData3(OrderReceivePlanVO.CUNITID, names[2], j);
              datasource.putData4(OrderReceivePlanVO.CUNITID, names[3], j);
              datasource.putData5(OrderReceivePlanVO.CUNITID, names[4], j);
              datasource.putData6(OrderReceivePlanVO.CUNITID, names[5], j);
            }
          }
          if (i == 23) {
            String[] names = orgMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderReceivePlanVO.PK_ARRVSTOORG, names[0], j);
              datasource
                  .putData2(OrderReceivePlanVO.PK_ARRVSTOORG, names[1], j);
              datasource
                  .putData3(OrderReceivePlanVO.PK_ARRVSTOORG, names[2], j);
              datasource
                  .putData4(OrderReceivePlanVO.PK_ARRVSTOORG, names[3], j);
              datasource
                  .putData5(OrderReceivePlanVO.PK_ARRVSTOORG, names[4], j);
              datasource
                  .putData6(OrderReceivePlanVO.PK_ARRVSTOORG, names[5], j);
            }
          }
          if (i == 24 && stordocMap != null) {
            String[] names = stordocMap.get(values[j][i]);
            if (names != null) {
              datasource
                  .putData(OrderReceivePlanVO.PK_RECVSTORDOC, names[0], j);
              datasource.putData2(OrderReceivePlanVO.PK_RECVSTORDOC, names[1],
                  j);
              datasource.putData3(OrderReceivePlanVO.PK_RECVSTORDOC, names[2],
                  j);
              datasource.putData4(OrderReceivePlanVO.PK_RECVSTORDOC, names[3],
                  j);
              datasource.putData5(OrderReceivePlanVO.PK_RECVSTORDOC, names[4],
                  j);
              datasource.putData6(OrderReceivePlanVO.PK_RECVSTORDOC, names[5],
                  j);
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
      PoRPNotArrvAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }

  private void setVfree(PoRPNotArrvAlertDataSource datasource, String[][] values) {
    Map<String, Integer> vfreeMap = new HashMap<String, Integer>();
    vfreeMap.put(OrderReceivePlanVO.VFREE1, Integer.valueOf(7));
    vfreeMap.put(OrderReceivePlanVO.VFREE2, Integer.valueOf(8));
    vfreeMap.put(OrderReceivePlanVO.VFREE3, Integer.valueOf(9));
    vfreeMap.put(OrderReceivePlanVO.VFREE4, Integer.valueOf(10));
    vfreeMap.put(OrderReceivePlanVO.VFREE5, Integer.valueOf(11));
    vfreeMap.put(OrderReceivePlanVO.VFREE6, Integer.valueOf(12));
    vfreeMap.put(OrderReceivePlanVO.VFREE7, Integer.valueOf(13));
    vfreeMap.put(OrderReceivePlanVO.VFREE8, Integer.valueOf(14));
    vfreeMap.put(OrderReceivePlanVO.VFREE9, Integer.valueOf(15));
    vfreeMap.put(OrderReceivePlanVO.VFREE10, Integer.valueOf(16));
    new PuAlertVfreeUtil(datasource, values, vfreeMap).setMarassist();
  }
}

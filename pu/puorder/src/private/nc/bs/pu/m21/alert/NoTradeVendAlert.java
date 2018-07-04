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
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.alert.PuAlertUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 无交易供应商预警类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>无交易供应商预警
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-8 下午02:57:22
 */
public class NoTradeVendAlert implements IPreAlertPlugin {
  static class NoTradeVendAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = 4623780808101443518L;

    public NoTradeVendAlertDataSource(String[][] values) {
      super(values);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        OrderItemVO.PK_SUPPLIER, NoTradeVendAlert.DLASTTRADEDATE,
        NoTradeVendAlert.DELAYDATE
      };
    }
  }

  /**
   * 逾期天数
   */
  public static final String DELAYDATE = "delaydate";

  /**
   * 最后一次交易日期
   */
  public static final String DLASTTRADEDATE = "dlasttradedate";

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
    String sAlertLastDate = dLoginDate.getDateBefore(iDays).toString();
    String sLoginDate = dLoginDate.toString();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.pk_supplier,max(b.dbilldate),'--' ");
    sql.append(" from po_order b ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_50.name());
    sql.append(builder.buildSQL(" where b.pk_org", pk_orgs));
    sql.append(" and b.dr = 0 ");
    sql.append(" and b.pk_supplier not in");
    sql.append("(");
    sql.append("select a.pk_supplier from po_order a where a.dbilldate >='");
    sql.append(sAlertLastDate + "' ");
    sql.append("and a.dbilldate <='");
    sql.append(sLoginDate + "'");
    IDExQueryBuilder builder2 =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_51.name());
    sql.append(builder2.buildSQL(" and a.pk_org ", pk_orgs));

    // 订单未作废
    sql.append("and a.dr = 0 ");
    sql.append(")");
    sql.append(" group by b.pk_supplier ");
    String[][] datas =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (String[] data : datas) {
      if (!StringUtil.isEmpty(data[1])) {
        data[1] = new UFDate(data[1]).toStdString(zone);
        data[2] =
            String.valueOf(dLoginDate.getDateBefore(iDays).getDaysAfter(
                new UFDate(data[1])));
      }
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
    int iDays = Integer.parseInt(odays.toString());
    return this.getData(pk_orgs, iDays, loginDate);
  }

  private NoTradeVendAlertDataSource getDataSource(String[][] values) {
    NoTradeVendAlertDataSource datasource =
        new NoTradeVendAlertDataSource(values);
    if (values != null && values.length > 0) {
      Map<String, String[]> supMap = PuAlertUtil.getSupNameMap(values, 0);
      for (int j = 0; j < values.length; ++j) {
        for (int i = 0; i < values[j].length; ++i) {
          if (StringUtils.isEmpty(values[j][i])) {
            continue;
          }
          if (i == 0) {
            String[] names = supMap.get(values[j][i]);
            if (names != null) {
              datasource.putData(OrderItemVO.PK_SUPPLIER, names[0], j);
              datasource.putData2(OrderItemVO.PK_SUPPLIER, names[1], j);
              datasource.putData3(OrderItemVO.PK_SUPPLIER, names[2], j);
              datasource.putData4(OrderItemVO.PK_SUPPLIER, names[3], j);
              datasource.putData5(OrderItemVO.PK_SUPPLIER, names[4], j);
              datasource.putData6(OrderItemVO.PK_SUPPLIER, names[5], j);
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
      NoTradeVendAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }
}

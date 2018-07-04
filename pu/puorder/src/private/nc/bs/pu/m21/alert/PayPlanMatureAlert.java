package nc.bs.pu.m21.alert;

import java.util.ArrayList;
import java.util.List;
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
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.rate.RateChVO;
import nc.vo.bd.rate.RateSchemaVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.alert.PuAlertUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.StringUtils;

/**
 * 采购—付款计划到期预警
 * 
 * @since 6.0
 * @version 2011-3-2 上午09:59:29
 * @author wuxla
 */

public class PayPlanMatureAlert implements IPreAlertPlugin {
  static class PayPlanAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = -5081346971196385797L;

    public PayPlanAlertDataSource(String[][] values) {
      super(values);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.DBILLDATE,
        OrderHeaderVO.CEMPLOYEEID, OrderHeaderVO.PK_SUPPLIER,
        AbstractPayPlanVO.DBEGINDATE, AbstractPayPlanVO.DENDDATE,
        AbstractPayPlanVO.NRATE, AbstractPayPlanVO.CORIGCURRENCYID,
        PayPlanMatureAlert.ONPAYMNY, RateChVO.RATE,
        PayPlanMatureAlert.AFTERDISCOUNTMNY, PayPlanMatureAlert.DISCOUNTMNY
      };
    }
  }

  /**
   * 折后金额
   */
  public static final String AFTERDISCOUNTMNY = "afterdiscountmny";

  /**
   * 现金折扣
   */
  public static final String DISCOUNTMNY = "discountmny";

  /**
   * 待付金额
   */
  public static final String ONPAYMNY = "onpaymny";

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

  private String[][] getData(String[] pk_orgs, String payplan_rely, int days,
      UFDate loginDate) {
    if (PUAlertConst.RELY_ENDDATE.equals(payplan_rely)) {
      return this.getDataByEndDate(pk_orgs, days, loginDate);
    }
    else if (PUAlertConst.RELY_DISCOUNT.equals(payplan_rely)) {
      return this.getDataByRate(pk_orgs, days, loginDate);
    }
    else {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0112")/*
                                                                   * @res
                                                                   * "参数不正确"
                                                                   */);
    }
    return null;
  }

  private String[][] getDataByEndDate(String[] pk_orgs, int days,
      UFDate loginDate) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select h." + OrderHeaderVO.VBILLCODE + ",h."
        + OrderHeaderVO.DBILLDATE);
    sql.append(",h." + OrderHeaderVO.CEMPLOYEEID + ",h."
        + OrderHeaderVO.PK_SUPPLIER);
    sql.append(",b." + AbstractPayPlanVO.DBEGINDATE + ",b."
        + AbstractPayPlanVO.DENDDATE);
    sql.append(",b." + AbstractPayPlanVO.NRATE);
    sql.append(",b." + AbstractPayPlanVO.CORIGCURRENCYID);
    sql.append(",b." + AbstractPayPlanVO.NORIGMNY);
    sql.append("-isnull(b." + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0)");
    sql.append(",null,null,null");
    sql.append(" from " + PUEntity.M21_H_TABLE + " h ");
    sql.append("inner join " + PUEntity.M21_PAYPLAN_TABLE + " b ");
    sql.append("on h." + OrderHeaderVO.PK_ORDER + " = b." + PayPlanVO.PK_ORDER);
    sql.append(" where ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_53.name());
    sql.append(builder.buildSQL("h." + OrderHeaderVO.PK_ORG, pk_orgs));
    UFDate alertLastDate = loginDate.getDateAfter(days);
    String alertDateBegin = alertLastDate.asBegin().toString();
    String alterDateEnd = alertLastDate.asEnd().toString();
    sql.append(" and b." + AbstractPayPlanVO.DENDDATE, ">=", alertDateBegin);
    sql.append(" and b." + AbstractPayPlanVO.DENDDATE, "<=", alterDateEnd);

    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
    sql.append(" and h." + OrderHeaderVO.BRETURN, UFBoolean.FALSE);
    sql.append(" and b." + AbstractPayPlanVO.DR, 0);
    sql.append(" and b." + AbstractPayPlanVO.NORIGMNY + ">" + "isnull(b."
        + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0)");

    String[][] datas =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (String[] data : datas) {
      data[1] = new UFDate(data[1]).toStdString(zone);
      data[4] = new UFDate(data[4]).toStdString(zone);
      data[5] = new UFDate(data[5]).toStdString(zone);
    }
    return datas;
  }

  private String[][] getDataByRate(String[] pk_orgs, int days, UFDate loginDate) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select h." + OrderHeaderVO.VBILLCODE + ",h."
        + OrderHeaderVO.DBILLDATE);
    sql.append(",h." + OrderHeaderVO.CEMPLOYEEID + ",h."
        + OrderHeaderVO.PK_SUPPLIER);
    sql.append(",b." + AbstractPayPlanVO.DBEGINDATE + ",b."
        + AbstractPayPlanVO.DENDDATE);
    sql.append(",b." + AbstractPayPlanVO.NRATE);
    sql.append(",b." + AbstractPayPlanVO.CORIGCURRENCYID);
    sql.append(",b." + AbstractPayPlanVO.NORIGMNY);
    sql.append("-isnull(b." + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0)");
    sql.append(",rb." + RateChVO.RATE);
    sql.append(",(b." + AbstractPayPlanVO.NORIGMNY + "-isnull(b."
        + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0))*");
    sql.append("(1-rb." + RateChVO.RATE + "/100)");
    sql.append(",rb." + RateChVO.RDATA);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h ");
    sql.append("inner join " + PUEntity.M21_PAYPLAN_TABLE + " b ");
    sql.append("on h." + OrderHeaderVO.PK_ORDER + " = b." + PayPlanVO.PK_ORDER);
    OrderPaymentVO paymentChVO = new OrderPaymentVO();
    sql.append(" inner join " + paymentChVO.getTableName());
    sql.append(" pb on b." + AbstractPayPlanVO.PK_PAYMENTCH + "= pb."
        + OrderPaymentVO.PK_PAYMENT);
    RateChVO rateChVO = new RateChVO();
    sql.append(" inner join " + rateChVO.getTableName());
    sql.append(" rb on pb." + PaymentChVO.PK_RATE + "=rb."
        + RateSchemaVO.PK_RATESCHEMA);
    sql.append(" where ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_54.name());
    sql.append(builder.buildSQL("h." + OrderHeaderVO.PK_ORG, pk_orgs));
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
    sql.append(" and h." + OrderHeaderVO.BRETURN, UFBoolean.FALSE);
    sql.append(" and b." + AbstractPayPlanVO.DR, 0);

    UFDate alertLastDate = loginDate.getDateAfter(days);
    String alertDateBegin = alertLastDate.asBegin().toString();
    sql.append(" and b." + AbstractPayPlanVO.DENDDATE, ">=", alertDateBegin);

    sql.append(" and ");
    sql.append("(b." + AbstractPayPlanVO.NORIGMNY + "-isnull(b."
        + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0))*");
    sql.append("(1-rb." + RateChVO.RATE + "/100)");
    sql.append(">");
    sql.append("isnull(b." + AbstractPayPlanVO.NACCUMPAYORGMNY + ",0)");

    String[][] tempValues =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    List<String[]> list = new ArrayList<String[]>();
    for (String[] temp : tempValues) {
      temp[1] = new UFDate(temp[1]).toStdString(zone);
      temp[4] = new UFDate(temp[4]).toStdString(zone);
      temp[5] = new UFDate(temp[5]).toStdString(zone);

      int rdata = Integer.parseInt(temp[11]);
      UFDate alertDate = alertLastDate.getDateAfter(rdata);
      UFDate denddate = new UFDate(temp[5]);
      if (denddate.isSameDate(alertDate)) {
        UFDouble discountMny =
            new UFDouble(temp[8]).sub(new UFDouble(temp[10]));
        temp[11] = discountMny.toString();
        list.add(temp);
      }
    }

    if (0 == list.size()) {
      return null;
    }
    String[][] values = new String[list.size()][];
    for (int i = 0; i < list.size(); ++i) {
      values[i] = list.get(i);
    }

    return values;
  }

  private String[][] getDatas(PreAlertContext context) {
    String[] pk_orgs = context.getPk_orgs();
    Object payplan_rely = context.getKeyMap().get(PUAlertConst.PAYPLAN_RELY);
    Object days = context.getKeyMap().get(PUAlertConst.DAYS);
    if (pk_orgs == null || pk_orgs.length == 0
        || ObjectUtil.isEmptyWithTrim(payplan_rely)
        || ObjectUtil.isEmptyWithTrim(days)) {
      return null;
    }
    UFDate loginDate = AppContext.getInstance().getBusiDate();
    String[][] value =
        this.getData(pk_orgs, payplan_rely.toString(),
            Integer.parseInt(days.toString()), loginDate);
    return value;
  }

  private PayPlanAlertDataSource getDataSource(String[][] values) {
    PayPlanAlertDataSource datasource = new PayPlanAlertDataSource(values);
    if (values != null && values.length > 0) {
      Map<String, String[]> psnMap = PuAlertUtil.getPsnNameMap(values, 2);
      Map<String, String[]> supMap = PuAlertUtil.getSupNameMap(values, 3);
      Map<String, String[]> curMap = PuAlertUtil.getCurrencyMap(values, 7);
      for (int j = 0; j < values.length; ++j) {
        for (int i = 0; i < values[j].length; ++i) {
          if (StringUtils.isEmpty(values[j][i])) {
            continue;
          }
          // // 前面处理时区时已经处理了，不需要处理了。
          // // 日期
          // if (i == 1) {
          // UFDate date = ValueUtils.getUFDate(values[j][i]);
          // datasource
          // .putData(OrderHeaderVO.DBILLDATE, date.toLocalString(), j);
          // }
          // if (i == 4) {
          // UFDate date = ValueUtils.getUFDate(values[j][i]);
          // datasource.putData(AbstractPayPlanVO.DBEGINDATE,
          // date.toLocalString(), j);
          // }
          // if (i == 5) {
          // UFDate date = ValueUtils.getUFDate(values[j][i]);
          // datasource.putData(AbstractPayPlanVO.DENDDATE,
          // date.toLocalString(), j);
          // }
          // 采购员
          if (i == 2 && values[j][i] != null && !values[j][i].equals("~")
              && psnMap != null) {
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
          // 供应商
          if (i == 3 && supMap != null) {
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
          // 币种
          if (i == 7) {
            String[] names = curMap.get(values[j][i]);
            if (names != null) {
              datasource
                  .putData(AbstractPayPlanVO.CORIGCURRENCYID, names[0], j);
              datasource.putData2(AbstractPayPlanVO.CORIGCURRENCYID, names[1],
                  j);
              datasource.putData3(AbstractPayPlanVO.CORIGCURRENCYID, names[2],
                  j);
              datasource.putData4(AbstractPayPlanVO.CORIGCURRENCYID, names[3],
                  j);
              datasource.putData5(AbstractPayPlanVO.CORIGCURRENCYID, names[4],
                  j);
              datasource.putData6(AbstractPayPlanVO.CORIGCURRENCYID, names[5],
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
      PayPlanAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }

}

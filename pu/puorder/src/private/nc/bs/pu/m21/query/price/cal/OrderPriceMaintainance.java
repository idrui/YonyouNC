package nc.bs.pu.m21.query.price.cal;

import java.util.Calendar;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ɹ�ѯ��-�ɹ��۸��ά����Ļ��࣬����������ά���๲ͬ�Ĳ���
 * 
 * @since 6.1
 * @version 2011-11-19 ����09:02:07
 * @author yangtian
 */
public abstract class OrderPriceMaintainance {

  private UFDate m_endtime = null;

  /**
   * ���м���po_order_price ������ݶ��ṫ���õ�һ�δ��룬������ȡ����
   * 
   * @param beginTime ��ʼʱ��
   * @param endTime ����ʱ��
   * @param bvalidate ����� bvalidate�ֶε�ֵ��'N'����'Y'
   * @param innerCode ��Ϊ��δ����ǻ����͵ģ�����Ҫ�Ѳ�ͬ���봫�룬�ŵ�����������м�
   * @return String sql
   */
  public String calculateOrderPriceForAll(String innerCode, String bvalidate,
      UFDate beginTime, UFDate endTime) {
    SqlBuilder sql = new SqlBuilder();
    // Ӧ����36���ֶΣ�po_order_price
    sql.append(" insert into po_order_price( ts, bvalidate, pk_order_price,  dbilldate,  modifiedtime, "
        + this.getSelectPart(" ")
        + PriceCalculatorUtil.getSelectPartForLowestQuery(" ", "m ") + " ) ");
    sql.append(" select  '" + new UFDate().toString() + "', '" + bvalidate
        + "', a.pk_order_b,  a.dbilldate, a.ts, " + this.getSelectPart(" a.")
        + PriceCalculatorUtil.getSelectPartForLowestQuery(" a.", "m"));
    // ��һ���Ӳ�ѯ
    sql.append(" from( ");

    sql.append(" select b.pk_order_b,  b.dbilldate, a.ts, "
        + this.getSelectPart(" b."));
    sql.append(this.getAnalysisFunctionPartForLowestPrice(" b.") + ", ");

    sql.append(" row_number()  over(partition by   substring(a.ts, 1,7) ,   "
        + this.getAnalysisFunctionColumnForLatestPrice(" b.")
        + "  order by  b.dbilldate desc ) rno  ");

    sql.append(" from po_order_b b inner  join po_order a on b.pk_order=a.pk_order ");

    // �������Ӧ�ó�����ͬ�Ĵ��벿��
    sql.append(innerCode);

    sql.append("   b.dr=0 and a.dr=0  and b.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");

    sql.append(" and a." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" a.ts ",
        beginTime, endTime, 0));
    // ��һ���Ӳ�ѯ
    sql.append(" ) a ");
    sql.append("where  a.rno=1");
    return sql.toString();
  }

  /**
   * ���м���po_order_price ������ݶ��ṫ���õ�һ�δ��룬������ȡ����
   * 
   * @param beginTime ��ʼʱ��
   * @param endTime ����ʱ��
   * @param innerCode ��Ϊ��δ����ǻ����͵ģ�����Ҫ�Ѳ�ͬ���봫�룬�ŵ�����������м�
   * @return String sql
   */
  public String calculateOrderPriceForAll(String innerCode, UFDate beginTime,
      UFDate endTime) {
    return this.calculateOrderPriceForAll(innerCode, "Y", beginTime, endTime);
  }

  /**
   * ���м���po_order_price ������ݶ��ṫ���õ�һ�δ��룬������ȡ����
   * 
   * @param beginTime ��ʼʱ��
   * @param endTime ����ʱ��
   * @param String otherWherePart ����Ԥ����Ҫ����������
   */
  public void calculatePriceBForAll(UFDate beginTime, UFDate endTime,
      String otherWherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" insert into po_order_price_b(ts,pk_order_b,dr,pk_order_price) ");
    sql.append(" select '"
        + new UFDate().toString()
        + "', b.pk_order_b, 0, prc.pk_order_price from po_order_b b inner join  po_order a on  a.pk_order=b.pk_order  ");
    sql.append(" inner join po_order_price prc on   b.pk_org=prc.pk_org ");
    sql.append(" and b.pk_supplier=prc.pk_supplier and b.corigcurrencyid=prc.corigcurrencyid and b.pk_srcmaterial=prc.pk_srcmaterial ");
    sql.append(" and b.pk_psfinanceorg=prc.pk_psfinanceorg and b.pk_arrvstoorg=prc.pk_arrvstoorg and substring(prc.modifiedtime,1,7) =substring(a.ts,1,7) ");
    sql.append("  where  b.dr=0 and b.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + otherWherePart + " ");
    sql.append(" and a." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" a.ts ",
        beginTime, endTime, 0));
    PriceCalculatorUtil.update(sql.toString(), "calculate4OrderPriceBByTime");
  }

  /**
   * ɾ��po_order_price�Ĳ���
   * 
   * @param wherePart ���sql��where�Ĳ���
   */
  public void deleteOrderPrice(String wherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_order_price  " + wherePart);
    PriceCalculatorUtil.update(sql.toString(), "deleteOrderPrice");

  }

  /**
   * ɾ��po_order_price_b�Ĳ���
   * 
   * @param wherePart ���sql��where�Ĳ���
   */
  public void deleteOrderPriceB(String wherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_order_price_b  " + wherePart);

    PriceCalculatorUtil.update(sql.toString(), "deleteOrderPriceB");
  }

  /**
   * ִ��ά������
   * 
   * @param UFDate lastAlertDate�ϴ�Ԥ��ʱ��
   */

  public abstract void doMaintainance(UFDate lastAlertDate);

  /**
   * ִ�ж�ʱ����
   * 
   * @throws BusinessException
   */

  public void excuteTimeTask() throws BusinessException {
    try {
      if (PriceCalculatorUtil.addLock()) {
        // ���������ϴμ����ʱ��,����ܲ鵽�ͼ����ϸ��µģ����û���鵽˵���ǵ�һ�μ��㣬�ͼ�������ʱ��
        UFDate lastAlertDate = this.getLastAlertDate();
        if (lastAlertDate == null) {
          // ��û�в鵽������Ϣ���ݵ�ʱ�����֮ǰ�������µ�����
          this.initCalculate();
        }
        else if (this.isNeedReCal().booleanValue()) {

          this.doMaintainance(lastAlertDate);

        }
      }
      else {
        Log.error("�ɹ�ѯ�۱����ʧ�ܣ���Դ����ס�����Ժ����");/* -=notranslate=- */
      }

    }
    catch (Exception e) {
      PriceCalculatorUtil.logError(e);/* -=notranslate=- */
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ���sql��ͳ�����¼۵�ʱ��ķ��������Ĳ���
   * 
   * @param tableName ����
   * @return String ����sql
   */
  private String getAnalysisFunctionColumnForLatestPrice(String tableName) {

    String attributeNames[] =
        new String[] {
          OrderPriceVO.PK_ORG, OrderPriceVO.PK_SUPPLIER,
          OrderPriceVO.PK_SRCMATERIAL, OrderPriceVO.CORIGCURRENCYID,
          OrderPriceVO.PK_PSFINANCEORG, OrderPriceVO.PK_ARRVSTOORG
        };

    SqlBuilder analysisFunctionPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      analysisFunctionPart.append(tableName + attributeName);
      analysisFunctionPart
          .append(attributeName == OrderPriceVO.PK_ARRVSTOORG ? " " : ", ");
    }
    return analysisFunctionPart.toString();
  }

  /**
   * ���sql��ͳ����ͼ۵�ʱ��ķ��������Ĳ���
   * 
   * @param tableName ����
   * @return String sql
   */
  private String getAnalysisFunctionPartForLowestPrice(String tableName) {
    SqlBuilder analysisFunctionPart = new SqlBuilder();
    String attributeNames[] =
        new String[] {
          OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NORIGTAXNETPRICE
        };

    for (String attributeName : attributeNames) {
      analysisFunctionPart.append(" min( " + tableName + attributeName
          + ") over(partition by substring(a.ts, 1,7) , "
          + this.getAnalysisFunctionColumnForLatestPrice(tableName) + " ) ");
      analysisFunctionPart.append(" " + attributeName + "m ");
      analysisFunctionPart
          .append(attributeName == OrderPriceVO.NORIGTAXNETPRICE ? " " : ", ");
    }
    return analysisFunctionPart.toString();
  }

  /**
   * ��Ϊsql�д󲿷�select�Ĳ��ֶ���һ���ģ����Կ��Գ�ȡ��һ���Ĳ��ַ���һ��
   * 
   * @param tableName ����
   * @return String sql
   */
  private String getSelectPart(String tableName) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.PK_ORG, OrderPriceVO.PK_PSFINANCEORG,
          OrderPriceVO.PK_ARRVSTOORG, OrderPriceVO.PK_SRCMATERIAL,
          OrderPriceVO.PK_SUPPLIER,

          OrderPriceVO.CORIGCURRENCYID, OrderPriceVO.CUNITID,
          OrderPriceVO.FTAXTYPEFLAG, OrderPriceVO.NTAXRATE,
          OrderPriceVO.CASTUNITID, OrderPriceVO.CQTUNITID,

          OrderPriceVO.NQTORIGTAXNETPRC, OrderPriceVO.NQTTAXNETPRICE,
          OrderPriceVO.NORIGTAXNETPRICE, OrderPriceVO.NTAXNETPRICE,

          OrderPriceVO.NQTORIGNETPRICE, OrderPriceVO.NQTNETPRICE,
          OrderPriceVO.NNETPRICE, OrderPriceVO.NORIGNETPRICE,

          OrderPriceVO.NQTTAXPRICE, OrderPriceVO.NQTORIGTAXPRICE,
          OrderPriceVO.NORIGTAXPRICE, OrderPriceVO.NTAXPRICE,

          OrderPriceVO.NQTORIGPRICE, OrderPriceVO.NQTPRICE,
          OrderPriceVO.NORIGPRICE, OrderPriceVO.NPRICE
        };

    SqlBuilder selectPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      if (attributeName != null) {
        selectPart.append(tableName + attributeName + ", ");
      }
    }
    // + PriceCalculatorUtil.getSelectPartForQuery(tableName);
    return selectPart.toString();
  }

  /**
   * ��һ��Ԥ������ʼ��������ļ���
   */

  private void initCalculate() {
    PriceCalculatorUtil.logTime("�����۸������ȡԤ����ʼʱ����:");/* -=notranslate=- */
    // ��ʼ�����ʱ������� po_order_price���po_order_price_b
    this.deleteOrderPrice(" ");
    this.initOrderPrice();
    this.deleteOrderPriceB(" ");
    this.initPriceB();

    // ֻ�м���ɹ�����ܽ��в������
    this.insertAlertDate();
    PriceCalculatorUtil.logTime("�����۸������ȡԤ������ʱ����:");/* -=notranslate=- */
  }

  /**
   * ΪPo_order_price�����һ��ʱ���µ����е�����
   * �����ʼʱ��ͽ���ʱ�䶼��null����ʾҪ���������·ݵ�����
   * 
   * @param endTime ����ʱ��
   */

  private void initOrderPrice() {
    String excuteSql =
        this.calculateOrderPriceForAll(" where ", null, this.getEndTime());
    PriceCalculatorUtil.update(excuteSql, "initOrderPrice");

  }

  private void initPriceB() {

    this.calculatePriceBForAll(null, this.getEndTime(), " ");
  }

  /**
   * ���뵱ǰԤ����ʱ��
   */
  private void insertAlertDate() {

    SqlBuilder sqlinsert = new SqlBuilder();
    sqlinsert
        .append(" insert into   po_order_price (pk_order_price, modifiedtime ) values ('"
            + OrderPriceVO.PK_CALC + "', '" + this.getEndTime() + "') ");
    PriceCalculatorUtil.update(sqlinsert.toString(), "insertAlertDate");
  }

  /**
   * ��ñ��μ���Ľ���ʱ��
   * ֮����Ҫȡ�����ǰһ����ΪԤ�����ݵĽ�������Ϊ�����ڵ����ݻ���Ϊ������ɶ�Ļ��ߴ����ӳٶ���ʧ�����һ���ʱ�����⣬����21���й�22�գ���12��Сʱ
   * 
   * @param tableName ����
   * @return UFDate ������Ԥ���Ľ���ʱ��
   */
  protected UFDate getEndTime() {
    if (null == this.m_endtime) {// ��֤һ�μ�����ÿ��sql��endtime����һ�µģ����ܼ���price��ʱ����һ��ʱ�䣬����priceb��ʱ����һ��ʱ��
      Calendar now = Calendar.getInstance();
      this.m_endtime = new UFDateTime(now.getTime()).getDate().getDateBefore(1);
    }
    return this.m_endtime;
  }

  /**
   * ����ϴ�Ԥ����ʱ��
   * 
   * @return UFDate �����ϴ�Ԥ����ʱ��
   */
  protected UFDate getLastAlertDate() {
    SqlBuilder sqlquery = new SqlBuilder();
    sqlquery
        .append(" select  a.modifiedtime from po_order_price a where a.pk_order_price='"
            + OrderPriceVO.PK_CALC + "' ");

    DataAccessUtils utils = new DataAccessUtils();
    String[] results =
        utils.query(sqlquery.toString()).toOneDimensionStringArray();
    if (results.length == 0) {
      return null;
    }
    return new UFDateTime(results[0], ICalendar.BASE_TIMEZONE).getDate();
  }

  protected UFBoolean isNeedReCal() {
    return UFBoolean.TRUE;
  }

}

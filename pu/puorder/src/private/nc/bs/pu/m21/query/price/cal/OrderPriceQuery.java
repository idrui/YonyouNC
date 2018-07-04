package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ɹ�ѯ��-ѯ�۲�ѯ��
 * 
 * @since 6.1
 * @version 2011-11-19 ����09:02:07
 * @author yangtian
 */
public class OrderPriceQuery {

  /**
   * ��ѯ���¼�
   * 
   * @param purchaseOrg �ɹ���֯<font color="red">����Ϊ�գ�</font>
   * @param financeOrg ������֯<font color="red">����Ϊ�գ�</font>
   * @param supplier ��Ӧ��<font color="red">����Ϊ�գ�</font>
   * @param currency ����<font color="red">����Ϊ�գ�</font>
   * @param materials ���ϵ�OID����
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   */
  public OrderPriceData[] queryLatestPrice(String[] purchaseOrgs,
      String[] financeOrgs, String[] suppliers, String[] currencies,
      String[] materials, UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�ѯ���¼ۿ�ʼʱ��:");/* -=notranslate=- */

    // ȡ���µĵ�һ����Ϊ�ֽ�ʱ�䣬��һ����ǰ�����ݶ���ѯ�۱���飬��һ��֮������ݶ��Ӳɹ��������
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPriceStrategy(purchaseOrgs, financeOrgs,
                  suppliers, currencies, materials), startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�ѯ���¼ۿ�ʼʱ��:");/* -=notranslate=- */

    return prices;

  }

  /**
   * Ϊ�����ѯ���¼�
   * 
   * @param pk_arrvstoorgs �ջ������֯
   * @param moids ����OID����
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   */
  public OrderPriceData[] queryLatestPrice4MM(String[] pk_arrvstoorgs,
      String[] moids, UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�����ѯ���¼ۿ�ʼʱ��:");/* -=notranslate=- */

    // ȡ���µĵ�һ����Ϊ�ֽ�ʱ�䣬��һ����ǰ�����ݶ���ѯ�۱���飬��һ��֮������ݶ��Ӳɹ��������
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPrice4MMStrategy(pk_arrvstoorgs, moids),
              startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�����ѯ���¼۽���ʱ��:");/* -=notranslate=- */

    return prices;
  }

  /**
   * Ϊ�ڲ����ײ�ѯ���¼�
   * 
   * @param fiorgs ������֯����
   * @param moids ����OID����
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   */
  public OrderPriceData[] queryLatestPrice4TO(String[] fiorgs, String[] moids,
      UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ��ڲ�����ѯ���¼ۿ�ʼʱ��:");/* -=notranslate=- */

    // ȡ���µĵ�һ����Ϊ�ֽ�ʱ�䣬��һ����ǰ�����ݶ���ѯ�۱���飬��һ��֮������ݶ��Ӳɹ��������
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPrice4ToStrategy(fiorgs, moids), startDate,
              endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ��ڲ�����ѯ���¼۽���ʱ��:");/* -=notranslate=- */

    return prices;
  }

  /**
   * ��ѯ��ͼ�
   * 
   * @param purchaseOrg
   *          �ɹ���֯
   * @param supplier
   *          ��Ӧ��
   * @param currency
   *          ����
   * @param materials
   *          ����OID����
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   */
  public OrderPriceData[] queryLowestPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials,
      UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�ѯ��ͼۿ�ʼʱ��:");/* -=notranslate=- */
    // ȡ���µĵ�һ����Ϊ�ֽ�ʱ�䣬��һ����ǰ�����ݶ���ѯ�۱���飬��һ��֮������ݶ��Ӳɹ��������
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLowestPriceStrategy(purchaseOrgs, suppliers, currencies,
                  materials), startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    PriceCalculatorUtil.logTime("�ɹ�ѯ�ۣ�ѯ��ͼ۽���ʱ��:");/* -=notranslate=- */

    return prices;
  }

  private OrderPriceData[] queryPrice(UFDate divideTime,
      IQueryPriceStrategy queryPriceStrategy, UFDate startDate, UFDate endDate)
      throws BusinessException {
    try {

      // ��dbilldate�������������Ҫȡ��ʼʱ�������µĵ�һ�죬����ʱ�������µ����һ��
      // ��ʵҲ������ô���ɣ���Ȼts�ǰ���ͳ�Ƶģ�����dbilldate����ѽ
      UFDate startTime = null;
      UFDate endTime = null;
      if (startDate != null) {
        startTime = startDate.getDateBefore(startDate.getDay() - 1);
      }
      if (endDate != null) {
        endTime =
            endDate.getDateAfter(endDate.getDaysMonth() - endDate.getDay())
                .asEnd();
      }
      return this.queryPriceByDivideTime(divideTime, queryPriceStrategy,
          startTime, endTime);
    }
    catch (Exception e) {
      Log.error(e);
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 3��ѯ�۲�ѯ�Ŀ������ͬ�ģ����ԣ�д����ͬ�ĵط�������strategy���룬��ƴ��Щ��ͬ�ĵط�
   * divideTime��һ���ֽ�㣬��㵽�������ʱ��β�ɹ�������po_order_b��һ�����ϴ�Ԥ��ʱ��
   * �����ʱ��㵽��ǰ�����ʱ��Ͳ�ѯѯ�۱�po_order_price�������ʱ��㵽��ǰ��ʱ�䣬
   * bvalidate�ֶ�����Ч�����ݻ��ǲ�ѯ�ɹ�������po_order_b��
   * ����3������union all������Ϊ�ܵ�����
   * 
   * @param divideTime��ѯѯ�۱�Ͳɹ�������ķֽ�ʱ�䣬һ�����ϴ�ѯ�۵�ʱ��
   * @param queryPriceStrategy ѯ�۵ĳ���
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   */
  private OrderPriceData[] queryPriceByDivideTime(UFDate divideTime,
      IQueryPriceStrategy queryPriceStrategy, UFDate startDate, UFDate endDate) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select " + queryPriceStrategy.getSelectPart(" a."));
    // ��һ���Ӳ�ѯ
    sql.append(" from( ");

    sql.append(" select "
        + queryPriceStrategy.getSelectPartFromUnionData(" b.", "m "));
    // �ڶ����Ӳ�ѯ
    sql.append(" from( ");
    // ��po_order_b�������ϴ�Ԥ��ʱ�䵽���ڵ�����
    sql.append(" select t.dbilldate, "
        + queryPriceStrategy.getSelectPart(" t."));
    sql.append(" from (");
    // �������Ӳ�ѯ��ʼ
    sql.append(" select /*+no_index(c I_PU_21_B_CALC)*/  c.dbilldate, "
        + queryPriceStrategy.getAnalysisFunctionPart(" c."));
    sql.append(" from po_order_b c inner join po_order d on c.pk_order=d.pk_order ");
    sql.append(queryPriceStrategy.getFromPart());
    sql.append(" where  d.dr=0  and c.dr=0 and c.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");
    sql.append(" and d." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    // �����һ����Ϊ����������������Ҫ��><��һ������������
    sql.append(queryPriceStrategy.getQueryConditionPart(" d.ts", " c.",
        divideTime, new UFDate().getDateAfter(1)));
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    // �������Ӳ�ѯ����
    sql.append(" ) t  WHERE t.rno=1 ");

    sql.append(" union all ");
    // ��po_order_price�������Ч������
    sql.append(" select c.dbilldate,   "
        + queryPriceStrategy.getSelectPart(" c."));
    sql.append(" from  po_order_price c  " + queryPriceStrategy.getFromPart());
    sql.append(" where c.bvalidate='Y' "
        + queryPriceStrategy.getQueryConditionPart(" c.modifiedtime", " c.",
            null, divideTime));
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    sql.append(" union all ");
    // ����divdetime֮ǰ����Щ״̬��ʶΪ��Ч��
    sql.append(" select t.dbilldate, "
        + queryPriceStrategy.getSelectPart(" t."));
    sql.append(" from ( ");
    // �������Ӳ�ѯ��ʼ
    sql.append(" select c.dbilldate,   "
        + queryPriceStrategy.getAnalysisFunctionPart(" c."));
    sql.append(" from po_order_b c " + queryPriceStrategy.getFromPart()
        + " inner join  po_order_price_b d on  d.pk_order_b=c.pk_order_b ");

    sql.append("  inner join po_order f on f.pk_order=c.pk_order    ");
    sql.append(" inner  join po_order_price e  on e.pk_order_price = d.pk_order_price ");
    sql.append(" where  e.bvalidate='N' ");
    sql.append(" and f." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(queryPriceStrategy.getQueryConditionPart(" f.ts", " e.", null,
        null));
    sql.append(" and f.dr=0 and c.dr=0 and c.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");
    // �������Ӳ�ѯ����
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    sql.append(" ) t  WHERE t.rno=1 ");

    // �ڶ����Ӳ�ѯ
    sql.append(" ) b ");
    // ��һ���Ӳ�ѯ
    sql.append(" ) a ");
    sql.append(" where  a.rno=1");

    return queryPriceStrategy.getResult(PriceCalculatorUtil.query(
        sql.toString(), "queryLatestPrice"));

  }
}

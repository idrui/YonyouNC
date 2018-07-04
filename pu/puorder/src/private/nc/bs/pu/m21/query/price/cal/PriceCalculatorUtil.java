package nc.bs.pu.m21.query.price.cal;

import java.util.Calendar;

import nc.bs.uap.lock.PKLock;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IPKLockBS;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ɹ�ѯ��-�����ࡣ
 * 
 * @since 6.1
 * @version 2011-11-19 ����09:02:07
 * @author yangtian
 */
public class PriceCalculatorUtil {
  /**
   * ��������
   * �������ƣ���ִ����Ч��������ά��������������Ԥ��֮ǰҪ��ס����pk�������������޶�/�������������Ԥ�����⡣
   */

  public static boolean addLock() {

    return PKLock.getInstance().addDynamicLock(OrderPriceVO.PK_CALC);
  }

  /**
   * �ӹ�����������ͬһ���û����Բ��в��������ǻ��ų�Ԥ�����������Ը���ɾ����Ԥ���Ǵ��еġ�
   * �������ƣ���ִ����Ч��������ά��������������Ԥ��֮ǰҪ��ס����pk�������������޶�/�������������Ԥ�����⣬���޶�/�������֮���ǲ��еġ�
   */

  public static boolean addSharedLock() {

    return PKLock.getInstance().addDynamicLock(
        OrderPriceVO.PK_CALC + IPKLockBS.STR_SHARED_LOCK);

  }

  /**
   * ��ѯ����ʱ�����Ʋ���ƴ�ӵ�sql
   * 
   * @param columnName�ֶεı���
   * @param beginTime ��ʼʱ��
   * @param endTime ����ʱ��
   * @param offsetSecondOnBegin ��ʼʱ�����Ӻ�����
   * @return String sql
   */
  public static String getBeginAndEndTimeQueryPart(String columnName,
      UFDate beginTime, UFDate endTime, int offsetSecondOnBegin) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" ");
    UFDate beginDate = null;
    /* !!�����԰�and�������棬��Ϊand����������������ʱ������ȫû������ʱ���ʱ�����û��and */

    if (beginTime != null) {// ��Ϊsql�淶˵ֻ����>=����Ҫ��beginTime��ǰ��һ��
      beginDate = UFDate.getDate(beginTime.getMillis() + offsetSecondOnBegin);
      sql.append(" and " + columnName + "  >=  '" + beginDate + "' ");
    }

    if (endTime != null) {
      sql.append(" and " + columnName + "  <=  '" + endTime + "' ");
    }
    return sql.toString();
  }

  /**
   * ��ò�ѯ��ͼ���ѡ��۸񲿷�
   * 
   * @param tableName ����
   * @param postfix ��׺
   * @return String
   */
  // �����԰����Ϸ������ΪԤ����ʱ����Ҫû������
  public static String getSelectPartForLowestQuery(String tableName,
      String postfix) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NORIGTAXNETPRICE
        };

    SqlBuilder selectPart = new SqlBuilder();

    for (String attributeName : attributeNames) {
      selectPart.append(tableName + attributeName + postfix);
      selectPart.append(attributeName == OrderPriceVO.NORIGTAXNETPRICE ? " "
          : ", ");
    }

    return selectPart.toString();
  }

  /**
   * ��¼һ��
   * 
   * @param e �쳣��Ϣ
   */
  public static void logError(Exception e) {
    Log.error("�ɹ�ѯ�۴���");/* -=notranslate=- */
    Log.error(e);
  }

  /**
   * ��¼��־
   * 
   * @param message ��־��Ϣ
   */

  public static void logTime(String message) {
    Log.debug("�ɹ�ѯ��"/* -=notranslate=- */+ message
        + UFDate.getDate(Calendar.getInstance().getTime()).toString());
  }

  /**
   * �Բ�ѯ���ݿ�������з�װ����¼��־
   * 
   * @param sql Ҫִ�е�sql
   * @param functionName ������
   */
  public static IRowSet query(String sql, String functionName) {

    PriceCalculatorUtil.logTime(functionName + "��ʼִ��ʱ���ǣ�");/* -=notranslate=- */
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet returnvalue = utils.query(sql);
    PriceCalculatorUtil.logTime(functionName + "ִ�н���ʱ���ǣ�");/* -=notranslate=- */
    return returnvalue;

  }

  /**
   * �Ը������ݿ�������з�װ����¼��־
   * 
   * @param sql Ҫִ�е�sql
   * @param functionName ������
   */
  public static int update(String sql, String functionName) {
    PriceCalculatorUtil.logTime(functionName + "��ʼִ��ʱ���ǣ�");/* -=notranslate=- */
    DataAccessUtils utils = new DataAccessUtils();
    int returnvalue = utils.update(sql);
    PriceCalculatorUtil.logTime(functionName + "ִ�н���ʱ���ǣ�");/* -=notranslate=- */
    return returnvalue;

  }
}

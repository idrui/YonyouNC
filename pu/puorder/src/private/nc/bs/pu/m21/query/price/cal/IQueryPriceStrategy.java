package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 * Ϊ��ͬ�Ĳ�ѯ�����ṩ��ͬ�Ĳ�ѯ����
 * 
 * @since 6.1
 * @version 2011-11-20 ����11:25:32
 * @author yangtian
 */
public interface IQueryPriceStrategy {
  /**
   * ���sql��select�ķ�����������
   * 
   * @param tableName ����
   * @return String sql
   */
  public String getAnalysisFunctionPart(String tableName);

  /**
   * ���sql��from����
   * 
   * @return String sql
   */
  public String getFromPart();

  /**
   * ���sql��where����
   * 
   * @param modifiedTimeTableName modifiedtime�ֶεı���
   * @param tableName ����
   * @param beginTime ��ʼʱ��
   * @param endTime ����ʱ��
   * @return String sql
   */
  public String getQueryConditionPart(String modifiedTimeTableName,
      String tableName, UFDate beginTime, UFDate endTime);

  /**
   * �ӹ������ݿ��в�ѯ�������ݣ�֮�󷵻ظ����÷�
   * 
   * @param rowset ���ݿ���ԭʼ����
   * @return OrderPriceData[] �����ӹ��������
   */
  public OrderPriceData[] getResult(IRowSet rowset);

  /**
   * ��po_order_price����ȡ���ݵ�select����
   * 
   * @param tableName ����
   * @return String sql
   */

  public String getSelectPart(String tableName);

  /**
   * ��ʵ��getAnalysisFunctionPart����ֻ�ǲ���ͼ۵�ʱ����������Ҫ���������Щ��ͼ��ֶ��ǲ���Ҫ��'m'��׺
   * 
   * @param tableName ����
   * @return String sql
   */
  public String getSelectPartFromUnionData(String tableName, String postfix);
}

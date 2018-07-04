/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 ����03:36:13
 */
package nc.ui.pu.m27.match.view.query;

import java.util.Map;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ʹ�òɹ���ⵥ��ѯģ���ѯ�������ݵ���Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-15 ����03:36:13
 */
public class SettleQueryInfo {
  private Map<String, String> attrPathMap;

  private String beanID;

  /**
   * SettleQueryInfo �Ĺ�����
   * 
   * @param attrPathMap
   *          ��ⵥ�����������㵥�ݲ�ѯģ������ӳ��
   * @param beanID
   *          ���������㵥��Ԫ������ʵ��ID
   */
  public SettleQueryInfo(String beanID, Map<String, String> attrPathMap) {
    super();
    this.attrPathMap = attrPathMap;
    this.beanID = beanID;
  }

  public Map<String, String> getAttrPathMap() {
    return this.attrPathMap;
  }

  public String getBeanID() {
    return this.beanID;
  }

}

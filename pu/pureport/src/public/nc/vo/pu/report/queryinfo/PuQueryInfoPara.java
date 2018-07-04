package nc.vo.pu.report.queryinfo;

import java.io.Serializable;

/**
 * �ɹ����ɱ����ѯ������
 * 
 * @since 6.0
 * @version 2011-2-22 ����09:58:19
 * @author yinfy
 */

public abstract class PuQueryInfoPara implements Serializable {

  public static final String QUERY_PARA = "#queryPara#";
  
  // ��ѯ����
  public static final String QUERY_CONDS = "#query_conds#";

  private static final long serialVersionUID = 3694910497899283742L;

  /** ���ܿھ� **/
  private String groupcondtion;

  public String getGroupcondtion() {
    return this.groupcondtion;
  }

  /**
   * ������������ȡ�����ֶ�
   * 
   * @return �����ֶ�
   */
  public String[] getGroupKeys() {
    return null;
  }

  /**
   * ������������ȡ�����ֶ�
   * 
   * @return �����ֶ�
   */
  public String[] getHideKeys() {
    return null;
  }

  /**
   * ������������ȡ�����ֶ�
   * 
   * @return
   */
  public String[] getSortKeys() {
    return null;
  }

  /**
   * ������������ȡ�ϼ��ֶ�
   * 
   * @return �ϼ��ֶ�
   */
  public String[] getTotalKeys() {
    return null;
  }

  public void setGroupcondtion(String groupenum) {
    this.groupcondtion = groupenum;
  }
}

/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 ����05:57:37
 */
package nc.pubimpl.pu.m4t.pub;

import nc.pubitf.pu.m4t.pub.IInitialEstPubQuery;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ѯ����ʵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 ����05:57:37
 */
public class InitialEstPubQueryImpl implements IInitialEstPubQuery {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m4t.pub.IInitialEstPubQuery#isExistFromOrder(java.lang.String[])
   */
  @Override
  public boolean isExistFromOrder(String[] bid) throws BusinessException {
    return false;
  }

}

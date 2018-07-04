/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 ����09:46:17
 */
package nc.pubimpl.pu.m21.ic.m45;

import nc.bs.pu.m21.query.ic.DirectOrderQueryForICBP;
import nc.pubitf.pu.m21.ic.m45.IDirectOrderQueryForIC;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ����ṩֱ�˽ӿ�ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 ����09:46:17
 */
public class DirectOrderQueryForICImpl implements IDirectOrderQueryForIC {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m21.ic.m45.IDirectOrderQueryForIC#directQueryForIC(java.lang.String[])
   */
  @Override
  public MapList<String, String> directQueryForIC(String[] bids)
      throws BusinessException {
    try {
      return new DirectOrderQueryForICBP().directQueryForIC(bids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}

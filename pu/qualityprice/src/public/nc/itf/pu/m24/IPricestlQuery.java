package nc.itf.pu.m24;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 ����07:41:37
 */
public interface IPricestlQuery {

  /**
   * ����������������ѯ�۸���㵥��
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-13 ����07:41:49
   */
  PricestlVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

}

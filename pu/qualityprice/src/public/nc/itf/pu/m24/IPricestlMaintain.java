package nc.itf.pu.m24;

import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��ɾ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-27 ����08:50:57
 */
public interface IPricestlMaintain {

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:02:05
   */
  void delete(PricestlVO[] vos) throws BusinessException;

  /**
   * ����������������������۸���㵥��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:51:38
   */
  PricestlVO[] insert(PricestlVO[] vos) throws BusinessException;

  /**
   * ������������������ͳһ��ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 ����08:45:33
   */
  PricestlVO[] saveBase(PricestlVO[] vos) throws BusinessException;

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:02:14
   */
  PricestlVO[] update(PricestlVO[] vos) throws BusinessException;

}

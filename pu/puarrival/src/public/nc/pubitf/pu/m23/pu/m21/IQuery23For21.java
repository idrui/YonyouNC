package nc.pubitf.pu.m23.pu.m21;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ϊ�ɹ������ṩ�ɲ������˻����Ĳ�ѯ�ӿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����04:24:59
 */
public interface IQuery23For21 {

  /**
   * ��������������Ϊ�ɹ������ṩ�ɲ������˻����Ĳ�ѯ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   *          ��ѯ���
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:26:04
   */
  ArriveVO[] queryBackArrive(IQueryScheme queryScheme) throws BusinessException;

  /**
   * �ж϶����Ƿ��е���
   * 
   * @param bids ��������
   * @return ���û�з���null����false
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryHaveArrive(String[] bids)
      throws BusinessException;

  /**
   * �ж϶��������ƻ��Ƿ��е���
   * 
   * @param bbids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryHaveArriveByBBid(String[] bbids)
      throws BusinessException;
}

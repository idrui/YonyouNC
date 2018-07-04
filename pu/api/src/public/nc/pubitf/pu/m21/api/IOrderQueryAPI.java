package nc.pubitf.pu.m21.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>���ݲ�ѯ������ѯ�ɹ���������VO
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�ɹ�����������Ϣ����ѯ�ֶβ�����Ϊ�գ�
 * <li>���ݲ�ѯ������ѯ�ɹ�������ϸ��Ϣ
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�ɹ�������ϸ��Ϣ
 * <li>���ݲɹ�����ID��ѯ�ɹ�����
 * <li>���ݲɹ�������ID��ѯ�ɹ�������ͼVO
 * <li>������Դ������ID��ѯ�ɹ�����
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		�ɹ�������ѯ��������API
 * @since 6.5
 * @version 2015-10-29 ����9:21:53
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("�ɹ�����")
public interface IOrderQueryAPI {

	/**
	 * <B>���ݲ�ѯ������queryscheme����ѯ�ɹ�����������Ϣ</B>
	 * <li>��ѯ��������Ϣ</li>  
	 * <li>���ص���������VO�ֶ�</li>
	 * @param queryscheme ��ѯ����
	 * @return OrderVO[] �ɹ������ۺ�VO
	 * @throws BusinessException
	 */
	OrderVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme���͵����ֶβ�ѯ������ѯ�ɹ�����</B>
	 * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>���زɹ��������ӱ�VO</li>
   * <li>��������Ҫ��ѯ���ֶ���Ϣ</li>
	 * @param queryscheme ��ѯ����
	 * @param fileds �����ֶ�
	 * @return OrderVO[] �ɹ������ۺ�VO
	 * @throws BusinessException
	 */
	OrderVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme����ѯ�ɹ�������ϸ��Ϣ��</B>
	 * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>���زɹ�������ͼVO</li>
   * <li>���زɹ�����������ϸ�ֶ�</li>
	 * @param queryscheme ��ѯ����
	 * @return OrderViewVO[] �ɹ�������ͼVO
	 * @throws BusinessException
	 */
	OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme���͵����ֶβ�ѯ������ѯ�ɹ�������ϸ��Ϣ</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>���ص�������ͼVO</li>
   * <li>��Ҫ��ѯ����ϸ�ֶ�</li>
	 * @param queryscheme ��ѯ����
	 * @param fileds �����ֶ�
	 * @return OrderViewVO[] �ɹ�������ͼVO
	 * @throws BusinessException
	 */
	OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>���ݲɹ�����IDs��ѯ�ɹ�����</B>
   * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>�������ӱ�VO</li>
   * <li>���زɹ����������ֶ���Ϣ</li>
   * @param ids �ɹ�����ID
   * @return OrderVO[] �ɹ�����VO
   * @throws BusinessException
   */
	OrderVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>���ݲɹ�����IDs�͵����ֶΣ���ѯ�ɹ�����</B>
   * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>�������ӱ�VO</li>
   * <li>������Ҫ��ѯ���ֶ���Ϣ</li>
	 * @param ids �ɹ�����IDs
	 * @param fields �����ֶλ�ö���ֶΣ�fields��Ϊ�գ�
	 * @return OrderVO[] �ɹ�����VO
	 */
	OrderVO[] queryVOByIDs(String[] ids,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>���ݲɹ�������IDs��ѯ�ɹ�����</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>���زɹ�������ͼVO</li>
   * <li>���زɹ�����������ϸ�ֶ���Ϣ</li>
	 * @param bids �ɹ�����bids
	 * @return OrderViewVO[] �ɹ�������ͼVO
	 */
	OrderViewVO[] queryViewVOByBIDs(String[] bids) 
			throws BusinessException;
	
	/**
	 * <B>���ݲɹ�������IDs�͵����ֶβ�ѯ�ɹ�����</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>���زɹ�������ͼVO</li>
   * <li>������Ҫ��ѯ����ϸ�ֶ���Ϣ</li>
	 * @param bids �ɹ�����bids
	 * @param fields �����ֶ�
	 * @return PraybillViewVO[] �ɹ�������ͼVO
	 */
	OrderViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>���ݲɹ�������Դ������IDs��ѯ�ɹ�����</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>���زɹ�������ͼVO</li>
   * <li>���زɹ�����������ϸ�ֶ���Ϣ</li>
	 * @param srcbids �ɹ�������Դbids
	 * @return OrderViewVO[] �ɹ�������ͼVO
	 */
	OrderViewVO[] queryViewVOBySourceBIDs(String[] srcbids) 
			throws BusinessException;
}

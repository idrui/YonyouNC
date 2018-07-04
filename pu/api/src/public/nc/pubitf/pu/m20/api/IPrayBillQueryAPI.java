package nc.pubitf.pu.m20.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>���ݲ�ѯ������ѯ�빺������VO
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�빺��������Ϣ����ѯ�ֶβ�����Ϊ�գ�
 * <li>���ݲ�ѯ������ѯ�빺����ϸ��Ϣ
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�빺����ϸ��Ϣ
 * <li>�����빺��ID��ѯ�빺��
 * <li>�����빺����ID��ѯ�빺����ͼVO
 * <li>������Դ������ID��ѯ�빺��
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		�빺����ѯ����API
 * @since 6.5
 * @version 2015-10-29 ����9:34:28
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("�빺��")
public interface IPrayBillQueryAPI {

	/**
   * <B>���ݲ�ѯ������queryscheme����ѯ�빺��������Ϣ</B>
   * <li>��ѯ�빺��������Ϣ</li>  
	 * <li>�����빺������VO�ֶ�</li>
	 * @param queryscheme ��ѯ����
	 * @return PraybillVO[] �빺���ۺ�VO
	 * @throws BusinessException
	 */
	PraybillVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme���͵����ֶβ�ѯ������ѯ�빺��</B>
   * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>�����빺�����ӱ�VO</li>
   * <li>��������Ҫ��ѯ���ֶ���Ϣ</li>
	 * @param queryscheme ��ѯ����
	 * @param fileds �����ֶ�
	 * @return PraybillVO[] �빺���ۺ�VO
	 * @throws BusinessException
	 */
	PraybillVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme����ѯ�빺����ϸ��Ϣ</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>�����빺����ͼVO</li>
   * <li>�����빺��������ϸ�ֶ�<li>
	 * @param queryscheme ��ѯ����
	 * @return PraybillViewVO[] �빺����ͼVO
	 * @throws BusinessException
	 */
	PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>���ݲ�ѯ������queryscheme���͵����ֶβ�ѯ������ѯ�빺����ϸ��Ϣ</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>�����빺����ͼVO</li>
   * <li>��Ҫ��ѯ����ϸ�ֶ�</li>
	 * @param queryscheme ��ѯ����
	 * @param fileds �����ֶ�
	 * @return PraybillViewVO[] �빺����ͼVO
	 * @throws BusinessException
	 */
	PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>�����빺��������ѯ�빺��</B>
   * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>�������ӱ�VO</li>
   * <li>�����빺�������ֶ���Ϣ</li>
   * @param ids �빺��ID
   * @return PraybillVO[] �빺��VO
   * @throws BusinessException
   */
	PraybillVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>�����빺��IDs�͵����ֶΣ���ѯ�빺��</B>
   * <li>������Ϣ����ѭ����ԭ��</li>
   * <li>�������ӱ�VO</li>
   * <li>������Ҫ��ѯ���ֶ���Ϣ</li>
	 * @param ids �빺��IDs
	 * @param fields ��Ҫ��ѯ���ֶ�
	 * @return PraybillVO[] �빺��VO
	 */
	PraybillVO[] queryVOByIDs(String[] ids,String[] fields) throws BusinessException;
	
	/**
	 * <B>�����빺����IDs��ѯ�빺��</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>�����빺����ͼVO</li>
   * <li>�����빺��������ϸ�ֶ���Ϣ</li>
	 * @param bids �빺��bids
	 * @return PraybillViewVO[] �빺����ͼVO
	 */
	PraybillViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;
	
	/**
	 * <B>�����빺����IDs�͵����ֶβ�ѯ�빺��</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>�����빺����ͼVO</li>
   * <li>������Ҫ��ѯ����ϸ�ֶ���Ϣ</li>
	 * @param bids �빺��bids
	 * @param fields ��Ҫ��ѯ�ĵ����ֶ�
	 * @return PraybillViewVO[] �빺����ͼVO
	 */
	PraybillViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>�����빺����Դ������IDs��ѯ�빺��</B>
   * <li>����ѭ����ԭ�򣬰��ղ�ѯ������ѯ����ϸ����</li>
   * <li>�����빺����ͼVO</li>
   * <li>�����빺��������ϸ�ֶ���Ϣ</li>
	 * @param srcbids �빺����Դbids
	 * @return PraybillViewVO[] �빺����ͼVO
	 */
	PraybillViewVO[] queryViewVOBySourceBIDs(String[] sourcebids) 
			throws BusinessException;
}

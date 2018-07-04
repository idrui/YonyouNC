package nc.pubitf.pu.m21.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>�ɹ�������������
 * <li>���ݲɹ�����IDɾ���ɹ�����
 * <li>���ݲɹ�������ԴIDɾ���ɹ�����
 * <li>�ɹ���������
 * <li>�ɹ���������
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *	      �ɹ������־û�����
 * @since 6.5
 * @version 2015-10-27 ����7:22:43
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("�ɹ�����")
public interface IOrderMaintainAPI {
	
	/**
	 * <b>�ɹ�������������</b> <li>��ǰ̨�������ݲ�ȫ��Ϣ<li>
	 * У�������У�
	 * <ol>
	 * <li>�ֶηǿ�У��</li>
	 * <li>�ֶ�ֵ�Ϸ���У��</li>
	 * </ol>
	 * ��ȫ�����У�
	 * <ol>
	 * <li>�����ĵ��ݺţ��кŲ�ȫ</li>
	 * <li>�������������ȱ����ȫ</li>
	 * <li>
	 * </ol>
	 * 
	 * @param bills
	 *          �ɹ���������
	 * @return OrderVO[] �����Ĳɹ���������
	 * @throws BusinessException
	 */
	OrderVO[] insertBills(OrderVO[] bills) throws BusinessException;

	/**
	 * <B>�����������������ݲɹ�����IDɾ���ɹ�����</B>
	 * 
	 * @param ids
	 *          �ɹ�����id����
	 * @throws BusinessException
	 */
	void deleteBillsByIDs(String[] ids) throws BusinessException;

	/**
	 * <B>�����������������ݲɹ�������ԴIDɾ���ɹ�����</B>
	 * 
	 * @param sourceids
	 *          �ɹ�������Դid����
	 * @throws BusinessException
	 */
	void deleteBillsBySourceIDs(String[] sourceids) throws BusinessException;

	/**
	 * <B>���������������ɹ���������<B/>
	 * 
	 * @param bills
	 *          �ɹ���������
	 * @return OrderVO[] ������Ĳɹ���������
	 * @throws BusinessException
	 */
	OrderVO[] approve(OrderVO[] bills) throws BusinessException;

	/**
	 * <B>�ɹ���������</B>
	 * 
	 * @param bills
	 *          ������Ĳɹ�����
	 * @throws BusinessException
	 * @return OrderVO[] �����Ĳɹ�����
	 */
	OrderVO[] unApprove(OrderVO[] bills) throws BusinessException;
}

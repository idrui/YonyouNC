/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ICgfaReviseService.java 
 * @Prject: pu
 * @Package: nc.itf.pu.cgfarevise 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��1��24�� ����1:56:08 
 * @version: V6.5   
 */
package nc.itf.pu.cgfarevise;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.it.m5801.entity.ContractViewVO;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.CgfaViewVO;
import nc.vo.pub.BusinessException;

/** 
 * @ClassName: ICgfaReviseService 
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��1��24�� ����1:56:08  
 */
public interface ICgfaReviseService {
	
	/**
	   * ����ɾ��
	   * 
	   * @param bills
	   * @return vos
	   * @throws BusinessException
	   */
	AggCgfa[] deleteCgfaForRevise(AggCgfa[] bills)
	      throws BusinessException;

	  /**
	   * ����ɾ��
	   * 
	   * @param bills
	   * @throws BusinessException
	   */
	  void deleteCgfaForRevising(AggCgfa[] bills) throws BusinessException;

	  /**
	   * ���ݲ�ѯ
	   * 
	   * @param keys
	   * @return vos
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForRevise(String[] keys) throws BusinessException;

	  /**
	   * ���ݲ�ѯ���޶���
	   * 
	   * @param scheme UI����֯�Ĳ�ѯ��an
	   * @return ���յ��ݺŽ�������ĵ������顣��������ʽ��ֻ�е�һ�ŵ��ݲ��б�
	   *         �����ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForReviseApp(IQueryScheme scheme)
	      throws BusinessException;

	  /**
	   * ���ݲ�ѯ(��ѯ���޶��ĵ���)
	   * 
	   * @param srcKeys
	   * @return vos
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForRevised(String[] srcKeys) throws BusinessException;



	  /**
	   * �޶�����
	   * 
	   * @param fullBills UI����֯�Ĵ�����ĵ������顣������VO��ֻ����������TS�Լ�
	   *          �ı���ֶ�
	   * @param isSave
	   * @return �ص�������vo ������VO��ֻ����������TS�Լ��������˸ı���ֶ�
	   * @throws BusinessException
	   */
	  AggCgfa[] saveCgfaForRevise(AggCgfa[] fullBills, boolean isSave)
	      throws BusinessException;

	  /**
	   * ��ѯ���ں�ͬViewVO
	   * 
	   * @param bids
	   * @return ContractViewVO
	   */
	  Map<String, CgfaViewVO> queryCgfaViewForCgfa(String[] bids);

}

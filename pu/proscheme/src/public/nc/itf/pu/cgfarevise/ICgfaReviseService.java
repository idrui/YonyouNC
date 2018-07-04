/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ICgfaReviseService.java 
 * @Prject: pu
 * @Package: nc.itf.pu.cgfarevise 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年1月24日 下午1:56:08 
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
 * @date: 2018年1月24日 下午1:56:08  
 */
public interface ICgfaReviseService {
	
	/**
	   * 单据删除
	   * 
	   * @param bills
	   * @return vos
	   * @throws BusinessException
	   */
	AggCgfa[] deleteCgfaForRevise(AggCgfa[] bills)
	      throws BusinessException;

	  /**
	   * 单据删除
	   * 
	   * @param bills
	   * @throws BusinessException
	   */
	  void deleteCgfaForRevising(AggCgfa[] bills) throws BusinessException;

	  /**
	   * 单据查询
	   * 
	   * @param keys
	   * @return vos
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForRevise(String[] keys) throws BusinessException;

	  /**
	   * 单据查询（修订）
	   * 
	   * @param scheme UI端组织的查询方an
	   * @return 按照单据号进行排序的单据数组。懒加载形式，只有第一张单据才有表
	   *         体数据。没有查询到数据时返回零长度的数组
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForReviseApp(IQueryScheme scheme)
	      throws BusinessException;

	  /**
	   * 单据查询(查询被修订的单据)
	   * 
	   * @param srcKeys
	   * @return vos
	   * @throws BusinessException
	   */
	  AggCgfa[] queryCgfaForRevised(String[] srcKeys) throws BusinessException;



	  /**
	   * 修订保存
	   * 
	   * @param fullBills UI端组织的待保存的单据数组。轻量级VO，只包含主键、TS以及
	   *          改变的字段
	   * @param isSave
	   * @return 回单保存后的vo 轻量级VO，只返回主键、TS以及服务器端改变的字段
	   * @throws BusinessException
	   */
	  AggCgfa[] saveCgfaForRevise(AggCgfa[] fullBills, boolean isSave)
	      throws BusinessException;

	  /**
	   * 查询进口合同ViewVO
	   * 
	   * @param bids
	   * @return ContractViewVO
	   */
	  Map<String, CgfaViewVO> queryCgfaViewForCgfa(String[] bids);

}

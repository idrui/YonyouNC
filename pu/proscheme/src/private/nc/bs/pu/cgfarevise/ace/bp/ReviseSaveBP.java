/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ReviseSaveBP.java 
 * @Prject: pu
 * @Package: nc.bs.pu.cgfarevise.ace.bp 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年1月24日 下午2:15:04 
 * @version: V6.5   
 */
package nc.bs.pu.cgfarevise.ace.bp;

import java.util.ArrayList;
import java.util.List;
import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.template.ICompareOperator;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * @ClassName: ReviseSaveBP
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年1月24日 下午2:15:04
 */
public class ReviseSaveBP implements ICompareOperator<AggCgfa> {

	/**
	 * @Title:ReviseSaveBP
	 * @Description:TODO
	 */
	public ReviseSaveBP() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: operate
	 * 
	 * @Description: TODO
	 * 
	 * @param vos
	 * 
	 * @param originVOs
	 * 
	 * @return
	 * 
	 * @see
	 * nc.impl.pubapp.pattern.rule.template.ICompareOperator#operate(java.lang
	 * .Object[], java.lang.Object[])
	 */
	@Override
	public AggCgfa[] operate(AggCgfa[] bills, AggCgfa[] originBills) {
		// TODO 自动生成的方法存根

		AggCgfa[] vos = null;
		AggCgfa originBill = originBills[0];
		AggCgfa revisedVO = (AggCgfa) originBill.clone();// 被修订单据
		this.operateRevised(bills, revisedVO);
		vos = this.oprateRevising(bills, originBill);
		return vos;

	}

	/**
	 * 被修订操作
	 * 
	 * @param bills
	 *            新单
	 * @param originBill
	 *            原单
	 * @param originClone
	 *            原单副本
	 */
	private void operateRevised(AggCgfa[] bills, AggCgfa revisedVO) {
		this.processRevisedBill(revisedVO);
		for (AggCgfa bill : bills) {
			Cgfa hvo = bill.getParentVO();
			// 该张单据是否进行了修订
			// 自定义项没有确定
			hvo.setAttributeValue("vdef121", "Y");
			;
		}

		Cgfa revisedHVO = revisedVO.getParentVO();
		// 不用billupdate主要原因是更新的字段比较少
		VOUpdate<ISuperVO> voUpdate = new VOUpdate<ISuperVO>();
		String[] updateHeadNames = new String[] { "修订的来源", "被修订的标志" };
		voUpdate.update(new Cgfa[] { revisedHVO }, updateHeadNames);
		IBillMeta billMeta = revisedVO.getMetaData();
		IVOMeta[] childMetas = billMeta.getChildren();
		String[] updateBodyNames = new String[] { "CREVISESRCBID" };
		for (IVOMeta childMeta : childMetas) {
			ISuperVO[] bodyVOs = revisedVO.getChildren(childMeta);
			if (bodyVOs == null || bodyVOs.length == 0) {
				continue;
			}
			voUpdate.update(bodyVOs, updateBodyNames);
		}
	}

	/**
	 * 处理被修订单据
	 * 
	 * @param originBill
	 *            原始单据
	 * @param revisedVO
	 *            被修订单据
	 */
	private void processRevisedBill(AggCgfa revisedVO) {
		Cgfa revisedHVO = revisedVO.getParentVO();
		boolean isFirstRevise = true;
		// 获取修订的版本
		String version = (String) revisedHVO.getAttributeValue("vdef");
		// 表头的是否被修订
		revisedHVO.setAttributeValue("vdefklajlkf", "Y");// (ReviseStatus.REVISED.getIntegerValue());
		// 第一次修订是时候补充来源id,此过程可以在审核的时候做,在这里实习主要是支持项目的需要
		if (isFirstRevise) {
			// 表头记录修订的来源表头记录(可能需要新增字段)
			revisedHVO.setAttributeValue("vdef10", revisedHVO.getPrimaryKey());
			IBillMeta billMeta = revisedVO.getMetaData();
			IVOMeta[] childMetas = billMeta.getChildren();
			for (IVOMeta childMeta : childMetas) {
				ISuperVO[] revisedChild = revisedVO.getChildren(childMeta);
				for (ISuperVO rChildVO : revisedChild) {
					String rKey = rChildVO.getPrimaryKey();
					rChildVO.setAttributeValue("CREVISESRCBID", rKey);
				}
			}
		}
	}

	/**
	 * 修订中操作
	 * 
	 * @param bills
	 *            新单
	 * @param originBill
	 *            原单
	 * @return
	 */
	private AggCgfa[] oprateRevising(AggCgfa[] bills, AggCgfa originBill) {
		InsertBPTemplate<AggCgfa> insertTemplate = new InsertBPTemplate<AggCgfa>(
				CgfaPluginPoint.REVISESAVE);
		AroundProcesser<AggCgfa> insertProcesser = insertTemplate
				.getAroundProcesser();
		this.addBeforeRule(insertProcesser);
		AggCgfa[] vos = null;
		AggCgfa insertBill = (AggCgfa) bills[0].clone();
		this.ognizeBill(new AggCgfa[] { insertBill });
		this.processRevisingBill(originBill, insertBill);
		// 新增修订的单据
		vos = insertTemplate.insert(new AggCgfa[] { insertBill });
		return vos;
	}

	/**
	 * @Title: addBeforeRule
	 * @Description: TODO
	 * @param insertProcesser
	 * @return: void
	 */
	private void addBeforeRule(AroundProcesser<AggCgfa> insertProcesser) {
		// TODO 自动生成的方法存根

	}

	private AggCgfa[] ognizeBill(AggCgfa[] bills) {
		for (IBill bill : bills) {
			this.ognizeBill(bill);
		}
		return bills;
	}

	private void ognizeBill(IBill bill) {
		IBillMeta billMeta = bill.getMetaData();
		IVOMeta[] childMetas = billMeta.getChildren();
		for (IVOMeta childMeta : childMetas) {
			ISuperVO[] vos = bill.getChildren(childMeta);
			if (vos == null || vos.length == 0) {
				continue;
			}
			this.orgnize(bill, vos);
		}
	}

	private void orgnize(IBill bill, ISuperVO[] vos) {
		if (vos == null || vos.length == 0) {
			return;
		}
		List<ISuperVO> list = new ArrayList<ISuperVO>();
		for (ISuperVO vo : vos) {
			if (vo.getStatus() == VOStatus.DELETED) {
				continue;
			}
			list.add(vo);
		}
		ListToArrayTool<ISuperVO> tool = new ListToArrayTool<ISuperVO>();
		if (list.size() == 0) {
			// 当前子表行已经全部被删除掉
			ISuperVO[] children = Constructor.construct(vos[0].getClass(), 0);
			bill.setChildren(vos[0].getMetaData(), children);
			return;
		}
		ISuperVO[] tempVOs = tool.convertToArray(list);
		bill.setChildren(vos[0].getMetaData(), tempVOs);
	}

	/**
	 * 处理修订中的单据
	 * 
	 * @param originBill
	 *            原始单据
	 * @param revisingVO
	 *            修订中单据
	 */
	private void processRevisingBill(AggCgfa originBill, AggCgfa revisingVO) {
		Cgfa originHVO = originBill.getParentVO();
		Cgfa revisingHVO = revisingVO.getParentVO();
		this.processRevisingHVO(revisingHVO, originHVO);
		this.processRevisingBVO(originBill, revisingVO);
	}

	/**
	 * 处理修订中主实体
	 * 
	 * @param revisingHVO
	 *            修订中的单据
	 * @param originHVO
	 *            原始单据
	 */
	private void processRevisingHVO(Cgfa revisingHVO, Cgfa originHVO) {
		boolean isFirstRevise = true;
		Integer version = (Integer) originHVO.getAttributeValue("Iversion");
		if (version != null) {
			isFirstRevise = version.compareTo(Integer.valueOf(0)) <= 1;
		}
		// 设置修订中的数据
		revisingHVO.setAttributeValue("Approver", null);
		revisingHVO.setAttributeValue("Taudittime", -1);
		revisingHVO.setAttributeValue("Fpfstatusflag", -1);
		revisingHVO.setAttributeValue("Fstatusflag", -1);
		revisingHVO.setAttributeValue("Creviser", AppContext.getInstance()
				.getPkUser());
		revisingHVO.setAttributeValue("Drevisiondate", AppContext.getInstance()
				.getBusiDate());
		revisingHVO
				.setAttributeValue("crevisesrcid", originHVO.getPrimaryKey());

		revisingHVO.setPrimaryKey(null);
		// 版本号加一
		int iversion = revisingHVO.getAttributeValue("iversion") == null ? 1
				: (Integer) revisingHVO.getAttributeValue("iversion");
		revisingHVO.setAttributeValue("iversion", Integer.valueOf(++iversion));
	}

	/**
	 * 处理修订中的子实体
	 * 
	 * @param originBill
	 *            原始单据
	 * @param revisingVO
	 *            修订中单据
	 */
	private void processRevisingBVO(AggCgfa originBill, AggCgfa revisingVO) {
		Cgfab[] orgChild = (Cgfab[]) originBill.getChildrenVO();
		Cgfab[] revisChild = (Cgfab[]) revisingVO.getChildrenVO();

		for (int j = 0, l = revisChild.length; j < l; j++) {
			SuperVO reivsingChlidren = revisChild[j];
			reivsingChlidren.setAttributeValue("pk_cgfa", null);
			Object revisesrcbid = reivsingChlidren
					.getAttributeValue("CREVISESRCBID");
			String childPk = reivsingChlidren.getPrimaryKey();
			for (SuperVO originChildrenVO : orgChild) {
				if (childPk != null
						&& childPk.equals(originChildrenVO.getPrimaryKey())
						&& revisesrcbid == null) {
					reivsingChlidren.setAttributeValue("来源子表修订",
							originChildrenVO.getPrimaryKey());
				}

			}
			reivsingChlidren.setPrimaryKey(null);
		}
	}

}

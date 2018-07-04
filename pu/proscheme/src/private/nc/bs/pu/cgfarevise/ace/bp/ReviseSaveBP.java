/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ReviseSaveBP.java 
 * @Prject: pu
 * @Package: nc.bs.pu.cgfarevise.ace.bp 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��1��24�� ����2:15:04 
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
 * @date: 2018��1��24�� ����2:15:04
 */
public class ReviseSaveBP implements ICompareOperator<AggCgfa> {

	/**
	 * @Title:ReviseSaveBP
	 * @Description:TODO
	 */
	public ReviseSaveBP() {
		// TODO �Զ����ɵĹ��캯�����
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
		// TODO �Զ����ɵķ������

		AggCgfa[] vos = null;
		AggCgfa originBill = originBills[0];
		AggCgfa revisedVO = (AggCgfa) originBill.clone();// ���޶�����
		this.operateRevised(bills, revisedVO);
		vos = this.oprateRevising(bills, originBill);
		return vos;

	}

	/**
	 * ���޶�����
	 * 
	 * @param bills
	 *            �µ�
	 * @param originBill
	 *            ԭ��
	 * @param originClone
	 *            ԭ������
	 */
	private void operateRevised(AggCgfa[] bills, AggCgfa revisedVO) {
		this.processRevisedBill(revisedVO);
		for (AggCgfa bill : bills) {
			Cgfa hvo = bill.getParentVO();
			// ���ŵ����Ƿ�������޶�
			// �Զ�����û��ȷ��
			hvo.setAttributeValue("vdef121", "Y");
			;
		}

		Cgfa revisedHVO = revisedVO.getParentVO();
		// ����billupdate��Ҫԭ���Ǹ��µ��ֶαȽ���
		VOUpdate<ISuperVO> voUpdate = new VOUpdate<ISuperVO>();
		String[] updateHeadNames = new String[] { "�޶�����Դ", "���޶��ı�־" };
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
	 * �����޶�����
	 * 
	 * @param originBill
	 *            ԭʼ����
	 * @param revisedVO
	 *            ���޶�����
	 */
	private void processRevisedBill(AggCgfa revisedVO) {
		Cgfa revisedHVO = revisedVO.getParentVO();
		boolean isFirstRevise = true;
		// ��ȡ�޶��İ汾
		String version = (String) revisedHVO.getAttributeValue("vdef");
		// ��ͷ���Ƿ��޶�
		revisedHVO.setAttributeValue("vdefklajlkf", "Y");// (ReviseStatus.REVISED.getIntegerValue());
		// ��һ���޶���ʱ�򲹳���Դid,�˹��̿�������˵�ʱ����,������ʵϰ��Ҫ��֧����Ŀ����Ҫ
		if (isFirstRevise) {
			// ��ͷ��¼�޶�����Դ��ͷ��¼(������Ҫ�����ֶ�)
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
	 * �޶��в���
	 * 
	 * @param bills
	 *            �µ�
	 * @param originBill
	 *            ԭ��
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
		// �����޶��ĵ���
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
		// TODO �Զ����ɵķ������

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
			// ��ǰ�ӱ����Ѿ�ȫ����ɾ����
			ISuperVO[] children = Constructor.construct(vos[0].getClass(), 0);
			bill.setChildren(vos[0].getMetaData(), children);
			return;
		}
		ISuperVO[] tempVOs = tool.convertToArray(list);
		bill.setChildren(vos[0].getMetaData(), tempVOs);
	}

	/**
	 * �����޶��еĵ���
	 * 
	 * @param originBill
	 *            ԭʼ����
	 * @param revisingVO
	 *            �޶��е���
	 */
	private void processRevisingBill(AggCgfa originBill, AggCgfa revisingVO) {
		Cgfa originHVO = originBill.getParentVO();
		Cgfa revisingHVO = revisingVO.getParentVO();
		this.processRevisingHVO(revisingHVO, originHVO);
		this.processRevisingBVO(originBill, revisingVO);
	}

	/**
	 * �����޶�����ʵ��
	 * 
	 * @param revisingHVO
	 *            �޶��еĵ���
	 * @param originHVO
	 *            ԭʼ����
	 */
	private void processRevisingHVO(Cgfa revisingHVO, Cgfa originHVO) {
		boolean isFirstRevise = true;
		Integer version = (Integer) originHVO.getAttributeValue("Iversion");
		if (version != null) {
			isFirstRevise = version.compareTo(Integer.valueOf(0)) <= 1;
		}
		// �����޶��е�����
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
		// �汾�ż�һ
		int iversion = revisingHVO.getAttributeValue("iversion") == null ? 1
				: (Integer) revisingHVO.getAttributeValue("iversion");
		revisingHVO.setAttributeValue("iversion", Integer.valueOf(++iversion));
	}

	/**
	 * �����޶��е���ʵ��
	 * 
	 * @param originBill
	 *            ԭʼ����
	 * @param revisingVO
	 *            �޶��е���
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
					reivsingChlidren.setAttributeValue("��Դ�ӱ��޶�",
							originChildrenVO.getPrimaryKey());
				}

			}
			reivsingChlidren.setPrimaryKey(null);
		}
	}

}

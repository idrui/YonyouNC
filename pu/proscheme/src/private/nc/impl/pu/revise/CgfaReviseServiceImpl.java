/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: CgfaReviseServiceImpl.java 
 * @Prject: pu
 * @Package: nc.impl.pu.revise 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��1��24�� ����2:00:33 
 * @version: V6.5   
 */
package nc.impl.pu.revise;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.it.m5801.pluginpoint.Operator;
import nc.bs.pu.cgfarevise.ace.bp.ReviseSaveBP;
import nc.bs.scmpub.operator.bill.QueryOperator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.template.CompareOperatorTemplate;
import nc.itf.it.m5801.IM5801Maintain;
import nc.itf.pu.ICgfaMaintain;
import nc.itf.pu.cgfarevise.ICgfaReviseService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ftpub.query.FTPUBQueryApproveUtil;
import nc.vo.ftpub.res.FTBusinessCheck;
import nc.vo.ftpub.res.enumeration.ReviseStatus;
import nc.vo.it.m5801.entity.ContractHVO;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.it.m5801.enumeration.BillStatus;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.CgfaViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @ClassName: CgfaReviseServiceImpl
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��1��24�� ����2:00:33
 */
public class CgfaReviseServiceImpl implements ICgfaReviseService {

	public CgfaReviseServiceImpl() {
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public AggCgfa[] deleteCgfaForRevise(AggCgfa[] bills)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void deleteCgfaForRevising(AggCgfa[] bills) throws BusinessException {
		// TODO �Զ����ɵķ������

	}

	@Override
	public AggCgfa[] queryCgfaForRevise(String[] keys) throws BusinessException {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public AggCgfa[] queryCgfaForReviseApp(IQueryScheme scheme)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		QueryOperator<AggCgfa> query = new QueryOperator<AggCgfa>(AggCgfa.class);
		this.createReviseRefSqlByQueryScheme(scheme);
		AggCgfa[] bills = null;
		try {
			bills = query.query(scheme, ContractHVO.VBILLCODE);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
		return bills;
	}

	@Override
	public AggCgfa[] queryCgfaForRevised(String[] srcKeys)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public AggCgfa[] saveCgfaForRevise(AggCgfa[] fullBills, boolean isSave)
			throws BusinessException {

		AggCgfa[] retBills = null;
		AggCgfa[] vos = null;
		try {
			BillTransferTool<AggCgfa> transTool = new BillTransferTool<AggCgfa>(
					fullBills);
			AggCgfa[] newbills = transTool.getClientFullInfoBill();
			AggCgfa[] originBills = transTool.getOriginBills();
			Cgfa originHVO = originBills[0].getParentVO();
			// �޶�
			// �Զ�����"�Ƿ��޶�"
			String reviseStatus = (String) originHVO
					.getAttributeValue("vdefXX");
			reviseStatus = reviseStatus == null ? "Y" : reviseStatus;
			// �����޶�
			if (reviseStatus.equals(ReviseStatus.COMMON.integerValue())) {
				ReviseSaveBP bp = new ReviseSaveBP();
				CompareOperatorTemplate<AggCgfa> template = new CompareOperatorTemplate<AggCgfa>(
						Operator.REVISESAVE, bp);
				vos = template.operate(newbills, originBills);
			} else {
				ICgfaMaintain service = NCLocator.getInstance().lookup(
						ICgfaMaintain.class);
				vos = service.update(newbills, originBills);
			}

			retBills = transTool.getBillForToClient(vos);
		} catch (Exception ex) {
			ExceptionUtils.wrappException(ex);
		}
		return retBills;
	}

	@Override
	public Map<String, CgfaViewVO> queryCgfaViewForCgfa(String[] bids) {
		// TODO �Զ����ɵķ������
		return null;
	}

	private void createReviseRefSqlByQueryScheme(IQueryScheme scheme) {
		QuerySchemeProcessor qsp = new QuerySchemeProcessor(scheme);
		SqlBuilder whr = new SqlBuilder();
		// ��ѯ�������Ѿ��޶�����û�б�ɾ���ĵ���
		qsp.appendWhere(whr.toString());
	}

}

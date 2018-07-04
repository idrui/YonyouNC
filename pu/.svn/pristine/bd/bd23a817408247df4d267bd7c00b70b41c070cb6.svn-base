package nc.impl.pub.ace;

import nc.bs.pu.cgfa.ace.bp.AceCgfaInsertBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaUpdateBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaDeleteBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaSendApproveBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaUnSendApproveBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaApproveBP;
import nc.bs.pu.cgfa.ace.bp.AceCgfaUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCgfaPubServiceImpl {
	// 新增
	public AggCgfa[] pubinsertBills(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggCgfa> transferTool = new BillTransferTool<AggCgfa>(
					clientFullVOs);
			// 调用BP
			AceCgfaInsertBP action = new AceCgfaInsertBP();
			AggCgfa[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceCgfaDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCgfa[] pubupdateBills(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCgfa> transferTool = new BillTransferTool<AggCgfa>(
					clientFullVOs);
			AceCgfaUpdateBP bp = new AceCgfaUpdateBP();
			AggCgfa[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCgfa[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCgfa[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCgfa> query = new BillLazyQuery<AggCgfa>(
					AggCgfa.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggCgfa[] pubsendapprovebills(
			AggCgfa[] clientFullVOs, AggCgfa[] originBills)
			throws BusinessException {
		AceCgfaSendApproveBP bp = new AceCgfaSendApproveBP();
		AggCgfa[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCgfa[] pubunsendapprovebills(
			AggCgfa[] clientFullVOs, AggCgfa[] originBills)
			throws BusinessException {
		AceCgfaUnSendApproveBP bp = new AceCgfaUnSendApproveBP();
		AggCgfa[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCgfa[] pubapprovebills(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCgfaApproveBP bp = new AceCgfaApproveBP();
		AggCgfa[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCgfa[] pubunapprovebills(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCgfaUnApproveBP bp = new AceCgfaUnApproveBP();
		AggCgfa[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}
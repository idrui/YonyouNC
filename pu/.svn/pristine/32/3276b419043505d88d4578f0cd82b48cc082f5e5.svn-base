package nc.impl.pub.ace;

import nc.bs.pu.qst.newoaspwh.ace.bp.AceNewoaspwhInsertBP;
import nc.bs.pu.qst.newoaspwh.ace.bp.AceNewoaspwhUpdateBP;
import nc.bs.pu.qst.newoaspwh.ace.bp.AceNewoaspwhDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

public abstract class AceNewoaspwhPubServiceImpl {
	// 新增
	public AggNewoaspwhaHeadVO[] pubinsertBills(AggNewoaspwhaHeadVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggNewoaspwhaHeadVO> transferTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			AggNewoaspwhaHeadVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceNewoaspwhInsertBP action = new AceNewoaspwhInsertBP();
			AggNewoaspwhaHeadVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggNewoaspwhaHeadVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggNewoaspwhaHeadVO> transferTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			AggNewoaspwhaHeadVO[] fullBills = transferTool.getClientFullInfoBill();
			AceNewoaspwhDeleteBP deleteBP = new AceNewoaspwhDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggNewoaspwhaHeadVO[] pubupdateBills(AggNewoaspwhaHeadVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggNewoaspwhaHeadVO> transTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			// 补全前台VO
			AggNewoaspwhaHeadVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggNewoaspwhaHeadVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceNewoaspwhUpdateBP bp = new AceNewoaspwhUpdateBP();
			AggNewoaspwhaHeadVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggNewoaspwhaHeadVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggNewoaspwhaHeadVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggNewoaspwhaHeadVO> query = new BillLazyQuery<AggNewoaspwhaHeadVO>(
					AggNewoaspwhaHeadVO.class);
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

}
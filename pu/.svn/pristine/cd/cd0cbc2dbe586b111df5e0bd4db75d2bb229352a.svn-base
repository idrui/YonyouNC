package nc.impl.pub.ace;

import nc.bs.pu.qst.newoabase.ace.bp.AceNewoabaseInsertBP;
import nc.bs.pu.qst.newoabase.ace.bp.AceNewoabaseUpdateBP;
import nc.bs.pu.qst.newoabase.ace.bp.AceNewoabaseDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.ecpubapp.pattern.database.DataAccessUtils;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ecpubapp.pattern.data.IRowSet;
import nc.vo.lm.oaspjg.AggOaspjgHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;

public abstract class AceNewoabasePubServiceImpl {
	// 新增
	public AggNewoabaseaHeadVO[] pubinsertBills(AggNewoabaseaHeadVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggNewoabaseaHeadVO> transferTool = new BillTransferTool<AggNewoabaseaHeadVO>(
					vos);
			AggNewoabaseaHeadVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceNewoabaseInsertBP action = new AceNewoabaseInsertBP();
			AggNewoabaseaHeadVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggNewoabaseaHeadVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggNewoabaseaHeadVO> transferTool = new BillTransferTool<AggNewoabaseaHeadVO>(
					vos);
			AggNewoabaseaHeadVO[] fullBills = transferTool.getClientFullInfoBill();
			AceNewoabaseDeleteBP deleteBP = new AceNewoabaseDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggNewoabaseaHeadVO[] pubupdateBills(AggNewoabaseaHeadVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggNewoabaseaHeadVO> transTool = new BillTransferTool<AggNewoabaseaHeadVO>(
					vos);
			// 补全前台VO
			AggNewoabaseaHeadVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggNewoabaseaHeadVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceNewoabaseUpdateBP bp = new AceNewoabaseUpdateBP();
			AggNewoabaseaHeadVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggNewoabaseaHeadVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggNewoabaseaHeadVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggNewoabaseaHeadVO> query = new BillLazyQuery<AggNewoabaseaHeadVO>(
					AggNewoabaseaHeadVO.class);
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
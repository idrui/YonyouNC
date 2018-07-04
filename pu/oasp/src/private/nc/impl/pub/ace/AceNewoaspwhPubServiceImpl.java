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
	// ����
	public AggNewoaspwhaHeadVO[] pubinsertBills(AggNewoaspwhaHeadVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggNewoaspwhaHeadVO> transferTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			AggNewoaspwhaHeadVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceNewoaspwhInsertBP action = new AceNewoaspwhInsertBP();
			AggNewoaspwhaHeadVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggNewoaspwhaHeadVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggNewoaspwhaHeadVO> transferTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			AggNewoaspwhaHeadVO[] fullBills = transferTool.getClientFullInfoBill();
			AceNewoaspwhDeleteBP deleteBP = new AceNewoaspwhDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggNewoaspwhaHeadVO[] pubupdateBills(AggNewoaspwhaHeadVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggNewoaspwhaHeadVO> transTool = new BillTransferTool<AggNewoaspwhaHeadVO>(
					vos);
			// ��ȫǰ̨VO
			AggNewoaspwhaHeadVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggNewoaspwhaHeadVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceNewoaspwhUpdateBP bp = new AceNewoaspwhUpdateBP();
			AggNewoaspwhaHeadVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
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
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

}
package nc.ui.pu.m20.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPuMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class PrayBillPageService implements IBillPageQuery,
		IPaginationInitQuery {

	@Override
	public Integer getPageSize() {
		return Integer.valueOf(10);
	}

	@Override
	public PageQueryVO query(IQueryScheme scheme) {
		@SuppressWarnings("unchecked")
		IPuMaintainApp<PraybillVO> service = NCLocator.getInstance().lookup(
				IPuMaintainApp.class);
		QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(scheme);
		this.makeLogicalCondition(qrySchemeProcessor);
		PageQueryVO page = null;
		try {
			page = service.queryPuApp(scheme, PraybillVO.class,
					PraybillHeaderVO.BISLATEST, PraybillHeaderVO.FBILLSTATUS,
					PraybillHeaderVO.VBILLCODE, POBillType.PrayBill);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

	private void makeLogicalCondition(QuerySchemeProcessor qrySchemeProcessor) {
		String tableB = qrySchemeProcessor
				.getTableAliasOfAttribute("pk_praybill_b.pk_org");
		// 处理 是否已经生成合同
		QueryCondition cond = qrySchemeProcessor
				.getQueryCondition(PraybillHeaderVO.BISNGENCT);
		if (null != cond && UFBoolean.valueOf(cond.getValues()[0]).booleanValue()) {
			qrySchemeProcessor.appendWhere(" and " + tableB + "."
					+ PraybillItemVO.NGENCT + " > 0");
		} else if (null != cond
				&& !UFBoolean.valueOf(cond.getValues()[0]).booleanValue()) {
			qrySchemeProcessor.appendWhere(" and " + tableB + "."
					+ PraybillItemVO.NGENCT + " = 0");
		}

		// 待审批
		// cond =
		// qrySchemeProcessor.getLogicalCondition(PraybillHeaderVO.BISWAITAUDIT);
	}

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		@SuppressWarnings("unchecked")
		IPuMaintainApp<PraybillVO> service = NCLocator.getInstance().lookup(
				IPuMaintainApp.class);
		IBill[] bills = null;
		try {
			bills = service.queryPuApp(pks, PraybillVO.class);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return bills;
	}

	@Override
	public PaginationQueryVO queryPaginationQueryVO(String condition,
			Integer billcount, Object... userobj) throws BusinessException {
		return null;
	}

}

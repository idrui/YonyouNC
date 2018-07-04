package nc.ui.pu.qst.newoabase.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;
import nc.itf.pu.INewoabaseMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceNewoabaseMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggNewoabaseaHeadVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		INewoabaseMaintain operator = NCLocator.getInstance().lookup(
				INewoabaseMaintain.class);
		AggNewoabaseaHeadVO[] vos = operator.insert((AggNewoabaseaHeadVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		INewoabaseMaintain operator = NCLocator.getInstance().lookup(
				INewoabaseMaintain.class);
		AggNewoabaseaHeadVO[] vos = operator.update((AggNewoabaseaHeadVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		INewoabaseMaintain operator = NCLocator.getInstance().lookup(
				INewoabaseMaintain.class);
		operator.delete((AggNewoabaseaHeadVO[]) value);
		return value;
	}
	
	@Override
	public AggNewoabaseaHeadVO operateBill(AggNewoabaseaHeadVO bill) throws Exception {
		INewoabaseMaintain operator = NCLocator.getInstance().lookup(
				INewoabaseMaintain.class);
		operator.delete(new AggNewoabaseaHeadVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		INewoabaseMaintain query = NCLocator.getInstance().lookup(
				INewoabaseMaintain.class);
		return query.query(queryScheme);
	}

}

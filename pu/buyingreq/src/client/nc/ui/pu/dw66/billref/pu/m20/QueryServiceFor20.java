package nc.ui.pu.dw66.billref.pu.m20;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.trade.business.HYPubBO;
import nc.itf.pu.dw66to20.query.IDW66QueryMaintain;
import nc.itf.uif.pub.IUifService;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.erpcgjhjk.AggErpcgjhjkHVO;
import nc.vo.lm.erpcgjhjk.ErpcgjhjkHVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class QueryServiceFor20 implements IRefQueryService {

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggErpcgjhjkHVO[] retbills = null;
		IDW66QueryMaintain service = NCLocator.getInstance().lookup(
				IDW66QueryMaintain.class);
		try {
			retbills = service.queryUpForPrayBillDates(queryScheme);
//			retbills=this.queryAggCgagForSource(queryScheme);
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
	
//	private AggErpcgjhjkHVO[] queryAggCgagForSource(IQueryScheme queryScheme) {
//
//		try {
//			String sql = queryScheme.getWhereSQLOnly();
//			List<AggErpcgjhjkHVO> aggvos = new ArrayList<AggErpcgjhjkHVO>();
//			HYPubBO bo = new HYPubBO();
//			SqlBuilder sb = new SqlBuilder();
//			sb.append(sql);
//			sb.append(" and nvl(dr,0)=0 and nvl(msginfo, 0) <> 2 ");
//			IUifService service =NCLocator.getInstance().lookup(IUifService.class);
//			service.queryByCondition(ErpcgjhjkHVO.class, sb.toString());
//			ErpcgjhjkHVO[] hvos = (ErpcgjhjkHVO[]) bo.queryByCondition(ErpcgjhjkHVO.class,
//					sb.toString());
//			// for (ErpcgjhjkHVO hvo : hvos) {
//			// AggErpcgjhjkHVO aggvo = new AggErpcgjhjkHVO();
//			// aggvo.setParent(hvo);
//			// aggvos.add(aggvo);
//			// }
//			return aggvos.toArray(new AggErpcgjhjkHVO[aggvos.size()]);
//		} catch (Exception e) {
//			ExceptionUtils.wrappException(e);
//		}
//		return null;
//
//	}

}

package nc.bs.pu.m21.writeback.cmp;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.cmp.rule.AccumPayAppMnyCalRule;
import nc.bs.pu.m21.writeback.cmp.rule.AccumPayAppOrgMnyRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.cmp.m36d1.OrderPayPlanWriteBackParaFor36D1;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.rule.OrderPayCloseChkRule;
import nc.vo.pu.m21.rule.OrderPayFreezeChkRule;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购订单付款计划给资金付款申请单提供的回写BP类
 * 
 * @since 6.3.1
 * @version 2013-9-11 下午02:44:33
 * @author fanly3
 */
public class OrderPayPlanWriteBackFor36D1BP {

	public void writeBack(OrderPayPlanWriteBackParaFor36D1[] backVos) {
		if (ArrayUtils.isEmpty(backVos)) {
			return;
		}

		Map<String, OrderPayPlanWriteBackParaFor36D1> backVoMap = new HashMap<String, OrderPayPlanWriteBackParaFor36D1>();
		for (OrderPayPlanWriteBackParaFor36D1 backVo : backVos) {
			backVoMap.put(backVo.getPk_order_payplan(), backVo);
		}

		ViewQuery<PayPlanViewVO> query = new ViewQuery<PayPlanViewVO>(
				PayPlanViewVO.class);
		String[] bids = backVoMap.keySet().toArray(
				new String[backVoMap.keySet().size()]);
		PayPlanViewVO[] viewVos = query.query(bids);

		new VOConcurrentTool().lock(PayPlanVO.class, bids);

		for (PayPlanViewVO viewVo : viewVos) {
			String ts = viewVo.getAttributeValue(PayPlanVO.TS).toString();
			OrderPayPlanWriteBackParaFor36D1 paraFor36D1 = backVoMap.get(viewVo
					.getAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN));
			UFDateTime srcTime = paraFor36D1.getTs();
			if (srcTime == null) {
				continue;
			}
			if (!ts.trim().equals(paraFor36D1.getTs().toString().trim())) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004030_0", "04004030-0382"));/*
																																			 * @res
																																			 * "出现并发，请重新操作！"
																																			 */
			}
		}

		AroundProcesser<PayPlanViewVO> processer = new AroundProcesser<PayPlanViewVO>(
				OrderPluginPoint.WRITEBACK_36DA);
		processer.addBeforeRule(new OrderPayCloseChkRule());
		processer.addBeforeRule(new OrderPayFreezeChkRule());

		// 计算累计金额
		processer.addBeforeRule(new AccumPayAppMnyCalRule(backVoMap));
		// 金额检查
		processer.addBeforeRule(new AccumPayAppOrgMnyRule());

		processer.before(viewVos);
		ViewUpdate<PayPlanViewVO> viewUpdate = new ViewUpdate<PayPlanViewVO>();
		viewUpdate.update(viewVos, PayPlanVO.class,
				new String[] { AbstractPayPlanVO.NACCUMPAYAPPORGMNY,
						AbstractPayPlanVO.NACCUMPAYAPPMNY });

	}
}

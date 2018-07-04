package nc.impl.pu;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.IRewriteJt01ForQg20;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RewriteJt01ForQg20Impl implements IRewriteJt01ForQg20 {

//	@Override
//	public void rewriteLM16NumForLM19(RewriteLM16Para[] paras)
//			throws BusinessException {
//		Map<String, RewriteLM16Para> index = this.prepareParams(paras);
//		// 此处设置session变量，以避免程序到处传递
//		BSContext.getInstance().setSession(RewriteLM16Para.class.getName(),
//				index);
//		YsjhViewVO[] views = this.query(index);
//		AroundProcesser<YsjhViewVO> processer = new AroundProcesser<YsjhViewVO>(
//				YsjhPluginPoint.REWRITELM16FORLM19);
//		addRule(processer, views);
//		processer.before(views);
//		String[] names = new String[] { "nordernum" };
//		ViewUpdate<YsjhViewVO> bo = new ViewUpdate<YsjhViewVO>();
//		views = bo.update(views, YsjhbBodyVO.class, names);
//		processer.after(views);
//		// 此处释放session变量，以免浪费内存
//		BSContext.getInstance().removeSession(RewriteLM16Para.class.getName());
//	}

//	private Map<String, RewriteLM16Para> prepareParams(RewriteLM16Para[] paras) {
//		Map<String, RewriteLM16Para> index = new HashMap<String, RewriteLM16Para>();
//		for (RewriteLM16Para para : paras) {
//			index.put(para.getPk_ysjhzb(), para);
//		}
//		return index;
//	}
//
//	private YsjhViewVO[] query(Map<String, RewriteLM16Para> index) {
//		String[] ids = this.lockBills(index);
//		ViewQuery<YsjhViewVO> bo = new ViewQuery<YsjhViewVO>(
//				YsjhViewVO.class);
//		bo.setSharedHead(true);
//
//		YsjhViewVO[] views = bo.query(ids);
//		if (views.length != index.size()) {
//			String message = "回写上游单据时出现并发，请重新查询上游单据";
//			ExceptionUtils.wrappBusinessException(message);
//		}
//		return views;
//	}
//
//	private String[] lockBills(Map<String, RewriteLM16Para> index) {
//		int size = index.size();
//		String[] bids = new String[size];
//		bids = index.keySet().toArray(bids);
//		LockOperator locker = new LockOperator();
//		String message = "回写上游单据锁表体失败";
//		locker.lock(bids, message);
//		return bids;
//	}

//	private void addRule(AroundProcesser<YsjhViewVO> processer,
//			YsjhViewVO[] views) throws BusinessException {
//		// 执行前规则
//
////		IRule<YsjhViewVO> rule = new RewriteCheck();
////		processer.addBeforeRule(rule);
////		rule = new RewriteSetArrInfoRule();
////		processer.addBeforeRule(rule);
//		// 执行后规则
//	}
}

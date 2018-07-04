package nc.bs.pu.cgfa.ace.rule;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 
 * @author wangzym
 * @version 2017年3月3日 上午11:29:52 modify by wangzym 2017-05-03 新增修改时间的回写，取服务器时间
 */

public class RewriteForPrayBillRule implements IRule<AggCgfa> {

	@Override
	public void process(AggCgfa[] vos) {
		// TODO 自动生成的方法存根
		// 只有一个Aggvo
		// 太tm恶心了，直接抛出未知异常
		Cgfab[] bvos = (Cgfab[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		UFDouble[] nums = new UFDouble[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			Cgfab cgfab = bvos[i];
			if ((cgfab.getAttributeValue("csrcid")) == null) {
				// 不需要回写没有取到上游的来源单据
				return;
			}
			UFDouble num = (UFDouble) cgfab.getAttributeValue("cgfanum");
			String csrcid = cgfab.getAttributeValue("csrcid").toString();
			srcPk[i] = csrcid;
			nums[i] = num;

		}
		this.rewrite(srcPk, nums);

	}

	/**
	 * 回写动作
	 * 
	 * @param nums
	 * 
	 * @param paras
	 *            当前的来源单据字段数组
	 */
	private void rewrite(String[] srcPk, UFDouble[] nums) {
		if (srcPk.length == 0) {
			return;
		}
		String[] names = new String[] { PraybillItemVO.STS_REQ ,"sumcgfanum"};
		VOUpdate<PraybillItemVO> bo = new VOUpdate<PraybillItemVO>();
		PraybillItemVO[] vos = new PraybillItemVO[srcPk.length];

		for (int i = 0; i < vos.length; i++) {
			PraybillItemVO praybillItemVO = new PraybillItemVO();
			// praybillItemVO.setSts_req("01234567890123456789012345678901234567890123456789");
			VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
					PraybillItemVO.class);
			PraybillItemVO[] old = query.query(new String[] { srcPk[i] });
			UFDouble oldnum = (UFDouble) old[0].getAttributeValue("sumcgfanum");
			UFDouble oldsum = (UFDouble) old[0].getAttributeValue("nastnum");
			
			UFDouble num = null;
			if (oldnum == null) {
				num = nums[i];
			} else {

				num = nums[i].add(oldnum);
			}
			if (oldsum.doubleValue()<num.doubleValue()) {
				ExceptionUtils.wrappBusinessException("修改后的数量超出上游最大可用量");
			}
			praybillItemVO.setAttributeValue("sumcgfanum", num);

			praybillItemVO.setAttributeValue("sts_req", "03");

			UFDateTime serverTime = AppContext.getInstance().getServerTime();
			praybillItemVO.setAttributeValue("bmodifiedtime", serverTime);

			praybillItemVO.setSts_req("03");
			String neq = praybillItemVO.getSts_req();
			praybillItemVO.setPk_praybill_b(srcPk[i]);

			vos[i] = praybillItemVO;
			String new1 = vos[i].getSts_req();
		}
		PraybillItemVO[] newvo = bo.update(vos, names);
		TimeLog.info("更新数据库"); /* -=notranslate=- */

	}

}

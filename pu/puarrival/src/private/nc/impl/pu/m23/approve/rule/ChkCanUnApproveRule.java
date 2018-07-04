package nc.impl.pu.m23.approve.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.itf.pu.reference.ic.M47PUServices;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 
 * @description <ul>
 *              <li>检查是否可弃审
 *              <li>检查是否存在生成过补货订单的到货单
 *              <li>生成下游单据的不能弃审(包括已生成资产卡片)
 *              <li>已报检的不能弃审
 *              </ul>
 * @scene
 * 到货单取消审批
 * @param 无
 * 
 * @since 6.3
 * @version 2010-1-19 上午09:34:01
 * @author hanbin
 */

public class ChkCanUnApproveRule implements IRule<ArriveVO> {

	@Override
	public void process(ArriveVO[] voArray) {
		for (ArriveVO aggVO : voArray) {
			// 检查是否可弃审
			this.chkCanUnApprove(aggVO);
		}
		// 检查是否生成过采购入
		this.checkHasPurchaseIn(voArray);
		// 检查是否生成过委外入
		this.checkHasSubcontIn(voArray);
	}

	private void checkHasPurchaseIn(ArriveVO[] vos) {
		List<String> hidLst = this.getHIDSourceFrom(vos, POBillType.Order);
		if (hidLst.size() == 0) {
			return;
		}
		Map<String, UFBoolean> hidMap = M45PUServices.getMapBysrcHid(hidLst
				.toArray(new String[hidLst.size()]));
		if (null == hidMap || hidMap.size() == 0) {
			return;
		}
		for (UFBoolean value : hidMap.values()) {
			if (UFBoolean.TRUE.equals(value)) {
				this.throwHasStoreInExp();
			}
		}
	}

	private void checkHasSubcontIn(ArriveVO[] vos) {
		List<String> hidLst = this.getHIDSourceFrom(vos, SCBillType.Order);
		if (hidLst.size() == 0) {
			return;
		}
		if (M47PUServices.hasFromSource(hidLst)) {
			this.throwHasStoreInExp();
		}
	}

	/**
	 * 方法功能描述：检查是否可弃审
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param aggVO
	 *            <p>
	 * @since 6.0
	 * @author hanbin
	 * @time 2010-1-19 上午10:22:02
	 */
	private void chkCanUnApprove(ArriveVO aggVO) {
		if (!ApproveFlowUtil.isCanUnApprove(aggVO)) {
			String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4004040_0", "04004040-0122")/*
															 * @res
															 * "到货单的当前状态不允许取消审核！"
															 */;
			ExceptionUtils.wrappBusinessException(message);
		}

		ArriveItemVO[] itemVOArray = aggVO.getBVO();
		for (int i = 0; i < itemVOArray.length; i++) {
			// 生成下游单据的不能弃审(包括已生成资产卡片)
			if (MathTool.nvl(itemVOArray[i].getNaccumstorenum()).doubleValue() > 0) {
				this.throwHasStoreInExp();
			}
			if (itemVOArray[i].getBfaflag().booleanValue()) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004040_0",
										"04004040-0124")/*
														 * @res "已生成设备卡片，不允许弃审！"
														 */);
			}
			// 检查是否存在生成过补货订单的到货单
			if (MathTool.nvl(itemVOArray[i].getNaccumreplnum()).doubleValue() > 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004040_0",
										"04004040-0125")/*
														 * @res "已生成补货单，不允许弃审！"
														 */);
			}
			
      //bg-NCM-zhangkjb-NC2015051100102-2015-05-15-通版
      /*
       * add by wandl 合维护开发部补丁
       * 参照原到货单生成退货单，会把累计校验数量带过来，
       * 需求建议可以在退货单不校验累计保健数量。
       */
      if(aggVO.getHVO().getBisback() == null ||!aggVO.getHVO().getBisback().booleanValue()){
				// 已报检的不能弃审
				if (MathTool.nvl(itemVOArray[i].getNaccumchecknum())
						.doubleValue() > 0) {
					ExceptionUtils
							.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
									.getNCLangRes().getStrByID("4004040_0",
											"04004040-0126")/*
															 * @res
															 * "已报检的到货单，不允许弃审！"
															 */);
				}
      }
      //ed-NCM-zhangkjb-NC2015051100102-2015-05-15-通版
			
			// 已紧急放行的到货单
			if (MathTool.nvl(itemVOArray[i].getNaccumletgonum()).doubleValue() > 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004040_0",
										"04004040-0127")/*
														 * @res
														 * "已紧急放行的到货单，不允许弃审！"
														 */);
			}
			// 生成转固单
			if (UFBoolean.TRUE.equals(itemVOArray[i].getBtransasset())) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004040_0",
										"04004040-0128")/*
														 * @res "已生成转固单，不允许弃审！"
														 */);
			}
			// 全部退货
			if (itemVOArray[i].getNaccumbacknum() != null
					&& !UFDouble.ZERO_DBL.equals(itemVOArray[i]
							.getNaccumbacknum())) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004040_0",
										"04004040-0192")/*
														 * @res
														 * "已基于该到货单生成退货单，不允许弃审！"
														 */);
			}
		}
	}

	private List<String> getHIDSourceFrom(ArriveVO[] vos, IBillType bt) {
		List<String> hidLst = new ArrayList<String>();
		for (ArriveVO vo : vos) {
			for (ArriveItemVO item : vo.getBVO()) {
				if (bt.getCode().equals(item.getCsourcetypecode())) {
					hidLst.add(item.getPk_arriveorder());
					break;
				}
			}
		}
		return hidLst;
	}

	private void throwHasStoreInExp() {
		ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004040_0", "04004040-0123"));/*
																		 * @res
																		 * "已生成入库单，不允许弃审！"
																		 */
	}

}

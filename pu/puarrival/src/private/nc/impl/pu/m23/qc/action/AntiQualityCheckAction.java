package nc.impl.pu.m23.qc.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m23.maintain.rule.update.UpdateChkRule;
import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的反检对应的Action // 清空相应行的累计报检数量、累计合格数量、累计不合格数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class AntiQualityCheckAction {

	public ArriveItemVO[] antiQualityCheck(ArriveItemVO[] vos) {
//		 BillTransferTool<ArriveVO> tool = new
//		 BillTransferTool<ArriveVO>(vos);
//		 ArriveVO[] vos = tool.getClientFullInfoBill();

		AroundProcesser<ArriveItemVO> processer = new AroundProcesser<ArriveItemVO>(
				ArriveActionPlugInPoint.AntiQualityCheckAction);

		this.addBeforeRule(processer);
		this.addAfterRule(processer);

		processer.before(vos);

		this.invokeQCService(vos);

		processer.after(vos);

		// 查询最新VO返回
		VOQuery<ArriveItemVO> query = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
		ArriveItemVO[] newVOArray = query.query(AggVOUtil.getPrimaryKeys(vos));
		return newVOArray;
	}

	private void addAfterRule(AroundProcesser<ArriveItemVO> processer) {
		if (processer == null) {
			return;
		}
		processer.addAfterRule(new UpdateChkRule());
		// processer.addAfterFinalRule(new ClearAccNnumRule());
	}

	private void addBeforeRule(AroundProcesser<ArriveItemVO> processer) {
		if (processer == null) {
			return;
		}
	}

	private void invokeQCService(ArriveItemVO[] bills) {
		if (bills == null || bills.length == 0) {
			return;
		}

		try {
			// 清空累计合格主数量、累计报检数量、累计不合格数量
			List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
			for (ArriveItemVO bvo : bills) {
				// ArriveItemVO[] bvos = avo.getBVO();
				// if (bvos != null && bvos.length != 0) {
				// for (ArriveItemVO bvo : bvos) {
				bvo.setNnotelignum(UFDouble.ZERO_DBL);
				bvo.setNelignum(UFDouble.ZERO_DBL);
				bvo.setNaccumchecknum(UFDouble.ZERO_DBL);
				list.add(bvo);
				// }
				// }
			}
			ArriveItemVO[] vos = list.toArray(new ArriveItemVO[0]);
			VOUpdate<ArriveItemVO> update = new VOUpdate<ArriveItemVO>();
			update.update(vos, new String[] { ArriveItemVO.NACCUMCHECKNUM,
					ArriveItemVO.NELIGNUM, ArriveItemVO.NNOTELIGNUM });
		} catch (Exception ex) {
			ExceptionUtils.wrappException(ex);
		}
	}
}

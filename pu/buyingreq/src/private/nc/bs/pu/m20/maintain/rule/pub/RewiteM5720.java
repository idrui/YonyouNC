package nc.bs.pu.m20.maintain.rule.pub;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.et.M5720PUServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.et.m5720arrange.Rewrite5720ArrangePara;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 出口合同回写
 * 
 * @since 6.31
 * @version 2013-8-26 下午01:56:28
 * @author zhangyhh
 */
public class RewiteM5720 implements IRewite {

	@Override
	public void writeback(List<RewritePara> rwParas,
			Map<String, PraybillVO> bidVOMap) {

		if (!SysInitGroupQuery.isETEnabled()) {
			return;
		}

		if (rwParas == null || rwParas.isEmpty()) {
			return;
		}

		// 构造回写出口合同的参数
		Rewrite5720ArrangePara[] paraArray = this.buildParaArray(rwParas);

		M5720PUServices.writeback5720For20(paraArray);
	}

	private Rewrite5720ArrangePara[] buildParaArray(List<RewritePara> rwParas) {
		if (rwParas == null || rwParas.isEmpty()) {
			return null;
		}
		String userid = InvocationInfoProxy.getInstance().getUserId();
		Rewrite5720ArrangePara[] paras = new Rewrite5720ArrangePara[rwParas
				.size()];
		int len = rwParas.size();
		for (int i = 0; i < len; i++) {
			RewritePara para = rwParas.get(i);
			String csaleorderbid = para.getCsrcbid();
			UFDouble narrangeponum = para.getNnum();
			paras[i] = new Rewrite5720ArrangePara(csaleorderbid, narrangeponum,
					userid, POBillType.PrayBill.getCode());
		}
		return paras;
	}

}

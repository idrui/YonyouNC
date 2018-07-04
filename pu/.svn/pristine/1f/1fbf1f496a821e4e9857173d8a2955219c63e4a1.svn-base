package nc.vo.pu.m23.rule.api.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;

/**
 * 
 * @description
 *		到货单单据表头合计行计算填充
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		汇总表头数量和价税合计填充表头
 * @since 6.5
 * @version 2015-10-31 上午10:53:53
 * @author wandl
 */
public class HeadNumAndMnySumFillRule implements IRule<ArriveVO>{

	@Override
	public void process(ArriveVO[] vos) {
		for(ArriveVO vo : vos){
			BillHelper<ArriveVO> bill = new BillHelper<ArriveVO>(vo);
	    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
	    // 本币加税合计
	    sum.setHeaderMnyField(ArriveHeaderVO.NTOTALTAXMNY);
	    sum.setItemMnyField(ArriveItemVO.NTAXMNY);
	    // 过滤赠品行
	    sum.setBlargessField(ArriveItemVO.BPRESENT);
	    sum.sum();
		}
	}

}

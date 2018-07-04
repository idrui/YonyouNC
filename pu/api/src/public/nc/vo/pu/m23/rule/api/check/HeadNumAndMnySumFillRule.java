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
 *		���������ݱ�ͷ�ϼ��м������
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		���ܱ�ͷ�����ͼ�˰�ϼ�����ͷ
 * @since 6.5
 * @version 2015-10-31 ����10:53:53
 * @author wandl
 */
public class HeadNumAndMnySumFillRule implements IRule<ArriveVO>{

	@Override
	public void process(ArriveVO[] vos) {
		for(ArriveVO vo : vos){
			BillHelper<ArriveVO> bill = new BillHelper<ArriveVO>(vo);
	    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
	    // ���Ҽ�˰�ϼ�
	    sum.setHeaderMnyField(ArriveHeaderVO.NTOTALTAXMNY);
	    sum.setItemMnyField(ArriveItemVO.NTAXMNY);
	    // ������Ʒ��
	    sum.setBlargessField(ArriveItemVO.BPRESENT);
	    sum.sum();
		}
	}

}

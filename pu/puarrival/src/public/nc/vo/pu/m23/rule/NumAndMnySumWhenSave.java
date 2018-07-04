package nc.vo.pu.m23.rule;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增、修改保存时进行单据表头整单数量和本币价税合计的计算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-19 下午05:01:13
 */
public class NumAndMnySumWhenSave {

  public void numAndMnySum(ArriveVO vo) {
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

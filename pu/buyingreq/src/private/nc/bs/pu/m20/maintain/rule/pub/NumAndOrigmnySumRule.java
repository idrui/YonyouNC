/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-29 上午10:22:20
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;

/**
 * @description
 *              请购单计算表头的总数量和价税合计
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:03:18
 * @author yanxm5
 */
public class NumAndOrigmnySumRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    for (PraybillVO aggVO : voArray) {
      // 计算表头的总数量和价税合计
      this.sum(aggVO);
    }
  }

  private void sum(PraybillVO vo) {
    BillHelper<PraybillVO> bill = new BillHelper<PraybillVO>(vo);

    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
    sum.setHeaderMnyField(PraybillHeaderVO.NTOTALTAXMNY);
    sum.setItemMnyField(PraybillItemVO.NTAXMNY);
    sum.sum();

  }

}

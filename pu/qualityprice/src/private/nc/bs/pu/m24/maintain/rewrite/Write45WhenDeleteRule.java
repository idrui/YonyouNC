package nc.bs.pu.m24.maintain.rewrite;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @description
 *              价格结算单回写采购入库单
 * @scene
 *        价格结算单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:18:01
 * @author luojw
 */
public class Write45WhenDeleteRule extends AbstractWrite45 implements
    IRule<PricestlVO> {

  @Override
  public List<RewritePara> getRewriteParaList(PricestlVO aggVO,
      PricestlVO originAggVO) {

    BillRewriter tool = this.getBillRewriter(this.getItemKeyMapping());
    Map<String, List<RewritePara>> rwParaMap =
        tool.splitForDelete(new PricestlVO[] {
          aggVO
        });

    return rwParaMap.get(ICBillType.PurchaseIn.getCode());
  }

  @Override
  public void process(PricestlVO[] voArray) {
    this.setPricestlVOs(voArray);

    for (PricestlVO aggVO : voArray) {

      // 回写
      this.writeback(this.getRewriteParaList(aggVO, null));
    }
  }

}

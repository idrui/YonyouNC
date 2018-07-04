package nc.bs.pu.m24.maintain.rewrite;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @description
 *              价格结算单回写采购入库单
 * @scene
 *        价格结算单更新保存
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:42:13
 * @author luojw
 */
public class Write45WhenUpdateRule extends AbstractWrite45 implements
    ICompareRule<PricestlVO> {

  @Override
  public List<RewritePara> getRewriteParaList(PricestlVO aggVO,
      PricestlVO originAggVO) {

    BillRewriter tool = this.getBillRewriter(this.getItemKeyMapping());
    Map<String, List<RewritePara>> rwParaMap =
        tool.splitForUpdate(new PricestlVO[] {
          aggVO
        }, new PricestlVO[] {
          originAggVO
        });

    return rwParaMap.get(ICBillType.PurchaseIn.getCode());
  }

  @Override
  public void process(PricestlVO[] voArray, PricestlVO[] originVOArray) {
    this.setPricestlVOs(voArray);

    for (int i = 0, len = voArray.length; i < len; i++) {

      // 回写
      this.writeback(this.getRewriteParaList(voArray[i], originVOArray[i]));
    }
  }

}

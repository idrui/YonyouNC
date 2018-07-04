/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-21 上午09:05:34
 */
package nc.vo.pu.m25.rule.maintain.delete;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *		切断费用发票与货物发票之间的关系
 * @scene
 * 		切断费用发票与货物发票之间的关系
 * @param
 *
 * @since 6.5
 * @author zhaoyha
 * @time 2010-4-21 上午09:05:34
 */
public class CutFeeParentRelationRule implements IRule<InvoiceVO> {
  private InvoiceVO[] cutrelFees;

  /**
   * CutFeeParentRelationRule 的构造子
   * 
   * @param cutrelFees
   */
  public CutFeeParentRelationRule(InvoiceVO[] cutrelFees) {
    this.cutrelFees = cutrelFees;
  }

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(this.cutrelFees)) {
      return;
    }
    for (InvoiceVO vo : this.cutrelFees) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setPk_parentinvoice(null);
      vo.getParentVO().setBfee(UFBoolean.FALSE);
    }
    String[] ids = AggVOUtil.getPrimaryKeys(this.cutrelFees);
    InvoiceVO[] orgFees = new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
    new BillUpdate<InvoiceVO>().update(this.cutrelFees, orgFees);
  }

}

/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-10 下午01:23:57
 */
package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 表头合计信息计算规则
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:21:52
 * @author zhangshqb
 */
public class InvoiceTotalValueCalcRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      BillHelper<InvoiceVO> bill = new BillHelper<InvoiceVO>(vo);
      new NumAndOrigmnySum(bill).sum();
    }
  }

}

/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-20 上午09:28:34
 */
package nc.bs.pu.m4t.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算可开票数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-20 上午09:28:34
 */
public class CanInvoiceNumCalcRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (InitialEstVO vo : vos) {
      for (InitialEstItemVO item : vo.getItems()) {
        UFDouble totalnum = item.getNnum();
        // 累计开票数量
        UFDouble accInvoiceNum = item.getNaccinvoicenum();
        // 累计结算数量
        UFDouble accSettleNum = item.getNaccsettlenum();
        // 可开票数量要考虑结算数量
        int result = MathTool.absCompareTo(accInvoiceNum, accSettleNum);
        if (result >= 0) {
          item.setNcaninvoicenum(MathTool.sub(totalnum, accInvoiceNum));
        }
        else {
          item.setNcaninvoicenum(MathTool.sub(totalnum, accSettleNum));
        }
      }
    }
  }

}

/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 上午08:40:05
 */
package nc.bs.pu.m4201.rule;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消签字时检查是否结算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 上午08:40:05
 */
public class UnSignSettleCheckRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PurchaseinFIVO vo : vos) {
      // change by wandl 自动暂估逻辑错误
      /*      PurchaseinFIHeaderVO head = vo.getParentVO();
            UFBoolean autoFi = head.getBautotofi();
            // 自动暂估过的要自动取消，这里不用检查
            if (ValueUtils.getBoolean(autoFi)) {
              continue;
            }*/
      this.check(vo.getChildrenVO());
    }

  }

  private void check(PurchaseinFIItemVO[] items) {
    for (PurchaseinFIItemVO item : items) {
      UFDouble num = item.getNaccumsettlenum();
      UFDouble feemny = item.getNaccfeesettlemny();
      if (0 != MathTool.compareTo(num, UFDouble.ZERO_DBL)
          || 0 != MathTool.compareTo(feemny, UFDouble.ZERO_DBL)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4004060_0", "04004060-0083")/*@res "入库单已经结算过，不能取消签字!"*/);
      }
    }
  }
}

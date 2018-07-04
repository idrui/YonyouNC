/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 下午04:50:37
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 费用发票中只能录入劳务和折扣类物料
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:35:18
 * @author zhangshqb
 */
public class FeeMaterialCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      boolean fee = ValueUtils.getBoolean(vo.getParentVO().getBfee());
      if (!fee) {
        continue;
      }
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if ((InvoiceRowType.DISCOUNT_ROW != item.getFrowtype().intValue())
            && (InvoiceRowType.FEE_ROW != item.getFrowtype().intValue())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0023")/*@res "费用发票中只能录入劳务和折扣类物料"*/);
        }
      }
    }
  }

}
package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            采购费用发票保存时，只能录入劳务和折扣类物料
 * @scene
 *      采购费用发票保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-4-29 下午04:50:37
 * @author zhaoyha
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
        if (InvoiceRowType.DISCOUNT_ROW != item.getFrowtype().intValue()
            && InvoiceRowType.FEE_ROW != item.getFrowtype().intValue()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004050_0", "04004050-0023")/*
                                                                       * @res
                                                                       * "费用发票中只能录入劳务和折扣类物料"
                                                                       */);
        }
      }
    }
  }

}

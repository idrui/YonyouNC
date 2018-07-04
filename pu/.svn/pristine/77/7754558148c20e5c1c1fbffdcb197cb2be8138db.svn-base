package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * @since 6.0
 * @version 2014-8-27 下午4:05:39
 * @author luojw
 */

public class InvoiceSourceTypeChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (!SysInitGroupQuery.isMMEnabled()) {
      return;
    }
    // 加工费结算单交易类型
    Object typecode = MMBillType.PscSettle.getCode();
    for (InvoiceVO vo : vos) {
      InvoiceItemVO[] items = vo.getChildrenVO();
      int len = items.length;
      int count = 0;
      for (int i = 0; i < len; i++) {
        Object bodytype = items[i].getCsourcetypecode();
        if (typecode.equals(bodytype)) {
          count++;
        }
      }
      if (count < len && count > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0128")/*
                                                                     * @res
                                                                     * "采购发票来源于加工费结算单时不允许存在非来源于加工费结算单的行！"
                                                                     */);
      }
    }
  }

}

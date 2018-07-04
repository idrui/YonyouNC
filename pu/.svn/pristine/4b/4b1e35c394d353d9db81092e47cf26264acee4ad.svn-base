package nc.ui.pu.costfactor.rule;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.validation.billlist.NotNullValidator;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 成本要素保存校验
 * 
 * @since 6.0
 * @version 2011-12-15 下午04:26:05
 * @author 田锋涛
 */

public class CostFactorNotNullValidator extends NotNullValidator {

  @Override
  protected String dataNotNullValidate(Object obj) {
    // 2011-12-15 按需求wangyf意见，经zhaoyha确认，这里不对表体做校验，
    // 即表体空时视为该成本要素是无用数据
    if (!(obj instanceof AggregatedValueObject)) {
      return null;
    }
    CircularlyAccessibleValueObject head =
        ((AggregatedValueObject) obj).getParentVO();
    StringBuffer message = null;
    BillItem[] headtailitems =
        this.getListEditor().getBillListPanel().getBillListData()
            .getHeadItems();
    if (headtailitems != null) {
      for (BillItem headtailitem : headtailitems) {
        if (headtailitem.isNull()) {
          if (this.isNULL(head.getAttributeValue(headtailitem.getKey()))) {
            if (message == null) {
              message = new StringBuffer();
            }
            message.append("[");
            message.append(headtailitem.getName());
            message.append("]");
            message.append(",");
          }
        }
      }
    }
    if (message != null) {
      message.deleteCharAt(message.length() - 1).append("\n");
      return message.toString()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("smcomm",
              "UPP1005-000239")/* @res "不能为空。" */;
    }
    return null;
  }

}

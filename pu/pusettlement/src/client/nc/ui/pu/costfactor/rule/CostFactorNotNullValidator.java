package nc.ui.pu.costfactor.rule;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.validation.billlist.NotNullValidator;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * �ɱ�Ҫ�ر���У��
 * 
 * @since 6.0
 * @version 2011-12-15 ����04:26:05
 * @author �����
 */

public class CostFactorNotNullValidator extends NotNullValidator {

  @Override
  protected String dataNotNullValidate(Object obj) {
    // 2011-12-15 ������wangyf�������zhaoyhaȷ�ϣ����ﲻ�Ա�����У�飬
    // �������ʱ��Ϊ�óɱ�Ҫ������������
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
              "UPP1005-000239")/* @res "����Ϊ�ա�" */;
    }
    return null;
  }

}

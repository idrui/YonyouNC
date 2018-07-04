/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-7 上午11:16:53
 */
package nc.ui.pu.m25.rule;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头的采购组织可编辑性处理器<br>
 * 参照生成采购发票时根据采购订单或者采购入库单表头采购组织带入， 如果非空，则不可编辑。如果为空时可以录入，参照组织类型为采购组织的组织单元录入。
 * 非空。默认值规则：如果发票结算财务组织勾选了采购组织属性，则取该组织作为采购组织→手工录入。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-7 上午11:16:53
 */
public class PurchaseOrgEditableHandler {
  private BillCardPanel bcp;

  /**
   * PurchaseOrgEditableHandler 的构造子
   * 
   * @param bcp
   */
  public PurchaseOrgEditableHandler(BillCardPanel bcp) {
    this.bcp = bcp;
  }

  public void setEditable() {
    CardEditorHelper helper = new CardEditorHelper(this.bcp);
    // BillItem item = this.bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG);
    BillItem item = this.bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG_V);
    if (!item.isEnabled()) {
      return;// 模板设置不可编辑则直接返回
    }
    item.setEnabled(this.isEditable(helper));
  }

  private boolean isEditable(CardEditorHelper helper) {
    int rowcount = helper.getItemCount();
    if (0 == rowcount) {
      return true;
    }
    String pk_purorg =
        helper.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    if (StringUtil.isEmptyWithTrim(pk_purorg)) {
      return true;
    }
    for (int i = 0; i < rowcount; ++i) {
      // 有来源且有值时不可编辑
      if (!StringUtil.isEmptyWithTrim(helper.getBodyStringValue(i,
          InvoiceItemVO.CSOURCEBID))) {
        return false;
      }
    }
    return true;// 自制时可编辑
  }

}

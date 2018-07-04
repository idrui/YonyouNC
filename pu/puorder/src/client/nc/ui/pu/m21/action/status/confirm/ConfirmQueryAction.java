package nc.ui.pu.m21.action.status.confirm;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @since 6.0
 * @version 2011-1-26 上午11:43:22
 * @author wuxla
 */

public class ConfirmQueryAction extends DefaultQueryAction {

  private static final long serialVersionUID = -6492904372749425427L;

  private IEditor editor = null;

  public IEditor getEditor() {
    return this.editor;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  private void setItemsEnable(BillModel bm) {
    bm.setEnabled(true);
    // 表头自定义项可编辑设置
    BillItem[] bihs =
        ((BillForm) this.editor).getBillCardPanel().getHeadItems();
    for (BillItem bi : bihs) {
      // 自定义项配置可编辑
      if (bi.getKey().contains("vdef") && bi.isEdit()) {
        bi.setEnabled(true);
      }
    }
    BillItem[] bibs =
        ((BillForm) this.editor).getBillCardPanel().getBodyItems();
    // 对方订单号、对方订单行号、确认数量、确认日期
    for (BillItem bi : bibs) {
      // 自定义项走模板配置
      if (bi.getKey().equals(OrderItemVO.VVENDORORDERCODE)
          || bi.getKey().equals(OrderItemVO.VVENDORORDERROW)
          || bi.getKey().equals(OrderItemVO.NCONFIRMNUM)
          || bi.getKey().equals(OrderItemVO.DCONFIRMDATE)
          || bi.getKey().equals(OrderItemVO.DPLANARRVDATE)
          || bi.getKey().contains("vbdef")) {
        continue;
      }
      bi.setEnabled(false);
    }
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getQueryCondition(OnwayStatusQryEnum.confirm.code());
    Object[] values = null;
    UFBoolean output = null;
    if (outputCond != null) {
      values = outputCond.getValues();
      output = UFBoolean.valueOf((String) values[0]);
    }

    super.executeQuery(queryScheme);

    BillCardPanel panel = ((BillForm) this.editor).getBillCardPanel();
    // 加载公式和相关项
    BillModel bm = panel.getBillModel();

    // 设置字段不可编辑,在确认时可以编辑,反确认时不可编辑
    bm.setEnabled(false);
    if (output == null) {
      return;
    }
    if (!output.booleanValue()) {
      this.setItemsEnable(bm);
    }
    else {
      // 反确认时,确认应该划上对勾add by wangljc at 2011-6-21 15:46:09
      int rowCount = panel.getRowCount();
      for (int i = 0; i < rowCount; i++) {
        panel.setBodyValueAt(UFBoolean.TRUE, i, OrderOnwayItemVO.BCONFIRMFLAG);
      }
    }
  }
}

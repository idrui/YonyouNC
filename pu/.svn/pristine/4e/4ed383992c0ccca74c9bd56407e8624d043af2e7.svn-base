package nc.ui.pu.m21.action.status.confirm;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

public class RefreshAction extends DefaultRefreshAction {
  private static final long serialVersionUID = -1704025823484597764L;

  private IEditor editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    if (this.isconfirm()) {
      BillCardPanel panel = ((BillForm) this.editor).getBillCardPanel();
      int nRowNum = panel.getRowCount();
      for (int i = 0; i < nRowNum; i++) {
        panel.setBodyValueAt(UFBoolean.TRUE, i, OrderOnwayItemVO.BCONFIRMFLAG);
      }
    }
  }

  public IEditor getEditor() {
    return this.editor;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  private boolean isconfirm() {
    IQueryScheme queryScheme =
        ((ModelDataManager) this.getDataManager()).getQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getLogicalCondition(OnwayStatusQryEnum.confirm
            .code());
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    return output.booleanValue();
  }
}

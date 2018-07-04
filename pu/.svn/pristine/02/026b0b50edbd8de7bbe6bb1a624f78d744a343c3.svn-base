package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参照上游请购单生成采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-27 下午03:39:36
 */
public class AddFromSourceAction extends AbstractReferenceAction {
  private static final long serialVersionUID = -3060427151080092840L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  @Override
  public void doAction(ActionEvent e) throws Exception {
  	this.editor.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      OrderVO[] vos = (OrderVO[]) PfUtilClient.getRetVos();
      // 显示到转单界面上
      this.getTransferViewProcessor().processBillTransfer(vos);
      // 可编辑性
      this.setEditable();
    }
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Order.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    // context.setCurrBilltype(POBillType.Order.getCode());
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.NoClassify);
    return context;
  }

  private void setEditable() {
    String csourcetype = this.getSourceBillType();
    if (POBillType.PrayBill.getCode().equals(csourcetype)) {
      this.setEditableForPray();
    }
  }

  private void setEditableForPray() {
    BillCardPanel panel = this.editor.getBillCardPanel();
    for (int i = 0; i < panel.getRowCount(); ++i) {
      // 参照请购单,需求库存组织,需求部门不可修改 by wangljc at 2011-6-7 16:00:06
      panel.setCellEditable(i, OrderItemVO.PK_REQSTOORG_V, false);
      panel.setCellEditable(i, OrderItemVO.PK_REQSTOORG, false);
      panel.setCellEditable(i, OrderItemVO.PK_REQDEPT_V, false);
      panel.setCellEditable(i, OrderItemVO.PK_REQDEPT, false);
    }
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }
}

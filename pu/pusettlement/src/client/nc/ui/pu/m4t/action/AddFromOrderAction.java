/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 下午03:53:47
 */
package nc.ui.pu.m4t.action;

import java.awt.event.ActionEvent;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.scmpub.res.billtype.POBillType;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参照采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 下午03:53:47
 */
public class AddFromOrderAction extends AbstractReferenceAction {

  private static final long serialVersionUID = 7272976980678104792L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // PfUtilClient.childButtonClickedWithBusi(this.getSourceBillType(), this
    // .getModel().getContext().getPk_group(), this.getModel().getContext()
    // .getPk_loginUser(), POBillType.InitEstimate.getCode(), this.getModel()
    // .getContext().getEntranceUI(), null, null, this.getBusitypes());

    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      InitialEstVO[] vos = (InitialEstVO[]) PfUtilClient.getRetVos();

      // 补充相关的信息
      // this.fillInformation(vos);

      // 显示到转单界面上
      this.getTransferViewProcessor().processBillTransfer(vos);

    }
    BillCardPanel panel = this.editor.getBillCardPanel();
    panel.getHeadItem(InitialEstHeaderVO.NTOTALORIGMNY).setEnabled(false);
  }

  /**
   * @return editor
   */
  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          要设置的 model
   */
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
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.InitEstimate.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return UIState.NOT_EDIT == this.model.getUiState();
  }

}

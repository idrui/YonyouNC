package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 从项目的物资及服务需求单拉单
 * 
 * @since 6.3.1
 * @version 2013-8-8 下午01:32:26
 * @author fanly3
 */
public class AddFrom4D14Action extends AbstractReferenceAction {

  private static final long serialVersionUID = -8193872247728573192L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isPIMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0029")/*
                                                                   * @res
                                                                   * "未安装项目管理模块该功能不可用！"
                                                                   */);
    }
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      StoreReqAppVO[] vos = (StoreReqAppVO[]) PfUtilClient.getRetVos();
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
      context.setCurrBilltype(POBillType.MRBill.getCode());
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
    context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
    return context;
  }

  /**
   * 设置单据字段的编辑性
   */
  private void setEditable() {
    // 参照物资及服务需求单,物料和项目不允许修改
    BillCardPanel panel = this.editor.getBillCardPanel();
    // 表头项目不允许修改
    panel.getHeadItem(StoreReqAppHeaderVO.PK_PROJECT).setEdit(false);
    // 表体物料，项目不允许修改
    for (int i = 0; i < panel.getRowCount(); ++i) {
      panel.setCellEditable(i, StoreReqAppItemVO.PK_MATERIAL, false);
      panel.setCellEditable(i, StoreReqAppItemVO.PK_SRCMATERIAL, false);
      panel.setCellEditable(i, StoreReqAppItemVO.CPROJECTID, false);
      panel.setCellEditable(i, StoreReqAppItemVO.CBS, false);
    }
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }
}

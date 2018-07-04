package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.fa.IArriveForFA;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 删除资产卡片 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class DelFACardUIAction extends NCAction {
  private static final long serialVersionUID = -1228883677361782080L;

  private ArriveCardForm billForm;

  private BillListView list;

  private BillManageModel model;

  public DelFACardUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_DELASSETCARD);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAIMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0183")/*
                                                                   * @res
                                                                   * "资产信息管理模块未启用,无法删除设备卡片!"
                                                                   */);
    }
    ArriveVO oldvo = (ArriveVO) this.model.getSelectedData();
    ArriveItemVO[] bvos = this.getBVOs(oldvo);
    if (bvos.length == 0 || bvos[0] == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0005")/*
                                                                   * @res
                                                                   * "请选中表体行"
                                                                   */);
    }
    ArriveVO vo = (ArriveVO) oldvo.clone();
    vo.setBVO(bvos);
    ArriveVO[] vos = new ArriveVO[] {
      vo
    };
    ClientBillToServer<ArriveVO> tool = new ClientBillToServer<ArriveVO>();
    ArriveVO[] lightVOs = tool.construct(vos, vos);
    lightVOs =
        NCLocator.getInstance().lookup(IArriveForFA.class)
            .deleteFACard(lightVOs);

    new ClientBillCombinServer<ArriveVO>().combine(new ArriveVO[] {
      oldvo
    }, lightVOs);
    this.model.directlyUpdate(oldvo);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004040_0", "04004040-0016")/*
                                                                 * @res
                                                                 * "取消生成设备卡片成功"
                                                                 */, this.model
        .getContext());
  }

  public ArriveCardForm getBillForm() {
    return this.billForm;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillForm(ArriveCardForm billForm) {
    this.billForm = billForm;
    this.billForm.getModel().addAppEventListener(this);
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private ArriveItemVO[] getBVOs(ArriveVO vo) {
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0007")/*
                                                                   * @res
                                                                   * "到货单必须审核"
                                                                   */);
    }
    int[] rows = null;
    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      rows = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {
      // 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      rows = panel.getBodyTable().getSelectedRows();
    }
    if (rows == null || rows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0008")/*
                                                                   * @res
                                                                   * "请选择到货单行数据"
                                                                   */);
    }

    BillIndex index = new BillIndex(new ArriveVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(ArriveItemVO.class);
    List<ArriveItemVO> arriveitemlist = new ArrayList<ArriveItemVO>();
    for (int row : rows) {
      String pk_arriveorder_b = null;
      if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
        pk_arriveorder_b =
            (String) this.getBillForm().getBillCardPanel()
                .getBodyValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      }
      else {
        pk_arriveorder_b =
            (String) this.list.getBillListPanel().getBodyBillModel()
                .getValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      }
      ArriveItemVO itemVO = (ArriveItemVO) index.get(meta, pk_arriveorder_b);
      if (itemVO != null && UFBoolean.TRUE.equals(itemVO.getBfaflag())) {
        arriveitemlist.add(itemVO);
      }
    }

    if (0 == arriveitemlist.size()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0017")/*
                                                                   * @res
                                                                   * "所选到货单行没有生成设备卡片，不能删除"
                                                                   */);
    }

    ArriveItemVO[] itemVOs =
        arriveitemlist.toArray(new ArriveItemVO[arriveitemlist.size()]);
    return itemVOs;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getAppUiState() == AppUiState.EDIT
        || this.model.getSelectedData() == null) {
      return false;
    }
    ArriveVO vo = (ArriveVO) this.model.getSelectedData();

    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      return false;
    }
    return true;
  }
}

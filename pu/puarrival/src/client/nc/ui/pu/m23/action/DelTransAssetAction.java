package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.fa.IArriveForFA;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
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

import org.apache.commons.lang.ArrayUtils;

/**
 * 删除转固单
 * 
 * @since 6.0
 * @version 2010-12-21 下午05:44:52
 * @author wuxla
 */

public class DelTransAssetAction extends NCAction {
  private static final long serialVersionUID = 1512370259508077154L;

  private BillForm billForm;

  private BillManageModel model;

  public DelTransAssetAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_DELTRANSASSETBILL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isFAEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0018")/*
                                                                   * @res
                                                                   * "固定资产模块未启用,无法删除资产卡片!"
                                                                   */);
    }
    ArriveVO selectedVO = this.getSelectedVO();
    if (null == selectedVO) {
      return;
    }

    ArriveVO vo = (ArriveVO) this.getModel().getSelectedData();
    ArriveVO[] vos = new ArriveVO[] {
      selectedVO
    };
    vos = new ClientBillToServer<ArriveVO>().construct(vos, vos);

    IArriveForFA service = NCLocator.getInstance().lookup(IArriveForFA.class);
    vos = service.deleteTransAsset(vos);

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    new ClientBillCombinServer<ArriveVO>().combine(new ArriveVO[] {
      vo
    }, vos);
    this.getModel().directlyUpdate(vo);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004040_0", "04004040-0019")/*
                                                                 * @res
                                                                 * "成功删除转固单"
                                                                 */, this.model
        .getContext());
  }

  public BillForm getBillForm() {
    return this.billForm;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private ArriveVO getSelectedVO() {
    ArriveVO vo = (ArriveVO) this.getModel().getSelectedData();
    if (null == vo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0021")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return null;
    }

    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0007")/*
                                                                   * @res
                                                                   * "到货单必须审核"
                                                                   */);
      return null;
    }

    int[] rows =
        this.getBillForm().getBillCardPanel().getBillTable().getSelectedRows();
    if (ArrayUtils.isEmpty(rows)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0021")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return null;
    }

    BillIndex index = new BillIndex(new ArriveVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(ArriveItemVO.class);
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    for (int row : rows) {
      String pk_arriveorder_b =
          (String) this.getBillForm().getBillCardPanel()
              .getBodyValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      ArriveItemVO itemVO = (ArriveItemVO) index.get(meta, pk_arriveorder_b);
      if (itemVO != null && UFBoolean.TRUE.equals(itemVO.getBtransasset())) {
        list.add(itemVO);
      }
    }

    if (0 == list.size()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0022")/*
                                                                   * @res
                                                                   * "所选到货单行没有生成转固单，不能删除"
                                                                   */);
      return null;
    }

    ArriveItemVO[] itemVOs = list.toArray(new ArriveItemVO[list.size()]);
    ArriveVO newVO = (ArriveVO) vo.clone();
    newVO.setBVO(itemVOs);
    return newVO;
  }

  @Override
  protected boolean isActionEnable() {
    if (AppUiState.EDIT == this.getModel().getAppUiState()) {
      return false;
    }

    if (!((ShowUpableBillForm) this.getBillForm()).isComponentVisible()) {
      return false;
    }

    ArriveVO vo = (ArriveVO) this.getModel().getSelectedData();
    return vo != null
        && vo.getHVO() != null
        && POEnumBillStatus.APPROVE.value()
            .equals(vo.getHVO().getFbillstatus());
  }

}

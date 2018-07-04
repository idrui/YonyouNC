package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;

import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * 原到货单退货按钮动作处理类
 * 
 * @since 6.0
 * @version 2012-8-8 下午03:25:10
 * @author lixyp
 */
public class BackFrom23UIAction extends CopyAction {

  public BackFrom23UIAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.PU_BACKFROMARRIVE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.checkBeforeBack();
    super.doAction(e);
    // 设置退货理由
    new BackReasonEditUtil(this.getEditor().getBillCardPanel()).setEnabled();
  }

  /**
   * 退货检查。<br />
   * 在进行退货时，对能否进行退货进行检查，没有在isActionEnable中做，是希望能明确的告诉用户为什么无法退货，否则只是置灰按钮，
   * 用户有可能不明白为什么。
   */
  private void checkBeforeBack() {
    BillManageModel model = (BillManageModel) this.getModel();

    Object[] selectedDatas = model.getSelectedOperaDatas();
    if (selectedDatas == null || selectedDatas.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0187")/*
                                                                   * @res
                                                                   * "请选择需要退货的到货单"
                                                                   */);
    }

    if (selectedDatas.length > 1) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0188")/*
                                                                   * @res
                                                                   * "不支持多选，请选择单一的到货单"
                                                                   */);
    }

    ArriveVO arriveVo = (ArriveVO) selectedDatas[0];
    if (arriveVo.getHVO().getBisback().booleanValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0189")/*
                                                                   * @res
                                                                   * "不能基于退货单退货"
                                                                   */);
    }

    if (!POEnumBillStatus.APPROVE.equalsValue(arriveVo.getHVO()
        .getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0190")/*
                                                                   * @res
                                                                   * "只有审批态的到货单才能退货"
                                                                   */);
    }

    ArriveItemVO[] itemVos = arriveVo.getBVO();
    boolean isAllBack = true;
    for (ArriveItemVO itemVo : itemVos) {
      if (!itemVo.getNnum().equals(itemVo.getNaccumbacknum())) {
        isAllBack = false;
        break;
      }
    }
    if (isAllBack) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0191")/*
                                                                   * @res
                                                                   * "您选择的到货单已经全部退货，无法继续退货"
                                                                   */);
    }
  }
}

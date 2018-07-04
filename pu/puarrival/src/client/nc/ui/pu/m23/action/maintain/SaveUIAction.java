package nc.ui.pu.m23.action.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.exception.AskWithCheckException;
import nc.vo.pu.m23.rule.ChkLiabcenterWhenSave;
import nc.vo.pu.m23.rule.ChkNumSignWhenSave;
import nc.vo.pu.m23.rule.NumAndMnySumWhenSave;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.sc.m61.exception.SCOrderAskPriceException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 保存 按钮处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class SaveUIAction extends SaveScriptAction {

  private static final long serialVersionUID = 6556301184427090719L;

  @Override
  public void doBeforAction() {
    ArriveVO aggVO = (ArriveVO) this.editor.getValue();

    ChkNumSignWhenSave chkNumSign = new ChkNumSignWhenSave();
    NumAndMnySumWhenSave numAndMnySum = new NumAndMnySumWhenSave();
    // 检查非空项
    // chkEmpty.chkEmpty(aggVO);
    // 检查数量符号的正负性
    chkNumSign.chkNumSign(aggVO);
    // 计算表头整单数量、本币价税合计
    numAndMnySum.numAndMnySum(aggVO);
    ChkLiabcenterWhenSave chkcenter = new ChkLiabcenterWhenSave();
    // 检查两个利润中心
    chkcenter.chkTwoCenter(aggVO);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(this.getFlowContext().getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return false;
    }

    ArrivalUIToBSEnv env = null;
    PfUserObject pfuo = this.getFlowContext().getUserObj();
    if (pfuo == null) {
      env = new ArrivalUIToBSEnv();
      pfuo = new PfUserObject();
    }
    else {
      env = (ArrivalUIToBSEnv) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AskNumException) {
      env.setbConfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof SCOrderAskPriceException) {
      env.setbConfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof AskWithCheckException) {
      env.setbBack(UFBoolean.TRUE);
    }
    else {
      return super.isResume(resumeInfo);
    }

    pfuo.setUserObject(env);
    this.getFlowContext().setUserObj(pfuo);
    return true;
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {
    super.processReturnObj(pretObj);

    // 刷新界面
    List<String> arriveHIDs = new ArrayList<String>();
    ArriveVO vo = (ArriveVO) this.editor.getValue();

    for (ArriveItemVO itemVo : vo.getBVO()) {
      if (itemVo.getCsourcearriveid() != null
          && !arriveHIDs.contains(itemVo.getCsourcearriveid())) {
        arriveHIDs.add(itemVo.getCsourcearriveid());
      }
    }

    if (!arriveHIDs.isEmpty()) {
      IArrivePubQuery arrivePubQuery =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      ArriveVO[] arriveVos =
          arrivePubQuery.queryAggVOByHids(arriveHIDs
              .toArray(new String[arriveHIDs.size()]));

      // 刷新前台数据
      List<ArriveVO> datas = this.getModel().getData();
      for (int i = 0; i < datas.size(); i++) {
        if (datas.get(i) == null || datas.get(i).getBVO() == null) {
          continue;
        }
        for (ArriveItemVO dataItemVo : datas.get(i).getBVO()) {
          for (ArriveVO arriveVo : arriveVos) {
            for (ArriveItemVO itemVo : arriveVo.getBVO()) {
              if (itemVo.getPk_arriveorder_b().equals(
                  dataItemVo.getPk_arriveorder_b())) {
                datas.set(i, arriveVo);
              }
            }
          }
        }
      }
    }
  }

  @Override
  protected void showSuccessInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("common", "UCH005")/* @res "保存成功" */, this
        .getModel().getContext());
  }
}

/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午12:09:34
 */
package nc.ui.pu.est.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消暂估动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-6 下午12:09:34
 */
@SuppressWarnings("serial")
public class CancelEstAction extends SCMTpaAction {
  private BillManageModel model;

  /**
   * PurchaseInUnEstAction 的构造子
   */
  public CancelEstAction() {
    super();
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.PU_CANCELESTIMATE);
    this.setEnabled(false);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    EstVO[] vos = this.getUnEstVOs();
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    boolean feeflag = this.onlyCancelFeeEst(vos);// 提示用户选择是否只取消费用暂估数据
    this.cancelEst(vos, UFBoolean.valueOf(feeflag));
    Object[] datas = this.getModel().getSelectedOperaDatas();
    this.getModel().directlyDelete(datas);
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    this.setContext(this.model.getContext());
    this.model.addAppEventListener(this);
  }

  private EstVO createUnEstVO(Object data) {
    EstVO modelVo = (EstVO) data;
    EstVO vo = (EstVO) Constructor.construct(data.getClass());
    GoodsEstVO head = Constructor.construct(modelVo.getParentVO().getClass());
    this.setHeadInfo(head, modelVo.getParentVO());
    vo.setParentVO(head);
    FeeEstVO[] oitems = modelVo.getChildrenVO();
    if (!ArrayUtils.isEmpty(oitems)) {
      FeeEstVO[] items =
          Constructor.construct(oitems[0].getClass(), oitems.length);
      this.setItemInfo(items, oitems);
      vo.setChildrenVO(items);
    }
    return vo;
  }

  private EstVO[] getUnEstVOs() {
    Object[] datas = this.getModel().getSelectedOperaDatas();
    EstVO[] vos =
        (EstVO[]) Constructor.declareArray(datas[0].getClass(), datas.length);
    for (int i = 0; i < vos.length; i++) {
      vos[i] = this.createUnEstVO(datas[i]);
    }
    return vos;
  }

  private boolean onlyCancelFeeEst(EstVO[] vos) {
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos);
    if (ArrayUtils.isEmpty(fees)) {
      return false;
    }
    String msg =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0009")/*
                             * @res
                             * "存在既作过货物暂估又作过费用暂估的记录，是否仅取消费用暂估（是：仅取消费用暂估；否：货物暂估和费用暂估全部取消）"
                             */;
    int answer = MessageDialog.showYesNoDlg(null, null, msg);
    if (UIDialog.ID_YES == answer) {
      return true;
    }
    return false;
  }

  /**
   * 方法功能描述：调用后台进行取消暂估操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @param onlyCancelFee
   * @since 6.0
   * @author zhaoyha
   * @throws BusinessException
   * @time 2010-7-6 下午02:00:41
   */
  protected void cancelEst(EstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException {
    // 子类各自实现
  }

  @Override
  protected boolean isActionEnable() {
    return !ArrayUtils.isEmpty(this.getModel().getSelectedOperaDatas());
  }

  protected void setHeadInfo(GoodsEstVO head, GoodsEstVO ohead) {
    head.setPk_stockps(ohead.getPk_stockps());
    head.setPk_stockps_b(ohead.getPk_stockps_b());
    head.setPk_financeorg(ohead.getPk_financeorg());
    head.setPk_apfinanceorg(ohead.getPk_apfinanceorg());
    head.setFtoapflag(ohead.getFtoapflag());
    head.setFtoiaflag(ohead.getFtoiaflag());
    head.setNaccestcoststtlnum(ohead.getNaccestcoststtlnum());
    head.setTs(ohead.getTs());
    head.setHts(ohead.getHts());
  }

  protected void setItemInfo(FeeEstVO[] items, FeeEstVO[] oitems) {
    for (int i = 0; i < oitems.length; i++) {
      items[i].setPk_stockps(oitems[i].getPk_stockps());
      items[i].setPk_stockps_b(oitems[i].getPk_stockps_b());
      items[i].setPk_stockps_fee(oitems[i].getPk_stockps_fee());
      items[i].setBtoap(oitems[i].getBtoap());
      items[i].setBtoia(oitems[i].getBtoia());
      items[i].setTs(oitems[i].getTs());
    }
  }

}

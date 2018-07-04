/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午02:47:16
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.report.order.IOrderReport;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmf.pub.TBBRelQueryDialog;
import nc.ui.scmf.pub.TBBRelQueryDialog.ITBBQueryScaleProcesser;
import nc.ui.scmf.pub.TBBRelQueryDialog.TBBQueryData;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.report.view.order.OrderPayExecViewMeta;
import nc.vo.pu.report.view.order.OrderPayExecViewVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>付款执行情况
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-8 下午02:47:16
 */
public class PayExecStatAction extends NCAction {
  private static class QueryScaleProcesser implements ITBBQueryScaleProcesser {

    @Override
    public void setScale(TBBQueryData tbbQueryData) {
      String pk_group = AppContext.getInstance().getPkGroup();
      this.setScale(new ListPaneScaleProcessor(pk_group, tbbQueryData.getBlp()));
    }

    private void setScale(BillScaleProcessor scale) {
      String[] mnykeys =
          new String[] {
            OrderPayExecViewMeta.NINVOICEBALANCE,
            OrderPayExecViewMeta.NINVOICEMNY,
            OrderPayExecViewMeta.NORDERBALANCE, OrderPayExecViewMeta.NORDERMNY,
            OrderPayExecViewMeta.NPAYMNY, OrderPayExecViewMeta.NUNVERIFYMNY
          };
      scale.setMnyCtlInfo(mnykeys, PosEnum.head, null,
          OrderPayExecViewMeta.CCURRENCYID, PosEnum.head, null);
      scale.process();
    }
  }

  private static final long serialVersionUID = -7666743907884949384L;

  // private BillForm billForm;

  private BillManageModel model;

  public PayExecStatAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_PAYEXECSTATE);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.SHIFT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(objs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
    }
    OrderVO[] vos = ArrayUtil.convertArrayType(objs);
    List<String> list = new ArrayList<String>();
    for (OrderVO vo : vos) {
      list.add(vo.getHVO().getPk_order());
    }
    String[] hids = list.toArray(new String[list.size()]);
    IOrderReport service = NCLocator.getInstance().lookup(IOrderReport.class);
    OrderPayExecViewVO[] views = service.queryPayExecVOs(hids);
    TBBQueryData[] tds =
        new TBBQueryData[] {
          new TBBQueryData(PuNodeKey.n40040400PE.code(), views,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004030_0", "04004030-0052")/* @res "付款执行情况" */)
        };
    TBBRelQueryDialog dlg =
        new TBBRelQueryDialog(this.getModel().getContext().getEntranceUI(),
            tds, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004030_0", "04004030-0052")/* @res "付款执行情况" */);
    dlg.setScaleProcesser(new QueryScaleProcesser());
    dlg.setReset(true);
    dlg.setResizable(true);
    dlg.showModal();
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  // public void setBillForm(BillForm billForm) {
  // this.billForm = billForm;
  // }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(objs)) {
      return false;
    }
    boolean isEnabled = false;
    if (this.getModel().getSelectedData() != null) {
      OrderVO vo = (OrderVO) this.getModel().getSelectedData();
      int status = vo.getHVO().getForderstatus().intValue();
      isEnabled =
          status == POEnumBillStatus.APPROVE.toInt()
              || status == EnumBillStatus.EXPORT.toInt();
    }
    return isEnabled;
  }

}

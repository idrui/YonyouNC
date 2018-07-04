/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.uif2.IExceptionHandler;

/**
 * <p>
 * 采购结算按钮动作父类，结算相关的按钮一般都是从这里继承即可
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public abstract class MatchDefaultAction extends SCMTpaAction {

  private static final long serialVersionUID = -5667491871111125098L;

  /** 管理应用模型 */
  private MatchManageModel model = null;

  @Override
  public IExceptionHandler getExceptionHandler() {
    // // 此处的异常处理只是显示状态栏信息用
    // DefaultExceptionHanler handler = new DefaultExceptionHanler();
    // handler.setContext(this.getModel().getContext());
    // handler.setErrormsg(NCLangRes.getInstance().getStrByID("4004060_0",
    // "04004060-0245")/* 结算失败! */);
    // return handler;
    return super.getExceptionHandler();
  }

  public MatchManageModel getModel() {
    return this.model;
  }

  /**
   * 管理应用模型设置，要求在配置文件里先配置eventHandler
   * <p>
   * <b>参数说明</b>
   * 
   * @param model
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-21 下午01:57:11
   */
  public void setModel(MatchManageModel model) {
    this.model = model;
    this.setContext(model.getContext());
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getContext().getPk_org() != null;
  }
}

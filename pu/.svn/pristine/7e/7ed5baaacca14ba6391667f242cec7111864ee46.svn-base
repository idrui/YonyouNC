package nc.ui.pu.est.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * 暂估打印菜单
 * 
 * @since 6.0
 * @version 2011-9-2 上午09:39:44
 * @author 田锋涛
 */
public class EstPrintMenuAction extends MenuAction {

  private static final long serialVersionUID = 3663322693035121880L;

  public EstPrintMenuAction() {
    // super("estPrintMenuAct",
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
    // "4004050_0", "04004050-0014")/* @res "联查" */);
    super("estPrintMenuAction", "打印"/* -=notranslate=- */);
  }

  @Override
  public Object getValue(String key) {
    if (this.getChildCount() > 0) {
      return this.getChildAction(0).getValue(key);
    }
    return super.getValue(key);
  }

  @Override
  public boolean isEnabled() {
    if (this.getChildCount() > 0) {
      // EstCardPrintAction act = (EstCardPrintAction) this.getChildAction(0);
      // return act.isActionEnable();
      return true;
    }
    return false;
  }
}

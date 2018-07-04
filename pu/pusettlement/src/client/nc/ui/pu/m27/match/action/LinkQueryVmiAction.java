package nc.ui.pu.m27.match.action;

import javax.swing.Action;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查入库单
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-1 下午10:50:00
 */
public class LinkQueryVmiAction extends LinkQueryStockAction {
  private static final long serialVersionUID = 1941125214057367981L;

  public LinkQueryVmiAction() {
    super();
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0018")/*@res "联查消耗汇总"*/);
    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

}
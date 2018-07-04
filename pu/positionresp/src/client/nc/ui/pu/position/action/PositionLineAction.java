package nc.ui.pu.position.action;

import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ά�����ܰ�ť��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-14 ����08:36:11
 */
public class PositionLineAction extends MenuAction {

  private static final long serialVersionUID = 2248947182555208820L;

  public PositionLineAction() {
    super("lineMenuAction", NCLangRes.getInstance().getStrByID("4004080_0", "04004080-0010")/*��ά��*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

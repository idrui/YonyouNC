package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.funcnode.ui.action.INCAction;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;

/**
 * 可以去掉，V6不做
 * 
 * @since 6.0
 * @version 2011-1-26 下午06:25:59
 * @author wuxla
 */

public class ImportVendorFileAction extends NCAction {

  private static final long serialVersionUID = 2309893258941055311L;

  public ImportVendorFileAction() {
    String str =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0043")/* @res "导入供应商条码文件" */;
    this.putValue(INCAction.CODE, str);
    this.setBtnName(str);
    this.putValue(Action.SHORT_DESCRIPTION, str);
    this.putValue(
        Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK
            | InputEvent.ALT_MASK));
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0044")/* @res "未实现功能" */);
  }

}

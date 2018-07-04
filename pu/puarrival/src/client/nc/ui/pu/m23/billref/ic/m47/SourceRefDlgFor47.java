package nc.ui.pu.m23.billref.ic.m47;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单为委托加工入库单提供的来源单据显示对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-27 下午03:45:29
 */
public class SourceRefDlgFor47 extends SourceRefDlg {

  private static final long serialVersionUID = 9116073734673180670L;

  public SourceRefDlgFor47(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写,用于获取上游单据的refinfo.xml的位置
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m23/billref/ic/m47/refinfo_for47.xml";
  }
}

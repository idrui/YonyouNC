package nc.ui.pu.m23.billref.pu.m21;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单为采购订单补货提供的来源单据显示对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-27 下午03:45:29
 */
public class SourceRefDlgFor21 extends SourceRefDlg {

  private static final long serialVersionUID = 7864972325432104233L;

  public SourceRefDlgFor21(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写,用于获取上游单据的refinfo.xml的位置
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m23/billref/pu/m21/refinfo_for21.xml";
  }
}

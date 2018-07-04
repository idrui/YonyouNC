package nc.ui.pu.m21.billref.srm.m4s1a;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * @since 6.31
 * @version 2014-3-26 下午12:49:13
 * @author zhangyhh
 */
public class SourceRefDlgFor4s1a extends SourceRefDlg {

  /**
   * 
   */
  private static final long serialVersionUID = 8360828526283592953L;

  public SourceRefDlgFor4s1a(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写,用于获取上游单据的refinfo.xml的位置
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m21/billref/srm/m4s1a/refinfo_for4s1a.xml";
  }
}

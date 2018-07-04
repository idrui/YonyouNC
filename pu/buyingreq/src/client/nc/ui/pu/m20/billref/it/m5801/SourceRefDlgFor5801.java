package nc.ui.pu.m20.billref.it.m5801;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * 进口合同参照请购单
 * 
 * @since 6.3.1
 * @version 2013-7-3上午11:10:39
 * @author fanly3
 */
public class SourceRefDlgFor5801 extends SourceRefDlg {

  private static final long serialVersionUID = -8512269136586570032L;

  public SourceRefDlgFor5801(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m20/billref/it/m5801/refinfo_for5801.xml";
  }

}

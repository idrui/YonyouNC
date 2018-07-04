package nc.ui.pu.m20.billref.ct.z2;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购合同参照请购单的查询对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-6 下午01:31:48
 */
public class SourceRefDlgForZ2 extends SourceRefDlg {

  private static final long serialVersionUID = 5811618879014228342L;

  public SourceRefDlgForZ2(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写,用于获取上游单据的refinfo.xml的位置
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m20/billref/ct/z2/refinfo_forZ2.xml";
  }
}

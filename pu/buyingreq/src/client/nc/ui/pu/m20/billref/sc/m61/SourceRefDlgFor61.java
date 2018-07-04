package nc.ui.pu.m20.billref.sc.m61;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>委外订单参照请购单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-20 下午04:23:08
 */
public class SourceRefDlgFor61 extends SourceRefDlg {

  private static final long serialVersionUID = 5811618879014228342L;

  public SourceRefDlgFor61(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写,用于获取上游单据的refinfo.xml的位置
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m20/billref/sc/m61/refinfo_for61.xml";
  }
}

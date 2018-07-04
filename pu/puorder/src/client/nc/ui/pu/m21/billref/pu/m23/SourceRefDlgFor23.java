package nc.ui.pu.m21.billref.pu.m23;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单为采购到货单提供的转单界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-1 下午01:22:16
 */
public class SourceRefDlgFor23 extends SourceRefDlg {
  private static final long serialVersionUID = -4959997802403335437L;

  public SourceRefDlgFor23(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar,true);
  }

  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m21/billref/pu/m23/refinfo_for23.xml";
  }

}

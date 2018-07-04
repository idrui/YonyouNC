/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午07:30:51
 */
package nc.ui.pu.m21.billref.pu.m30;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>协同采购订单为销售订单提供的转单界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午07:30:51
 */
public class SourceRefDlgFor30 extends SourceRefDlg {

  private static final long serialVersionUID = -1235319992219387824L;

  /**
   * SourceRefDlgFor30 的构造子
   * 
   * @param parent
   * @param bsVar
   */
  public SourceRefDlgFor30(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.billref.src.view.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m21/billref/pu/m30/refinfo_for30.xml";
  }
}

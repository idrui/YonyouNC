/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-26 ����04:23:08
 */
package nc.ui.pu.m20.billref.pu.m21;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-26 ����04:23:08
 */
public class SourceRefDlgFor21 extends SourceRefDlg {

  /**
   * 
   */
  private static final long serialVersionUID = 5811618879014228342L;

  public SourceRefDlgFor21(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * ���෽����д,���ڻ�ȡ���ε��ݵ�refinfo.xml��λ��
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m20/billref/pu/m21/refinfo_for21.xml";
  }
}
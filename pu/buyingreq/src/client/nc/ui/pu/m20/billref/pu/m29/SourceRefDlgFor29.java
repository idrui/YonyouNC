/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-26 ����04:23:08
 */
package nc.ui.pu.m20.billref.pu.m29;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ���۵������빺��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-8 ����01:55:18
 */
public class SourceRefDlgFor29 extends SourceRefDlg {

  private static final long serialVersionUID = 5811618879014228342L;

  public SourceRefDlgFor29(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * ���෽����д,���ڻ�ȡ���ε��ݵ�refinfo.xml��λ��
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m20/billref/pu/m29/refinfo_for29.xml";
  }
}

/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 ����04:42:05
 */
package nc.ui.pu.m21.billref.pu.m4t;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����Ϊ�ڳ��ݹ����ṩ��ת������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 ����04:42:05
 */
public class SourceRefDlgFor4t extends SourceRefDlg {

  private static final long serialVersionUID = -7331589555188641612L;

  /**
   * SourceRefDlgFor4t �Ĺ�����
   * 
   * @param parent
   * @param bsVar
   */
  public SourceRefDlgFor4t(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar,true);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.billref.src.view.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m21/billref/pu/m4t/refinfo_for4t.xml";
  }

}

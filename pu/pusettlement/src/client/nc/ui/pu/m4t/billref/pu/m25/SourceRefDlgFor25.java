/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 ����10:47:52
 */
package nc.ui.pu.m4t.billref.pu.m25;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ���Ϊ�ɹ���Ʊ�ṩ��ת������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 ����10:47:52
 */
public class SourceRefDlgFor25 extends SourceRefDlg {

  /**
   * 
   */
  private static final long serialVersionUID = -2018818022347734833L;

  /**
   * SourceRefDlgFor25 �Ĺ�����
   * 
   * @param parent
   * @param bsVar
   */
  public SourceRefDlgFor25(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar,true);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.billref.src.view.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m4t/billref/pu/m25/refinfo_for25.xml";
  }

}

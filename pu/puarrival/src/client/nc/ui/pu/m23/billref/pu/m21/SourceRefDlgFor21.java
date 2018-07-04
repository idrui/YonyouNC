package nc.ui.pu.m23.billref.pu.m21;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ϊ�ɹ����������ṩ����Դ������ʾ�Ի���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-27 ����03:45:29
 */
public class SourceRefDlgFor21 extends SourceRefDlg {

  private static final long serialVersionUID = 7864972325432104233L;

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
    return "nc/ui/pu/m23/billref/pu/m21/refinfo_for21.xml";
  }
}

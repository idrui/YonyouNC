package nc.ui.pu.m23.billref.ic.m47;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ϊί�мӹ���ⵥ�ṩ����Դ������ʾ�Ի���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-27 ����03:45:29
 */
public class SourceRefDlgFor47 extends SourceRefDlg {

  private static final long serialVersionUID = 9116073734673180670L;

  public SourceRefDlgFor47(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  /**
   * ���෽����д,���ڻ�ȡ���ε��ݵ�refinfo.xml��λ��
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m23/billref/ic/m47/refinfo_for47.xml";
  }
}

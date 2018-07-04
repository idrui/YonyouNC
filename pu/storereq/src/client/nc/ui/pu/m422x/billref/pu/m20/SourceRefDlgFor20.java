package nc.ui.pu.m422x.billref.pu.m20;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * @since 6.0
 * @version 2012-5-29 ����12:49:13
 * @author lixyp
 */
public class SourceRefDlgFor20 extends SourceRefDlg {

  /**
   * 
   */
  private static final long serialVersionUID = 5811618879014228342L;

  public SourceRefDlgFor20(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar,true);
    this.setResizable(true);
  }

  /**
   * ���෽����д,���ڻ�ȡ���ε��ݵ�refinfo.xml��λ��
   * 
   * @see nc.ui.scmpub.billref.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m422x/billref/pu/m20/refinfo_for20.xml";
  }
}

package nc.ui.pu.m21.billref.ic.m45;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����Ϊ�ɹ���ⵥ�ṩ��ת������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-1 ����01:22:16
 */
public class SourceRefDlgFor45 extends SourceRefDlg {
  private static final long serialVersionUID = -4959997802403335437L;

  public SourceRefDlgFor45(Container parent, BillSourceVar bsVar) {
    super(parent, bsVar, true);
  }

  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/pu/m21/billref/ic/m45/refinfo_for45.xml";
  }

}
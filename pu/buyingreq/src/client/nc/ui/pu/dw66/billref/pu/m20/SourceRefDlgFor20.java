package nc.ui.pu.dw66.billref.pu.m20;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class SourceRefDlgFor20 extends SourceRefDlg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SourceRefDlgFor20(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
		// TODO �Զ����ɵĹ��캯�����
	}

	/**
	 * ��ȡ���� ��Ӧ���ս����ӦVO���ݵ�xml·��
	 */
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/pu/dw66/billref/pu/m20/refinfo_dw66for20.xml";

	}

}

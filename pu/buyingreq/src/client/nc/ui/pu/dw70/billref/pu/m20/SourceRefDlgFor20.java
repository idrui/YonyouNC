package nc.ui.pu.dw70.billref.pu.m20;

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
		// TODO 自动生成的构造函数存根
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/pu/dw70/billref/pu/m20/refinfo_dw70for20.xml";
	}
}

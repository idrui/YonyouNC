package nc.ui.pu.cgfa.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/*来源单据显示类*/
public class TJ01RefQG20SourceRefDlg extends SourceRefDlg {

	private static final long serialVersionUID = 3581432494198863324L;

	public TJ01RefQG20SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/pu/cgfa/billref/TJ01RefQg20Info.xml";
	}
}
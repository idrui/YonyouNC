package nc.ui.pu.qst.newoascc.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;
/**
  batch addLine or insLine action autogen
*/
public class NewoasccAddLineAction extends BatchAddLineAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setDefaultData(Object obj) {
		super.setDefaultData(obj);
		NewoasccHeadVO singleDocVO = (NewoasccHeadVO) obj;
		//singleDocVO.setPk_group(this.getModel().getContext().getPk_group());
		//singleDocVO.setPk_org(this.getModel().getContext().getPk_org());
		singleDocVO.setAttributeValue("pk_group", this.getModel().getContext().getPk_group());
		singleDocVO.setAttributeValue("pk_org", this.getModel().getContext().getPk_org());
	}

}
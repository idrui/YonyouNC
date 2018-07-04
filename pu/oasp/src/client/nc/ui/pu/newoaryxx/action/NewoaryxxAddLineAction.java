package nc.ui.pu.newoaryxx.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.pu.newoaryxx.NewoaryxxHeadVO;
/**
  batch addLine or insLine action autogen
*/
public class NewoaryxxAddLineAction extends BatchAddLineAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setDefaultData(Object obj) {
		super.setDefaultData(obj);
		NewoaryxxHeadVO singleDocVO = (NewoaryxxHeadVO) obj;
		//singleDocVO.setPk_group(this.getModel().getContext().getPk_group());
		//singleDocVO.setPk_org(this.getModel().getContext().getPk_org());
		singleDocVO.setAttributeValue("pk_group", this.getModel().getContext().getPk_group());
		singleDocVO.setAttributeValue("pk_org", this.getModel().getContext().getPk_org());
	}

}
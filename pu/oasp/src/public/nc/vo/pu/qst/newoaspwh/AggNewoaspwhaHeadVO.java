package nc.vo.pu.qst.newoaspwh;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.qst.newoaspwh.NewoaspwhaHeadVO")

public class AggNewoaspwhaHeadVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggNewoaspwhaHeadVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public NewoaspwhaHeadVO getParentVO(){
	  	return (NewoaspwhaHeadVO)this.getParent();
	  }
	  
}
package nc.vo.pu.qst.newoabase;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.qst.newoabase.NewoabaseaHeadVO")

public class AggNewoabaseaHeadVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggNewoabaseaHeadVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public NewoabaseaHeadVO getParentVO(){
	  	return (NewoabaseaHeadVO)this.getParent();
	  }
	  
}
package nc.vo.pu.cgfa;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.cgfa.Cgfa")

public class AggCgfa extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggCgfaMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public Cgfa getParentVO(){
	  	return (Cgfa)this.getParent();
	  }
	  
}
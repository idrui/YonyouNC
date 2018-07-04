package nc.vo.pu.qst.newoaspwh;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggNewoaspwhaHeadVOMeta extends AbstractBillMeta{
	
	public AggNewoaspwhaHeadVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.pu.qst.newoaspwh.NewoaspwhaHeadVO.class);
		this.addChildren(nc.vo.pu.qst.newoaspwh.NewoaspwhbBodyVO.class);
	}
}
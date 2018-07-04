package nc.ui.pu.dw66.view;

import nc.vo.lm.erpcgjhjk.AggErpcgjhjkHVO;
import nc.vo.lm.erpcgjhjk.ErpcgjhjkHVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class ErpcgjhjkViewVO extends AbstractDataView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggErpcgjhjkHVO.class);
	}
	//Ö÷±í
	public ErpcgjhjkHVO getHead() {
		return (ErpcgjhjkHVO) this.getVO(ErpcgjhjkHVO.class);
	}

	public void setHead(ErpcgjhjkHVO head) {
		this.setVO(head);
	}
}

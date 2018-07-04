package nc.ui.pu.dw70.view;

import nc.vo.lm.kycgjhxxjk.AggKycgjhxxjkHVO;
import nc.vo.lm.kycgjhxxjk.KycgjhxxjkHVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class KycgjhxxjkViewVO extends AbstractDataView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggKycgjhxxjkHVO.class);
	}
	
	//Ö÷±í
	public KycgjhxxjkHVO getHead() {
		return (KycgjhxxjkHVO) this.getVO(KycgjhxxjkHVO.class);
	}

	public void setHead(KycgjhxxjkHVO head) {
		this.setVO(head);
	}

}

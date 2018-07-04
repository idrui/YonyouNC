package nc.vo.pu.cgfa;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class Qg20ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6273841699792954203L;

	public PraybillVO changeToBill() {
		PraybillVO bill = new PraybillVO();
		bill.setParent(this.getHead());
		PraybillItemVO[] items = new PraybillItemVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}

	public PraybillHeaderVO getHead() {
		return (PraybillHeaderVO) this.getVO(PraybillHeaderVO.class);
	}

	public PraybillItemVO getItem() {
		return (PraybillItemVO) this.getVO(PraybillItemVO.class);
	}

	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				PraybillVO.class);
	}

	public void setHead(PraybillHeaderVO head) {
		this.setVO(head);
	}

	public void setItem(PraybillItemVO item) {
		this.setVO(item);
	}
}

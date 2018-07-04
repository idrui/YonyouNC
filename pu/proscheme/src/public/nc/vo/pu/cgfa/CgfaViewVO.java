/**
 * 
 */
package nc.vo.pu.cgfa;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @author wangzym
 * @version 2017年3月1日 上午8:39:19
 */
public class CgfaViewVO extends AbstractDataView {

	private static final long serialVersionUID = -1820045146832934628L;

	/**
	 * 
	 */

	public AggCgfa changeToBill() {
		AggCgfa bill = new AggCgfa();
		bill.setParent(this.getHead());
		Cgfab[] items = new Cgfab[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}

	public Cgfa getHead() {
		return (Cgfa) this.getVO(Cgfa.class);
	}

	public Cgfab getItem() {
		return (Cgfab) this.getVO(Cgfab.class);
	}

	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(AggCgfa.class);
	}

	public void setHead(Cgfa head) {
		this.setVO(head);
	}

	public void setItem(Cgfa item) {
		this.setVO(item);
	}

}

/**
 * 
 */
package nc.ui.pu.m20.action.arrange;

import nc.ui.pubapp.uif2app.actions.batch.BatchEditAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-11 ����4:42:34
 */
public class PrayarrangeArrangeAction extends BatchEditAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3529957587240593537L;
	
	/* 
	 * ���෽����д
	 *
	 * @see nc.ui.uif2.actions.batch.BatchEditAction#isActionEnable()
	 */
	@Override
	protected boolean isActionEnable() {
		BatchBillTableModel model=this.getModel();
		
	    if (model.getUiState() != UIState.NOT_EDIT) {
	      return false;
	    }

	    if (model.getRows().isEmpty()) {
	      return false;
	    }

	    PrayarrangeViewVO view = (PrayarrangeViewVO) model.getRow(0);

		return !view.getBisarrange().booleanValue();
	}

}

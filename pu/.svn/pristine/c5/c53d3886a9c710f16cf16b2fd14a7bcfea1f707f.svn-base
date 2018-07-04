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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-11 下午4:42:34
 */
public class PrayarrangeArrangeAction extends BatchEditAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3529957587240593537L;
	
	/* 
	 * 父类方法重写
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

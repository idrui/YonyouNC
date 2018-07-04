/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午10:53:52
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购部门
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午10:53:52
 */
public class Dept implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    String pk_org =
        (String) e.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_PURCHASEORG)
            .getValueObject();

		if (pk_org == null) {
			e.setReturnValue(Boolean.FALSE);
		} else {
			BillItem biv = e.getBillCardPanel().getHeadTailItem(
					InitialEstHeaderVO.PK_DEPT_V);
			UIRefPane panev = (UIRefPane) biv.getComponent();
			FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(panev)
					.filterItemRefByOrg(pk_org);

			BillItem bi = e.getBillCardPanel().getHeadTailItem(
					InitialEstHeaderVO.PK_DEPT);
			UIRefPane pane = (UIRefPane) bi.getComponent();
			FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(pane).filterItemRefByOrg(
					pk_org);
			e.setReturnValue(Boolean.TRUE);
		}

  }

}

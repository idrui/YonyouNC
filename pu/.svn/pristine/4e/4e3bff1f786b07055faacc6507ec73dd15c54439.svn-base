/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 上午11:08:24
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.scmpub.res.billtype.CTBillType;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料，如果订单参照合同，控制物料参照范围
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 上午11:08:24
 */
public class Material implements ICardBodyBeforeEditEventListener {

	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
		int row = event.getRow();
		BillCardPanel panel = event.getBillCardPanel();
		String sourcetype = (String) panel.getBodyValueAt(row,
				OrderItemVO.CSOURCETYPECODE);
		String marbasclass = null;
		if (ObjectUtils.equals(CTBillType.PurDaily.getCode(), sourcetype)) {
			String ccontractrowid = (String) panel.getBodyValueAt(row,
					OrderItemVO.CCONTRACTROWID);
			CtBusinessVO ctvo = this.queryCtBusinessByPks(event, ccontractrowid);
			if (ctvo != null
					&& Ninvctlstyle.MARBASCLASS.value().equals(ctvo.getNinvctlstyle())) {
				marbasclass = ctvo.getPk_marbasclass();
			}
		}

		this.filterMaterial(panel, marbasclass);
	}

	private void filterMaterial(BillCardPanel panel, String marbasclass) {
		Object pk_org = panel.getHeadItem(InitialEstHeaderVO.PK_ORG)
				.getValueObject();
		UIRefPane refpane = (UIRefPane) panel.getBodyItem(OrderItemVO.PK_MATERIAL)
				.getComponent();
		FilterMaterialRefUtils filter = new FilterMaterialRefUtils(refpane);
		if (marbasclass != null) {
			filter.filterRefByMarBasClass(marbasclass);
		} else {
			((AbstractRefTreeModel) refpane.getRefModel()).setClassWherePart(null);
		}
		filter.setMultiSelectedEnabled(true);
		filter.filterItemRefByOrg(pk_org.toString());
	}

	private CtBusinessVO queryCtBusinessByPks(CardBodyBeforeEditEvent event,
			String ccontractrowid) {
		ClientContext ctx = (ClientContext) event.getContext();
		return ctx.getCtBusiType(ccontractrowid);
	}

}

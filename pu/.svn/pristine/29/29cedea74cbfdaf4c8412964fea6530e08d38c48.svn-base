/**
 * 
 */
package nc.ui.pu.m20.view;

import org.apache.commons.lang.ArrayUtils;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.value.BillCardPanelBodyVOValueAdapter;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.log.Log;


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
 * @time 2014-6-25 下午4:50:31
 */
public class PrayarrangeVOValueAdapter extends BillCardPanelBodyVOValueAdapter {

	  /* 
	 * 父类方法重写
	 *
	 * @see nc.ui.pubapp.uif2app.view.value.BillCardPanelBodyVOValueAdapter#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object object) {
		if (object == null) {
			Log.debug(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4001001_0", "04001001-0089")/*
												 * @res
												 * "BillCardPanelVOValueGetter填充空值"
												 */);
			return;
		}
		Object[] objs = null;
		if (object.getClass().isArray()) {
			objs = (Object[]) object;
		} else {
			objs = new Object[] { object };
		}

		BillCardPanel panel = (BillCardPanel) this.getComponent();
		if (ArrayUtils.isEmpty(objs)) {
			panel.getBillData().setBodyValueVO(null);
			return;
		}

		CircularlyAccessibleValueObject[] bodyVOes = new CircularlyAccessibleValueObject[objs.length];

		for (int i = 0; i < objs.length; i++) {
			bodyVOes[i] = (CircularlyAccessibleValueObject) objs[i];
		}

		// 表头
		BillItem[] headItems = panel.getBillData().getHeadTailItems();
		if (headItems != null) {
			CircularlyAccessibleValueObject hvo = bodyVOes[0];
			for (BillItem headItem : headItems) {
				panel.setHeadItem(headItem.getKey(),
						hvo.getAttributeValue(headItem.getKey()));
			}
		}

		panel.getBillData().setBodyValueVO(bodyVOes);
		panel.getBillModel().loadLoadRelationItemValue();
	}
	
}

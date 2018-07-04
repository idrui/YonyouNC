/**   
 * Copyright  2017 Yonyou. All rights reserved.
 * 
 * @Title: BodyDelLineActionForCGFA.java 
 * @Prject: pu
 * @Package: nc.ui.pu.cgfa.action 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2017年11月29日 下午9:58:23 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.action;

import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.vo.pub.lang.UFDouble;

/**
 * @ClassName: BodyDelLineActionForCGFA
 * @Description: TODO
 * @author: wangzy
 * @date: 2017年11月29日 下午9:58:23
 */
public class BodyDelLineActionForCGFA extends BodyDelLineAction {

	/**
	 * @Title:BodyDelLineActionForCGFA
	 * @Description:TODO
	 */
	public BodyDelLineActionForCGFA() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: doAction
	 * 
	 * @Description: TODO
	 * 
	 * @see nc.ui.pubapp.uif2app.actions.BodyDelLineAction#doAction()
	 */
	@Override
	public void doAction() {
		// TODO 自动生成的方法存根
		super.doAction();
		// 删行重新计算表体
		nc.ui.pub.bill.BillCardPanel bcp = this.getCardPanel();
		int rowCount = bcp.getBillModel().getRowCount();
		UFDouble hSum = UFDouble.ZERO_DBL;
		for (int i = 0; i < rowCount; i++) {
			hSum = hSum
					.add(bcp.getBodyValueAt(i, "vbdef2") == null ? UFDouble.ZERO_DBL
							: new UFDouble(bcp.getBodyValueAt(i,
									"vbdef2").toString()));
		}
		bcp.setHeadItem("forecastprice", hSum);
	}

}

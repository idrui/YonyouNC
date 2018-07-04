/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-20 上午08:19:55
 */
package nc.ui.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询本位币和汇率
 * <li>默认值规则：采购订单带入——>根据原币币种+结算财务组织本位币币种+单据日期匹配财务组织汇率方案自动获取折本汇率值。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-20 上午08:19:55
 */
public class CurrencyAndExchangeRate {
	private BillCardPanel panel;

	public CurrencyAndExchangeRate(BillCardPanel panel) {
		this.panel = panel;
	}

	/**
	 * 方法功能描述：设置本位币和汇率
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param fromOrder
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-20 上午08:24:47
	 */
	public void setCurrencyAndExchangerate(boolean fromOrder) {
		String corigcurrencyid = (String) this.panel.getHeadItem(
				InitialEstHeaderVO.CORIGCURRENCYID).getValueObject();
		String pk_org = (String) this.panel.getHeadItem(InitialEstHeaderVO.PK_ORG)
				.getValueObject();
		if (null == corigcurrencyid || null == pk_org) {
			return;
		}

		// 设置本币
		String ccurrencyid = (String) this.panel.getHeadItem(
				InitialEstHeaderVO.CCURRENCYID).getValueObject();
		if (null == ccurrencyid) {
			ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);

			this.panel.setHeadItem(InitialEstHeaderVO.CCURRENCYID, ccurrencyid);
		}

		// 设置汇率
		UFDouble rate = (UFDouble) this.panel.getHeadItem(
				InitialEstHeaderVO.NEXCHANGERATE).getValueObject();
		if (fromOrder && rate != null) {
			return;
		}

		// 日期为业务日期，不是入库日期，入库日期默认为组织的启用日期
		UFDate date = ClientContext.getInstance().getBusiDate();
		rate = CurrencyRate.getCurrencySellRateByOrg(pk_org, corigcurrencyid,
				ccurrencyid, date);

		/*
		 * change by wandl 修改币种清空单价金额
		 */
		// if(rate == null|| rate.compareTo(UFDouble.ZERO_DBL)==0){
		int rowCount = panel.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NASTTAXPRICE);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NASTPRICE);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NASTORIGPRICE);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NASTORIGTAXPRICE);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NMNY);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NORIGMNY);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NORIGTAXMNY);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NORIGTAXPRICE);
			this.panel.setBodyValueAt(null, i, InitialEstItemVO.NORIGPRICE);
		}
		// }

		this.panel.setHeadItem(InitialEstHeaderVO.NEXCHANGERATE, rate);
	}
}

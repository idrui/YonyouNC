/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-20 ����08:19:55
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ��λ�Һͻ���
 * <li>Ĭ��ֵ���򣺲ɹ��������롪��>����ԭ�ұ���+���������֯��λ�ұ���+��������ƥ�������֯���ʷ����Զ���ȡ�۱�����ֵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-20 ����08:19:55
 */
public class CurrencyAndExchangeRate {
	private BillCardPanel panel;

	public CurrencyAndExchangeRate(BillCardPanel panel) {
		this.panel = panel;
	}

	/**
	 * �����������������ñ�λ�Һͻ���
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param fromOrder
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-20 ����08:24:47
	 */
	public void setCurrencyAndExchangerate(boolean fromOrder) {
		String corigcurrencyid = (String) this.panel.getHeadItem(
				InitialEstHeaderVO.CORIGCURRENCYID).getValueObject();
		String pk_org = (String) this.panel.getHeadItem(InitialEstHeaderVO.PK_ORG)
				.getValueObject();
		if (null == corigcurrencyid || null == pk_org) {
			return;
		}

		// ���ñ���
		String ccurrencyid = (String) this.panel.getHeadItem(
				InitialEstHeaderVO.CCURRENCYID).getValueObject();
		if (null == ccurrencyid) {
			ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);

			this.panel.setHeadItem(InitialEstHeaderVO.CCURRENCYID, ccurrencyid);
		}

		// ���û���
		UFDouble rate = (UFDouble) this.panel.getHeadItem(
				InitialEstHeaderVO.NEXCHANGERATE).getValueObject();
		if (fromOrder && rate != null) {
			return;
		}

		// ����Ϊҵ�����ڣ�����������ڣ��������Ĭ��Ϊ��֯����������
		UFDate date = ClientContext.getInstance().getBusiDate();
		rate = CurrencyRate.getCurrencySellRateByOrg(pk_org, corigcurrencyid,
				ccurrencyid, date);

		/*
		 * change by wandl �޸ı�����յ��۽��
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

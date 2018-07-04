package nc.vo.pu.upgrade.v636;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pu.pub.VOQryUtil;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.SecondaryPage;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.itf.pmpub.pub.IWBSService;
import nc.itf.scmpub.reference.uap.cmtproxy.CMTProxySrv;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.upgrade.v636.util.PrayarrangeUpgrate;
import nc.vo.pu.upgrade.v636.util.StoreReqTrantypeUpgrate;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * �����������ʵ���� ע����汾��ص����������������������Դ�汾��
 * 
 * @since 6.36
 * @version 2015-3-30 ����9:04:39
 * @author mengjian
 */
public class PUUpgradeTOV636Util {

	/**
	 * vo�������֮ǰ��Υ����������֮ǰ�汾�д��ڷ�Z8��Ԥ�������� delete from pub_vochange_b where
	 * pk_vochange_b ='1001AA10000000000KSY' and pk_vochange
	 * ='1002Z8100000000046GP' delete from pub_vochange_b where pk_vochange_b like
	 * '1001ZZ1000000000A7%' and pk_vochange ='1001Z810000000008W8A' delete from
	 * pub_vochange_b where pk_vochange_b like '1001ZZ1000000000A7%' and
	 * pk_vochange ='1001Z810000000008W8M' delete from pub_vochange_b where
	 * pk_vochange_b= '1001ZP1000000006A4MQ' and pk_vochange
	 * ='1001Z81000000004ENF8'
	 */
	public void deleteVOChange_bDate() {
		Logger.debug("vo�������֮ǰ��Υ����������������ʼ������������");
		DataAccessUtils util = new DataAccessUtils();
		String sql = "";
		sql = " delete from pub_vochange_b where pk_vochange_b like  '1001ZZ1000000000A7%' and pk_vochange ='1001Z810000000008W8A' ";
		util.update(sql);
		sql = " delete from pub_vochange_b where pk_vochange_b like  '1001ZZ1000000000A7%' and pk_vochange ='1001Z810000000008W8M'";
		util.update(sql);
		sql = " delete from pub_vochange_b where pk_vochange_b= '1001ZP1000000006A4MQ' and pk_vochange ='1001Z81000000004ENF8'";
		util.update(sql);
		sql = " delete from pub_vochange_b where pk_vochange_b ='1001AA10000000000KSY' and pk_vochange ='1002Z8100000000046GP'";
		util.update(sql);
		Logger.debug("vo�������֮ǰ��Υ������������������������������");
	}

	/**
	 * ����������Ʒ���ͣ�Ĭ��Ϊ����Ʒ�� �����������������ֵ���ջ���������ûֵ���򽫽����������ĵ�ֵ�����ջ���������
	 */
	public void upArrivalColumnData() {
		Logger.debug("��������Ʒ�����ֶ�������ʼ������������");
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_arriveorder_b set fproductclass=1 where fproductclass is null ");
		DataAccessUtils util = new DataAccessUtils();
		util.update(sql.toString());
		Logger.debug("��������Ʒ�����ֶ���������������������");
		Logger.debug("�����ջ����������ֶ�������ʼ������������");
		SqlBuilder sql2 = new SqlBuilder();
		sql2.append(" update po_arriveorder_b set pk_arrliabcenter = pk_apliabcenter, pk_arrliabcenter_v = pk_apliabcenter_v where pk_apliabcenter <> '~' and (pk_arrliabcenter is null or pk_arrliabcenter = '~') ");
		util.update(sql2.toString());
		Logger.debug("�����ջ���������������������������������");

	}

	/**
	 * ί�мӹ�����񸱱�����Ʒ���ͣ�Ĭ��Ϊ����Ʒ��
	 */
	public void upSubcontinfiColumnData() {
		Logger.debug("ί�мӹ�����񸱱���Ʒ�����ֶ�������ʼ������������");
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_subcontinfi_b set foutputtype = 1 where foutputtype is null ");
		DataAccessUtils util = new DataAccessUtils();
		util.update(sql.toString());
		Logger.debug("ί�мӹ�����񸱱���Ʒ�����ֶ���������������������");
	}

	/**
	 * ����������������������ȡ����������������
	 */
	public void upArrliabcenterColumnData() {
		Logger.debug("����������������������ȡ����������������������ʼ������������");
		SqlBuilder sql = new SqlBuilder();
		DataAccessUtils util = new DataAccessUtils();
		sql.append(" update  po_arriveorder_b set po_arriveorder_b.pk_apliabcenter = ");
		sql.append(" (select po_order_b.pk_apliabcenter from po_order_b ");
		sql.append(" where po_order_b.pk_order_b = po_arriveorder_b.pk_order_b )");
		sql.append(" where exists  ");
		sql.append(" (select 1 from po_order_b ");
		sql.append(" where po_order_b.pk_order_b = po_arriveorder_b.pk_order_b)");
		util.update(sql.toString());
		sql.reset();
		sql.append(" update  po_arriveorder_b set po_arriveorder_b.pk_apliabcenter_v = ");
		sql.append(" (select po_order_b.pk_apliabcenter_v from po_order_b ");
		sql.append(" where po_order_b.pk_order_b = po_arriveorder_b.pk_order_b )");
		sql.append(" where exists ");
		sql.append(" (select 1 from po_order_b ");
		sql.append(" where po_order_b.pk_order_b = po_arriveorder_b.pk_order_b)");
		util.update(sql.toString());
		Logger.debug("����������������������ȡ������������������������������������");
	}

	/**
	 * �ɹ�����---����Э����������
	 */
	@SuppressWarnings({ "unchecked" })
	public void upgradeForPayment() {
		Logger.debug("�ɹ���������Э��������ʼ������������");
		Logger.debug("---��ѯ���б�ͷ��������Э�����ݵĲɹ�������ʼ---");
		SqlBuilder sql = new SqlBuilder();
		sql.append("select p.pk_payterm,p.pk_order from po_order p where p.pk_payterm != '~' and p.dr = 0");
		DataAccessUtils util = new DataAccessUtils();
		IRowSet query = util.query("select pk_payment from po_order_payment");
		if (query != null && query.next()) {
			Logger.debug("---����Э����������Ѿ�����������������---");
			return;
		}
		IRowSet set = util.query(sql.toString());
		if (set != null) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			while (set.next()) {
				String pk_payterm = set.getString(0);
				String pk_order = set.getString(1);
				List<String> list = map.get(pk_payterm);
				if (list == null) {
					list = new ArrayList<String>();
					map.put(pk_payterm, list);
				}
				list.add(pk_order);
			}
			Logger.debug("---��ѯ���б�ͷ��������Э�����ݵĲɹ���������---");
			Logger.debug("---��ѯ����Э�鵵�����ݿ�ʼ---");
			IMDPersistenceQueryService service = MDPersistenceService
					.lookupPersistenceQueryService();
			Collection<SuperVO> vos = null;
			try {
				vos = service.queryBillOfVOByPKs(PaymentVO.class,
						map.keySet().toArray(new String[0]), false);
			} catch (MetaDataException e) {
				ExceptionUtils.wrappException(e);
			}
			Logger.debug("---��ѯ����Э�鵵�����ݽ���---");
			if (vos != null && !vos.isEmpty()) {
				Logger.debug("---�������б�ͷ��������Э�����ݵĲɹ��������帶��Э��ҳǩ��ʼ---");
				ArrayList<OrderPaymentVO> paymentlist = new ArrayList<OrderPaymentVO>();
				for (SuperVO vo : vos) {
					PaymentChVO[] chvos = ((PaymentVO) vo).getPaymentch();
					String pk_payment = ((PaymentVO) vo).getPk_payment();
					List<String> pk_orders = map.get(pk_payment);
					for (PaymentChVO chvo : chvos) {
						OrderPaymentVO paymentVO = new OrderPaymentVO();
						paymentVO.setAccountday(chvo.getAccountday());
						paymentVO.setAccrate(chvo.getAccrate());
						paymentVO.setCheckdata(chvo.getCheckdata());
						paymentVO.setEffectaddmonth(chvo.getEffectaddmonth());
						paymentVO.setEffectdateadddate(chvo.getEffectdateadddate());
						if (chvo.getEffectmonth() == null) {
							paymentVO.setEffectmonth(null);
						} else {
							paymentVO.setEffectmonth(Integer.valueOf(chvo.getEffectmonth()));
						}
						paymentVO.setIsdeposit(chvo.getIsdeposit());
						paymentVO.setPaymentday(chvo.getPaymentday());
						paymentVO.setPk_balatype(chvo.getPk_balatype());
						paymentVO.setPk_payperiod(chvo.getPk_payperiod());
						paymentVO.setPk_rate(chvo.getPk_rate());
						paymentVO.setPrepayment(chvo.getPrepayment());
						paymentVO.setShoworder(chvo.getShoworder());
						for (String pk_order : pk_orders) {
							OrderPaymentVO newVO = (OrderPaymentVO) paymentVO.clone();
							newVO.setPk_order(pk_order);
							paymentlist.add(newVO);
						}
					}
				}
				VOInsert<OrderPaymentVO> update = new VOInsert<OrderPaymentVO>();
				update.insert(paymentlist.toArray(new OrderPaymentVO[0]));
				Logger.debug("---�������б�ͷ��������Э�����ݵĲɹ��������帶��Э��ҳǩ����---");
				Logger.debug("---���¸���ƻ���ʼ---");
				sql.reset();
				sql.append(" update po_order_payplan ");
				sql.append(" set pk_paymentch = ");
				sql.startParentheses();
				sql.append(" select pk_payment from po_order_payment ");
				sql.append(" where ");
				sql.append(" po_order_payplan.pk_order = po_order_payment.pk_order ");
				sql.append(" and po_order_payplan.iaccounttermno = po_order_payment.showorder ");
				sql.append(" and po_order_payplan.dr = 0 ");
				sql.append(" and po_order_payment.dr = 0 ");
				sql.endParentheses();
				util.update(sql.toString());
				Logger.debug("---���¸���ƻ�����---");
			} else {
				Logger.debug("��ѯ���ĸ���Э�鵵������Ϊ��,�ɹ���������Э����������������������");
			}
		} else {
			Logger.debug("��ѯ�������������Ĳɹ�������Ϊ�㣬����Э����������������������");
		}
		Logger.debug("�ɹ���������Э����������������������");
	}

	/**
	 * �빺��---�빺������������ �빺�������������ԡ���Ҫ�빺���š���Ĭ��Ϊ���񡱡� �빺���������Ѱ����ֶ�Ĭ��Ϊ���񡱡�
	 */
	public void upgradeForPrayarrange() {
		Logger.debug("�빺��������������������ʼ������������");
		PrayarrangeUpgrate.upgrate();
		Logger.debug("�빺����������������������������������");
		Logger.debug("�빺�������Ѱ����ֶ�������ʼ������������");
		DataAccessUtils utils = new DataAccessUtils();
		SqlBuilder update = new SqlBuilder();
		update
				.append("update po_praybill_b set bisarrange = 'N' where bisarrange is null");
		utils.update(update.toString());
		Logger.debug("�빺�����������Ѱ����ֶ���������������������");
	}

	/**
	 * �ɹ�����v63->v63EHP���������� ��������freqtypeflag��Ĭ�����ó�ë����0��
	 * ����������������vtrantypecode�������422XĬ�����ó���ͨ������������422X-01�� ��ƽ��bendgather��N
	 * ԭʼ��������֯���´�ƽ������֯��Ĭ�����ó�����֯��
	 */
	public void upgradeStorereqDefaultValue() {
		Logger.debug("�����������뵥�ֶ�������ʼ������������");
		String parallel_storereq = DBHintConstantValue.getHintCode("po_storereq");
		DataAccessUtils utils = new DataAccessUtils();
		utils.update("update " + parallel_storereq
				+ " po_storereq  set  freqtypeflag = 0 where freqtypeflag is null  ");
		utils
				.update("update "
						+ parallel_storereq
						+ " po_storereq  set  vtrantypecode = '422X-01' where vtrantypecode = '422X' ");
		parallel_storereq = DBHintConstantValue.getHintCode("po_storereq_b");
		utils
				.update("update "
						+ parallel_storereq
						+ " po_storereq_b  set pk_reqstoorg=pk_org,pk_nextbalanceorg=pk_org,"
						+ "pk_reqstoorg_v=pk_org_v,pk_nextbalanceorg_v=pk_org_v,bendgather='N'  "
						+ "where pk_reqstoorg is null and pk_nextbalanceorg is null ");
		Logger.debug("�����������뵥�ֶ���������������������");
		Logger.debug("�����������뵥�ֶν�������������ʼ������������");
		StoreReqTrantypeUpgrate.upgrate(parallel_storereq);
		Logger.debug("�����������뵥�ֶν���������������������������");

	}

	/**
	 * ����po_invoice_b���ж������������ֶ� vordertrantype,�ɱ����Ϊ����
	 */

	public void upInvoiceColumnData() {
		Logger.debug("�ɹ���Ʊ�������������ֶ�������ʼ������������");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append("update po_invoice_b set vordertrantype = ");
		sql.append("(select pk_billtypeid from bd_billtype trantype ");
		sql.append("where trantype.pk_group = po_invoice_b.pk_group and po_invoice_b.vordertrantype = trantype.pk_billtypecode)");
		sql.append("where vordertrantype like '21%' or vordertrantype like '61%'");
		util.update(sql.toString());
		Logger.debug("�ɹ���Ʊ�������������ֶ���������������������");
	}

	/**
	 * ����po_initialest_b���ж������������ֶ� vordertrantype,�ɱ����Ϊ����
	 */

	public void upInitialestColumnData() {
		Logger.debug("�ڳ��ݹ����������������ֶ�������ʼ������������");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append("update po_initialest_b set po_initialest_b.vordertrantype = ");
		sql.append("(select trantype.pk_billtypeid from bd_billtype trantype ");
		sql.append(" where trantype.pk_group = po_initialest_b.pk_group and po_initialest_b.vordertrantype = trantype.pk_billtypecode)");
		sql.append(" where po_initialest_b.vordertrantype like '21%'");
		util.update(sql.toString());
		Logger.debug("�ڳ��ݹ����������������ֶ���������������������");
	}

	/**
	 * �ɹ����������㷽ʽ���빺�����빺����ϸĬ��Ϊ�ա��ջ��������� �����������������ֵ���ջ���������ûֵ���򽫽����������ĵ�ֵ�����ջ���������
	 * ����po_invoice�����¼��ֶ� finvoicetype�ֶ� Ϊ0
	 */
	public void upOrderInvoiceColumnData() {
		Logger.debug("�����ջ����������ֶ�������ʼ������������");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_order_b set pk_arrliabcenter = pk_apliabcenter, pk_arrliabcenter_v = pk_apliabcenter_v where pk_apliabcenter <> '~' and (pk_arrliabcenter is null  or pk_arrliabcenter = '~') ");
		util.update(sql.toString());
		Logger.debug("�����ջ���������������������������������");
		Logger.debug("�ɹ���Ʊ��Ʊ�����ֶ�������ʼ������������");
		sql.reset();
		sql.append(" update po_invoice set finvoicetype=0 where finvoicetype is null ");
		util.update(sql.toString());
		Logger.debug("�ɹ���Ʊ��Ʊ�����ֶ���������������������");
	}

	public void upSettleBillDate() {
		Logger.debug("�ɹ����㵥������ںͿ�Ʊ����������ʼ������������");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append(" UPDATE po_settlebill_b SET stockbilldate = ");
		sql.append(" ( SELECT  po_Settlebill.dbilldate FROM  po_Settlebill  ");
		sql.append(" WHERE po_Settlebill.pk_settlebill = po_settlebill_b.pk_settlebill ) ");
		sql.append(" WHERE EXISTS ");
		sql.append(" ( SELECT pk_settlebill FROM po_Settlebill  ");
		sql.append(" WHERE po_Settlebill.pk_settlebill = po_settlebill_b.pk_settlebill) ");
		util.update(sql.toString());
		sql.reset();
		sql.append(" UPDATE po_settlebill_b SET invoicebilldate = ");
		sql.append(" ( SELECT  po_Settlebill.dbilldate FROM  po_Settlebill  ");
		sql.append(" WHERE po_Settlebill.pk_settlebill = po_settlebill_b.pk_settlebill ) ");
		sql.append(" WHERE EXISTS ");
		sql.append(" ( SELECT pk_settlebill FROM po_Settlebill  ");
		sql.append(" WHERE po_Settlebill.pk_settlebill = po_settlebill_b.pk_settlebill) ");
		util.update(sql.toString());
		Logger.debug("�ɹ����㵥������ںͿ�Ʊ������������������������");
		Logger.debug("�ɹ������ʶ�ֶ�������ʼ������������");
		sql.reset();
		sql.append(" update po_settlebill set fsettletype=0 where fsettletype is null ");
		util.update(sql.toString());
		Logger.debug("�ɹ������ʶ�ֶ���������������������");
	}

	/**
	 * �����������ύ���㵥����SQL����ֹ���������쳣���������ݿ�ع�ѹ����
	 */
	public void updateSettleBill_ReqNew() {
		Method method = this.getUpdateSettleBillMethod();
		CMTProxySrv.delegate_RequiresNew(this, method, null);
	}

	/**
	 * �����������ύ�ɹ������Ͳɹ���Ʊ����SQL����ֹ���������쳣���������ݿ�ع�ѹ����
	 */
	public void updateOrderInvoice_ReqNew() {
		Method method = this.getUpdateOrderInvoiceMethod();
		CMTProxySrv.delegate_RequiresNew(this, method, null);
	}

	/**
	 * �����������ύ������������������ȥ�ɹ�����������������SQL
	 */
	public void updateArrliabcenter_ReqNew() {
		Method method = this.getUpdateArrliabcenterMethod();
		CMTProxySrv.delegate_RequiresNew(this, method, null);
	}

	private Method getUpdateSettleBillMethod() {
		Method method = null;
		try {
			method = this.getClass().getDeclaredMethod("upSettleBillDate");
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return method;
	}

	private Method getUpdateOrderInvoiceMethod() {
		Method method = null;
		try {
			method = this.getClass().getDeclaredMethod("upOrderInvoiceColumnData");
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return method;
	}

	private Method getUpdateArrliabcenterMethod() {
		Method method = null;
		try {
			method = this.getClass().getDeclaredMethod("upArrliabcenterColumnData");
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return method;
	}

	/**
	 * ����63�ɹ�����636��CBSȡֵ
	 */
	public void upCBS() {
		Logger.debug("�����������뵥CBS������ʼ������������");
		SqlBuilder sql = new SqlBuilder();
		sql.append("select pk_storereq_b from po_storereq_b where cprojecttaskid != null");
		IDDBPage ds = new IDDBPage(sql.toString());
		IPage<String> page = new SecondaryPage<String>(ds, 20000);
		String[] bfields = new String[] { StoreReqAppItemVO.CPROJECTTASKID,
				StoreReqAppItemVO.CBS };
		VOQryUtil<StoreReqAppItemVO> butil = new VOQryUtil<StoreReqAppItemVO>(
				StoreReqAppItemVO.class);
		while (page.hasNext()) {
			String[] ids = page.next();
			StoreReqAppItemVO[] vos = butil.qryByPKs(ids, bfields);
			HashSet<String> set = new HashSet<String>();
			for (StoreReqAppItemVO vo : vos) {
				String cprojecttaskid = vo.getCprojecttaskid();
				set.add(cprojecttaskid);
			}
			IWBSService service = NCLocator.getInstance().lookup(IWBSService.class);
			VOUpdate<StoreReqAppItemVO> update = new VOUpdate<StoreReqAppItemVO>();
			try {
				Map<String, String> cbsByIDs = service.queryCBSByIDs(set
						.toArray(new String[set.size()]));
				if (cbsByIDs == null || cbsByIDs.isEmpty()) {
					continue;
				}
				for (StoreReqAppItemVO item : vos) {
					item.setCbs(cbsByIDs.get(item.getCprojecttaskid()));
				}
				update.update(vos, new String[] { StoreReqAppItemVO.CPROJECTTASKID });
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
		Logger.debug("�����������뵥CBS��������������������");
	}

}

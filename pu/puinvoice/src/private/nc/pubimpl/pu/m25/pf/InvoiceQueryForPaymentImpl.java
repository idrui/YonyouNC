package nc.pubimpl.pu.m25.pf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.arap.util.BillTermUtils;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.arap.pub.IArapTermDateQueryService;
import nc.pubitf.pu.m25.ap.f1.IInvoiceQuery4F1;
import nc.vo.arap.termitem.ArapTermDateVO;
import nc.vo.arap.termitem.PaymentDateVO;
import nc.vo.arap.termitem.PaymentDateVO.AccountDate;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ��ѯ����Э������
 * 
 * @since 6.0
 * @version 2015-3-12 ����8:56:19
 * @author zhangshqb
 */
public class InvoiceQueryForPaymentImpl implements IArapTermDateQueryService {

	@Override
	public boolean isSupportBilltype(String billtype) {
		if ("25".equals(billtype) || "45".equals(billtype)) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, ArapTermDateVO[]> queryTermDataVOs(String billtype,
			Map<String, String> arg1) throws BusinessException {
		if (arg1 == null || arg1.isEmpty()) {
			return Collections.emptyMap();
		}
		if ("25".equals(billtype)) {
			return this.getPaymentVOsBy25(arg1);
		} else if ("45".equals(billtype)) {
			return this.getPaymentVOsBy45(arg1);
		}
		return Collections.emptyMap();
	}

	/**
	 * ����PaymentDateVO
	 * 
	 * @param chVOs
	 *          ����Э������VO����
	 * @param pk_invoice_b
	 *          �ɹ���Ʊ��ϸpk
	 * @return list<PaymentDateVO> PaymentDateVO ����
	 */
	private List<PaymentDateVO> createCollectionsOfPaymentDateVO(
			PaymentChVO[] chVOs, String pk_invoice_b, String billtype) {
		List<PaymentDateVO> list = new ArrayList<PaymentDateVO>();
		for (PaymentChVO chvo : chVOs) {
			String pk_payperiod = chvo.getPk_payperiod();
			PaymentDateVO dateVO = new PaymentDateVO();
			dateVO.setBilltype(billtype);
			dateVO.setItemid(pk_invoice_b);
			if (IPaymentUtil.PURCHASE_INVOICE_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.PURCHASE_INVOICE_DATE.getValue());
			}
			if (IPaymentUtil.PURCHASE_INVOICE_APPROVE_DATE.equals(pk_payperiod)) {
				dateVO
						.setDateType(AccountDate.PURCHASE_INVOICE_APPROVE_DATE.getValue());
			}
			if (IPaymentUtil.PURCHASE_CONTRACT_EFFECTIVE_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.PURCHASE_CONTRACT_EFFECTIVE_DATE
						.getValue());
			}
			if (IPaymentUtil.PURCHASE_ORDER_APPROVE_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.PURCHASE_ORDER_APPROVE_DATE.getValue());
			}
			if (IPaymentUtil.RECEIPT_APPROVE_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.RECEIPT_APPROVE_DATE.getValue());
			}
			if (IPaymentUtil.STORE_RECEIPT_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.STORE_RECEIPT_DATE.getValue());
			}
			if (IPaymentUtil.STORE_RECEIPT_SIGNATURE_DATE.equals(pk_payperiod)) {
				dateVO.setDateType(AccountDate.STORE_RECEIPT_SIGNATURE_DATE.getValue());
			}
			list.add(dateVO);
		}
		return list;
	}

	/**
	 * ���ε���Ϊ�ɹ���Ʊ�����жϲɹ���Ʊ�����е����Ƿ�Ϊ�ڳ��ݹ�������Ϊ�ڳ��ݹ�������
	 * ����ԭ���߼���ѯ�ڳ��ݹ����ĸ���Э�飬����Ϊ�ڳ��ݹ����������жϲɹ���Ʊ����Դ��
	 * ���Ƕ��������ǣ����ѯ�����زɹ������ĸ���Э��ҳǩ���ݣ��������ѯ�ɹ���Ʊ�ĸ� ��Э�鵵��������
	 * 
	 * @param arg
	 *          map<���ε�����ϸpk,����Э������pk>
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, ArapTermDateVO[]> getPaymentVOsBy25(
			Map<String, String> arg) throws BusinessException {

		VOQuery<InvoiceItemVO> voQuery = new VOQuery<InvoiceItemVO>(
				InvoiceItemVO.class);
		InvoiceItemVO[] itemVOs = voQuery
				.query(arg.keySet().toArray(new String[0]));
		Map<String, String> billFromPayterm = new HashMap<String, String>();
		Map<String, String> billfrom21 = new HashMap<String, String>();
		for (InvoiceItemVO item : itemVOs) {
			String pk_order_b = item.getPk_order_b();
			if ("4T".equals(item.getCsourcetypecode())) {// ��Դ������4T�ģ��ŵ�һ��Map��
				String payment = arg.get(item.getPk_invoice_b());
				if (billFromPayterm.containsKey(payment)) { // ���Ѕ������У����ڶ��pk_invoice_b��Ӧһ��payment,�ʴ˴����ʱ��Ҫ�ӡ�,���������
					String value = billFromPayterm.get(payment) + ","
							+ item.getPk_invoice_b();
					billFromPayterm.put(payment, value);
					continue;
				}
				billFromPayterm.put(payment, item.getPk_invoice_b());
				// billFromPayterm.put(pk_order_b, item.getPk_invoice_b());
			} else if (pk_order_b != null && !pk_order_b.isEmpty()) {// ��Դ�����Ƕ����ķŵ�һ��map��˴��ж��Ƿ���в������У�
				if (billfrom21.containsKey(pk_order_b)) { // ���Ѕ������У����ڶ��pk_invoice_b��Ӧһ��pk_order_b,�ʴ˴����ʱ��Ҫ�ӡ�,���������
					String value = billfrom21.get(pk_order_b) + ","
							+ item.getPk_invoice_b();
					billfrom21.put(pk_order_b, value);
					continue;
				}
				billfrom21.put(pk_order_b, item.getPk_invoice_b());
			} else {
				String payment = arg.get(item.getPk_invoice_b());
				if (billFromPayterm.containsKey(payment)) { // ���Ѕ������У����ڶ��pk_invoice_b��Ӧһ��payment,�ʴ˴����ʱ��Ҫ�ӡ�,���������
					String value = billFromPayterm.get(payment) + ","
							+ item.getPk_invoice_b();
					billFromPayterm.put(payment, value);
					continue;
				}
				billFromPayterm.put(payment, item.getPk_invoice_b());
				// billFromPayterm.put(arg.get(item.getPk_invoice_b()),
				// item.getPk_invoice_b());
			}
		}
		Map<String, ArapTermDateVO[]> result = new HashMap<String, ArapTermDateVO[]>();
		result.putAll(this.queryPayterm(billFromPayterm, "25"));
		result.putAll(this.queryBillPayment(billfrom21, "25"));
		return result;
	}

	/**
	 * ���ε���Ϊ�ɹ���ⵥ����ѯ�丶��Э������
	 * 
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, ArapTermDateVO[]> getPaymentVOsBy45(
			Map<String, String> arg1) throws BusinessException {
		VOQuery<PurchaseInBodyVO> voQuery = new VOQuery<PurchaseInBodyVO>(
				PurchaseInBodyVO.class);
		PurchaseInBodyVO[] bodyVOs = voQuery.query(arg1.keySet().toArray(
				new String[0]));
		Map<String, String> billfrom21 = new HashMap<String, String>();
		Map<String, String> billFromPayterm = new HashMap<String, String>();
		for (PurchaseInBodyVO bodyVO : bodyVOs) {
			String cgeneralbid = bodyVO.getCgeneralbid();
			if ("21".equals(bodyVO.getCfirsttype())) {
				String pk_order_b = bodyVO.getCfirstbillbid();
				if (billfrom21.containsKey(pk_order_b)) { // ���Ѕ������У����ڶ��cgeneralbid��Ӧһ��pk_order_b,�ʴ˴����ʱ��Ҫ�ӡ�,���������
					String value = billfrom21.get(pk_order_b) + "," + cgeneralbid;
					billfrom21.put(pk_order_b, value);
					continue;
				}
				billfrom21.put(pk_order_b, cgeneralbid);
				// billfrom21.put(pk_order_b, cgeneralbid);
			} else {
				String pk_payterm = arg1.get(cgeneralbid);
				if (billFromPayterm.containsKey(pk_payterm)) { // ���Ѕ������У����ڶ��pk_invoice_b��Ӧһ��pk_order_b,�ʴ˴����ʱ��Ҫ�ӡ�,���������
					String value = billFromPayterm.get(pk_payterm) + "," + cgeneralbid;
					billFromPayterm.put(pk_payterm, value);
					continue;
				}
				billFromPayterm.put(pk_payterm, cgeneralbid);
			}
		}
		Map<String, ArapTermDateVO[]> result = new HashMap<String, ArapTermDateVO[]>();
		result.putAll(this.queryPayterm(billFromPayterm, "45"));
		result.putAll(this.queryBillPayment(billfrom21, "45"));
		return result;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param vos
	 *          ����Э������VO����
	 * @param billmap
	 *          Map<����Э�鵵������,�ɹ���Ʊ��ϸpk>
	 * @return Map<pk_invoice_b + datetype,UFDouble>
	 * @throws BusinessException
	 */
	private Map<String, UFDate> queryBeginDate(Collection<SuperVO> vos,
			Map<String, String> billmap, String billtype) throws BusinessException {
		List<PaymentDateVO> list = new ArrayList<PaymentDateVO>();
		for (SuperVO vo : vos) {
			String pk_payterm = vo.getPrimaryKey();
			String bids = billmap.get(pk_payterm);
			String[] split = bids.split(",");
			if (vo instanceof PaymentVO) {
				for (String pk_invoice_b : split) {
					PaymentChVO[] chVOs = ((PaymentVO) vo).getPaymentch();
					// ArapTermDateVO[] atdVOs = new ArapTermDateVO[chVOs.length];
					list = this.createCollectionsOfPaymentDateVO(chVOs, pk_invoice_b,
							billtype);
				}
			}
		}
		Map<String, UFDate> map = NCLocator.getInstance()
				.lookup(IInvoiceQuery4F1.class).queryPaytermDate(list);
		return map;
	}

	/**
	 * ��ѯ�ɹ������ĸ���ƻ�����
	 * 
	 * @param billfrom21
	 *          map<pk_order_b,pk_inovice_b>
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, ArapTermDateVO[]> queryBillPayment(
			Map<String, String> billfrom21, String billtype) throws BusinessException {
		if (billfrom21 != null && !billfrom21.isEmpty()) {
			String[] ids = billfrom21.keySet().toArray(new String[0]);
			Map<String, ArapTermDateVO[]> result = new HashMap<String, ArapTermDateVO[]>();
			SqlBuilder sql = new SqlBuilder();
			sql.append("select b.pk_order_b,h.iaccounttermno,");
			sql.append("h.pk_paymentch,h.feffdatetype,h.iitermdays,");
			sql.append("h.denddate,h.bpreflag,");
			sql.append("h.isdeposit,h.nrate ");
			sql.append("from po_order_b b inner join ");
			sql.append("po_order_payplan h on b.pk_order = h.pk_order ");
			sql.append("and h.dr = 0 ");
			sql.append(new IDQueryBuilder()
					.buildAnotherSQL(" and b.pk_order_b ", ids));
			IRowSet rs = new DataAccessUtils().query(sql.toString());
			// ��Ÿ���Э�������Լ���Ӧ��pk_invoice_b
			Map<ArapTermDateVO, String> atdMap = new HashMap<ArapTermDateVO, String>();
			// ��Ÿ���Э���pk�Լ�������pk��Ӧ�ĸ���Э������VO�������ʱ������pk�������ȷ��Ψһ��ʶ��
			Map<String, ArapTermDateVO> ptMap = new HashMap<String, ArapTermDateVO>();
			// ��ŷ�Ʊ����pk��Ӧ�ĸ���Э������
			HashMap<String, List<ArapTermDateVO>> listMap = new HashMap<String, List<ArapTermDateVO>>();
			while (rs.next()) {
				String pk_order_b = rs.getString(0);
				PaymentChVO chVO = new PaymentChVO();
				chVO.setShoworder(rs.getInteger(1));// ���ں�
				chVO.setPk_paymentch(rs.getString(2));// ������������
				chVO.setPk_payperiod(rs.getString(3));// ��Ч����
				chVO.setPaymentday(rs.getInteger(4));// ��������
				UFDate denddate = rs.getUFDate(5);// ���ڵ�����
				chVO.setPrepayment(rs.getUFBoolean(6));// Ԥ����
				chVO.setIsdeposit(rs.getUFBoolean(7));// �ʱ���
				chVO.setAccrate(rs.getUFDouble(8));// ����
				ArapTermDateVO atdVO = new ArapTermDateVO();
				atdVO.setIncome(false);
				atdVO.setPaymentvo(chVO);
				atdVO.setExpiredate(denddate);// ���ڵ�����
				String pk_invoice_b = billfrom21.get(pk_order_b);
				atdMap.put(atdVO, pk_invoice_b);
				if (denddate == null) {
					if (chVO.getPk_paymentch() != null) {
						ptMap.put(chVO.getPk_paymentch() + "," + pk_order_b, atdVO);
					} else {
						List<ArapTermDateVO> list = listMap.get(pk_invoice_b);
						if (list == null) {
							list = new ArrayList<ArapTermDateVO>();
							listMap.put(pk_invoice_b, list);
						}
						list.add(atdVO);
					}
				} else {
					List<ArapTermDateVO> list = listMap.get(pk_invoice_b);
					if (list == null) {
						list = new ArrayList<ArapTermDateVO>();
						listMap.put(pk_invoice_b, list);
					}
					list.add(atdVO);
				}
			}
			// ���ڸ���ƻ���ȱ��һЩ�ֶε�ֵ�����ö�Ӧ�ĸ���Э���ֵ��ȫ
			VOQuery<OrderPaymentVO> voQuery = new VOQuery<OrderPaymentVO>(
					OrderPaymentVO.class);
			if (!ptMap.isEmpty()) {
				Set<String> set = new HashSet<String>();
				for (String key : ptMap.keySet()) {
					String[] split = key.split(",");
					set.add(split[0]);
				}
				OrderPaymentVO[] vos = voQuery.query(set.toArray(new String[0]));
				for (Entry<String, String> entry : billfrom21.entrySet()) {
					for (OrderPaymentVO vo : vos) {
						String pk_payment = vo.getPk_payment();
						ArapTermDateVO arapTermDateVO = ptMap.get(pk_payment + ","
								+ entry.getKey());
						if(arapTermDateVO == null) {
							continue;
						}
						String pk_invoice_b = atdMap.get(arapTermDateVO);
						PaymentChVO paymentvo = arapTermDateVO.getPaymentvo();
						paymentvo.setCheckdata(vo.getCheckdata());
						paymentvo.setAccountday(vo.getAccountday());
						paymentvo.setEffectaddmonth(vo.getEffectaddmonth());
						paymentvo.setEffectdateadddate(vo.getEffectdateadddate());
						paymentvo.setEffectmonth(String.valueOf(vo.getEffectmonth()));
						if (pk_invoice_b.contains(",")) {
							String[] split = pk_invoice_b.split(",");
							for (String invoice_b : split) {
								List<ArapTermDateVO> list = listMap.get(invoice_b);
								if (list == null) {
									list = new ArrayList<ArapTermDateVO>();
									listMap.put(invoice_b, list);
								}
								list.add(arapTermDateVO);
							}
						} else {
							List<ArapTermDateVO> list = listMap.get(pk_invoice_b);
							if (list == null) {
								list = new ArrayList<ArapTermDateVO>();
								listMap.put(pk_invoice_b, list);
							}
							list.add(arapTermDateVO);
						}
					}
				}
			}
			// ��ȡ�������ڵ����յĲ���
			List<PaymentDateVO> pdvoList = new ArrayList<PaymentDateVO>();
			for (Entry<String, List<ArapTermDateVO>> entry : listMap.entrySet()) {
				List<ArapTermDateVO> atdVos = entry.getValue();
				ArrayList<PaymentChVO> list = new ArrayList<PaymentChVO>();
				for (ArapTermDateVO vo : atdVos) {
					PaymentChVO chVO = vo.getPaymentvo();
					list.add(chVO);
				}
				List<PaymentDateVO> paymentDateVOs = this
						.createCollectionsOfPaymentDateVO(list.toArray(new PaymentChVO[0]),
								entry.getKey(), billtype);
				for (PaymentDateVO pdvo : paymentDateVOs) {
					if (pdvo.getDateType() != 0) {
						pdvoList.add(pdvo);
					}
				}
			}
			Map<String, UFDate> map1 = NCLocator.getInstance()
					.lookup(IInvoiceQuery4F1.class).queryPaytermDate(pdvoList);
			for (Entry<String, List<ArapTermDateVO>> entry : listMap.entrySet()) {
				String pk_invoice_b = entry.getKey();
				List<ArapTermDateVO> list = entry.getValue();
				for (ArapTermDateVO atdVO : list) {
					UFDate ufDate = map1.get(pk_invoice_b
							+ atdVO.getPaymentvo().getPk_payperiod());
					if (ufDate == null) {
						ufDate = AppContext.getInstance().getBusiDate();
					}
					atdVO.setPaydate(ufDate);
					if (atdVO.getExpiredate() != null) {
						continue;
					}
					// �������ڵ�����
					UFDate date = BillTermUtils.getExpiredateByTermDateVO(atdVO);
					atdVO.setExpiredate(date);
				}
				ArapTermDateVO[] dateVOs = list.toArray(new ArapTermDateVO[0]);
				if (pk_invoice_b.contains(",")) {
					String[] split = pk_invoice_b.split(",");
					for (String invoice_b : split) {
						result.put(invoice_b, dateVOs);
					}
				} else {
					result.put(pk_invoice_b, dateVOs);
				}
			}
			return result;
		}
		return Collections.emptyMap();
	}

	/**
	 * ��ѯ����Э�鵵������
	 * 
	 * @param billFromPayterm
	 *          Map<pk_payterm,pk_invoice_b>
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, ArapTermDateVO[]> queryPayterm(
			Map<String, String> billFromPayterm, String billtype)
			throws BusinessException {
		if (billFromPayterm == null || billFromPayterm.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, ArapTermDateVO[]> result = new HashMap<String, ArapTermDateVO[]>();
		IMDPersistenceQueryService service = MDPersistenceService
				.lookupPersistenceQueryService();
		@SuppressWarnings("unchecked")
		Collection<SuperVO> vos = service.queryBillOfVOByPKs(PaymentVO.class,
				billFromPayterm.keySet().toArray(new String[0]), false);
		if (vos == null || vos.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, UFDate> beginDate = this.queryBeginDate(vos, billFromPayterm,
				billtype);
		for (SuperVO vo : vos) {
			String pk_payterm = vo.getPrimaryKey();
			String pk_invoice_b = billFromPayterm.get(pk_payterm);
			if (vo instanceof PaymentVO) {
				PaymentChVO[] chVOs = ((PaymentVO) vo).getPaymentch();
				List<ArapTermDateVO> list = new ArrayList<ArapTermDateVO>();
				for (int i = 0; i < chVOs.length; i++) {
					ArapTermDateVO dateVO = new ArapTermDateVO();
					UFDate paydate = beginDate.get(pk_invoice_b
							+ chVOs[i].getPk_payperiod());
					dateVO.setIncome(false);
					dateVO.setPaymentvo(chVOs[i]);
					if(paydate == null){
						paydate = AppContext.getInstance().getBusiDate();
					}
					dateVO.setPaydate(paydate);
					UFDate date = BillTermUtils.getExpiredateByTermDateVO(dateVO);
					dateVO.setExpiredate(date);
					list.add(dateVO);
				}
				result.put(pk_invoice_b, list.toArray(new ArapTermDateVO[0]));
			}
		}
		Map<String,ArapTermDateVO[]> map = new HashMap<String, ArapTermDateVO[]>();
		for (Entry<String, ArapTermDateVO[]> entry : result.entrySet()) {
			String keys = entry.getKey();
			String[] split = keys.split(",");
			for (String pk_invoice_b : split) {
				map.put(pk_invoice_b, entry.getValue());
			}
		}
		return map;
	}
}

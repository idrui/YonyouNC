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
 * 升级处理具体实现类 注：与版本相关的升级处理方法命名中添加来源版本号
 * 
 * @since 6.36
 * @version 2015-3-30 下午9:04:39
 * @author mengjian
 */
public class PUUpgradeTOV636Util {

	/**
	 * vo升级后对之前的违规主键处理（之前版本中存在非Z8的预置主键） delete from pub_vochange_b where
	 * pk_vochange_b ='1001AA10000000000KSY' and pk_vochange
	 * ='1002Z8100000000046GP' delete from pub_vochange_b where pk_vochange_b like
	 * '1001ZZ1000000000A7%' and pk_vochange ='1001Z810000000008W8A' delete from
	 * pub_vochange_b where pk_vochange_b like '1001ZZ1000000000A7%' and
	 * pk_vochange ='1001Z810000000008W8M' delete from pub_vochange_b where
	 * pk_vochange_b= '1001ZP1000000006A4MQ' and pk_vochange
	 * ='1001Z81000000004ENF8'
	 */
	public void deleteVOChange_bDate() {
		Logger.debug("vo升级后对之前的违规主键处理升级开始。。。。。。");
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
		Logger.debug("vo升级后对之前的违规主键处理升级结束。。。。。。");
	}

	/**
	 * 到货单：产品类型：默认为主产品。 如果结算利润中心有值，收货利润中心没值，则将结算利润中心的值赋给收货利润中心
	 */
	public void upArrivalColumnData() {
		Logger.debug("到货单产品类型字段升级开始。。。。。。");
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_arriveorder_b set fproductclass=1 where fproductclass is null ");
		DataAccessUtils util = new DataAccessUtils();
		util.update(sql.toString());
		Logger.debug("到货单产品类型字段升级结束。。。。。。");
		Logger.debug("订单收货利润中心字段升级开始。。。。。。");
		SqlBuilder sql2 = new SqlBuilder();
		sql2.append(" update po_arriveorder_b set pk_arrliabcenter = pk_apliabcenter, pk_arrliabcenter_v = pk_apliabcenter_v where pk_apliabcenter <> '~' and (pk_arrliabcenter is null or pk_arrliabcenter = '~') ");
		util.update(sql2.toString());
		Logger.debug("订单收货利润中心升级升级结束。。。。。。");

	}

	/**
	 * 委托加工入财务副本：产品类型：默认为主产品。
	 */
	public void upSubcontinfiColumnData() {
		Logger.debug("委托加工入财务副本产品类型字段升级开始。。。。。。");
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_subcontinfi_b set foutputtype = 1 where foutputtype is null ");
		DataAccessUtils util = new DataAccessUtils();
		util.update(sql.toString());
		Logger.debug("委托加工入财务副本产品类型字段升级结束。。。。。。");
	}

	/**
	 * 到货单结算利润中心升级取订单结算利润中心
	 */
	public void upArrliabcenterColumnData() {
		Logger.debug("到货单结算利润中心升级取订单结算利润中心升级开始。。。。。。");
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
		Logger.debug("到货单结算利润中心升级取订单结算利润中心升级结束。。。。。。");
	}

	/**
	 * 采购订单---付款协议升级处理
	 */
	@SuppressWarnings({ "unchecked" })
	public void upgradeForPayment() {
		Logger.debug("采购订单付款协议升级开始。。。。。。");
		Logger.debug("---查询所有表头包含付款协议内容的采购订单开始---");
		SqlBuilder sql = new SqlBuilder();
		sql.append("select p.pk_payterm,p.pk_order from po_order p where p.pk_payterm != '~' and p.dr = 0");
		DataAccessUtils util = new DataAccessUtils();
		IRowSet query = util.query("select pk_payment from po_order_payment");
		if (query != null && query.next()) {
			Logger.debug("---付款协议相关内容已经升级过，不再升级---");
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
			Logger.debug("---查询所有表头包含付款协议内容的采购订单结束---");
			Logger.debug("---查询付款协议档案内容开始---");
			IMDPersistenceQueryService service = MDPersistenceService
					.lookupPersistenceQueryService();
			Collection<SuperVO> vos = null;
			try {
				vos = service.queryBillOfVOByPKs(PaymentVO.class,
						map.keySet().toArray(new String[0]), false);
			} catch (MetaDataException e) {
				ExceptionUtils.wrappException(e);
			}
			Logger.debug("---查询付款协议档案内容结束---");
			if (vos != null && !vos.isEmpty()) {
				Logger.debug("---更新所有表头包含付款协议内容的采购订单表体付款协议页签开始---");
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
				Logger.debug("---更新所有表头包含付款协议内容的采购订单表体付款协议页签结束---");
				Logger.debug("---更新付款计划开始---");
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
				Logger.debug("---更新付款计划结束---");
			} else {
				Logger.debug("查询到的付款协议档案内容为空,采购订单付款协议升级结束。。。。。。");
			}
		} else {
			Logger.debug("查询到的满足条件的采购订单数为零，付款协议升级结束。。。。。。");
		}
		Logger.debug("采购订单付款协议升级结束。。。。。。");
	}

	/**
	 * 请购单---请购安排升级处理 请购单交易类型属性“需要请购安排”，默认为“否”。 请购单单据上已安排字段默认为“否”。
	 */
	public void upgradeForPrayarrange() {
		Logger.debug("请购单交易类型属性升级开始。。。。。。");
		PrayarrangeUpgrate.upgrate();
		Logger.debug("请购单交易类型属性升级结束。。。。。。");
		Logger.debug("请购单表体已安排字段升级开始。。。。。。");
		DataAccessUtils utils = new DataAccessUtils();
		SqlBuilder update = new SqlBuilder();
		update
				.append("update po_praybill_b set bisarrange = 'N' where bisarrange is null");
		utils.update(update.toString());
		Logger.debug("请购单交易类型已安排字段升级结束。。。。。。");
	}

	/**
	 * 采购管理v63->v63EHP升级处理类 需求类型freqtypeflag：默认设置成毛需求0；
	 * 物资需求申请类型vtrantypecode：如果是422X默认设置成普通物资需求申请422X-01； 已平衡bendgather：N
	 * 原始需求库存组织、下次平衡库存组织，默认设置成主组织。
	 */
	public void upgradeStorereqDefaultValue() {
		Logger.debug("物资需求申请单字段升级开始。。。。。。");
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
		Logger.debug("物资需求申请单字段升级结束。。。。。。");
		Logger.debug("物资需求申请单字段交易类型升级开始。。。。。。");
		StoreReqTrantypeUpgrate.upgrate(parallel_storereq);
		Logger.debug("物资需求申请单字段交易类型升级结束。。。。。。");

	}

	/**
	 * 更新po_invoice_b表中订单交易类型字段 vordertrantype,由编码变为主键
	 */

	public void upInvoiceColumnData() {
		Logger.debug("采购发票订单交易类型字段升级开始。。。。。。");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append("update po_invoice_b set vordertrantype = ");
		sql.append("(select pk_billtypeid from bd_billtype trantype ");
		sql.append("where trantype.pk_group = po_invoice_b.pk_group and po_invoice_b.vordertrantype = trantype.pk_billtypecode)");
		sql.append("where vordertrantype like '21%' or vordertrantype like '61%'");
		util.update(sql.toString());
		Logger.debug("采购发票订单交易类型字段升级结束。。。。。。");
	}

	/**
	 * 更新po_initialest_b表中订单交易类型字段 vordertrantype,由编码变为主键
	 */

	public void upInitialestColumnData() {
		Logger.debug("期初暂估单订单交易类型字段升级开始。。。。。。");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append("update po_initialest_b set po_initialest_b.vordertrantype = ");
		sql.append("(select trantype.pk_billtypeid from bd_billtype trantype ");
		sql.append(" where trantype.pk_group = po_initialest_b.pk_group and po_initialest_b.vordertrantype = trantype.pk_billtypecode)");
		sql.append(" where po_initialest_b.vordertrantype like '21%'");
		util.update(sql.toString());
		Logger.debug("期初暂估单订单交易类型字段升级结束。。。。。。");
	}

	/**
	 * 采购订单：结算方式、请购单、请购单明细默认为空、收货利润中心 如果结算利润中心有值，收货利润中心没值，则将结算利润中心的值赋给收货利润中心
	 * 更新po_invoice表中新加字段 finvoicetype字段 为0
	 */
	public void upOrderInvoiceColumnData() {
		Logger.debug("订单收货利润中心字段升级开始。。。。。。");
		DataAccessUtils util = new DataAccessUtils();
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update po_order_b set pk_arrliabcenter = pk_apliabcenter, pk_arrliabcenter_v = pk_apliabcenter_v where pk_apliabcenter <> '~' and (pk_arrliabcenter is null  or pk_arrliabcenter = '~') ");
		util.update(sql.toString());
		Logger.debug("订单收货利润中心升级升级结束。。。。。。");
		Logger.debug("采购发票发票归属字段升级开始。。。。。。");
		sql.reset();
		sql.append(" update po_invoice set finvoicetype=0 where finvoicetype is null ");
		util.update(sql.toString());
		Logger.debug("采购发票发票归属字段升级结束。。。。。。");
	}

	public void upSettleBillDate() {
		Logger.debug("采购结算单入库日期和开票日期升级开始。。。。。。");
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
		Logger.debug("采购结算单入库日期和开票日期升级结束。。。。。。");
		Logger.debug("采购结算标识字段升级开始。。。。。。");
		sql.reset();
		sql.append(" update po_settlebill set fsettletype=0 where fsettletype is null ");
		util.update(sql.toString());
		Logger.debug("采购结算标识字段升级结束。。。。。。");
	}

	/**
	 * 启独立事物提交结算单升级SQL，防止后续爆发异常到至少数据库回滚压力大！
	 */
	public void updateSettleBill_ReqNew() {
		Method method = this.getUpdateSettleBillMethod();
		CMTProxySrv.delegate_RequiresNew(this, method, null);
	}

	/**
	 * 启独立事物提交采购订单和采购发票升级SQL，防止后续爆发异常到至少数据库回滚压力大！
	 */
	public void updateOrderInvoice_ReqNew() {
		Method method = this.getUpdateOrderInvoiceMethod();
		CMTProxySrv.delegate_RequiresNew(this, method, null);
	}

	/**
	 * 启独立事务提交到货单结算利润中心去采购订单结算利润中心SQL
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
	 * 升级63采购管理到636，CBS取值
	 */
	public void upCBS() {
		Logger.debug("物资需求申请单CBS升级开始。。。。。。");
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
		Logger.debug("物资需求申请单CBS升级结束。。。。。。");
	}

}

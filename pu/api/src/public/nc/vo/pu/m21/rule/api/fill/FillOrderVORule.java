package nc.vo.pu.m21.rule.api.fill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.ftpub.reference.uap.bd.DeptPubUtilService;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.ic.material.query.InvInfoQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description 补充单据表头和表体的组织、发货国等以及联动计算。
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:05:04
 * @author zhangshqb
 */
public class FillOrderVORule implements IBillValueFill {

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		OrderVO[] vos = (OrderVO[]) billVOs;
		ArrayList<OrderHeaderVO> headers = new ArrayList<OrderHeaderVO>();
		ArrayList<OrderItemVO[]> items = new ArrayList<OrderItemVO[]>();
		HashSet<String> suppliers = new HashSet<String>();
		HashSet<String> orgs = new HashSet<String>();
		for (OrderVO vo : vos) {
			OrderHeaderVO header = vo.getHVO();
			headers.add(header);
		}
		this.fillHeadvoInfo(headers);
		for (OrderVO vo : vos) {
			OrderHeaderVO header = vo.getHVO();
			suppliers.add(header.getPk_supplier());
			items.add(vo.getBVO());
			orgs.add(header.getPk_org());
		}
		InvInfoQuery query = new InvInfoQuery();
		HashSet<String> material = new HashSet<String>();
		HashSet<String> apforg_v = new HashSet<String>();
		HashSet<String> financeorg_v = new HashSet<String>();
		HashSet<String> reqstoorg_v = new HashSet<String>();
		for (OrderItemVO[] itemVOs : items) {
			for (OrderItemVO vo : itemVOs) {
				String pk_material = vo.getPk_material();
				String pk_apfinanceorg_v = vo.getPk_apfinanceorg_v();
				String pk_psfinanceorg_v = vo.getPk_psfinanceorg_v();
				String pk_reqstoorg_v = vo.getPk_reqstoorg_v();
				reqstoorg_v.add(pk_reqstoorg_v);
				financeorg_v.add(pk_psfinanceorg_v);
				material.add(pk_material);
				apforg_v.add(pk_apfinanceorg_v);
			}
		}
		// 查询发货国
		Map<String, String> countrys = SupplierPubService
				.queryCountryBySupplier(suppliers.toArray(new String[0]));
		// 查询物料基本信息
		Map<String, InvBasVO> basVOs = query.getInvBasVOs(material
				.toArray(new String[0]));
		// 查询应付组织的oid
		Map<String, String> orgVids_v = OrgUnitPubService.getOrgIDSByVIDS(apforg_v
				.toArray(new String[0]));
		Map<String, String> reqstoorg = OrgUnitPubService
				.getOrgIDSByVIDS(reqstoorg_v.toArray(new String[0]));
		Map<String, String> financeorg = OrgUnitPubService
				.getOrgIDSByVIDS(financeorg_v.toArray(new String[0]));
		// Map<财务组织,本位币>
		Map<String, String> orgv_currs = new HashMap<String, String>();
		for (String org_v : financeorg_v) {
			String curr = CurrencyRateUtilHelper.getInstance()
					.getLocalCurrtypeByOrgID(financeorg.get(org_v));
			orgv_currs.put(org_v, curr);
		}
		Map<String, String> countryByStockOrg = StockOrgPubService
				.queryCountryByStockOrg(reqstoorg.values().toArray(new String[0]));
		Map<String, String> countryByFinanceOrg = FinanceOrgPubService
				.queryCountryByFinanceOrg(financeorg.values().toArray(new String[0]));
		for (OrderVO vo : vos) {
			OrderHeaderVO header = vo.getHVO();
			String pk_supplier = header.getPk_supplier();
			String org = header.getPk_org();
			Integer fhtaxtypeflag = header.getFhtaxtypeflag();
			for (OrderItemVO item : (OrderItemVO[]) vo.getChildren(OrderItemVO.class)) {
				item.setStatus(VOStatus.NEW);
				item.setPk_org(org);
				item.setPk_org_v(header.getPk_org_v());
				item.setFtaxtypeflag(fhtaxtypeflag);
				item.setAttributeValue(OrderHeaderVO.CORIGCURRENCYID,
						header.getCorigcurrencyid());
				if (item.getBreceiveplan() != null
						&& item.getBreceiveplan().booleanValue()) {
				} else {
					item.setPk_arrvstoorg_v(item.getPk_reqstoorg_v());
					item.setPk_arrvstoorg(reqstoorg.get(item.getPk_reqstoorg_v()));
				}
				if (item.getPk_psfinanceorg() == null) {
					item.setPk_psfinanceorg(financeorg.get(item.getPk_psfinanceorg_v()));
				}
				if (item.getPk_reqstoorg() == null) {
					item.setPk_reqstoorg(reqstoorg.get(item.getPk_reqstoorg_v()));
				}
				if (item.getPk_srcmaterial() == null) {
					item.setPk_srcmaterial(basVOs.get(item.getPk_material())
							.getPk_source());
				}
				if (item.getPk_apfinanceorg() == null) {
					item.setPk_apfinanceorg(orgVids_v.get(item.getPk_apfinanceorg_v()));
				}
				if (item.getCunitid() == null) {
					item.setCunitid(basVOs.get(item.getPk_material()).getPk_measdoc());
				}
				if (item.getCsendcountryid() == null) {
					item.setCsendcountryid(countrys.get(pk_supplier));
				}
				if (item.getCrececountryid() == null) {
					item.setCrececountryid(countryByStockOrg.get(item.getPk_reqstoorg()));
				}
				if (item.getCtaxcountryid() == null) {
					item.setCtaxcountryid(countryByFinanceOrg.get(item
							.getPk_psfinanceorg()));
				}
				if (item.getCqtunitid() == null) {
					item.setCqtunitid(item.getCunitid());
				}
				if (item.getNqtunitnum() == null) {
					item.setNqtunitnum(item.getNastnum());
				}
				if (item.getVqtunitrate() == null) {
					item.setVqtunitrate(item.getVchangerate());
				}
				if (item.getCcurrencyid() == null) {
					item.setCcurrencyid(orgv_currs.get(item.getPk_psfinanceorg_v()));
				}
				if (item.getNitemdiscountrate() == null) {
					item.setNitemdiscountrate(new UFDouble(100));
				}
				if (item.getFbuysellflag() == null) {
					if (item.getCsendcountryid().equals(item.getCtaxcountryid())) {
						item.setFbuysellflag(nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum.NATIONAL_BUY
								.value());
					} else {
						item.setFbuysellflag(nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum.IMPORT
								.value());
					}
				}
				if (item.getNexchangerate() == null) {
					String corigcurrencyid = item.getCorigcurrencyid();
					String ccurrencyid = item.getCorigcurrencyid();
					UFDouble rate = CurrencyRate.getCurrencySellRateByOrg(
							item.getPk_org(), corigcurrencyid, ccurrencyid,
							header.getDbilldate());
					item.setNexchangerate(rate);
				}
			}
			UFDouble nhtaxrate = vo.getHVO().getNhtaxrate();
			if (nhtaxrate != null && nhtaxrate.compareTo(UFDouble.ZERO_DBL) > 0) {
				OrderItemVO[] bvos = vo.getBVO();
				for (OrderItemVO item : bvos) {
					item.setNtaxrate(nhtaxrate);
				}
			}
		}
		return vos;
	}

	/**
	 * 补充表头信息
	 * 
	 * @param headvos
	 */
	private void fillHeadvoInfo(List<OrderHeaderVO> headvos) {
		Set<String> pk_orgs = new HashSet<String>();
		Set<String> pk_depts_v = new HashSet<String>();
		for (OrderHeaderVO vo : headvos) {
			pk_orgs.add(vo.getPk_org());
			pk_depts_v.add(vo.getPk_dept_v());
		}
		// 查询部门信息
		Map<String, String> deptIDS_V = DeptPubUtilService
				.getDeptOidByVid(pk_depts_v.toArray(new String[0]));
		for (OrderHeaderVO headvo : headvos) {
			// 设置单据状态为新增
			headvo.setStatus(VOStatus.NEW);
			if (headvo.getPk_dept() == null) {
				headvo.setPk_dept(deptIDS_V.get(headvo.getPk_dept_v()));
			}
			if (headvo.getPk_invcsupllier() == null) {
				headvo.setPk_invcsupllier(headvo.getPk_supplier());
			}
		}
	}
}

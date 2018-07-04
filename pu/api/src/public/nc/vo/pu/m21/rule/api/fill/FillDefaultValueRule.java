package nc.vo.pu.m21.rule.api.fill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.ftpub.reference.uap.bd.DeptPubUtilService;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.ic.material.query.InvInfoQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.SupplierDefaultInfo;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.m21.rule.api.Calculate;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.SupplierInfoUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description 填充默认值
 * 
 * @scene 采购订单API保存
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-30 上午10:09:50
 * @author zhangshqb
 */
public class FillDefaultValueRule implements IBillValueFill {

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		OrderVO[] vos = (OrderVO[]) billVOs;
		Set<String> materials = new HashSet<String>();
		Set<String> pk_depts_v = new HashSet<String>();
		Map<String, List<OrderVO>> map = new HashMap<String, List<OrderVO>>();
		// 设置供应商相关信息
		this.fillSupplierInfo(vos);

		Set<String> orgs = new HashSet<String>();

		for (OrderVO vo : vos) {
			OrderHeaderVO hvo = vo.getHVO();
			hvo.setStatus(VOStatus.NEW);
			String pk_dept_v = hvo.getPk_dept_v();
			if (pk_dept_v != null) {
				pk_depts_v.add(pk_dept_v);
			}
			String vtrantypecode = hvo.getVtrantypecode();
			if (vtrantypecode != null) {
				List<OrderVO> list = map.get(vtrantypecode);
				if (list == null) {
					list = new ArrayList<OrderVO>();
					map.put(vtrantypecode, list);
				}
				list.add(vo);
			}
			Integer fhtaxtypeflag = hvo.getFhtaxtypeflag();
			if (fhtaxtypeflag == null) {
				// 默认整单扣税类别为应税外加
				hvo.setFhtaxtypeflag(EnumDiscounttaxtype.TAXOUT.toInteger());
			}
			IKeyValue helper = new BillHelper<OrderVO>(vo);
			String pk_org = hvo.getPk_org();
			// 设置默认的采购员以及采购部门
			new SetPeptRule(helper, OrderHeaderVO.CEMPLOYEEID, OrderHeaderVO.PK_DEPT,
					OrderHeaderVO.PK_DEPT_V, AppContext.getInstance().getPkUser(), pk_org)
					.setPsnAndDept();
			UFDate dbilldate = hvo.getDbilldate();
			if (dbilldate == null) {
				hvo.setDbilldate(AppContext.getInstance().getBusiDate());
			}
			// 补充表体组织以及利润中心信息
			OrganizationDefaultValue odv = new OrganizationDefaultValue(helper);
			odv.setClear(Boolean.FALSE);
			OrderItemVO[] bvo = vo.getBVO();
			int[] rows = new int[bvo.length];
			for (int i = 0; i < rows.length; i++) {
				rows[i] = i;
			}
			odv.setDefaultOrganizationValue(rows);

			for (OrderItemVO item : bvo) {
				item.setStatus(VOStatus.NEW);
				String pk_material = item.getPk_material();
				materials.add(pk_material);
				String cqtunitid = item.getCqtunitid();
				if (cqtunitid == null) {
					item.setCqtunitid(item.getCastunitid());
				}
				String vqtunitrate = item.getVqtunitrate();
				if (vqtunitrate == null) {
					item.setVqtunitrate(item.getVchangerate());
				}
				UFDouble nqtunitnum = item.getNqtunitnum();
				if (nqtunitnum == null) {
					item.setNqtunitnum(item.getNastnum());
				}
				String pk_apfinanceorg = item.getPk_apfinanceorg();
				String pk_apfinanceorg_v = item.getPk_apfinanceorg_v();
				if (pk_apfinanceorg == null && pk_apfinanceorg_v != null) {
					orgs.add(pk_apfinanceorg_v);
				}
				String pk_arrvstoorg_v = item.getPk_arrvstoorg_v();
				String pk_arrvstoorg = item.getPk_arrvstoorg();
				if (pk_arrvstoorg == null && pk_arrvstoorg_v != null) {
					orgs.add(pk_arrvstoorg_v);
				}
				String pk_psfinanceorg = item.getPk_psfinanceorg();
				String pk_psfinanceorg_v = item.getPk_psfinanceorg_v();
				if (pk_psfinanceorg == null && pk_psfinanceorg_v != null) {
					orgs.add(pk_psfinanceorg_v);
				}
			}

			// 发货地点以及发货地区
			SupplierDefaultInfo supRule = new SupplierDefaultInfo(helper, rows);
			supRule.prepare();
		}
		// 若用户手动输入了组织的oid没有输入vid，前面组织询源时不会讲vid补全，此处重新补充组织的vid
		this.setOrgOidByVid(vos, orgs);
		Map<String, String> deptIDS_V = DeptPubUtilService
				.getDeptOidByVid(pk_depts_v.toArray(new String[0]));
		// 设置物料版本信息
		this.setMaterialInfo(vos, materials);
		// 设置本位币
		this.setCurrInfo(vos);
		// 设置VAT信息
		this.setVatInfo(vos);
		// 处理到货计划
		this.processReceivePlan(map);
		// 设置部门信息
		if (deptIDS_V != null) {
			for (OrderVO vo : vos) {
				OrderHeaderVO hvo = vo.getHVO();
				if (hvo.getPk_dept() == null) {
					hvo.setPk_dept(deptIDS_V.get(hvo.getPk_dept_v()));
				}
			}
		}
		// 进行联动计算
		this.calculate(vos);
		return vos;
	}

	/**
	 * 根据组织的oid补充vid <br/>
	 * 
	 * @author zhangshqb
	 * @param vos
	 * @param orgs_v
	 */
	private void setOrgOidByVid(OrderVO[] vos, Set<String> orgs_v) {
		if (orgs_v == null || orgs_v.isEmpty()) {
			return;
		}
		Map<String, String> orgVids_v = OrgUnitPubService.getOrgIDSByVIDS(orgs_v
				.toArray(new String[orgs_v.size()]));
		for (OrderVO vo : vos) {
			for (OrderItemVO item : vo.getBVO()) {
				if (item.getPk_apfinanceorg() == null) {
					item.setPk_apfinanceorg(orgVids_v.get(item.getPk_apfinanceorg_v()));
				}
				if (item.getPk_arrvstoorg() == null) {
					item.setPk_arrvstoorg(orgVids_v.get(item.getPk_arrvstoorg_v()));
				}
				if (item.getPk_psfinanceorg() == null) {
					item.setPk_psfinanceorg(orgVids_v.get(item.getPk_psfinanceorg_v()));
				}
			}
		}
	}

	/**
	 * 进行联动计算 <br/>
	 * 
	 * @author zhangshqb
	 * @param vos
	 */
	private void calculate(OrderVO[] vos) {
		for (OrderVO vo : vos) {
			OrderHeaderVO hvo = vo.getHVO();
			String pk_org = hvo.getPk_org();
			boolean taxPricePriorToPrice = isTaxPricePriorToPrice(pk_org);
			Calculate calculate = new Calculate();
			if (taxPricePriorToPrice) {
				calculate.calculate(new AggVOHelper<OrderVO>(vo), vo,
						taxPricePriorToPrice);
			} else {
				calculate.calculate(new AggVOHelper<OrderVO>(vo), vo,
						taxPricePriorToPrice);
			}
		}
	}

	/**
	 * 设置物料相关信息 <br/>
	 * 
	 * @author zhangshqb
	 * @param vos
	 * @param materials
	 *          物料集合
	 */
	private void setMaterialInfo(OrderVO[] vos, Set<String> materials) {
		InvInfoQuery query = new InvInfoQuery();
		Map<String, InvBasVO> basVOs = query.getInvBasVOs(materials
				.toArray(new String[0]));
		if (basVOs == null) {
			return;
		}

		for (OrderVO vo : vos) {
			for (OrderItemVO item : vo.getBVO()) {
				if (item.getPk_srcmaterial() == null) {
					item.setPk_srcmaterial(basVOs.get(item.getPk_material())
							.getPk_source());
				}
			}
		}

	}

	/**
	 * 设置本币币种 <br/>
	 * 
	 * @author zhangshqb
	 * @param vos
	 */
	private void setCurrInfo(OrderVO[] vos) {
		Map<String, List<OrderVO>> map = new HashMap<String, List<OrderVO>>();
		for (OrderVO vo : vos) {
			String pk_group = vo.getHVO().getPk_group();
			for (OrderItemVO item : vo.getBVO()) {
				item.setPk_group(pk_group);
				String pk_psfinanceorg = item.getPk_psfinanceorg();
				List<OrderVO> list = map.get(pk_psfinanceorg);
				if (list == null) {
					list = new ArrayList<OrderVO>();
					map.put(pk_psfinanceorg, list);
				}
				list.add(vo);
			}
		}

		for (Entry<String, List<OrderVO>> entry : map.entrySet()) {
			String currtypeByOrgID = CurrencyRateUtilHelper.getInstance()
					.getLocalCurrtypeByOrgID(entry.getKey());
			List<OrderVO> list = entry.getValue();
			for (OrderVO vo : list) {
				for (OrderItemVO item : vo.getBVO()) {
					item.setCcurrencyid(currtypeByOrgID);
				}
			}
		}
	}

	/**
	 * 设置VAT信息 <br/>
	 * 
	 * @author zhangshqb
	 * @param vos
	 */
	private void setVatInfo(OrderVO[] vos) {
		Set<String> psfinanceorgs = new HashSet<String>();// 结算财务组织
		Set<String> arrvstoorgs = new HashSet<String>();// 收货库存组织
		Set<String> suppliers = new HashSet<String>();// 供应商
		for (OrderVO vo : vos) {
			for (OrderItemVO item : vo.getBVO()) {
				String psfinanceorg = item.getPk_psfinanceorg();
				psfinanceorgs.add(psfinanceorg);
				String arrvstoorg = item.getPk_arrvstoorg();
				arrvstoorgs.add(arrvstoorg);
			}
			suppliers.add(vo.getHVO().getPk_supplier());
		}
		Map<String, String> taxcountrys = FinanceOrgPubService
				.queryCountryByFinanceOrg(psfinanceorgs.toArray(new String[0]));
		Map<String, String> countryByStockOrg = StockOrgPubService
				.queryCountryByStockOrg(arrvstoorgs.toArray(new String[0]));
		Map<String, String> countrys = SupplierPubService
				.queryCountryBySupplier(suppliers.toArray(new String[0]));
		Map<String, String> financeOrgIDByStockOrgID = StockOrgPubService
				.queryFinanceOrgIDByStockOrgID(arrvstoorgs.toArray(new String[0]));
		for (OrderVO vo : vos) {
			String pk_supplier = vo.getHVO().getPk_supplier();
			int[] rows = new int[vo.getBVO().length];
			for (int i = 0; i < rows.length; i++) {
				rows[i] = i;
			}
			for (OrderItemVO item : vo.getBVO()) {
				if (item.getCsendcountryid() == null) {
					item.setCsendcountryid(countrys.get(pk_supplier));
				}
				String stockorg = item.getPk_arrvstoorg();
				if (item.getCrececountryid() == null) {
					if (stockorg != null) {
						item.setCrececountryid(countryByStockOrg.get(stockorg));
					} else {
						item.setCrececountryid(taxcountrys.get(item.getPk_psfinanceorg()));
					}
				}
				String psfinanceorg = item.getPk_psfinanceorg();
				String financeOrg = financeOrgIDByStockOrgID.get(item
						.getPk_psfinanceorg());
				String taxcountry = financeOrg.equals(psfinanceorg) ? countryByStockOrg
						.get(stockorg) : taxcountrys.get(psfinanceorg);
				if (item.getCtaxcountryid() == null) {
					item.setCtaxcountryid(taxcountry);
				}
			}
			AggVOHelper<OrderVO> voHelper = new AggVOHelper<OrderVO>(vo);
			OrderVatValueFillRule rule = new OrderVatValueFillRule(
					new AggVOHelper[] { voHelper });
			rule.prepare();
			rule.process();

			// 询汇率
			CurrencyAndExchangerate exchangerate = new CurrencyAndExchangerate(voHelper);
			exchangerate.setCurrencyAndExchangeRate(rows);
		}
	}

	/**
	 * 根据供应商设置供应商相关的默认信息 <br/>
	 * 
	 * @author zhangshqb
	 * @param list
	 *          需要填充默认信息的VO集合
	 */
	private void fillSupplierInfo(OrderVO[] list) {
		SupplierInfoUtil util = new SupplierInfoUtil();
		HashMap<String, SupplierInfo> supplierMap = new HashMap<String, SupplierInfo>();
		for (OrderVO vo : list) {
			OrderHeaderVO hvo = vo.getHVO();
			String supplier = hvo.getPk_supplier();
			BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
			SupplierInfo supplierInfo = supplierMap.get(supplier);
			if (supplierInfo == null) {
				supplierInfo = util.getSupplierInfo(helper);
				supplierMap.put(supplier, supplierInfo);
			}
			SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(helper);
			vendorDefaultValue.setDefaultValueNotClear(supplierInfo);
		}
	}

	/**
	 * 含税优先还是无税优先
	 * 
	 * @param pk_org
	 * @return
	 */
	private boolean isTaxPricePriorToPrice(String pk_org) {
		boolean flag = true;
		if (null == pk_org) {
			return flag;
		}
		PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
		if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
			flag = false;
		}
		return flag;
	}

	private void processReceivePlan(Map<String, List<OrderVO>> map)
			throws BusinessException {
		Set<String> transtypecodes = map.keySet();
		IPoTransTypeQuery query = NCLocator.getInstance().lookup(
				IPoTransTypeQuery.class);
		Map<String, PoTransTypeVO> transtypeMap = query.queryAttrByTypes(
				transtypecodes.toArray(new String[transtypecodes.size()]),
				new String[] { PoTransTypeVO.BRECEIVEPLAN });
		if (transtypeMap == null || transtypeMap.isEmpty()) {
			return;
		}
		for (Entry<String, PoTransTypeVO> entry : transtypeMap.entrySet()) {
			String transtypecode = entry.getKey();
			List<OrderVO> list = map.get(transtypecode);
			PoTransTypeVO vo = entry.getValue();
			if (vo != null && vo.getBreceiveplan().booleanValue()) {
			}
			for (OrderVO order : list) {
				for (OrderItemVO item : order.getBVO()) {
					if (item.getPk_arrvstoorg() == null) {
						item.setPk_arrvstoorg(item.getPk_reqstoorg());
					}
					if (item.getPk_arrvstoorg_v() == null) {
						item.setPk_arrvstoorg_v(item.getPk_reqstoorg_v());
					}
				}
			}
		}
	}

}

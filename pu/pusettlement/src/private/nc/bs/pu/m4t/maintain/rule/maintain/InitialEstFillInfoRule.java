package nc.bs.pu.m4t.maintain.rule.maintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ftpub.reference.uap.bd.DeptPubUtilService;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.ic.material.query.InvInfoQuery;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.InitialEstScaleRule;
import nc.vo.pu.m4t.rule.InitialEstVatValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.calculator.data.IDataSetDollarForCal;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationDollarForItems;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * @description
 *            期初暂估单导入时，补充其他数据信息
 * @scene
 *      期初暂估单导入
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-6-23 下午4:21:56
 * @author zhangshqb
 */
public class InitialEstFillInfoRule implements IRule<InitialEstVO> {

	private ScaleUtils scale = null;

	@Override
	public void process(InitialEstVO[] vos) {
		String pk_group = vos[0].getHeader().getPk_group();
		this.scale = new nc.vo.pubapp.scale.ScaleUtils(pk_group);
		this.fillInfo(vos);
		// 补充完数据信息之后做精度处理
		new InitialEstScaleRule().setScale(new BillVOScaleProcessor(pk_group, vos),
				null);
	}

	/**
	 * 补充表头信息
	 * 
	 * @param headvos
	 */
	private void fillHeadvoInfo(ArrayList<InitialEstHeaderVO> headvos) {
		HashSet<String> pk_orgs = new HashSet<String>();
		HashSet<String> pk_depts_v = new HashSet<String>();
		HashSet<String> pk_purchaseorgs = new HashSet<String>();
		HashSet<String> pk_stockorgs_v = new HashSet<String>();
		HashSet<String> ctrantypeids = new HashSet<String>();
		for (InitialEstHeaderVO vo : headvos) {
			pk_orgs.add(vo.getPk_org());
			pk_depts_v.add(vo.getPk_dept_v());
			pk_purchaseorgs.add(vo.getPk_purchaseorg());
			pk_stockorgs_v.add(vo.getPk_stockorg_v());
			ctrantypeids.add(vo.getCtrantypeid());
		}
		String[] pk_org = pk_orgs.toArray(new String[0]);
		Map<String, String> ccurrencyids = OrgUnitPubService
				.queryOrgCurrByPk(pk_org);
		Map<String, String> vidsByOrgIDS = OrgUnitPubService
				.getNewVIDSByOrgIDS(pk_org);
		Map<String, String> purOids_v = OrgUnitPubService
				.getNewVIDSByOrgIDS(pk_purchaseorgs.toArray(new String[0]));
		Map<String, String> stoOids_V = OrgUnitPubService
				.getOrgIDSByVIDS(pk_stockorgs_v.toArray(new String[0]));
		Map<String, String> types = PfServiceScmUtil
				.getTrantypecodeByids(ctrantypeids.toArray(new String[0]));
		Map<String, String> deptIDS_V = DeptPubUtilService
				.getDeptOidByVid(pk_depts_v.toArray(new String[0]));
		for (InitialEstHeaderVO headvo : headvos) {
			headvo.setStatus(VOStatus.NEW);
			if (headvo.getCcurrencyid() == null) {
				headvo.setCcurrencyid(ccurrencyids.get(headvo.getPk_org()));
			}
			if (headvo.getNexchangerate() == null) {
				String corigcurrencyid = headvo.getCorigcurrencyid();
				String ccurrencyid = headvo.getCcurrencyid();
				UFDouble rate = CurrencyRate.getCurrencySellRateByOrg(
						headvo.getPk_org(), corigcurrencyid, ccurrencyid,
						headvo.getDbilldate());
				headvo.setNexchangerate(rate);
			}
			if (headvo.getPk_org_v() == null) {
				headvo.setPk_org_v(vidsByOrgIDS.get(headvo.getPk_org()));
			}
			if (headvo.getPk_purchaseorg_v() == null) {
				headvo.setPk_purchaseorg_v(purOids_v.get(headvo.getPk_purchaseorg()));
			}
			if (headvo.getPk_stockorg() == null) {
				headvo.setPk_stockorg(stoOids_V.get(headvo.getPk_stockorg_v()));
			}
			if (headvo.getPk_dept() == null) {
				headvo.setPk_dept(deptIDS_V.get(headvo.getPk_dept_v()));
			}
			if (headvo.getVtrantypecode() == null) {
				headvo.setVtrantypecode(types.get(headvo.getCtrantypeid()));
			}
		}
	}

	/**
	 * 补充所有信息
	 * 
	 * @param vos
	 */
	private void fillInfo(InitialEstVO[] vos) {
		ArrayList<InitialEstHeaderVO> headers = new ArrayList<InitialEstHeaderVO>();
		ArrayList<InitialEstItemVO[]> items = new ArrayList<InitialEstItemVO[]>();
		HashSet<String> suppliers = new HashSet<String>();
		HashSet<String> storgs = new HashSet<String>();
		HashSet<String> orgs = new HashSet<String>();
		for (InitialEstVO estVO : vos) {
			InitialEstHeaderVO header = estVO.getHeader();
			headers.add(header);
		}
		this.fillHeadvoInfo(headers);
		for (InitialEstVO estVO : vos) {
			InitialEstHeaderVO header = estVO.getHeader();
			suppliers.add(header.getPk_supplier());
			items.add(estVO.getItems());
			storgs.add(header.getPk_stockorg());
			orgs.add(header.getPk_org());
		}
		InvInfoQuery query = new InvInfoQuery();
		HashSet<String> material = new HashSet<String>();
		HashSet<String> apforg_v = new HashSet<String>();
		for (InitialEstItemVO[] itemVOs : items) {
			for (InitialEstItemVO vo : itemVOs) {
				String pk_material = vo.getPk_material();
				String pk_apfinanceorg_v = vo.getPk_apfinanceorg_v();
				material.add(pk_material);
				apforg_v.add(pk_apfinanceorg_v);
			}
		}
		Map<String, String> countrys = SupplierPubService
				.queryCountryBySupplier(suppliers.toArray(new String[0]));
		Map<String, InvBasVO> basVOs = query.getInvBasVOs(material
				.toArray(new String[0]));
		Map<String, String> orgVids_v = OrgUnitPubService.getOrgIDSByVIDS(apforg_v
				.toArray(new String[0]));
		Map<String, String> countryByStockOrg = StockOrgPubService
				.queryCountryByStockOrg(storgs.toArray(new String[0]));
		Map<String, String> taxcountrys = FinanceOrgPubService
				.queryCountryByFinanceOrg(orgs.toArray(new String[0]));
		Map<String, String> financeOrgIDByStockOrgID = StockOrgPubService
				.queryFinanceOrgIDByStockOrgID(storgs.toArray(new String[0]));
		for (InitialEstVO estVo : vos) {
			InitialEstHeaderVO header = estVo.getHeader();
			String pk_supplier = header.getPk_supplier();
			String stockorg = header.getPk_stockorg();
			String org = header.getPk_org();
			String pk_group = header.getPk_group();
			String financeOrg = financeOrgIDByStockOrgID.get(stockorg);
			String taxcountry = financeOrg.equals(org) ? countryByStockOrg
					.get(stockorg) : taxcountrys.get(org);
			Map<String, UFDouble> prices = new HashMap<String, UFDouble>();
			for (InitialEstItemVO vo : (InitialEstItemVO[]) estVo
					.getChildren(InitialEstItemVO.class)) {
				vo.setStatus(VOStatus.NEW);
				vo.setPk_org(org);
				vo.setPk_org_v(header.getPk_org_v());
				prices.put(vo.getCrowno(), vo.getNastorigprice());
				vo.setAttributeValue(InitialEstHeaderVO.NEXCHANGERATE,
						header.getNexchangerate());
				vo.setAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID,
						header.getCorigcurrencyid());
				if (vo.getPk_srcmaterial() == null) {
					vo.setPk_srcmaterial(basVOs.get(vo.getPk_material()).getPk_source());
				}
				if (vo.getPk_apfinanceorg() == null) {
					vo.setPk_apfinanceorg(orgVids_v.get(vo.getPk_apfinanceorg_v()));
				}
				if (vo.getCunitid() == null) {
					vo.setCunitid(basVOs.get(vo.getPk_material()).getPk_measdoc());
				}
				if (vo.getCsendcountryid() == null) {
					vo.setCsendcountryid(countrys.get(pk_supplier));
				}
				if (vo.getCrececountryid() == null) {
					vo.setCrececountryid(countryByStockOrg.get(stockorg));
				}
				if (vo.getCtaxcountryid() == null) {
					vo.setCtaxcountryid(taxcountry);
				}
				if (vo.getFbuysellflag() == null) {
					if (vo.getCsendcountryid().equals(vo.getCtaxcountryid())) {
						vo.setFbuysellflag(nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum.NATIONAL_BUY
								.value());
					} else {
						vo.setFbuysellflag(nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum.IMPORT
								.value());
					}
				}
				if (vo.getPk_group() == null) {
					vo.setPk_group(pk_group);
				}
			}
			this.setVatInfo(estVo);
			for (ISuperVO vo : estVo.getChildren(InitialEstItemVO.class)) {
				vo.setAttributeValue(InitialEstItemVO.NASTORIGPRICE,
						prices.get(vo.getAttributeValue(InitialEstItemVO.CROWNO)));
			}
			this.calculate(new AggVOHelper<InitialEstVO>(estVo),
					InitialEstItemVO.NASTORIGPRICE, estVo);
		}
	}

	/**
	 * 设置vat信息
	 * 
	 * @param estVO
	 */
	private void setVatInfo(InitialEstVO estVO) {
		AggVOHelper<InitialEstVO> voHelper = new AggVOHelper<InitialEstVO>(estVO);
		int count = voHelper.getItemCount();
		int[] rows = new int[count];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = i;
		}
		MapList<String, Integer> mapList = new InitialEstVatValue().setVatValue(
				voHelper, rows);
		if (null == mapList || mapList.size() == 0) {
			return;
		}
	}

	/**
	 * 联动计算
	 * 
	 * @param voHelper
	 * @param rows
	 * @param itemKey
	 * @param estVO
	 */
	private void calculate(AggVOHelper<InitialEstVO> voHelper, String itemKey,
			InitialEstVO estVO) {
		IRelationForItems item = new InitialEstRelationItemForCal();
		String pk_org = (String) voHelper
				.getHeadValue(InitialEstHeaderVO.PK_PURCHASEORG);
		boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);
		InitialEstItemVO[] vos = (InitialEstItemVO[]) estVO
				.getChildren(InitialEstItemVO.class);
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		CircularlyAccessibleValueObject parentVO = estVO.getParentVO();

		Set<String> allKeys = new HashSet<String>();
		allKeys.addAll(Arrays.asList(parentVO.getAttributeNames()));
		allKeys.addAll(Arrays.asList(vos[0].getAttributeNames()));

		for (InitialEstItemVO vo : vos) {
			// 创建数据集实例 初始化数据关系计算用的数据集
			IDataSetForCal data = new InitialEstBillCardPanelDataSet(parentVO, vo,
					item, allKeys);
			Calculator tool = new Calculator(data, this.scale);//
			// 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
			Condition cond = new Condition();// 创建参数实例
			// 设置是否进行本币换算
			cond.setIsCalLocalCurr(true);

			// 只有主单价没有单位的单价时，计算的策略
			if (InitialEstItemVO.NASTNUM.equals(itemKey)
					|| InitialEstItemVO.NNUM.equals(itemKey)) {
				if (vo.getAttributeValue(InitialEstItemVO.NASTORIGPRICE) == null
						|| vo.getAttributeValue(InitialEstItemVO.NASTORIGTAXPRICE) == null) {
					cond.setCalLocalPior(true);
					if (InitialEstItemVO.NNUM.equals(itemKey)) {
						cond.setUnitPriorType(Condition.MAIN_PRIOR);
					}
				}
			}

			// 设置调单价方式调单价
			cond.setIsChgPriceOrDiscount(true);
			cond.setOrigCurToGlobalMoney(false);
			cond.setOrigCurToGroupMoney(false);
			// 设置是否固定单位换算率
			cond.setIsFixNchangerate(true);
			// 是否固定报价单位换算率
			cond.setIsFixNqtunitrate(true);
			// 设置含税优先还是无税优先
			cond.setIsTaxOrNet(isTaxPricePriorToPrice);
			// VAT联动计算，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
			// 因为采购支持进口采购、国内采购。所以不用判断出口销售
			Integer fbuysellflag = (Integer) vo
					.getAttributeValue(InitialEstItemVO.FBUYSELLFLAG);
			cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));
			// 两个参数 cond 为计算时的参数条件
			tool.calculate(cond, itemKey);
			UFDouble hsl = HslParseUtil.getUFDoubleHsl(vo.getVchangerate());
			ScaleUtils scaleUtils = new ScaleUtils(estVO.getHeader().getPk_group());
			if (hsl == null) {
				vo.setNnum(scaleUtils.adjustNumScale(vo.getNastnum(), vo.getCunitid()));
			} else {
				vo.setNnum(scaleUtils.adjustNumScale(vo.getNastnum().multiply(hsl),
						vo.getCunitid()));
			}
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

	private static class InitialEstBillCardPanelDataSet implements
			IDataSetForCal, IDataSetDollarForCal {

		private CircularlyAccessibleValueObject headVO;

		private CircularlyAccessibleValueObject bodyVO;

		private IRelationForItems item;

		private Set<String> itemKeys;

		/**
		 * InitialEstBillCardPanelDataSet 的构造子
		 * 
		 * @param cardPanel
		 * @param row
		 * @param item
		 */
		public InitialEstBillCardPanelDataSet(
				CircularlyAccessibleValueObject headVO,
				CircularlyAccessibleValueObject bodyVO, IRelationForItems item,
				Set<String> allItemKeys) {
			this.headVO = headVO;
			this.bodyVO = bodyVO;
			this.item = item;
			this.itemKeys = allItemKeys;
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getBillDate()
		 */
		@Override
		public UFDate getBillDate() {
			return (UFDate) this.headVO
					.getAttributeValue(InitialEstHeaderVO.DBILLDATE);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCcurrencyid()
		 */
		@Override
		public String getCcurrencyid() {
			return (String) this.headVO
					.getAttributeValue(InitialEstHeaderVO.CCURRENCYID);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCorigcurrencyid()
		 */
		@Override
		public String getCorigcurrencyid() {
			return (String) this.headVO
					.getAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getNexchangerate()
		 */
		@Override
		public UFDouble getNexchangerate() {
			return (UFDouble) this.headVO
					.getAttributeValue(InitialEstHeaderVO.NEXCHANGERATE);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#hasItemKey(java.lang.String)
		 */
		@Override
		public boolean hasItemKey(String key) {
			if (InitialEstHeaderVO.NEXCHANGERATE.equals(key)) {
				return true;
			} else if (InitialEstHeaderVO.CORIGCURRENCYID.equals(key)) {
				return true;
			} else if (InitialEstHeaderVO.CCURRENCYID.equals(key)) {
				return true;
			} else if (InitialEstHeaderVO.DBILLDATE.equals(key)) {
				return true;
			} else {
				return this.itemKeys.contains(key);
			}
		}

		@Override
		public Object getAttributeValue(String key) {
			Object value = this.bodyVO.getAttributeValue(key);
			if (value == null) {
				value = this.headVO.getAttributeValue(key);
			}
			return value;
		}

		@Override
		public String getCastunitid() {
			String value = this.getString(this.item.getCastunitidKey());
			return value;
		}

		/** 获得报价币种 */
		@Override
		public String getCqtcurrencyid() {
			String value = this.getString(this.item.getCqtcurrencyidKey());
			return value;
		}

		@Override
		public String getCqtunitid() {
			String value = this.getString(this.item.getCqtunitidKey());
			return value;
		}

		@Override
		public String getCunitid() {
			String value = this.getString(this.item.getCunitidKey());
			return value;
		}

		@Override
		public int getFtaxtypeflag() {
			int value = this.getInt(this.item.getFtaxtypeflagKey());
			return value;
		}

		@Override
		public UFDouble getNaskqtorigprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNaskqtorigpriceKey());
			return value;
		}

		@Override
		public UFDouble getNaskqtorigtaxprc() {
			UFDouble value = this
					.getUFDoubleValue(this.item.getNaskqtorigtaxprcKey());
			return value;
		}

		@Override
		public UFDouble getNaskqtprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNaskqtpriceKey());
			return value;
		}

		@Override
		public UFDouble getNaskqttaxprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNaskqttaxpriceKey());
			return value;
		}

		@Override
		public UFDouble getNassistnum() {
			UFDouble value = this.getUFDoubleValue(this.item.getNassistnumKey());
			return value;
		}

		@Override
		public String getNchangerate() {
			String value = this.getString(this.item.getNchangerateKey());
			return value;
		}

		@Override
		public UFDouble getNcostmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNcostmnyKey());
			return value;
		}

		@Override
		public UFDouble getNcostprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNcostpriceKey());
			return value;
		}

		@Override
		public UFDouble getNdiscount() {
			UFDouble value = this.getUFDoubleValue(this.item.getNdiscountKey());
			return value;
		}

		@Override
		public UFDouble getNdiscountrate() {
			UFDouble value = this.getUFDoubleValue(this.item.getNdiscountrateKey());
			return value;
		}

		@Override
		public UFDouble getNglobalexchgrate() {
			UFDouble value = this
					.getUFDoubleValue(this.item.getNglobalexchgrateKey());
			return value;
		}

		@Override
		public UFDouble getNglobalmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNglobalmnyKey());
			return value;
		}

		@Override
		public UFDouble getNglobaltaxmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNglobaltaxmnyKey());
			return value;
		}

		@Override
		public UFDouble getNgroupexchgrate() {
			UFDouble value = this.getUFDoubleValue(this.item.getNgroupexchgrateKey());
			return value;
		}

		@Override
		public UFDouble getNgroupmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNgroupmnyKey());
			return value;
		}

		@Override
		public UFDouble getNgrouptaxmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNgrouptaxmnyKey());
			return value;
		}

		@Override
		public UFDouble getNitemdiscountrate() {
			UFDouble value = this.getUFDoubleValue(this.item
					.getNitemdiscountrateKey());
			return value;
		}

		@Override
		public UFDouble getNmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNmnyKey());
			return value;
		}

		@Override
		public UFDouble getNnetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNnetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNnum() {
			UFDouble value = this.getUFDoubleValue(this.item.getNnumKey());
			return value;
		}

		@Override
		public UFDouble getNorigdiscount() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigdiscountKey());
			return value;
		}

		@Override
		public UFDouble getNorigmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigmnyKey());
			return value;
		}

		@Override
		public UFDouble getNorignetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorignetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNorigprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigpriceKey());
			return value;
		}

		@Override
		public UFDouble getNorigtaxmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigtaxmnyKey());
			return value;
		}

		@Override
		public UFDouble getNorigtaxnetprice() {
			UFDouble value = this
					.getUFDoubleValue(this.item.getNorigtaxnetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNorigtaxprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigtaxpriceKey());
			return value;
		}

		@Override
		public UFDouble getNprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtnetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtnetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtorignetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtorignetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtorigprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtorigpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtorigtaxnetprc() {
			UFDouble value = this
					.getUFDoubleValue(this.item.getNqtorigtaxnetprcKey());
			return value;
		}

		@Override
		public UFDouble getNqtorigtaxprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtorigtaxpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqttaxnetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqttaxnetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqttaxprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqttaxpriceKey());
			return value;
		}

		@Override
		public UFDouble getNqtunitnum() {
			UFDouble value = this.getUFDoubleValue(this.item.getNqtunitnumKey());
			return value;
		}

		@Override
		public String getNqtunitrate() {
			String value = this.getString(this.item.getNqtunitrateKey());
			return value;
		}

		@Override
		public UFDouble getNtax() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtaxKey());
			return value;
		}

		@Override
		public UFDouble getNtaxmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtaxmnyKey());
			return value;
		}

		@Override
		public UFDouble getNtaxnetprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtaxnetpriceKey());
			return value;
		}

		@Override
		public UFDouble getNtaxprice() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtaxpriceKey());
			return value;
		}

		@Override
		public UFDouble getNtaxrate() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtaxrateKey());
			return value;
		}

		@Override
		public UFDouble getNtotalnum() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtotalnumKey());
			return value;
		}

		@Override
		public UFDouble getNtotalorigmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNtotalorigmnyKey());
			return value;
		}

		@Override
		public UFDouble getNtotalorigtaxmny() {
			UFDouble value = this
					.getUFDoubleValue(this.item.getNtotalorigtaxmnyKey());
			return value;
		}

		@Override
		public String getPk_group() {
			String value = this.getString(this.item.getPk_group());
			return value;
		}

		@Override
		public String getPk_org() {
			String value = this.getString(this.item.getPk_org());
			return value;
		}

		@Override
		public UFDouble getQualifiedNum() {
			UFDouble value = this.getUFDoubleValue(this.item.getQualifiedNumKey());
			return value;
		}

		@Override
		public IRelationForItems getRelationForItem() {
			return this.item;
		}

		@Override
		public UFDouble getUnQualifiedNum() {
			UFDouble value = this.getUFDoubleValue(this.item.getUnQualifiedNumKey());
			return value;
		}

		@Override
		public void setBillDate(UFDate date) {
			this.setAttributeValue(this.item.getBillDate(), date);
		}

		@Override
		public void setCastunitid(String value) {
			this.setAttributeValue(this.item.getUnQualifiedNumKey(), value);
		}

		/** 设置本位币币种 */
		@Override
		public void setCcurrencyid(String value) {
			this.setAttributeValue(this.item.getCcurrencyidKey(), value);
		}

		/** 设置原币币种 */
		@Override
		public void setCorigcurrencyid(String value) {
			this.setAttributeValue(this.item.getCorigcurrencyidKey(), value);
		}

		/** 设置报价币种 */
		@Override
		public void setCqtcurrencyid(String value) {
			this.setAttributeValue(this.item.getCqtcurrencyidKey(), value);
		}

		@Override
		public void setCqtunitid(String value) {
			this.setAttributeValue(this.item.getUnQualifiedNumKey(), value);
		}

		@Override
		public void setCunitid(String value) {
			this.setAttributeValue(this.item.getCunitidKey(), value);
		}

		@Override
		public void setFtaxtypeflag(int value) {
			this.setAttributeValue(this.item.getFtaxtypeflagKey(),
					Integer.valueOf(value));
		}

		@Override
		public void setNaskqtorigprice(UFDouble value) {
			this.setAttributeValue(this.item.getNaskqtorigpriceKey(), value);
		}

		@Override
		public void setNaskqtorigtaxprc(UFDouble value) {
			this.setAttributeValue(this.item.getNaskqtorigtaxprcKey(), value);
		}

		@Override
		public void setNaskqtprice(UFDouble value) {
			this.setAttributeValue(this.item.getNaskqtpriceKey(), value);
		}

		@Override
		public void setNaskqttaxprice(UFDouble value) {
			this.setAttributeValue(this.item.getNaskqttaxpriceKey(), value);
		}

		@Override
		public void setNassistnum(UFDouble value) {
			this.setAttributeValue(this.item.getNassistnumKey(), value);
		}

		@Override
		public void setNchangerate(String value) {
			this.setAttributeValue(this.item.getNchangerateKey(), value);
		}

		@Override
		public void setNcostmny(UFDouble value) {
			this.setAttributeValue(this.item.getNcostmnyKey(), value);
		}

		@Override
		public void setNcostprice(UFDouble value) {
			this.setAttributeValue(this.item.getNcostpriceKey(), value);
		}

		@Override
		public void setNdiscount(UFDouble value) {
			this.setAttributeValue(this.item.getNdiscountKey(), value);
		}

		@Override
		public void setNdiscountrate(UFDouble value) {
			this.setAttributeValue(this.item.getNdiscountrateKey(), value);
		}

		@Override
		public void setNexchangerate(UFDouble value) {
			this.setAttributeValue(this.item.getNexchangerateKey(), value);
		}

		@Override
		public void setNglobalexchgrate(UFDouble value) {
			this.setAttributeValue(this.item.getNglobalexchgrateKey(), value);
		}

		@Override
		public void setNglobalmny(UFDouble value) {
			this.setAttributeValue(this.item.getNglobalmnyKey(), value);
		}

		@Override
		public void setNglobaltaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNglobaltaxmnyKey(), value);
		}

		@Override
		public void setNgroupexchgrate(UFDouble value) {
			this.setAttributeValue(this.item.getNgroupexchgrateKey(), value);
		}

		@Override
		public void setNgroupmny(UFDouble value) {
			this.setAttributeValue(this.item.getNgroupmnyKey(), value);
		}

		@Override
		public void setNgrouptaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNgrouptaxmnyKey(), value);
		}

		@Override
		public void setNitemdiscountrate(UFDouble value) {
			this.setAttributeValue(this.item.getNitemdiscountrateKey(), value);
		}

		@Override
		public void setNmny(UFDouble value) {
			this.setAttributeValue(this.item.getNmnyKey(), value);
		}

		@Override
		public void setNnetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNnetpriceKey(), value);
		}

		@Override
		public void setNnum(UFDouble value) {
			this.setAttributeValue(this.item.getNnumKey(), value);
		}

		@Override
		public void setNorigdiscount(UFDouble value) {
			this.setAttributeValue(this.item.getNorigdiscountKey(), value);
		}

		@Override
		public void setNorigmny(UFDouble value) {
			this.setAttributeValue(this.item.getNorigmnyKey(), value);
		}

		@Override
		public void setNorignetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNorignetpriceKey(), value);
		}

		@Override
		public void setNorigprice(UFDouble value) {
			this.setAttributeValue(this.item.getNorigpriceKey(), value);
		}

		@Override
		public void setNorigtaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNorigtaxmnyKey(), value);
		}

		@Override
		public void setNorigtaxnetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNorigtaxnetpriceKey(), value);
		}

		@Override
		public void setNorigtaxprice(UFDouble value) {
			this.setAttributeValue(this.item.getNorigtaxpriceKey(), value);
		}

		@Override
		public void setNprice(UFDouble value) {
			this.setAttributeValue(this.item.getNpriceKey(), value);
		}

		@Override
		public void setNqtnetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqtnetpriceKey(), value);
		}

		@Override
		public void setNqtorignetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqtorignetpriceKey(), value);
		}

		@Override
		public void setNqtorigprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqtorigpriceKey(), value);
		}

		@Override
		public void setNqtorigtaxnetprc(UFDouble value) {
			this.setAttributeValue(this.item.getNqtorigtaxnetprcKey(), value);
		}

		@Override
		public void setNqtorigtaxprice(Object value) {
			this.setAttributeValue(this.item.getNqtorigtaxpriceKey(), value);
		}

		@Override
		public void setNqtprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqtpriceKey(), value);
		}

		@Override
		public void setNqttaxnetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqttaxnetpriceKey(), value);
		}

		@Override
		public void setNqttaxprice(UFDouble value) {
			this.setAttributeValue(this.item.getNqttaxpriceKey(), value);
		}

		@Override
		public void setNqtunitnum(UFDouble value) {
			this.setAttributeValue(this.item.getNqtunitnumKey(), value);
		}

		@Override
		public void setNqtunitrate(String value) {
			this.setAttributeValue(this.item.getNqtunitrateKey(), value);
		}

		@Override
		public void setNtax(UFDouble value) {
			this.setAttributeValue(this.item.getNtaxKey(), value);
		}

		@Override
		public void setNtaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNtaxmnyKey(), value);
		}

		@Override
		public void setNtaxnetprice(UFDouble value) {
			this.setAttributeValue(this.item.getNtaxnetpriceKey(), value);
		}

		@Override
		public void setNtaxprice(UFDouble value) {
			this.setAttributeValue(this.item.getNtaxpriceKey(), value);
		}

		@Override
		public void setNtaxrate(UFDouble value) {
			this.setAttributeValue(this.item.getNtaxrateKey(), value);
		}

		@Override
		public void setNtotalnum(UFDouble value) {
			this.setAttributeValue(this.item.getNtotalnumKey(), value);
		}

		@Override
		public void setNtotalorigmny(UFDouble value) {
			this.setAttributeValue(this.item.getNtotalorigmnyKey(), value);
		}

		@Override
		public void setNtotalorigtaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNtotalorigtaxmnyKey(), value);
		}

		@Override
		public void setPk_group(String value) {
			this.setAttributeValue(this.item.getPk_group(), value);
		}

		@Override
		public void setPk_org(String value) {
			this.setAttributeValue(this.item.getPk_org(), value);
		}

		@Override
		public void setQualifiedNum(UFDouble value) {
			this.setAttributeValue(this.item.getQualifiedNumKey(), value);
		}

		@Override
		public void setUnQualifiedNum(UFDouble value) {
			this.setAttributeValue(this.item.getUnQualifiedNumKey(), value);
		}

		private int getInt(String key) {
			if (key == null) {
				return -1;
			}
			Object obj = this.bodyVO.getAttributeValue(key);
			int value = ValueUtils.getInt(obj);
			return value;
		}

		private String getString(String key) {
			if (key == null) {
				return null;
			}
			Object value = this.bodyVO.getAttributeValue(key);
			String str = ValueUtils.getString(value);
			return str;
		}

		private UFDouble getUFDoubleValue(String key) {
			if (key == null) {
				return null;
			}
			Object value = this.bodyVO.getAttributeValue(key);
			UFDouble d = ValueUtils.getUFDouble(value);
			return d;
		}

		private void setAttributeValue(String key, Object value) {
			this.bodyVO.setAttributeValue(key, value);
		}

		@Override
		public UFDouble getNcaltaxmny() {
			UFDouble value = this.getUFDoubleValue(this.item.getNcaltaxmnyKey());
			return value;
		}

		@Override
		public UFDouble getNdeductibleTaxrate() {
			UFDouble value = this.getUFDoubleValue(this.item
					.getNdeductibleTaxrateKey());
			return value;
		}

		@Override
		public UFDouble getNdeductibletax() {
			UFDouble value = this.getUFDoubleValue(this.item.getNdeductibletaxKey());
			return value;
		}

		@Override
		public void setNcaltaxmny(UFDouble value) {
			this.setAttributeValue(this.item.getNcaltaxmnyKey(), value);

		}

		@Override
		public void setNdeductibleTaxrate(UFDouble value) {
			this.setAttributeValue(this.item.getNdeductibleTaxrateKey(), value);

		}

		@Override
		public void setNdeductibletax(UFDouble value) {
			this.setAttributeValue(this.item.getNdeductibletaxKey(), value);

		}

		@Override
		public UFDouble getNorigtax() {
			UFDouble value = this.getUFDoubleValue(this.item.getNorigtaxKey());
			return value;
		}

		@Override
		public void setNorigtax(UFDouble value) {
			this.setAttributeValue(this.item.getNorigtaxKey(), value);

		}

		@Override
		public void setNusdmny(UFDouble value) {
			this.setAttributeValue(
					((IRelationDollarForItems) this.getRelationForItem()).getNusdmnyKey(),
					value);
		}

		@Override
		public UFDouble getNusdmny() {
			UFDouble value = this.getUFDoubleValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNusdmnyKey());
			return value;
		}

		@Override
		public void setNqtusdprice(UFDouble value) {
			this.setAttributeValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNqtusdpriceKey(), value);
		}

		@Override
		public UFDouble getNqtusdprice() {
			UFDouble value = this.getUFDoubleValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNqtusdpriceKey());
			return value;
		}

		@Override
		public void setNusdprice(UFDouble value) {
			this.setAttributeValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNusdpriceKey(), value);
		}

		@Override
		public UFDouble getNusdprice() {
			UFDouble value = this.getUFDoubleValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNusdpriceKey());
			return value;
		}

		@Override
		public void setNusdexchgrate(UFDouble value) {
			this.setAttributeValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNusdexchgrateKey(), value);
		}

		@Override
		public UFDouble getNusdexchgrate() {
			UFDouble value = this.getUFDoubleValue(((IRelationDollarForItems) this
					.getRelationForItem()).getNusdexchgrateKey());
			return value;
		}

		@Override
		public void setCusdcurrencyid(String value) {
			this.setAttributeValue(((IRelationDollarForItems) this
					.getRelationForItem()).getCusdcurrencyidKey(), value);
		}

		@Override
		public String getCusdcurrencyid() {
			String value = this.getString(((IRelationDollarForItems) this
					.getRelationForItem()).getCusdcurrencyidKey());
			return value;
		}
	}

	private static class InitialEstRelationItemForCal extends RelationItemForCal {

		public InitialEstRelationItemForCal() {
			// 构造函数
		}

		@Override
		public String getCastunitidKey() {
			return super.getCqtunitidKey();
		}

		@Override
		public String getCqtunitidKey() {
			return super.getCastunitidKey();
		}

		@Override
		public String getNnetpriceKey() {
			return InitialEstItemVO.NPRICE;
		}

		@Override
		public String getNorignetpriceKey() {
			return InitialEstItemVO.NORIGPRICE;
		}

		@Override
		public String getNorigtaxnetpriceKey() {
			return InitialEstItemVO.NORIGTAXPRICE;
		}

		@Override
		public String getNqtnetpriceKey() {
			return InitialEstItemVO.NASTPRICE;
		}

		@Override
		public String getNqtorignetpriceKey() {
			return InitialEstItemVO.NASTORIGPRICE;
		}

		@Override
		public String getNqtorigpriceKey() {
			return InitialEstItemVO.NASTORIGPRICE;
		}

		@Override
		public String getNqtorigtaxnetprcKey() {
			return InitialEstItemVO.NASTORIGTAXPRICE;
		}

		@Override
		public String getNqtorigtaxpriceKey() {
			return InitialEstItemVO.NASTORIGTAXPRICE;
		}

		@Override
		public String getNqtpriceKey() {
			return InitialEstItemVO.NASTPRICE;
		}

		@Override
		public String getNqttaxnetpriceKey() {
			return InitialEstItemVO.NASTTAXPRICE;
		}

		@Override
		public String getNqttaxpriceKey() {
			return InitialEstItemVO.NASTTAXPRICE;
		}

		@Override
		public String getNqtunitnumKey() {
			return InitialEstItemVO.NASTNUM;
		}

		@Override
		public String getNqtunitrateKey() {
			return InitialEstItemVO.VCHANGERATE;
		}

		@Override
		public String getNtaxnetpriceKey() {
			return InitialEstItemVO.NTAXPRICE;
		}
	}

}

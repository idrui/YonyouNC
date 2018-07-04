package nc.vo.pu.m21.rule.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
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
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.para.NCPara;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

public class Calculate {

	/**
	 * 联动计算
	 * 
	 * @param voHelper
	 * @param rows
	 * @param itemKey
	 * @param vo
	 */
	@SuppressWarnings("unchecked")
	public void calculate(AggVOHelper<OrderVO> voHelper, OrderVO vo,
			boolean isTaxPricePriorToPrice) {
		IRelationForItems item = new InitialEstRelationItemForCal();
		String pk_group = (String) voHelper.getHeadValue(OrderHeaderVO.PK_GROUP);
		String pk_org = (String) voHelper.getHeadValue(OrderHeaderVO.PK_ORG);
		ScaleUtils scale = new ScaleUtils(pk_group);
		OrderItemVO[] vos = (OrderItemVO[]) vo.getChildren(OrderItemVO.class);
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		boolean isChangePrice = this.isChangePrice(pk_org);
		boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
		boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

		Map<String, UFBoolean> fixUnitRateAst = this.isFixUnitRate(vos, true);
		Map<String, UFBoolean> fixUnitRateQt = this.isFixUnitRate(vos, false);

		CircularlyAccessibleValueObject parentVO = vo.getParentVO();

		Set<String> allKeys = new HashSet<String>();
		allKeys.addAll(Arrays.asList(parentVO.getAttributeNames()));
		allKeys.addAll(Arrays.asList(vos[0].getAttributeNames()));
		for (OrderItemVO itemVO : vos) {
			String itemKey = this.getItemKey(itemVO, isTaxPricePriorToPrice);
			// 创建数据集实例 初始化数据关系计算用的数据集
			IDataSetForCal data = new OrderBillCardPanelDataSet(parentVO, itemVO,
					item, allKeys);
			Calculator tool = new Calculator(data, scale);//
			// 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
			Condition cond = new Condition();// 创建参数实例
			// 设置是否进行本币换算
			cond.setIsCalLocalCurr(true);

			// 只有主单价没有单位的单价时，计算的策略
			if (OrderItemVO.NASTNUM.equals(itemKey)
					|| OrderItemVO.NNUM.equals(itemKey)) {
				if (itemVO.getAttributeValue(OrderItemVO.NQTORIGPRICE) == null
						|| itemVO.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC) == null) {
					cond.setCalLocalPior(true);
					if (OrderItemVO.NNUM.equals(itemKey)) {
						cond.setUnitPriorType(Condition.MAIN_PRIOR);
					}
				}
			}
			// 设置调单价方式调单价
			// 设置调单价方式调折扣
			cond.setIsChgPriceOrDiscount(isChangePrice);
			cond.setOrigCurToGlobalMoney(isOrigCurToGlobalMoney);
			cond.setOrigCurToGroupMoney(isOrigCurToGroupMoney);

			String material = itemVO.getPk_material();
			String cunitid = itemVO.getCunitid();
			String castunitid = itemVO.getCastunitid();
			String cqtunitid = itemVO.getCqtunitid();
			String keyAst = material + cunitid + castunitid;
			String keyQt = material + cunitid + cqtunitid;
			// 设置是否固定单位换算率
			cond.setIsFixNchangerate(null == fixUnitRateAst.get(keyAst) ? false
					: fixUnitRateAst.get(keyAst).booleanValue());
			// 是否固定报价单位换算率
			cond.setIsFixNqtunitrate(null == fixUnitRateQt.get(keyQt) ? false
					: fixUnitRateQt.get(keyQt).booleanValue());
			// 设置含税优先还是无税优先
			cond.setIsTaxOrNet(isTaxPricePriorToPrice);
			// VAT联动计算，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
			// 因为采购支持进口采购、国内采购。所以不用判断出口销售
			Integer fbuysellflag = (Integer) itemVO
					.getAttributeValue(OrderItemVO.FBUYSELLFLAG);
			cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));
			// 两个参数 cond 为计算时的参数条件
			tool.calculate(cond, itemKey);
			UFDouble hsl = HslParseUtil.getUFDoubleHsl(itemVO.getVchangerate());
			ScaleUtils scaleUtils = new ScaleUtils(vo.getHVO().getPk_group());
			if (hsl == null) {
				itemVO.setNnum(scaleUtils.adjustNumScale(itemVO.getNastnum(),
						itemVO.getCunitid()));
			} else {
				itemVO.setNnum(scaleUtils.adjustNumScale(
						itemVO.getNastnum().multiply(hsl), itemVO.getCunitid()));
			}
		}
	}

	/**
	 * mengjian by 20141104
	 * 
	 * @param panel
	 * @param rows
	 * @param isAst
	 *          是辅单位还是报价单位
	 * @return
	 */
	private Map<String, UFBoolean> isFixUnitRate(OrderItemVO[] items,
			boolean isAst) {
		Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
		List<String> pk_materials = new ArrayList<String>();
		List<String> mainunits = new ArrayList<String>();
		List<String> otherunits = new ArrayList<String>();
		for (OrderItemVO item : items) {
			String material = item.getPk_material();
			String cunitid = item.getCunitid();
			String castunitid = item.getCastunitid();
			String cqtunitid = item.getCqtunitid();
			pk_materials.add(material);
			mainunits.add(cunitid);
			if (isAst) {
				otherunits.add(castunitid);
			} else {
				otherunits.add(cqtunitid);
			}
		}
		if (CollectionUtils.isEmpty(pk_materials)
				|| CollectionUtils.isEmpty(otherunits)) {
			return map;
		}
		String[] materialsstr = pk_materials.toArray(new String[0]);
		String[] mainunitsstr = mainunits.toArray(new String[0]);
		String[] otherunitsstr = otherunits.toArray(new String[0]);

		map = MaterialPubService.queryIsFixedRateByMaterialAndMeasdocs(
				materialsstr, otherunitsstr);
		Map<String, UFBoolean> mapRes = new HashMap<String, UFBoolean>();
		int count = pk_materials.size();
		for (int i = 0; i < count; i++) {
			String keyRes = materialsstr[0] + mainunitsstr[0] + otherunitsstr[0];
			String keyTemp = materialsstr[0] + otherunitsstr[0];
			// 主单位或者要转换的单位为空
			if (StringUtil.isEmpty(mainunitsstr[0])
					|| StringUtil.isEmpty(otherunitsstr[0])) {
				mapRes.put(keyRes, UFBoolean.FALSE);
			}
			// 主单位和要转换单位相同
			else if (otherunitsstr[0].equals(mainunitsstr[0])) {
				mapRes.put(keyRes, UFBoolean.TRUE);
			}
			// 主单位和要转换单位不同，查询
			else {
				mapRes.put(keyRes, map.get(keyTemp));
			}
		}
		return mapRes;

	}

	private boolean isChangePrice(String pk_org) {
		return PUParaValue.po84.adjust_price == PUSysParamUtil.getPO84(pk_org);
	}

	private boolean isOrigCurToGroupMoney(String pk_group) {
		String nc001 = PubSysParamUtil.getNC001(pk_group);
		if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
			return true;
		}
		return false;
	}

	private boolean isOrigCurToGlobalMoney() {
		String nc002 = PubSysParamUtil.getNC002();
		if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
			return true;
		}
		return false;
	}

	private String getItemKey(OrderItemVO itemVO, boolean isTaxPricePriorToPrice) {
		String itemKey = "";
		UFDouble norigmny = itemVO.getNorigmny();
		UFDouble norigtaxmny = itemVO.getNorigtaxmny();
		UFDouble nqtorigprice = itemVO.getNqtorigprice();
		UFDouble nqtorigtaxprice = itemVO.getNqtorigtaxprice();
		if (norigmny != null) {
			if (norigtaxmny != null) {
				if (isTaxPricePriorToPrice) {
					itemKey = OrderItemVO.NORIGTAXMNY;
				} else {
					itemKey = OrderItemVO.NORIGMNY;
				}
			} else {
				itemKey = OrderItemVO.NORIGMNY;
			}
		} else {
			if (norigtaxmny != null) {
				itemKey = OrderItemVO.NORIGTAXMNY;
			} else if (nqtorigprice != null) {
				if (nqtorigtaxprice != null) {
					if (isTaxPricePriorToPrice) {
						itemKey = OrderItemVO.NQTORIGTAXPRICE;
					} else {
						itemKey = OrderItemVO.NQTORIGPRICE;
					}
				} else {
					itemKey = OrderItemVO.NQTORIGPRICE;
				}
			} else if (nqtorigtaxprice != null) {
				itemKey = OrderItemVO.NQTORIGTAXPRICE;
			}
		}
		return itemKey;
	}

	private static class OrderBillCardPanelDataSet implements IDataSetForCal,
			IDataSetDollarForCal {

		private CircularlyAccessibleValueObject headVO;

		private CircularlyAccessibleValueObject bodyVO;

		private IRelationForItems item;

		private Set<String> itemKeys;

		/**
		 * OrderBillCardPanelDataSet 的构造子
		 * 
		 * @param cardPanel
		 * @param row
		 * @param item
		 */
		public OrderBillCardPanelDataSet(CircularlyAccessibleValueObject headVO,
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
			return (UFDate) this.headVO.getAttributeValue(OrderHeaderVO.DBILLDATE);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCcurrencyid()
		 */
		@Override
		public String getCcurrencyid() {
			return (String) this.bodyVO.getAttributeValue(OrderItemVO.CCURRENCYID);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCorigcurrencyid()
		 */
		@Override
		public String getCorigcurrencyid() {
			return (String) this.headVO
					.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getNexchangerate()
		 */
		@Override
		public UFDouble getNexchangerate() {
			return (UFDouble) this.bodyVO
					.getAttributeValue(OrderItemVO.NEXCHANGERATE);
		}

		/**
		 * 父类方法重写
		 * 
		 * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#hasItemKey(java.lang.String)
		 */
		@Override
		public boolean hasItemKey(String key) {
			if (OrderItemVO.NEXCHANGERATE.equals(key)) {
				return true;
			} else if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
				return true;
			} else if (OrderItemVO.CCURRENCYID.equals(key)) {
				return true;
			} else if (OrderHeaderVO.DBILLDATE.equals(key)) {
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
			return OrderItemVO.NNETPRICE;
		}

		@Override
		public String getNorignetpriceKey() {
			return OrderItemVO.NORIGNETPRICE;
		}

		@Override
		public String getNorigtaxnetpriceKey() {
			return OrderItemVO.NORIGTAXNETPRICE;
		}

		@Override
		public String getNqtnetpriceKey() {
			return OrderItemVO.NQTNETPRICE;
		}

		@Override
		public String getNqtorignetpriceKey() {
			return OrderItemVO.NQTORIGNETPRICE;
		}

		@Override
		public String getNqtorigpriceKey() {
			return OrderItemVO.NQTORIGPRICE;
		}

		@Override
		public String getNqtorigtaxnetprcKey() {
			return OrderItemVO.NQTORIGTAXNETPRC;
		}

		@Override
		public String getNqtorigtaxpriceKey() {
			return OrderItemVO.NQTORIGTAXPRICE;
		}

		@Override
		public String getNqtpriceKey() {
			return OrderItemVO.NQTPRICE;
		}

		@Override
		public String getNqttaxnetpriceKey() {
			return OrderItemVO.NQTTAXNETPRICE;
		}

		@Override
		public String getNqttaxpriceKey() {
			return OrderItemVO.NQTTAXPRICE;
		}

		@Override
		public String getNqtunitnumKey() {
			return OrderItemVO.NASTNUM;
		}

		@Override
		public String getNqtunitrateKey() {
			return OrderItemVO.VCHANGERATE;
		}

		@Override
		public String getNtaxnetpriceKey() {
			return OrderItemVO.NTAXNETPRICE;
		}
	}

}

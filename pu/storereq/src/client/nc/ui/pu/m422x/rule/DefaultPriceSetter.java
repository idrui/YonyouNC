package nc.ui.pu.m422x.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m422x.IStoreReqDefaultPriceQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.PurchaseOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.ui.pu.m422x.editor.card.afteredit.RelationCalculate;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 物资需求默认价格处理
 * 
 * @since 6.0
 * @version 2011-6-11 下午01:55:36
 * @author 田锋涛
 */

public class DefaultPriceSetter {

	private BillCardPanel cardPanel;

	public DefaultPriceSetter(BillCardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	/**
	 * @param rows
	 */
	public void setMaterialPrice(int[] rows) {
		String[] pk_material = this.getMaterialPks(rows);
		// 没有物料直接返回
		if (ArrayUtils.isEmpty(pk_material)) {
			return;
		}
		CardPanelValueUtils cardUtils = new CardPanelValueUtils(this.cardPanel);

		// 2015/9/15合并并改造通版补丁，询支持采购最新价
		// 准备询价参数
		OrderPriceQueryParam[] param = this.getParam(rows);
		if (param == null || param.length == 0) {
			return;
		}

		IStoreReqDefaultPriceQuery service = NCLocator.getInstance().lookup(
				IStoreReqDefaultPriceQuery.class);
		// 询价
		try {
			param = service.queryOrderPrice4StoreReq(param);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		this.setPrice(cardUtils, param);
	}

	/**
	 * 库存组织所属财务组织
	 * 
	 * @param pk_stockorg
	 * @return
	 */
	private String getFiOrgOfStockOrg(String pk_stockorg) {
		Map<String, String> fiOrg = StockOrgPubService
				.queryFinanceOrgIDByStockOrgID(new String[] { pk_stockorg });
		return fiOrg.get(pk_stockorg);
	}

	/**
	 * 物料pk
	 * 
	 * @param rows
	 * @return
	 */
	private String[] getMaterialPks(int[] rows) {
		HashSet<String> materialPks = new HashSet<String>();
		for (int row : rows) {
			Object pk_material = this.cardPanel.getBodyValueAt(row,
					StoreReqAppItemVO.PK_MATERIAL);
			if (pk_material != null) {
				materialPks.add(pk_material.toString());
			}
		}
		if (materialPks.size() == 0) {
			return null;
		}
		return materialPks.toArray(new String[materialPks.size()]);
	}

	/**
	 * 获取询价的参数
	 * 
	 * @param rows
	 * @return
	 */
	private OrderPriceQueryParam[] getParam(int[] rows) {
		List<OrderPriceQueryParam> params = new ArrayList<OrderPriceQueryParam>();
		CardPanelValueUtils cardUtils = new CardPanelValueUtils(this.cardPanel);
		String pk_stockOrg = cardUtils
				.getHeadTailStringValue(StoreReqAppHeaderVO.PK_ORG);
		// 获取财务组织
		String fiOrg = this.getFiOrgOfStockOrg(pk_stockOrg);
		// 准备询价参数

		List<String> materials = new ArrayList<String>();
		Map<String, Integer> materialRow = new HashMap<String, Integer>();
		for (int i = 0; i < rows.length; i++) {
			Object material = cardUtils.getBodyValue(rows[i],
					StoreReqAppItemVO.PK_MATERIAL);
			if (material != null) {
				materials.add(material.toString());
				materialRow.put(material.toString(), rows[i]);
			}
		}
		String[] orgs = new String[materials.size()];
		for (int i = 0; i < orgs.length; i++) {
			orgs[i] = pk_stockOrg;
		}
		if (!materialRow.isEmpty()) {
			// Map<stock|material,purorg>
			/**
			 * 根据库存组织ID[]、物料ID[]，得到采购组织ID[]（适用物料采购分类明细优先原则） 查找顺序：①物料档案库存信息页签中的采购组织
			 * ②根据需求库存组织+物料对应采购分类匹配采购业务委托关系找到对应的采购组织
			 * ③逐级向上匹配采购分类，直至一级分类，最后匹配采购分类为空的组织委托关系
			 **/
			Map<String, String> PurchaseOrgs = PurchaseOrgPubService
					.queryMaterialStockInfoByPk(orgs,
							materials.toArray(new String[materials.size()]));
			List<OrderPriceQueryParam> paramByOrgRelationData = this
					.getParamByOrgRelationData(PurchaseOrgs, materialRow, cardUtils,
							pk_stockOrg, fiOrg);
			if (paramByOrgRelationData != null && !paramByOrgRelationData.isEmpty()) {
				params.addAll(paramByOrgRelationData);
			}
		}

		return params.toArray(new OrderPriceQueryParam[params.size()]);
	}

	private List<OrderPriceQueryParam> getParamByOrgRelationData(
			Map<String, String> PurchaseOrgs, Map<String, Integer> materialRow,
			CardPanelValueUtils cardUtils, String pk_stockOrg, String fiOrg) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (Entry<String, String> entry : PurchaseOrgs.entrySet()) {
			String purchaseOrg = entry.getValue();
			List<Integer> list = map.get(purchaseOrg);
			if (list == null) {
				list = new ArrayList<Integer>();
				map.put(purchaseOrg, list);
			}
			Integer row = materialRow.get(entry.getKey().split("\\|")[1]);
			list.add(row);
		}
		return this.getParams(map, cardUtils, pk_stockOrg, fiOrg);
	}

	/**
	 * 获取参数
	 * 
	 * @param map
	 *          Map<Pk_purchaseorg,rows> key为采购组织，value为该库存组织对应的所有物料行
	 * @param cardUtils
	 *          CardPanelValueUtils Panel设置工具类
	 * @param pk_stockOrg
	 *          库存组织
	 * @param fiOrg
	 *          财务组织
	 * @return 询价参数集合
	 */
	private List<OrderPriceQueryParam> getParams(Map<String, List<Integer>> map,
			CardPanelValueUtils cardUtils, String pk_stockOrg, String fiOrg) {
		List<OrderPriceQueryParam> params = new ArrayList<OrderPriceQueryParam>();
		for (Entry<String, List<Integer>> entry : map.entrySet()) {
			OrderPriceQueryParam param = new OrderPriceQueryParam();
			param.setBillDate(cardUtils
					.getHeadTailUFDateValue(StoreReqAppHeaderVO.DBILLDATE));
			String ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_stockOrg);
			param.setCurrency(ccurrencyid);
			param.setPurchaseOrg(entry.getKey());
			List<Integer> rows = entry.getValue();
			OrderPriceQueryDetail[] detail = new OrderPriceQueryDetail[entry
					.getValue().size()];
			for (int i = 0; i < rows.size(); i++) {
				Integer row = rows.get(i);
				detail[i] = new OrderPriceQueryDetail();
				detail[i].setRow(row);
				// 报价单位
				detail[i].setCastunitid(cardUtils.getBodyStringValue(row,
						StoreReqAppItemVO.CUNITID));
				// 财务组织
				detail[i].setFinanceOrg(fiOrg);
				// 物料VID
				detail[i].setMaterial(cardUtils.getBodyStringValue(row,
						StoreReqAppItemVO.PK_MATERIAL));
				// 物料OID
				detail[i].setMaterialSourceId(cardUtils.getBodyStringValue(row,
						StoreReqAppItemVO.PK_SRCMATERIAL));
				// 生产厂商
				detail[i].setProductor(cardUtils.getBodyStringValue(row,
						StoreReqAppItemVO.CPRODUCTORID));
				// 收货地区
				detail[i].setReceiveArea(cardUtils.getBodyStringValue(row,
						StoreReqAppItemVO.CDEVAREAID));
			}
			param.setDetail(detail);
			params.add(param);
		}
		return params;
	}

	private void setPrice(CardPanelValueUtils cardUtils,
			OrderPriceQueryParam[] params) {
		for (OrderPriceQueryParam param : params) {
			OrderPriceQueryDetail[] details = param.getDetail();
			List<Integer> toCalRows = new ArrayList<Integer>();
			String currency = param.getCurrency();
			for (OrderPriceQueryDetail detail : details) {
				if (detail != null) {
					int row = detail.getRow();
					UFDouble newPrice = null;
					if (MathTool.nvl(detail.getPrice()).compareTo(UFDouble.ZERO_DBL) > 0) {
						newPrice = detail.getPrice();
					} else if (MathTool.nvl(detail.getOrigTaxPrice()).compareTo(
							UFDouble.ZERO_DBL) > 0) {
						newPrice = detail.getOrigTaxPrice();
					}
					if (newPrice != null) {
						ScaleUtils utils = new ScaleUtils(AppContext.getInstance().getPkGroup());
						newPrice = utils.adjustSoPuPriceScale(newPrice, currency);
						UFDouble oldPrice = cardUtils.getBodyUFDoubleValue(row,
								StoreReqAppItemVO.NTAXPRICE);
						if (!MathTool.equals(newPrice, oldPrice)) {
							cardUtils
									.setBodyValue(newPrice, row, StoreReqAppItemVO.NTAXPRICE);
							toCalRows.add(Integer.valueOf(row));
						}
					}
				}
			}

			// 联动计算
			if (toCalRows.size() > 0) {
				RelationCalculate calculator = new RelationCalculate();
				calculator.calculate(this.cardPanel, ArrayUtil.toPrimitive(toCalRows),
						StoreReqAppItemVO.NTAXPRICE);
			}
		}
	}
}

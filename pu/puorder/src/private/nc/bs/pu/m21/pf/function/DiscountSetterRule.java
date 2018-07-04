package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ct.Z2CTServices;
import nc.pubitf.pp.discount.IQueryDiscountService;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.pp.pub.discount.entity.DiscountQryResultVO;
import nc.vo.pp.pub.discount.entity.DiscountQueryVO;
import nc.vo.pp.pub.discount.enumeration.DiscountConditionEnum;
import nc.vo.pp.pub.discount.enumeration.DisntTypeDetailEnum;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 采购订单询采购折扣规则
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存时询折扣：
 * <ol>
 * <li>新增的折扣规则不影响采购订单原来的询价算法。
 * <li>采购订单保存时，检查采购订单函数询采购折扣规则，如果未配置，直接保存；如果配置，要先询采购折扣规则，调整订单折扣，再保存。
 * <li>赠品行不询折扣规则。
 * <li>暂不支持折上折业务。如果订单行手工录入了折扣或手工维护了净价导致净价<>单价且折扣规则编码为空，则该订单行不再询折扣规则。
 * <li>询到折扣以后回写含税净价及折扣字段，不受参数“PO84调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价”的控制。
 * <li>如果总括订单的交易类型为严格控制单价，对询折扣算法的影响？
 * <li>具体询折扣规则的算法见后面描述。
 * </ol>
 * <li>修改时对折扣的影响：
 * <ol>
 * <li>采购订单修改后，点击保存时，重新询采购折扣规则。
 * <li>重新询采购折扣规则时的算法与保存时算法一致。
 * <li>
 * 修改订单时，如果订单行已经询过折扣规则，则修改时不允许修改折扣。如果参数“PO84调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价”为调折扣
 * ，则无税金额、价税合计、含税净价、无税净价也不允许手工编辑。--20121108，取消参数“PO84”的校验，都只检查折扣不能编辑。
 * <li>手工维护的折扣，修改时允许修改，保存时也不会重新询折扣。
 * <li>修改后保存重新询折扣，如果未询到，则清空表体行折扣规则编码，折扣置为100，含税净价=含税单价，其他联动计算。
 * </ol>
 * <li>算法详细说明：
 * <ol>
 * <li>匹配折扣规则：
 * <ul>
 * <li>关联到采购合同的订单行，以采购合同的主组织=采购折扣规则的主组织+供应商+订单日期+币种来匹配采购折扣规则，
 * 然后再判断采购订单的主组织是否包含在采购折扣规则的适用采购组织范围内，然后再根据采购订单物料行询到最新的折扣规则， 将折扣规则编码返回采购订单行。
 * <li>
 * 未关联采购合同的订单行，根据采购订单主组织=采购折扣规则主组织（适用范围为本组织）及采购订单主组织=采购折扣规则适用采购组织（适用范围为集团、多组织）+
 * 供应商+订单日期+币种来匹配采购折扣规则，然后再根据采购订单物料行询到最新的折扣规则，将折扣规则编码返回采购订单行。
 * <li>按采购订单物料匹配折扣规则时，要先判断折扣规则表头的优先规则，如果单品折扣优先，如果该物料属于“单品折扣”，则直接返回折扣规则编码；
 * 如果未匹配到单品折扣，继续匹配组合折扣表，如果该物料属于“组合折扣”，则直接返回折扣规则编码。如果是组合折扣优先，则优先匹配组合折扣表，匹配到直接返回，
 * 匹配不到，再匹配单品折扣表。
 * </ul>
 * <li>计算折扣： 依照上述规则处理采购订单的所有物料行，将折扣规则编码相同且折扣计算条件=金额且为组合折扣且折扣组编码相同的物料行合并处理，
 * 将折扣规则编码相同且折扣计算条件=数量且为组合折扣且折扣组编码相同且报价单位相同的物料行合并处理，按照折扣规则的梯度计算折扣；
 * 其他组合折扣行直接按照折扣规则的梯度计算折扣；单品折扣直接按照折扣规则的梯度计算折扣。
 * 折扣计算条件：金额/数量，选择金额时，表示折扣的梯度以金额来计算，对应采购订单的价税合计；
 * 选择数量时，表示以采购订单报价单位对应数量为梯度来进行计算。从实施方案中避免所选物料分类、物料报价单位不一致，产品不做强约束。
 * 折扣形式：金额/单价。金额，
 * 表示折扣的形式是对采购订单价税合计进行按百分比的折扣；单价，表示折扣的形式是对采购订单含税净价进行按百分比或者金额数值来进行折扣。
 * <ul>
 * <li>折扣形式为金额时：
 * <ul>
 * <li>折扣后的价税合计=折扣前价税合计*金额百分比
 * <li>折扣后的价税合计/数量=含税净价
 * <li>含税净价/单价=折扣
 * <li>回写采购订单行：价税合计、含税净价、折扣
 * <li>如果是组合折扣，该组的最后一条订单行处理折扣后的价税合计的尾差。
 * </ul>
 * <li>折扣形式为单价时-如果调单价百分比：
 * <ul>
 * <li>折扣后的含税净价=单价*单价百分比
 * <li>含税净价/单价=折扣
 * <li>回写采购订单行：含税净价、折扣
 * </ul>
 * <li>折扣形式为单价时-如果调单价数值：
 * <ul>
 * <li>折扣后的含税净价=单价-单价数值
 * <li>含税净价/单价=折扣
 * <li>回写采购订单行：含税净价、折扣
 * </ul>
 * </ul>
 * <li>处理完毕后，订单保存成功。
 * </ol>
 * </ul>
 * 
 * @author liuyand
 */
public class DiscountSetterRule implements IRule<AggregatedValueObject> {

	public static final String FIELD_SAPARATER = "_";

	@Override
	public void process(AggregatedValueObject[] aggvos) {
		OrderVO[] vos = ArrayUtil.convertArrayType(aggvos);
		// 检查金额字段是否被编辑
		try {
			this.checkMnyField(vos);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		// 获取需要处理折扣的所有单据的表体行VO
		List<OrderItemVO> needDiscountItems = this.getNeedDiscountItems(vos);
		if (needDiscountItems.size() == 0) {
			return;
		}
		// 为表体冗余字段赋值
		this.fillBodyAsHead(vos);

		// 关联合同的表体行记录列表
		List<OrderItemVO> ctItems = new ArrayList<OrderItemVO>();
		// 未关联合同的表体行记录列表
		List<OrderItemVO> nonCtItems = new ArrayList<OrderItemVO>();
		// 为关联合同和非关联合同列表赋值
		this.fillCtItemList(ctItems, nonCtItems, needDiscountItems);
		// 取合同信息
		Map<String, CtPubillViewVO> ctmap = this.getCtInfo(ctItems);

		// 取折扣信息
		DiscountQryResultVO[] qryResultVOs = this.fetch(ctItems, nonCtItems, ctmap);
		// 设置折扣信息
		this.fill(vos, qryResultVOs, ctmap);
	}

	/**
	 * 添加匹配到折扣规则的采购订单表体行VO到Map中
	 * 
	 * @param relCalInfoMap
	 *          联动计算信息Map
	 * @param groupInfoMap
	 *          组合折扣订单VO关系信息Map
	 * @param groupResultVOMap
	 *          组合折扣档案信息Map
	 * @param scale
	 *          精度
	 * @param currid
	 *          币种
	 * @param itemVO
	 *          采购订单表体VO
	 * @param qryResultVO
	 *          折扣档案VO
	 */
	private void addMatchedItemsToMap(MapList<String, OrderItemVO> relCalInfoMap,
			Map<String, List<OrderItemVO>> groupInfoMap,
			Map<String, DiscountQryResultVO> groupResultVOMap, ScaleUtils scale,
			String currid, OrderItemVO itemVO, DiscountQryResultVO qryResultVO) {
		// 如果是组合折扣-构造组合折扣分组信息。组合+金额，需要处理尾差，其他不需要，可按单品折扣处理
		if (StringUtils.isNotBlank(qryResultVO.getVdiscGroupCode())
				&& DisntTypeDetailEnum.MNYPERSENT.getNum().intValue() == qryResultVO
						.getFtype().intValue()) {
			String groupKey = null;
			if (DiscountConditionEnum.NUM.toInt() == qryResultVO.getFcondition()
					.intValue()) {
				groupKey = qryResultVO.getPk_discount()
						+ DiscountSetterRule.FIELD_SAPARATER
						+ qryResultVO.getVdiscGroupCode()
						+ qryResultVO.getDiscountQryVO().getCqtunitid();
			} else {
				groupKey = qryResultVO.getPk_discount()
						+ DiscountSetterRule.FIELD_SAPARATER
						+ qryResultVO.getVdiscGroupCode();
			}
			if (groupInfoMap.containsKey(groupKey)) {
				groupInfoMap.get(groupKey).add(itemVO);
			} else {
				List<OrderItemVO> orderItems = new ArrayList<OrderItemVO>();
				orderItems.add(itemVO);
				groupInfoMap.put(groupKey, orderItems);
			}
			// 加入分组折扣信息的缓存Map中，为后继处理组合折扣用
			if (!groupResultVOMap.containsKey(groupKey)) {
				groupResultVOMap.put(groupKey, qryResultVO);
			}
			relCalInfoMap.put(OrderItemVO.NORIGTAXMNY, itemVO);
		} else {
			// 如果是单品折扣
			// if(单品+其他类型的组合折扣)，不需要处理尾差，直接处理
			if (DisntTypeDetailEnum.MNYPERSENT.getNum().intValue() == qryResultVO
					.getFtype().intValue()) {
				// 折扣后的价税合计=折扣前价税合计*金额百分比 （处理精度），价税合计=含税单价*数量，保证每次询折扣的基数一致
				UFDouble disNorigtaxmny = this.getSrcNorigtaxmny(itemVO).multiply(
						qryResultVO.getDiscountValue());
				itemVO.setNorigtaxmny(scale.adjustMnyScale(disNorigtaxmny, currid));
				relCalInfoMap.put(OrderItemVO.NORIGTAXMNY, itemVO);
			} else {
				String keyItem = null;
				if (DisntTypeDetailEnum.PRICEPERSENT.getNum().intValue() == qryResultVO
						.getFtype().intValue()) {
					itemVO.setNitemdiscountrate(qryResultVO.getDiscountValue().multiply(
							new UFDouble(100.0f)));
					keyItem = OrderItemVO.NQTORIGTAXPRICE;
				} else if (DisntTypeDetailEnum.PRICE.getNum().intValue() == qryResultVO
						.getFtype().intValue()) {
					// 折扣后的含税净价=单价-单价数值
					UFDouble nqtorigtaxnetprc = itemVO.getNqtorigtaxprice().add(
							qryResultVO.getDiscountValue());
					itemVO.setNqtorigtaxnetprc(scale.adjustSoPuPriceScale(
							nqtorigtaxnetprc, currid));
					keyItem = OrderItemVO.NQTORIGTAXNETPRC;
				}
				relCalInfoMap.put(keyItem, itemVO);
			}
		}
		itemVO.setPk_discount(qryResultVO.getPk_discount()); // 设置匹配的折扣规则主键
	}

	/**
	 * 添加未匹配到折扣规则的采购订单表体行VO到Map中。清空折扣规则主键
	 * 
	 * @param items
	 * @param relCalInfoMap
	 */
	private void addNotMatchedItemsToMap(List<OrderItemVO> items,
			MapList<String, OrderItemVO> relCalInfoMap) {
		for (OrderItemVO itemVO : items) {
			// 之前没有匹配过折扣，不联动计算
			if (StringUtils.isBlank(itemVO.getPk_discount())) {
				continue;
			}
			itemVO.setNqtorigtaxnetprc(itemVO.getNqtorigtaxprice());
			itemVO.setNitemdiscountrate(new UFDouble(100.00));
			itemVO.setPk_discount(null); // 清空折扣规则主键
			relCalInfoMap.put(OrderItemVO.NQTORIGTAXNETPRC, itemVO);
		}
	}

	/**
	 * 检查金额字段是否被编辑
	 * 
	 * @param vos
	 * @throws BusinessException
	 */
	private void checkMnyField(OrderVO[] vos) throws BusinessException {
		// 由于校验非编辑字段是否被修改只涉及页面操作的情况，这种场景下只有一个单据；批量推单都是新增，不需要校验
		if (vos.length < 1 || vos.length > 1) {
			return;
		}
		OrderVO vo = vos[0];
		// 已保存的采购订单表体行VO
		Map<String, OrderItemVO> savedItems = new HashMap<String, OrderItemVO>();
		for (OrderItemVO itemVO : vo.getBVO()) {
			if (StringUtils.isNotBlank(itemVO.getPk_order_b())) {
				savedItems.put(itemVO.getPk_order_b(), itemVO);
			}
		}
		// 如果没有已保存的表体行数据（场景：新增单据保存）
		if (savedItems.size() == 0) {
			return;
		}

		Set<String> pk_order_bs = savedItems.keySet();
		OrderItemVO[] itemVOsInDb = new VOQuery<OrderItemVO>(OrderItemVO.class)
				.query(pk_order_bs.toArray(new String[pk_order_bs.size()]));

		List<String> modifiedRowNos = new ArrayList<String>();
		for (OrderItemVO itemVOInDb : itemVOsInDb) {
			OrderItemVO itemVO = savedItems.get(itemVOInDb.getPk_order_b());
			if (this.checkNotEditableMoneyField(itemVO, itemVOInDb)) {
				modifiedRowNos.add(itemVO.getCrowno());
			}
		}

		if (modifiedRowNos.size() > 0) {
			Collections.sort(modifiedRowNos);
			StringBuffer rowNos = new StringBuffer();
			for (int i = 0; i < modifiedRowNos.size(); i++) {
				rowNos.append("[").append(modifiedRowNos.get(i)).append("]");
			}
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4004030_0", "04004030-0363", null,
							new String[] { rowNos.toString() })/*
																									 * @res
																									 * "采购订单[][][]行已询过折扣，折扣不允许编辑！"
																									 */);
		}
	}

	/**
	 * 检查页面非编辑字段是否和数据库中已保存值相同
	 * 
	 * @param itemVO
	 * @param itemVOInDb
	 * @return true-不允许修改但已被修改，false-未修改
	 */
	private boolean checkNotEditableMoneyField(OrderItemVO itemVO,
			OrderItemVO itemVOInDb) {
		if (itemVO == null) {
			return false;
		}
		if (StringUtils.isNotBlank(itemVO.getPk_discount())) {
			if (!itemVOInDb.getNitemdiscountrate().equals(
					itemVO.getNitemdiscountrate())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查询对应的规则
	 */
	private DiscountQryResultVO[] fetch(List<OrderItemVO> ctItems,
			List<OrderItemVO> nonCtItems, Map<String, CtPubillViewVO> ctmap) {
		// 获取查询条件QueryVO
		List<DiscountQueryVO> queryVOList = new ArrayList<DiscountQueryVO>();
		queryVOList.addAll(this.getQueryVOList(ctItems, true, ctmap));
		queryVOList.addAll(this.getQueryVOList(nonCtItems, false, null));

		IQueryDiscountService service = NCLocator.getInstance().lookup(
				IQueryDiscountService.class);
		DiscountQryResultVO[] resultVOs = null;
		try {
			resultVOs = service.queryDiscountForPub(queryVOList
					.toArray(new DiscountQueryVO[queryVOList.size()]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}

		return resultVOs;
	}

	/**
	 * 设置单个采购订单的折扣信息
	 * 
	 * @param vo
	 * @param qryResultVOs
	 * @param ctmap
	 */
	private void fill(OrderVO vo, DiscountQryResultVO[] qryResultVOs,
			Map<String, CtPubillViewVO> ctmap) {
		List<OrderItemVO> needDiscountItems = this
				.getNeedDiscountItems(new OrderVO[] { vo });
		// 匹配到折扣规则的采购订单表体VO
		List<OrderItemVO> matchedItems = new ArrayList<OrderItemVO>();
		// 未匹配到折扣规则的采购订单表体VO
		List<OrderItemVO> nonMatchedItems = new ArrayList<OrderItemVO>();
		// 需要联动计算的Map
		MapList<String, OrderItemVO> relCalInfoMap = new MapList<String, OrderItemVO>();

		// 组合折扣信息：key-组合折扣主键_组合编码，val-采购订单表体行VO列表
		Map<String, List<OrderItemVO>> groupInfoMap = new HashMap<String, List<OrderItemVO>>();

		// 组合折扣档案信息Map
		Map<String, DiscountQryResultVO> groupResultVOMap = new HashMap<String, DiscountQryResultVO>();

		// 处理精度
		ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
		String currid = vo.getHVO().getCorigcurrencyid();

		for (OrderItemVO itemVO : needDiscountItems) {
			// 是否匹配上折扣规则
			boolean isMatch = false;
			DiscountQryResultVO qryResultVO = null;
			for (DiscountQryResultVO resultVO : qryResultVOs) {
				// 折扣信息为空，不处理
				if (StringUtils.isBlank(resultVO.getPk_discount())) {
					continue;
				}
				DiscountQueryVO qryVO = resultVO.getDiscountQryVO();
				if (this.isMatch(itemVO, qryVO, ctmap)) {
					isMatch = true;
					qryResultVO = resultVO;
					break;
				}
			}

			if (isMatch && qryResultVO != null) {
				this.addMatchedItemsToMap(relCalInfoMap, groupInfoMap,
						groupResultVOMap, scale, currid, itemVO, qryResultVO);
				// 处理完的从待处理列表中去掉，最终在列表中的都是本次匹配时未匹配到折扣规则的采购订单表体行VO
				matchedItems.add(itemVO);
			} else {
				nonMatchedItems.add(itemVO);
			}
		}

		// 处理未匹配到折扣规则的采购订单表体行记录：清空折扣规则主键
		this.addNotMatchedItemsToMap(nonMatchedItems, relCalInfoMap);

		// 处理组合折扣，处理尾差
		this.fillGroupItemField(vo, needDiscountItems, groupInfoMap,
				groupResultVOMap, scale, currid);

		// 联动计算
		this.relationCalc(vo.getHVO(), relCalInfoMap);
	}

	/**
	 * 设置折扣信息
	 * 
	 * @param vos
	 * @param qryResultVOs
	 * @param ctmap
	 */
	private void fill(OrderVO[] vos, DiscountQryResultVO[] qryResultVOs,
			Map<String, CtPubillViewVO> ctmap) {
		for (OrderVO vo : vos) {
			if (this.isBodyEmpty(vo)) {
				continue;
			}
			this.fill(vo, qryResultVOs, ctmap);
		}
	}

	/**
	 * 为表体冗余字段赋值 由于表体冗余字段在保存前赋值，新增的表体冗余字段在校验时刻值为空，为校验先将冗余字段赋值
	 * 
	 * @param vos
	 */

	private void fillBodyAsHead(OrderVO[] vos) {
		for (OrderVO orderVO : vos) {
			OrderHeaderVO hvo = orderVO.getHVO();
			for (OrderItemVO itemVO : orderVO.getBVO()) {
				itemVO.setCorigcurrencyid(hvo.getCorigcurrencyid());
				itemVO.setPk_supplier(hvo.getPk_supplier());
				itemVO.setDbilldate(hvo.getDbilldate());
			}
		}
	}

	/**
	 * 为关联合同和非关联合同列表赋值。根据需要处理的采购订单表体VO列表
	 * 
	 * @param ctItems
	 *          关联合同
	 * @param nonCtItems
	 *          非关联合同
	 * @param needDiscountItems
	 *          需要处理的采购订单表体VO列表
	 */
	private void fillCtItemList(List<OrderItemVO> ctItems,
			List<OrderItemVO> nonCtItems, List<OrderItemVO> needDiscountItems) {
		for (OrderItemVO itemVO : needDiscountItems) {
			if (StringUtils.isNotBlank(itemVO.getCcontractrowid())) {
				ctItems.add(itemVO);
			} else {
				nonCtItems.add(itemVO);
			}
		}
	}

	/**
	 * 处理单个采购订单中各组合折扣的表体行VO赋值
	 * 
	 * @param vo
	 * @param needDiscountItems
	 * @param groupInfoMap
	 * @param groupResultVOMap
	 * @param scale
	 * @param currid
	 */
	private void fillGroupItemField(OrderVO vo,
			List<OrderItemVO> needDiscountItems,
			Map<String, List<OrderItemVO>> groupInfoMap,
			Map<String, DiscountQryResultVO> groupResultVOMap, ScaleUtils scale,
			String currid) {
		if (groupInfoMap.size() == 0) {
			return;
		}

		for (Entry<String, List<OrderItemVO>> entry : groupInfoMap.entrySet()) {
			List<OrderItemVO> sortedItemsInBill = this.getSortedItemsInBill(vo,
					entry.getValue());
			DiscountQryResultVO qryResultVO = groupResultVOMap.get(entry.getKey());
			this.fillSingleGroupItemField(sortedItemsInBill, qryResultVO, scale,
					currid);
		}

	}

	/**
	 * 处理单个组合折扣的采购订单表体行VO赋值
	 * 
	 * @param itemVOs
	 * @param qryResultVO
	 * @param scale
	 * @param currid
	 */
	private void fillSingleGroupItemField(List<OrderItemVO> itemVOs,
			DiscountQryResultVO qryResultVO, ScaleUtils scale, String currid) {
		// 总价税合计
		UFDouble sumNorigtaxmny = UFDouble.ZERO_DBL;
		// 总价税合计-折扣后
		UFDouble sumNorigtaxmnyDis = UFDouble.ZERO_DBL;
		// 价税合计-折扣后
		UFDouble norigtaxmnyDis = UFDouble.ZERO_DBL;
		int rowIndex = 0;

		for (OrderItemVO itemVO : itemVOs) {
			UFDouble srcNorigtaxmny = this.getSrcNorigtaxmny(itemVO);
			sumNorigtaxmny = sumNorigtaxmny.add(srcNorigtaxmny);
			norigtaxmnyDis = scale.adjustMnyScale(
					srcNorigtaxmny.multiply(qryResultVO.getDiscountValue()), currid);
			// 最后一行处理尾差。最后一行的价税合计=总价税合计*折扣系数-之前各行价税合计-折扣后的和
			if (rowIndex == itemVOs.size() - 1) {
				itemVO.setNorigtaxmny(scale.adjustMnyScale(
						sumNorigtaxmny.multiply(qryResultVO.getDiscountValue()), currid)
						.sub(sumNorigtaxmnyDis));
			} else {
				itemVO.setNorigtaxmny(norigtaxmnyDis);
				sumNorigtaxmnyDis = sumNorigtaxmnyDis.add(norigtaxmnyDis);
				rowIndex++;
			}
		}
	}

	/**
	 * 取合同信息
	 * 
	 * @param ctItems
	 * @return
	 */
	private Map<String, CtPubillViewVO> getCtInfo(List<OrderItemVO> ctItems) {
		if (ctItems == null || ctItems.size() == 0) {
			return null;
		}

		Set<String> ccontractids = new HashSet<String>();
		for (OrderItemVO itemVO : ctItems) {
			ccontractids.add(itemVO.getCcontractrowid());
		}

		return Z2CTServices.queryViewVOsByBPks(ccontractids
				.toArray(new String[ccontractids.size()]));
	}

	/**
	 * 获取需要处理折扣规则的采购订单表体行记录列表
	 * 
	 * @param vos
	 *          采购订单聚合VO数组
	 * @return 需要处理折扣规则的表体行记录Map，key-采购订单表体行主键，value-采购订单表体行
	 */
	private List<OrderItemVO> getNeedDiscountItems(OrderVO[] vos) {
		List<OrderItemVO> needDiscountItems = new ArrayList<OrderItemVO>();
		for (OrderVO vo : vos) {
			if (this.isBodyEmpty(vo)) {
				continue;
			}
			for (OrderItemVO itemVO : vo.getBVO()) {
				if (this.isNeedDiscount(itemVO)) {
					needDiscountItems.add(itemVO);
				}
			}
		}
		return needDiscountItems;
	}

	/**
	 * 获取匹配折扣条件列表
	 * 
	 * @param items
	 *          采购订单表体行VO列表
	 * @param isCt
	 *          是否关联合同
	 * @param ctmap
	 *          关联的合同信息
	 * @return
	 */
	private List<DiscountQueryVO> getQueryVOList(List<OrderItemVO> items,
			boolean isCt, Map<String, CtPubillViewVO> ctmap) {
		List<DiscountQueryVO> queryVOs = new ArrayList<DiscountQueryVO>();
		for (OrderItemVO itemVO : items) {
			DiscountQueryVO qryVO = new DiscountQueryVO();
			qryVO.setCorigcurrencyid(itemVO.getCorigcurrencyid());
			qryVO.setDbilldate(itemVO.getDbilldate());
			qryVO.setNastnum(itemVO.getNastnum());
			qryVO.setCqtunitid(itemVO.getCqtunitid());
			// 价税合计=含税单价*数量，保证每次询折扣的基数一致
			qryVO.setNmoney(this.getSrcNorigtaxmny(itemVO));
			qryVO.setPk_srcmaterial(itemVO.getPk_srcmaterial());
			qryVO.setPk_supplier(itemVO.getPk_supplier());
			if (isCt && ctmap != null
					&& ctmap.containsKey(itemVO.getCcontractrowid())) {
				qryVO.setMatchPk_org(itemVO.getPk_org());
				qryVO.setPk_org(ctmap.get(itemVO.getCcontractrowid()).getPk_org());
			} else {
				// 不关联合同只赋主组织（采购折扣接口约定）
				qryVO.setPk_org(itemVO.getPk_org());
			}

			queryVOs.add(qryVO);
		}

		return queryVOs;
	}

	/**
	 * 获取排序的分组折扣信息。按订单表体行顺序排序
	 * 
	 * @param vo
	 * @param groupInfo
	 * @return
	 */
	private List<OrderItemVO> getSortedItemsInBill(OrderVO vo,
			List<OrderItemVO> items) {
		List<OrderItemVO> temp = new LinkedList<OrderItemVO>();
		temp.addAll(items);
		List<OrderItemVO> sortedItems = new ArrayList<OrderItemVO>();
		for (OrderItemVO itemVOInBill : vo.getBVO()) {
			for (OrderItemVO itemVOInList : temp) {
				// 这一步的match，如果维度相同可能会匹配错误。
				// 所以在上面构造一个临时的list，如果曾经匹配过，就删除它，避免后续维度相同的项匹配错误
				if (this.isMatch(itemVOInBill, itemVOInList)) {
					sortedItems.add(itemVOInList);
					temp.remove(itemVOInList);
					break;
				}
			}
		}
		return sortedItems;
	}

	/**
	 * 获取原价税合计
	 * 
	 * @param itemVO
	 * @return
	 */
	private UFDouble getSrcNorigtaxmny(OrderItemVO itemVO) {
		if (itemVO == null) {
			return UFDouble.ZERO_DBL;
		}
		// if (MathTool.compareTo(itemVO.getNitemdiscountrate(), new UFDouble(100))
		// == 0) {
		// return itemVO.getNorigtaxmny();
		// }
		// return itemVO.getNqtorigtaxprice().multiply(itemVO.getNastnum());
		// 根据步步设位原则，直接用已经处理的金额
		return itemVO.getNorigtaxmny();
	}

	/**
	 * 判断采购订单表体行是否为空
	 * 
	 * @param vo
	 * @return true-表体行为空， false-表体行不为空
	 */
	private boolean isBodyEmpty(OrderVO vo) {
		if (vo == null || vo.getBVO() == null || vo.getBVO().length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 当前采购订单表体VO是否和折扣规则查询条件匹配。因为保存前函数无法通过Pk匹配。
	 * 
	 * @param itemVO
	 * @param qryVO
	 * @param ctmap
	 * @return
	 */
	private boolean isMatch(OrderItemVO itemVO, DiscountQueryVO qryVO,
			Map<String, CtPubillViewVO> ctmap) {
		if (itemVO == null || qryVO == null) {
			return false;
		}
		boolean isMatch = false;
		if (itemVO.getCorigcurrencyid().equals(qryVO.getCorigcurrencyid())
				&& itemVO.getDbilldate().equals(qryVO.getDbilldate())
				&& itemVO.getNastnum().equals(qryVO.getNastnum())
				&& itemVO.getCqtunitid().equals(qryVO.getCqtunitid())
				&& this.getSrcNorigtaxmny(itemVO).equals(qryVO.getNmoney())
				&& itemVO.getPk_srcmaterial().equals(qryVO.getPk_srcmaterial())
				&& itemVO.getPk_supplier().equals(qryVO.getPk_supplier())) {
			// 如果关联合同
			if (StringUtils.isNotBlank(itemVO.getCcontractrowid()) && ctmap != null
					&& ctmap.containsKey(itemVO.getCcontractrowid())) {
				if (itemVO.getPk_org().equals(qryVO.getMatchPk_org())
						&& ctmap.get(itemVO.getCcontractrowid()).getPk_org()
								.equals(qryVO.getPk_org())) {
					isMatch = true;
				}
			} else {
				if (itemVO.getPk_org().equals(qryVO.getPk_org())) {
					isMatch = true;
				}
			}
		}
		return isMatch;
	}

	/**
	 * 表体VO是否匹配
	 * 
	 * @param itemVO1
	 * @param itemVO2
	 * @return
	 */
	private boolean isMatch(OrderItemVO itemVO1, OrderItemVO itemVO2) {
		if (itemVO1 == null || itemVO2 == null) {
			return false;
		}
		// 如果表体行VO有主见并相同
		if (StringUtils.isNotBlank(itemVO1.getPk_order_b())
				&& itemVO1.getPk_order_b().equals(itemVO2.getPk_order_b())) {
			return true;
		}
		// 非空字段
		List<String> compareFieldKeys = new ArrayList<String>();
		compareFieldKeys.add(OrderItemVO.CORIGCURRENCYID);
		compareFieldKeys.add(OrderItemVO.DBILLDATE);
		compareFieldKeys.add(OrderItemVO.NASTNUM);
		compareFieldKeys.add(OrderItemVO.CQTUNITID);
		compareFieldKeys.add(OrderItemVO.PK_SRCMATERIAL);
		compareFieldKeys.add(OrderItemVO.PK_SUPPLIER);
		compareFieldKeys.add(OrderItemVO.PK_ORG);
		for (String key : compareFieldKeys) {
			if (!itemVO1.getAttributeValue(key)
					.equals(itemVO2.getAttributeValue(key))) {
				return false;
			}
		}
		// 取原字段值比较
		if (!this.getSrcNorigtaxmny(itemVO1)
				.equals(this.getSrcNorigtaxmny(itemVO2))) {
			return false;
		}
		// 可能为空的字段
		if (!StringUtils.equals(itemVO1.getCcontractrowid(),
				itemVO2.getCcontractrowid())) {
			return false;
		}
		return true;
	}

	/**
	 * 当前订单表体行是否需要处理折扣
	 * 
	 * @param itemVO
	 *          订单表体行VO
	 * @return
	 */
	private boolean isNeedDiscount(OrderItemVO itemVO) {
		// 表体行为空不处理
		if (itemVO == null) {
			return false;
		}
		// 删除的行不需询折扣
		if (VOStatus.DELETED == itemVO.getStatus()) {
			return false;
		}
		// 赠品行不询折扣规则
		if (UFBoolean.TRUE.equals(itemVO.getBlargess())) {
			return false;
		}
		// 询过折扣的，修改时候重新询折扣
		if (StringUtils.isNotBlank(itemVO.getPk_discount())
				&& VOStatus.UPDATED == itemVO.getStatus()) {
			return true;
		}
		// 如果订单行手工录入了折扣或手工维护了净价导致净价<>单价且折扣规则编码为空，则该订单行不再询折扣规则（金额：含税净价、含税单价）
		if (!itemVO.getNqtorigtaxprice().equals(itemVO.getNqtorigtaxnetprc())) {
			return false;
		}

		return true;
	}

	/**
	 * 标记没有修改采购订单表体VO状态为修改。因为每次保存都要重新询折扣，对于不是修改状态的，不做更新。只针对已保存过的表体行。
	 * 
	 * @param itemVOs
	 */
	private void markItemVOsUpdate(List<OrderItemVO> itemVOs) {
		for (OrderItemVO itemVO : itemVOs) {
			if (VOStatus.UNCHANGED == itemVO.getStatus()) {
				itemVO.setStatus(VOStatus.UPDATED);
			}
		}
	}

	/**
	 * 联动计算
	 * 
	 * @param relCalInfoMap
	 */
	private void relationCalc(OrderHeaderVO headerVO,
			MapList<String, OrderItemVO> relCalInfoMap) {
		if (relCalInfoMap.size() == 0) {
			return;
		}

		for (String itemKey : relCalInfoMap.keySet()) {
			List<OrderItemVO> itemVOs = relCalInfoMap.get(itemKey);
			// 标记没有修改采购订单表体VO状态为修改
			this.markItemVOsUpdate(itemVOs);

			// 联动计算用的采购订单实体（只包含需要处理的表体行）
			OrderVO orderVOCalc = new OrderVO();
			orderVOCalc.setHVO(headerVO);
			orderVOCalc.setBVO(itemVOs.toArray(new OrderItemVO[itemVOs.size()]));

			RelationCalculate calc = new RelationCalculate();
			boolean bdiscount = true;
			// 如果以含税单价发起联动计算，不算折扣；否则计算折扣
			if (OrderItemVO.NQTORIGTAXPRICE.equals(itemKey)) {
				bdiscount = false;
			}
			calc.calculate(orderVOCalc, itemKey, true, bdiscount);
		}
	}
}

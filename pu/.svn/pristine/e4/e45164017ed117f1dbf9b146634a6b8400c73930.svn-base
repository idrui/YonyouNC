
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description <ul>
 *              <li>非空检查
 *              <li>表头非空:主组织,所属集团,采购部门， 期初暂估类型, 库存组织, 成本域,币种，本币币种，单据日期
 *              <li>表体非空:行号,主组织,所属集团,应付组织,物料VID,物料OID,主计量单位,单位
 *              </ul>
 * @scene
 * 
 * @param 无
 * 
 * @since 6.3
 * @version 2010-9-8 上午10:02:19
 * @author wuxla
 */

public class InitialEstNotNullChkRule implements IRule<InitialEstVO> {

	static class ItemChkInfo {

		private String itemCode;

		private String itemName;

		ItemChkInfo(String itemCode, String itemName) {
			this.itemCode = itemCode;
			this.itemName = itemName;
		}

		public String getItemCode() {
			return this.itemCode;
		}

		public String getItemName() {
			return this.itemName;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
	}

	
	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		ItemChkInfo[] headInfos = this.getHeadInfos();
		ItemChkInfo[] itemInfos = this.getItemInfos();

		for (InitialEstVO vo : vos) {
			this.checkEmpty(vo, headInfos, itemInfos);
		}
	}

	/**
	 * 方法功能描述：检查表体
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @param itemInfos
	 * @param sb
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午03:33:54
	 */
	private void checkBodyItems(InitialEstVO vo, ItemChkInfo[] itemInfos,
			StringBuilder sb) {
		InitialEstItemVO[] itemVOs = vo.getItems();
		if (ArrayUtils.isEmpty(itemVOs)) {
			return;
		}

		for (InitialEstItemVO itemVO : itemVOs) {
			StringBuilder itemBuilder = new StringBuilder();
			for (int i = 0; i < itemInfos.length; ++i) {
				if (itemVO.getAttributeValue(itemInfos[i].getItemCode()) != null) {
					continue;
				}

				itemBuilder.append(itemInfos[i].getItemName()).append(",");
			}

			if (itemBuilder.length() > 0) {
				itemBuilder.deleteCharAt(itemBuilder.length() - 1);
				sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0",
						"04004060-0230",
						null,
						new String[] { itemVO.getCrowno(),
								itemBuilder.toString() })/*
														 * 表体第{0}行的以下字段不允许为空：{1}\
														 * n
														 */);
			}
		}
	}

	/**
	 * 方法功能描述：检查非空项
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @param headInfos
	 * @param itemInfos
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午06:52:14
	 */
	private void checkEmpty(InitialEstVO vo, ItemChkInfo[] headInfos,
			ItemChkInfo[] itemInfos) {
		StringBuilder sb = new StringBuilder();
		this.checkHeadItems(vo, headInfos, sb);
		this.checkBodyItems(vo, itemInfos, sb);

		if (sb.length() > 0) {
			ExceptionUtils.wrappBusinessException(sb.toString());
		}
	}

	/**
	 * 方法功能描述：检查表头
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @param headInfos
	 * @param sb
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午06:51:59
	 */
	private void checkHeadItems(InitialEstVO vo, ItemChkInfo[] headInfos,
			StringBuilder sb) {
		StringBuilder headBuilder = new StringBuilder();
		InitialEstHeaderVO headerVO = vo.getHeader();
		for (int i = 0; i < headInfos.length; ++i) {
			if (headerVO.getAttributeValue(headInfos[i].getItemCode()) != null) {
				continue;
			}

			headBuilder.append(headInfos[i].getItemName()).append(",");
		}

		if (headBuilder.length() > 0) {
			headBuilder.deleteCharAt(headBuilder.length() - 1);
			sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4004060_0", "04004060-0174")/* @res "表头以下字段不允许为空：" */
					+ headBuilder.toString() + "\n");
		}
	}

	/**
	 * 方法功能描述：表头非空项:主组织,所属集团,采购部门， 期初暂估类型, 库存组织, 成本域,币种，本币币种，单据日期
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午06:51:48
	 */
	private ItemChkInfo[] getHeadInfos() {
		ItemChkInfo[] headInfos = new ItemChkInfo[13];
		headInfos[0] = new ItemChkInfo(InitialEstHeaderVO.PK_ORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0175")/*
													 * @res "财务组织版本"
													 */);
		headInfos[1] = new ItemChkInfo(InitialEstHeaderVO.PK_ORG_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0176")/* @res "财务组织" */);
		headInfos[2] = new ItemChkInfo(InitialEstHeaderVO.PK_GROUP,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC001-0000072")/* @res "集团" */);
		headInfos[3] = new ItemChkInfo(InitialEstHeaderVO.PK_DEPT,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0004099")/* @res "采购部门" */);
		headInfos[4] = new ItemChkInfo(InitialEstHeaderVO.PK_DEPT_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0004099")/* @res "采购部门" */);
		headInfos[5] = new ItemChkInfo(InitialEstHeaderVO.VTRANTYPECODE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0177")/* @res "期初暂估类型" */);
		headInfos[6] = new ItemChkInfo(InitialEstHeaderVO.PK_STOCKORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0178")/* @res "库存组织版本" */);
		headInfos[7] = new ItemChkInfo(InitialEstHeaderVO.PK_STOCKORG_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001825")/* @res "库存组织" */);
		headInfos[8] = new ItemChkInfo(InitialEstHeaderVO.PK_COSTREGION,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0179")/* @res "成本域" */);
		headInfos[9] = new ItemChkInfo(InitialEstHeaderVO.CORIGCURRENCYID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001755")/* @res "币种" */);
		headInfos[10] = new ItemChkInfo(InitialEstHeaderVO.CCURRENCYID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0180")/* @res "本位币" */);
		headInfos[11] = new ItemChkInfo(InitialEstHeaderVO.DBILLDATE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000799")/* @res "单据日期" */);
		headInfos[12] = new ItemChkInfo(InitialEstHeaderVO.PK_SUPPLIER,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000275")/* @res "供应商" */);
		// 添加元素注意该数组长度
		return headInfos;
	}

	/**
	 * 方法功能描述：表体非空项:行号,主组织,所属集团,应付组织,物料VID,物料OID,主计量单位,单位
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午06:51:36
	 */
	private ItemChkInfo[] getItemInfos() {
		ItemChkInfo[] itemInfos = new ItemChkInfo[] {
				new ItemChkInfo(InitialEstItemVO.CROWNO,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC000-0003389")/* @res "行号" */),
				new ItemChkInfo(InitialEstItemVO.PK_ORG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0175")/*
															 * @res "财务组织版本"
															 */),
				new ItemChkInfo(InitialEstItemVO.PK_ORG_V,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0176")/* @res "财务组织" */),
				new ItemChkInfo(InitialEstItemVO.PK_GROUP,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC001-0000072")/* @res "集团" */),
				new ItemChkInfo(InitialEstItemVO.PK_APFINANCEORG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0181")/* @res "应付组织版本" */),
				new ItemChkInfo(InitialEstItemVO.PK_APFINANCEORG_V,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0182")/* @res "应付组织" */),
				new ItemChkInfo(InitialEstItemVO.PK_MATERIAL,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC000-0002911")/* @res "物料编码" */),
				new ItemChkInfo(InitialEstItemVO.PK_SRCMATERIAL,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0183")/* @res "物料版本" */),
				new ItemChkInfo(InitialEstItemVO.CASTUNITID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0184")/* @res "单位" */),
				new ItemChkInfo(InitialEstItemVO.CUNITID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0185")/*
															 * @res "主单位"
															 */),
				new ItemChkInfo(InitialEstItemVO.NNUM,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0247")/*
															 * @res "主数量"
															 */),
				new ItemChkInfo(InitialEstItemVO.NASTNUM,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0246")/*
															 * @res "数量"
															 */),
				new ItemChkInfo(InitialEstItemVO.VCHANGERATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0248")/* @res "换算率" */),
				new ItemChkInfo(InitialEstItemVO.CRECECOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0005")/* @res "收货国家/地区" */),
				new ItemChkInfo(InitialEstItemVO.CSENDCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0006")/* @res "发货国家/地区" */),
				new ItemChkInfo(InitialEstItemVO.CTAXCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0007")/* @res "报税国家/地区" */),
				new ItemChkInfo(InitialEstItemVO.FBUYSELLFLAG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0008")/* @res "购销类型" */),

				new ItemChkInfo(InitialEstItemVO.CTAXCODEID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0009")/* @res "税码" */),
				new ItemChkInfo(InitialEstItemVO.CSENDCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0010")/* @res "扣税类别区" */),
				new ItemChkInfo(InitialEstItemVO.VCHANGERATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0011")/* @res "换算率" */) };
		return itemInfos;
	}

}

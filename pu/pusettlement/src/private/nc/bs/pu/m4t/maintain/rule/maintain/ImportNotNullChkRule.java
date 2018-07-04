package nc.bs.pu.m4t.maintain.rule.maintain;

import org.apache.commons.lang.ArrayUtils;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 期初暂估导入时，必输项的校验
 * @scene
 * 期初暂估单导入
 * @param
 * 
 *
 * @since 6.3
 * @version 2015-10-12 上午11:14:52
 * @author luojw
 */
public class ImportNotNullChkRule implements IRule<InitialEstVO> {

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
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午03:33:54
	 */
	private void checkBodyItems(InitialEstVO vo, ItemChkInfo[] itemInfos,
			StringBuilder sb) {
		InitialEstItemVO[] itemVOs = vo.getItems();
		if (ArrayUtils.isEmpty(itemVOs)) {
			sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
					"04004060-0253")/*
													 * 存在表体为空的单据，导入excl文件存在标识号错误。
													 */);
			return;
		}

		for (InitialEstItemVO itemVO : itemVOs) {
			StringBuilder itemBuilder = new StringBuilder();
			for (int i = 0; i < itemInfos.length; ++i) {
				Object itemValue = itemVO.getAttributeValue(itemInfos[i].getItemCode());
				if (itemValue != null && !itemValue.toString().isEmpty()) {
					continue;
				}

				itemBuilder.append(itemInfos[i].getItemName()).append(",");
			}

			if (itemBuilder.length() > 0) {
				itemBuilder.deleteCharAt(itemBuilder.length() - 1);
				sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0230", null,
						new String[] { itemVO.getCrowno(), itemBuilder.toString() })/*
																																				 * 表体第{0}
																																				 * 行的以下字段不允许为空
																																				 * ：{1}\
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
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 下午06:52:14
	 */
	private void checkEmpty(InitialEstVO vo, ItemChkInfo[] headInfos,
			ItemChkInfo[] itemInfos) {
		StringBuilder sb = new StringBuilder();
		this.checkHeadItems(vo, headInfos, sb);
		this.checkBodyItems(vo, itemInfos, sb);
		this.checkDate(vo,sb);
		
		if (sb.length() > 0) {
			ExceptionUtils.wrappBusinessException(sb.toString());
		}
	}

	
	private void checkDate(InitialEstVO vo,StringBuilder sb){
		for(InitialEstItemVO item:vo.getItems()){
			if(item.getVchangerate().contains("-")){
				sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0254")/*
														 * 存在单据表体的换算率为负数，请检查导入的excel文件。
														 */);
				break;
			}
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
	 *          <p>
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
		ItemChkInfo[] headInfos = new ItemChkInfo[10];
		headInfos[0] = new ItemChkInfo(InitialEstHeaderVO.PK_ORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0175")/*
														 * @res "财务组织版本"
														 */);
		headInfos[1] = new ItemChkInfo(InitialEstHeaderVO.PK_DEPT_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0004099")/* @res "采购部门" */);
		headInfos[2] = new ItemChkInfo(InitialEstHeaderVO.CTRANTYPEID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0177")/* @res "期初暂估类型" */);
		headInfos[3] = new ItemChkInfo(InitialEstHeaderVO.PK_STOCKORG_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001825")/* @res "库存组织" */);
		headInfos[4] = new ItemChkInfo(InitialEstHeaderVO.PK_COSTREGION,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0179")/* @res "成本域" */);
		headInfos[5] = new ItemChkInfo(InitialEstHeaderVO.CORIGCURRENCYID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001755")/* @res "币种" */);
		headInfos[6] = new ItemChkInfo(InitialEstHeaderVO.DBILLDATE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000799")/* @res "单据日期" */);
		headInfos[7] = new ItemChkInfo(InitialEstHeaderVO.PK_SUPPLIER,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000275")/* @res "供应商" */);
		headInfos[8] = new ItemChkInfo(InitialEstHeaderVO.NEXCHANGERATE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006001_2",
						"02400600101-0027")/* @res "折本汇率" */);
		headInfos[9] = new ItemChkInfo(InitialEstHeaderVO.PK_PURCHASEORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_2",
						"2400406005-0032")/* @res "采购组织" */);
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
				new ItemChkInfo(InitialEstItemVO.CROWNO, nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("common", "UC000-0003389")/* @res "行号" */),
				new ItemChkInfo(InitialEstItemVO.PK_APFINANCEORG_V,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
								"04004060-0182")/* @res "应付组织" */),
				new ItemChkInfo(InitialEstItemVO.PK_MATERIAL,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
								"UC000-0002911")/* @res "物料编码" */),
				new ItemChkInfo(InitialEstItemVO.CASTUNITID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
								"04004060-0184")/* @res "单位" */),
				new ItemChkInfo(InitialEstItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004060_0", "04004060-0246")/*
																																		 * @res "数量"
																																		 */),
				new ItemChkInfo(InitialEstItemVO.NTAXRATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
								"2400100107-0000")/*
																 * @res "税率"
																 */),
				new ItemChkInfo(InitialEstItemVO.VCHANGERATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
								"04004060-0248")/* @res "换算率" */),
				new ItemChkInfo(InitialEstItemVO.FTAXTYPEFLAG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
								"04004000-0010")/* @res "扣税类别区" */),
				new ItemChkInfo(InitialEstItemVO.NASTORIGPRICE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
								"2400403004-0027")/*
																	 * @res "无税单价"
																	 */) };
		return itemInfos;
	}

}

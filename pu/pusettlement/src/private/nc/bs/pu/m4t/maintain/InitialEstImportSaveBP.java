package nc.bs.pu.m4t.maintain;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.rule.maintain.ImportNotNullChkRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCalCostMnyPriceRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeProcRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeUniqueRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstConfirmBusitypeRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstFillInfoRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTailItemFillRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTotalValueCalcRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.InitialEstBillDateChkRule;
import nc.vo.pu.m4t.rule.InitialEstNotNullChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueLmtChkRule;
import nc.vo.pu.m4t.rule.InitialEstRowNoChkRule;
import nc.vo.pu.m4t.rule.InitialEstScaleCheckRule;
import nc.vo.pu.pub.rule.ItemDupHeaderInfoRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

public class InitialEstImportSaveBP {
	public InitialEstVO[] save(InitialEstVO[] insertVos) {
		CompareAroundProcesser<InitialEstVO> processer = new CompareAroundProcesser<InitialEstVO>(
				InitialEstPluginPoint.SAVE_BP);
		this.addRule(processer);
		InitialEstVO[] insertedVos = null;
		processer.before(insertVos, null);
		insertedVos = new BillInsert<InitialEstVO>().insert(insertVos);
		processer.after(insertedVos, null);
		return insertedVos;
	}

	/**
	 * @param processer
	 */
	private void addRule(CompareAroundProcesser<InitialEstVO> processer) {
		//校验导入必输项是否为空
		processer.addBeforeRule(new ImportNotNullChkRule());
		// 补充单据数据信息
		processer.addBeforeRule(new InitialEstFillInfoRule());
		// 计算主组织最新版
		processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InitialEstVO>(
				InitialEstHeaderVO.PK_INITIALEST));
		// 主组织停用检查
		processer.addBeforeRule(new FinanceOrgEnableCheckRule<InitialEstVO>());
		// 表体冗余表头信息
		processer.addBeforeFinalRule(new ItemDupHeaderInfoRule<InitialEstVO>());
		// // 检查交易类型是否为空
		// processer.addBeforeFinalRule(new
		// TrantypeNotNullCheckRule<InitialEstVO>());
		// 非空检查
		processer.addBeforeRule(new InitialEstNotNullChkRule());
		// 数值型属性检查
		processer.addBeforeRule(new InitialEstNumValueChkRule());
		// 数值型属性极限值检查
		processer.addBeforeRule(new InitialEstNumValueLmtChkRule());
		// 单据日期检查
		processer.addBeforeRule(new InitialEstBillDateChkRule());
		// 行号检查
		processer.addBeforeRule(new InitialEstRowNoChkRule());
		// 精度检查
		processer.addBeforeRule(new InitialEstScaleCheckRule());
		// 表尾审计 填充
		processer.addBeforeRule(new InitialEstTailItemFillRule());
		processer.addBeforeRule(new InitialEstCalCostMnyPriceRule());
		// 表头整单合计重算
		processer.addBeforeRule(new InitialEstTotalValueCalcRule());
		// 确定业务流程
		processer.addBeforeRule(new InitialEstConfirmBusitypeRule());
		// 自由项检查
		processer.addBeforeRule(new UserDefSaveRule<InitialEstVO>(new Class[] {
				InitialEstHeaderVO.class, InitialEstItemVO.class }));
		// 单据号处理
		processer.addBeforeRule(new InitialEstCodeProcRule());
		// 单据号重复检查
		processer.addAfterRule(new InitialEstCodeUniqueRule());
	}
}

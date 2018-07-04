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
		//У�鵼��������Ƿ�Ϊ��
		processer.addBeforeRule(new ImportNotNullChkRule());
		// ���䵥��������Ϣ
		processer.addBeforeRule(new InitialEstFillInfoRule());
		// ��������֯���°�
		processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InitialEstVO>(
				InitialEstHeaderVO.PK_INITIALEST));
		// ����֯ͣ�ü��
		processer.addBeforeRule(new FinanceOrgEnableCheckRule<InitialEstVO>());
		// ���������ͷ��Ϣ
		processer.addBeforeFinalRule(new ItemDupHeaderInfoRule<InitialEstVO>());
		// // ��齻�������Ƿ�Ϊ��
		// processer.addBeforeFinalRule(new
		// TrantypeNotNullCheckRule<InitialEstVO>());
		// �ǿռ��
		processer.addBeforeRule(new InitialEstNotNullChkRule());
		// ��ֵ�����Լ��
		processer.addBeforeRule(new InitialEstNumValueChkRule());
		// ��ֵ�����Լ���ֵ���
		processer.addBeforeRule(new InitialEstNumValueLmtChkRule());
		// �������ڼ��
		processer.addBeforeRule(new InitialEstBillDateChkRule());
		// �кż��
		processer.addBeforeRule(new InitialEstRowNoChkRule());
		// ���ȼ��
		processer.addBeforeRule(new InitialEstScaleCheckRule());
		// ��β��� ���
		processer.addBeforeRule(new InitialEstTailItemFillRule());
		processer.addBeforeRule(new InitialEstCalCostMnyPriceRule());
		// ��ͷ�����ϼ�����
		processer.addBeforeRule(new InitialEstTotalValueCalcRule());
		// ȷ��ҵ������
		processer.addBeforeRule(new InitialEstConfirmBusitypeRule());
		// ��������
		processer.addBeforeRule(new UserDefSaveRule<InitialEstVO>(new Class[] {
				InitialEstHeaderVO.class, InitialEstItemVO.class }));
		// ���ݺŴ���
		processer.addBeforeRule(new InitialEstCodeProcRule());
		// ���ݺ��ظ����
		processer.addAfterRule(new InitialEstCodeUniqueRule());
	}
}

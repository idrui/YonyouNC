/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 ����10:46:09
 */
package nc.bs.pu.m4t.maintain;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstBillStatusChkRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCalCostMnyPriceRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeProcRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeUniqueRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstConfirmBusitypeRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTailItemFillRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTotalValueCalcRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.InitialEstBillDateChkRule;
import nc.vo.pu.m4t.rule.InitialEstNotNullChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueLmtChkRule;
import nc.vo.pu.m4t.rule.InitialEstRowNoChkRule;
import nc.vo.pu.m4t.rule.InitialEstScaleCheckRule;
import nc.vo.pu.m4t.rule.InitialEstSourceInfoChkRule;
import nc.vo.pu.pub.rule.ItemDupHeaderInfoRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ�������BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 ����10:46:09
 */
public class InitialEstSaveBP {
  private InitialEstContext context;

  public InitialEstSaveBP(InitialEstContext context) {
    this.context = context;
  }

  public InitialEstVO[] save(InitialEstVO[] insertVos,
      InitialEstVO[] updateVos, InitialEstVO[] updateOrgVos) {
    CompareAroundProcesser<InitialEstVO> processer =
        new CompareAroundProcesser<InitialEstVO>(InitialEstPluginPoint.SAVE_BP);
    this.addRule(processer);
    InitialEstVO[] insertedVos = null;
    if (!ArrayUtils.isEmpty(insertVos)) {
      processer.before(insertVos, null);
      insertedVos = new BillInsert<InitialEstVO>().insert(insertVos);
      processer.after(insertedVos, null);
    }
    if (!ArrayUtils.isEmpty(updateVos)) {
      processer.before(updateVos, updateOrgVos);
      InitialEstVO[] updatedVos =
          new BillUpdate<InitialEstVO>().update(updateVos, updateOrgVos);
      processer.after(updatedVos, updateOrgVos);
    }
    InitialEstVO[] savedVos = ArrayUtil.combinArrays(insertedVos, updateVos);
    return savedVos;
  }

  /**
   * @param processer
   */
  private void addRule(CompareAroundProcesser<InitialEstVO> processer) {
    // ��������֯���°�
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InitialEstVO>(
        InitialEstHeaderVO.PK_INITIALEST));
    // ����֯ͣ�ü��
    processer.addBeforeRule(new FinanceOrgEnableCheckRule<InitialEstVO>());
    // ��齻�������Ƿ�Ϊ��
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<InitialEstVO>());
    // ���������ͷ��Ϣ
    processer.addBeforeFinalRule(new ItemDupHeaderInfoRule<InitialEstVO>());
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
    // ��Ʊ��Դ��Ϣ���
    processer.addBeforeRule(new InitialEstSourceInfoChkRule());
    // ��Ʊ״̬���
    processer.addBeforeRule(new InitialEstBillStatusChkRule());
    // ��β��� ���
    processer.addBeforeRule(new InitialEstTailItemFillRule());
    processer.addBeforeRule(new InitialEstCalCostMnyPriceRule());
    // ��ͷ�����ϼ�����
    processer.addBeforeRule(new InitialEstTotalValueCalcRule());
    // ȷ��ҵ������
    processer.addBeforeRule(new InitialEstConfirmBusitypeRule());
    // ��������
    processer.addBeforeRule(new UserDefSaveRule<InitialEstVO>(new Class[] {
      InitialEstHeaderVO.class, InitialEstItemVO.class
    }));
    // ���ݺŴ���
    processer.addBeforeRule(new InitialEstCodeProcRule());
    // ���ݺ��ظ����
    processer.addAfterRule(new InitialEstCodeUniqueRule());
    // ���ɸ�������У��
    // MarAssistantSaveRule<InitialEstVO> marRule =
    // new MarAssistantSaveRule<InitialEstVO>();
    // marRule.setNotNullValidate(InitialEstItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);
    // ��д���ε���
    processer.addAfterRule(new WriteBackSourceRule(this.context));
  }
}

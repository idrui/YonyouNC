/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����10:56:43
 */
package nc.bs.pu.m25.maintain;

import nc.bs.pu.m25.maintain.rule.InvocieWriteBackOrderRule;
import nc.bs.pu.m25.maintain.rule.WriteBackSourceRule;

import nc.bs.pu.m25.maintain.rule.save.DirectTranTypeChkRule;

import nc.bs.pu.m25.maintain.rule.save.InvcSaveAfterEventRule;
import nc.bs.pu.m25.maintain.rule.save.InvcSaveBeforeEventRule;



import nc.bs.pu.m25.maintain.rule.save.OrderPriceCheckRule;
import nc.bs.pu.m25.maintain.rule.save.ScOrderPriceCheckRule;

import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.InvoiceAuditInfoFillRule;
import nc.vo.pu.m25.rule.InvoiceDateChkRule;
import nc.vo.pu.m25.rule.InvoiceNotNullChkRule;
import nc.vo.pu.m25.rule.InvoiceNumValueChkRule;
import nc.vo.pu.m25.rule.InvoiceSourceInfoChkRule;
import nc.vo.pu.m25.rule.ParaValidityChkRule;
import nc.vo.pu.m25.rule.maintain.InvoiceLimitCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceRowNoCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceScaleCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceTotalValueCalcRule;
import nc.vo.pu.m25.rule.maintain.save.ConfirmInvoiceBiztypeRule;
import nc.vo.pu.m25.rule.maintain.save.FeeMaterialCheckRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceCodeProcRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceCodeUniqueCheckRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceRowTypeFillRule;
import nc.vo.pu.m25.rule.maintain.save.SynchronizeDupDataRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ����ĺ���BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 ����10:56:43
 */
public class InvoiceSaveBP {

  private InvoiceUIToBSEnv env;

  /**
   * InvoiceSaveBP �Ĺ�����
   * 
   * @param env
   */
  public InvoiceSaveBP(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  public InvoiceVO[] save(InvoiceVO[] insertInvoiceVOs,
      InvoiceVO[] updateInvoiceVOs, InvoiceVO[] updateOrgVos) {
    InvoiceVO[] insertVos = insertInvoiceVOs;
    InvoiceVO[] updateVos = updateInvoiceVOs;
    CompareAroundProcesser<InvoiceVO> processer =
        new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.SAVE_BP);
    this.addRule(processer);
    if (!ArrayUtils.isEmpty(insertVos)) {
      processer.before(insertVos, null);
      insertVos = new BillInsert<InvoiceVO>().insert(insertVos);
      processer.after(insertVos, null);
    }
    if (!ArrayUtils.isEmpty(updateVos)) {
      processer.before(updateVos, updateOrgVos);
      updateVos = new BillUpdate<InvoiceVO>().update(updateVos, updateOrgVos);
      processer.after(updateVos, updateOrgVos);
    }
    InvoiceVO[] savedVos = ArrayUtil.combinArrays(insertVos, updateVos);
    return savedVos;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new ParaValidityChkRule());// ������ȷ�Լ��
    processer.addBeforeRule(new InvoiceSourceInfoChkRule());// �����Դ��Ϣ�Ƿ�����
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InvoiceVO>(
        InvoiceHeaderVO.PK_INVOICE));// ��������֯���°�
    processer.addBeforeRule(new InvoiceAuditInfoFillRule());// ����ʱ�����
    processer.addBeforeRule(new InvoiceCodeProcRule()); // ���õ��ݺ�
    processer.addBeforeFinalRule(new InvoiceRowTypeFillRule());// ��䷢Ʊ������
    processer.addBeforeFinalRule(new InvoiceTotalValueCalcRule());// ��ͷ�ϼƼ������
    processer.addBeforeFinalRule(new InvoiceNotNullChkRule());// �ǿ�У��
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<InvoiceVO>()); // ��齻�������Ƿ�Ϊ��
    processer.addBeforeFinalRule(new InvoiceDateChkRule()); // ��Ʊ���ڼ��
    processer.addBeforeFinalRule(new InvoiceNumValueChkRule());// ��ֵ�ֶμ��
    processer.addBeforeFinalRule(new InvoiceScaleCheckRule());// ���ȼ��
    processer.addBeforeRule(new InvoiceRowNoCheckRule());// �кźϷ��Լ��
    processer.addBeforeRule(new FinanceOrgEnableCheckRule<InvoiceVO>());// ����֯ͣ�ü��
    processer.addBeforeFinalRule(new DirectTranTypeChkRule());// ֱ��ҵ������֯���
    processer.addBeforeFinalRule(new InvoiceLimitCheckRule());// ����ֵ���
    processer.addBeforeFinalRule(new ConfirmInvoiceBiztypeRule()); // ȷ��ҵ������(����)
    processer.addBeforeFinalRule(new SynchronizeDupDataRule());// ͬ������������Ϣ
    // ���ɸ�������У��
    // MarAssistantSaveRule<InvoiceVO> marRule =
    // new MarAssistantSaveRule<InvoiceVO>();
    // marRule.setNotNullValidate(InvoiceItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);
    // �Զ�������
    processer.addBeforeRule(new UserDefSaveRule<InvoiceVO>(new Class[] {
      InvoiceHeaderVO.class, InvoiceItemVO.class
    }));
    processer.addBeforeFinalRule(new NoPassUpdateRule<InvoiceVO>()); // ������ͨ��ʱ�޸ĺ��״̬���������������ڵȴ���
    processer.addBeforeRule(new InvcSaveBeforeEventRule());// ��������ί��ӹ��ѽ��㵥��д
    processer.addAfterRule(new InvoiceCodeUniqueCheckRule());// ���ݺ�Ψһ��У��
    processer.addAfterRule(new FeeMaterialCheckRule());// ���÷�Ʊ����Ƿ��������
    processer.addAfterFinalRule(new InvocieWriteBackOrderRule(this.env));// ��д��Դ
    processer.addAfterFinalRule(new WriteBackSourceRule(this.env));// ��д��Դ
    processer.addAfterFinalRule(new OrderPriceCheckRule(this.env));// ����Ƿ񳬲ɹ����������ݲ�
    processer.addAfterFinalRule(new ScOrderPriceCheckRule(this.env));// ����Ƿ�ί�ⶩ�������ݲ�
    processer.addAfterFinalRule(new InvcSaveAfterEventRule());// ��������ί��ӹ��ѽ��㵥��д
  }

}

package nc.pubimpl.pu.it.bp;

import nc.bs.pu.m27.settlebill.rule.WriteM5801AndM5805Rule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.SettleBillScaleCheckRule;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.AuditInfoRule;
import nc.bs.pu.m27.settlebill.rule.BackWashEstAPForRBStockRush;
import nc.bs.pu.m27.settlebill.rule.BillCodeRule;
import nc.bs.pu.m27.settlebill.rule.FillOrgCurrencyRule;
import nc.bs.pu.m27.settlebill.rule.FillUnitidRule;
import nc.bs.pu.m27.settlebill.rule.OrgInfoFillRule;
import nc.bs.pu.m27.settlebill.rule.RefCostPriceUpdateRule;
import nc.bs.pu.m27.settlebill.rule.RowNoRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillVOCheckRule;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackOrderRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;

import nc.impl.pu.m27.bp.SettleMakeInvoiceToAP;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.pu.it.rule.FeeSettleForGoodsRuleForIT;

/**
 * @description
 * 
 * 
 * 
 * @since 6.36
 * @version 2015-3-18 ����3:11:58
 * @author yixl
 */
public class SettleBillBPForIT {

  /**
   * ������㵥
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @param vos
   * @param settleEnv
   * @return SettleBillVO
   * @throws BusinessException
   * @time 2009-6-29 ����01:48:20
   */
  public SettleBillVO[] saveSettleBills(SettleBillVO[] vos,
      SettleEnvironment settleEnv) throws BusinessException {
    if (vos == null) {
      return null;
    }

    // �Ƿ񴫳ɱ�
    boolean bToIA =
        this.isAutoToIA(settleEnv, vos[0].getParentVO().getPk_org());
    // �����ж�IAģ���Ƿ�����
    if (bToIA) {
      bToIA = SysInitGroupQuery.isIAEnabled();
    }

    StockInfoUtil util = new StockInfoUtil(vos);

    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.SAVE_IT);

    // ���ӱ���ǰ���򣺵��ݺŴ���
    processor.addBeforeFinalRule(new BillCodeRule());
    // ���ӱ���ǰ���������Ϣ
    processor.addBeforeFinalRule(new AuditInfoRule());
    // ���ӱ���ǰ���򣺲�����֯�汾��Ϣ����
    processor.addBeforeFinalRule(new OrgInfoFillRule());
    // ��ȫ��֯��λ��
    processor.addBeforeFinalRule(new FillOrgCurrencyRule());
    // ����֯ͣ�ü��
    processor.addBeforeRule(new FinanceOrgEnableCheckRule<SettleBillVO>());
    // ��ȫ��������λ
    processor.addBeforeFinalRule(new FillUnitidRule());
    // ���ӱ���ǰ���򣺲�ȫ�к�
    processor.addBeforeFinalRule(new RowNoRule());
    // ����ǰ����voУ��
    processor.addBeforeFinalRule(new SettleBillVOCheckRule());
    // ���ӱ���ǰ�����Ƿ񴫳ɱ��ı�ʶ����
    processor.addBeforeFinalRule(new ToIAStatusRule(bToIA, false));
    // ���ӱ������򣺺�����ⵥ�Գ�ʱ�س��ݹ�Ӧ��
    processor.addAfterFinalRule(new BackWashEstAPForRBStockRush(util));
    // ���ӱ������򣺻�д�ɹ������
    processor.addAfterFinalRule(new PurchsInSettleUpdateRule(
        WritebackPoint.SAVE));
    // ���ӱ������򣺻�д��Ʊ
    processor.addAfterFinalRule(new WritebackInvoiceRule(WritebackPoint.SAVE));
    // ���ӱ�����������Դ�����ķ�Ʊ����Դ�ڶ�������ⵥ����ʱ��д�������ۼƿ�Ʊ�������ۼƿ�Ʊ���
    processor.addAfterFinalRule(new WritebackOrderRule(WritebackPoint.SAVE,
        settleEnv));
    // ����Դ��ϸ���ķ�Ʊ����Դ����ϸ������ⵥ����ʱ��д������ϸ�������ں�ͬ
    processor.addAfterFinalRule(new WriteM5801AndM5805Rule(WritebackPoint.SAVE,
        settleEnv));

    // ���²ο��ɱ�
    processor.addAfterFinalRule(new RefCostPriceUpdateRule(bToIA));
    // ���ȼ��
    processor.addAfterFinalRule(new SettleBillScaleCheckRule());
    processor.before(vos);
    for (SettleBillVO settleVO : vos) {

      // 21.�ɹ�����ʱ�ɹ���ⵥδ�����������в������ݹ��������ʱ����ƥ�����ݹ�������
      // ����¼���ݹ������б��ν����������һ�����Ա��ڶ�Ӧ�Ĳɹ���Ʊ��Ӧ��ʱ
      // �����ݸòɹ���ⵥ�Ƿ���й��ݹ�Ӧ����ȷ���Ƿ�س��Ӧ���ݹ�Ӧ��
      // ��ͬʱ��һ����ɹ����㴫�������ʱ����ݹ����ֽ��гɱ��Ļس���߲�������ݹ�����ʽ��������
      BillInsert<SettleBillVO> billInsertAction =
          new BillInsert<SettleBillVO>();
      billInsertAction.insert(new SettleBillVO[] {
        settleVO
      });

      // WYF �Զ���������ƥ��״̬�Ĳɹ����㵥����ݲ������Զ��������ɲɹ����㵥�Ƿ��Զ���������㡱ȷ�����Զ���������㣨����ֵΪ��
      // ���������û��ڲɹ����㵥�ڵ��ֹ������������ֵΪ�񣩣��Ƿ����SettleEnv�д��롣
      // ���ɱ�
      if (bToIA) {
        new SettleBillToIABPForIT(settleVO, settleEnv).submitToIA(util);
      }
    }
    // ������������ٽ��з��ý��㣬һ���ŵ����һ��������
    processor.addAfterFinalRule(new FeeSettleForGoodsRuleForIT(settleEnv));

    processor.after(vos);

    // ���·�Ʊ��Ӧ��
    new SettleMakeInvoiceToAP(vos).makeInvoiceToAP();

    // WYF ��Ҫ������֯����
    return vos;
  }

  /**
   * ���������������Ƿ��Զ����ɱ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param settleEnv ���㻷��
   * @return �Զ����ɱ�������true�����򷵻�false
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-17 ����01:31:46
   */
  private boolean isAutoToIA(SettleEnvironment settleEnv, String pk_org) {
    if (settleEnv.getSettleType() == EnumSettleType.IT_UI_AUTO
        || settleEnv.getSettleType() == EnumSettleType.IT_INVOICE_AUTO) {
      return PUSysParamUtil.getPO87(pk_org);
    }
    return true;
  }
}

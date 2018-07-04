/**
 * $�ļ�˵��$
 *
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 ����04:22:14
 */
package nc.pubimpl.pu.m20.mm.action;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.bs.pu.m20.maintain.rule.pub.ChangeRateRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPraytypeRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPsnAndDeptRule;
import nc.bs.pu.m20.maintain.rule.pub.SetSctypeRule;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.pu.m20.plugin.ActionPlugInPoint;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.BDirectRule;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.CalculateNumRule;
import nc.vo.pu.m20.rule.SetAstunitRule;
import nc.vo.pu.m20.rule.SetDefaultValueRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pu.m20.rule.SetRownoRule;
import nc.vo.pu.m20.rule.margin.PrayBillMarginRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.scmpub.res.billtype.MMBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ�������ʽ�����빺��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 ����04:22:14
 */
public class PushSave20For55B4Action {
  public void pushSave(PraybillVO[] praybills) {
    if (ArrayUtils.isEmpty(praybills)) {
      return;
    }
    TimeLog.logStart();
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(ActionPlugInPoint.PushSave20ForA1Action);
    // ����Action�е�ִ��ǰ/�����
    this.addBeforeRule(processer);

    // ֱ�˴���
    new BDirectRule().process(praybills);
    // �ֵ�����
    PraybillVO[] retvos = new SetSctypeRule().process(praybills);

    processer.before(retvos);
    PraybillVO[] savedVOs = new PraybillInsertBP().insert(praybills);
    processer.after(savedVOs);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0052")/* @res "ҵ����־:��̨���뵥������" */);
  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    // ��ӹ���˳���ܸ��ģ���
    // ������ȷ�Լ��
    processer.addBeforeFinalRule(new ChkPrayVONotNullRule());
    // ��ȫĬ��ֵ
    processer.addBeforeFinalRule(new SetDefaultValueRule());
    // ��ȫ�빺����
    processer.addBeforeFinalRule(new SetPraytypeRule());
    // ��ȫ�ƻ�Ա�ͼƻ�����
    processer.addBeforeFinalRule(new SetPsnAndDeptRule());
    // ��ȫ�к�
    processer.addBeforeFinalRule(new SetRownoRule());
    // ��ȫ���ұ���
    processer.addBeforeFinalRule(new SetOrgCurrRule());
    // ��ȫ�ɹ���֯
    processer.addBeforeFinalRule(new SetPurchaseorgRule());
    // ��ȫ�ɹ�Ա
    processer.addBeforeFinalRule(new SetEmployeeRule());
    // ��ȫ��������
    processer.addBeforeFinalRule(new SetOrdertrantypeRule());
    // ��ȫ��λ
    processer.addBeforeFinalRule(new SetAstunitRule());
    // ��ȫ������
    processer.addBeforeFinalRule(new ChangeRateRule());
    // ��ȫ����
    processer.addBeforeFinalRule(new CalculateNumRule());
    // ��ȫ�������ںͽ��鶩������
    processer.addBeforeFinalRule(new CalculateDateRule());
    // ��ȫ��˰���ۺͼ�˰�ϼ�
    processer.addBeforeFinalRule(new SetPriceRule());
    // �Ƶ�ʱ����β���(����)
    processer.addBeforeRule(new PrayBillMarginRule(this.getBillCode(), null));
  }

  protected String getBillCode() {
    return MMBillType.PlanOrder.getCode();

  }
}

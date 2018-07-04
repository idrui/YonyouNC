package nc.bs.pu.m27.feesettle;

import java.util.List;

import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.rule.SettleDetailAndEstInfoSetRule;
import nc.bs.pu.m27.feesettle.rule.WriteNtimesafterfirstRule;
import nc.bs.pu.m27.feesettle.rule.WriteStockSettleNumRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.ChkFeeAllotCanDeletelRule;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;

/**
 * 
 * @description
 * ��ɷ��ý����ҵ��ʵ��
 * @scene
 * ɾ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:05:58
 * @author zhangshqb
 */
public class FeeSettleDeleteBP implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] bills) {
    // ���ý�����صĽ��㵥
    List<SettleBillVO> myvos = FeeSettlePrivateUtil.findNeedFeeSettleVO(bills);
    if (myvos == null || myvos.size() == 0) {
      return;
    }
    SettleBillVO[] feevos = myvos.toArray(new SettleBillVO[0]);

    // �����̯��ϸ
    FeeSettleDataContext feectx = new FeeSettleDataContext();
    // ׼��������÷�̯��ϸ�������ݹ�����
    new PrepareFeeSettleDataRule(false, true, feectx).process(feevos);
    SettleFeeAllotDetailVO[] details = feectx.getBeenSavedAllotDetailArray();
    if (details == null || details.length == 0) {
      // û�з��÷�̯��ϸ��ζ�Ų��Ƿ��ý��㣬ֱ�ӷ���
      return;
    }
    SettlebillPluginPoint pt = SettlebillPluginPoint.CancelFeeSettleBP;
    AroundProcesser<SettleBillVO> proc = new AroundProcesser<SettleBillVO>(pt);

    // ɾ��ǰ��飺����ǵ�һ�η��ý��㣬���Ҵ��ں����ķ��ý��㣬����ɾ��
    proc.addBeforeFinalRule(new ChkFeeAllotCanDeletelRule(feectx));

    proc.before(feevos);

    new VODelete<SettleFeeAllotDetailVO>().delete(details);

    // ��д��ⵥ(�ۼƷ��ý������)
    proc.addAfterFinalRule(new WriteStockSettleNumRule(false));
    // ��д�ݹ��������ϵ�һ�ν���Ľ��㵥ID
    proc.addAfterFinalRule(new SettleDetailAndEstInfoSetRule(false, feectx));
    // ��д�����̯��ϸ�ĺ����ۼƷ��ý������
    proc.addAfterFinalRule(new WriteNtimesafterfirstRule(false));
    // ɾ�����ý��㵥ǰɾ���������ݵĴ�����㵥��
    proc.addAfterFinalRule(new FeeSettleCancelToIABP(feectx));
    // ɾ�����ý��㵥ǰɾ���������ݵ��������Ĵ�����㵥��
    proc.addAfterFinalRule(new FeeSettleCancelToPCIABP(feectx));
    proc.after(feevos);
  }
}

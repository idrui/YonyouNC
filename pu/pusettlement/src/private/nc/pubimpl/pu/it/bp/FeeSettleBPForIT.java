package nc.pubimpl.pu.it.bp;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.it.FirstDisResultForIT;
import nc.bs.pu.it.FirstDistributeUtilForIT;
import nc.bs.pu.it.SecondDistributeUtilForIT;
import nc.bs.pu.m27.feesettle.rule.FeeSettleToAPRule;
import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.rule.SaveFeeAllotDetailRule;
import nc.bs.pu.m27.feesettle.rule.SettleDetailAndEstInfoSetRule;
import nc.bs.pu.m27.feesettle.rule.WriteNtimesafterfirstRule;
import nc.bs.pu.m27.feesettle.rule.WriteStockSettleNumRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.AuditInfoRule;
import nc.bs.pu.m27.settlebill.rule.BillCodeRule;
import nc.bs.pu.m27.settlebill.rule.FillOrgCurrencyRule;
import nc.bs.pu.m27.settlebill.rule.FillUnitidRule;
import nc.bs.pu.m27.settlebill.rule.OrgInfoFillRule;
import nc.bs.pu.m27.settlebill.rule.RowNoRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillVOCheckRule;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.pub.MapList;

public class FeeSettleBPForIT {
  private boolean bAutoToIA = true;

  private SettleEnvironment envi;

  private StockSettleVO[] stocks;

  public FeeSettleBPForIT(StockSettleVO[] stocks, SettleEnvironment envi) {
    this.envi = envi;
    this.stocks = stocks;
  }

  public SettleBillVO[] doFeeSettle(SettleBillVO[] bills) {
    if (this.envi == null || this.stocks == null) {
      return null;
    }
    if (bills == null || bills.length != 1) {
      return null;
    }
    if (!this.getBOnlyFeeSettle()) {
      // ������Ƿ��ý��㣬���Ƿ�IA = ��������Ƿ�IA
      this.bAutoToIA = bills[0].getParentVO().getBtoia().booleanValue();
    }
    // ���з��÷�̯
    MapList<String, SettleFeeAllotDetailVO> details = null;
    details = this.doDistribute(bills[0]);
    // �����ݷŵ��Զ����Context
    FeeSettleDataContext datactx = new FeeSettleDataContext();
    datactx.setNoneSavedAllotDetails(details);
    datactx.setStockSettleVOMap(CirVOUtil.createKeyVOMap(this.stocks));

    IPluginPoint point = SettlebillPluginPoint.FeeSettleBP_IT;
    AroundProcesser<SettleBillVO> pc = new AroundProcesser<SettleBillVO>(point);

    // ���BP����
    this.addBeforeRule(pc);
    this.addAfterRule(pc, datactx);

    pc.before(bills);

    if (this.getBOnlyFeeSettle()) {
      // ����Ƿ��ý��㣬����Ҫ���㵥�־û�������������ʱ�ѳ־û�
      BillInsert<SettleBillVO> bo = new BillInsert<SettleBillVO>();
      bo.insert(bills);
    }
    pc.after(bills);

    return bills;
  }

  private void addAfterRule(AroundProcesser<SettleBillVO> proc,
      FeeSettleDataContext datactx) {

    // ��ѯ�����ݹ���ϸ(�����ݷŵ�FeeSettleDataContext��ȥ)
    proc.addAfterFinalRule(new PrepareFeeSettleDataRule(true, datactx));
    // ���·��ý����̯��ϸһЩ��Ϣ(���㵥����������������һ�η����ݹ������־���������ڵĽ��㵥��ID��)
    // ��д�����ݹ���(�ݹ��������ϵ�һ�ν���Ľ��㵥ID����ID)
    proc.addAfterFinalRule(new SettleDetailAndEstInfoSetRule(true, datactx));

    // �־û������̯��ϸ
    proc.addAfterFinalRule(new SaveFeeAllotDetailRule(datactx));

    // ��д�����̯��ϸ�ĺ����ۼƷ��ý������
    proc.addAfterFinalRule(new WriteNtimesafterfirstRule(true));

    // ��д��ⵥ(�ۼƷ��ý������)
    proc.addAfterFinalRule(new WriteStockSettleNumRule(true));

    if (this.getBOnlyFeeSettle()) {
      // ������Ƿ��ý��㣬���ڻ������ʱ�Ѵ����д�ɹ���Ʊ�����÷�Ʊ��Ӧ��
      proc.addAfterFinalRule(new WritebackInvoiceRule(WritebackPoint.SAVE));
      proc.addAfterFinalRule(new FeeSettleToAPRule());
      // ���ӱ������򣺻�д�ɹ������
      proc.addAfterFinalRule(new PurchsInSettleUpdateRule(WritebackPoint.SAVE));
    }
    // ���������
    if (this.bAutoToIA) {
      proc.addAfterFinalRule(new FeeSettleToIABPForIT(datactx));
    }
  }

  private void addBeforeRule(AroundProcesser<SettleBillVO> proc) {
    if (!this.getBOnlyFeeSettle()) {
      return; // ������Ƿ��ý��㣬���ڻ������ʱ���¹�����Ѵ���
    }
    // ���ӱ���ǰ���򣺵��ݺŴ���
    proc.addBeforeFinalRule(new BillCodeRule());

    // ���ӱ���ǰ���������Ϣ
    proc.addBeforeFinalRule(new AuditInfoRule());

    // ���ӱ���ǰ���򣺲�ȫ�к�
    proc.addBeforeFinalRule(new RowNoRule());

    // ���ӱ���ǰ���򣺲�����֯�汾��Ϣ����
    proc.addBeforeFinalRule(new OrgInfoFillRule());

    // ��ȫ��֯��λ��
    proc.addBeforeFinalRule(new FillOrgCurrencyRule());
    // ��ȫ��������λ
    proc.addBeforeFinalRule(new FillUnitidRule());
    // proc����ǰ����voУ��
    proc.addBeforeFinalRule(new SettleBillVOCheckRule());
  }

  private MapList<String, SettleFeeAllotDetailVO> doDistribute(SettleBillVO bill) {
    // modify by liangchen1
    // 1����ⵥ����֮��ķ�̯(��һ�η�̯)
    FirstDistributeUtilForIT firstDistribute =
        new FirstDistributeUtilForIT(bill, this.stocks);
    firstDistribute.distribute();
    FirstDisResultForIT[] firstReuslts = firstDistribute.getFirstResults();

    // 2��������㵥�������ݹ�֮��ķ��õķ��÷�̯(�ڶ��η�̯)
    CostfactorViewVO[] factors = firstDistribute.getTotalFactors();
    SecondDistributeUtilForIT secondDistribute =
        new SecondDistributeUtilForIT(this.stocks, firstReuslts, factors);
    // <��ⵥ��ID,��Ӧ�ķ��÷�̯��ϸ>
    return secondDistribute.distribute();
  }

  private boolean getBOnlyFeeSettle() {
    if (this.envi == null) {
      return true;
    }
    EnumSettleType type = this.envi.getSettleType();
    if (type == EnumSettleType.IT_FEE) {
      return true;
    }
    return false;
  }
}

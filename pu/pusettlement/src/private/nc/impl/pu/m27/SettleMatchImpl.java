package nc.impl.pu.m27;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import nc.bs.pu.m27.feesettle.FeeSettleBP;
import nc.bs.pu.m27.match.WithoutInvoiceMatch;
import nc.bs.pu.m27.rule.SettleTOIAChkRule;
import nc.impl.pu.m27.bp.SettleBillBP;
import nc.impl.pu.m27.merge.FeeMatchMerge;
import nc.impl.pu.m27.merge.MergeFactory;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m27.ISettleMatch;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchExecType;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ViewConcurrentUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

public class SettleMatchImpl implements ISettleMatch {

  @Override
  public SettleBillVO[] autoMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.UI_AUTO);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public void checkGoodsSettleTOIA(StockSettleVO[] ssVos)
      throws BusinessException {
    try {
      new SettleTOIAChkRule().process(ssVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public SettleBillVO[] differentMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.DIFFERENT_MATERIAL);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] feeMatch(InvoiceSettleVO[] invoices,
      StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, SettleEnvironment envi)
      throws BusinessException {
    try {
      // 1����������
      this.concurrentControl(invoices, stocks, fees, discounts);

      // 2�����з��ý���
      envi.setSettleType(EnumSettleType.FEE);
      FeeMatchMerge merge = new FeeMatchMerge(stocks, fees, discounts, envi);
      SettleBillVO[] bills = merge.merge();
      return new FeeSettleBP(stocks, envi).doFeeSettle(bills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public MapList<String, String> getEstAPFeeMaterial(StockSettleVO[] ssVos)
      throws BusinessException {
    try {
      return this.queryEstAPFee(ssVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public SettleBillVO[] sameMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.SAME_MATERIAL);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] voiConsumeFeeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.VOI_CONSUME_FEE);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] voiConsumeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.VOI_CONSUME);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] withoutInvoiceMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    try {
      // ��������
      this.concurrentControl(voaInvoice, voaStock, voaFee, voaDiscount);
      // ���ý����û�������Ϣ��nc.pubimpl.pu.m25.pu.settle.VirtualInvoiceMaintainImpl.genInvoiceʹ��
      BSContext.getInstance().setSession("withoutInvoiceMatchEnv", settleEnv);
      // �������ⷢƱ-���ⷢƱ�Զ�����-��Ʊ�Զ�����
      WithoutInvoiceMatch bp = new WithoutInvoiceMatch();
      return bp.matchProcess(voaStock);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  private void concurrentControl(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount) {

    ViewConcurrentUtil util = ViewConcurrentUtil.getInstance();
    if (!ArrayUtils.isEmpty(voaInvoice)) {
      util.concurrentCheck(voaInvoice);
    }
    if (!ArrayUtils.isEmpty(voaStock)) {
      MapList<String, StockSettleVO> btSsMap =
          new MapList<String, StockSettleVO>();
      for (StockSettleVO ssVo : voaStock) {
        btSsMap.put(ssVo.getCbilltypecode(), ssVo);
      }
      for (Entry<String, List<StockSettleVO>> entry : btSsMap.entrySet()) {
        List<StockSettleVO> voLst = entry.getValue();
        StockSettleVO[] vos = voLst.toArray(new StockSettleVO[voLst.size()]);
        util.concurrentCheck(vos);
      }
    }
    if (!ArrayUtils.isEmpty(voaFee)) {
      util.concurrentCheck(voaFee);
    }
    if (!ArrayUtils.isEmpty(voaDiscount)) {
      util.concurrentCheck(voaDiscount);
    }
  }

  /**
   * ����������ߴ˹��̡�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-10 ����03:00:16
   */
  private SettleBillVO[] matchProcess(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException {
    // ��������
    this.concurrentControl(voaInvoice, voaStock, voaFee, voaDiscount);

    // ��桢��Ʊͬ���Ͻ���VO�ϲ�
    MatchMerge merge =
        new MergeFactory().getMerge(voaInvoice, voaStock, voaFee, voaDiscount,
            settleEnv);
    SettleBillVO[] voaBill = merge.merge();

    if (MatchExecType.mock.equals(settleEnv.getExecType())) {
      // ��Ϊģ�����,�򲻱���ֱ�ӷ���
      return voaBill;
    }
    // ������㵥
    return new SettleBillBP().saveSettleBills(voaBill, settleEnv);
  }

  private MapList<String, String> queryEstAPFee(StockSettleVO[] ssVos) {
    MapList<String, String> retml = new MapList<String, String>();
    if (ArrayUtils.isEmpty(ssVos)) {
      return retml;
    }
    HashSet<Object> pks =
        CirVOUtil.getDistinctFieldSet(ssVos, StockSettleVO.PK_STOCKPS_B);
    String[] aryPks = pks.toArray(new String[pks.size()]);
    FeeEstVO[] feeVos = null;
    String[] names = new String[] {
      FeeEstVO.PK_STOCKPS_B, FeeEstVO.BTOAP, FeeEstVO.PK_FEEMATERIAL
    };
    String cond =
        "and "
            + new IDExQueryBuilder(PUTempTable.tmp_po_27_08.name()).buildSQL(
                FeeEstVO.PK_STOCKPS_B, aryPks);
    if (ICBillType.VmiSum.getCode().equals(ssVos[0].getCbilltypecode())) {
      feeVos =
          new VOQuery<VmiFIFeeVO>(VmiFIFeeVO.class, names).query(cond, null);
    }
    else {
      feeVos =
          new VOQuery<PurchaseinFIFeeVO>(PurchaseinFIFeeVO.class, names).query(
              cond, null);
    }
    if (ArrayUtils.isEmpty(feeVos)) {
      return retml;
    }
    for (FeeEstVO feeVo : feeVos) {
      if (UFBoolean.TRUE.equals(feeVo.getBtoap())) {
        retml.put(feeVo.getPk_stockps_b(), feeVo.getPk_feematerial());
      }
    }
    return retml;
  }
}

package nc.ui.pu.m27.match.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.rule.feedivide.FeeDivideRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.AbstrictFirstFeeDistribute;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����֮�ڶ��������еķ��÷�̯��ť��ʹ�õĹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-16 ����05:14:35
 */
public class UIFeeDistributeUtil extends AbstrictFirstFeeDistribute {
  private BillListPanel blp;

  /** ��ǰ���������֯���Ҿ��� **/
  private int digit = 2;

  // �ۿ��෢Ʊ
  private FeeDiscountSettleVO[] discounts;

  // �����෢Ʊ
  private FeeDiscountSettleVO[] fees;

  // ��ǰ����
  private String group;

  // ����ڶ�������VO(���ý���ʱֻ����ⵥ)
  private MatchMaterialVO[] matchvos;

  private ListPanelValueUtils panelUtil;

  private CostfactorViewVO[] totalFactors;

  public UIFeeDistributeUtil(FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, MatchMaterialVO[] mmVos,
      BillListPanel blp, SettleEnvironment env) {
    this.fees = fees;
    this.discounts = discounts;
    this.blp = blp;
    this.panelUtil = new ListPanelValueUtils(blp);
    this.group = ClientContext.getInstance().getPk_group();
    // ֻ�������ⵥ��
    List<MatchMaterialVO> stockmatchvos = this.extractStockMMVO(mmVos);
    this.matchvos =
        stockmatchvos.toArray(new MatchMaterialVO[stockmatchvos.size()]);
    // �ɱ�Ҫ�س�ʼ��
    this.totalFactors = env.getCostFactorViews();
    // ��ʼ����λ�Ҿ���
    this.initOrgCurrDigit(env);
  }

  public UIFeeDistributeUtil(MatchManageModel model, BillListPanel blp) {
    this(model.getMatchFeeInvoices(), model.getMatchDiscountInvoices(), model
        .getMatchMaterialVOs(), blp, model.getSettleEnvironment());
  }

  @Override
  public void distributeDiscount() {
    if (ArrayUtils.isEmpty(this.discounts)) {
      return;
    }
    for (FeeDiscountSettleVO discount : this.discounts) {
      // �ѷ�Ʊת���ɷ��÷�̯����Ҫ�Ľṹ
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(discount);

      // ����ⵥת���ɷ��÷�̯����Ҫ�Ľṹ
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO();
      FeeDivideRule t = new FeeDivideRule(this.group, this.totalFactors, fdvos);

      // ���з�̯
      FeeMnyDivideVO[] feeDivVos = t.divideForDiscount(feeMnyVos);

      // ��¼�ۿ۷�̯���
      this.recordDiscountResult(discount, feeDivVos);
    }
  }

  @Override
  public void distributeFee() {
    if (ArrayUtils.isEmpty(this.fees)) {
      return;
    }

    for (FeeDiscountSettleVO fee : this.fees) {
      // �ѷ��÷�Ʊת���ɷ��÷�̯����Ҫ�Ľṹ
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(fee);

      // ����ⵥת���ɷ��÷�̯����Ҫ�Ľṹ
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO();
      FeeDivideRule t = new FeeDivideRule(this.group, this.totalFactors, fdvos);

      // ���з�̯
      FeeMnyDivideVO[] feeDivVos = t.divide(feeMnyVos);

      // ��¼���÷�̯���
      this.recordFeeResult(fee, feeDivVos);
    }
  }

  private void addCurrentSettleMny(int i, UFDouble addMny) {
    String mnyKey = MatchMaterialVO.NCURSEETLEMNY;
    UFDouble oldMny = (UFDouble) this.panelUtil.getHeadValueAt(i, mnyKey);
    UFDouble newMny = MathTool.add(oldMny, addMny);
    this.panelUtil.setHeadValueAt(newMny, i, mnyKey);
  }

  private FeeMnyVO[] convertToFeeMnyVO(FeeDiscountSettleVO fee) {
    // �������ۿ��෢Ʊת��Ϊ���÷�̯����Ҫ�Ľ⹹
    if (fee == null) {
      return null;
    }
    FeeMnyVO[] feemnyvo = new FeeMnyVO[1];
    String feemrloid = fee.getPk_srcmaterial();
    UFDouble feemny = fee.getNcurrentinvoicesettlemny();
    feemnyvo[0] = new FeeMnyVO(feemrloid, feemny, this.digit);
    return feemnyvo;
  }

  private List<MatchMaterialVO> extractStockMMVO(
      MatchMaterialVO[] invoiceAndStock) {
    if (invoiceAndStock == null || invoiceAndStock.length == 0) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0030")/* @res "û�п��Բ�����÷�̯����ⵥ�У����飡" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    List<MatchMaterialVO> stockmatchvos = new ArrayList<MatchMaterialVO>();
    for (MatchMaterialVO vo : invoiceAndStock) {
      if (ValueUtils.getBoolean(vo.getBstock())) {
        stockmatchvos.add(vo);
      }
    }
    if (stockmatchvos.size() == 0) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0030")/* @res "û�п��Բ�����÷�̯����ⵥ�У����飡" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    return stockmatchvos;
  }

  private CostfactorViewVO findFactorVO(String pk_factor) {
    // ���ݳɱ�Ҫ��������ö�Ӧ�ĳɱ�Ҫ����ͼVO(�����ж��Ƿ�Ӱ��ɱ�)
    if (StringUtils.isEmpty(pk_factor) || this.totalFactors == null) {
      return null;
    }
    for (CostfactorViewVO view : this.totalFactors) {
      if (!pk_factor.equals(view.getPk_costfactor())) {
        continue;
      }
      return view;
    }
    String msg =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0031")/* @res "������Ʊ���з�̯ʱ�������Ҳ�����Ӧ�ĳɱ�Ҫ�ض��壡" */;
    ExceptionUtils.wrappBusinessException(msg);
    return null;
  }

  private FeeMnyDivideVO[] getFeeMnyDivideVO() {
    if (ArrayUtils.isEmpty(this.matchvos)) {
      return null;
    }
    // �����ⵥ�����ݹ�������÷�̯ʱ���շ��ý��/���ݹ��������߽��+δ�ݹ������ۼ��ѽ����������߽����з�̯��
    // �����ⵥ��ȷ��Ӧ���ͳɱ�������÷�̯ʱ���շ��ý��/ȷ�ϳɱ����������߽�
    FeeMnyDivideVO[] mnyDivides = new FeeMnyDivideVO[this.matchvos.length];
    for (int i = 0, len = this.matchvos.length; i < len; i++) {
      StockSettleVO vo = this.matchvos[i].getStockSettleVO();
      if (vo == null) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
                "04004060-0032")/* @res "���÷�̯ʱ��������ⵥ����Ϊ�յ����ݴ���" */;
        ExceptionUtils.wrappBusinessException(msg);
        return null;
      }
      String stockbid = vo.getPk_stockps_b();
      UFDouble disnum;
      UFDouble dismny;
      if (EnumToIAFlag.ConfirmToIA.value().equals(vo.getFdirtoiatype())) {
        // ȷ�Ϲ��ɱ�ֱ��ʹ��ȷ�ϳɱ�������
        disnum = vo.getNinnum();
        // wuxla v61
        // dismny = vo.getNmny();
        dismny = vo.getNcalcostmny();
      }
      else {
        // ��̯���� = �ۼƽ����������ۼ��ݹ����ֽ�������+�ݹ�����
        disnum =
            MathTool.add(
                MathTool.sub(vo.getNaccumsettlenum(),
                    vo.getNaccestcoststtlnum()), vo.getNestnum());

        // ��̯���= �ݹ�ǰ�ۼƽ�����+�ݹ����Һ�˰���(���Һ�˰���)
        // dismny = MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestmny());
        // wuxla v61
        dismny =
            MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestcalcostmny());

      }
      String mrlvid = this.matchvos[i].getPk_material();
      // �����Գ���Ϊ0��ȡ���� 2011-11-17 ��ϡ������С������һ��ȷ��
      if (MathTool.isZero(dismny)) {
        dismny = disnum;
      }
      mnyDivides[i] = new FeeMnyDivideVO(dismny, disnum, mrlvid);
      mnyDivides[i].setBillpk(stockbid);
    }
    return mnyDivides;
  }

  private void initOrgCurrDigit(SettleEnvironment env) {
    String pk_curr = env.getOrgCurr();
    this.digit =
        new ScaleObjectFactory.CurrtypeScaleObject(2, 4).getDigit(pk_curr);
  }

  private void recordDiscountResult(FeeDiscountSettleVO discount,
      FeeMnyDivideVO[] feeDivVos) {
    if (discount == null || feeDivVos == null) {
      return;
    }
    // ����ṹ��<��ⵥ��ID,��̯VO>
    Map<String, FeeMnyDivideVO> bid_dividevo_map = null;
    bid_dividevo_map = new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO vo : feeDivVos) {
      bid_dividevo_map.put(vo.getBillpk(), vo);
    }
    int len = this.blp.getHeadBillModel().getRowCount();
    for (int i = 0; i < len; i++) {
      String bid =
          this.panelUtil.getHeadStringValueAt(i, MatchMaterialVO.PK_BILLBID);
      if (bid_dividevo_map.get(bid) == null) {
        continue;
      }
      // ����ʽ����Ҫ�صķ�̯���
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      String key = MatchMaterialVO.NDISCOUNT;
      UFDouble oldMny = (UFDouble) this.panelUtil.getHeadValueAt(i, key);
      UFDouble newMny = MathTool.add(oldMny, addMny);
      this.panelUtil.setHeadValueAt(newMny, i, key);
      this.addCurrentSettleMny(i, addMny);
    }
  }

  private void recordFeeResult(FeeDiscountSettleVO fee,
      FeeMnyDivideVO[] feeDivVos) {
    CostfactorViewVO factorvo = this.findFactorVO(fee.getPk_costfactor());
    if (ArrayUtils.isEmpty(feeDivVos) || factorvo == null) {
      return;
    }
    // ����ṹ��<��ⵥ��ID,��̯VO>
    Map<String, FeeMnyDivideVO> bid_dividevo_map = null;
    bid_dividevo_map = new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO vo : feeDivVos) {
      bid_dividevo_map.put(vo.getBillpk(), vo);
    }
    int len = this.blp.getHeadBillModel().getRowCount();
    for (int i = 0; i < len; i++) {
      String bid =
          this.panelUtil.getHeadStringValueAt(i, MatchMaterialVO.PK_BILLBID);
      if (bid_dividevo_map.get(bid) == null) {
        continue;
      }
      // ����ʽ����Ҫ�صķ�̯���
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      if (factorvo.getBentercost().booleanValue()) {
        // �Ƿ�������ɱ�=TRUEʱ�������ñ��ν�����Ž��д�I9��IG
        this.addCurrentSettleMny(i, addMny);
      }

      int ishoworder = this.getFactorIndex(factorvo);// Ҫ���м�¼��˳���
      String factKey = SettlePublicUtil.getNcostfactorNameByIndex(ishoworder);
      UFDouble oldMny = (UFDouble) this.panelUtil.getHeadValueAt(i, factKey);
      UFDouble newMny = MathTool.add(oldMny, addMny);
      this.panelUtil.setHeadValueAt(newMny, i, factKey);
    }
  }
}

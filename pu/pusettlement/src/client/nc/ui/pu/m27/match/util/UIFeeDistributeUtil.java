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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算之第二个界面中的费用分摊按钮所使用的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-16 下午05:14:35
 */
public class UIFeeDistributeUtil extends AbstrictFirstFeeDistribute {
  private BillListPanel blp;

  /** 当前结算财务组织本币精度 **/
  private int digit = 2;

  // 折扣类发票
  private FeeDiscountSettleVO[] discounts;

  // 费用类发票
  private FeeDiscountSettleVO[] fees;

  // 当前集团
  private String group;

  // 结算第二个界面VO(费用结算时只有入库单)
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
    // 只抽出来入库单行
    List<MatchMaterialVO> stockmatchvos = this.extractStockMMVO(mmVos);
    this.matchvos =
        stockmatchvos.toArray(new MatchMaterialVO[stockmatchvos.size()]);
    // 成本要素初始化
    this.totalFactors = env.getCostFactorViews();
    // 初始化本位币精度
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
      // 把发票转换成费用分摊所需要的结构
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(discount);

      // 把入库单转换成费用分摊所需要的结构
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO();
      FeeDivideRule t = new FeeDivideRule(this.group, this.totalFactors, fdvos);

      // 进行分摊
      FeeMnyDivideVO[] feeDivVos = t.divideForDiscount(feeMnyVos);

      // 记录折扣分摊结果
      this.recordDiscountResult(discount, feeDivVos);
    }
  }

  @Override
  public void distributeFee() {
    if (ArrayUtils.isEmpty(this.fees)) {
      return;
    }

    for (FeeDiscountSettleVO fee : this.fees) {
      // 把费用发票转换成费用分摊所需要的结构
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(fee);

      // 把入库单转换成费用分摊所需要的结构
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO();
      FeeDivideRule t = new FeeDivideRule(this.group, this.totalFactors, fdvos);

      // 进行分摊
      FeeMnyDivideVO[] feeDivVos = t.divide(feeMnyVos);

      // 记录费用分摊结果
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
    // 把劳务折扣类发票转换为费用分摊所需要的解构
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
              "04004060-0030")/* @res "没有可以参与费用分摊的入库单行，请检查！" */;
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
              "04004060-0030")/* @res "没有可以参与费用分摊的入库单行，请检查！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    return stockmatchvos;
  }

  private CostfactorViewVO findFactorVO(String pk_factor) {
    // 根据成本要素主键获得对应的成本要素视图VO(用于判断是否影响成本)
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
            "04004060-0031")/* @res "对劳务发票进行分摊时，出现找不到对应的成本要素定义！" */;
    ExceptionUtils.wrappBusinessException(msg);
    return null;
  }

  private FeeMnyDivideVO[] getFeeMnyDivideVO() {
    if (ArrayUtils.isEmpty(this.matchvos)) {
      return null;
    }
    // 如果入库单作过暂估，则费用分摊时按照费用金额/（暂估数量或者金额+未暂估部分累计已结算数量或者金额）进行分摊；
    // 如果入库单传确认应付和成本，则费用分摊时按照费用金额/确认成本的数量或者金额。
    FeeMnyDivideVO[] mnyDivides = new FeeMnyDivideVO[this.matchvos.length];
    for (int i = 0, len = this.matchvos.length; i < len; i++) {
      StockSettleVO vo = this.matchvos[i].getStockSettleVO();
      if (vo == null) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
                "04004060-0032")/* @res "费用分摊时，出现入库单数据为空的数据错误！" */;
        ExceptionUtils.wrappBusinessException(msg);
        return null;
      }
      String stockbid = vo.getPk_stockps_b();
      UFDouble disnum;
      UFDouble dismny;
      if (EnumToIAFlag.ConfirmToIA.value().equals(vo.getFdirtoiatype())) {
        // 确认过成本直接使用确认成本的数据
        disnum = vo.getNinnum();
        // wuxla v61
        // dismny = vo.getNmny();
        dismny = vo.getNcalcostmny();
      }
      else {
        // 分摊数量 = 累计结算数量－累计暂估部分结算数量+暂估数量
        disnum =
            MathTool.add(
                MathTool.sub(vo.getNaccumsettlenum(),
                    vo.getNaccestcoststtlnum()), vo.getNestnum());

        // 分摊金额= 暂估前累计结算金额+暂估本币含税金额(本币含税金额)
        // dismny = MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestmny());
        // wuxla v61
        dismny =
            MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestcalcostmny());

      }
      String mrlvid = this.matchvos[i].getPk_material();
      // 红蓝对冲金额为0，取数量 2011-11-17 孙聪、赵玉行、田锋涛一起确认
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
    // 构造结构：<入库单行ID,分摊VO>
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
      // 增量式设置要素的分摊金额
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
    // 构造结构：<入库单行ID,分摊VO>
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
      // 增量式设置要素的分摊金额
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      if (factorvo.getBentercost().booleanValue()) {
        // 是否进入存货成本=TRUE时，才设置本次结算金额，才进行传I9、IG
        this.addCurrentSettleMny(i, addMny);
      }

      int ishoworder = this.getFactorIndex(factorvo);// 要素中记录了顺序号
      String factKey = SettlePublicUtil.getNcostfactorNameByIndex(ishoworder);
      UFDouble oldMny = (UFDouble) this.panelUtil.getHeadValueAt(i, factKey);
      UFDouble newMny = MathTool.add(oldMny, addMny);
      this.panelUtil.setHeadValueAt(newMny, i, factKey);
    }
  }
}

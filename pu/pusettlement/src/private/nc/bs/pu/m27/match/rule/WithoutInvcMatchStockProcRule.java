package nc.bs.pu.m27.match.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.ic.m47.entity.SubcontInBodyVO;
import nc.vo.ic.m47.entity.SubcontInHeadVO;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.ic.m47.entity.SubcontInViewVO;
import nc.vo.ic.org.OrgInfoQuery;
import nc.vo.ic.pub.calc.BusiCalculator;
import nc.vo.ic.pub.calc.PriceAndMoneyCalculator;
import nc.vo.ic.pub.calc.PriceAndMoneyCalculator.MnyCalcType;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.entity.InitialEstViewVO;
import nc.vo.pu.m4t.rule.RelationCalculate;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.tool.BillHelper;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.uap.taxcode.TaxType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 无发票结算时，相应的入库单信息查询及处理，用于生成虚拟发票
 * 
 * @since 6.0
 * @version 2011-1-31 下午10:00:07
 * @author zhaoyha
 */
public class WithoutInvcMatchStockProcRule {

  private InitialEstVO[] initVos;

  private PurchaseInVO[] purVos;

  private StockSettleVO[] stockStlVos;

  private SubcontInVO[] subconVos;

  public WithoutInvcMatchStockProcRule(StockSettleVO[] stockVos) {
    this.stockStlVos = stockVos;
    this.init();// 初始化－得到需要的入库单信息
  }

  /**
   * 得到生成虚拟发票的期初暂估入库单VO（此类构建时已经生成所需数据，反复调用此方法不会有效率问题）
   * 
   * @return
   */
  public InitialEstVO[] getInitialEstVOs() {
    return this.initVos;
  }

  /**
   * 得到生成虚拟发票的采购入库单VO（此类构建时已经生成所需数据，反复调用此方法不会有效率问题）
   * 
   * @return
   */
  public PurchaseInVO[] getPurchaseInVOs() {
    return this.purVos;
  }

  /**
   * 得到生成虚拟发票的委托加工入库单VO（此类构建时已经生成所需数据，反复调用此方法不会有效率问题）
   * 
   * @return
   */
  public SubcontInVO[] getSubcontInVOs() {
    return this.subconVos;
  }

  private String[] getStockBIDs(String bt) {
    List<String> bidLst = new ArrayList<String>();
    for (StockSettleVO ssVo : this.stockStlVos) {
      if (bt.equals(ssVo.getCbilltypecode())) {
        bidLst.add(ssVo.getPk_stockps_b());
      }
    }
    return bidLst.toArray(new String[bidLst.size()]);
  }

  private void init() {
    try {
      // 得到采购入的虚拟发票生成VO
      this.initPurchsIn();
      // 得到委托加工入的虚拟发票生成VO
      this.initSubcontIn();
      // 得到期初暂估的虚拟发票生成VO
      this.initInitialEst();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void initEstCalc(InitialEstVO[] vos) {
    RelationCalculate calc = new RelationCalculate();
    for (InitialEstVO vo : vos) {
      // 只计算数量线
      calc.calcNum(vo, InitialEstItemVO.NNUM);
      // 先以金额发起计算
      calc.calculate(vo, InitialEstItemVO.NMNY);
      // 将可开票数量设置为主数量
      for (InitialEstItemVO item : vo.getItems()) {
        item.setNcaninvoicenum(item.getNnum());
      }
    }
  }

  private void initInitialEst() throws BusinessException {
    String[] bids = this.getStockBIDs(POBillType.InitEstimate.getCode());
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    InitialEstViewVO[] views =
        new ViewQuery<InitialEstViewVO>(InitialEstViewVO.class).query(bids);
    InitialEstVO[] vos =
        new CombineViewToAggUtil<InitialEstVO>(InitialEstVO.class,
            InitialEstHeaderVO.class, InitialEstItemVO.class).combineViewToAgg(
            views, InitialEstHeaderVO.PK_INITIALEST);
    this.procStockVo(vos, InitialEstItemVO.NMNY);
    this.initEstCalc(vos);// 联动计算
    this.initVos = vos;
  }

  private void initPurchsIn() throws BusinessException {
    String[] bids = this.getStockBIDs(ICBillType.PurchaseIn.getCode());
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    PurchaseInViewVO[] views =
        new ViewQuery<PurchaseInViewVO>(PurchaseInViewVO.class).query(bids);
    PurchaseInVO[] vos =
        new CombineViewToAggUtil<PurchaseInVO>(PurchaseInVO.class,
            PurchaseInHeadVO.class, PurchaseInBodyVO.class).combineViewToAgg(
            views, MetaNameConst.CGENERALHID);
    this.procStockVo(vos, ICPubMetaNameConst.NCALCOSTMNY);
    this.purInCalc(vos);// 联动计算
    this.purVos = vos;
  }

  private void initSubcontIn() throws BusinessException {
    String[] bids = this.getStockBIDs(ICBillType.SubContinIn.getCode());
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    SubcontInViewVO[] views =
        new ViewQuery<SubcontInViewVO>(SubcontInViewVO.class).query(bids);
    SubcontInVO[] vos =
        new CombineViewToAggUtil<SubcontInVO>(SubcontInVO.class,
            SubcontInHeadVO.class, SubcontInBodyVO.class).combineViewToAgg(
            views, MetaNameConst.CGENERALHID);
    this.procStockVo(vos, ICPubMetaNameConst.NCOSTMNY);
    this.subContIncalc(vos);// 联动计算
    this.subconVos = vos;
  }

  private void procStockVo(AbstractBill[] stockVos, String mnyName)
      throws BusinessException {
    Map<String, StockSettleVO> stockMap =
        CirVOUtil.createKeyVOMap(this.stockStlVos);
    for (AbstractBill svo : stockVos) {
      for (CircularlyAccessibleValueObject item : svo.getChildrenVO()) {
        StockSettleVO settle = stockMap.get(item.getPrimaryKey());
        // if (null != settle.getNavgsettleprice()) {
        // item.setAttributeValue(priceName, settle.getNavgsettleprice());
        // }
        // 结算以金额为主，从金额发起联动计算即可
        item.setAttributeValue(mnyName, settle.getNcurrentinvoicesettlemny());
        item.setAttributeValue(InitialEstItemVO.NNUM,
            settle.getNcurrentsettlenum());
      }
    }
  }

  private void purInCalc(PurchaseInVO[] vos) {
    BusiCalculator icCalc = BusiCalculator.getBusiCalculatorAtBS();
    BillHelper bh = new BillHelper(vos);
    List<ISuperVO> blst =
        bh.getItemIndex().get(vos[0].getBody(0).getMetaData());
    PurchaseInBodyVO[] bvos = blst.toArray(new PurchaseInBodyVO[blst.size()]);
    // 先只计算数量线（可算辅数量），成本价及金额虚拟发票不关心，可暂时不算，有需求再说
    icCalc.calcNum(bvos, ICPubMetaNameConst.NNUM);
    // 因为公共算法不支持以记成本金额驱动反算单价金额，所以自己先算出本币无税金额，再以本币无税驱动公共算法
    for (PurchaseInBodyVO bvo : bvos) {
      Integer ftaxtypeflag = bvo.getFtaxtypeflag();
      UFDouble nmny = null;
      // 税率先取不可抵扣税率，如果没值，取税率
      UFDouble ntaxrate = bvo.getnnosubtaxrate();
      if(ntaxrate == null){
        ntaxrate = bvo.getNtaxrate();
      }
      if (ftaxtypeflag.intValue() == TaxType.TAXABLE_PLUS.toIntValue()) {
        nmny =
            bvo.getNcalcostmny().div(
                1 + ntaxrate.doubleValue() / 100);
      }
      else {
        nmny =
            bvo.getNcalcostmny().multiply(
                1 - ntaxrate.doubleValue() / 100);
      }
      ScaleUtils utils =
          new ScaleUtils(InvocationInfoProxy.getInstance().getGroupId());
      nmny = utils.adjustMnyScale(nmny, bvo.getCcurrencyid());
      bvo.setAttributeValue(InitialEstItemVO.NMNY, nmny);
      // 先缓存记成本金额，以防被金额反算
      UFDouble ncalcostmny = bvo.getNcalcostmny();
      UFDouble nosubtax = ncalcostmny.sub(nmny);
      icCalc.calc(new PurchaseInBodyVO[] {
        bvo
      }, InitialEstItemVO.NMNY);
      bvo.setNcalcostmny(ncalcostmny);
      bvo.setNnosubtax(nosubtax);
    }
  }

  private void subContIncalc(SubcontInVO[] vos) {
    BusiCalculator icCalc = BusiCalculator.getBusiCalculatorAtBS();
    BillHelper bh = new BillHelper(vos);
    List<ISuperVO> blst =
        bh.getItemIndex().get(vos[0].getBody(0).getMetaData());
    SubcontInBodyVO[] bvos = blst.toArray(new SubcontInBodyVO[blst.size()]);
    // 先只计算数量线
    icCalc.calcNum(bvos, ICPubMetaNameConst.NNUM);
    // 计算虚拟发票关心的成本价及金额
    PriceAndMoneyCalculator pcalc =
        new PriceAndMoneyCalculator(ScaleUtils.getScaleUtilAtBS(),
            new OrgInfoQuery());
    for (SubcontInVO vo : vos) {
      pcalc.calcPriceMny(vo, MnyCalcType.Mny);
    }
  }

}

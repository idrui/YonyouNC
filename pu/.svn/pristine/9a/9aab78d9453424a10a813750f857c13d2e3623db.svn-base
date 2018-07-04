package nc.vo.pu.m27.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

public class InvoiceSettleVOUtil {

  /**
   * 计算未结算金额及数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoiceSettleVOs <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-28 下午08:00:37
   */
  public static void calInvoiceCanSettleValue(InvoiceSettleVO[] invoiceSettleVOs) {
    if (ArrayUtils.isEmpty(invoiceSettleVOs)) {
      return;
    }
    ScaleUtils su = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (int i = 0; i < invoiceSettleVOs.length; i++) {
      // V61 wuxla 发票金额、本次结算金额等由取发票的本币无税金额调整为取发票的计成本金额。
      // 单价:如果计成本金额=本币无税金额，取发票本币无税单价，否则取计成本金额/发票数量。
      if (InvoiceRowType.GOODS_ROW == invoiceSettleVOs[i].getFrowtype()
          .intValue()
          && MathTool.compareTo(invoiceSettleVOs[i].getNmny(),
              invoiceSettleVOs[i].getNcalcostmny()) != 0
          && MathTool.compareTo(invoiceSettleVOs[i].getNnum(),
              UFDouble.ZERO_DBL) != 0) {// 2012-04-26
                                        // tianft：对于货物发票，数量为0是没有意义的，但是得保证查询没问题
        UFDouble nprice =
            su.adjustSoPuPriceScale(
                invoiceSettleVOs[i].getNcalcostmny().div(
                    invoiceSettleVOs[i].getNnum()),
                invoiceSettleVOs[i].getCorigcurrencyid());
        invoiceSettleVOs[i].setNprice(nprice);
      }
      invoiceSettleVOs[i].setNmny(invoiceSettleVOs[i].getNcalcostmny());

      // 未结算数量 = 发票数量 - 累计结算数量
      invoiceSettleVOs[i]
          .setNcansettlenum(MathTool.sub(invoiceSettleVOs[i].getNnum(),
              invoiceSettleVOs[i].getNaccumsettnum()));
      // 未结算金额 = 发票金额 - 累计结算金额
      invoiceSettleVOs[i]
          .setNcansettlemny(MathTool.sub(invoiceSettleVOs[i].getNmny(),
              invoiceSettleVOs[i].getNaccumsettmny()));
      // 本次结算数量 = 未结算数量
      invoiceSettleVOs[i].setNcurrentsettlenum(invoiceSettleVOs[i]
          .getNcansettlenum());
      // 本次结算金额 = 未结算金额
      invoiceSettleVOs[i].setNcurrentinvoicesettlemny(invoiceSettleVOs[i]
          .getNcansettlemny());
      // 本次结算总金额
      invoiceSettleVOs[i].setNcurrentotalsettlemny(invoiceSettleVOs[i]
          .getNcansettlemny());
      // 合理损耗数量(因累计结算的发票合理损耗未回写，所以V60只有第一次结算时才带过去合理损耗)
      if (!MathTool.isZero(invoiceSettleVOs[i].getNaccumsettmny())) {
        invoiceSettleVOs[i].setNreasonwastenum(null);
      }
    }
  }

  /**
   * 计算本次结算还剩下多少未结算，会填充发票的Nresidualsettlenum
   * <p>
   * <b>参数说明</b>
   * 
   * @param listInvoice <p>
   * @author wangyf
   * @time 2010-3-23 下午02:24:49
   */
  public static void calResidualSettleNum(ArrayList<InvoiceSettleVO> listInvoice) {
    if (ListUtil.isEmpty(listInvoice)) {
      return;
    }
    for (InvoiceSettleVO vo : listInvoice) {
      vo.setNresidualsettlenum(MathTool.sub(
          InvoiceSettleVOUtil.getCurrentRealSettleNum(vo),
          vo.getNcurrentaccumsettlenum()));
    }
  }

  /**
   * 从发票VO转至货物结算VO，不处理费用和折扣<br>
   * 这里不再到后台查询，直接转换，直接使用全VO作为视图VO
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voOrgInvoice 发票VO
   * @return <p>
   * @author wangyf
   * @time 2009-7-2 下午04:43:53
   */
  public static InvoiceSettleVO[] convertFromInvoice(InvoiceVO voOrgInvoice) {
    List<InvoiceSettleVO> listMaterial = new ArrayList<InvoiceSettleVO>();
    for (int i = 0; i < voOrgInvoice.getChildrenVO().length; i++) {
      InvoiceItemVO voItem = voOrgInvoice.getChildrenVO()[i];
      InvoiceHeaderVO voHeader = voOrgInvoice.getParentVO();

      InvoiceSettleVO voWillSettle = null;
      if (voItem.getFrowtype().intValue() == InvoiceRowType.GOODS_ROW) {// 货物
        voWillSettle = new InvoiceSettleVO();
        voWillSettle.setVO(voHeader);
        voWillSettle.setVO(voItem);
        listMaterial.add(voWillSettle);
      }

    }
    InvoiceSettleVO[] ssVos =
        listMaterial.toArray(new InvoiceSettleVO[listMaterial.size()]);
    InvoiceSettleVOUtil.calInvoiceCanSettleValue(ssVos);
    return ssVos;
  }

  /**
   * 手工结算，从界面的VO转至发票结算VO
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaMatch
   * @return <p>
   * @author wangyf
   * @time 2010-1-28 上午09:46:14
   */
  public static InvoiceSettleVO[] convertFromMatchMaterialVO(
      MatchMaterialVO[] voaMatch) {

    if (voaMatch == null || voaMatch.length == 0) {
      return null;
    }

    ArrayList<InvoiceSettleVO> listVO = new ArrayList<InvoiceSettleVO>();
    int iLen = voaMatch.length;
    for (int i = 0; i < iLen; i++) {
      if (ValueUtils.getBoolean(voaMatch[i].getBinvoice())) {
        // 得到原始的VO
        InvoiceSettleVO voDest =
            (InvoiceSettleVO) voaMatch[i].getInvoiceSettleVO().clone();

        // 加入从界面得到的数据
        // 本次发票结算数量、本次发票结算金额、合理损耗数量
        voDest.setNcurrentsettlenum(voaMatch[i].getNcurinvoicesettlenum());
        voDest.setNcurrentinvoicesettlemny(voaMatch[i]
            .getNcurinvoicesettlemny());
        voDest.setNreasonwastenum(voaMatch[i].getNreasonwastenum());
        // 折扣、成本要素1-8
        voDest.setNdiscount(voaMatch[i].getNdiscount());
        voDest.setNcostfactor1(voaMatch[i].getNcostfactor1());
        voDest.setNcostfactor2(voaMatch[i].getNcostfactor2());
        voDest.setNcostfactor3(voaMatch[i].getNcostfactor3());
        voDest.setNcostfactor4(voaMatch[i].getNcostfactor4());
        voDest.setNcostfactor5(voaMatch[i].getNcostfactor5());
        voDest.setNcostfactor6(voaMatch[i].getNcostfactor6());
        voDest.setNcostfactor7(voaMatch[i].getNcostfactor7());
        voDest.setNcostfactor8(voaMatch[i].getNcostfactor8());
        // 货物调整 金额add by liangchen1
        voDest.setNadjustmny(voaMatch[i].getNadjustmny());
        // 本次结算总金额
        voDest.setNcurrentotalsettlemny(voaMatch[i].getNcurseetlemny());

        listVO.add(voDest);
      }
    }

    if (listVO.size() == 0) {
      return null;
    }

    return listVO.toArray(new InvoiceSettleVO[listVO.size()]);
  }

  /**
   * 计算得到本次实际可结算数量
   * <ul>
   * <li>正发票：即为本次结算数量－本次发票上的合理损耗数量</li><br>
   * <li>负发票：即为本次结算数量，按V5X规则，退货发票不支持合理损耗结算</li>
   * </ul>
   * 
   * @param vo
   * @return 本次实际可结算数量
   */
  public static UFDouble getCurrentRealSettleNum(InvoiceSettleVO vo) {

    return MathTool.greaterThan(vo.getNcurrentsettlenum(), UFDouble.ZERO_DBL) ? MathTool
        .sub(vo.getNcurrentsettlenum(), vo.getNreasonwastenum()) : vo
        .getNcurrentsettlenum();
  }

  /**
   * 得到发票的结算查询类型，是用于采购结算，还是用于消耗汇总结算
   * 
   * @param vo
   * @param defaultType 自制的默认为何种类型
   * @return
   * @see IInvoiceSettleQuery.QRY_TYPE_PO
   * @see IInvoiceSettleQuery.QRY_TYPE_VMI
   */
  public static String getQueryType(InvoiceVO vo, String defaultType) {
    if (InvoiceVOUtil.isSelfMade(vo)) {
      return defaultType;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      String srcTT = item.getCsourcetypecode();
      if (ICBillType.VmiSum.getCode().equals(srcTT)) {
        return IInvoiceSettleQuery.QRY_TYPE_VMI;
      }
    }
    return IInvoiceSettleQuery.QRY_TYPE_PO;
  }

  /**
   * 该发票是否进行过费用分摊：折扣＝0 而且 成本要素1-5为零时，未进行过分摊。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   * @return <p>
   * @author wangyf
   * @time 2010-1-28 下午01:46:47
   */
  public static boolean isAllot(InvoiceSettleVO voInvoice) {
    return MathTool.compareTo(voInvoice.getNdiscount(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor1(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor2(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor3(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor4(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor5(), UFDouble.ZERO_DBL) != 0
        // add by liangchen1 调整货物
        || MathTool.compareTo(voInvoice.getNadjustmny(), UFDouble.ZERO_DBL) != 0;
  }

  /**
   * 当前结算数量是否已完成
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voStock
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 上午10:29:19
   */
  public static boolean isCurrentSettleFinished(InvoiceSettleVO voInvoice) {
    if (UFBoolean.TRUE.equals(voInvoice.getBvirtual())
        && MathTool.isZero(voInvoice.getNmny())
        && MathTool.compareTo(voInvoice.getNcurrentaccumsettlenum(),
            voInvoice.getNnum()) != 0) {
      // 虚拟发票支持零金额结算
      return false;
    }
    return MathTool.compareTo(voInvoice.getNcurrentotalsettlemny(),
        voInvoice.getNcurrentaccumtotalsettlemny()) == 0;
  }

  /**
   * 发票是否未关联订单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return true发票关联了订单；false未关联订单。
   *         <p>
   * @author wangyf
   * @time 2010-1-19 上午10:43:35
   */
  public static boolean isOrderRelated(InvoiceSettleVO voSettle) {
    if (voSettle.getPk_order() == null
        || voSettle.getPk_order().trim().length() == 0) {
      return false;
    }

    return true;
  }

  /**
   * 发票是否未关联订单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return true发票关联了订单；false未关联订单。
   *         <p>
   * @author wangyf
   * @time 2010-1-19 上午10:43:35
   */
  public static boolean isStockRelated(InvoiceSettleVO voSettle) {

    // 45采购入、47委外入、4T期初暂估、50VMI
    if (!StringUtil.isEmptyWithTrim(voSettle.getCsourcebid())
        && (voSettle.getCsourcetypecode().equals(
            ICBillType.PurchaseIn.getCode())
            || voSettle.getCsourcetypecode()
                .equals(ICBillType.VmiSum.getCode())
            || voSettle.getCsourcetypecode().equals(
                POBillType.InitEstimate.getCode()) || voSettle
            .getCsourcetypecode().equals(ICBillType.SubContinIn.getCode()))) {
      return true;
    }

    return false;
  }

  /**
   * 方法功能描述：把费用折扣类发票数据从匹配界面上向模型中同步
   * <p>
   * <b>参数说明</b>
   * 
   * @param uiInvoices 界面上的数据
   * @param modelFees 模型中的费用类发票
   * @param modelDiscounts 模型中的折扣类发票
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-3 下午11:18:07
   */
  public static void synchFeeAndDiscounts(FeeDiscountSettleVO[] uiInvoices,
      FeeDiscountSettleVO[] modelFees, FeeDiscountSettleVO[] modelDiscounts) {
    if (ArrayUtils.isEmpty(uiInvoices)) {
      return;
    }

    // 同步当前Model中劳务类、折扣类发票的当前结算金额(结算时、前台费用分摊时调用)
    Map<String, FeeDiscountSettleVO> feeAndDiscounts =
        new HashMap<String, FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(modelFees)) {
      for (FeeDiscountSettleVO modelFee : modelFees) {
        feeAndDiscounts.put(modelFee.getPk_invoice_b(), modelFee);
      }
    }
    if (!ArrayUtils.isEmpty(modelDiscounts)) {
      for (FeeDiscountSettleVO modelDiscount : modelDiscounts) {
        feeAndDiscounts.put(modelDiscount.getPk_invoice_b(), modelDiscount);
      }
    }

    for (FeeDiscountSettleVO uiInvoice : uiInvoices) {
      String bid = uiInvoice.getPk_invoice_b();
      UFDouble currMny = uiInvoice.getNcurrentinvoicesettlemny();
      FeeDiscountSettleVO modelData = feeAndDiscounts.get(bid);
      modelData.setNcurrentinvoicesettlemny(currMny);
    }
  }

  /**
   * 方法功能描述：把费用折扣类发票数据从匹配界面上向模型中同步
   * <p>
   * <b>参数说明</b>
   * 
   * @param uiInvoices 界面上的数据
   * @param modelFees 模型中的费用类发票
   * @param modelDiscounts 模型中的折扣类发票
   * @param modelAdjs 模型中的调整发票
   *          <p>
   * @since 6.0
   * @author wangzhqf
   * @time 2013年10月25日8:32:47
   */
  public static void synchFeeDiscountsAdjs(FeeDiscountSettleVO[] uiInvoices,
      FeeDiscountSettleVO[] modelFees, FeeDiscountSettleVO[] modelDiscounts,
      FeeDiscountSettleVO[] modelAdjs) {
    if (ArrayUtils.isEmpty(uiInvoices)) {
      return;
    }

    // 同步当前Model中劳务类、折扣类发票、调整发票的当前结算金额(结算时、前台费用分摊时调用)
    Map<String, FeeDiscountSettleVO> feeAndDiscountsAdjs =
        new HashMap<String, FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(modelFees)) {
      for (FeeDiscountSettleVO modelFee : modelFees) {
        feeAndDiscountsAdjs.put(modelFee.getPk_invoice_b(), modelFee);
      }
    }
    if (!ArrayUtils.isEmpty(modelDiscounts)) {
      for (FeeDiscountSettleVO modelDiscount : modelDiscounts) {
        feeAndDiscountsAdjs.put(modelDiscount.getPk_invoice_b(), modelDiscount);
      }
    }
    if (!ArrayUtils.isEmpty(modelAdjs)) {
      for (FeeDiscountSettleVO modelAdj : modelAdjs) {
        feeAndDiscountsAdjs.put(modelAdj.getPk_invoice_b(), modelAdj);
      }
    }

    for (FeeDiscountSettleVO uiInvoice : uiInvoices) {
      String bid = uiInvoice.getPk_invoice_b();
      UFDouble currMny = uiInvoice.getNcurrentinvoicesettlemny();
      FeeDiscountSettleVO modelData = feeAndDiscountsAdjs.get(bid);
      modelData.setNcurrentinvoicesettlemny(currMny);
    }
  }

  /**
   * 方法功能描述：把发票数据从匹配界面上向模型中同步
   * <p>
   * <b>参数说明</b>
   * 
   * @param uiMatchVos 界面上的MatchMaterialVO
   * @param modelMatchVos 模型中的MatchMaterialVO
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-3 下午10:37:48
   */
  public static void synchInvoiceOfMatchMaterial(MatchMaterialVO[] uiMatchVos,
      MatchMaterialVO[] modelMatchVos) {
    if (ArrayUtils.isEmpty(uiMatchVos) || ArrayUtils.isEmpty(modelMatchVos)) {
      return;
    }

    Map<String, MatchMaterialVO> matchVoMap =
        new HashMap<String, MatchMaterialVO>();
    for (MatchMaterialVO modelMatchVo : modelMatchVos) {
      if (ValueUtils.getBoolean(modelMatchVo.getBinvoice())) {
        matchVoMap.put(modelMatchVo.getPk_billbid(), modelMatchVo);
      }
    }

    for (MatchMaterialVO uiMatchVo : uiMatchVos) {
      if (!ValueUtils.getBoolean(uiMatchVo.getBinvoice())) {
        continue;
      }
      // 得到模型中的VO
      MatchMaterialVO modelMatchVo = matchVoMap.get(uiMatchVo.getPk_billbid());

      // 从模型中的匹配VO中拿到发票VO
      InvoiceSettleVO invoice = modelMatchVo.getInvoiceSettleVO();

      // 加入从界面得到的数据
      // 本次发票结算数量、本次发票结算金额、合理损耗数量
      invoice.setNcurrentsettlenum(uiMatchVo.getNcurinvoicesettlenum());
      invoice.setNcurrentinvoicesettlemny(uiMatchVo.getNcurinvoicesettlemny());
      invoice.setNreasonwastenum(uiMatchVo.getNreasonwastenum());
      // 折扣、成本要素1-8
      invoice.setNdiscount(uiMatchVo.getNdiscount());
      invoice.setNcostfactor1(uiMatchVo.getNcostfactor1());
      invoice.setNcostfactor2(uiMatchVo.getNcostfactor2());
      invoice.setNcostfactor3(uiMatchVo.getNcostfactor3());
      invoice.setNcostfactor4(uiMatchVo.getNcostfactor4());
      invoice.setNcostfactor5(uiMatchVo.getNcostfactor5());
      invoice.setNcostfactor6(uiMatchVo.getNcostfactor6());
      invoice.setNcostfactor7(uiMatchVo.getNcostfactor7());
      invoice.setNcostfactor8(uiMatchVo.getNcostfactor8());
      // 货物调整 add by liangchen1
      invoice.setNadjustmny(uiMatchVo.getNadjustmny());
      // 本次结算总金额
      invoice.setNcurrentotalsettlemny(uiMatchVo.getNcurseetlemny());
    }
  }
}

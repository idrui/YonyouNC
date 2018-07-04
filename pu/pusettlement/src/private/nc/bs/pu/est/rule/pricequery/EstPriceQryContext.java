/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 下午06:19:19
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pu.est.util.EstRelationCalcItem;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单的询价处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 下午06:19:19
 */
public class EstPriceQryContext {
  private GoodsEstVO[] heads;

  private IEstPriceQueryInfoProvide[] pqinfo;

  private AbstractEstPriceQueryStrategy strategy;

  /**
   * PurchsInEstPriceQryProcesser 的构造子
   * 
   * @param heads
   * @param pss
   */
  public EstPriceQryContext(GoodsEstVO[] heads) {
    this.heads = heads;
    this.pqinfo = heads;
  }

  /**
   * 方法功能描述：是否已经询价完成。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-21 下午02:17:34
   */
  public boolean isComplete() {
    return ArrayUtils.isEmpty(this.pqinfo);
  }

  /**
   * 方法功能描述：根据构造时传入的参数进行询价处理。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:17:04
   */
  public void process() {
    Map<String, GoodsEstVO> voMap = CirVOUtil.createKeyVOMap(this.heads);
    if (null == this.strategy) {
      return;
    }
    EstPriceQryResltData[] qryReslt = this.strategy.queryPrice(this.pqinfo);
    this.setPrice(voMap, qryReslt);
    this.pqinfo = this.getNoPricePqInfo(this.pqinfo, qryReslt);
  }

  /**
   * 方法功能描述：设置需要使用的询价策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param strategy
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-21 下午02:15:55
   */
  public void setStrategy(AbstractEstPriceQueryStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * 方法功能描述：得到需要继续询价(上次未询到)的询价信息VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param oldPqinfo
   *          上次询价信息VO
   * @param qryReslt
   *          上次询价结果VO
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:17:39
   */
  private IEstPriceQueryInfoProvide[] getNoPricePqInfo(
      IEstPriceQueryInfoProvide[] oldPqinfo, EstPriceQryResltData[] qryReslt) {
    List<IEstPriceQueryInfoProvide> newPqinfo =
        new ArrayList<IEstPriceQueryInfoProvide>();
    for (int i = 0; i < qryReslt.length; i++) {
      EstPriceQryResltData reslt = qryReslt[i];
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getOrigPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getOrigTaxPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getTaxPrice()))) {
        newPqinfo.add(oldPqinfo[i]);
      }
    }
    IEstPriceQueryInfoProvide[] newPqinfoAry = null;
    if (0 < newPqinfo.size()) {
      newPqinfoAry =
          new ListToArrayTool<IEstPriceQueryInfoProvide>()
              .convertToArray(newPqinfo);
    }
    return newPqinfoAry;
  }

  /**
   * 方法功能描述：设置无税价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          暂估VO
   * @param reslt
   *          询价结果
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:21:14
   */
  private void setNoTaxPrice(GoodsEstVO vo, EstPriceQryResltData reslt) {
    UFDouble origprice = MathTool.nvl(reslt.getOrigPrice());
    UFDouble price = MathTool.nvl(reslt.getPrice());
    String chgKey = null;
    if (!UFDouble.ZERO_DBL.equals(origprice)) {
      vo.setNestoprice(origprice);
      chgKey = GoodsEstVO.NESTOPRICE;
    }
    else {
      if (!UFDouble.ZERO_DBL.equals(price)) {
        vo.setNestprice(price);
        chgKey = GoodsEstVO.NESTPRICE;
      }
    }
    // 补充税率和扣税类别，询供应商价目表时处理
    if (reslt.getTaxRate() != null) {
      vo.setNesttaxrate(reslt.getTaxRate());
    }
    if (reslt.getTaxTypeFlag() != null) {
      vo.setFesttaxtype(reslt.getTaxTypeFlag());
    }
    // wuxla V61 询供应商价目表后得到的不可抵扣税率，覆盖暂估的不可抵扣税率
    if (reslt.getNosubTaxRate() != null) {
      vo.setNestnosubtaxrate(reslt.getNosubTaxRate());
    }
    if (null == chgKey) {
      return;
    }
    EstRelationCalcUtil util =
        new EstRelationCalcUtil(vo.getPk_group(), new EstRelationCalcItem());
    util.calcVO(vo, chgKey, PricePriority.PRICE_PRIOR_TO_TAXPRICE);
  }

  /**
   * 方法功能描述：将一轮询价之后已经询到的价格设置到暂估VO中。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voMap
   *          暂估VO的map
   * @param qryReslt
   *          上一轮询价的结果
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:19:39
   */
  private void setPrice(Map<String, GoodsEstVO> voMap,
      EstPriceQryResltData[] qryReslt) {
    for (int i = 0; i < qryReslt.length; i++) {
      EstPriceQryResltData reslt = qryReslt[i];
      GoodsEstVO vo = voMap.get(reslt.getBID());
      // 全部信息取来源订单的信息，不用重新计算
      if (!reslt.isNeedCalc()) {
        vo.setNestprice(reslt.getPrice());
        vo.setNesttaxprice(reslt.getTaxPrice());
        vo.setNestmny(reslt.getMny());
        vo.setNesttaxmny(reslt.getTax());
        vo.setNesttotalmny(reslt.getTotalmny());
        vo.setNestoprice(reslt.getOrigPrice());
        vo.setNestotaxprice(reslt.getOrigTaxPrice());
        vo.setNestomny(reslt.getOrigmny());
        // wuxla V61 去掉原币税额
        // vo.setNestotaxmny(reslt.getOrigtax());
        vo.setNestototalmny(reslt.getOrigtotalmny());

        // wuxla V61 将VAT信息带过来
        vo.setNestnosubtax(reslt.getNosubtax());
        vo.setNestcalcostmny(reslt.getCalcostmny());
        vo.setNestcaltaxmny(reslt.getCaltaxmny());
      }
      else {
        PricePriority pp = EstVOUtil.getPO28(vo.getPk_purchaseOrg());
        if (PricePriority.TAXPRICE_PRIOR_TO_PRICE == pp) {
          this.setTaxPrice(vo, reslt);
        }
        else {
          this.setNoTaxPrice(vo, reslt);
        }
      }
    }
  }

  /**
   * 方法功能描述：设置含税价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          暂估VO
   * @param reslt
   *          询价结果
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:21:29
   */
  private void setTaxPrice(GoodsEstVO vo, EstPriceQryResltData reslt) {
    UFDouble origTaxprice = MathTool.nvl(reslt.getOrigTaxPrice());
    UFDouble taxprice = MathTool.nvl(reslt.getTaxPrice());
    UFDouble origprice = MathTool.nvl(reslt.getOrigPrice());
    UFDouble price = MathTool.nvl(reslt.getPrice());
    String chgKey = null;
    if (UFDouble.ZERO_DBL.equals(origTaxprice)) {
      if (UFDouble.ZERO_DBL.equals(taxprice)) {
        if (UFDouble.ZERO_DBL.equals(origprice)) {
          vo.setNestprice(price);
          chgKey = GoodsEstVO.NESTPRICE;
        }
        else {
          vo.setNestoprice(origprice);
          chgKey = GoodsEstVO.NESTOPRICE;
        }
      }
      else {
        vo.setNesttaxprice(taxprice);
        chgKey = GoodsEstVO.NESTTAXPRICE;
      }
    }
    else {
      vo.setNestotaxprice(origTaxprice);
      chgKey = GoodsEstVO.NESTOTAXPRICE;
    }
    // 补充税率和扣税类别，询供应商价目表时处理
    if (reslt.getTaxRate() != null) {
      vo.setNesttaxrate(reslt.getTaxRate());
    }
    if (reslt.getTaxTypeFlag() != null) {
      vo.setFesttaxtype(reslt.getTaxTypeFlag());
    }
    // wuxla V61 询供应商价目表后得到的不可抵扣税率，覆盖暂估的不可抵扣税率
    if (reslt.getNosubTaxRate() != null) {
      vo.setNestnosubtaxrate(reslt.getNosubTaxRate());
    }
    if (null == chgKey) {
      return;
    }
    EstRelationCalcUtil util =
        new EstRelationCalcUtil(vo.getPk_group(), new EstRelationCalcItem());
    util.calcVO(vo, chgKey, PricePriority.TAXPRICE_PRIOR_TO_PRICE);
  }
}

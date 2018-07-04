/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 上午11:08:11
 */
package nc.impl.pu.m25;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pu.m25.pricequery.HqHpPriceQuery;
import nc.impl.pu.m25.pricequery.OrderNewestPriceQuery;
import nc.impl.pu.m25.pricequery.OrderPriceQuery;
import nc.impl.pu.m25.pricequery.PlanPriceQuery;
import nc.impl.pu.m25.pricequery.PurchaseInPriceQuery;
import nc.impl.pu.m25.pricequery.RefCostPriceQuery;
import nc.impl.pu.m25.pricequery.SupplierPriceQuery;
import nc.itf.pu.m25.IDefaultPriceQuery;
import nc.itf.pu.m25.IPriceQuery;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 发票询默认价实现组件
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 上午11:08:11
 */
public class DefaultPriceQueryImpl implements IDefaultPriceQuery {

  @Override
  public InvoicePriceQueryVO[] queryHqHpPrice(InvoicePriceQueryVO[] para)
      throws BusinessException {
    try {
      InvoicePriceQueryVO[] noPricePara = this.pickupNoPricePara(para);
      if (ArrayUtils.isEmpty(noPricePara)) {
        return para;
      }
      // 未寻到价格继续询价
      new HqHpPriceQuery().query(noPricePara);
      return para;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return para;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IDefaultPriceQuery#queryPlanPrice(nc.vo.pu.m25.entity.InvoicePriceQueryVO[])
   */
  @Override
  public InvoicePriceQueryVO[] queryPlanPrice(InvoicePriceQueryVO[] para)
      throws BusinessException {
    if (ArrayUtils.isEmpty(para)) {
      return para;
    }
    try {
      String fiorg = this.getFiOrg(para);
      if (null == fiorg) {
        return para;
      }
      new PlanPriceQuery().query(para);
      return para;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return para;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IDefaultPriceQuery#queryPrice(nc.vo.pu.m25.entity.InvoicePriceQueryVO[])
   */
  @Override
  public InvoicePriceQueryVO[] queryPriceBySysPara(InvoicePriceQueryVO[] para)
      throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(para)) {
        return para;
      }
      // 清除原有的单价
      this.clearParaOldPrice(para);
      String fiorg = this.getFiOrg(para);
      if (null == fiorg) {
        return para;
      }
      // 取询价参数
      PriceSource[] PO83 = PUSysParamUtil.getPO83(fiorg);
      if (ArrayUtils.isEmpty(PO83)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0035")/*
                                                                     * @res
                                                                     * "读取参数PO83[采购发票价格来源]错误!"
                                                                     */);
      }
      InvoicePriceQueryVO[] noPricePara = para;
      for (PriceSource priceSrcCode : PO83) {
        IPriceQuery queryer = this.getPriceQueryer(priceSrcCode);
        if (queryer == null) {
          continue;
        }
        queryer.query(noPricePara);
        // 未寻到价格继续询价
        noPricePara = this.pickupNoPricePara(noPricePara);
        // 全部寻到价格，不再继续
        if (0 == noPricePara.length) {
          break;
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return para;
  }

  @Override
  public InvoicePriceQueryVO[] queryPriceWithHqHp(InvoicePriceQueryVO[] para)
      throws BusinessException {
    // 参数价格优先，优质优价次之
    try {
      InvoicePriceQueryVO[] resultPara = this.queryPriceBySysPara(para);
      return this.queryHqHpPrice(resultPara);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return para;
  }

  private void clearParaOldPrice(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      vo.setNorigprice(null);
      vo.setNorigtaxprice(null);
      vo.setNprice(null);
      vo.setNtaxprice(null);
    }
  }

  private String getFiOrg(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtils.isBlank(vo.getPk_org())) {
        return vo.getPk_org();
      }
    }
    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param priceSrcCode
   * @return <p>
   * @since 6.0
   * @author zhaoyha 修改历史 1.2010-7-1 需求去掉计划价和订单最低价
   */
  private IPriceQuery getPriceQueryer(PriceSource priceSrcCode) {
    if (PriceSource.OrderPice == priceSrcCode) {
      return new OrderPriceQuery();// 订单价
    }
    if (PriceSource.OrderNewestPrice == priceSrcCode) {
      return new OrderNewestPriceQuery();// 订单最新价
    }
    if (PriceSource.RefCostPrice == priceSrcCode) {
      return new RefCostPriceQuery();// 参考成本
    }
    if (PriceSource.SupplierPrice == priceSrcCode) {
      return new SupplierPriceQuery();// 供应商价目表
    }
    if (PriceSource.PurchaseInPrice == priceSrcCode) {
      return new PurchaseInPriceQuery();// 库存采购入库单价
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004050_0", "04004050-0036")/*
                                                                 * @res
                                                                 * "不支持的询价来源类型："
                                                                 */
        + priceSrcCode);
    return null;
  }

  private InvoicePriceQueryVO[] pickupNoPricePara(InvoicePriceQueryVO[] para) {
    List<InvoicePriceQueryVO> noPriceList =
        new ArrayList<InvoicePriceQueryVO>();
    for (InvoicePriceQueryVO vo : para) {
      UFDouble orgPrice = vo.getNorigprice();
      UFDouble orgTaxPrice = vo.getNorigtaxprice();
      UFDouble price = vo.getNprice();
      UFDouble taxPrice = vo.getNtaxprice();
      if (0 == MathTool.compareTo(orgPrice, UFDouble.ZERO_DBL)
          && 0 == MathTool.compareTo(orgTaxPrice, UFDouble.ZERO_DBL)
          && 0 == MathTool.compareTo(price, UFDouble.ZERO_DBL)
          && 0 == MathTool.compareTo(taxPrice, UFDouble.ZERO_DBL)) {
        noPriceList.add(vo);
      }
    }
    return noPriceList.toArray(new InvoicePriceQueryVO[noPriceList.size()]);
  }

}

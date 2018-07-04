/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 上午09:07:28
 */
package nc.vo.pu.m25.pub;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IDefaultPriceQuery;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票询价相关
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-14 上午09:07:28
 */
public class InvoiceQueryPriceHandler {

  /** 要做询价处理的发票vo */
  private InvoiceVO[] invoiceVOs = null;

  /** 价格策略 map 采购组织=> 价格策略 */
  private Map<String, UFBoolean> pricePriorityMap =
      new HashMap<String, UFBoolean>();

  private Map<Integer, InvoiceVO> voIndexMap = null;

  public InvoiceQueryPriceHandler(InvoiceVO[] vos) {
    this.invoiceVOs = vos;
  }

  public Map<Integer, InvoiceVO> getVoIndexMap() {
    if (this.voIndexMap == null) {
      this.voIndexMap = new HashMap<Integer, InvoiceVO>();
      for (int i = 0; i < this.invoiceVOs.length; i++) {
        this.voIndexMap.put(Integer.valueOf(i), this.invoiceVOs[i]);
      }
    }
    return this.voIndexMap;
  }

  /**
   * 优质优价处理
   */
  public void handleHqHpPrice() {
    InvoicePriceQueryVO[] queryVOs =
        InvoiceVOUtil.getHqHpQueryVO(this.invoiceVOs);
    if (queryVOs == null) {
      return;
    }
    queryVOs = this.queryHqHpPrice(queryVOs);
    this.matchPrice(queryVOs);

  }

  /**
   * 带优质优价处理
   */
  public void handlePriceWithHqHp() {
    InvoicePriceQueryVO[] queryVOs =
        InvoiceVOUtil.getInvoicePriceQuerVO(this.invoiceVOs);
    if (queryVOs == null) {
      return;
    }
    queryVOs = this.queryPriceWithHqHp(queryVOs);
    this.matchPrice(queryVOs);

  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-4-13 下午04:01:06
   */
  public void handleQueryPrice() {
    InvoicePriceQueryVO[] queryVOs =
        InvoiceVOUtil.getInvoicePriceQuerVO(this.invoiceVOs);
    if (queryVOs == null) {
      return;
    }
    queryVOs = this.queryPrice(queryVOs);
    this.matchPrice(queryVOs);

  }

  /**
   * 单价计算
   * 
   * @param heder
   * @param item
   * @param queryVO
   * @param priceItemKey
   * @return 需要计算返回true，否则false
   */
  private boolean calByPrice(InvoiceHeaderVO heder, InvoiceItemVO item,
      InvoicePriceQueryVO queryVO, String priceItemKey) {

    UFDouble curPrice = (UFDouble) item.getAttributeValue(priceItemKey);
    UFDouble queryPrice = (UFDouble) queryVO.getAttributeValue(priceItemKey);

    if (queryPrice == null || queryPrice.equals(curPrice)) {
      return false;
    }
    item.setAttributeValue(priceItemKey, queryPrice);
    // 不可抵扣税率
    if (queryVO.getNNoSubTaxRate() != null) {
      item.setNnosubtaxrate(queryVO.getNNoSubTaxRate());
    }
    // 税率
    if (queryVO.getNtaxrate() != null) {
      item.setNtaxrate(queryVO.getNtaxrate());
    }
    // 扣税类别
    if (queryVO.getFtaxtypeflag() != null) {
      item.setFtaxtypeflag(queryVO.getFtaxtypeflag());
    }
    // 单价金额换算工具类
    VORelationCalculate calculate = new VORelationCalculate();
    // 由主本币无税单价折算
    calculate.calculate(heder, item, priceItemKey);
    return true;
  }

  /**
   * 方法功能描述：获取价格优先参数PO28
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_purchaseorg 采购组织
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-12 下午02:37:13
   */
  private boolean getPricePriority(String pk_purchaseorg) {
    UFBoolean prior = this.pricePriorityMap.get(pk_purchaseorg);
    // 重新查
    if (prior == null) {
      prior = UFBoolean.valueOf(PUSysParamUtil.getPO28For25(pk_purchaseorg));
      this.pricePriorityMap.put(pk_purchaseorg, prior);
    }
    return prior.booleanValue();
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-12 下午02:09:13
   */
  private IDefaultPriceQuery getPriceQuerySerivce() {
    return NCLocator.getInstance().lookup(IDefaultPriceQuery.class);
  }

  /**
   * 方法功能描述：匹配价格
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param queryVOs <p>
   * @since 6.0
   * @author tianft
   * @time 2010-7-1 下午01:55:29
   */
  private void matchPrice(InvoicePriceQueryVO[] queryVOs) {

    for (int i = 0; i < queryVOs.length; i++) {
      InvoiceVO vo = this.getVoIndexMap().get(queryVOs[i].getVoIndex());
      boolean isTaxPricePrior =
          this.getPricePriority(vo.getParentVO().getPk_purchaseorg());
      String priceItemKey =
          isTaxPricePrior ? InvoiceItemVO.NORIGTAXPRICE
              : InvoiceItemVO.NORIGPRICE;
      InvoiceItemVO item = vo.getChildrenVO()[queryVOs[i].getItemIndex()];
      UFDouble queryPrice =
          (UFDouble) queryVOs[i].getAttributeValue(priceItemKey);
      // 查询到的主含税/主无税单价为空，则比较主本币无税单价
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(queryPrice))) {
        // 主本币无税计算
        if (!this.calByPrice(vo.getParentVO(), item, queryVOs[i],
            InvoiceItemVO.NPRICE)) {
          // 主本币含税计算
          this.calByPrice(vo.getParentVO(), item, queryVOs[i],
              InvoiceItemVO.NTAXPRICE);
        }
      }
      else {// 查询到的主含税/主无税单价不为空
        this.calByPrice(vo.getParentVO(), item, queryVOs[i], priceItemKey);
      }
    }
  }

  private InvoicePriceQueryVO[] queryHqHpPrice(InvoicePriceQueryVO[] queryVOs) {
    try {
      return this.getPriceQuerySerivce().queryHqHpPrice(queryVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryVOs
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-12 下午02:09:08
   */
  private InvoicePriceQueryVO[] queryPrice(InvoicePriceQueryVO[] queryVOs) {
    try {
      return this.getPriceQuerySerivce().queryPriceBySysPara(queryVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  private InvoicePriceQueryVO[] queryPriceWithHqHp(
      InvoicePriceQueryVO[] queryVOs) {
    try {
      return this.getPriceQuerySerivce().queryPriceWithHqHp(queryVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
      return null;
    }
  }
}

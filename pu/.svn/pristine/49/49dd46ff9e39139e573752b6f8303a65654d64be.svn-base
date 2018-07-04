package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackFor25;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 
 * @description
 * 自制发票与来源于订单的入库单结算时回写订单的累计开票数量
 * @scene
 * 删除结算单,结算完毕保存结算单
 * @param
 * point 回写时点的枚举类（保存时、删除时） 
 *
 * @since 6.3
 * @version 2014-10-22 下午4:08:39
 * @author zhangshqb
 */
public class WritebackOrderRule implements IRule<SettleBillVO> {
  private WritebackPoint point = WritebackPoint.SAVE;

  private SettleEnvironment settleEnv;

  public WritebackOrderRule(WritebackPoint point) {
    this.point = point;
  }

  public WritebackOrderRule(WritebackPoint point, SettleEnvironment settleEnv) {
    this.settleEnv = settleEnv;
    this.point = point;
  }

  public WritebackPoint getPoint() {
    return this.point;
  }

  public SettleEnvironment getSettleEnv() {
    return this.settleEnv;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // 获得发票不是来源于订单的采购结算单行
    SettleBillItemVO[] items = this.getNotFromOrderItemVOs(vos);
    if (items.length == 0) {
      return;
    }
    // 根据结算单行构造回写参数对象数组
    OrderWriteBackParaFor25[] params = this.getWriteBackParams(items);

    // 回写订单
    IOrderWriteBackFor25 service = this.getWriteBackService();
    try {
      service.invoicePriceChk(params);
      service.writeBackFor25(params);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：获得发票不是来源于订单的结算单行
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 结算单数组
   * @return 发票不是来源于订单的结算单行
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-20 下午03:17:39
   */
  private SettleBillItemVO[] getNotFromOrderItemVOs(SettleBillVO[] vos) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] itemVos = vo.getChildrenVO();
      for (SettleBillItemVO itemVo : itemVos) {
        Integer rowType = itemVo.getFrowtype();
        if (EnumMatchRowType.StockInvoiceMatch.value().equals(rowType)
            && ICBillType.PurchaseIn.getCode().equals(
                itemVo.getVstockbilltype()) // TODO 赵玉行 只对采购入库单进行处理，是否还包含期初
            && itemVo.getPk_invoiceorder_b() == null
            && itemVo.getPk_stockorder_b() != null) {
          items.add(itemVo);
        }
      }
    }
    return items.toArray(new SettleBillItemVO[items.size()]);
  }

  private OrderWriteBackParaFor25[] getWriteBackParams(SettleBillItemVO[] items) {
    // 获得发票含税单价
    Map<String, UFDouble> taxPrices = this.queryTaxPrice(items);
    // 获得发票无税单价
    Map<String, UFDouble> prices = this.queryPrice(items);
    OrderWriteBackParaFor25[] temps = new OrderWriteBackParaFor25[items.length];
    for (int i = 0; i < items.length; i++) {
      temps[i] = new OrderWriteBackParaFor25();
      temps[i].setBid(items[i].getPk_stockorder_b());
      temps[i].setHid(items[i].getPk_stockorder());
      temps[i].setInvoicebid(items[i].getPk_invoice_b());
      temps[i].setDiscard(this.point == WritebackPoint.DELETE);
      // 要回写的数量
      UFDouble diffNum = MathTool.nvl(items[i].getNsettlenum());
      if (this.point == WritebackPoint.DELETE) {
        diffNum = MathTool.oppose(diffNum);
      }
      temps[i].setDiffNum(diffNum);
      // 含税单价
      UFDouble taxPrice =
          MathTool.nvl(taxPrices.get(items[i].getPk_invoice_b()));
      temps[i].setNtaxprice(taxPrice);
      // 无税单价
      UFDouble price = MathTool.nvl(prices.get(items[i].getPk_invoice_b()));
      temps[i].setPrice(price);
      // 要回写的开票金额=发票含税单价*结算数量
      // 经过跟需求确认：此处不处理尾差
      temps[i].setDiffMny(taxPrice.multiply(diffNum));
      if (this.getSettleEnv() != null) {
        temps[i].setPriceUserConfirm(this.getSettleEnv()
            .isInvoicePriceOverOder());
      }
    }
    return temps;
  }

  private IOrderWriteBackFor25 getWriteBackService() {
    return NCLocator.getInstance().lookup(IOrderWriteBackFor25.class);
  }

  private Map<String, UFDouble> queryPrice(SettleBillItemVO[] items) {
    Set<String> bids = new LinkedHashSet<String>();
    for (SettleBillItemVO item : items) {
      bids.add(item.getPk_invoice_b());
    }
    IInvoiceSettleQuery service =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      map = service.queryPrice(bids.toArray(new String[bids.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return map;
  }

  private Map<String, UFDouble> queryTaxPrice(SettleBillItemVO[] items) {
    Set<String> bids = new LinkedHashSet<String>();
    for (SettleBillItemVO item : items) {
      bids.add(item.getPk_invoice_b());
    }
    IInvoiceSettleQuery service =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      map = service.queryTaxPrice(bids.toArray(new String[bids.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return map;
  }
}

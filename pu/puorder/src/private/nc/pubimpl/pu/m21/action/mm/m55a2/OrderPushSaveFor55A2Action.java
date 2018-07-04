/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 下午02:34:46
 */
package nc.pubimpl.pu.m21.action.mm.m55a2;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.pu.m21.rule.CheckOrderNotNullRule;
import nc.pubimpl.pu.m21.rule.CurrencyAndExchangeRule;
import nc.pubimpl.pu.m21.rule.NumCalcRule;
import nc.pubimpl.pu.m21.rule.OrganizationValueRule;
import nc.pubimpl.pu.m21.rule.PlanArrDateOrderDateRule;
import nc.pubimpl.pu.m21.rule.PlanArriveDateRule;
import nc.pubimpl.pu.m21.rule.RelationCalculateRule;
import nc.pubimpl.pu.m21.rule.SupplierValueRule;
import nc.pubimpl.pu.m21.rule.TrantypeRule;
import nc.pubimpl.pu.m21.rule.WarehouseValueRule;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.QuerySourceRule;
import nc.vo.pu.m21.rule.RowNoRule;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产订单推式生成采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 下午02:34:46
 */
public class OrderPushSaveFor55A2Action {

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>推式生成采购订单
   * </ul>
   * 
   * @param orderVOs
   * @param SourceReturnVOs
   * @return
   */
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs) {
    // 注册业务规则
    AroundProcesser<OrderVO> processer = new AroundProcesser<OrderVO>(null);
    this.addBeforeRule(processer, SourceReturnVOs);

    // 保存
    processer.before(orderVOs);
    OrderSaveBP bp = new OrderSaveBP(new OrderContext());
    OrderVO[] savedVOs = bp.save(orderVOs, null);
    processer.after(savedVOs);
    return savedVOs;
  }

  private void addBeforeRule(AroundProcesser<OrderVO> processer,
      SourceReturnVO[] sourceReturnVOs) {
    // 参数检查
    processer.addBeforeRule(new CheckOrderNotNullRule());
    // 补全行号
    processer.addBeforeRule(new RowNoRule());
    // 补全其他组织
    processer.addBeforeRule(new OrganizationValueRule());
    // 补全供应商信息
    processer.addBeforeRule(new SupplierValueRule());
    // vat信息补充
    processer.addBeforeRule(new OrderVatInfoSrcFillRule(false));
    // 寻源信息补充
    processer.addBeforeRule(new QuerySourceRule(sourceReturnVOs));
    // 补全币种和汇率
    processer.addBeforeRule(new CurrencyAndExchangeRule());
    // 补全仓库
    processer.addBeforeRule(new WarehouseValueRule());
    // 补全数量
    processer.addBeforeRule(new NumCalcRule());
    // 单价金额计算
    processer.addBeforeRule(new RelationCalculateRule());
    // 计划到货日期
    processer.addBeforeRule(new PlanArriveDateRule());
    processer.addBeforeRule(new PlanArrDateOrderDateRule());
    // 补全订单类型
    processer.addBeforeRule(new TrantypeRule());
    // 订单尾差倒挤
    processer.addBeforeRule(new OrderMarginRule(MMBillType.ProduceOrder
        .getCode(), null));
    // 根据参数PO08自动转换采购单位
    processer.addBeforeRule(new AutoSwitchPUAssistUnitRule());

  }
}

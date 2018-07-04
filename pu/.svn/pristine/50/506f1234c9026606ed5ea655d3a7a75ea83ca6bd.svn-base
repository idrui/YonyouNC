/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 下午09:41:09
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>开票关闭检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 下午09:41:09
 */
public class InvoiceCloseChkRule implements IRule<OrderViewVO> {

  private OrderViewVO[] orgViews;

  public InvoiceCloseChkRule(OrderViewVO[] orgViews) {
    this.orgViews = orgViews;
  }

  @Override
  public void process(OrderViewVO[] views) {
    // 补充规则
    Map<String, OrderViewVO> orgMap = CirVOUtil.createKeyVOMap(this.orgViews);
    for (OrderViewVO view : views) {
      // 需要确认负订单和退库的情况
      if (view.getBfinalclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0258", null, new String[]{view.getVordercode()})/*单据号：{0}已经最终关闭，采购发票不能再做修改！*/);
      }
      // "单据号：{0}已经最终关闭，采购发票不能再做修改！");
      OrderViewVO orgView = orgMap.get(view.getPk_order_b());
      UFDouble oldAccNum = orgView.getNaccuminvoicenum();
      UFDouble newAccNum = view.getNaccuminvoicenum();

      // 数量
      if (MathTool.absCompareTo(newAccNum, oldAccNum) > 0
          && view.getBinvoiceclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0259", null, new String[]{view.getVordercode(),view.getCrowno()})/*单据号：{0}行号：{1}已经开票关闭，采购发票不能再做修改！*/);
        // "单据号：{0}行号：{0}已经开票关闭，采购发票不能再做修改！");
      }
    }
  }
}

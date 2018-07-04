/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-16 上午09:58:58
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
 * <li>入库关闭检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-16 上午09:58:58
 */
public class StoreCloseChkRule implements IRule<OrderViewVO> {
  private OrderViewVO[] orgViews = null;

  public StoreCloseChkRule(OrderViewVO[] orgViews) {
    this.orgViews = orgViews;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> orgMap = CirVOUtil.createKeyVOMap(this.orgViews);
    for (OrderViewVO view : views) {
      OrderViewVO orgView = orgMap.get(view.getPk_order_b());
      UFDouble oldBackAccNum = orgView.getNbackstorenum();
      UFDouble newBackAccNum = view.getNbackstorenum();
      if (MathTool.compareTo(oldBackAccNum, newBackAccNum) != 0
          && !view.getBreturn().booleanValue()) {
        continue;
      }

      if (view.getBfinalclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0260", null, new String[] {
              view.getVordercode()
            })/* 单据号：{0}已经最终关闭，期初暂估单不能再做修改！ */);
      }

      UFDouble oldAccNum = orgView.getNaccumstorenum();
      UFDouble newAccNum = view.getNaccumstorenum();

      // 数量
      if (MathTool.absCompareTo(newAccNum, oldAccNum) <= 0) {
        continue;
      }
      else if (view.getBstockclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0261", null, new String[] {
              view.getVordercode(), view.getCrowno()
            })/* 单据号：{0}行号：{1}已经入库关闭，期初暂估单不能再做修改！ */);
      }
    }
  }

}

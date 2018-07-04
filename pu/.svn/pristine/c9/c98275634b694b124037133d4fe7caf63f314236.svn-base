/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 上午10:46:49
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单入库关闭检查
 * @scene
 *        采购入库单回写订单
 * @param OrderViewVO[] orgViews 采购订单的视图VO
 *          IOrderWriteBackPara[] vos 采购订单公共回写参数接口
 * @since 6.3
 * @version 2014-10-22 下午3:23:20
 * @author luojw
 */
public class StoreCloseChkRule implements IRule<OrderViewVO> {

  private OrderViewVO[] orgViews;

  private IOrderWriteBackPara[] vos;

  public StoreCloseChkRule(OrderViewVO[] orgViews, IOrderWriteBackPara[] vos) {
    this.orgViews = orgViews;
    this.vos = vos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> orgMap = CirVOUtil.createKeyVOMap(this.orgViews);
    Map<String, IOrderWriteBackPara> paraMap =
        OrderVOUtil.getInsance().createWBMap(this.vos);
    for (OrderViewVO view : views) {
      String pk_order_b = view.getPk_order_b();
      IOrderWriteBackPara para = paraMap.get(pk_order_b);
      if (para != null && para.isReturn()) {
        continue;
      }
      OrderViewVO orgView = orgMap.get(pk_order_b);
      UFDouble oldBackAccNum = orgView.getNbackstorenum();
      UFDouble newBackAccNum = view.getNbackstorenum();
      if (MathTool.compareTo(oldBackAccNum, newBackAccNum) != 0
          && !view.getBreturn().booleanValue()) {
        continue;
      }

      // 需要确认负订单和退库的情况
      if (view.getBfinalclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0254", null, new String[] {
              view.getVordercode()
            })/* 单据号：{0}已经最终关闭，入库单不能再做修改！ */);
        // "单据号：{0}已经最终关闭，入库单不能再做修改！");
      }

      UFDouble oldAccNum = orgView.getNaccumstorenum();
      UFDouble newAccNum = view.getNaccumstorenum();

      // 数量
      if (MathTool.absCompareTo(newAccNum, oldAccNum) <= 0) {
        continue;
      }
      else if (view.getBstockclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0255", null, new String[] {
              view.getVordercode(), view.getCrowno()
            })/* 单据号：{0}行号：{1}已经入库关闭，入库单不能再做修改！ */);
        // "单据号：{0}行号：{0}已经入库关闭，入库单不能再做修改！");
      }
    }
  }

}

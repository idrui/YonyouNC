/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 下午09:10:39
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-17 下午09:10:39
 */
public class ArriveCloseChkRule implements IRule<OrderViewVO> {

  private OrderViewVO[] orgViews;

  private IOrderWriteBackParaFor23[] vos;

  public ArriveCloseChkRule(OrderViewVO[] orgViews,
      IOrderWriteBackParaFor23[] vos) {
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
      // 累计退货数量，判断是否退货
      UFDouble oldBackAccNum = orgView.getNbackarrvnum();
      UFDouble newBackAccNum = view.getNbackarrvnum();
      if (MathTool.compareTo(oldBackAccNum, newBackAccNum) != 0
          && !view.getBreturn().booleanValue()) {
        continue;
      }

      // 需要确认负订单和退库的情况
      if (view.getBfinalclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0256", null, new String[] {
              view.getVordercode()
            })/* 单据号：{0}已经最终关闭，到货单不能再做修改！ */);
        // "单据号：{0}已经最终关闭，到货单不能再做修改！");
      }

      UFDouble oldAccNum = orgView.getNaccumarrvnum();
      UFDouble newAccNum = view.getNaccumarrvnum();

      // 数量
      if (MathTool.absCompareTo(newAccNum, oldAccNum) <= 0) {
        continue;
      }
      else if (view.getBarriveclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0257", null, new String[] {
              view.getVordercode(), view.getCrowno()
            })/* 单据号：{0}行号：{1}已经到货关闭，到货单不能再做修改！ */);
        // "单据号：{0}行号：{0}已经到货关闭，到货单不能再做修改！");
      }
    }
  }

}

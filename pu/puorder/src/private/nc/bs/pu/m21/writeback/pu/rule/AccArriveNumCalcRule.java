/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-1 下午01:22:54
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算累计到货(退)、途损数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-1 下午01:22:54
 */
public class AccArriveNumCalcRule implements IRule<OrderViewVO> {

  private IOrderWriteBackParaFor23[] wbVos;

  public AccArriveNumCalcRule(IOrderWriteBackParaFor23[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {

    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor23 vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      UFDouble diffNum = vo.getDiffNum();
      // 到货或基于原订单退货
      if (!vo.isReturn()) {
        UFDouble newAccNum = MathTool.add(view.getNaccumarrvnum(), diffNum);
        view.setNaccumarrvnum(newAccNum);
      }
      else {// 退货
        if (view.getBreturn().booleanValue()
            || view.getBrefwhenreturn().booleanValue()) {
          UFDouble newAccNum = MathTool.add(view.getNaccumarrvnum(), diffNum);
          view.setNaccumarrvnum(newAccNum);
        }
        UFDouble newAccBackNum =
            MathTool.add(view.getNbackarrvnum(), MathTool.oppose(diffNum));
        view.setNbackarrvnum(newAccBackNum);
      }
      // 累计途损数量
      UFDouble diffWasteNum = vo.getDiffWasteNum();
      UFDouble newWasteNum =
          MathTool.add(view.getNaccumwastnum(), diffWasteNum);
      view.setNaccumwastnum(newWasteNum);
    }
  }

}

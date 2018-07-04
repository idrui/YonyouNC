package nc.bs.pu.m21.writeback.dm.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackParaFor4804;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              运输单回写订单累计运输主数量
 * @scene
 *        运输单回写订单
 * @param
 *        IOrderWriteBackParaFor4804[] wbVos 运输单回写
 * @since 6.3
 * @version 2014-10-22 下午3:52:01
 * @author luojw
 */

public class AccDevNumCalcRule implements IRule<OrderViewVO> {
  private IOrderWriteBackParaFor4804[] wbVos;

  public AccDevNumCalcRule(IOrderWriteBackParaFor4804[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor4804 wbVo : this.wbVos) {
      OrderViewVO view = viewMap.get(wbVo.getBID());
      UFDouble newAccNum =
          MathTool.add(view.getNaccumdevnum(), wbVo.getDiffNum());
      view.setNaccumdevnum(newAccNum);
    }
  }

}

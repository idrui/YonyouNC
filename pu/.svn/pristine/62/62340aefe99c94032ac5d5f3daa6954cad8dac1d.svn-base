package nc.bs.pu.m21.maintain.rule.save;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              采购订单订单直运标记的处理
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午1:48:23
 * @author luojw
 */
public class OrderDirectPurchaseUpdateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    Set<String> trantypeCode = new HashSet<String>();
    for (OrderVO vo : vos) {
      trantypeCode.add(vo.getHVO().getVtrantypecode());
    }
    Map<String, UFBoolean> trantypeMap =
        TrantypeUtil.getInstance().getDirectPurchaseAttr(
            trantypeCode.toArray(new String[trantypeCode.size()]));
    for (OrderVO vo : vos) {
      if (vo.getHVO().getBdirect() == null) {
        vo.getHVO().setBdirect(trantypeMap.get(vo.getHVO().getVtrantypecode()));
        // 后面判断是新增或者修改是根据status，如果新增也改成update就错了。
        if (VOStatus.NEW != vo.getHVO().getStatus()) {
          vo.getHVO().setStatus(VOStatus.UPDATED);
        }
      }
    }
  }

}

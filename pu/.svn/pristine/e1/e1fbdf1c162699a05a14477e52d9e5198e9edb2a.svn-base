package nc.bs.pu.m21.query.dm;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.ic.rule.StorePrivilegeChkRule;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2010-12-13 下午06:02:02
 * @author wuxla
 */

public class OrderQueryFor4804BP {

  public OrderViewVO[] queryFor4804(String condition, UFBoolean isLazy) {
    OrderVO[] vos = OrderQueryUtil.queryFor45_23(condition, isLazy);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL4804);
    this.addRule(processer);
    vos = processer.after(vos);
    OrderViewVO[] views =
        new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
            .convert(vos);
    return views;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 计算可入库数量
    // processer.addAfterRule(new CanStockNumCalcRule());
    // 按库管员权限过滤
    processer.addAfterRule(new StorePrivilegeChkRule());
  }
}

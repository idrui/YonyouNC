package nc.bs.pu.m21.maintain.rule.save;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.et.M5720PUServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.ETBillType;
/**
 * 
 * @description
 * 采购订单回写出口合同单价
 * @scene
 * 采购订单回写出口合同
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-1-28 上午10:00:43
 * @author mengjian
 */
public class WriteBackPriceForET implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (null == vos || !SysInitGroupQuery.isETEnabled()) {
      return;
    }
    Map<String, UFDouble> pricemap = new HashMap<String, UFDouble>();
    for (OrderVO vo : vos) {
      OrderItemVO[] bvos = vo.getBVO();
      if (!ETBillType.CONTRACT.getCode().equals(bvos[0].getCsourcetypecode())) {
        continue;
      }
      for (OrderItemVO bvo : bvos) {
        pricemap.put(bvo.getCsourcebid(), bvo.getNqtprice());
      }
    }
    if (pricemap.size() == 0) {
      return;
    }
    M5720PUServices.writeback5720For21price(pricemap);
  }

}

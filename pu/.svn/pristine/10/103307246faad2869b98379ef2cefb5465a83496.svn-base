package nc.bs.pu.m23.upgrade.v63.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.sc.M61PUServices;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 到货单表体新增字段“扣税类别”的升级规则类
 * 
 * @since 6.3
 * @version 2013-4-10 下午04:29:08
 * @author fanly3
 */
public class FtaxtypeflagUpgradeRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    // 到货单来源采购订单bid
    Set<String> puOrderBid = new HashSet<String>();
    // 到货单来源委外订单bid
    Set<String> scOrderBid = new HashSet<String>();

    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        if (POBillType.Order.getCode().equals(itemVo.getCsourcetypecode())) {
          puOrderBid.add(itemVo.getCsourcebid());
        }
        else if (SCBillType.Order.getCode().equals(itemVo.getCsourcetypecode())) {
          scOrderBid.add(itemVo.getCsourcebid());
        }
      }
    }

    Map<String, Integer> puOrderMap = this.getPuOrderMap(puOrderBid);
    Map<String, Integer> scOrderMap = null;
    if (scOrderBid.size() > 0) {
      scOrderMap =
          M61PUServices.getFtaxtypeflagByBid(scOrderBid
              .toArray(new String[scOrderBid.size()]));
    }

    // 更新到货单表体扣税类别值
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        if (POBillType.Order.getCode().equals(itemVo.getCsourcetypecode())) {
          itemVo.setFtaxtypeflag(puOrderMap.get(itemVo.getCsourcebid()));
        }
        else if (SCBillType.Order.getCode().equals(itemVo.getCsourcetypecode())) {
          itemVo.setFtaxtypeflag(scOrderMap.get(itemVo.getCsourcebid()));
        }
      }
    }
  }

  /**
   * 取得到货单来源采购订单行，返回map. key=采购订单bid，value=订单行对应扣税类别的值
   * 
   * @param puOrderMap
   * @param puOrderBid
   * @return
   */
  private Map<String, Integer> getPuOrderMap(Set<String> puOrderBid) {
    if (puOrderBid.isEmpty()) {
      return null;
    }
    // IOrderPubQuery service =
    // NCLocator.getInstance().lookup(IOrderPubQuery.class);
    // OrderItemVO[] vos =
    // service.queryOrderItem(puOrderBid.toArray(new String[puOrderBid
    // .size()]));
    // 上面注释行，在大数据环境下会报内存溢出，改成用VOQuery查询，只查询需要的两个字段值
    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.FTAXTYPEFLAG
        });
    OrderItemVO[] vos =
        query.query(puOrderBid.toArray(new String[puOrderBid.size()]));

    Map<String, Integer> map = new HashMap<String, Integer>();
    for (OrderItemVO vo : vos) {
      map.put(vo.getPk_order_b(), vo.getFtaxtypeflag());
    }
    return map;
  }
}

/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 上午11:13:08
 */
package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对一组订单VO数组，按到货计划拆单后返回
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-26 上午11:13:08
 */
public class SplitOrderVOUtil {

  private static SplitOrderVOUtil instance = new SplitOrderVOUtil();

  private SplitOrderVOUtil() {
    // 私有
  }

  public static SplitOrderVOUtil getInstance() {
    return SplitOrderVOUtil.instance;
  }

  /**
   * 方法功能描述：对一组订单VO数组，按到货计划拆单后返回
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param rpVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-27 下午07:24:12
   */
  public OrderVO[] splitOrderVOByRPVOs(OrderVO[] orderVOs,
      OrderReceivePlanVO[] rpVOs) {
    if (ArrayUtils.isEmpty(orderVOs)) {
      return null;
    }

    if (ArrayUtils.isEmpty(rpVOs)) {
      return orderVOs;
    }

    MapList<String, OrderReceivePlanVO> mapList =
        new MapList<String, OrderReceivePlanVO>();
    Map<String, OrderReceivePlanVO> rpVOMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      mapList.put(rpVO.getPk_order_b(), rpVO);
      rpVOMap.put(rpVO.getPk_order_bb1(), rpVO);
    }

    Set<String> pkOrderBs = mapList.keySet();
    IKeyValue[] keyValues = BillHelper.declareArray(orderVOs);

    for (OrderVO orderVO : orderVOs) {
      OrderItemVO[] itemVOs = orderVO.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      Set<OrderItemVO> setItemVO = new HashSet<OrderItemVO>();
      Map<String, OrderItemVO> itemMap = new HashMap<String, OrderItemVO>();
      OrderItemVO[] afterItemVOs = null;
      for (OrderItemVO itemVO : itemVOs) {
        itemMap.put(itemVO.getPk_order_b(), itemVO);
        // 如果订单行不存在到货计划，直接放入Set中
        if (!pkOrderBs.contains(itemVO.getPk_order_b())) {
          setItemVO.add(itemVO);
          continue;
        }
        // 如果订单行存在到货计划，按到货计划分解订单行
        afterItemVOs = this.getBodyVOsByRPVOs(itemVO, mapList);
        for (int i = 0; i < afterItemVOs.length; ++i) {
          // 计算
          setItemVO.add(afterItemVOs[i]);
        }
      }
      // 重新设置表体VO
      orderVO.setBVO(setItemVO.toArray(new OrderItemVO[setItemVO.size()]));
      // 按到货计划拆行的并且数量不一致的时候才会联动计算
      this.calWhenNeed(orderVO, itemMap, keyValues, rpVOMap);
      this.setMny(orderVO, itemMap);

    }

    return orderVOs;
  }

  /**
   * 按到货计划拆行的并且数量不一致的时候才会联动计算
   * 
   * @param orderVO
   * @param itemMap
   * @param keyValues
   * @param rpVOMap
   */
  private void calWhenNeed(OrderVO orderVO, Map<String, OrderItemVO> itemMap,
      IKeyValue[] keyValues, Map<String, OrderReceivePlanVO> rpVOMap) {
    WeightVolumePieceCalc weightVolumePieceCal =
        new WeightVolumePieceCalc(keyValues);
    RelationCalculate calc = new RelationCalculate();
    List<OrderItemVO> needCalItem = new ArrayList<OrderItemVO>();
    OrderItemVO[] items = orderVO.getBVO();
    for (OrderItemVO item : orderVO.getBVO()) {
      OrderItemVO origItem = itemMap.get(item.getPk_order_b());
      if (origItem != null
          && MathTool.compareTo(origItem.getNnum(), item.getNnum()) == 0) {
        continue;
      }
      needCalItem.add(item);
    }
    if (needCalItem.size() > 0) {
      orderVO.setBVO(needCalItem.toArray(new OrderItemVO[needCalItem.size()]));
      calc.calculate(orderVO, OrderItemVO.NNUM, true, false);
      BillHelper<OrderVO> billhelper = new BillHelper<OrderVO>(orderVO);
      weightVolumePieceCal.calc(billhelper);
      // 下游补差
      this.setItemNum(orderVO, rpVOMap);
    }
    // 还原表体VO
    orderVO.setBVO(items);

  }

  /**
   * 方法功能描述：按到货计划信息填充订单行，有几行分解为几行
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @param mapList
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-27 下午07:24:58
   */
  private OrderItemVO[] getBodyVOsByRPVOs(OrderItemVO itemVO,
      MapList<String, OrderReceivePlanVO> mapList) {
    String pkOrderB = itemVO.getPk_order_b();
    List<OrderReceivePlanVO> list = mapList.get(pkOrderB);
    int len = list.size();

    // 不存在到货计划，直接返回表体VO
    if (0 == len) {
      return new OrderItemVO[] {
        itemVO
      };
    }

    OrderReceivePlanVO[] rpVOs =
        new ListToArrayTool<OrderReceivePlanVO>().convertToArray(list);
    OrderItemVO[] itemVOs = new OrderItemVO[len];
    for (int i = 0; i < len; ++i) {
      itemVOs[i] = (OrderItemVO) itemVO.clone();
      // 到货计划主键
      itemVOs[i].setPk_receiveplan(rpVOs[i].getPk_order_bb1());
      // 计划到货日期
      itemVOs[i].setDplanarrvdate(rpVOs[i].getDplanarrvdate());
      // 批次号
      itemVOs[i].setPk_batchcode(rpVOs[i].getPk_batchcode());
      itemVOs[i].setVbatchcode(rpVOs[i].getVbatchcode());
      // 库存组织
      itemVOs[i].setPk_arrvstoorg(rpVOs[i].getPk_arrvstoorg());
      itemVOs[i].setPk_arrvstoorg_v(rpVOs[i].getPk_arrvstoorg_v());
      // 仓库
      itemVOs[i].setPk_recvstordoc(rpVOs[i].getPk_recvstordoc());
      // 收货地址、地区、地点
      itemVOs[i].setPk_receiveaddress(rpVOs[i].getPk_receiveaddress());
      itemVOs[i].setCdevareaid(rpVOs[i].getCdevareaid());
      itemVOs[i].setCdevaddrid(rpVOs[i].getCdevaddrid());
      // 供应商发货地址、地区、地点
      itemVOs[i].setVvenddevaddr(rpVOs[i].getVvenddevaddr());
      itemVOs[i].setCvenddevareaid(rpVOs[i].getCvenddevareaid());
      itemVOs[i].setCvenddevaddrid(rpVOs[i].getCvenddevaddrid());
      // 备注
      itemVOs[i].setVbmemo(rpVOs[i].getVmemo());

      // 设置数量
      itemVOs[i].setNnum(rpVOs[i].getNnum());
      itemVOs[i].setNastnum(rpVOs[i].getNastnum());
      itemVOs[i].setNqtunitnum(rpVOs[i].getNqtunitnum());
      itemVOs[i].setNaccumarrvnum(rpVOs[i].getNaccumarrvnum());
      itemVOs[i].setNaccumstorenum(rpVOs[i].getNaccumstorenum());
      itemVOs[i].setNaccumdevnum(rpVOs[i].getNaccumdevnum());
      UFDouble ncanarrivenum =
          MathTool.sub(rpVOs[i].getNnum(), rpVOs[i].getNaccumarrvnum());
      if (MathTool.compareTo(itemVOs[i].getNcanarrivenum(), ncanarrivenum) > 0) {
        itemVOs[i].setNcanarrivenum(ncanarrivenum);
      }
      UFDouble ncaninnum =
          MathTool.sub(rpVOs[i].getNnum(), rpVOs[i].getNaccumstorenum());
      if (MathTool.compareTo(itemVOs[i].getNcaninnum(), ncaninnum) > 0) {
        itemVOs[i].setNcaninnum(ncaninnum);
      }
    }

    return itemVOs;
  }

  private void setItemNum(OrderVO orderVO,
      Map<String, OrderReceivePlanVO> rpVOMap) {
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (OrderItemVO itemVO : itemVOs) {
      String pk_receiveplan = itemVO.getPk_receiveplan();
      if (StringUtils.isBlank(pk_receiveplan)
          || !rpVOMap.containsKey(pk_receiveplan)) {
        continue;
      }

      OrderReceivePlanVO rpVO = rpVOMap.get(pk_receiveplan);
      if (null == rpVO) {
        continue;
      }
      UFDouble nnum = itemVO.getNnum();
      UFDouble nrpnum = rpVO.getNnum();
      if (MathTool.compareTo(nnum, nrpnum) == 0) {
        itemVO.setNastnum(rpVO.getNastnum());
        itemVO.setNqtunitnum(rpVO.getNqtunitnum());
      }
    }
  }

  private void setMny(OrderVO orderVO, Map<String, OrderItemVO> itemMap) {
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }
    String[] mnyAttrs =
        new String[] {
          OrderItemVO.NCALCOSTMNY, OrderItemVO.NCALTAXMNY,
          OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY,
          OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY, OrderItemVO.NMNY,
          OrderItemVO.NNOSUBTAX, OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY,
          OrderItemVO.NTAX, OrderItemVO.NTAXMNY
        };
    for (OrderItemVO itemVO : itemVOs) {
      String pk_order_b = itemVO.getPk_order_b();
      OrderItemVO old = itemMap.get(pk_order_b);
      if (null == old
          || MathTool.compareTo(itemVO.getNnum(), old.getNnum()) != 0) {
        continue;
      }
      for (String mnyAttr : mnyAttrs) {
        itemVO.setAttributeValue(mnyAttr, old.getAttributeValue(mnyAttr));
      }
    }
  }
}

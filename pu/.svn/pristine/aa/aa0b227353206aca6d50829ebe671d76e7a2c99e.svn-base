/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-17 上午09:59:37
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.ItemEmptyCheck;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.NumValueNoZeroCheckRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * @description
 *              采购订单检查非空项
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:23:24
 * @author luojw
 */
public class ItemEmptyRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    new ItemEmptyCheck().checkEmpty(vos);
    // 检查数值型字段不能为零或空的规则,非赠品检查
    List<OrderVO> list = this.filterNoZeroCheckVO(vos, UFBoolean.FALSE);
    if (null != list && list.size() > 0) {
      new NumValueNoZeroCheckRule<OrderVO>(this.getNumvalueCheckMap())
          .process(list.toArray(new OrderVO[list.size()]));
    }
    // 检查数值型字段不能为零或空的规则,赠品检查
    list = this.filterNoZeroCheckVO(vos, UFBoolean.TRUE);
    if (null != list && list.size() > 0) {
      new NumValueNoZeroCheckRule<OrderVO>(this.getBlagressNumvalueCheckMap())
          .process(list.toArray(new OrderVO[list.size()]));
    }
  }

  private List<OrderVO> filterNoZeroCheckVO(OrderVO[] vos, UFBoolean blagress) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (blagress.equals(null == itemVO.getBlargess() ? UFBoolean.FALSE
            : itemVO.getBlargess())) {
          itemList.add(itemVO);
        }
      }

      if (itemList.size() > 0) {
        OrderVO newVO = new OrderVO();
        newVO.setHVO(vo.getHVO());
        newVO.setBVO(itemList.toArray(new OrderItemVO[itemList.size()]));
        list.add(newVO);
      }
    }
    return list;
  }

  private MapList<String, String> getBlagressNumvalueCheckMap() {
    MapList<String, String> ml = new MapList<String, String>();
    ml.put(PUEntity.M21_B, OrderItemVO.NASTNUM);
    ml.put(PUEntity.M21_B, OrderItemVO.NNUM);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTUNITNUM);
    return ml;
  }

  private MapList<String, String> getNumvalueCheckMap() {
    MapList<String, String> ml = new MapList<String, String>();
    ml.put(PUEntity.M21_B, OrderItemVO.NASTNUM);
    ml.put(PUEntity.M21_B, OrderItemVO.NEXCHANGERATE);
    ml.put(PUEntity.M21_B, OrderItemVO.NMNY);
    ml.put(PUEntity.M21_B, OrderItemVO.NNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NNUM);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGMNY);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGTAXMNY);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGTAXNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NORIGTAXPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTORIGNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTORIGPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTORIGTAXNETPRC);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTORIGTAXPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTTAXNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTTAXPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NQTUNITNUM);
    ml.put(PUEntity.M21_B, OrderItemVO.NTAXMNY);
    ml.put(PUEntity.M21_B, OrderItemVO.NTAXNETPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NTAXPRICE);
    ml.put(PUEntity.M21_B, OrderItemVO.NCALCOSTMNY);

    return ml;
  }

}

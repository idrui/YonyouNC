package nc.bs.pu.m23.maintain.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * �������桢�޸ı��涼���õ��˹��򣬱�����Ҫ������¹��ܣ�
 * ����˴ε������������۾��붩��ƥ�䣬����ö������ҽ��
 * @scene
 * ��������������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-14 ����03:42:46
 * @author hanbin
 */


public class DealLocalMnyRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    if (voArray == null) {
      return;
    }
    ArriveItemVO[] items = AggVOUtil.getCombinItemVOs(voArray);
    // ��������еı��ҽ��
    this.dealLocalMny(items);
  }

  /**
   * ������������������˴ε������������۾��붩��ƥ�䣬����ö������ҽ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVOArray
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-18 ����08:22:10
   */
  private void dealLocalMny(ArriveItemVO[] itemVOArray) {
    if (itemVOArray == null || itemVOArray.length == 0) {
      return;
    }
    // ��ѯ�ɹ�������VO
    String[] orderBidArray = ArrivePublicUtil.getOrderBidArray(itemVOArray);
    if (orderBidArray == null || orderBidArray.length == 0) {
      return;
    }
    OrderItemVO[] orderItems = null;
    try {
      IOrderPubQuery sv = NCLocator.getInstance().lookup(IOrderPubQuery.class);
      orderItems = sv.queryOrderItem(orderBidArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (orderItems == null) {
      return;
    }
    // <������ID,������VO>
    Map<String, OrderItemVO> bidToItem = CirVOUtil.createKeyVOMap(orderItems);
    // ������VO
    OrderItemVO orderItem;
    for (ArriveItemVO item : itemVOArray) {
      if (item.getStatus() == VOStatus.DELETED) {
        continue;
      }
      orderItem = bidToItem.get(item.getPk_order_b());
      if (orderItem.getNmny() == null || orderItem.getNnum() == null) {
        continue;
      }
      // �������ҵ��� = �������ҽ�� / ��������
      UFDouble orderPrice = orderItem.getNmny().div(orderItem.getNnum());
      UFDouble arrivePrice = MathTool.nvl(item.getNprice());
      orderPrice =
          orderPrice.setScale(arrivePrice.getPower(), UFDouble.ROUND_HALF_UP);
      // ����˴ε������������۾��붩��ƥ�䣬����ö������ҽ��(������˰,ԭ�ҽ��)
      if (item.getNnum().equals(orderItem.getNnum())
          && arrivePrice.equals(orderPrice)) {
        item.setNmny(orderItem.getNmny());
        item.setNtaxmny(orderItem.getNtaxmny());
        item.setNorigmny(orderItem.getNorigmny());
        item.setNorigtaxmny(orderItem.getNorigtaxmny());
        if (item.getStatus() == VOStatus.UNCHANGED) {
          item.setStatus(VOStatus.UPDATED);
        }

      }
    }
  }
}

/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午07:05:11
 */
package nc.pubimpl.pu.m21.arap.mf1.handler;

import java.util.HashSet;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.verify.AggverifyVO;
import nc.vo.arap.verify.VerifyDetailVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>核销回写采购订单工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午07:05:11
 */
public class OrderWriteBackForF1Util {

  private static OrderWriteBackForF1Util instance =
      new OrderWriteBackForF1Util();

  public static OrderWriteBackForF1Util getInstance() {
    return OrderWriteBackForF1Util.instance;
  }

  /**
   * 方法功能描述：根据核销VO得到核销明细Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param verifyVOs
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午07:07:50
   */
  public MapList<String, VerifyDetailVO> getDetailVOMap(AggverifyVO[] verifyVOs) {
    MapList<String, VerifyDetailVO> mapList =
        new MapList<String, VerifyDetailVO>();
    if (ArrayUtils.isEmpty(verifyVOs)) {
      return mapList;
    }
    for (AggverifyVO payBillVO : verifyVOs) {
      if (null == payBillVO) {
        continue;
      }

      VerifyDetailVO[] details = (VerifyDetailVO[]) payBillVO.getChildrenVO();

      if (ArrayUtils.isEmpty(details)) {
        continue;
      }

      for (VerifyDetailVO detail : details) {
        String pk_item = detail.getPk_item();
        String pk_billtype = detail.getPk_billtype();
        if (!StringUtil.isEmptyWithTrim(pk_item)
            && IArapBillTypeCons.F1.equals(pk_billtype)) {
          mapList.put(pk_item, detail);
        }
      }
    }
    return mapList;
  }

  /**
   * 方法功能描述：根据应付单得到对应的采购订单表体付款关闭标志
   * <p>
   * <b>参数说明</b>
   * 
   * @param payablebillitemVOs
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午07:12:24
   */
  public OrderItemVO[] getOrderItemVOs(PayableBillItemVO[] payablebillitemVOs) {
    if (ArrayUtils.isEmpty(payablebillitemVOs)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (PayableBillItemVO payableBillItemVO : payablebillitemVOs) {
      String srcbilltype = payableBillItemVO.getSrc_billtype();
      if (StringUtil.isEmptyWithTrim(srcbilltype)) {
        continue;
      }

      if (POBillType.Order.getCode().equals(
          payableBillItemVO.getSrc_billtype().trim())
          && payableBillItemVO.getSrc_itemid() != null) {
        set.add(payableBillItemVO.getSrc_itemid());
      }
    }

    if (set.size() > 0) {
      VOQuery<OrderItemVO> query =
          new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
            OrderItemVO.PK_ORDER_B, OrderItemVO.BPAYCLOSE
          });
      return query.query(set.toArray(new String[set.size()]));
    }

    return null;

  }

  /**
   * 方法功能描述：根据核销VO得到应付单表体
   * <p>
   * <b>参数说明</b>
   * 
   * @param verifyVOs
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午07:07:07
   */
  public PayableBillItemVO[] getPayableBillItems(AggverifyVO[] verifyVOs) {
    if (ArrayUtils.isEmpty(verifyVOs)) {
      return null;
    }
    Set<String> payableidSet = new HashSet<String>();
    for (AggverifyVO payBillVO : verifyVOs) {
      if (null == payBillVO) {
        continue;
      }

      VerifyDetailVO[] details = (VerifyDetailVO[]) payBillVO.getChildrenVO();

      if (ArrayUtils.isEmpty(details)) {
        continue;
      }

      for (VerifyDetailVO detail : details) {
        String pk_item = detail.getPk_item();
        String pk_billtype = detail.getPk_billtype();
        if (!StringUtil.isEmptyWithTrim(pk_item)
            && IArapBillTypeCons.F1.equals(pk_billtype)) {
          payableidSet.add(pk_item);
        }
      }
    }

    if (payableidSet.isEmpty()) {
      return null;
    }

    VOQuery<PayableBillItemVO> query =
        new VOQuery<PayableBillItemVO>(PayableBillItemVO.class);
    PayableBillItemVO[] payableBillItems =
        query.query(payableidSet.toArray(new String[0]));

    if (ArrayUtils.isEmpty(payableBillItems)) {
      return null;
    }

    Set<PayableBillItemVO> payableBillItemset =
        new HashSet<PayableBillItemVO>();
    for (PayableBillItemVO payableBillItem : payableBillItems) {
      String srcbilltype = payableBillItem.getSrc_billtype();
      if (StringUtil.isEmptyWithTrim(srcbilltype)) {
        continue;
      }
      if (POBillType.Order.getCode().equals(srcbilltype.trim())
          && payableBillItem.getSrc_itemid() != null
          && !TOBillType.SettleListIn.getCode().equals(
              payableBillItem.getTop_billtype().trim())) {
        payableBillItemset.add(payableBillItem);
      }

    }

    if (payableBillItemset.size() > 0) {
      return payableBillItemset
          .toArray(new PayableBillItemVO[payableBillItemset.size()]);
    }

    return null;
  }
}

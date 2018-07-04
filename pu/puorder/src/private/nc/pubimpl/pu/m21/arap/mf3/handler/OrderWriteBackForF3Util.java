/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午06:20:05
 */
package nc.pubimpl.pu.m21.arap.mf3.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.ps.PsServicesUtil;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.verify.AggverifyVO;
import nc.vo.arap.verify.VerifyDetailVO;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.pub.IApplyConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>付款单修改、核销时回写采购订单工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午06:20:05
 */
public class OrderWriteBackForF3Util {

  private static OrderWriteBackForF3Util instance =
      new OrderWriteBackForF3Util();

  public static OrderWriteBackForF3Util getInstance() {
    return OrderWriteBackForF3Util.instance;
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
   * @time 2010-7-16 下午06:59:37
   */
  public Map<String, VerifyDetailVO> getDetailVOMap(AggverifyVO[] verifyVOs) {
    Map<String, VerifyDetailVO> map = new HashMap<String, VerifyDetailVO>();
    if (ArrayUtils.isEmpty(verifyVOs)) {
      return map;
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
            && IArapBillTypeCons.F3.equals(pk_billtype)) {
          map.put(pk_item, detail);
        }
      }
    }

    return map;
  }

  /**
   * 方法功能描述：得到付款单对应的采购订单表体付款关闭属性
   * <p>
   * <b>参数说明</b>
   * 
   * @param paybillItemVOs
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午06:34:29
   */
  public OrderItemVO[] getOrderItemVOs(PayBillItemVO[] paybillItemVOs) {
    if (ArrayUtils.isEmpty(paybillItemVOs)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (PayBillItemVO payBillItemVO : paybillItemVOs) {
      String srcbilltype = payBillItemVO.getSrc_billtype();
      if (StringUtil.isEmptyWithTrim(srcbilltype)) {
        continue;
      }

      if (POBillType.Order.getCode().equals(
          payBillItemVO.getSrc_billtype().trim())
          && payBillItemVO.getSrc_itemid() != null) {
        set.add(payBillItemVO.getSrc_itemid());
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
   * 方法功能描述：获得来源于订单的付款单表体数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param payBillVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午06:22:13
   */
  public PayBillItemVO[] getPayBillItems(AggPayBillVO[] payBillVOs) {
    if (ArrayUtils.isEmpty(payBillVOs)) {
      return null;
    }
    Set<PayBillItemVO> set = new HashSet<PayBillItemVO>();
    for (AggPayBillVO payBillVO : payBillVOs) {
      if (null == payBillVO) {
        continue;
      }
      PayBillItemVO[] payBillItemVOs =
          (PayBillItemVO[]) payBillVO.getChildrenVO();
      if (ArrayUtils.isEmpty(payBillItemVOs)) {
        continue;
      }
      for (PayBillItemVO payBillItemVO : payBillItemVOs) {
        if (POBillType.Order.getCode().equals(payBillItemVO.getSrc_billtype())) {
          set.add(payBillItemVO);
        }
      }
    }

    if (!set.isEmpty()) {
      return set.toArray(new PayBillItemVO[set.size()]);
    }

    return null;
  }

  public PayBillItemVO[] getPayBillItems(Object value) {
    PayBillItemVO[] payBillItems = null;
    if (value.getClass().isArray()) {
      Object[] obj = (Object[]) value;
      AggPayBillVO[] aggVOs = new AggPayBillVO[obj.length];
      System.arraycopy(obj, 0, aggVOs, 0, obj.length);
      payBillItems = this.getPayBillItems(aggVOs);
    }
    else {
      AggPayBillVO aggVO = (AggPayBillVO) value;
      payBillItems = this.getPayBillItems(new AggPayBillVO[] {
        aggVO
      });
    }

    return payBillItems;
  }

  public Map<String, PayBillItemVO> getPayBillItemsFrom36D1(Object value) {
    AggPayBillVO[] aggVOs = (AggPayBillVO[]) value;
    Map<String, String> map = this.getAggApplyVO(aggVOs);
    if (null == map || 0 == map.size()) {
      return null;
    }

    Map<String, PayBillItemVO> orderPayBillMap =
        new HashMap<String, PayBillItemVO>();
    for (AggPayBillVO aggVO : aggVOs) {
      PayBillItemVO[] payBillItemVOs = (PayBillItemVO[]) aggVO.getChildrenVO();
      for (PayBillItemVO payBillItemVO : payBillItemVOs) {
        if (IApplyConst.CMP_36D1.equals(payBillItemVO.getSrc_billtype())) {
          String src_billid = payBillItemVO.getSrc_billid();
          String pk_order_payplan = map.get(src_billid);
          orderPayBillMap.put(pk_order_payplan, payBillItemVO);
        }
      }
    }

    return orderPayBillMap;
  }

  private Map<String, String> getAggApplyVO(AggPayBillVO[] aggVOs) {
    String[] payAppIds = this.getPayAppIds(aggVOs);
    if (ArrayUtils.isEmpty(payAppIds)) {
      return null;
    }
    AggApplyVO[] appVOs = PsServicesUtil.getAggApplyVOByPKs(payAppIds);
    if (ArrayUtils.isEmpty(appVOs)) {
      return null;
    }

    Map<String, String> map = new HashMap<String, String>();
    for (AggApplyVO appVO : appVOs) {
      ApplyBVO[] bvos = (ApplyBVO[]) appVO.getChildren(ApplyBVO.class);
      for (ApplyBVO vo : bvos) {
        if (POBillType.Order.getCode().equals(vo.getSrcbilltypecode())) {
          map.put(vo.getPk_apply(), vo.getPk_srcbill());
        }
      }
    }

    return map;
  }

  private String[] getPayAppIds(AggPayBillVO[] payBillVOs) {
    Set<String> set = new HashSet<String>();
    for (AggPayBillVO payBillVO : payBillVOs) {
      PayBillItemVO[] payBillItemVOs =
          (PayBillItemVO[]) payBillVO.getChildrenVO();
      for (PayBillItemVO payBillItemVO : payBillItemVOs) {
        if (IApplyConst.CMP_36D1.equals(payBillItemVO.getSrc_billtype())) {
          set.add(payBillItemVO.getSrc_billid());
        }
      }
    }
    if (set.size() > 0) {
      return set.toArray(new String[set.size()]);
    }
    return null;
  }

}

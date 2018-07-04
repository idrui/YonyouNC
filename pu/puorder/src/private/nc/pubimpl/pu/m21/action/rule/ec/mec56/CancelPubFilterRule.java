/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 下午02:42:30
 */
package nc.pubimpl.pu.m21.action.rule.ec.mec56;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>过滤掉不能取消发布的订单
 * <li>如果来源于EC采购订单为自由态，则在NC中已审核则不允许取消发布；
 * <li>如果来源于EC采购订单为审核态，则在NC中已修订或已有下游单据，则不允许取消发布。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-8 下午02:42:30
 */
public class CancelPubFilterRule implements IFilterRule<OrderVO> {

  private Map<String, Integer> paraMap;

  public CancelPubFilterRule(Map<String, Integer> paraMap) {
    this.paraMap = paraMap;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IFilterRule#process(E[])
   */
  @Override
  public OrderVO[] process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Set<OrderVO> set = new HashSet<OrderVO>();
    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }
      OrderItemVO[] itemVOs = vo.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      String csourceid = itemVOs[0].getCsourceid();
      Integer para = this.paraMap.get(csourceid);
      if (null == para) {
        continue;
      }
      else if (0 == para.intValue() && this.isFree(vo)) {
        // 自由
        set.add(vo);
      }
      else if (1 == para.intValue() && !this.haveFollowupBill(vo)) {
        // 审批，没有手续单据
        set.add(vo);
      }
    }

    if (!set.isEmpty()) {
      return set.toArray(new OrderVO[0]);
    }

    return null;
  }

  /**
   * 方法功能描述：判断订单是否修订或者有后续单据，如果修订或者有后续单据返回true，否则返回false
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-8 下午03:14:31
   */
  private boolean haveFollowupBill(OrderVO orderVO) {

    // 订单在途状态
    if (this.haveOrderBB(orderVO.getPrimaryKey())) {
      return true;
    }

    // 销售订单
    if (orderVO.getHVO().getBcooptoso() != null
        && orderVO.getHVO().getBcooptoso().equals(UFBoolean.TRUE)) {
      return true;
    }

    boolean haveFollowupBill = false;
    OrderItemVO[] items = orderVO.getBVO();
    for (OrderItemVO item : items) {
      // 修订
      if (((Integer) EnumActive.REVISEHISTORY.value()).equals(item
          .getFisactive())) {
        haveFollowupBill = true;
        break;
      }

      // 到货
      if (MathTool.compareTo(item.getNaccumarrvnum(), UFDouble.ZERO_DBL) > 0
          || MathTool.compareTo(item.getNbackarrvnum(), UFDouble.ZERO_DBL) > 0) {
        haveFollowupBill = true;
        break;
      }

      // 入库
      if (MathTool.compareTo(item.getNaccumstorenum(), UFDouble.ZERO_DBL) > 0
          || MathTool.compareTo(item.getNbackstorenum(), UFDouble.ZERO_DBL) > 0) {
        haveFollowupBill = true;
        break;
      }

      // 发票
      if (MathTool.compareTo(item.getNaccuminvoicemny(), UFDouble.ZERO_DBL) > 0
          || MathTool.compareTo(item.getNfeemny(), UFDouble.ZERO_DBL) > 0) {
        haveFollowupBill = true;
        break;
      }

      // 运输
      if (MathTool.compareTo(item.getNaccumdevnum(), UFDouble.ZERO_DBL) > 0) {
        haveFollowupBill = true;
        break;
      }
    }

    return haveFollowupBill;
  }

  /**
   * 方法功能描述：查询在途状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_order
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-8 下午03:12:42
   */
  private boolean haveOrderBB(String pk_order) {
    boolean haveOrderBB = false;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset =
        utils.query("select pk_order from po_order_bb where pk_order = '"
            + pk_order + "' and dr = 0");
    if (rowset.size() > 0) {
      haveOrderBB = true;
    }
    return haveOrderBB;
  }

  /**
   * 方法功能描述：判断单据是否是自由态
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-8 下午05:05:50
   */
  private boolean isFree(OrderVO orderVO) {
    return ((Integer) POEnumBillStatus.FREE.value()).equals(orderVO.getHVO()
        .getForderstatus());
  }

}

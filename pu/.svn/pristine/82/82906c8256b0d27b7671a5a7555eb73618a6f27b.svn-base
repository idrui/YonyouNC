/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午04:23:56
 */
package nc.impl.pu.m21.action.rule.approve;

import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单弃审时检查是否已经生成过下游
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:04:31
 * @author luojw
 */
public class FollowupBillChkRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    boolean haveFollowupBill = false;
    for (OrderVO vo : vos) {

      // 销售订单
      if (vo.getHVO().getBcooptoso() != null
          && vo.getHVO().getBcooptoso().equals(UFBoolean.TRUE)) {
        haveFollowupBill = true;
        break;
      }
      OrderItemVO[] items = vo.getBVO();
      for (OrderItemVO item : items) {
        // 到货
        if (item.getNaccumarrvnum() != null
            && item.getNaccumarrvnum().compareTo(UFDouble.ZERO_DBL) > 0
            || item.getNbackarrvnum() != null
            && item.getNbackarrvnum().compareTo(UFDouble.ZERO_DBL) > 0) {
          haveFollowupBill = true;
          break;
        }

        // 入库
        if (item.getNaccumstorenum() != null
            && item.getNaccumstorenum().compareTo(UFDouble.ZERO_DBL) > 0
            || item.getNbackstorenum() != null
            && item.getNbackstorenum().compareTo(UFDouble.ZERO_DBL) > 0) {
          haveFollowupBill = true;
          break;
        }

        // 发票
        if (item.getNaccuminvoicemny() != null
            && item.getNaccuminvoicemny().compareTo(UFDouble.ZERO_DBL) > 0
            || item.getNfeemny() != null
            && item.getNfeemny().compareTo(UFDouble.ZERO_DBL) > 0) {
          haveFollowupBill = true;
          break;
        }

        // 运输
        if (item.getNaccumdevnum() != null
            && item.getNaccumdevnum().compareTo(UFDouble.ZERO_DBL) > 0) {
          haveFollowupBill = true;
          break;
        }

        // 拣货
        if (item.getNaccumpickupnum() != null
            && item.getNaccumpickupnum().compareTo(UFDouble.ZERO_DBL) > 0) {
          haveFollowupBill = true;
          break;
        }
      }

      if (!haveFollowupBill) {
        String hid = vo.getPrimaryKey();
        // 订单在途状态
        if (this.haveOrderBB(hid)) {
          haveFollowupBill = true;
          break;
        }

        if (this.havePurchaseIn(hid)) {
          haveFollowupBill = true;
          break;
        }

        // 付款计划在删除规则中检查
      }
    }

    if (haveFollowupBill) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0156")/*
                                                                   * @res
                                                                   * "存在有后续单据的订单，请检查"
                                                                   */);
    }

  }

  private boolean haveOrderBB(String pk_order) {
    boolean haveOrderBB = false;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset =
        utils.query("select pk_order from po_order_bb where pk_order = '"
            + pk_order + "' and isoperated = 'Y' and dr = 0 ");
    if (rowset.size() > 0) {
      haveOrderBB = true;
    }
    return haveOrderBB;
  }

  private boolean havePurchaseIn(String hid) {
    Map<String, UFBoolean> purchaseInMap =
        M45PUServices.getMapBysrcHid(new String[] {
          hid
        });
    return purchaseInMap.get(hid).booleanValue();
  }

}

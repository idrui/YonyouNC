/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 上午09:01:04
 */
package nc.ui.pu.m21.editor.card.afteredit.header;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFConfig;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单交易类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 上午09:01:04
 */
public class Vtrantypecode implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    String vtrantypecode =
        (String) panel.getHeadItem(OrderHeaderVO.VTRANTYPECODE)
            .getValueObject();

    if (StringUtil.isEmptyWithTrim(vtrantypecode)) {
      return;
    }
    this.setBusiType(panel, vtrantypecode);
    // 设置直运属性，走缓存
    this.setDirect(panel, vtrantypecode);
    boolean isReceivePlan = this.isReceivePlan(vtrantypecode);
    panel.getBodyItem("material", OrderItemVO.PK_ARRVSTOORG_V).setNull(
        !isReceivePlan);
    panel.getBodyItem("material", OrderItemVO.PK_ARRVSTOORG).setNull(
        !isReceivePlan);
  }

  /**
   * 方法功能描述：交易类型是否有到货计划
   * <p>
   * <b>参数说明</b>
   * 
   * @param vtrantypecode
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 上午10:34:04
   */
  private boolean isReceivePlan(String vtrantypecode) {
    boolean breceiveplan = false;
    try {
      IPoTransTypeQuery query =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> transtypeMap =
          query.queryAttrByTypes(new String[] {
            vtrantypecode
          }, new String[] {
            PoTransTypeVO.BRECEIVEPLAN
          });
      PoTransTypeVO vo = transtypeMap.get(vtrantypecode);

      if (vo != null && vo.getBreceiveplan().booleanValue()) {
        breceiveplan = true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return breceiveplan;
  }

  private void setBusiType(BillCardPanel panel, String vtrantypecode) {
    // if (panel.getHeadItem(OrderHeaderVO.PK_BUSITYPE).getValueObject() !=
    // null) {
    // return;
    // }
    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    String userid = ClientContext.getInstance().getLoginUserId();
    try {
      String busitype =
          NCLocator
              .getInstance()
              .lookup(IPFConfig.class)
              .retBusitypeCanStart(POBillType.Order.getCode(), vtrantypecode,
                  pk_org, userid);
      panel.setHeadItem(OrderHeaderVO.PK_BUSITYPE, busitype);
    }
    catch (BusinessException e) {
      Log.info(e);
    }
  }

  /**
   * 设置直运属性，走缓存
   * 
   * @param panel
   */
  private void setDirect(BillCardPanel panel, String vtrantypecode) {
    UFBoolean dirct =
        TrantypeUtil.getInstance().getDirectPurchaseAttr(vtrantypecode);
    panel.setHeadItem(OrderHeaderVO.BDIRECT, dirct);
  }

}

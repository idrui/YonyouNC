package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>收货仓库编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑前：
 * <li>根据表头业务类型，设置“仓库”参照的是否直运仓
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class ReceiveStore implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();

    // 库存组织
    String stockOrg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_ORG);

    // 收货仓库(按库存组织过滤)
    UIRefPane receiveStoreRef =
        (UIRefPane) card.getBodyItem(ArriveItemVO.PK_RECEIVESTORE)
            .getComponent();
    // 获得收货利润中心
    Object profitcentre =
        card.getBodyValueAt(e.getRow(), ArriveItemVO.PK_ARRLIABCENTER);
    FilterWareHouseRefUtils util = new FilterWareHouseRefUtils(receiveStoreRef);
    // 根据收货利润中心过滤
    if(profitcentre != null){
      util.filterByLiabcenter(profitcentre);
    }
    util.filterItemRefByOrg(stockOrg);

    // 过滤掉直运仓
    String sourceBilltype =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.CSOURCETYPECODE);
    String sourceTranstype =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.VSOURCETRANTYPE);
    boolean isDirect = false;
    if (POBillType.Order.getCode().equals(sourceBilltype)) {
      isDirect = ArriveClientUtil.isPODirectPurchase(sourceTranstype);
    }
    else {
      // TODO hanbin 委外情况
    }

    if (!isDirect) {
      util.filterDirectStorc(); // 如果不是直运类业务，过滤掉直运仓
    }
    e.setReturnValue(Boolean.TRUE);
  }
}

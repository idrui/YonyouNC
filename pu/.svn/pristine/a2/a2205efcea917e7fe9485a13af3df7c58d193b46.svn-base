package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.refmodel.MaterialRefModel;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>设置物料的参照范围，本类主要完成以下功能：</b>
 * <ul>
 * <li>设置规则：
 * <li>如果（来源订单行不是赠品 && 到货单行是赠品），物料参照 = 所有物料（含订单物料＋替换件）
 * <li>否则，物料参照 = 订单物料＋替换件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 对应采购订单表体行主键、物料主键
    String pk_order_b =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_ORDER_B);
    if (StringUtils.isEmpty(pk_order_b)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane materialRefPane =
        (UIRefPane) card.getBodyItem(ArriveItemVO.PK_MATERIAL).getComponent();
    MaterialRefModel materialRefModel =
        (MaterialRefModel) materialRefPane.getRefModel();
    materialRefModel.initialize(card, e.getRow());

    e.setReturnValue(Boolean.TRUE);
  }
}

/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 上午11:37:27
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体仓库编辑前处理，从采购入库单生成的发票仓库不能更改
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 上午11:37:27
 */
public class StoreHouse implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 原56从采购入库单生成的发票仓库不能更改，按tangjf的意见能编辑库存组织就可以编辑仓库

    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    // 库存组织
    Object stockOrg = helper.getHeadValue(InvoiceHeaderVO.PK_STOCKORG);
    // 物料
    String pk_material =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.PK_MATERIAL);

    // 没有库存组织或物料，仓库不可编辑
    if (ObjectUtil.isEmptyWithTrim(stockOrg)
        || StringUtil.isEmptyWithTrim(pk_material)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 仓库
    UIRefPane storeHousePanel =
        (UIRefPane) event.getBillCardPanel()
            .getBodyItem(InvoiceItemVO.PK_STORDOC).getComponent();
    // 根据库存组织过滤仓库
    new FilterWareHouseRefUtils(storeHousePanel).filterItemRefByOrg(stockOrg
        .toString());
    event.setReturnValue(Boolean.TRUE);

  }

}

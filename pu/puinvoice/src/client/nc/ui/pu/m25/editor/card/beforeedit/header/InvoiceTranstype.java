/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午02:47:46
 */
package nc.ui.pu.m25.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>交易类型过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-11 下午02:47:46
 */
public class InvoiceTranstype implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    IKeyValue billhelper = new CardEditorHelper(e.getBillCardPanel());
    boolean isself = AggVOUtil.isSelfMade(billhelper, InvoiceItemVO.CSOURCEID);
    BillItem item = e.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_ORG);
    String pk_org = (String) item.getValueObject();
    item = e.getBillCardPanel().getHeadItem(e.getKey());
    FilterTransTypeRefUtils filter = new FilterTransTypeRefUtils(item, pk_org);
    // 自制时不按流程找交易类型
    // 因为自制时交易类型编辑后，前台会先确定一个流程，再编辑时不能只参照此流程中的交易类型
    // 而有来源于时流程不能变，必须只找流程中的交易类型
    if (isself) {
      filter.filterTranType(new String[] {
        POBillType.Invoice.getCode(),
      }, null);
    }
    else {
      String pk_busitype =
          (String) e.getBillCardPanel()
              .getHeadItem(InvoiceHeaderVO.PK_BUSITYPE).getValueObject();
      filter.filterTranType(new String[] {
        POBillType.Invoice.getCode()
      }, pk_busitype);
    }
  }
}

/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-17 ����09:40:30
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ϱ༭ǰ�¼�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-17 ����09:40:30
 */
public class Material implements ICardBodyBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    CardPanelValueUtils cardUtil =
        new CardPanelValueUtils(e.getBillCardPanel());
    String sourceBillType =
        cardUtil.getBodyStringValue(e.getRow(), InvoiceItemVO.CSOURCETYPECODE);
    // ��Դ��ⵥʱ�����ϲ�����༭����V5�߼�һ�£�
    if (ICBillType.PurchaseIn.getCode().equals(sourceBillType)
        || ICBillType.SubContinIn.getCode().equals(sourceBillType)
        || ICBillType.VmiSum.getCode().equals(sourceBillType)
        || POBillType.InitEstimate.getCode().equals(sourceBillType)
        || MMBillType.PscSettle.getCode().equals(sourceBillType)) {
      e.setReturnValue(Boolean.FALSE);
      return;

    }
    // ����������ع���
    this.filterByFeeMaterial(e);

  }

  /**
   * @param e
   */
  private void filterByFeeMaterial(CardBodyBeforeEditEvent e) {
    BillItem feeItem = e.getBillCardPanel().getHeadItem(InvoiceHeaderVO.BFEE);
    Object pk_org = e.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_ORG).getValueObject();
    if (feeItem == null || feeItem.getValueObject() == null) {
      return;
    }
    Boolean feeValue = (Boolean) feeItem.getValueObject();
    // ���ݡ��Ƿ���á���������
    BillItem materialItem =
        e.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_MATERIAL);
    FilterMaterialRefUtils filterMaterialRefUtils =
        new FilterMaterialRefUtils((UIRefPane) materialItem.getComponent());
    // ���÷�Ʊ���ˣ����򲻹���
    if (feeValue.booleanValue()) {
      filterMaterialRefUtils.filterRefByFeeOrDiscount(UFBoolean.TRUE,
          UFBoolean.TRUE);
    }
    else {
      filterMaterialRefUtils.filterRefByFeeOrDiscount(null, null);
    }
    filterMaterialRefUtils.filterItemRefByOrg(pk_org.toString());
  }

}

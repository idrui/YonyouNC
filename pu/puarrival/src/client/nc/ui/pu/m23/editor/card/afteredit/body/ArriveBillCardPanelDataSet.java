package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������ڽ��й�ϵ����Ķ�������Դ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-29 ����11:09:13
 */
public class ArriveBillCardPanelDataSet extends BillCardPanelDataSet {

  public ArriveBillCardPanelDataSet(BillCardPanel cardPanel, int row,
      IRelationForItems item) {
    super(cardPanel, row, item);
  }

  @Override
  public UFDate getBillDate() {
    // ����������
    BillItem dateItem =
        this.getBillCardPanel().getHeadItem(ArriveHeaderVO.DBILLDATE);
    Object obj = dateItem.getValueObject();
    return ValueUtils.getUFDate(obj);
  }

  @Override
  public boolean hasItemKey(String key) {
    if ("dbilldate".equalsIgnoreCase(key)) {
      return true;
    }
    else {
      return super.hasItemKey(key);
    }
  }
}

package nc.ui.pu.pub.parapanel;

import java.util.LinkedList;
import java.util.List;

import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.para.SysInitVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѡֵΪ����Ӧ�̼�Ŀ���ο��ɱ����ƻ��ۡ��������¼ۡ�������ͼۡ��빺�����ۡ���Ĭ��
 * <li>Ĭ��ֵΪ����Ӧ�̼�Ŀ��->�������¼�->�빺������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-10 ����05:56:36
 */
public class PO06ListToListPanel extends AbstractParaListToListPanel {
  private static final long serialVersionUID = 859317663154317848L;

  private ParaListItemInfo[] leftItems;

  private ParaListItemInfo[] rightItems;

  public PO06ListToListPanel(String pk_org) {
    // super(pk_org);
  }

  @Override
  public String getParamValueCode() {
    // ԭ����PO06_v �ָ���ΪPO06����Ӧuap�µı仯
    return POParas.PO06.toString();
  }

  public List<String> getPO06PriceList() {
    // ��ѡֵΪ����Ӧ�̼�Ŀ���ο��ɱ����ƻ��ۡ��������¼ۡ�������ͼۡ��빺�����ۡ���Ĭ�ϡ�
    List<String> PO06PriceList = new LinkedList<String>();

    PO06PriceList.add(PriceSource.SupplierPrice.name());
    PO06PriceList.add(PriceSource.RefCostPrice.name());
    PO06PriceList.add(PriceSource.PlanPrice.name());
    PO06PriceList.add(PriceSource.OrderNewestPrice.name());
    PO06PriceList.add(PriceSource.OrderMinPrice.name());
    PO06PriceList.add(PriceSource.PrayPrice.name());

    return PO06PriceList;
  }

  @Override
  public ParaListItemInfo[] initLeftItems(SysInitVO initVO) {
    if (this.leftItems == null) {
      this.buildInitData(initVO);
    }

    return this.leftItems;
  }

  @Override
  public ParaListItemInfo[] initRightItems(SysInitVO initVO) {
    if (this.rightItems == null) {
      this.buildInitData(initVO);
    }

    return this.rightItems;
  }

  private void buildInitData(SysInitVO initVO) {

    String[] initValues = initVO.getValue().split(",");

    this.rightItems = new ParaListItemInfo[initValues.length];
    List<String> list = this.getPO06PriceList();
    for (int i = 0, len = initValues.length; i < len; i++) {

      this.rightItems[i] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(initValues[i]);
      this.rightItems[i].setText(priceEnum.code());
      this.rightItems[i].setValue(priceEnum.name());

      // ɾ���ѱ�ѡ�е�ֵ
      list.remove(priceEnum.name());
    }

    this.leftItems = new ParaListItemInfo[list.size()];
    for (int j = 0, len = this.leftItems.length; j < len; j++) {

      this.leftItems[j] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(list.get(j));
      this.leftItems[j].setText(priceEnum.code());
      this.leftItems[j].setValue(priceEnum.name());
    }
  }
}

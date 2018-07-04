package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽���ϵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-2 ����06:39:15
 */
public class RelationCalculate extends AbstractRelationCalculateListener {

  private Condition cond;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    CanArriveNumCalc canArriveCalc = new CanArriveNumCalc(e);
    super.afterEdit(e);
    // ��������������������仯����Ҫ����ɵ���������
    canArriveCalc.calc();
  }

  @Override
  public BillCardPanelDataSet getBillCardPanelDataSet(BillCardPanel panel,
      int rowNo, IRelationForItems item) {

    return new ArriveBillCardPanelDataSet(panel, rowNo, item);
  }

  @Override
  public Condition getCalculatorCondition(BillCardPanel panel, int rowno) {
    String pk_purchaseorg =
        (String) panel.getHeadTailItem(ArriveHeaderVO.PK_PURCHASEORG)
            .getValueObject();
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    this.cond = new Condition();
    // �����Ƿ���б��һ���
    this.cond.setIsCalLocalCurr(true);
    // ���õ����۷�ʽ���ۿ�
    boolean isChangePrice = this.isChangePrice(pk_purchaseorg);
    this.cond.setIsChgPriceOrDiscount(isChangePrice);
    // �����Ƿ�̶���λ������
    String material =
        (String) panel.getBodyValueAt(rowno, ArriveItemVO.PK_MATERIAL);
    String cunitid = (String) panel.getBodyValueAt(rowno, ArriveItemVO.CUNITID);
    String castunitid =
        (String) panel.getBodyValueAt(rowno, ArriveItemVO.CASTUNITID);
    this.cond.setIsFixNchangerate(this.isFixUnitRate(material, cunitid,
        castunitid));
    // �Ƿ�̶����۵�λ������
    this.cond.setIsFixNqtunitrate(false);

    // ���ú�˰���Ȼ�����˰����
    boolean isTaxPricePriorToPrice =
        this.isTaxPricePriorToPrice(pk_purchaseorg);
    this.cond.setIsTaxOrNet(isTaxPricePriorToPrice);
    // ��������λ����
    this.cond.setUnitPriorType(Condition.MAIN_PRIOR);
    Integer fbuysellflag =
        (Integer) panel.getBodyValueAt(rowno, ArriveItemVO.FBUYSELLFLAG);
    // �Ƿ���ҵ�񣨹�������Ϊ�������ۡ����ڲɹ�ʱ��Ϊ���ҵ��
    this.cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(
        fbuysellflag)
        || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));

    return this.cond;
  }

  @Override
  public IRelationForItems getIRelationForItem(String itemKey) {
    IRelationForItems item = new RelationItemForCal();
    // ԭ�Һ�˰����--����������ԭ�Һ�˰����
    item.setNorigtaxnetpriceKey(ArriveItemVO.NORIGTAXPRICE);
    // ԭ����˰����--����������ԭ����˰����
    item.setNorignetpriceKey(ArriveItemVO.NORIGPRICE);
    // ���Һ�˰����--�������������Һ�˰����
    item.setNtaxnetpriceKey(ArriveItemVO.NTAXPRICE);
    // ������˰����--����������������˰����
    item.setNnetpriceKey(ArriveItemVO.NPRICE);
    return item;
  }

  private boolean isChangePrice(String pk_org) {
    if (StringUtils.isBlank(pk_org)) {
      return true;
    }
    return PUParaValue.po84.adjust_price == PUSysParamUtil.getPO84(pk_org);
  }

  private boolean isFixUnitRate(String material, String cunitid,
      String castunitid) {
    boolean flag = true;
    if (material == null || cunitid == null || castunitid == null) {
      return flag;
    }
    flag =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
            cunitid, castunitid);
    return flag;
  }

  private boolean isTaxPricePriorToPrice(String pk_org) {
    if (StringUtils.isBlank(pk_org)) {
      return true;
    }
    return PricePriority.TAXPRICE_PRIOR_TO_PRICE == PUSysParamUtil
        .getPO28(pk_org);
  }
}

class CanArriveNumCalc {

  private CardBodyAfterEditEvent e;

  private UFDouble newNum = null;

  private UFDouble oldNum = null;

  CanArriveNumCalc(CardBodyAfterEditEvent e) {
    this.e = e;
    if (ArriveItemVO.NNUM.equals(e.getKey())) {
      this.oldNum = (UFDouble) e.getOldValue();
      this.newNum = (UFDouble) e.getValue();
    }
    else {
      this.oldNum =
          (UFDouble) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              ArriveItemVO.NNUM);
    }
  }

  /**
   * ��������������������仯����Ҫ����ɵ���������
   */
  void calc() {
    BillItem bi =
        this.e.getBillCardPanel().getBodyItem(ArriveItemVO.NCANARRIVENUM);
    if (null == bi || !bi.isShow()) { // û������ֶΣ���δ��ʾ�򲻴���
      return;
    }
    this.newNum =
        null == this.newNum ? (UFDouble) this.e.getBillCardPanel()
            .getBodyValueAt(this.e.getRow(), ArriveItemVO.NNUM) : this.newNum;
    // ��������������������仯����Ҫ����ɵ���������
    if (!MathTool.equals(this.newNum, this.oldNum)) {
      UFDouble canNum =
          (UFDouble) this.e.getBillCardPanel().getBodyValueAt(this.e.getRow(),
              ArriveItemVO.NCANARRIVENUM);
      canNum = MathTool.add(canNum, MathTool.sub(this.oldNum, this.newNum));
      this.e.getBillCardPanel().setBodyValueAt(canNum, this.e.getRow(),
          ArriveItemVO.NCANARRIVENUM);
    }
  }
}

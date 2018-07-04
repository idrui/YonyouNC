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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-2 下午06:39:15
 */
public class RelationCalculate extends AbstractRelationCalculateListener {

  private Condition cond;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    CanArriveNumCalc canArriveCalc = new CanArriveNumCalc(e);
    super.afterEdit(e);
    // 如果主数量（到货数量变化，则要重算可到货数量）
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
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    this.cond = new Condition();
    // 设置是否进行本币换算
    this.cond.setIsCalLocalCurr(true);
    // 设置调单价方式调折扣
    boolean isChangePrice = this.isChangePrice(pk_purchaseorg);
    this.cond.setIsChgPriceOrDiscount(isChangePrice);
    // 设置是否固定单位换算率
    String material =
        (String) panel.getBodyValueAt(rowno, ArriveItemVO.PK_MATERIAL);
    String cunitid = (String) panel.getBodyValueAt(rowno, ArriveItemVO.CUNITID);
    String castunitid =
        (String) panel.getBodyValueAt(rowno, ArriveItemVO.CASTUNITID);
    this.cond.setIsFixNchangerate(this.isFixUnitRate(material, cunitid,
        castunitid));
    // 是否固定报价单位换算率
    this.cond.setIsFixNqtunitrate(false);

    // 设置含税优先还是无税优先
    boolean isTaxPricePriorToPrice =
        this.isTaxPricePriorToPrice(pk_purchaseorg);
    this.cond.setIsTaxOrNet(isTaxPricePriorToPrice);
    // 主计量单位优先
    this.cond.setUnitPriorType(Condition.MAIN_PRIOR);
    Integer fbuysellflag =
        (Integer) panel.getBodyValueAt(rowno, ArriveItemVO.FBUYSELLFLAG);
    // 是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
    this.cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(
        fbuysellflag)
        || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));

    return this.cond;
  }

  @Override
  public IRelationForItems getIRelationForItem(String itemKey) {
    IRelationForItems item = new RelationItemForCal();
    // 原币含税净价--》到货单：原币含税单价
    item.setNorigtaxnetpriceKey(ArriveItemVO.NORIGTAXPRICE);
    // 原币无税净价--》到货单：原币无税单价
    item.setNorignetpriceKey(ArriveItemVO.NORIGPRICE);
    // 本币含税净价--》到货单：本币含税单价
    item.setNtaxnetpriceKey(ArriveItemVO.NTAXPRICE);
    // 本币无税净价--》到货单：本币无税单价
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
   * 如果主数量（到货数量变化，则要重算可到货数量）
   */
  void calc() {
    BillItem bi =
        this.e.getBillCardPanel().getBodyItem(ArriveItemVO.NCANARRIVENUM);
    if (null == bi || !bi.isShow()) { // 没有这个字段，或未显示则不处理
      return;
    }
    this.newNum =
        null == this.newNum ? (UFDouble) this.e.getBillCardPanel()
            .getBodyValueAt(this.e.getRow(), ArriveItemVO.NNUM) : this.newNum;
    // 如果主数量（到货数量变化，则要重算可到货数量）
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

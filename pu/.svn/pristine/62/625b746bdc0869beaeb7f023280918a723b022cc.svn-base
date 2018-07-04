package nc.ui.pu.m24.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 价格结算单联动计算。
 * 由于在模板上设置了精度，此类没有进行精度处理。
 * 
 * @since 6.0
 * @version 2012-8-13 下午02:26:36
 * @author lixyp
 */
@SuppressWarnings("restriction")
public class RelationCalculate implements ICardBodyAfterEditEventListener {

  private CardBodyAfterEditEvent event = null;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    this.event = e;
    this.calculate();
    this.event = null;
  }

  /**
   * 计算本币无税单价
   */
  private void calcPrice() {
    Integer ftaxtypeflag =
        (Integer) this.getBodyValue(PricestlItemVO.FTAXTYPEFLAG);
    UFDouble ntaxprice = (UFDouble) this.getBodyValue(PricestlItemVO.NTAXPRICE);
    UFDouble ntaxrate = (UFDouble) this.getBodyValue(PricestlItemVO.NTAXRATE);
    ntaxrate = CalculatorUtil.div(ntaxrate, new UFDouble(100));
    UFDouble nprice = null;
    if (EnumDiscounttaxtype.TAXIN.toInteger().equals(ftaxtypeflag)) {
      nprice =
          CalculatorUtil.multiply(ntaxprice,
              MathTool.sub(UFDouble.ONE_DBL, ntaxrate));
    }
    else {
      nprice =
          CalculatorUtil.div(ntaxprice,
              MathTool.add(UFDouble.ONE_DBL, ntaxrate));
    }
    this.setBodyValue(PricestlItemVO.NPRICE, nprice);
  }

  /**
   * 计算本币价税合计
   */
  private void calcTaxMny() {
    UFDouble ninnum = (UFDouble) this.getBodyValue(PricestlItemVO.NINNUM);
    UFDouble ntaxprice = (UFDouble) this.getBodyValue(PricestlItemVO.NTAXPRICE);
    UFDouble ntaxmny = CalculatorUtil.multiply(ninnum, ntaxprice);
    this.setBodyValue(PricestlItemVO.NTAXMNY, ntaxmny);
  }

  /**
   * 计算本币含税单价
   * 
   * @return 本币含税单价
   */
  private void calcTaxPrice() {
    Integer ftaxtypeflag =
        (Integer) this.getBodyValue(PricestlItemVO.FTAXTYPEFLAG);
    UFDouble nprice = (UFDouble) this.getBodyValue(PricestlItemVO.NPRICE);
    UFDouble ntaxrate = (UFDouble) this.getBodyValue(PricestlItemVO.NTAXRATE);
    ntaxrate = CalculatorUtil.div(ntaxrate, new UFDouble(100));
    UFDouble ntaxprice = null;
    if (EnumDiscounttaxtype.TAXIN.toInteger().equals(ftaxtypeflag)) {
      ntaxprice =
          CalculatorUtil.div(nprice, MathTool.sub(UFDouble.ONE_DBL, ntaxrate));
    }
    else {
      ntaxprice =
          CalculatorUtil.multiply(nprice,
              MathTool.add(UFDouble.ONE_DBL, ntaxrate));
    }
    this.setBodyValue(PricestlItemVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 联动计算。
   */
  private void calculate() {
    // 编辑的是本币含税单价
    if (PricestlItemVO.NTAXPRICE.equals(this.event.getKey())) {
      this.calcPrice();
      this.calcTaxMny();
    }
    // 编辑的是本币无税单价
    if (PricestlItemVO.NPRICE.equals(this.event.getKey())) {
      this.calcTaxPrice();
      this.calcTaxMny();
    }
  }

  /**
   * 获取表体的值。
   * 
   * @param key 表体的字段
   * @return 字段对应的值
   */
  private Object getBodyValue(String key) {
    return this.event.getBillCardPanel().getBodyValueAt(this.event.getRow(),
        key);
  }

  /**
   * 设置表体的值
   * 
   * @param key 表体的字段
   * @param value 字段对应的值。
   */
  private void setBodyValue(String key, Object value) {
    this.event.getBillCardPanel().setBodyValueAt(value, this.event.getRow(),
        key);
  }
}

package nc.vo.pu.m25.settle;


/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于采购结算，主要针对：费用类发票、折扣类发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-4 下午04:36:37
 */
public class FeeDiscountSettleVO extends InvoiceSettleVO {

  public static final String FAPPORTIONMODE = "fapportionmode";

  public static final String PK_COSTFACTOR = "pk_costfactor";

  private static final long serialVersionUID = -4165740984074020828L;

  // 分摊方式
  private Integer fapportionmode;

  // 成本要素
  private String pk_costfactor;

  @Override
  public Object getAttributeValue(String name) {
    if (FeeDiscountSettleVO.PK_COSTFACTOR.equals(name)) {
      return this.getPk_costfactor();// 成本要素
    }
    else if (FeeDiscountSettleVO.FAPPORTIONMODE.equals(name)) {
      return this.getFapportionmode();// 分摊方式
    }

    return super.getAttributeValue(name);
  }

  public Integer getFapportionmode() {
    return this.fapportionmode;
  }

  public String getPk_costfactor() {
    return this.pk_costfactor;
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    if (FeeDiscountSettleVO.PK_COSTFACTOR.equals(name)) {
      this.setPk_costfactor((String) value);// 成本要素
    }
    else if (FeeDiscountSettleVO.FAPPORTIONMODE.equals(name)) {
      this.setFapportionmode((Integer) value);// 分摊方式
    }

    super.setAttributeValue(name, value);
  }

  public void setFapportionmode(Integer fapportionmode) {
    this.fapportionmode = fapportionmode;
  }

  public void setPk_costfactor(String pkCostfactor) {
    this.pk_costfactor = pkCostfactor;
  }
}

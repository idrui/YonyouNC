package nc.vo.pu.m25.settle;


/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ڲɹ����㣬��Ҫ��ԣ������෢Ʊ���ۿ��෢Ʊ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-4 ����04:36:37
 */
public class FeeDiscountSettleVO extends InvoiceSettleVO {

  public static final String FAPPORTIONMODE = "fapportionmode";

  public static final String PK_COSTFACTOR = "pk_costfactor";

  private static final long serialVersionUID = -4165740984074020828L;

  // ��̯��ʽ
  private Integer fapportionmode;

  // �ɱ�Ҫ��
  private String pk_costfactor;

  @Override
  public Object getAttributeValue(String name) {
    if (FeeDiscountSettleVO.PK_COSTFACTOR.equals(name)) {
      return this.getPk_costfactor();// �ɱ�Ҫ��
    }
    else if (FeeDiscountSettleVO.FAPPORTIONMODE.equals(name)) {
      return this.getFapportionmode();// ��̯��ʽ
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
      this.setPk_costfactor((String) value);// �ɱ�Ҫ��
    }
    else if (FeeDiscountSettleVO.FAPPORTIONMODE.equals(name)) {
      this.setFapportionmode((Integer) value);// ��̯��ʽ
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

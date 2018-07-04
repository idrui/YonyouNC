package nc.vo.pu.m27.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>结算行类型</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-1-26 上午11:25:10
 */
public class EnumMatchRowType extends NCMDEnum {

  /** 进口调整货物，9 */
  public static final EnumMatchRowType AdjustGoods = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(9));

  /** 折扣，6 */
  public static final EnumMatchRowType Discount = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(6));

  /** 劳务，5 */
  public static final EnumMatchRowType Fee = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(5));

  /** 直运无入库单之发票， 4 */
  public static final EnumMatchRowType InvoiceDTransPO = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(4));

  /** 异物料结算之发票，8 */
  public static final EnumMatchRowType InvoiceInDiffMatch = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(8));

  /** 采购发票红兰对冲，3 */
  public static final EnumMatchRowType InvoiceRush = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(3));

  /** 费用结算之入库单，1 */
  public static final EnumMatchRowType StockFeeSettle = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(1));

  /** 异物料结算之入库单，7 */
  public static final EnumMatchRowType StockInDiffMatch = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(7));

  /** 入库单发票匹配 ，0 */
  public static final EnumMatchRowType StockInvoiceMatch = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(0));

  /** 采购入库单红兰对冲，2 */
  public static final EnumMatchRowType StockRush = MDEnum.valueOf(
      EnumMatchRowType.class, Integer.valueOf(2));

  public EnumMatchRowType(IEnumValue value) {
    super(value);
  }

}

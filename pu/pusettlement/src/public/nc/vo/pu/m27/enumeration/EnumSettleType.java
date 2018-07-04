package nc.vo.pu.m27.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算的方式（自动结算、同物料结算、异物料结算、无发票结算、费用结算等）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 下午02:32:15
 */
public enum EnumSettleType {
  /** 异物料结算 */
  DIFFERENT_MATERIAL,

  /** 费用结算 */
  FEE,

  /** 发票自动结算 */
  INVOICE_AUTO,

  /** 进口异物料结算 */
  IT_DIFFERENT_MATERIAL,

  /** 进口费用结算 */
  IT_FEE,

  /** 进口发票自动结算 */
  IT_INVOICE_AUTO,

  /** 进口同物料结算 */
  IT_SAME_MATERIAL,

  /** 进口界面自动结算 */
  IT_UI_AUTO,

  /** 进口无发票结算 */
  IT_WITHOUT_INVOICE,

  /** 同物料结算 */
  SAME_MATERIAL,

  /** 界面自动结算 */
  UI_AUTO,

  /** VMI结算 */
  VOI_CONSUME,

  /** VMI费用结算 */
  VOI_CONSUME_FEE,

  /** 无发票结算 */
  WITHOUT_INVOICE

}

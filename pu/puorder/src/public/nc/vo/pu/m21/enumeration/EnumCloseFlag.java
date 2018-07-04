/**
 * $$�ļ�˵��$$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-06 14:48:39
 */
package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ر�����
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-01-06 14:48:39
 */
public class EnumCloseFlag extends NCMDEnum {
  /** �����ر� **/
  public static final EnumCloseFlag ARRIVE_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(2));

  /** ���չر� **/
  public static final EnumCloseFlag FINAL_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(0));

  /** ��Ʊ�ر� **/
  public static final EnumCloseFlag INVOICE_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(4));

  /** ����ر� **/
  public static final EnumCloseFlag PAY_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(5));

  /** �йر� **/
  public static final EnumCloseFlag ROW_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(1));

  /** ���ر� **/
  public static final EnumCloseFlag STOCK_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(3));

  public EnumCloseFlag(IEnumValue enumvalue) {
    super(enumvalue);
  }

  /**
   * ���෽����д
   * 
   * @see nc.md.model.impl.MDEnum#getReturnType()
   */
  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
  }

  /**
   * ���෽����д
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}

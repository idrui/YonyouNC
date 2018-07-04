/**
 * $$�ļ�˵��$$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-11 09:35:24
 */
package nc.vo.pu.costfactor.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ��̯��ʽö���ࣺ0������ 1����� 2������ 3�����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-01-11 09:35:24
 */
public class ApportionMode extends MDEnum {
  /** ����� **/
  public static final ApportionMode MONEY = MDEnum.valueOf(ApportionMode.class,
      Integer.valueOf(1));

  /** ������ **/
  public static final ApportionMode NUM = MDEnum.valueOf(ApportionMode.class,
      Integer.valueOf(0));

  /** ����� **/
  public static final ApportionMode VOLUME = MDEnum.valueOf(
      ApportionMode.class, Integer.valueOf(3));

  /** ������ **/
  public static final ApportionMode WEIGHT = MDEnum.valueOf(
      ApportionMode.class, Integer.valueOf(2));

  /**
   * ApportionMode �Ĺ�����
   * 
   * @param enumvalue
   */
  public ApportionMode(IEnumValue enumvalue) {
    super(enumvalue);
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

/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-2 ����01:25:42
 */
package nc.vo.pu.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����״̬ö�ٹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-2 ����01:25:42
 */
public class POEnumBillStatus extends NCMDEnum {
  /** ����ͨ�� */
  public static final POEnumBillStatus APPROVE =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(3));

  /** ������ */
  public static final POEnumBillStatus APPROVING =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(2));

  /** �ύ */
  public static final POEnumBillStatus COMMIT =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(1));

  /** ���� */
  public static final POEnumBillStatus FREE =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(0));

  /** ����δͨ�� */
  public static final POEnumBillStatus NOPASS =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(4));

  /**
   * POEnumBillStatus �Ĺ�����
   * 
   * @param enumvalue
   */
  public POEnumBillStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}

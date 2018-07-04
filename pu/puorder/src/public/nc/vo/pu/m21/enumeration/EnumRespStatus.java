package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * ��Ӧ״̬
 * 
 * @since 6.0
 * @version 2011-3-15 ����03:19:06
 * @author wuxla
 */

public class EnumRespStatus extends NCMDEnum {
  /**
   * ����
   */
  public static final EnumRespStatus ACCEPT = MDEnum.valueOf(
      EnumRespStatus.class, Integer.valueOf(2));

  /**
   * �򷽱��
   */
  public static final EnumRespStatus BUYERCHANGE = MDEnum.valueOf(
      EnumRespStatus.class, Integer.valueOf(5));

  /**
   * ��ȷ��
   */
  public static final EnumRespStatus ONCONFIRM = MDEnum.valueOf(
      EnumRespStatus.class, Integer.valueOf(1));

  /**
   * �ܾ�
   */
  public static final EnumRespStatus REFUSE = MDEnum.valueOf(
      EnumRespStatus.class, Integer.valueOf(3));

  /**
   * �������
   */
  public static final EnumRespStatus SELLERCHANGE = MDEnum.valueOf(
      EnumRespStatus.class, Integer.valueOf(4));

  /**
   * @param enumValue
   */
  public EnumRespStatus(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

}

package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * ��Ӧ�̽���״̬
 * 
 * @since 6.0
 * @version 2011-3-15 ����03:19:35
 * @author wuxla
 */

public class EnumStoreStatus extends NCMDEnum {
  /**
   * ȫ������
   */
  public static final EnumStoreStatus ALLSENDOUT = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(5));

  /**
   * ��;
   */
  public static final EnumStoreStatus ONLOAD = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(4));

  /**
   * ����
   */
  public static final EnumStoreStatus ONPRODUCE = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(2));

  /**
   * �ɹ�
   */
  public static final EnumStoreStatus PURCHASE = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(1));

  /**
   * ���
   */
  public static final EnumStoreStatus STOCK = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(3));

  /**
   * @param enumValue
   */
  public EnumStoreStatus(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

}

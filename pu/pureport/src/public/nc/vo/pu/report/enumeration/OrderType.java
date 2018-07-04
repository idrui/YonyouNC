package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * �ۺ��ձ� ������Χ
 * 
 * @since 6.0
 * @version 2011-6-14 ����04:32:44
 * @author liuchx
 */
public class OrderType extends NCMDEnum {

  /**
   * ������
   */
  public static final OrderType PUARRIVE = MDEnum.valueOf(OrderType.class,
      Integer.valueOf(1));

  /**
   * �ɹ���Ʊ
   */
  public static final OrderType PUINVOICE = MDEnum.valueOf(OrderType.class,
      Integer.valueOf(3));

  /**
   * �ɹ�����
   */
  public static final OrderType PUORDER = MDEnum.valueOf(OrderType.class,
      Integer.valueOf(0));

  /**
   * ��ⵥ
   */
  public static final OrderType PURIN = MDEnum.valueOf(OrderType.class,
      Integer.valueOf(2));

  /**
   * �ɹ����㵥
   */
  public static final OrderType PUSETTLE = MDEnum.valueOf(OrderType.class,
      Integer.valueOf(4));

  public OrderType(IEnumValue enumValue) {
    super(enumValue);
  }

}

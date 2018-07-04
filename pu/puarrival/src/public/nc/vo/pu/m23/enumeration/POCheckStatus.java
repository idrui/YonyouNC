package nc.vo.pu.m23.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * ����״̬ö����
 * 
 * @since 6.0
 * @version 2011-7-6 ����09:56:51
 * @author liugxa
 */
public class POCheckStatus extends NCMDEnum {
  /** ������� **/
  public static final POCheckStatus CHECKED =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(2));

  /** �ѱ���δ���ؽ��� **/
  public static final POCheckStatus CHECKING =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(1));

  /** δ���� **/
  public static final POCheckStatus UNCHECK =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(0));

  /**
   * POEnumBillStatus �Ĺ�����
   * 
   * @param enumvalue
   */
  public POCheckStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}

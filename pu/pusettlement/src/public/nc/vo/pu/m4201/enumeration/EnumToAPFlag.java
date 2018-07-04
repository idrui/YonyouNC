package nc.vo.pu.m4201.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>��Ӧ����־</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-1-26 ����11:25:10
 */
public class EnumToAPFlag extends NCMDEnum {
  /**
   * ȷ��Ӧ��
   */
  public static final EnumToAPFlag ConfirmToAP = MDEnum.valueOf(
      EnumToAPFlag.class, Integer.valueOf(2));

  /**
   * �ݹ�Ӧ��
   */
  public static final EnumToAPFlag EstimateToAP = MDEnum.valueOf(
      EnumToAPFlag.class, Integer.valueOf(1));

  /**
   * δ��Ӧ��
   */
  public static final EnumToAPFlag NotToAP = MDEnum.valueOf(EnumToAPFlag.class,
      Integer.valueOf(0));

  public EnumToAPFlag(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
  }
}

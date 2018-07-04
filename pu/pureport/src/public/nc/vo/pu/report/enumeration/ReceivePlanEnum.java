package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * �����ƻ� ͳ�Ʒ�ʽö����
 * 
 * @since 6.0
 * @version 2011-3-23 ����09:25:27
 * @author wangfengd
 */
public class ReceivePlanEnum extends NCMDEnum {

  /** �����ƻ���ϸ **/
  public static final ReceivePlanEnum PLAN_DETAIL = MDEnum.valueOf(
      ReceivePlanEnum.class, Integer.valueOf(1));

  /** �����ƻ����� **/
  public static final ReceivePlanEnum PLAN_GATHER = MDEnum.valueOf(
      ReceivePlanEnum.class, Integer.valueOf(0));

  public ReceivePlanEnum(IEnumValue enumValue) {
    super(enumValue);
  }
}

package nc.vo.pu.m25.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
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
 * @author zhangyhh
 * @time 2013-8-20 ����03:05:27
 */
public class EffectDateBill extends NCMDEnum {

  /** ���ں�ͬ **/
  public static final EffectDateBill IMPORTCONTRACT = MDEnum.valueOf(
      EffectDateBill.class, Integer.valueOf(0));;

  /** ������ϸ�� **/
  public static final EffectDateBill IMPORTDETAIL = MDEnum.valueOf(
      EffectDateBill.class, Integer.valueOf(1));;;

  public EffectDateBill(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}

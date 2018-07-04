package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class EffectMonthEnum extends NCMDEnum{

	/**当月生效*/
	public static final EffectMonthEnum CURRENT_MONTH = MDEnum.valueOf(EffectMonthEnum.class,
		      Integer.valueOf(0));
	/**下月生效*/
	public static final EffectMonthEnum NEXT_MONTH = MDEnum.valueOf(EffectMonthEnum.class,
		      Integer.valueOf(1));
	
	public EffectMonthEnum(IEnumValue enumValue) {
		super(enumValue);
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

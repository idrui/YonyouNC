package nc.vo.pu.m25.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>此处简要描述此枚举的功能 </b>
 * <p>
 *   此处添加该枚举的描述信息
 * </p>
 *  创建日期:2018-3-20
 * @author 
 * @version NCPrj ??
 */
public class InvoiceRowType extends MDEnum{
	public InvoiceRowType(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final InvoiceRowType 货物行 = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(0));
	
	
	public static final InvoiceRowType 折扣行 = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(1));
	
	
	public static final InvoiceRowType 劳务行 = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(2));
	
	
	public static final InvoiceRowType 零数量行 = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(3));
	

}
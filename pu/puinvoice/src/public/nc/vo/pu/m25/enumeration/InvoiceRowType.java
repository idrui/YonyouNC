package nc.vo.pu.m25.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2018-3-20
 * @author 
 * @version NCPrj ??
 */
public class InvoiceRowType extends MDEnum{
	public InvoiceRowType(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final InvoiceRowType ������ = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(0));
	
	
	public static final InvoiceRowType �ۿ��� = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(1));
	
	
	public static final InvoiceRowType ������ = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(2));
	
	
	public static final InvoiceRowType �������� = MDEnum.valueOf(InvoiceRowType.class, Integer.valueOf(3));
	

}
package nc.vo.pu.m21.rule.api.fill;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.scmpub.fill.billfill.TrantypeCodeBillFill;

/**
 * 
 * @description �����������ͱ���code��䴦��
 * @scene
 * 
 * @param
 * 
 * @functionName �ɹ�����API����
 * @since 6.5
 * @version 2015-11-16 ����4:05:33
 * @author zhangshqb
 */
public class Vtrantypecode extends TrantypeCodeBillFill {

	public Vtrantypecode(String pk_billTypeCode) {
		super(pk_billTypeCode);
	}

	@Override
	public String getTransTypeCodeFieldName() {
		return OrderHeaderVO.VTRANTYPECODE;
	}

}

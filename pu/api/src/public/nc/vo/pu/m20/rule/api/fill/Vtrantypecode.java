package nc.vo.pu.m20.rule.api.fill;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.scmpub.fill.billfill.TrantypeCodeBillFill;

/**
 * 
 * @description 请购单交易类型编码code填充规则
 * @scene
 * 
 * @param
 * 
 * @functionName 请购单API保存
 * @since 6.5
 * @version 2015-11-16 下午3:59:45
 * @author zhangshqb
 */
public class Vtrantypecode extends TrantypeCodeBillFill {

	public Vtrantypecode(String pk_billTypeCode) {
		super(pk_billTypeCode);
	}

	@Override
	public String getTransTypeCodeFieldName() {
		return PraybillHeaderVO.VTRANTYPECODE;
	}

}

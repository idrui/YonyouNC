package nc.vo.pu.m23.rule.api.fill;

import nc.vo.scmpub.fill.billfill.TrantypeCodeBillFill;

public class TrantypeCodeFillFor23 extends TrantypeCodeBillFill{

	public TrantypeCodeFillFor23(String pk_billTypeCode) {
		super(pk_billTypeCode);
	}

	@Override
	public String getTransTypeCodeFieldName() {
		// TODO Auto-generated method stub
		return "vtrantypecode";
	}

}

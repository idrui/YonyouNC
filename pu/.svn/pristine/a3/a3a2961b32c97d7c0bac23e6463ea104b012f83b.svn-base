package nc.vo.pu.m23.rule.api;

import nc.vo.pu.m23.rule.api.check.PUArriveVONullValidate;
import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;
/**
 * 
 * @description
 *		到货单保存前单据合法性校验，包括非空校验、字段值合法性校验、无子表校验等
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-28 下午9:57:57
 * @author wandl
 */
public class PUArriveVOValidator extends BillVOValidator{

	@Override
	public IVOValidate[] getVOValidators() {
		return new IVOValidate[]{
				new PUArriveVONullValidate(),
				
		};
	}

}

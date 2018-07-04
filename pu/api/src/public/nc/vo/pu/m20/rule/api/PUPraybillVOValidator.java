package nc.vo.pu.m20.rule.api;

import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;

/**
 * 
 * @description 请购单API保存校验规则 （多个针对请购单的校验规则均可）
 * @scene
 * 
 * @param
 * 
 * @functionName 请购单API保存规则校验
 * @since 6.5
 * @version 2015-11-16 下午4:03:32
 * @author zhangshqb
 */
public class PUPraybillVOValidator extends BillVOValidator {

	@Override
	public IVOValidate[] getVOValidators() {
		return new IVOValidate[] { new PUPraybillVONullValidate() };
	}

}

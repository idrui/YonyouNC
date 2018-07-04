package nc.vo.pu.m21.rule.api;

import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;

/**
 * 
 * @description 针对订单API必输项的一组校验
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:06:54
 * @author zhangshqb
 */
public class PUOrderVOValidator extends BillVOValidator {

	@Override
	public IVOValidate[] getVOValidators() {
		return new IVOValidate[] { new PUOrderVONullValidate() };
	}

}

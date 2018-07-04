package nc.vo.pu.m21.rule.api;

import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;

/**
 * 
 * @description ��Զ���API�������һ��У��
 * @scene
 * 
 * @param
 * 
 * @functionName �ɹ�����API����
 * @since 6.5
 * @version 2015-11-16 ����4:06:54
 * @author zhangshqb
 */
public class PUOrderVOValidator extends BillVOValidator {

	@Override
	public IVOValidate[] getVOValidators() {
		return new IVOValidate[] { new PUOrderVONullValidate() };
	}

}

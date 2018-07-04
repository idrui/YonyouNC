package nc.vo.pu.m20.rule.api;

import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;

/**
 * 
 * @description �빺��API����У����� ���������빺����У�������ɣ�
 * @scene
 * 
 * @param
 * 
 * @functionName �빺��API�������У��
 * @since 6.5
 * @version 2015-11-16 ����4:03:32
 * @author zhangshqb
 */
public class PUPraybillVOValidator extends BillVOValidator {

	@Override
	public IVOValidate[] getVOValidators() {
		return new IVOValidate[] { new PUPraybillVONullValidate() };
	}

}

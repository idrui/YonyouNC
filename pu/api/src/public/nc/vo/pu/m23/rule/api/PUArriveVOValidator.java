package nc.vo.pu.m23.rule.api;

import nc.vo.pu.m23.rule.api.check.PUArriveVONullValidate;
import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;
/**
 * 
 * @description
 *		����������ǰ���ݺϷ���У�飬�����ǿ�У�顢�ֶ�ֵ�Ϸ���У�顢���ӱ�У���
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-28 ����9:57:57
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

package nc.vo.pu.m23.rule.api.check;

import java.util.List;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.check.vovalidate.VONullValidate;

/**
 * 
 * @description
 *			�ɹ�����������ǿ�У��
 * @scene
 * 			����������
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-23 ����3:31:54
 * @author wandl
 */
public class ArriveVOMinNullValidate extends VONullValidate{

	@Override
	public String[] getHeadNotNullFields() {
		return new String[]{
				ArriveHeaderVO.PK_PURCHASEORG,
				ArriveHeaderVO.PK_PUPSNDOC
		};
	}

	@Override
	public String[] getBodyNotNullFields() {
		return new String[]{
				ArriveItemVO.VSOURCECODE,ArriveItemVO.VSOURCEROWNO,
				ArriveItemVO.CSOURCETYPECODE
		};
	}

	@Override
	public MapList<String, String> getMultiBodyNotNullFields() {
		return null;
	}

	@Override
	public void otherCheck(AbstractBill billVO,
			List<ValidationException> exceptions) {
		String[] checkField = new String[]{
				
		};
		
	}

}

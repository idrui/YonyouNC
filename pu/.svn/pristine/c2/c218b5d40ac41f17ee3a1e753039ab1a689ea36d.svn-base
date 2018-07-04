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
 *			采购到货单保存非空校验
 * @scene
 * 			到货单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-23 下午3:31:54
 * @author wandl
 */
public class PUArriveVONullValidate extends VONullValidate{

	@Override
	public String[] getHeadNotNullFields() {
		return new String[]{
				ArriveHeaderVO.PK_ORG,ArriveHeaderVO.PK_PURCHASEORG,
				ArriveHeaderVO.PK_SUPPLIER,ArriveHeaderVO.PK_DEPT,
				ArriveHeaderVO.PK_PUPSNDOC
		};
	}

	@Override
	public String[] getBodyNotNullFields() {
		return new String[]{
				ArriveItemVO.PK_SRCMATERIAL,ArriveItemVO.VCHANGERATE,
				ArriveItemVO.NASTNUM,ArriveItemVO.NPLANASTNUM
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

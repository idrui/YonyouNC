package nc.vo.pu.m20.rule.api;

import java.util.List;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.check.vovalidate.VONullValidate;

/**
 * 
 * @description 校验请购单必输项
 * @scene
 * 
 * @param
 * 
 * @functionName 请购单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:01:22
 * @author zhangshqb
 */
public class PUPraybillVONullValidate extends VONullValidate {

	@Override
	public String[] getHeadNotNullFields() {
		return new String[] { PraybillHeaderVO.PK_ORG };
	}

	@Override
	public String[] getBodyNotNullFields() {
		return new String[] { PraybillItemVO.PK_MATERIAL, PraybillItemVO.NASTNUM };
	}

	@Override
	public MapList<String, String> getMultiBodyNotNullFields() {
		return null;
	}

	@Override
	public void otherCheck(AbstractBill billVO,
			List<ValidationException> exceptions) {
		PraybillVO vo = (PraybillVO) billVO;
		PraybillHeaderVO hvo = vo.getHVO();
		// 自制的请购单不能是直运类型的
		UFBoolean bdirecttransit = hvo.getBdirecttransit();
		if (bdirecttransit != null && bdirecttransit.booleanValue()) {
			new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4004020_0", "04004020-0120"));
		}
	}
}

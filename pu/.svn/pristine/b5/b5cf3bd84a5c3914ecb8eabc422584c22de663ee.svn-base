package nc.vo.pu.m23.rule.api.fill;

import nc.pubitf.uapbd.IMaterialPubService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.scmpub.fill.billfill.AssUnitAndChangeRateBillFill;

/**
 * @description
 *		到货单换算率和固定换算率填充处理
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-28 下午9:32:03
 * @author wandl
 */
public class PUAssUnitAndChangeRate extends
AssUnitAndChangeRateBillFill {
	@Override
	public String getMaterialVIDFieldName() {
		return ArriveItemVO.PK_MATERIAL;
	}

	@Override
	public String getUnitFieldName() {
		return ArriveItemVO.CUNITID;
	}

	@Override
	public String getAssUnitFieldName() {
		return ArriveItemVO.CASTUNITID;
	}

	@Override
	public String getVchangerateFieldName() {
		return ArriveItemVO.VCHANGERATE;
	}

	@Override
	public int getAssUnitType() {
		// TODO Auto-generated method stub
		return IMaterialPubService.MATERIAL_CONVERT_STOCK;
	}
}


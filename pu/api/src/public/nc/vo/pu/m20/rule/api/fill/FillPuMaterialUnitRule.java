package nc.vo.pu.m20.rule.api.fill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.ic.material.define.InvBasVO;
import nc.vo.ic.material.query.InvInfoQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description 补充订单表体的物料的主单位
 * @scene 请购单保存API
 * @param
 * 
 * @functionName
 * 
 * @since 6.5
 * @version 2015-12-2 下午4:42:05
 * @author zhangshqb
 */
public class FillPuMaterialUnitRule implements IBillValueFill {

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		PraybillVO[] vos = (PraybillVO[]) billVOs;
		Map<String, InvBasVO> materialBaseInfo = this.getMaterialBaseInfo(vos);
		for (PraybillVO vo : vos) {
			PraybillItemVO[] bvo = vo.getBVO();
			for (PraybillItemVO item : bvo) {
				String pk_material = item.getPk_material();
				InvBasVO invBasVO = materialBaseInfo.get(pk_material);
				if (invBasVO == null) {
					continue;
				}
				if (item.getCunitid() == null) {
					item.setCunitid(invBasVO.getPk_measdoc());
				}
				if (item.getPk_srcmaterial() == null ){
					item.setPk_srcmaterial(invBasVO.getPk_source());
				}
			}
		}

		return vos;
	}

	private Map<String, InvBasVO> getMaterialBaseInfo(PraybillVO[] vos) {
		Set<String> materials = new HashSet<String>();
		for (PraybillVO vo : vos) {
			for (PraybillItemVO item : vo.getBVO()) {
				String pk_material = item.getPk_material();
				materials.add(pk_material);
			}
		}
		InvInfoQuery query = new InvInfoQuery();
		Map<String, InvBasVO> basVOs = new HashMap<String, InvBasVO>();
		basVOs = query.getInvBasVOs(materials.toArray(new String[0]));
		return basVOs;
	}
}

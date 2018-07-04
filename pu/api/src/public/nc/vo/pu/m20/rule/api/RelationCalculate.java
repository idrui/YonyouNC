package nc.vo.pu.m20.rule.api;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.calculate.PuSimpleCalCondition;
import nc.vo.pu.pub.calculate.PuSimpleCalculateDataSet;
import nc.vo.pu.pub.calculate.PuSimpleCalculator;
import nc.vo.pu.pub.calculate.PuSimpleRelationItems;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pubapp.scale.ScaleUtils;

public class RelationCalculate {

	public void calculate(PraybillVO[] vos) {
		for (PraybillVO vo : vos) {
			PraybillItemVO[] bvos = vo.getBVO();
			for (int i = 0; i < bvos.length; i++) {
				this.calculate(vo, i);
			}
		}
	}

	private void calculate(PraybillVO vo, int row) {
		String pk_group = vo.getHVO().getPk_group();
		ScaleUtils scale = new ScaleUtils(pk_group);
		BillHelper<PraybillVO> helper = new BillHelper<PraybillVO>(vo);
		PuSimpleCalculator calculator = new PuSimpleCalculator(
				new PuSimpleCalculateDataSet(helper, row, new PuSimpleRelationItems()),
				scale);
		PuSimpleCalCondition condition = new PuSimpleCalCondition();
		String material = (String) helper.getBodyValue(row,
				PraybillItemVO.PK_MATERIAL);
		String cunitid = (String) helper.getBodyValue(row, PraybillItemVO.CUNITID);
		String castunitid = (String) helper.getBodyValue(row,
				PraybillItemVO.CASTUNITID);
		// 设置是否固定单位换算率
		condition.setIsfixedChangeRate(this.isFixUnitRate(material, cunitid,
				castunitid));
		calculator.calculate(condition, PraybillItemVO.NASTNUM);
	}

	private boolean isFixUnitRate(String material, String cunitid,
			String castunitid) {
		boolean flag = true;
		if (material == null || cunitid == null || castunitid == null) {
			return flag;
		}
		flag = MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
				cunitid, castunitid);
		return flag;
	}
}

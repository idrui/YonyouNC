package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description 过滤界面选择的数据，因为BillTransferTool.getClientFullInfoBill
 *              返回的是全VO，包括不选择的数据
 * @scene
 * 生成资产卡片时
 * @param 无
 * 
 * @since 6.3
 * @version 2010-12-23 下午12:36:16
 * @author wuxla
 */

public class FilterBySelectedRule implements IFilterRule<ArriveVO> {
	private ArriveVO[] selectedVOs;

	public FilterBySelectedRule(ArriveVO[] selectedVOs) {
		this.selectedVOs = selectedVOs;
	}

	@Override
	public ArriveVO[] process(ArriveVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return null;
		}
		List<ArriveVO> voList = new ArrayList<ArriveVO>();

		BillIndex index = new BillIndex(vos);
		IVOMeta parentMeta = vos[0].getMetaData().getParent();
		IVOMeta childMeta = vos[0].getMetaData().getVOMeta(ArriveItemVO.class);

		for (ArriveVO vo : this.selectedVOs) {
			List<ArriveItemVO> itemList = new ArrayList<ArriveItemVO>();
			for (ArriveItemVO itemVO : vo.getBVO()) {
				ArriveItemVO newItemVO = (ArriveItemVO) index.get(childMeta,
						itemVO.getPk_arriveorder_b());
				if (newItemVO != null) {
					itemList.add(newItemVO);
				}
			}

			if (itemList.size() == 0) {
				continue;
			}

			ArriveHeaderVO headVO = (ArriveHeaderVO) index.get(parentMeta, vo
					.getHVO().getPk_arriveorder());
			if (headVO != null) {
				ArriveVO newVO = new ArriveVO();
				newVO.setHVO(headVO);
				newVO.setBVO(itemList.toArray(new ArriveItemVO[itemList.size()]));
				voList.add(newVO);
			}
		}

		if (voList.size() > 0) {
			return voList.toArray(new ArriveVO[voList.size()]);
		}

		return null;
	}
}

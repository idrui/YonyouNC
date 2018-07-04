package nc.vo.pu.m4t.rule;

import java.util.Arrays;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description 设置期初暂估单的表体影响成本标志
 * @scene
 * 期初暂估单审批
 * @param 无
 * 
 * @since 6.3
 * @version 2011-12-13 下午1:53:50
 * @author zhaoyha
 */

public class InitialEstAffectCostFlagRule implements IRule<InitialEstVO> {

	
	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {// 将来支持审批流时就有此分支了
			return;
		}
		// 设置是否影响成本标志
		this.setAffectCost(vos);
	}
	/**
	 *设置期初暂估单的表体影响成本标志,根据V61需求，期初暂估只考虑物料价值属性
	 */
	private void setAffectCost(InitialEstVO[] vos) {
		// 根据V61需求，期初暂估只考虑物料价值属性
		Map<String, Map<String, UFBoolean>> map = SettleBillItemVOUtil
				.getEffectByMaterial(Arrays.asList(vos));
		for (InitialEstVO vo : vos) {
			String pk_org = vo.getHeader().getPk_org();
			for (InitialEstItemVO item : vo.getItems()) {
				UFBoolean oldflag = item.getBaffectcost();
				UFBoolean flag = map.get(pk_org).get(item.getPk_material());
				if (flag == null) {
					ExceptionUtils
							.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
									.getNCLangRes().getStrByID("4004060_0",
											"04004060-0244")/*
															 * @res
															 * "业务数据错误，请检查物料是否分配到财务组织！"
															 */);
				} else if (flag.equals(oldflag)) {
					continue;
				}
				item.setBaffectcost(flag);
				item.setStatus(VOStatus.UPDATED);
			}
		}
	}

}

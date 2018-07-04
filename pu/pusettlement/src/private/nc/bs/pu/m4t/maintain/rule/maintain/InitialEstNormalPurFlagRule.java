package nc.bs.pu.m4t.maintain.rule.maintain;

import nc.bs.pu.m27.rule.FillNormalPurFlagRule;
import nc.vo.pu.m4t.entity.InitialEstVO;

/**
 * 
 * @description 根据结算财务组织与收货库存组织所属财务组织是否相同，设置是否走内部交易标志
 * @scene
 * 期初暂估单审批
 * @param 无
 * 
 * @since 6.3
 * @version 2011-2-22 下午01:27:26
 * @author yinfy
 */

public class InitialEstNormalPurFlagRule extends
		FillNormalPurFlagRule<InitialEstVO> {
	@Override
	protected String getpk_fiorg(InitialEstVO vo) {
		return vo.getHeader().getPk_org();
	}

	@Override
	protected String getpk_storeorg(InitialEstVO vo) {
		return vo.getHeader().getPk_stockorg();
	}
}

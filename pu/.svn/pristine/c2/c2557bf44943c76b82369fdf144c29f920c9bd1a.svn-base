package nc.bs.pu.m4202.rule;

import nc.bs.pu.m27.rule.FillNormalPurFlagRule;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * 根据结算财务组织与收货库存组织所属财务组织是否相同，设置是否走内部交易标志
 * 
 * @since 6.0
 * @version 2011-1-29 下午01:43:50
 * @author yinfy
 */

public class VMINormalPurFlagRule extends FillNormalPurFlagRule<VmiFIVO> {
  @Override
  protected String getpk_fiorg(VmiFIVO vo) {
    return vo.getParentVO().getPk_financeorg();
  }

  @Override
  protected String getpk_storeorg(VmiFIVO vo) {
    return (String) vo.getParentVO().getAttributeValue(
        VmiFIHeaderVO.PK_STOREORG);
  }
}

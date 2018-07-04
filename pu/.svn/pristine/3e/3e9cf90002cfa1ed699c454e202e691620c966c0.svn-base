package nc.bs.pu.m27.settlebill.rule;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.CirVOUtil;

/**
 * 
 * @description
 * 红蓝入库单对冲的结算单行取消结算时，同时需要取消回冲的暂估应付单
 * @scene
 * 删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:06:38
 * @author zhangshqb
 */
public class UnBackWashEstAPForRBStockRush implements IRule<SettleBillVO> {
  @Override
  public void process(SettleBillVO[] vos) {
    boolean isIAEnabled = SysInitGroupQuery.isAPEnabled();
    if (!isIAEnabled) {
      return;
    }
    for (SettleBillVO vo : vos) {
      this.unbackwash(vo);
    }
  }

  private void unbackwash(SettleBillVO vo) {
    Set<String> clbh =
        CirVOUtil.getDistinctFieldSet(vo.getChildrenVO(),
            SettleBillItemVO.PK_SETTLEBILL_B);
    // 取消回冲暂估应付
    ArapServicesForPUUtil
        .unWashEstPayable(clbh.toArray(new String[clbh.size()]));
  }
}

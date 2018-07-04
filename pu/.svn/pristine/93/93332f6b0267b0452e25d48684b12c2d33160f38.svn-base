/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 下午02:41:33
 */
package nc.bs.pu.m4201.rule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.arap.ArapServicesUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.util.CirVOUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动取消确认和暂估应付
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 下午02:41:33
 */
public class CancelConfirmTOAPRule extends CancelConfirmTOFIRule {

  @Override
  protected void cancelConfirm(PurchaseinFIItemVO[] items) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    Set<String> ids =
        CirVOUtil.getDistinctFieldSet(items, PurchaseinFIItemVO.PK_STOCKPS);
    // 调应付的接口删除确认应付单
    ArapServicesUtil
        .delPayableBillByPurchsIn(ids.toArray(new String[ids.size()]));
  }

  @Override
  protected void cancelEst(PurchaseinFIItemVO[] items) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    Map<String, String> bidApOrgMap = new HashMap<String, String>();
    for (PurchaseinFIItemVO item : items) {
      bidApOrgMap.put(item.getPk_stockps_b(), item.getPk_apfinanceorg());
    }
    // 调应付的接口取消暂估应付单
    ArapServicesForPUUtil.unEstPayable(bidApOrgMap);
  }

  @Override
  protected int getFIModule() {
    return this.AP;
  }

}

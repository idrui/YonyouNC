/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 ����02:41:33
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ�ȡ��ȷ�Ϻ��ݹ�Ӧ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 ����02:41:33
 */
public class CancelConfirmTOAPRule extends CancelConfirmTOFIRule {

  @Override
  protected void cancelConfirm(PurchaseinFIItemVO[] items) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    Set<String> ids =
        CirVOUtil.getDistinctFieldSet(items, PurchaseinFIItemVO.PK_STOCKPS);
    // ��Ӧ���Ľӿ�ɾ��ȷ��Ӧ����
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
    // ��Ӧ���Ľӿ�ȡ���ݹ�Ӧ����
    ArapServicesForPUUtil.unEstPayable(bidApOrgMap);
  }

  @Override
  protected int getFIModule() {
    return this.AP;
  }

}

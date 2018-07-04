/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午09:34:53
 */
package nc.impl.pu.m4t.action.rule.unapprove;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *取消暂估应付：如果暂估过应付就调用收付服务取消暂估应付
 * @scene
 * 期初暂估单取消审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午09:34:53
 * @author wuxla
 */
public class CancelSendAPRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, String> apPara = this.getCancelAPPara(vos);
    if (!apPara.isEmpty()) {
      this.cancelEstAP(apPara);
    }

  }
  /*
   * 取消暂估应付
   */
  private void cancelEstAP(Map<String, String> apPara) {
    ArapServicesForPUUtil.unEstPayable(apPara);
  }
  private Map<String, String> getCancelAPPara(InitialEstVO[] vos) {
    Map<String, String> paraMap = new HashMap<String, String>();
    for (InitialEstVO vo : vos) {
      InitialEstItemVO[] items = vo.getItems();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (InitialEstItemVO item : items) {
        if (null == item) {
          continue;
        }
        UFBoolean bap = item.getBestimateap();
        if (UFBoolean.TRUE.equals(bap)) {
          paraMap.put(item.getPk_initialest_b(), item.getPk_apfinanceorg());
          item.setBestimateap(UFBoolean.FALSE);
          item.setStatus(VOStatus.UPDATED);
        }
      }
    }
    return paraMap;
  }

}

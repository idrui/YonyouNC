/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午12:05:17
 */
package nc.bs.pu.est.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 取消费用暂估应付
 * @scene
 * 取消暂估-BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:48:14
 * @author zhangshqb
 */
public class FeeUnEstAPRule implements IRule<EstVO> {

  @Override
  public void process(EstVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    Map<String, String> map = this.getBIDApOrgMap(vos);
    if (MapUtils.isEmpty(map)) {
      return;
    }
    ArapServicesForPUUtil.unEstPayable(map);
  }

  /** 组织取消传应付的数据 **/
  private Map<String, String> getBIDApOrgMap(EstVO[] vos) {
    Map<String, String> bidApOrgMap = new HashMap<String, String>();
    for (EstVO vo : vos) {
      GoodsEstVO head = vo.getParentVO();
      FeeEstVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (int i = 0; i < items.length; i++) {
        // 未暂估应付
        UFBoolean estflag = items[i].getBtoap();
        if (!ValueUtils.getBoolean(estflag)) {
          continue;
        }
        String pk_apfiorg = head.getPk_apfinanceorg();
        String bid = items[i].getPk_stockps_fee();
        bidApOrgMap.put(bid, pk_apfiorg);
      }
    }
    return bidApOrgMap;
  }

}

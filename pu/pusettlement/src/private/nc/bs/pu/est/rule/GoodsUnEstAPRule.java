/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 上午11:26:26
 */
package nc.bs.pu.est.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @description
 * 取消货物暂估应付
 * @scene
 * 取消暂估-BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:51:00
 * @author zhangshqb
 */
public class GoodsUnEstAPRule implements IRule<EstVO> {
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
      // 未暂估成本
      if (!EnumToAPFlag.EstimateToAP.value().equals(head.getFtoapflag())) {
        continue;
      }
      // 已经结算过
      UFDouble estSttlNum = head.getNaccestcoststtlnum();
      if (!UFDouble.ZERO_DBL.equals(MathTool.nvl(estSttlNum))) {
        continue;
      }
      String pk_apfiorg = head.getPk_apfinanceorg();
      String bid = head.getPk_stockps_b();
      bidApOrgMap.put(bid, pk_apfiorg);
    }
    return bidApOrgMap;
  }

}

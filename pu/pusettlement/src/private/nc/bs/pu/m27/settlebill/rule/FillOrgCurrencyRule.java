package nc.bs.pu.m27.settlebill.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.org.OrgVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillVO;

/**
 * 
 * @description
 * 补充财务组织的本位币信息
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:13:08
 * @author zhangshqb
 */
public class FillOrgCurrencyRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    Map<String, String> map = this.getCurrencyType(vos);
    for (SettleBillVO vo : vos) {
      SettleBillHeaderVO header = vo.getParentVO();
      header.setCcurrencyid(map.get(header.getPk_org()));
    }
  }

  private Map<String, String> getCurrencyType(SettleBillVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    OrgVO[] orgs = this.getOrgs(vos);
    if (orgs != null) {
      for (OrgVO org : orgs) {
        map.put(org.getPk_org(), org.getPk_currtype());
      }
    }
    return map;
  }

  private OrgVO[] getOrgs(SettleBillVO[] vos) {
    Set<String> pks = new HashSet<String>();
    for (SettleBillVO vo : vos) {
      pks.add(vo.getParentVO().getPk_org());
    }
    OrgVO[] orgs =
        OrgUnitPubService.getOrgs(pks.toArray(new String[pks.size()]));
    return orgs;
  }

}

package nc.vo.pu.m20.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * ȡ��֯�°汾
 * 
 * @since 6.0
 * @version 2011-8-24 ����11:42:38
 * @author liuchx
 */
public class SetOrgNewVersionRule {

  private Map<String, String> orgVidMap;

  public void process(PraybillVO[] vos) {
    // ����id
    Set<String> pkorgSet = new HashSet<String>();
    for (PraybillVO vo : vos) {
      pkorgSet.add(vo.getHVO().getPk_org());
    }

    this.initOrgVidMap(pkorgSet);

    for (PraybillVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      String pk_org_v = this.orgVidMap.get(pk_org);
      if (null != pk_org_v) {
        vo.getHVO().setPk_org_v(pk_org_v);
      }
    }
  }

  private void initOrgVidMap(Set<String> pkorgSet) {
    if (pkorgSet.size() == 0) {
      return;
    }
    String[] pk_orgs = new String[pkorgSet.size()];
    pkorgSet.toArray(pk_orgs);
    this.orgVidMap = OrgUnitPubService.getNewVIDSByOrgIDS(pk_orgs);
  }
}

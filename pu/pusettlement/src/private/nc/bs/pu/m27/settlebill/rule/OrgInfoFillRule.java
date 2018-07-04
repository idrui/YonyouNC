package nc.bs.pu.m27.settlebill.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.AggVOUtil;

/**
 * 
 * @description
 * ���������֯�İ汾��Ϣ,�����ʱ��ͳһ����
 * @scene
 * ���ý���,������ϱ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:12:50
 * @author zhangshqb
 */
public class OrgInfoFillRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    Map<String, String> map = this.getOrg_v(vos);
    for (SettleBillVO vo : vos) {
      SettleBillHeaderVO header = vo.getParentVO();
      String pk_org_v = map.get(header.getPk_org());
      header.setPk_org_v(pk_org_v);
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        item.setPk_org_v(pk_org_v);
      }
    }
  }

  private Map<String, String> getOrg_v(SettleBillVO[] vos) {
    String[] pk_orgs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            OrderHeaderVO.PK_ORG, String.class);

    return OrgUnitPubService.getNewVIDSByOrgIDS(pk_orgs);
  }

}

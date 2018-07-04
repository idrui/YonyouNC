/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����04:50:46
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Эͬʱ������֯Ĭ��ֵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����04:50:46
 */
public class CoopOrganizationValue implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      this.setPurchaseOrg(vo);
      this.setDefaultStoreOrganization(vo);
      // ���������֯��Эͬʱ�Ѿ�������ֵ��:���۶����ͻ��ڿͻ������еĶ�Ӧ������֯
      this.setDefaultApFinance(vo);
    }

  }

  /**
   * ��������������Ĭ�ϵ��ڽ��������֯
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 ����01:34:23
   */
  public void setDefaultApFinance(OrderVO vo) {
    for (OrderItemVO itemVO : vo.getBVO()) {
      itemVO.setPk_apfinanceorg(itemVO.getPk_psfinanceorg());
      itemVO.setPk_apfinanceorg_v(itemVO.getPk_psfinanceorg_v());
    }
  }

  private String getOrgVid(String pkorg) {
    return OrgUnitPubService.getOrgVid(pkorg);
  }

  /**
   * ���������������ջ������֯
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 ����12:09:11
   */
  private void setDefaultStoreOrganization(OrderVO vo) {
    boolean isStroc =
        OrgUnitPubService.isTypeOf(vo.getHVO().getPk_org(),
            IOrgConst.STOCKORGTYPE);

    OrderItemVO[] itemVOs = vo.getBVO();
    for (OrderItemVO itemVO : itemVOs) {
      if (!StringUtil.isEmptyWithTrim(itemVO.getPk_reqstoorg())) {
        continue;
      }
      if (isStroc) {
        itemVO.setPk_reqstoorg(vo.getHVO().getPk_org());
        itemVO.setPk_reqstoorg_v(vo.getHVO().getPk_org_v());
      }
    }

    for (OrderItemVO itemVO : itemVOs) {
      if (!StringUtil.isEmptyWithTrim(itemVO.getPk_arrvstoorg())) {
        continue;
      }
      itemVO.setPk_arrvstoorg(itemVO.getPk_reqstoorg());
      itemVO.setPk_arrvstoorg_v(itemVO.getPk_reqstoorg_v());
    }
  }

  /**
   * �����������������òɹ���֯VID�Լ������еĲɹ���֯
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 ����12:09:27
   */
  private void setPurchaseOrg(OrderVO vo) {
    String pk_org = vo.getHVO().getPk_org();
    String pk_org_v = this.getOrgVid(pk_org);
    vo.getHVO().setPk_org_v(pk_org_v);
    for (OrderItemVO itemVO : vo.getBVO()) {
      itemVO.setPk_org(pk_org);
      itemVO.setPk_org_v(pk_org_v);
    }
  }

}

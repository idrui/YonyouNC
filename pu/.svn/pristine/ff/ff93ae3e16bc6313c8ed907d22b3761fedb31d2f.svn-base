/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午04:50:46
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>协同时设置组织默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午04:50:46
 */
public class CoopOrganizationValue implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      this.setPurchaseOrg(vo);
      this.setDefaultStoreOrganization(vo);
      // 结算财务组织在协同时已经设置上值了:销售订单客户在客户档案中的对应财务组织
      this.setDefaultApFinance(vo);
    }

  }

  /**
   * 方法功能描述：默认等于结算财务组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 下午01:34:23
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
   * 方法功能描述：收货库存组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 下午12:09:11
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
   * 方法功能描述：设置采购组织VID以及表体行的采购组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-30 下午12:09:27
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

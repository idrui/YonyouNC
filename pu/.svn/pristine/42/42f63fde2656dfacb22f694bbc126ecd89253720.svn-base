package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.util.AuditInfoUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              价格结算单补全数据
 * @scene
 *        价格结算单新增保存、更新保存
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:23:06
 * @author luojw
 */
public class FillInsertPricestlVos implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      // 设置表头
      PricestlHeaderVO head = vo.getHVO();
      this.fillHead(head);

      String pk_org = head.getPk_org();
      String pk_org_v = head.getPk_org_v();
      String pk_group = head.getPk_group();

      // 设置子表
      PricestlItemVO[] itemvos = vo.getBVO();
      this.fillitems(itemvos, pk_org, pk_org_v, pk_group);

      // 设置子子表
      PricestlItemBVO[] itembvos = vo.getBBVO();
      this.fillBitems(itembvos, pk_org, pk_org_v, pk_group);
    }

    AuditInfoUtils.setAddAuditInfo(vos);
    ApproveFlowUtil.setBillMakeInfo(vos);
  }

  /**
   * 方法功能描述：补全子子表。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itembvos
   * @param pk_org
   * @param pk_org_v
   * @param pk_group <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 上午09:51:48
   */
  private void fillBitems(PricestlItemBVO[] itembvos, String pk_org,
      String pk_org_v, String pk_group) {
    if (ArrayUtils.isEmpty(itembvos)) {
      return;
    }

    for (PricestlItemBVO itembvo : itembvos) {
      if (null == itembvo.getPk_group()) {
        itembvo.setPk_group(pk_group);
      }

      if (null == itembvo.getPk_org()) {
        itembvo.setPk_org(pk_org);
      }

      if (null == itembvo.getPk_org_v()) {
        itembvo.setPk_org_v(pk_org_v);
      }
    }

  }

  private void fillHead(PricestlHeaderVO head) {
    // 补全单据状态
    if (null == head.getFbillstatus()) {
      head.setFbillstatus(POEnumBillStatus.FREE.toInteger());
    }
  }

  /**
   * 方法功能描述：补全子表。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemvos
   * @param pk_org
   * @param pk_org_v
   * @param pk_group <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 上午09:51:30
   */
  private void fillitems(PricestlItemVO[] itemvos, String pk_org,
      String pk_org_v, String pk_group) {
    if (ArrayUtils.isEmpty(itemvos)) {
      return;
    }

    for (int i = 0, len = itemvos.length; i < len; i++) {
      // 行号
      itemvos[i].setCrowno(String.valueOf((i + 1) * 10));

      if (null == itemvos[i].getPk_group()) {
        itemvos[i].setPk_group(pk_group);
      }

      if (null == itemvos[i].getPk_org()) {
        itemvos[i].setPk_org(pk_org);
      }

      if (null == itemvos[i].getPk_org_v()) {
        itemvos[i].setPk_org_v(pk_org_v);
      }

    }

  }
}

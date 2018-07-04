package nc.bs.pu.m27.update.v61;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m27.update.v61.rule.M27AllotNumMnyUpgradeRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.upgrade.scmpub.VOPageUpgrade;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ��������V61
 * 
 * @since 6.0
 * @version 2012-3-29 ����09:43:31
 * @author wuxla
 */
public class M27UpgradeFor61 {
  /**
   * ��������V61
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>��������V61
   * </ul>
   */
  public void doAfterUpdateDataFrom60To61() {
    this.upgradeAllotNumMny();
  }

  private void upgradeAllotNumMny() {
    VOPageUpgrade<SettleFeeAllotDetailVO> upgrade =
        new VOPageUpgrade<SettleFeeAllotDetailVO>(SettleFeeAllotDetailVO.class);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + SettleFeeAllotDetailVO.DR, 0);
    List<IRule<SettleFeeAllotDetailVO>> rules =
        new ArrayList<IRule<SettleFeeAllotDetailVO>>();
    rules.add(new M27AllotNumMnyUpgradeRule());
    upgrade.doUpgrade(sql.toString(), rules);
  }
}

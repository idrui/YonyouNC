package nc.bs.pu.m23.upgrade.v63;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m23.upgrade.v63.rule.FtaxtypeflagUpgradeRule;
import nc.bs.pu.m23.upgrade.v63.rule.NtaxUpgradeRule;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.upgrade.scmpub.BillPageUpgrade;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.rule.upgrade.VOStatusUpdateRule;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 到货单v63升级类
 * 
 * @since 6.3
 * @version 2013-1-10 上午10:07:11
 * @author lixyp
 */
public class M23UpgradeFor63 {

  public void process() {
    this.processForEC();
    this.processByBillPage();
  }

  /**
   * 通过BillPageUpgrade机制升级。包括：
   * 1. (原币)无税金额、(本币)税额、本币无税金额升级。
   */
  private void processByBillPage() {
    BillPageUpgrade<ArriveVO> billUpgrade =
        new BillPageUpgrade<ArriveVO>(ArriveVO.class);

    List<IRule<ArriveVO>> rules = new ArrayList<IRule<ArriveVO>>();
    // 到货单表体新增字段"扣税类别"的更新规则 modified by fanly3 at 2013-04-10
    rules.add(new FtaxtypeflagUpgradeRule());
    // 税额更新规则
    rules.add(new NtaxUpgradeRule());
    // 单据vo状态更新
    rules.add(new VOStatusUpdateRule<ArriveVO>());

    billUpgrade.doUpgrade(null, rules);
  }

  /**
   * 升级电商相关字段。
   */
  private void processForEC() {
    String po_arriveorder = DBHintConstantValue.getHintCode("po_arriveorder");
    // 发布设为N；发布人、响应人设为~
    new DataAccessUtils().update("update " + po_arriveorder
        + " po_arriveorder set bpublish = 'N' where isnull(bpublish,'~')='~' ");

    new DataAccessUtils()
        .update("update "
            + po_arriveorder
            + " po_arriveorder set pk_pubpsn = '~' where isnull(pk_pubpsn,'~')='~' ");

    new DataAccessUtils()
        .update("update "
            + po_arriveorder
            + " po_arriveorder set pk_resppsn = '~' where isnull(pk_resppsn,'~')='~'");
  }
}

package nc.vo.pu.upgrade.v636;

import nc.pub.cmp.apply.update.CmpApplyUpdateV65;
import nc.scmmm.upgrade.scmpub.ISCMUpgradePath;
import nc.scmmm.upgrade.scmpub.v65.V65UpgradePath;
import nc.scmmm.upgrade.scmpub.version.ISCMUpdateAccount;
import nc.vo.pub.BusinessException;

public class PUUpgradeV631TOV636 implements ISCMUpdateAccount {

  @Override
  public void doAfterUpdateData() throws BusinessException {
    // 该方法将在账套升级最后调用
    PUUpgradeTOV636Util upTo636Util = new PUUpgradeTOV636Util();

    upTo636Util.upgradeForPayment();
    upTo636Util.upgradeForPrayarrange();
    upTo636Util.upgradeStorereqDefaultValue();
    upTo636Util.updateArrliabcenter_ReqNew();
    upTo636Util.upArrivalColumnData();
    upTo636Util.upInvoiceColumnData();
    upTo636Util.deleteVOChange_bDate();
    upTo636Util.updateOrderInvoice_ReqNew();
    upTo636Util.updateSettleBill_ReqNew();
    upTo636Util.upInitialestColumnData();
    upTo636Util.upSubcontinfiColumnData();
    CmpApplyUpdateV65.getInstance().noticeApplyUpdate("4004");
  }

  @Override
  public void doBeforeUpdateData() throws BusinessException {
  }

  @Override
  public void doBeforeUpdateDB() throws BusinessException {
  }

  @Override
  public ISCMUpgradePath getUpgradePath() {
    return V65UpgradePath.V631TO65;
  }

}

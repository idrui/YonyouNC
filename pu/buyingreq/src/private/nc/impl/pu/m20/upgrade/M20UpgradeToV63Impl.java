package nc.impl.pu.m20.upgrade;

import nc.bs.pu.m20.upgrade.v63.M20UpgradeFor63;
import nc.bs.pub.pa.AlertRegistryUpgrader;
import nc.itf.pu.m20.upgrade.IM20UpgradeToV63;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 请购单升级实现类
 * 
 * @since 6.3
 * @version 2012-10-18 上午09:49:59
 * @author fanly3
 */
public class M20UpgradeToV63Impl implements IM20UpgradeToV63 {

  @Override
  public void doAfterUpdateDataFrom61To63() throws BusinessException {
    try {
      new M20UpgradeFor63().doAfterUpdateDataFrom61To63();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void doBeforeUpdateDataFrom61To63() throws BusinessException {
    AlertRegistryUpgrader upgrader = new AlertRegistryUpgrader("pu");
    // nc.bs.pu.m20.alert.PrayNoOrderAlert
    upgrader.putAlertType("1002Z81000000000W8HU", "pk_org");
    // nc.bs.pu.m20.alert.PrayAudittedNotCreateOrderAlart
    upgrader.putAlertType("1002Z810000000014YJR", "pk_stockorg");
    upgrader.doUpgrade();
  }

  @Override
  public void doBeforeUpdateDBFrom61To63() throws BusinessException {
  }

}

package nc.vo.pu.upgrade.v63;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.bs.upgrade.AbstractSCMUpgrade;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.upgrade.IPUUpgradeToV63;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购管理v61->v63升级处理类
 * 
 * @since 6.3
 * @version 2012-12-20 上午09:06:52
 * @author lixyp
 */
public class PUUpgradeToV63 extends AbstractSCMUpgrade {

  private Map<String, String> billtypeUpgradeMap =
      new HashMap<String, String>();

  public PUUpgradeToV63() {
    // v63请购单升级
    this.billtypeUpgradeMap.put(POBillType.PrayBill.getCode(),
        "nc.itf.pu.m20.upgrade.IM20UpgradeToV63");
    // v63采购订单升级
    this.billtypeUpgradeMap.put(POBillType.Order.getCode(),
        "nc.itf.pu.m21.upgrade.IM21UpgradeToV63");
    // v63到货单升级
    this.billtypeUpgradeMap.put(POBillType.Arrive.getCode(),
        "nc.itf.pu.m23.upgrade.IM23UpgradeToV63");
  }

  @Override
  public void doBeforeUpdateDBFrom61To63() throws BusinessException {
    try {
      // 更新pub_systemplate_base表中layer字段 由于61数据库中脚本是存在问题，没有将layer字段的值变成0
      this.upSystemplateLayer();

      for (Entry<String, String> entry : this.billtypeUpgradeMap.entrySet()) {
        String key = entry.getKey();
        String message = "upgrade[" + key + "] start.";/* -=notranslate=- */
        Log.debug(message);

        // 升级
        String value = entry.getValue();
        IPUUpgradeToV63 service =
            (IPUUpgradeToV63) NCLocator.getInstance().lookup(value);
        service.doBeforeUpdateDBFrom61To63();

        message = "upgrade[" + key + "] end.";/* -=notranslate=- */
        Log.debug(message);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 更新pub_systemplate_base表中layer字段 由于61数据库中脚本是存在问题，没有将layer字段的值变成0
   */
  private void upSystemplateLayer() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update pub_systemplate_base set layer=0 ");
    sql.append(" where (funnode like '4004%' or funnode in ('40010515','40010520')) and (layer is null or layer <> 0)");
    DataAccessUtils util = new DataAccessUtils();
    util.update(sql.toString());

  }

  @Override
  protected void doAfterUpdateDataFrom61To63() throws BusinessException {
    try {
      // 客商地址升级。
      new PuAddressUpgrader().doUpgrade();

      for (Entry<String, String> entry : this.billtypeUpgradeMap.entrySet()) {
        String key = entry.getKey();
        String message = "upgrade[" + key + "] start.";/* -=notranslate=- */
        Log.debug(message);

        // 升级
        String value = entry.getValue();
        IPUUpgradeToV63 service =
            (IPUUpgradeToV63) NCLocator.getInstance().lookup(value);
        service.doAfterUpdateDataFrom61To63();

        message = "upgrade[" + key + "] end.";/* -=notranslate=- */
        Log.debug(message);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  protected void doBeforeUpdateDataFrom61To63() throws BusinessException {
    try {
      for (Entry<String, String> entry : this.billtypeUpgradeMap.entrySet()) {
        String key = entry.getKey();
        String message = "upgrade[" + key + "] start.";/* -=notranslate=- */
        Log.debug(message);

        // 升级
        String value = entry.getValue();
        IPUUpgradeToV63 service =
            (IPUUpgradeToV63) NCLocator.getInstance().lookup(value);
        service.doBeforeUpdateDataFrom61To63();

        message = "upgrade[" + key + "] end.";/* -=notranslate=- */
        Log.debug(message);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}

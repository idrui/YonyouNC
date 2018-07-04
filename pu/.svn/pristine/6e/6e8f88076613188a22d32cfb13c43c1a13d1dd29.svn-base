package nc.vo.pu.upgrade.v633;

import nc.bs.logging.Logger;
import nc.bs.sm.accountmanage.IUpdateAccount;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购管理v633升级处理类
 * 
 * @version 6.33
 * @since 6.33
 * @author mengjian
 * @time 2014-09-24 上午09:35:29
 */
public class PUUpgradeToV633 implements IUpdateAccount {

  // private static final String NC63 = "6.3";
  //
  // private static final String NC633 = "6.33";

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doAfterUpdateDataFrom63To633();
    // }
  }

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doBeforeUpdateDataFrom63To633();
    // }
  }

  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doBeforeUpdateDBFrom63To633();
    // }
  }

  private boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  private boolean isVersionEmpty(String oldVersion, String newVersion) {
    return this.isEmpty(oldVersion) || this.isEmpty(newVersion);
  }

  /**
   * 更新po_invoice表中新加字段 finvoicetype字段 为0
   */
  private void upInvoiceColumnData() {
    Logger.debug("采购发票发票归属字段升级开始。。。。。。");
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update po_invoice set finvoicetype=0 where finvoicetype is null ");
    DataAccessUtils util = new DataAccessUtils();
    util.update(sql.toString());
    Logger.debug("采购发票发票归属字段升级结束。。。。。。");

  }

  /**
   * 更新po_settlebill表中 新加字段fsettletype字段 为0 ,0表示为采购结算单
   */
  private void upSettleBillColumnData() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update po_settlebill set fsettletype=0 ");
    DataAccessUtils util = new DataAccessUtils();
    util.update(sql.toString());

  }

  /**
   * 在账套升级后调用
   * 
   * @throws BusinessException
   */
  protected void doAfterUpdateDataFrom63To633() throws BusinessException {
    // 如有需要，子类复写
    // 结算单头 结算归属类型 默认 设置为0 （采购结算）
    this.upSettleBillColumnData();

    this.upInvoiceColumnData();
  }

  /**
   * 进行数据升级之前调用
   * 
   * @throws BusinessException
   */
  protected void doBeforeUpdateDataFrom63To633() throws BusinessException {
    // 如有需要，子类复写
  }

  /**
   * 进行数据库结构升级之前调用
   * 
   * @throws BusinessException
   */
  protected void doBeforeUpdateDBFrom63To633() throws BusinessException {
    // 如有需要，子类复写

  }
}

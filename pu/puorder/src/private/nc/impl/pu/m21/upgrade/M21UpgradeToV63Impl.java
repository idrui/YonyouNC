package nc.impl.pu.m21.upgrade;

import nc.bs.pub.pa.AlertRegistryUpgrader;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m21.upgrade.IM21UpgradeToV63;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.EffDateTypeUpgrade;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;
import nc.vo.scmpub.res.DBHintConstantValue;

public class M21UpgradeToV63Impl implements IM21UpgradeToV63 {

  @Override
  public void doAfterUpdateDataFrom61To63() throws BusinessException {
    this.updateDiscount();
    this.updateFeffDateType();
    this.updateSplitItem();
  }

  @Override
  public void doBeforeUpdateDataFrom61To63() throws BusinessException {
    AlertRegistryUpgrader upgrader = new AlertRegistryUpgrader("pu");
    // nc.bs.pu.m21.alert.PoCloseBillAlert
    upgrader.putAlertType("1002Z81000000000W8HY", "pk_org");
    // nc.bs.pu.m21.alert.OrderArrAlert
    upgrader.putAlertType("1002Z810000000014YJJ", "pk_org");
    // nc.bs.pu.m21.alert.NoTradeVendAlert
    upgrader.putAlertType("1002Z810000000014YJN", "pk_org");
    // nc.bs.pu.m21.alert.PoRPNotArrvAlert
    upgrader.putAlertType("1002Z810000000014YJG", "pk_org");
    // nc.bs.pu.m21.alert.PayPlanMatureAlert
    upgrader.putAlertType("1003Z8100000000001FH", "pk_org");
    upgrader.doUpgrade();
  }

  @Override
  public void doBeforeUpdateDBFrom61To63() throws BusinessException {
  }

  /**
   * 升级折扣规则
   * 
   * @throws BusinessException
   */
  private void updateDiscount() throws BusinessException {
    try {
      String po_order_b = DBHintConstantValue.getHintCode("po_order_b");
      new DataAccessUtils()
          .update("update "
              + po_order_b
              + " po_order_b set pk_discount = '~' where isnull(pk_discount,'~')='~'");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 升级付款计划起算依据
   * 
   * @throws BusinessException
   */
  private void updateFeffDateType() throws BusinessException {
    try {
      new EffDateTypeUpgrade(PUEntity.M21_PAYPLAN_TABLE,
          AbstractPayPlanVO.FEFFDATETYPE).doUpgrade();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 升级分单规则。
   */
  private void updateSplitItem() {
    String pub_vosplititem = DBHintConstantValue.getHintCode("pub_vosplititem");

    DataAccessUtils utils = new DataAccessUtils();
    utils
        .update("update "
            + pub_vosplititem
            + " pub_vosplititem set item = 'mm_mo.temp_supplier' where pk_vosplititem = '1002Z810000000005IBE'");

    utils
        .update("update "
            + pub_vosplititem
            + " pub_vosplititem set item = 'mm_mo.temp_currencyid' where pk_vosplititem = '1002Z810000000005IBF'");

    utils
        .update("update "
            + pub_vosplititem
            + " pub_vosplititem set item = 'mm_mo.temp_puorg' where pk_vosplititem = '1002Z810000000005IBG'");

    String[] deletePks =
        new String[] {
          "1001Z810000000009PJM", "1001Z810000000009VPN",
          "1002Z81000000000CXA3", "1001Z81000000000I2H5",
          "1002Z81000000000CXA7", "1001Z810000000009RUQ",
          "1001Z810000000009R3N", "1002Z81000000000D0D5",
          "0001Z81000000000SRJW"
        };
    SqlBuilder builder = new SqlBuilder();
    builder.append(" delete from pub_vosplititem ");
    builder.append(" where pk_vosplititem ", deletePks);
    utils.update(builder.toString());
  }
}

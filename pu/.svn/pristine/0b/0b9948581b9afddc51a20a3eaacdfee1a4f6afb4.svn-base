package nc.bs.pu.m20.upgrade.v61;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 请购单客户，项目任务升级
 * 
 * @since 6.0
 * @version 2012-4-11 上午09:09:53
 * @author wuxla
 */
public class M20UpgradeFor61 {
  /**
   * 请购单客户，项目任务升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>V60到V61升级，请购单表体客户pk_customer更名为casscustid，cprojecttaskid项目任务设置为~
   * </ul>
   */
  public void doAfterUpdateDataFrom60To61() {
    String parallel_pray = DBHintConstantValue.getHintCode("po_praybill_b");
    new DataAccessUtils()
        .update("update "
            + parallel_pray
            + "  po_praybill_b  set  casscustid = pk_customer, cprojecttaskid='~' ");
  }
}

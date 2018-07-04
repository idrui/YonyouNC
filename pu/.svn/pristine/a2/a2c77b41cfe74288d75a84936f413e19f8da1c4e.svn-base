package nc.bs.pu.m20.upgrade.v63;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 请购单表体已生成总括订单升级
 * 
 * @since 6.3
 * @version 2012-10-17 上午09:44:49
 * @author fanly3
 */
public class M20UpgradeFor63 {
  /**
   * V61到V63升级, 请购单表体已生成总括订单设置为否
   */
  public void doAfterUpdateDataFrom61To63() {
    String parallel_pray = DBHintConstantValue.getHintCode("po_praybill_b");
    new DataAccessUtils()
        .update("update "
            + parallel_pray
            + " po_praybill_b set bisgensaorder = 'N' where isnull(bisgensaorder,'~')='~' ");
  }
}

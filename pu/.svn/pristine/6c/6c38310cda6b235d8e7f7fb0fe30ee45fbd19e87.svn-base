package nc.bs.pu.m422x.upgrade.v61;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 项目任务升级
 * 
 * @since 6.0
 * @version 2012-4-11 上午09:27:18
 * @author wuxla
 */
public class M422XUpgradeFor61 {
  public void doAfterUpdateDataFrom60To61() {
    String parallel_storereq = DBHintConstantValue.getHintCode("po_storereq_b");
    DataAccessUtils utils = new DataAccessUtils();
    utils.update("update " + parallel_storereq
        + " po_storereq_b  set  cprojecttaskid = cprjtaskid  ");
  }
}

package nc.bs.pu.m422x.upgrade.v65;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 物资需求申请单表头"物资需求申请类型"升级
 * 
 * SQL 语句如下：
 * UPDATE
 * po_storereq a
 * SET
 * a.ctrantypeid =
 * (
 * SELECT
 * b.pk_billtypeid
 * FROM
 * bd_billtype b
 * WHERE
 * b.pk_group = a.pk_group
 * AND b.pk_billtypecode='422X-01')
 * WHERE
 * a.dr=0;
 * 
 * @since 6.5
 * @version 2014-1-23 下午04:20:36
 * @author fanly3
 */
public class M422XUpgradeFor65 {
  public void doAfterUpdateDataFrom61To63() {
    String parallel_storeq = DBHintConstantValue.getHintCode("po_storereq");
    new DataAccessUtils()
        .update("update "
            + parallel_storeq
            + " po_storereq a set a.ctrantypeid =(select b.pk_billtypeid from bd_billtype b where b.pk_group = a.pk_group and b.pk_billtypecode='422X-01') where a.dr=0;");
  }
}

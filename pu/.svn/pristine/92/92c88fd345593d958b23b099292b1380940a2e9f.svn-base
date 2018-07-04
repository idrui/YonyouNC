package nc.impl.pu.m23.upgrade;

import nc.bs.pu.m23.upgrade.v63.M23UpgradeFor63;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m23.upgrade.IM23UpgradeToV63;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * 到货单v63升级实现类
 * 
 * @since 6.3
 * @version 2012-12-20 上午10:23:26
 * @author lixyp
 */
public class M23UpgradeToV63Impl implements IM23UpgradeToV63 {

  @Override
  public void doAfterUpdateDataFrom61To63() throws BusinessException {
    try {
      new M23UpgradeFor63().process();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void doBeforeUpdateDataFrom61To63() throws BusinessException {
    // 本版本修改了到货单单据模板T表和B表中table_code等字段，由于平台单据模板在升级时会以此为维度判断是否新增记录
    // 所以需要在平台调用升级前将旧数据先更新过来，否则平台的升级程序将会新增很多记录。

    String pub_billtemplet_t =
        DBHintConstantValue.getHintCode("pub_billtemplet_t");
    DataAccessUtils utils = new DataAccessUtils();
    // 由于名称有可能会变，所以只更新名称仍然是body，code是“表体”的数据。
    utils
        .update("update "
            + pub_billtemplet_t
            + " pub_billtemplet_t set tabname = '表体' "/* -=notranslate=- */
            + " where PK_BILLTEMPLET in ("
            + " select PK_BILLTEMPLET from PUB_BILLTEMPLET where pk_billtypecode = '40040800') "
            + " and tabname = 'body' and tabcode = '表体'");/* -=notranslate=- */
    // 之后再更新code。
    utils
        .update("update "
            + pub_billtemplet_t
            + " pub_billtemplet_t set tabcode = 'body' where PK_BILLTEMPLET in ("
            + " select PK_BILLTEMPLET from PUB_BILLTEMPLET where pk_billtypecode = '40040800') "
            + " and tabcode = '表体'");/* -=notranslate=- */

    // 升级pub_billtemplate_b表
    String pub_billtemplet_b =
        DBHintConstantValue.getHintCode("pub_billtemplet_b");
    utils
        .update("update "
            + pub_billtemplet_b
            + " pub_billtemplet_b set table_name = '表体' "/* -=notranslate=- */
            + " where pk_billtemplet in (select pk_billtemplet from "
            + " pub_billtemplet where pk_billtypecode = '40040800') and table_name = 'body' "
            + " and table_code = '表体'");/* -=notranslate=- */
    utils
        .update("update "
            + pub_billtemplet_b
            + " pub_billtemplet_b set table_code = 'body' where pk_billtemplet in ("
            + " select pk_billtemplet from pub_billtemplet where pk_billtypecode = '40040800') "
            + " and table_code = '表体'");/* -=notranslate=- */
  }

  @Override
  public void doBeforeUpdateDBFrom61To63() throws BusinessException {
  }

}

package nc.bs.pu.m20.upgrade.v63;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

/**
 * �빺������������������������
 * 
 * @since 6.3
 * @version 2012-10-17 ����09:44:49
 * @author fanly3
 */
public class M20UpgradeFor63 {
  /**
   * V61��V63����, �빺������������������������Ϊ��
   */
  public void doAfterUpdateDataFrom61To63() {
    String parallel_pray = DBHintConstantValue.getHintCode("po_praybill_b");
    new DataAccessUtils()
        .update("update "
            + parallel_pray
            + " po_praybill_b set bisgensaorder = 'N' where isnull(bisgensaorder,'~')='~' ");
  }
}

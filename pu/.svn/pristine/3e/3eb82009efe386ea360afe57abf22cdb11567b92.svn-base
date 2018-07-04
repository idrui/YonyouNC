package nc.bs.pu.m20.rewrite.ec;

import nc.impl.pubapp.pattern.data.vo.VOUpdateTS;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>电子商务回写请购单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-18 上午10:00:49
 */
public class ReWrite20ForECBP {

  /**
   * 方法功能描述：电子商务回写请购单是否发布到电子商务。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   *          请购单行
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 下午12:03:05
   */
  public void backWrite(String[] pks) {
    if (ArrayUtils.isEmpty(pks)) {
      return;
    }

    this.upDel(pks);

    this.upTs(pks);

  }

  private void upDel(String[] pks) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("UPDATE po_praybill_b set bpublishtoec = 'N'  WHERE ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_06.name());
    sql.append(builder.buildSQL("pk_praybill_b", pks));

    new DataAccessUtils().update(sql.toString());

  }

  private void upTs(String[] pks) {
    PraybillItemVO[] items = new PraybillItemVO[pks.length];

    for (int i = 0, len = pks.length; i < len; i++) {
      PraybillItemVO item = new PraybillItemVO();
      item.setPk_praybill_b(pks[i]);
      items[i] = item;
    }

    new VOUpdateTS<PraybillItemVO>().update(items);
  }
}

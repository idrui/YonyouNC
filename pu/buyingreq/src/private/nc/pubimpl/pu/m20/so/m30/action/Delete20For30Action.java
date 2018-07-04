/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 上午08:59:35
 */
package nc.pubimpl.pu.m20.so.m30.action;

import nc.bs.pu.m20.maintain.PraybillDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-1 上午08:59:35
 */
public class Delete20For30Action {
  /**
   * 方法功能描述：根据销售订单PK删除请购单
   * <p>
   * <b>参数说明</b>
   * 
   * @param sourcebids
   *          销售订单PK数组
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午09:00:10
   */
  public void deleteBills(String[] sourcebids) {
    if (ArrayUtils.isEmpty(sourcebids)) {
      return;
    }

    // 根据来源销售订单查询请购单
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(this.getPraybillKeysSql(sourcebids));
    String[] ids = rowset.toOneDimensionStringArray();
    PraybillVO[] delvos =
        new BillQuery<PraybillVO>(PraybillVO.class).query(ids);

    if (ArrayUtils.isEmpty(delvos)) {
      return;
    }

    // 删除请购单
    new PraybillDeleteBP().delete(delvos);

  }

  private String getPraybillKeysSql(String[] sourcebids) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct pk_praybill ");
    sql.append(" from po_praybill_b  where dr=0 and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_09.name());
    sql.append(builder.buildSQL(PraybillItemVO.CSOURCEID, sourcebids));

    return sql.toString();
  }
}

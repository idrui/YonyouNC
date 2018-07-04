package nc.bs.pu.m20.query;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 请购单为资产维修工单提供查询服务的查询BP
 * <p>
 * <b>本类 主要完成以下功能：</b>
 * <ul>
 * <li>根据资产维修工单表头ID查询下游请购单。
 * <li>根据资产维修工单表体ID数组查询下游请购单。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-21 下午04:40:04
 */
public class QueryFor4b36BP {

  /**
   * 方法功能描述：根据资产维修工单表头ID查询下游请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billid
   *          资产维修工单表头id
   * @return 请购单
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 下午03:54:18
   */
  public PraybillVO[] queryBillBySource(String[] billid) {
    if (ArrayUtils.isEmpty(billid)) {
      return null;
    }

    String sql = this.makeGetSqlByHeadId(billid);
    return this.getpraybillByUpSour(sql);

  }

  /**
   * 方法功能描述：根据资产维修工单表体ID数组查询下游请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemids
   *          资产维修工单表体id数组
   * @return 请购单
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 下午03:54:18
   */
  public PraybillVO[] queryBillBySourceBID(String[] itemids) {
    if (ArrayUtils.isEmpty(itemids)) {
      return null;
    }

    String sql = this.makeGetSqlByItemIds(itemids);
    return this.getpraybillByUpSour(sql);
  }

  /**
   * 方法功能描述：查询请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return 查询请购单。
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 上午11:29:27
   */
  private PraybillVO[] getpraybillByUpSour(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 查询表头PK和子表PK
    String[][] pks = utils.query(sql).toTwoDimensionStringArray();
    if ((null == pks) || (pks.length == 0)) {
      return null;
    }

    Set<String> headPks = new HashSet<String>();
    String[] itemPks = new String[pks.length];

    for (int i = 0; i < pks.length; i++) {
      headPks.add(pks[i][0]);
      itemPks[i] = pks[i][1];
    }

    PraybillHeaderVO[] headers =
        new VOQuery<PraybillHeaderVO>(PraybillHeaderVO.class).query(headPks
            .toArray(new String[headPks.size()]));

    PraybillItemVO[] items =
        new VOQuery<PraybillItemVO>(PraybillItemVO.class).query(itemPks);
    BillComposite<PraybillVO> bc =
        new BillComposite<PraybillVO>(PraybillVO.class);
    PraybillVO tempVO = new PraybillVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(PraybillItemVO.class), items);
    return bc.composite();
    // return (PraybillVO[]) AggVOUtil.createBillVO(headers, items,
    // PraybillVO.class);
  }

  private String makeGetSqlByHeadId(String[] billid) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct pk_praybill, pk_praybill_b ");
    sql.append(" from po_praybill_b");
    sql.append("  where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_10.name());
    sql.append(builder.buildSQL("csourceid", billid));
    sql.append(" and dr = 0");

    return sql.toString();
  }

  private String makeGetSqlByItemIds(String[] itemids) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct pk_praybill, pk_praybill_b ");
    sql.append(" from po_praybill_b");
    sql.append(" where  dr = 0  and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_05.name());
    sql.append(builder.buildSQL("csourcebid", itemids));

    return sql.toString();
  }
}

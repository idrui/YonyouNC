package nc.bs.pu.m422x.query;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 查询物资需求申请单for工单
 * @author zhangshqb
 *
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
  public StoreReqAppVO[] queryBillBySource(String[] billid) {
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
  public StoreReqAppVO[] queryBillBySourceBID(String[] itemids) {
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
  private StoreReqAppVO[] getpraybillByUpSour(String sql) {
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

    StoreReqAppHeaderVO[] headers =
        new VOQuery<StoreReqAppHeaderVO>(StoreReqAppHeaderVO.class).query(headPks
            .toArray(new String[headPks.size()]));

    StoreReqAppItemVO[] items =
        new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class).query(itemPks);
    BillComposite<StoreReqAppVO> bc =
        new BillComposite<StoreReqAppVO>(StoreReqAppVO.class);
    StoreReqAppVO tempVO = new StoreReqAppVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(StoreReqAppItemVO.class), items);
    return bc.composite();
  }

  private String makeGetSqlByHeadId(String[] billid) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct pk_storereq, pk_storereq_b ");
    sql.append(" from po_storereq_b");
    sql.append("  where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_422x_05.name());
    sql.append(builder.buildSQL("csourceid", billid));
    sql.append(" and dr = 0");

    return sql.toString();
  }

  private String makeGetSqlByItemIds(String[] itemids) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct pk_storereq, pk_storereq_b ");
    sql.append(" from po_storereq_b");
    sql.append(" where  dr = 0  and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_422x_06.name());
    sql.append(builder.buildSQL("csourcebid", itemids));

    return sql.toString();
  }

}

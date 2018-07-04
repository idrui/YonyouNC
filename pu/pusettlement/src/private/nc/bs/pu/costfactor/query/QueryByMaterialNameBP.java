/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-20 上午09:42:46
 */
package nc.bs.pu.costfactor.query;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.data.IRowSet;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>按用户定义的费用显示顺序查询成本要素
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-20 上午09:42:46
 */
public class QueryByMaterialNameBP {

  public CostfactorVO[] query(String whereSql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(this.getCostfactorKeysSql(whereSql));
    String[] ids = rowset.toOneDimensionStringArray();
    return new BillQuery<CostfactorVO>(CostfactorVO.class).query(ids);
  }

  private String getCostfactorKeysSql(String whereSql) {
    String sql;

    if (StringUtils.isNotEmpty(whereSql)) {
      sql = " select distinct po_costfactor.pk_costfactor " + whereSql;
      sql +=
          " and po_costfactor.pk_costfactor=po_costfactor_b.pk_costfactor and po_costfactor.bentercost='Y' and po_costfactor_b.bshow='Y' and po_costfactor.dr=0 and po_costfactor.pk_group='"
              + InvocationInfoProxy.getInstance().getGroupId() + "'";
    }
    else {
      sql =
          " select distinct po_costfactor.pk_costfactor from po_costfactor a,po_costfactor_b b where a.pk_costfactor=b.pk_costfactor and a.bentercost='Y' and b.bshow='Y' and a.dr=0 and b.dr=0 and a.pk_group='"
              + InvocationInfoProxy.getInstance().getGroupId() + "'";
    }

    return sql;
  }
}

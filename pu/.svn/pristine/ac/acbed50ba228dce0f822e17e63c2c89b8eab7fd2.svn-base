/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-28 下午04:55:34
 */
package nc.pubimpl.pu.m21.ct.mz2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.ct.mz2.IOrderQueryForZ2;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购合同接口实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-28 下午04:55:34
 */
public class OrderQueryForZ2Impl implements IOrderQueryForZ2 {
  @Override
  public MapList<String, String> getRelationOrderItem(String[] pkCts)
      throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select " + OrderItemVO.CCONTRACTID + ","
          + OrderItemVO.PK_ORDER_B);
      sql.append(" from " + PUEntity.M21_H_TABLE + " h inner join "
          + PUEntity.M21_B_TABLE + " b on ");
      sql.append("h." + OrderHeaderVO.PK_ORDER + "=b." + OrderItemVO.PK_ORDER);
      sql.append(" where ");
      sql.append("h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
      String name = " and b." + OrderItemVO.CCONTRACTID;
      sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_21_18.name())
          .buildSQL(name, pkCts));
      sql.append(" and h.dr", 0);
      sql.append(" and b.dr", 0);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      if (rowset.size() == 0) {
        return null;
      }
      MapList<String, String> ml = new MapList<String, String>();
      while (rowset.next()) {
        ml.put(rowset.getString(0), rowset.getString(1));
      }
      return ml;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public boolean isExistOrderFromCt(String pkCt) throws BusinessException {
    try {
      StringBuilder sql = new StringBuilder();
      sql.append("select count(*) from po_order_b where ");
      sql.append("ccontractid='" + pkCt + "'");
      sql.append(" and dr=0");
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      rowset.next();
      int count = rowset.getInt(0);
      if (count > 0) {
        return true;
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return false;
  }

  @Override
  public Map<String, UFBoolean> isExistOrderFromCt(String pkCt, String[] pk_orgs)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_19.name());
      String cond = builder.buildSQL(" and " + OrderItemVO.PK_ORG, pk_orgs);
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct pk_org from po_order_b where ");
      sql.append(OrderItemVO.CCONTRACTID, pkCt);
      sql.append(cond);
      sql.append(" and dr", 0);

      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      String[] values = rowset.toOneDimensionStringArray();

      Set<String> set = new HashSet<String>();
      if (!ArrayUtils.isEmpty(values)) {
        CollectionUtils.addAll(set, values);
      }
      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (String pk_org : pk_orgs) {
        if (set.contains(pk_org)) {
          map.put(pk_org, UFBoolean.TRUE);
        }
        else {
          map.put(pk_org, UFBoolean.FALSE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}

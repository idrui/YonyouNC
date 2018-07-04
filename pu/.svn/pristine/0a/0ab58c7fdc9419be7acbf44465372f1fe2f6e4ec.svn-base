package nc.pubimpl.pu.m21.arap.mf3;

import java.util.Arrays;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.arap.mf3.IOrderAPQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-7-4 ÏÂÎç03:35:37
 * @author wuxla
 */

public class OrderAPQueryImpl implements IOrderAPQuery {

  @Override
  public List<String> getPayPlanIDByOrderBID(List<String> bids)
      throws BusinessException {
    if (CollectionUtils.isEmpty(bids)) {
      return null;
    }

    try {
      String[] payplanids = bids.toArray(new String[bids.size()]);
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_17.name());
      String bidcond =
          builder.buildSQL(" and b." + OrderItemVO.PK_ORDER_B, payplanids);

      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct p." + PayPlanVO.PK_ORDER_PAYPLAN + " from "
          + PUEntity.M21_PAYPLAN_TABLE + " p, ");
      sql.append(PUEntity.M21_B_TABLE + " b ");
      sql.append(" where ");
      sql.append(" b." + OrderItemVO.PK_ORDER + " = p." + PayPlanVO.PK_ORDER);
      sql.append(bidcond);
      sql.append(" and b." + OrderItemVO.DR, 0);
      sql.append(" and p." + AbstractPayPlanVO.DR, 0);
      sql.append(" and b." + OrderItemVO.FISACTIVE, new int[] {
        EnumActive.ACTIVE.toInt(), EnumActive.CLOSE.toInt()
      });

      String[] orderbids =
          new DataAccessUtils().query(sql.toString())
              .toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(orderbids)) {
        return null;
      }
      return Arrays.asList(orderbids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}

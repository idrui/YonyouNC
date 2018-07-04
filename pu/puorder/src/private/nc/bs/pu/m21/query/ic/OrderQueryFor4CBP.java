package nc.bs.pu.m21.query.ic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.ic.rule.FilterAndCalcFor4CRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.so.M30SOServices;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-27 ÏÂÎç02:26:38
 * @author wuxla
 */

public class OrderQueryFor4CBP {

  public Map<String, OrderItemVO> queryCoopOrderVO(String[] sobids) {
    String[] pobids = null;
    Map<String, String> map = M30SOServices.queryCoop21Bids(sobids);
    if (map.size() > 0) {
      pobids = map.values().toArray(new String[map.size()]);
    }

    OrderVO[] vos = null;
    if (!ArrayUtils.isEmpty(pobids)) {
      vos = this.queryOrderVOCoopToSO(pobids);
    }
    else {
      vos = this.queryOrderVOCoopFromSO(sobids);
    }
    Map<String, OrderItemVO> sopoMap = new HashMap<String, OrderItemVO>();
    if (ArrayUtils.isEmpty(vos)) {
      return sopoMap;
    }

    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PUSH4C);
    this.addRule(processer);
    vos = processer.after(vos);
    if (ArrayUtils.isEmpty(vos)) {
      return sopoMap;
    }
    if (!ArrayUtils.isEmpty(pobids)) {
      BillIndex index = new BillIndex(vos);
      IVOMeta voMeta = vos[0].getMetaData().getVOMeta(OrderItemVO.class);
      for (Entry<String, String> entry : map.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        sopoMap.put(key, (OrderItemVO) index.get(voMeta, value));
      }
    }
    else {
      for (OrderVO vo : vos) {
        for (OrderItemVO itemVO : vo.getBVO()) {
          sopoMap.put(itemVO.getCsourcebid(), itemVO);
        }
      }
    }
    return sopoMap;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    processer.addAfterRule(new FilterAndCalcFor4CRule());
  }

  private OrderVO[] queryBySql(String sql) {
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rowSet = util.query(sql.toString());
    String[] bids = rowSet.toOneDimensionStringArray();
    ViewQuery<OrderViewVO> query =
        new ViewQuery<OrderViewVO>(OrderViewVO.class);
    OrderViewVO[] views = query.query(bids);
    return OrderViewVO.getOrderVO(views);
  }

  private OrderVO[] queryOrderVOCoopFromSO(String[] sobids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_04.name());
    String bidsql =
        builder.buildSQL(" and b." + OrderItemVO.CSOURCEBID, sobids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b." + OrderItemVO.PK_ORDER_B);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h," + PUEntity.M21_B_TABLE
        + " b");
    sql.append(" where h." + OrderHeaderVO.PK_ORDER + "=b."
        + OrderItemVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BSOCOOPTOME, UFBoolean.TRUE);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and abs(" + OrderItemVO.NNUM + ")>" + "abs(isnull("
        + OrderItemVO.NACCUMSTORENUM + ",0.0))");
    sql.append(bidsql);

    return this.queryBySql(sql.toString());
  }

  private OrderVO[] queryOrderVOCoopToSO(String[] pobids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_05.name());
    String bidsql =
        builder.buildSQL(" and b." + OrderItemVO.PK_ORDER_B, pobids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b." + OrderItemVO.PK_ORDER_B);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h," + PUEntity.M21_B_TABLE
        + " b");
    sql.append(" where h." + OrderHeaderVO.PK_ORDER + "=b."
        + OrderItemVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BCOOPTOSO, UFBoolean.TRUE);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and abs(" + OrderItemVO.NNUM + ")>" + "abs(isnull("
        + OrderItemVO.NACCUMSTORENUM + ",0.0))");
    sql.append(bidsql);

    return this.queryBySql(sql.toString());
  }
}

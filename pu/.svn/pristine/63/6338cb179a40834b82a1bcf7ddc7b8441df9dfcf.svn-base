package nc.bs.pu.report.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.MetaData;
import nc.vo.bd.cust.areaclass.AreaclassVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.marpuclass.MarPuClassVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.report.queryinfo.order.OrderPayExecRptQueryView;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.view.order.OrderPayExecViewVO;
import nc.vo.pu.report.view.order.RptMetaDataUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-9-16 ÏÂÎç04:16:15
 * @author wuxla
 */

public class PayExecRptBP {

  public DataSet getPayExecDataSet(SmartContext context) {
    ConditionVO[] conds =
        (ConditionVO[]) context.getAttribute(PUOrderQryInfoPara.PAYEXEC_COND);
    // int max = RptMaxRowUtil.getMaxRow(context);
    DataAccessUtils util = new DataAccessUtils();
    // util.setMaxRows(max);
    OrderPayExecRptQueryView view = new OrderPayExecRptQueryView(conds, context);
    String sql = view.getViewSqlName();
    String[][] tempValues = util.query(sql).toTwoDimensionStringArray();

    OrderPayExecViewVO[] views = this.getViewVOs(tempValues);
    DataSet dataset = this.getDataSet(views);
    return dataset;
  }

  public OrderPayExecViewVO[] queryPayExecVOsByHid(String[] hids) {
    String sql = this.getQuerySql(hids);
    String[][] tempValues =
        new DataAccessUtils().query(sql).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(tempValues)) {
      return null;
    }
    OrderPayExecViewVO[] views = this.getViewVOs(tempValues);
    return views;
  }


  private DataSet getDataSet(OrderPayExecViewVO[] views) {
    DataViewMeta viewmeta =
        (DataViewMeta) new OrderPayExecViewVO().getMetaData();
    MetaData meta = RptMetaDataUtil.getMetaData(viewmeta);
    String[] attrs = new OrderPayExecViewVO().getAttributeNames();
    DataSet ds = RptMetaDataUtil.getDataSet(meta, views, attrs);
    return ds;
  }

  private Map<String, UFDouble> getPayMny(String[] hid) {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(hid)) {
      return map;
    }

    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_04.name());
    String cond = builder.buildSQL("po_order." + OrderHeaderVO.PK_ORDER, hid);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select po_order." + OrderHeaderVO.PK_ORDER + ",");
    sql.append("sum(isnull(po_order_payplan.naccumpaymny,0)) npaymny ");
    sql.append(" from po_order po_order left join  ");
    sql.append(" po_order_payplan po_order_payplan ");
    sql.append(" on po_order.pk_order = po_order_payplan.pk_order ");
    sql.append(" where ");
    sql.append(cond);
    String pk_group = BSContext.getInstance().getGroupID();
    sql.append(" and po_order." + OrderHeaderVO.PK_GROUP, pk_group);
    sql.append(" and po_order." + OrderHeaderVO.DR, 0);
    sql.append(" and po_order_payplan." + AbstractPayPlanVO.DR, 0);
    sql.append(" group by po_order." + OrderHeaderVO.PK_ORDER);
    String[][] tempValues =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(tempValues)) {
      return map;
    }
    for (int i = 0; i < tempValues.length; ++i) {
      map.put(tempValues[i][0], new UFDouble(tempValues[i][1]));
    }
    return map;
  }

  private String getQuerySql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_05.name());
    String cond = builder.buildSQL("po_order." + OrderHeaderVO.PK_ORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select po_order." + OrderHeaderVO.PK_ORDER + ",");

    sql.append("po_order." + OrderHeaderVO.VBILLCODE + ",");
    sql.append("po_order." + OrderHeaderVO.VCOOPORDERCODE + ",");
    sql.append("po_order." + OrderHeaderVO.PK_SUPPLIER + ",");
    sql.append("sum(isnull(po_order_b.naccuminvoicemny,0)) ninvoicemny,");
    sql.append("sum(po_order_b.ntaxmny) nordermny,");
    sql.append("sum(isnull(po_order_b.naccuminvoicemny,0) ");
    sql.append("-isnull(po_order_b.nacccancelinvmny,0)) ninvoicebalance ");
    sql.append(" from po_order po_order inner join po_order_b po_order_b ");
    sql.append(" on po_order.pk_order = po_order_b.pk_order ");

    sql.append(" where ");
    sql.append(cond);
    String pk_group = BSContext.getInstance().getGroupID();
    sql.append(" and po_order." + OrderHeaderVO.PK_GROUP, pk_group);
    sql.append(" and po_order." + OrderHeaderVO.DR, 0);
    sql.append(" and po_order." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    int[] orderstatus = new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    };
    sql.append(" and po_order." + OrderHeaderVO.FORDERSTATUS, orderstatus);
    sql.append(" and po_order_b." + OrderItemVO.DR, 0);
    sql.append(" and po_order_b." + OrderItemVO.BLARGESS, UFBoolean.FALSE);
    sql.append(" group by po_order." + OrderHeaderVO.PK_ORDER + ",");

    sql.append("po_order." + OrderHeaderVO.VBILLCODE + ",");
    sql.append("po_order." + OrderHeaderVO.VCOOPORDERCODE + ",");
    sql.append("po_order." + OrderHeaderVO.PK_SUPPLIER);
    sql.append(" order by po_order.vbillcode ");
    return sql.toString();
  }

  private OrderPayExecViewVO[] getViewVOs(String[][] tempValues) {
    String[] hid = new String[tempValues.length];
    OrderPayExecViewVO[] views = new OrderPayExecViewVO[tempValues.length];
    for (int i = 0; i < tempValues.length; ++i) {
      hid[i] = tempValues[i][0];
      views[i] = new OrderPayExecViewVO();
      views[i].setPk_order(tempValues[i][0]);
      String vbillcode = tempValues[i][1];

      views[i].setVbillcode(vbillcode);
      String vcoopordercode = tempValues[i][2];
      views[i].setVcoopordercode(vcoopordercode);
      String pk_supplier = tempValues[i][3];
      views[i].setPk_supplier(pk_supplier);
      String ninvoicemny = tempValues[i][4];
      views[i].setNinvoicemny(new UFDouble(ninvoicemny));
      String nordermny = tempValues[i][5];
      views[i].setNordermny(new UFDouble(nordermny));
      String ninvoicebalance = tempValues[i][6];
      views[i].setNinvoicebalance(new UFDouble(ninvoicebalance));
      views[i].setNunverifymny(UFDouble.ZERO_DBL);
      views[i].setNorderbalance(new UFDouble(ninvoicebalance));
    }

    Map<String, UFDouble> payMap = this.getPayMny(hid);
    for (OrderPayExecViewVO view : views) {
      String pk_order = view.getPk_order();
      view.setNpaymny(payMap.get(pk_order));
    }

    Map<String, UFDouble> paymap =
        ArapServicesForPUUtil.queryLocalMoneyByOrderID(hid);
    if (paymap != null) {
      for (OrderPayExecViewVO view : views) {
        String pk_order = view.getPk_order();
        UFDouble balmny = MathTool.nvl(paymap.get(pk_order));
        view.setNunverifymny(balmny);
        UFDouble norderbalance =
            MathTool.sub(view.getNinvoicebalance(), balmny);
        view.setNorderbalance(norderbalance);
      }
    }

    this.setCcurrencyid(views);

    return views;
  }

  private void setCcurrencyid(OrderPayExecViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    List<String> list = new ArrayList<String>();
    for (OrderPayExecViewVO view : views) {
      list.add(view.getPk_order());
    }
    String[] hids = list.toArray(new String[list.size()]);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_06.name());

    String cond =
        builder.buildSQL("po_order_b." + OrderHeaderVO.PK_ORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct po_order_b." + OrderItemVO.PK_ORDER);
    sql.append(",po_order_b." + OrderItemVO.CCURRENCYID);
    sql.append(" from po_order_b po_order_b ");
    sql.append(" where ");
    sql.append(cond);
    sql.append(" and po_order_b." + OrderItemVO.DR, 0);
    String[][] tempValues =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    Map<String, String> map = new HashMap<String, String>();
    for (String[] tempValue : tempValues) {
      map.put(tempValue[0], tempValue[1]);
    }

    for (OrderPayExecViewVO view : views) {
      String pk_order = view.getPk_order();
      view.setCcurrencyid(map.get(pk_order));
    }
  }
}

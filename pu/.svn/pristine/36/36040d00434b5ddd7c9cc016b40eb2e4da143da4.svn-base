package nc.pubimpl.pu.m27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m27.ISettleBillQueryFor25;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算关系查询服务-为采购发票提供（采购发票传应付时调用）的实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-13 下午06:54:48
 */
public class SettleBillQueryFor25Impl implements ISettleBillQueryFor25 {
  @Override
  public String getQryNoOrderInvcBIDSql(String ordBIDWhr)
      throws BusinessException {
    try {
      return this.getQryNoOrderInvcSql(ordBIDWhr);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public List<SettleBillInfo> querySettleBills(String pk_stock_b)
      throws BusinessException {
    try {
      List<SettleBillInfo> info =
          this.queryGoodsInvoiceAndStockRush(pk_stock_b);
      return info;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public MapList<String, SettleBillInfo> querySettleBills(String[] pkInvoices)
      throws BusinessException {
    MapList<String, SettleBillInfo> ml = new MapList<String, SettleBillInfo>();
    try {
      if (pkInvoices == null || pkInvoices.length == 0) {
        return ml;
      }
      // 查询货物发票结算和红蓝发票对冲的结算关系
      this.queryGoodsInvoiceAndInvoiceRush(pkInvoices, ml);
      // 如果是费用结算，则只需要查询出第一次费用结算的行
      this.queryFee(pkInvoices, ml);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return ml;
  }

  private SettleBillInfo generateSettleBillInfo(SettleBillItemVO item) {
    SettleBillInfo info = new SettleBillInfo();
    info.setVstockbilltype(item.getVstockbilltype());
    info.setPk_stock(item.getPk_stock());
    info.setPk_stock_b(item.getPk_stock_b());
    info.setPk_rushstock(item.getPk_rushstock());
    info.setPk_rushstock_b(item.getPk_rushstock_b());
    info.setPk_stockorder(item.getPk_stockorder());
    info.setPk_stockorder_b(item.getPk_stockorder_b());
    info.setPk_invoice(item.getPk_invoice());
    info.setPk_invoice_b(item.getPk_invoice_b());
    info.setPk_rushinvoice(item.getPk_rushinvoice());
    info.setPk_rushinvoice_b(item.getPk_rushinvoice_b());
    info.setNsettlenum(item.getNsettlenum());
    info.setPk_settlebill_b(item.getPk_settlebill_b());
    info.setNoppconfirmapmny(item.getNoppoconfmapmny());
    info.setBwashest(item.getBwashest());
    return info;
  }

  private String getQryNoOrderInvcSql(String ordBIdInSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_INVOICE_B);
    sql.append(" from " + PUEntity.SettleBill_B_TAB);
    sql.append(" where dr=0");
    sql.append(" and ");
    sql.append(PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_STOCKORDER_B + " ");
    sql.append(ordBIdInSql);
    // 必须发票为自制的
    sql.appendIDIsNull(" and " + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_INVOICEORDER_B);
    sql.appendIDIsNotNull(" and " + PUEntity.SettleBill_B_TAB + "."
        + SettleBillItemVO.PK_INVOICE_B);
    return sql.toString();
  }

  private SettleBillItemVO[] query(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(sql);
    String[] ids = rs.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(ids)) {
      return new SettleBillItemVO[0];
    }
    Set<String> idSet = new HashSet<String>(Arrays.asList(ids));
    ids = idSet.toArray(new String[idSet.size()]);
    String[] fields = this.queryFields();
    VOQuery<SettleBillItemVO> query =
        new VOQuery<SettleBillItemVO>(SettleBillItemVO.class, fields);
    SettleBillItemVO[] items = query.query(ids);
    return items;
  }

  /**
   * 方法功能描述：查询费用结算的结算关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkInvoices 发票的ID数组
   * @param ml 用来存放查询结果的MapList
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午08:12:38
   */
  private void queryFee(String[] pkInvoices, MapList<String, SettleBillInfo> ml) {
    // 则只需要查询出第一次费用结算的行
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_10.name());
    SqlBuilder builder = new SqlBuilder();
    // 必须加distinct
    builder
        .append("select distinct i.pk_settlebill_b,a.pk_settlebill_b from po_settle_feeallot a ");
    builder
        .append(" inner join po_settlebill_b i on i.pk_settlebill_b = a.pk_oppofee_b ");
    builder.append(" where i.dr = 0 ");
    builder.append(" and a.dr=0");
    builder.append(" and a.bestfirstsettle", UFBoolean.TRUE);
    builder.append(" and i."
        + idbuilder.buildSQL(SettleBillItemVO.PK_INVOICE, pkInvoices));
    SettleBillItemVO[] items = this.queryForFee(builder.toString());
    for (SettleBillItemVO item : items) {
      SettleBillInfo info = this.generateSettleBillInfo(item);
      ml.put(info.getPk_invoice_b(), info);
    }
  }

  /**
   * @return
   */
  private String[] queryFields() {
    String[] fields =
        new String[] {
          SettleBillItemVO.VSTOCKBILLTYPE, SettleBillItemVO.PK_STOCK,
          SettleBillItemVO.PK_STOCK_B, SettleBillItemVO.PK_RUSHSTOCK,
          SettleBillItemVO.PK_RUSHSTOCK_B, SettleBillItemVO.PK_STOCKORDER,
          SettleBillItemVO.PK_STOCKORDER_B, SettleBillItemVO.PK_INVOICE,
          SettleBillItemVO.PK_INVOICE_B, SettleBillItemVO.PK_RUSHINVOICE,
          SettleBillItemVO.PK_RUSHINVOICE_B, SettleBillItemVO.NSETTLENUM,
          SettleBillItemVO.PK_SETTLEBILL_B, SettleBillItemVO.NOPPOCONFMAPMNY,
          SettleBillItemVO.BWASHEST
        };
    return fields;
  }

  private SettleBillItemVO[] queryForFee(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(sql);
    if (rs.size() == 0) {
      return new SettleBillItemVO[0];
    }
    List<String> ids = new ArrayList<String>();
    Set<String> stockStlIds = new HashSet<String>();
    MapList<String, String> feeStlStockStlMap = new MapList<String, String>();
    while (rs.next()) {
      // 要返回的费用结算单的费用发票行ID
      ids.add(rs.getString(0));
      // 费用结算单费用发票行与入库单所在结算单行的映射
      feeStlStockStlMap.put(rs.getString(0), rs.getString(1));
      // 入库单所在结算单行ID
      stockStlIds.add(rs.getString(1));
    }
    stockStlIds.addAll(ids);
    VOQuery<SettleBillItemVO> query =
        new VOQuery<SettleBillItemVO>(SettleBillItemVO.class,
            this.queryFields());
    SettleBillItemVO[] items =
        query.query(stockStlIds.toArray(new String[stockStlIds.size()]));
    Map<String, SettleBillItemVO> itemMap = CirVOUtil.createKeyVOMap(items);
    Map<String, Integer> feeStlStockStlIndexMap =
        new HashMap<String, Integer>();
    List<SettleBillItemVO> retItemList = new ArrayList<SettleBillItemVO>();
    for (String feeStlID : ids) {
      SettleBillItemVO item = itemMap.get(feeStlID);
      // 根据相关的入库单所在结算单行，生成多个费用发票结算单行
      List<String> stockStlList =
          feeStlStockStlMap.get(item.getPk_settlebill_b());
      Integer dealIndex = feeStlStockStlIndexMap.get(item.getPk_settlebill_b());
      if (null == dealIndex) {
        dealIndex = Integer.valueOf(0);
      }
      String stockStlID = stockStlList.get(dealIndex.intValue());
      dealIndex = Integer.valueOf(dealIndex.intValue() + 1);
      feeStlStockStlIndexMap.put(item.getPk_settlebill_b(), dealIndex);
      SettleBillItemVO stockItem = itemMap.get(stockStlID);
      // 替换入库单信息
      item.setVstockbilltype(stockItem.getVstockbilltype());
      item.setPk_stock_b(stockItem.getPk_stock_b());
      retItemList.add((SettleBillItemVO) item.clone());
    }
    return retItemList.toArray(new SettleBillItemVO[retItemList.size()]);
  }

  /**
   * 方法功能描述：查询货物发票结算和红蓝发票对冲的结算关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkInvoices 发票的行ID数组
   * @param ml 用来存放查询结果的MapList
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午06:42:00
   */
  private void queryGoodsInvoiceAndInvoiceRush(String[] pkInvoices,
      MapList<String, SettleBillInfo> ml) {
    // 1、过滤掉直运无入库单的结算行
    // 2、过滤掉异物料结算的发票行
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_11.name());
    SqlBuilder builder = new SqlBuilder();
    builder.append("select b.pk_settlebill_b from po_settlebill_b b ");
    builder.append(" where b.dr = 0 ");
    builder.append(" and b.frowtype", new int[] {
      ((Integer) EnumMatchRowType.StockInvoiceMatch.value()).intValue(),
      ((Integer) EnumMatchRowType.InvoiceRush.value()).intValue()
    });
    builder.append(" and (b."
        + idbuilder.buildSQL(SettleBillItemVO.PK_INVOICE, pkInvoices));
    builder.append(" or b."
        + idbuilder
            .buildAnotherSQL(SettleBillItemVO.PK_RUSHINVOICE, pkInvoices));
    builder.append(")");
    SettleBillItemVO[] items = this.query(builder.toString());
    for (SettleBillItemVO item : items) {
      SettleBillInfo info = this.generateSettleBillInfo(item);
      ml.put(info.getPk_invoice_b(), info);
    }
  }

  private List<SettleBillInfo> queryGoodsInvoiceAndStockRush(String pk_stock_b) {
    SqlBuilder builder = new SqlBuilder();
    builder.append("select b.pk_settlebill_b from po_settlebill_b b ");
    builder.append(" where b.dr = 0 ");
    builder.append(" and b.frowtype", new int[] {
      ((Integer) EnumMatchRowType.StockInvoiceMatch.value()).intValue(),
      ((Integer) EnumMatchRowType.StockRush.value()).intValue()
    });
    builder.append(" and (b.pk_stock_b", pk_stock_b);
    builder.append(" or b.pk_rushstock_b", pk_stock_b);
    builder.append(")");
    SettleBillItemVO[] items = this.query(builder.toString());
    List<SettleBillInfo> info = new ArrayList<SettleBillInfo>();
    for (SettleBillItemVO item : items) {
      info.add(this.generateSettleBillInfo(item));
    }
    return info;
  }
}

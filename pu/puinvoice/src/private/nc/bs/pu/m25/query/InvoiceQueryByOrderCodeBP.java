package nc.bs.pu.m25.query;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-7 下午03:38:33
 * @author wuxla
 */

public class InvoiceQueryByOrderCodeBP {
  /**
   * 根据订单号找发票表体id
   * 
   * @param codes
   * @return
   */
  public MapList<String, String> queryInvoiceBidByOrderCode(String[] codes) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_05.name());
    String codeCond =
        builder.buildSQL(" and " + InvoiceItemVO.VFIRSTCODE, codes);
    SqlBuilder sql = new SqlBuilder();
    sql.append(codeCond);
    sql.append(" and " + InvoiceItemVO.DR, 0);
    sql.append(" and " + InvoiceItemVO.CFIRSTTYPECODE,
        POBillType.Order.getCode());
    String pk_group = BSContext.getInstance().getGroupID();
    sql.append(" and " + InvoiceItemVO.PK_GROUP, pk_group);
    VOQuery<InvoiceItemVO> query =
        new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
          InvoiceItemVO.VFIRSTCODE, InvoiceItemVO.PK_INVOICE_B
        });
    InvoiceItemVO[] itemVOs = query.query(sql.toString(), null);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    MapList<String, String> mapList = new MapList<String, String>();
    for (InvoiceItemVO itemVO : itemVOs) {
      mapList.put(itemVO.getVfirstcode(), itemVO.getPk_invoice_b());
    }
    return mapList;
  }
  
  /**
   * 根据订单表体id找发票表体id
   * 
   * @param orderBids
   * @return
   */
  public MapList<String, String> queryInvoiceBidByOrderBid(String[] orderBids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_12.name());
    String codeCond =
        builder.buildSQL(" and " + InvoiceItemVO.PK_ORDER_B, orderBids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(codeCond);
    sql.append(" and " + InvoiceItemVO.DR, 0);
    String pk_group = BSContext.getInstance().getGroupID();
    sql.append(" and " + InvoiceItemVO.PK_GROUP, pk_group);
    VOQuery<InvoiceItemVO> query =
        new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
          InvoiceItemVO.PK_ORDER_B, InvoiceItemVO.PK_INVOICE_B
        });
    InvoiceItemVO[] itemVOs = query.query(sql.toString(), null);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    MapList<String, String> mapList = new MapList<String, String>();
    for (InvoiceItemVO itemVO : itemVOs) {
      mapList.put(itemVO.getPk_order_b(), itemVO.getPk_invoice_b());
    }
    return mapList;
  }
}

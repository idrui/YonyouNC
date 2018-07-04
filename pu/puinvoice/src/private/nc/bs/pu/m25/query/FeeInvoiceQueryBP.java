/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 ����11:26:44
 */
package nc.bs.pu.m25.query;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ���÷�ƱBP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-15 ����11:26:44
 */
public class FeeInvoiceQueryBP {
  public InvoiceVO[] query(String[] pids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct pk_invoice from po_invoice where dr=0 and ");
    String insql =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_03.name()).buildSQL(
            InvoiceHeaderVO.PK_PARENTINVOICE, pids);
    sql.append(insql);
    IRowSet rowset = new DataAccessUtils().query(sql.toString());
    String[] ids = rowset.toOneDimensionStringArray();
    return new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
  }

  public InvoiceVO[] queryFree(String[] pids) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct pk_invoice from po_invoice where dr=0 and ");
    String insql =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_04.name()).buildSQL(
            InvoiceHeaderVO.PK_PARENTINVOICE, pids);
    sql.append(insql);
    sql.append(" and ");
    sql.append(InvoiceHeaderVO.FBILLSTATUS, POEnumBillStatus.FREE.toInt());
    IRowSet rowset = new DataAccessUtils().query(sql.toString());
    String[] ids = rowset.toOneDimensionStringArray();
    return new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
  }
}

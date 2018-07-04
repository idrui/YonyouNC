package nc.pubimpl.pu.m25.ia.costcal;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubift.pu.m25.ia.costcal.IInvoiceQueryForIACostCal;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class InvoiceQueryForIACostCalImpl implements IInvoiceQueryForIACostCal {

  @Override
  public Map<String, UFDouble> queryMaxPrice(String pk_org,
      String[] pk_srcmaterials, UFDate beginDate, UFDate endDate)
      throws BusinessException {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      String sql =
          this.getQueryMaxPriceSql(pk_org, pk_srcmaterials, beginDate, endDate);
      String[][] values =
          new DataAccessUtils().query(sql).toTwoDimensionStringArray();
      if (values == null || values.length == 0) {
        return map;
      }
      for (String[] value : values) {
        map.put(value[0], new UFDouble(value[1]));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return map;
  }

  private String getQueryMaxPriceSql(String pk_org, String[] pk_srcmaterials,
      UFDate beginDate, UFDate endDate) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b." + InvoiceItemVO.PK_SRCMATERIAL);
    sql.append(",max(b." + InvoiceItemVO.NPRICE + ")");
    sql.append(" from po_invoice h ");
    sql.append("inner join po_invoice_b b on h.pk_invoice = b.pk_invoice ");
    sql.append("where ");
    sql.append("h." + InvoiceHeaderVO.PK_ORG, pk_org);
    sql.append(" and h." + InvoiceHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    if (beginDate != null) {
      sql.append(" and h." + InvoiceItemVO.DBILLDATE, ">=",
          beginDate.toString());
    }
    if (endDate != null) {
      sql.append(" and h." + InvoiceItemVO.DBILLDATE, "<=", endDate.toString());
    }
    sql.append(" and h.dr", 0);
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_13.name());
    sql.append(idbuilder.buildSQL(" and b." + InvoiceItemVO.PK_SRCMATERIAL,
        pk_srcmaterials));
    sql.append(" and b.dr", 0);
    sql.append(" group by b." + InvoiceItemVO.PK_SRCMATERIAL);
    return sql.toString();
  }

}

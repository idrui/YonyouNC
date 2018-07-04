package nc.pubimpl.pu.m4201.pu.m25;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m4201.pu.m25.IPurchaseinFIQueryFor25;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购入副本 提供给发票的查询服务实现
 * 
 * @since 6.0
 * @version 2011-4-8 下午06:34:17
 * @author 田锋涛
 */

public class PurchaseinFIQueryFor25Impl implements IPurchaseinFIQueryFor25 {
  @Override
  public Map<String, String> queryQualityMaterial(String[] itemIds)
      throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_4201_1.name());
      sql.append("select po_purchaseinfi_b.pk_stockps_b,");// bid
      sql.append("       po_purchaseinfi_b.pk_batchcode ");// 批次
      sql.append("  from po_purchaseinfi_b inner join bd_materialstock ");
      sql.append("    on po_purchaseinfi_b.pk_material");
      sql.append("       =bd_materialstock.pk_material");
      sql.append("   and po_purchaseinfi_b.pk_org");
      sql.append("       = bd_materialstock.pk_org ");// 库存组织
      sql.append(" where po_purchaseinfi_b.dr=0 ");
      sql.append("   and  ");
      sql.append(builder.buildSQL("po_purchaseinfi_b.pk_stockps_b", itemIds));// bid
      sql.append("   and isnull(bd_materialstock.wholemanaflag,'N')='Y' ");// 批次管理
      sql.append("   and isnull(bd_materialstock.chkfreeflag,'N')='N'");// 非免检

      DataAccessUtils util = new DataAccessUtils();
      IRowSet rowSet = util.query(sql.toString());
      Map<String, String> idMap = new HashMap<String, String>();
      while (rowSet.next()) {
        idMap.put(rowSet.getString(0), rowSet.getString(1));
      }
      return idMap;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}

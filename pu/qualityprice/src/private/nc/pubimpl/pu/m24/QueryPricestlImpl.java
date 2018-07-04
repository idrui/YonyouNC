/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-13 下午01:49:47
 */
package nc.pubimpl.pu.m24;

import nc.bs.pu.pub.PUIDQueryBuilder;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m24.IQueryPricestl;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.pu.m24.entity.PricParaVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单提供的查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-13 下午01:49:47
 */
public class QueryPricestlImpl implements IQueryPricestl {

  @Override
  public MapSet<String, String> queryOrderUsedByHid(String[] corderids)
      throws BusinessException {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_24_01.name());
    try {
      String cond =
          builder.buildSQL(
              PUEntity.M24_B_TABLE + "." + PricestlItemVO.CORDERID, corderids);

      SqlBuilder sql = new SqlBuilder();
      String m45_b = new PurchaseInBodyVO().getTableName();
      sql.append("select ");
      sql.append(PUEntity.M24_B_TABLE + "." + PricestlItemVO.CORDERID);
      sql.append("," + m45_b + "." + MetaNameConst.CFIRSTBILLBID);
      sql.append(" from " + PUEntity.M24_B_TABLE + " " + PUEntity.M24_B_TABLE);
      sql.append(" inner join " + m45_b + " " + m45_b);
      sql.append(" on " + m45_b + "." + MetaNameConst.CGENERALBID);
      sql.append(" = " + PUEntity.M24_B_TABLE + "." + PricestlItemVO.CSOURCEBID);
      sql.append(" where ");
      sql.append(cond);
      sql.append(" and " + PUEntity.M24_B_TABLE + "." + PricestlItemVO.DR, 0);

      DataAccessUtils util = new DataAccessUtils();
      String[][] values =
          util.query(sql.toString()).toTwoDimensionStringArray();
      if (null == values || 0 == values.length) {
        return null;
      }
      MapSet<String, String> mapset = new MapSet<String, String>();
      for (String[] value : values) {
        mapset.put(value[0], value[1]);
      }
      return mapset;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m24.IQueryPricestl#queryPricStlPrices(java.lang.String[])
   */
  @Override
  public PricParaVO[] queryPricStlPrices(String[] cgeneralbids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(cgeneralbids)) {
      return null;
    }

    try {
      return this.query(cgeneralbids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private PricParaVO[] query(String[] cgeneralbids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.csourcebid, b.ntaxprice, b.nprice ");
    sql.append(" from po_pricesettle h,po_pricesettle_b b ");
    sql.append(" where h.pk_pricesettle = b.pk_pricesettle  ");
    sql.append(" and h.dr=0 ");
    sql.append(" and b.dr=0 ");
    sql.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.value());
    PUIDQueryBuilder builder = new PUIDQueryBuilder();
    sql.append(builder.buildSQL(" and b.csourcebid", cgeneralbids));
    String[][] ret =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    if (ArrayUtils.isEmpty(ret)) {
      return null;
    }

    PricParaVO[] retVOs = new PricParaVO[ret.length];
    for (int i = 0, len = ret.length; i < len; i++) {
      retVOs[i] = new PricParaVO();
      retVOs[i].setCgeneralbid(ret[i][0]);
      retVOs[i].setNtaxprice(new UFDouble(ret[i][1]));
      retVOs[i].setNprice(new UFDouble(ret[i][2]));
    }
    return retVOs;

  }

}

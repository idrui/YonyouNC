package nc.pubimpl.pu.m20.mm.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20ToMM;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PrayExecVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-29 上午09:29:45
 */
public class QueryPrayAction {
  public PrayExecVO[] getPrayExecA(String pkStockorg, String pkMartial,
      UFDate reqdate) {
    ArrayList<PrayExecVO> list = new ArrayList<PrayExecVO>();

    DataAccessUtils utils = new DataAccessUtils();
    // 查询符合条件的数据
    IRowSet rs = utils.query(this.getSqlA(pkStockorg, pkMartial, reqdate));
    while (rs.next()) {
      PrayExecVO prayExecVO = this.getVO(rs);

      list.add(prayExecVO);
    }
    return list.toArray(new PrayExecVO[list.size()]);
  }

  public PrayExecVO[] getPrayExecB(String pk_stockorg,
      List<String> pk_martials, UFDate fromReqDates, UFDate endReqDates) {
    ArrayList<PrayExecVO> list = new ArrayList<PrayExecVO>();

    DataAccessUtils utils = new DataAccessUtils();
    // 查询符合条件的数据
    IRowSet rs =
        utils.query(this.getSqlB(pk_stockorg, pk_martials, fromReqDates,
            endReqDates));
    while (rs.next()) {
      PrayExecVO prayExecVO = this.getVO(rs);
      // 满足：累计订货数量为零 的单据数据返回
      if (prayExecVO.getCompletenum().doubleValue() == 0.0) {
        list.add(prayExecVO);
      }
    }
    return list.toArray(new PrayExecVO[list.size()]);
  }

  private String getOrder() {
    String sql =
        NCLocator.getInstance().lookup(IOrderQueryFor20ToMM.class)
            .getSqlFor23ToMM();
    return sql;
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkStockorg
   * @param pkMartial
   * @param reqdate
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-30 上午10:14:53
   */
  private String getSqlA(String pkStockorg, String pkMartial, UFDate reqdate) {
    SqlBuilder sql = new SqlBuilder();

    sql.append(" select A.pk_org, A.pk_praybill, A.vbillcode, ");
    sql.append(" B.pk_praybill_b,B.pk_material,B.dreqdate,B.cunitid,B.nnum,");
    sql.append(" C.nnum, B.pk_reqstor, B.pk_suggestsupplier,");
    sql.append(" B.pk_srcmaterial,B.cproductorid,B.cprojectid");

    sql.append(" from po_praybill A, po_praybill_b B");
    sql.append(",(" + this.getOrder() + ") C");
    sql.append(" where A.bislatest = 'Y' and A.dr = 0 and B.dr = 0 and A.pk_praybill = B.pk_praybill and B.pk_praybill_b = c.csourcebid");
    if (!StringUtil.isEmptyWithTrim(pkStockorg)) {
      sql.append(" and ");
      sql.append("A.pk_org", pkStockorg);
    }
    if (!StringUtil.isEmptyWithTrim(pkMartial)) {
      sql.append(" and ");
      sql.append("B.pk_material", pkMartial);
    }

    if (null != reqdate) {
      sql.append(" and B.dreqdate >= '" + reqdate.toString() + "'");
    }

    return sql.toString();
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkStockorg
   * @param pkMartial
   * @param reqdate
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-30 上午10:15:03
   */
  private String getSqlB(String pk_stockorg, List<String> pk_martials,
      UFDate fromReqDates, UFDate endReqDates) {
    SqlBuilder sql = new SqlBuilder();

    sql.append(" select A.pk_org, A.pk_praybill, A.vbillcode, ");
    sql.append(" B.pk_praybill_b,B.pk_material,B.dreqdate,B.cunitid,B.nnum,");
    sql.append(" B.naccumulatenum, B.pk_reqstor, B.pk_suggestsupplier,");
    sql.append(" B.pk_srcmaterial,B.cproductorid,B.cprojectid");

    sql.append(" from po_praybill A, po_praybill_b B");
    sql.append(" where A.bislatest = 'Y' and A.dr = 0 and B.dr = 0 and A.pk_praybill = B.pk_praybill");
    if (!StringUtil.isEmptyWithTrim(pk_stockorg)) {
      sql.append(" and ");
      sql.append("A.pk_org", pk_stockorg);
    }
    if (null != pk_martials && pk_martials.size() > 0) {
      sql.append(" and ");
      // 临时表
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_20_07.name());
      sql.append(builder.buildSQL("B.pk_material",
          pk_martials.toArray(new String[pk_martials.size()])));
    }
    if (null != fromReqDates) {
      sql.append(" and B.dreqdate >= '" + fromReqDates.toString() + "'");
    }

    if (null != endReqDates) {
      sql.append(" and B.dreqdate <= '" + endReqDates.toString() + "'");
    }

    return sql.toString();
  }

  private PrayExecVO getVO(IRowSet rs) {
    PrayExecVO prayExecVO = new PrayExecVO();
    String pk_org = rs.getString(0);
    prayExecVO.setPk_org(pk_org == null ? null : pk_org.trim());

    String pk_praybill = rs.getString(1);
    prayExecVO.setPk_praybill(pk_praybill == null ? null : pk_praybill.trim());

    String vbillcode = rs.getString(2);
    prayExecVO.setVbillcode(vbillcode == null ? null : vbillcode.trim());

    String pk_praybill_b = rs.getString(3);
    prayExecVO.setPk_praybill_b(pk_praybill_b == null ? null : pk_praybill_b
        .trim());

    String pk_material = rs.getString(4);
    prayExecVO.setPk_material(pk_material == null ? null : pk_material.trim());

    String dreqdate = rs.getString(5);
    prayExecVO.setDreqdate(dreqdate == null ? null : new UFDate(
        dreqdate.trim(), false));

    String cunitid = rs.getString(6);
    prayExecVO.setCunitid(cunitid == null ? null : cunitid.trim());

    Object nnum = rs.getObject(7);
    prayExecVO.setNnum(ObjectUtil.isEmptyWithTrim(nnum) ? UFDouble.ZERO_DBL
        : new UFDouble(nnum.toString().trim()));

    Object naccumulatenum = rs.getObject(8);
    prayExecVO
        .setCompletenum(ObjectUtil.isEmptyWithTrim(naccumulatenum) ? UFDouble.ZERO_DBL
            : new UFDouble(naccumulatenum.toString().trim()));

    String pk_reqstor = rs.getString(9);
    prayExecVO.setPk_reqstor(pk_reqstor == null ? null : pk_reqstor.trim());

    String pk_supplier = rs.getString(10);
    prayExecVO.setPk_supplier(pk_supplier == null ? null : pk_supplier.trim());

    String pk_srcmaterial = rs.getString(11);
    prayExecVO.setPk_srcmaterial(pk_srcmaterial == null ? null : pk_srcmaterial
        .trim());

    String cproductorid = rs.getString(12);
    prayExecVO.setCproductorid(cproductorid == null ? null : cproductorid
        .trim());

    String cprojectid = rs.getString(13);
    prayExecVO.setCprojectid(cprojectid == null ? null : cprojectid.trim());

    return prayExecVO;
  }
}

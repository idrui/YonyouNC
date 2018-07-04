package nc.pubimpl.pu.m21.ps.m36d1;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.pubitf.pu.m21.ps.m36d1.IBillIdQueryFor36D1;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款申请查询服务实现类
 * 
 * @since 6.0
 * @version 2011-3-22 下午08:48:12
 * @author wuxla
 */

public class BillIdQueryFor36D1Impl implements IBillIdQueryFor36D1 {

  @Override
  public String[] queryBillIdsFor36D1(String[] ids, String billtype)
      throws BusinessException {
    try {
      if (POBillType.Order.getCode().equals(billtype)) {
        return this.getBillIdsWhenOrder(ids);
      }
      else if (CTBillType.PurDaily.getCode().equals(billtype)) {
        return this.getBillIdsWhenCnt(ids);
      }
      else if (IArapBillTypeCons.F1.equals(billtype)) {
        return this.getBillIdsWhenAp(ids);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private String[] getBillIdsWhenAp(String[] ids) {
    String[] orderids = ArapServicesForPUUtil.getPayableTopId(ids);
    if (ArrayUtils.isEmpty(orderids)) {
      return null;
    }
    String[] cthids = this.getCtIDs(orderids);
    return ArrayUtil.combinArrays(orderids, cthids);
  }

  private String[] getBillIdsWhenCnt(String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct h." + OrderHeaderVO.PK_ORDER + " from ");
    sql.append(PUEntity.M21_H_TABLE + " h ," + PUEntity.M21_B_TABLE
        + " b where ");
    sql.append("h." + OrderHeaderVO.PK_ORDER + " = b." + OrderItemVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_45.name());
    sql.append(builder.buildSQL(" and b." + OrderItemVO.CCONTRACTID, ids));

    String[] orderhids =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(orderhids)) {
      return null;
    }

    String[] payableIds = this.getPayableHids(orderhids);

    String[] retIds = ArrayUtil.combinArrays(orderhids, payableIds);
    return retIds;
  }

  private String[] getBillIdsWhenOrder(String[] ids) {
    // String[] cthids = this.getCtIDs(ids);
    // 应付
    String[] payableIds = this.getPayableHids(ids);
    // String[] retIds = ArrayUtil.combinArrays(cthids, payableIds);
    return payableIds;
  }

  private String[] getCtIDs(String[] ids) {
    SqlBuilder ctSql = new SqlBuilder();
    ctSql.append("select distinct b." + OrderItemVO.CCONTRACTID + " from ");
    ctSql.append(PUEntity.M21_H_TABLE + " h ," + PUEntity.M21_B_TABLE
        + " b where ");
    ctSql
        .append("h." + OrderHeaderVO.PK_ORDER + " = b." + OrderItemVO.PK_ORDER);
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_46.name());
    ctSql.append(builder.buildSQL(" and h." + OrderHeaderVO.PK_ORDER, ids));
    ctSql.append(" and h." + OrderHeaderVO.DR, 0);
    ctSql.append(" and b." + OrderItemVO.DR, 0);
    ctSql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    ctSql.appendIDIsNotNull(" and " + OrderItemVO.CCONTRACTID);

    String[] cthids =
        new DataAccessUtils().query(ctSql.toString())
            .toOneDimensionStringArray();
    return cthids;
  }

  private String[] getPayableHids(String[] orderids) {
    String[] payableIds = ArapServicesForPUUtil.getPayableHids(orderids);
    return payableIds;
  }
}

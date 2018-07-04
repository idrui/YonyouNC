package nc.bs.pu.m20.query;

import nc.bs.pu.m20.query.logicutil.CondTOWhereUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 请购单为委外订单提供查询服务的查询BP
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
 * @time 2010-2-1 上午10:57:07
 */
public class QueryFor61BP extends AbstractRefQueryBP {

  public QueryFor61BP(QuerySchemeProcessor psor) {
    super(psor);
    // 增加接口定义的交易类型过滤条件-委外订单拉请购单
    this.psor.appendRefTrantypeWhere(POBillType.PrayBill.getCode(),
        SCBillType.Order.getCode(), PraybillHeaderVO.VTRANTYPECODE);
  }

  @Override
  public StringBuffer makeGetPKSql() {

    String sqlWhere = this.psor.getFinalFromWhere();
    String where = sqlWhere;
    if (null != sqlWhere && "1=1".equals(sqlWhere.trim())) {
      where = null;
    }

    StringBuffer sql = new StringBuffer();
    sql.append(" select distinct " + this.headtb + ".pk_praybill,");
    sql.append(this.itemtb + ".pk_praybill_b ");
    sql.append(where);
    sql.append(" and " + this.headtb + ".bsctype = 'Y'");
    sql.append(" and " + this.headtb + ".bislatest = 'Y'");
    sql.append(" and " + this.headtb + ".fbillstatus = "
        + POEnumBillStatus.APPROVE.value());
    sql.append(" and " + this.headtb + ".dr = 0");
    sql.append(" and " + this.headtb + ".pk_praybill = " + this.itemtb
        + ".pk_praybill");
    sql.append(" and " + this.itemtb + ".nnum > isnull(" + this.itemtb
        + ".naccumulatenum, 0)");
    sql.append(" and " + this.itemtb + ".nnum is not null");
    sql.append(" and " + this.itemtb + ".browclose = 'N'");
    sql.append(" and " + this.itemtb + ".bpublishtoec = 'N'");
    // 2012-10-17 fanly3 V63新需求 已生成总括订单的请购单行不允许生成下游单据
    sql.append(" and " + this.itemtb + ".bisgensaorder = 'N'");
    sql.append(" and " + this.itemtb + ".dr = 0");

    // 请购安排处理
    CondTOWhereUtil.buildIsarrange(sql, this.headtb, this.itemtb);

    // 按照采购岗过滤物料
    FilterForPosUtil.filterMaterialByPos(this.psor, sql);

    return sql;
  }

}

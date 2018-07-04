package nc.bs.pu.m27.settlebill;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单行信息的查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-28 下午03:52:40
 */
public class SettleBillItemQueryBP {

  /**
   * 方法功能描述：根据入库单/VMI的ID查询货物结算的结算单的货物行数组,暂估或确认成本前的结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_stock_bs 入库单的行ID数组
   * @return 货物行数据
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-28 下午04:10:49
   */
  public SettleBillItemVO[] queryBillItemFromGoodSettle(String[] pk_stock_bs) {
    // 构造查询sql字符串
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_05.name());
    SqlBuilder builder = new SqlBuilder();
    builder.append("select ");
    builder.append(SettleBillItemVO.PK_SETTLEBILL_B);
    builder.append(" from po_settlebill_b ");
    builder.append("where dr = 0 and ");
    builder.append(SettleBillItemVO.BWASHEST, UFBoolean.FALSE);
    builder.append(" and ");
    builder.appendNot(SettleBillItemVO.FROWTYPE, new int[] {
      ((Integer) EnumMatchRowType.StockFeeSettle.value()).intValue()
    });
    builder.append(" and "
        + idbuilder.buildSQL(SettleBillItemVO.PK_STOCK_B, pk_stock_bs));
    String sql = builder.toString();

    // 查询结算单行ID数组
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(sql);
    String[] ids = rs.toOneDimensionStringArray();

    // 查询结算单行
    if (ArrayUtils.isEmpty(ids)) {
      return null;
    }
    VOQuery<SettleBillItemVO> query =
        new VOQuery<SettleBillItemVO>(SettleBillItemVO.class);
    return query.query(ids);
  }

  /**
   * 方法功能描述：检查某些入库单的某些费用项是否进行过费用结算(提供给费用暂估时检查是否进行过费用结算所使用)
   * <p>
   * <b>参数说明</b>
   * 
   * @param stockbid 入库单行主键数组
   * @param pk_feemrls 费用项OID数组(与入库单行主键数组长度等长)
   * @return <p>
   *         有一项进行过费用结算即返回True;否则返回False
   * @since 6.0
   * @author hanbin
   * @time 2010-8-26 上午09:39:15
   */
  public UFBoolean queryFeeItemIsFeeSettled(String[] stockbids,
      String[] pk_feemrls) {
    if (ArrayUtils.isEmpty(stockbids) || ArrayUtils.isEmpty(pk_feemrls)) {
      return UFBoolean.FALSE;
    }
    if (stockbids.length != pk_feemrls.length) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0081")/* @res "查询费用项是否进行过结算时，参数入库单行主键与费用项参数数组必须等长！" */;

      ExceptionUtils.wrappBusinessException(msg);
      return UFBoolean.FALSE;
    }
    SqlBuilder sql = new SqlBuilder();
    if (stockbids.length == 1) {// 不使用临时表
      sql.append(" select count(bb.pk_settle_feeallot) from po_settlebill_b b");
      sql.append(" inner join po_settle_feeallot bb");
      sql.append(" on b.pk_settlebill_b = bb.pk_settlebill_b ");
      sql.append(" where b.dr=0 and bb.dr=0");
      sql.append(" and b.pk_stock_b", stockbids[0]);// 入库单行主键
      sql.append(" and bb.pk_srcmaterial", pk_feemrls[0]);// 费用项
    }
    else {// 创建临时表
      String tname = new TempTableDefine().get(stockbids, pk_feemrls);
      if (StringUtils.isEmpty(tname)) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
                "04004060-0067")/* @res "生成临时表失败" */;

        ExceptionUtils.wrappBusinessException(msg);
        return UFBoolean.FALSE;
      }
      sql.append(" select count(bb.pk_settle_feeallot) from po_settlebill_b b");
      sql.append(" inner join po_settle_feeallot bb");
      sql.append(" on b.pk_settlebill_b = bb.pk_settlebill_b ");
      sql.append(" inner join " + tname + " t");
      sql.append(" on b.pk_stock_b = t.id1");// 入库单行主键
      sql.append(" and bb.pk_srcmaterial = t.id2");// 费用项
      sql.append(" where b.dr=0 and bb.dr=0 ");
    }
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    if (rowset == null || rowset.size() == 0) {
      return UFBoolean.FALSE;
    }
    rowset.next();
    // 有一项进行过费用结算即返回True;否则返回False
    return UFBoolean.valueOf(rowset.getInt(0) > 0);
  }
}

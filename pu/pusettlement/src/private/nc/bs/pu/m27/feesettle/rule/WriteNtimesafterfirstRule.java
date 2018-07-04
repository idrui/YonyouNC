package nc.bs.pu.m27.feesettle.rule;

import java.util.List;

import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.feesettle.util.FeeSettleQueryPara;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 回写结算分摊明细的后续累计费用结算次数
 * @scene
 * 取消费用结算,费用结算
 * @param
 * bdosettle TRUE:费用结算,FALSE:取消结算
 *
 * @since 6.3
 * @version 2014-10-22 下午4:03:14
 * @author zhangshqb
 */
public class WriteNtimesafterfirstRule implements IRule<SettleBillVO> {

  // TRUE:费用结算,FALSE:取消结算
  private boolean bdosettle;

  public WriteNtimesafterfirstRule(boolean bdosettle) {
    this.bdosettle = bdosettle;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 抽取费用结算单
    List<SettleBillVO> feevos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // 从结算单中抽取劳务类、折扣类发票对应的物料VID
    FeeSettleQueryPara[] queryParas =
        FeeSettlePrivateUtil.buildFeeSettleQueryPara(feevos);
    if (ArrayUtils.isEmpty(queryParas)) {
      return;
    }
    // 构造查询结算分摊明细的临时表
    String tempTname = FeeSettlePrivateUtil.buldTempTable(queryParas);
    String[] stlHIds =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            SettleBillHeaderVO.PK_SETTLEBILL, String.class);
    // <入库单行主键，对应第一次费用结算明细列表>
    this.updateAfterfirsttimeFlag(tempTname, stlHIds);
  }

  private void updateAfterfirsttimeFlag(String tempTname, String[] stlHIds) {
    // 查询检验单的子表主键
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update  po_settle_feeallot ");
    sql.append(" set " + SettleFeeAllotDetailVO.NTIMESAFTERFIRST + "=");
    sql.append(SettleFeeAllotDetailVO.NTIMESAFTERFIRST);
    if (this.bdosettle) {
      sql.append("+1");
    }
    else {
      sql.append("-1");
    }
    sql.append(" where " + SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT + " in (");
    sql.append(" select bb.pk_settle_feeallot");
    sql.append(" from po_settlebill_b b inner join po_settle_feeallot bb");
    sql.append(" on b.pk_settlebill_b = bb.pk_settlebill_b");
    sql.append(" inner join " + tempTname + " t");
    sql.append(" on b.pk_stock_b = t.id1");
    sql.append(" and bb.pk_srcmaterial = t.id2");
    sql.append(" where b.dr = 0 and bb.dr =0");
    sql.append(" and bestfirstsettle", UFBoolean.TRUE);
    // 不包含本次的结算单
    String inName = " and b." + SettleBillItemVO.PK_SETTLEBILL;
    // 虽然这个规则是批量结算VO，但根据V60需求，费用结算只会生成一张结算单，这里不走临时表
    sql.appendNot(inName, stlHIds);
    sql.append(")");
    DataAccessUtils utils = new DataAccessUtils();
    utils.update(sql.toString());
  }

}

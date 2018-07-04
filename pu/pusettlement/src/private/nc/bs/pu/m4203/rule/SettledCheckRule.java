package nc.bs.pu.m4203.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 委托加工入取消签字时检查是否结算
 * 
 * @since 6.0
 * @version 2011-1-20 下午04:03:26
 * @author zhaoyha
 */
public class SettledCheckRule implements IRule<SubcontinFIVO> {

  @Override
  public void process(SubcontinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (SubcontinFIVO vo : vos) {
      for (SubcontinFIItemVO item : vo.getChildrenVO()) {
        UFDouble num = item.getNaccumsettlenum();
        UFDouble washmny = item.getNaccestcostwashmny();
        if (0 != MathTool.compareTo(num, UFDouble.ZERO_DBL)
            || 0 != MathTool.compareTo(washmny, UFDouble.ZERO_DBL)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004060_0", "04004060-0086")/*
                                                                       * @res
                                                                       * "委托加工入库单已经结算过，不能取消签字!"
                                                                       */);
        }
      }
    }
    // 检查是否进行过费用结算，因委托加工入可以直接进行费用结算，而采购入不可能
    this.checkFeeSettled(vos);
  }

  private void checkFeeSettled(SubcontinFIVO[] vos) {
    String[] hids =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            SubcontinFIHeaderVO.PK_STOCKPS, String.class);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(SettleBillItemVO.PK_SUBCONTRACT);
    sql.append(" from ");
    sql.append(PUEntity.SettleBill_B_TAB);
    sql.append(" where dr=0 and ");
    sql.append(SettleBillItemVO.FROWTYPE,
        EnumMatchRowType.StockFeeSettle.toInteger());
    sql.append(" and ");
    sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_4203_1.name()).buildSQL(
        SettleBillItemVO.PK_SUBCONTRACT, hids));
    if (new DataAccessUtils().query(sql.toString()).size() > 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0087")/*
                                                                   * @res
                                                                   * "委托加工入库单已经费用结算过，不能取消签字!"
                                                                   */);
    }

  }

}

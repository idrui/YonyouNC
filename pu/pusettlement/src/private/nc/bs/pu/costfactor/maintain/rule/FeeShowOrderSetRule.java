package nc.bs.pu.costfactor.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              设置费用项默认显示顺序（用于暂估带默认费用项）
 * @scene
 *        成本要素定义新增保存BP、成本要素定义修改保存BP
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:26:11
 * @author yanxm5
 */
public class FeeShowOrderSetRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] vos) {
    for (CostfactorVO vo : vos) {
      String[] bids = this.getShowItem(vo);
      if (ArrayUtils.isEmpty(bids)) {
        continue;
      }
      String pk_org = vo.getParentVO().getPk_org();
      // 查询库中已经有的当前组织下的成本要素费用项最大显示序号（不包括当前要素）
      int maxorder = this.getMaxFeeShowOrder(pk_org, bids);
      this.reSortShowOrder(maxorder, bids);// 重新排序显示序号，避免重复
    }

  }

  private int getMaxFeeShowOrder(String pk_org, String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select max(" + CostfactorItemVO.ISHOWORDER + ")");
    sql.append(" from " + PUEntity.COSTFACTOR_H_TAB + " inner join "
        + PUEntity.COSTFACTOR_B_TAB);
    sql.append(" on " + PUEntity.COSTFACTOR_H_TAB + "."
        + CostfactorHeaderVO.PK_COSTFACTOR);
    sql.append("=" + PUEntity.COSTFACTOR_B_TAB + "."
        + CostfactorItemVO.PK_COSTFACTOR);
    sql.append(" where " + PUEntity.COSTFACTOR_H_TAB + "."
        + CostfactorHeaderVO.PK_ORG, pk_org);
    sql.append(" and " + PUEntity.COSTFACTOR_H_TAB + "."
        + CostfactorHeaderVO.BENTERCOST, UFBoolean.TRUE);
    sql.append(" and " + PUEntity.COSTFACTOR_B_TAB + "."
        + CostfactorItemVO.BSHOW, UFBoolean.TRUE);
    sql.appendNot(" and " + PUEntity.COSTFACTOR_B_TAB + "."
        + CostfactorItemVO.PK_COSTFACTOR_B, bids);
    sql.append(" and " + PUEntity.COSTFACTOR_H_TAB + ".dr", 0);
    sql.append(" and " + PUEntity.COSTFACTOR_B_TAB + ".dr", 0);
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    int maxorder = 0;
    if (rs.size() > 0) {
      rs.next();
      if (null != rs.getInteger(0)) {
        maxorder = rs.getInteger(0).intValue();
      }
    }
    return maxorder;
  }

  private String[] getShowItem(CostfactorVO vo) {
    List<String> bidLst = new ArrayList<String>();
    if (!UFBoolean.TRUE.equals(vo.getParentVO().getBentercost())) {
      return null;// 不进成本，则不处理
    }
    if (!ArrayUtils.isEmpty(vo.getChildrenVO())) {
      for (CostfactorItemVO item : vo.getChildrenVO()) {
        if (!UFBoolean.TRUE.equals(item.getBshow())) {
          continue;
        }
        bidLst.add(item.getPk_costfactor_b());
      }
    }
    return bidLst.toArray(new String[bidLst.size()]);
  }

  private void reSortShowOrder(int maxorder, String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update ");
    sql.append("    po_costfactor_b ");
    sql.append("set ");
    sql.append("    ishoworder= ");
    sql.append("    ( ");
    sql.append("        select ");
    sql.append("           ishoworder " + "+" + maxorder);
    sql.append("        from ");
    sql.append("            ( ");
    sql.append("                select ");
    sql.append("                    pk_costfactor_b, ");
    sql.append("                    row_number() over(order by isnull(ishoworder,0)) ishoworder ");
    sql.append("                from po_costfactor_b b ");
    sql.append("                where ");
    sql.append("                b.dr=0 ");
    sql.append("                and b.pk_costfactor_b", bids);
    sql.append("            ) ");
    sql.append("            ordertab ");
    sql.append("        where ");
    sql.append("            ordertab.pk_costfactor_b=po_costfactor_b.pk_costfactor_b ");
    sql.append("    ) ");
    sql.append("where ");
    sql.append("    pk_costfactor_b ", bids);
    sql.append("and dr=0 ");
    new DataAccessUtils().update(sql.toString());
  }

}

package nc.bs.pu.costfactor.maintain.rule;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @description
 *              成本要素新增修改保存并发控制，用于控制一个财务组织，只允许一个同时操作
 * @scene
 *        成本要素定义新增保存BP
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:25:21
 * @author yanxm5
 */
public class ConcurrCtrlRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] vos) {
    this.check(vos);
  }

  private void check(CostfactorVO[] vos) {
    String[] pk_orgs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            CostfactorHeaderVO.PK_ORG, String.class);
    CostfactorVO[] existVos = this.queryCostfacotorIn(pk_orgs);
    for (CostfactorVO vo : vos) {
      String pk_org = vo.getParentVO().getPk_org();
      new LockOperator().lock(
          pk_org + CostfactorVO.class.getName(),
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0051")/* @res "不能多人同时维护同一财务组织的成本要素！" */);
      if (null == existVos || existVos.length == 0) {
        continue;
      }
      for (CostfactorVO existVo : existVos) {
        if (vo.getParentVO().getPk_org()
            .equals(existVo.getParentVO().getPk_org())
            && vo.getParentVO().getIfactororder()
                .equals(existVo.getParentVO().getIfactororder())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004060_0", "04004060-0052")/*
                                                                       * @res
                                                                       * "多人同时维护同一个财务组织的成本要素，出现并发！"
                                                                       */);
        }
      }
    }
  }

  private CostfactorVO[] queryCostfacotorIn(String[] pk_orgs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + CostfactorHeaderVO.PK_COSTFACTOR);
    sql.append(" from " + PUEntity.COSTFACTOR_H_TAB);
    sql.append(" where " + CostfactorHeaderVO.PK_ORG, pk_orgs);
    sql.append(" and dr=0");
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    if (rs.size() == 0) {
      return null;
    }
    String[] ids = rs.toOneDimensionStringArray();
    return new BillQuery<CostfactorVO>(CostfactorVO.class).query(ids);
  }

}

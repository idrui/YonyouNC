package nc.vo.pu.report.convertor;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.scmmm.pub.scmpub.report.adapter.SCMRptAbsSubConvertor;
import nc.scmmm.pubitf.scmpub.report.adapter.ISCMReportQueryInitializer;
import nc.vo.pu.report.enumeration.StoreReqSummaryQryFieldCode;
import nc.vo.pu.report.initializer.StoreReqSummaryQueryInitializer;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreePrivateContextKey;

public class StoreReqSummarySubConverter extends SCMRptAbsSubConvertor {

  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    // 为了适配平台的主组织权限，报表必须查出组织字段（可以不显示），对于汇总报表，取查询条件中第一个组织写死在SQL里。
    String pk_org_cond = "''";
    ConditionVO[] conds = this.getTranMap().getConditionVOs();
    for (ConditionVO condVo : conds) {
      if (StoreReqSummaryQryFieldCode.org.getFieldCode().equals(
          condVo.getFieldCode())) {
        String[] temp = condVo.getValue().split(",");
        if (temp.length == 1) {
          pk_org_cond = "'" + temp[0] + "'";
        }
        else {
          pk_org_cond = StringUtils.remove(temp[0], "(");
        }
      }
    }
    context.setAttribute(FreePrivateContextKey.KEY_USER_PK_ORG, pk_org_cond);
  }

  @Override
  protected ISCMReportQueryInitializer getReportInitializer() {
    return new StoreReqSummaryQueryInitializer();
  }

}

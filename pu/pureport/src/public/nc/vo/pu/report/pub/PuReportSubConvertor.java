package nc.vo.pu.report.pub;

import nc.adapter.ic.icreport.base.ICRptDefaultSubConvertor;
import nc.itf.iufo.freereport.extend.IBusiFormat;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreePrivateContextKey;

/**
 * 报表条件转换器(按yangb意见，统一用ic的公共类)
 * 
 * @since 6.0
 * @version 2011-7-5 下午03:35:28
 * @author 田锋涛
 */

public class PuReportSubConvertor extends ICRptDefaultSubConvertor {

  @Override
  protected IBusiFormat getBusiFormat() {
    return null;
  }

  /**
   * 为了适配平台的主组织权限，报表必须查出组织字段（可以不显示），对于汇总报表，取查询条件中第一个组织写死在SQL里。
   * 此方法的作用就是将查询条件中的主组织条件的第一个PK值存入上下文，供后台拼SQL使用。
   */
  protected void setCondOrgAttribute(IContext context, String mainOrgKey) {
    String pk_org_cond = "''";
    ConditionVO[] conds = this.getTranMap().getConditionVOs();
    for (ConditionVO condVo : conds) {
      if (mainOrgKey.equals(condVo.getFieldCode())) {
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
}

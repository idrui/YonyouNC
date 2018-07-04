package nc.ui.pu.m21.billref.srm.m4s1a;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.srm.m4s1a.IQuery21For4s1a;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 投诉建议单参照订单查询服务
 * 
 * @since 6.31
 * @version 2014-3-26 下午03:10:00
 * @author zhangyhh
 */
public class QueryServiceFor4s1a extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery21For4s1a queryService =
        NCLocator.getInstance().lookup(IQuery21For4s1a.class);
    OrderVO[] bills = queryService.queryPuOrderApps(queryScheme);
    return bills;
  }

  @Override
  protected void checkQueryCond(IQueryScheme qs) {
    QueryCondition qc =
        new QuerySchemeProcessor(qs).getQueryCondition(OrderHeaderVO.PK_ORG);
    if (null == qc || !PuRefQueryService.DOUBLEE_EQS.equals(qc.getOperator())
        || ArrayUtils.isEmpty(qc.getValues()) || null == qc.getValues()[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0072")/*
                                                                   * @res
                                                                   * "采购组织是必输唯一条件，请修改查询条件！"
                                                                   */);
    }
  }

}

/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����07:28:40
 */
package nc.ui.pu.m21.billref.pu.m30;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.so.m30.ICoopOrderQueryFor30;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������Эͬ�ɹ������Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����07:28:40
 */
public class QueryServiceFor30 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    ICoopOrderQueryFor30 queryService =
        NCLocator.getInstance().lookup(ICoopOrderQueryFor30.class);
    return queryService.coopOrderQueryFor30(queryScheme);
  }

  @Override
  protected void checkQueryCond(IQueryScheme qs) {
    super.checkQueryCond(qs);
    QueryCondition qc =
        new QuerySchemeProcessor(qs).getQueryCondition(OrderHeaderVO.PK_ORG);
    if (null == qc || !PuRefQueryService.DOUBLEE_EQS.equals(qc.getOperator())
        || ArrayUtils.isEmpty(qc.getValues()) || null == qc.getValues()[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0072")/*@res "�ɹ���֯�Ǳ���Ψһ���������޸Ĳ�ѯ������"*/);
    }
  }

  @Override
  protected String getRefOrgFieldCode() {
    return OrderQueryDLGInitializer.PK_SALEORG;
  }

}
package nc.ui.pu.pub.billref;

import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 采购组件 提供 查询服务实现类
 *
 * @since 6.0
 * @version 2011-2-25 下午04:58:05
 * @author liuchx
 */
public class PuRefQueryService implements IRefQueryService {
  protected static final String DOUBLEE_EQS = "==";

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return null;
  }

  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {
    return null;
  }

  /**
   * 转单检查查询条件<br>
   * <ul>
   * <li>下游主组织是否已经录入唯一值</li>
   * </ul>
   *
   * @param qs
   * @param orgFieldCode
   */
  protected void checkQueryCond(IQueryScheme qs) {
    String orgFieldCode = this.getRefOrgFieldCode();
    if (StringUtils.isBlank(orgFieldCode)) {
      return;
    }
    QueryCondition qc =
        new QuerySchemeProcessor(qs).getQueryCondition(orgFieldCode);
    if (null == qc || !PuRefQueryService.DOUBLEE_EQS.equals(qc.getOperator())
        || ArrayUtils.isEmpty(qc.getValues()) || null == qc.getValues()[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0040")/*@res "参照生单，下游主组织是必输唯一条件，请修改查询条件！"*/);
    }
  }

  /**
   * 得到下游组织查询模板字段名称
   *
   * @return
   */
  protected String getRefOrgFieldCode() {
    return null;
  }

}
/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 ����10:47:09
 */
package nc.impl.pu.m4t;

import nc.itf.pu.m4t.IInitialEstQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ����ʵ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 ����10:47:09
 */
public class InitialEstQueryImpl implements IInitialEstQuery {

  @Override
  public InitialEstVO[] query(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return this.lazyQuery(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private InitialEstVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }
    qrySchemeProcessor.appendWhere(mainTableAlias + ".dr = 0 ");
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();// ����֯Ȩ��
    return (InitialEstVO[]) new PUBillLazyQuery<InitialEstVO>(
        InitialEstVO.class, POBillType.InitEstimate)
        .queryOrderByBillcode(queryScheme);
  }
}

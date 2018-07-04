package nc.ui.pu.pub.billref;

import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���� �ṩ ��ѯ����ʵ����
 *
 * @since 6.0
 * @version 2011-2-25 ����04:58:05
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
   * ת������ѯ����<br>
   * <ul>
   * <li>��������֯�Ƿ��Ѿ�¼��Ψһֵ</li>
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
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0040")/*@res "������������������֯�Ǳ���Ψһ���������޸Ĳ�ѯ������"*/);
    }
  }

  /**
   * �õ�������֯��ѯģ���ֶ�����
   *
   * @return
   */
  protected String getRefOrgFieldCode() {
    return null;
  }

}
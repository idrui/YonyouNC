package nc.vo.pu.pub.util;

import java.util.HashMap;
import java.util.Map;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQuerySQLGenerator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryConstants;

import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���Ĳ�ѯ������������
 * 
 * @since 6.0
 * @version 2011-12-6 ����11:58:47
 * @author zhaoyha
 */
public class QuerySchemeUtil {

  /**
   * ����ָ������ʵ�壬����ģ���ѯģ�����ɵ�scheme<br>
   * Ĭ�Ϸ��ص�scheme�൱�ڣ��ڲ�ѯģ���������ѡ������ʵ��.dr=0<br>
   * �û��ɸ���������ص�scheme�������Լ�����������������Schemeһ��<br>
   * ����֧�ִ���ȡ��conditionvo��Ifilter<br>
   * 
   * @param mainEntityFullName ���磺pu.po_order
   * @return ����Ϊnull
   */
  public static IQueryScheme createQueryScheme(String mainEntityFullName) {
    IBean bean = null;
    try {
      bean =
          MDBaseQueryFacade.getInstance().getBeanByFullName(mainEntityFullName);
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    String beanid = null != bean ? bean.getID() : null;
    String mainTab = null != bean ? bean.getTable().getName() : null;
    if (StringUtils.isBlank(beanid) || StringUtils.isBlank(mainTab)
        || null == mainTab) {
      return null;
    }
    QueryScheme newQs = new QueryScheme();
    newQs.put(QueryConstants.BEAN_ID, beanid);
    SqlBuilder from = new SqlBuilder();
    mainTab = mainTab.toLowerCase();
    from.append(mainTab + " " + mainTab);
    SqlBuilder where = new SqlBuilder();
    where.append(" " + mainTab + ".dr", 0);
    Map<String, String> attMap = new HashMap<String, String>();
    attMap.put(IQuerySQLGenerator.DEFAULT_KEY, mainTab);
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    fwsql.setAttrpath_alias_map(attMap);
    newQs.putTableJoinFromWhereSQL(fwsql);
    return newQs;
  }

}

/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 ����07:13:11
 */
package nc.vo.pu.m21.pub;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.querytemplate.QueryTemplateConvertor;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-29 ����07:13:11
 */
public class QueryUtil {

  private static QueryUtil instance = new QueryUtil();

  private QueryUtil() {
    // ˽��
  }

  public static QueryUtil getInstance() {
    return QueryUtil.instance;
  }

  public void appendReplaceCond(QuerySchemeProcessor qrySchemeProcessor) {
    String bb1TableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    this.appendArrOrg(qrySchemeProcessor, bb1TableAlias);
    // ����Ҫ�������ɸ������ԡ����κ�
  }

  public String getArrOrg(QuerySchemeProcessor qrySchemeProcessor,
      String bb1TableAlias) {
    QueryCondition arrvOrgCond =
        qrySchemeProcessor.getQueryCondition(OrderItemVO.PK_ORDER_B + "."
            + OrderItemVO.PK_ARRVSTOORG);
    if (null == arrvOrgCond) {
      return null;
    }
    Object[] value = arrvOrgCond.getValues();
    SqlBuilder sql = new SqlBuilder();
    sql.append(
        " and " + bb1TableAlias + "." + OrderReceivePlanVO.PK_ARRVSTOORG,
        value[0].toString());
    return sql.toString();
  }

  public ConditionVO getDplanArrvDateCond(IQueryScheme queryScheme) {
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] vos =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      String key = OrderItemVO.PK_ORDER_B + "." + OrderItemVO.DPLANARRVDATE;
      for (ConditionVO vo : vos) {
        if (key.equals(vo.getFieldCode())) {
          return vo;
        }
      }
    }
    return null;
  }

  public String getReplacedCond(String cond, String poOrderB) {
    String replaced = cond;
    // �ջ������֯��ע��ֻ�ܵ�ѡ������
    Pattern pattern =
        Pattern.compile(poOrderB + ".pk_arrvstoorg\\s*=\\s*'\\w*'");
    Matcher matcher = pattern.matcher(cond);
    boolean find = matcher.find(0);
    if (find) {
      replaced =
          matcher.replaceFirst(" " + poOrderB + "." + OrderItemVO.DR + " = 0 ");
    }

    return replaced;
  }

  /**
   * �����������������ӱ��滻Ϊ�����ƻ����Ĳ�ѯ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param poOrderB
   * @param poOrderBB1
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-8 ����10:27:49
   */
  public String getReplacedCond(String cond, String poOrderB, String poOrderBB1) {
    String replaced = cond;
    // �ջ������֯��ע��ֻ�ܵ�ѡ������
    Pattern pattern =
        Pattern.compile(poOrderB + ".pk_arrvstoorg\\s*=\\s*'\\w*'");
    Matcher matcher = pattern.matcher(cond);
    boolean find = matcher.find();
    if (find) {
      String arrvstoorg = matcher.group();
      replaced =
          matcher.replaceAll(this.replaceString(arrvstoorg, poOrderB,
              poOrderBB1));
    }

    pattern = Pattern.compile(poOrderB + ".vbatchcode\\s*=\\s*'\\w*'");
    matcher = pattern.matcher(cond);
    find = matcher.find();
    if (find) {
      String vbatchcode = matcher.group();
      replaced =
          matcher.replaceAll(this.replaceString(vbatchcode, poOrderB,
              poOrderBB1));
    }

    return replaced;
  }

  public String getReplacedCondByPlanDate(String cond, String poOrderB) {
    String replaced = cond;
    Pattern pattern =
        Pattern.compile(poOrderB
            + ".dplanarrvdate\\s*>=\\s*'\\w*-\\w*-\\w*\\s*\\w*:\\w*:\\w*'");
    Matcher matcher = pattern.matcher(cond);
    boolean find = matcher.find(0);
    if (find) {
      replaced =
          matcher.replaceFirst(" " + poOrderB + "." + OrderItemVO.DR + " = 0 ");
    }

    pattern =
        Pattern.compile(poOrderB
            + ".dplanarrvdate\\s*<=\\s*'\\w*-\\w*-\\w*\\s*\\w*:\\w*:\\w*'");
    matcher = pattern.matcher(replaced);
    find = matcher.find(0);
    if (find) {
      replaced =
          matcher.replaceFirst(" " + poOrderB + "." + OrderItemVO.DR + " = 0 ");
    }
    return replaced;
  }

  /**
   * �����������������SQL����б�ı������ڣ����ر��������򷵻ر�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param tableName����
   * @param aliasName��ı���
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-29 ����07:19:23
   */
  public String getTableName(String tableName, String aliasName) {
    return StringUtil.isEmptyWithTrim(aliasName) ? tableName : aliasName;
  }

  private void appendArrOrg(QuerySchemeProcessor qrySchemeProcessor,
      String bb1TableAlias) {
    QueryCondition arrvOrgCond =
        qrySchemeProcessor.getQueryCondition(OrderItemVO.PK_ORDER_B + "."
            + OrderItemVO.PK_ARRVSTOORG);
    if (null == arrvOrgCond) {
      return;
    }
    Object[] value = arrvOrgCond.getValues();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" or " + bb1TableAlias + "." + OrderReceivePlanVO.PK_ARRVSTOORG,
        value[0].toString());
    qrySchemeProcessor.appendWhere(sql.toString());
  }

  /**
   * ���������������滻���ַ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param source
   * @param replaced
   * @param replace
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-8 ����10:37:13
   */
  private String replaceString(String source, String replaced, String replace) {
    String ret = source;
    ret = StringUtil.replaceAllString(ret, replaced, replace);
    ret = "(" + source + " or " + ret + ") ";
    return ret;
  }

}

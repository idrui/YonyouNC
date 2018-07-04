/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 下午07:13:11
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-29 下午07:13:11
 */
public class QueryUtil {

  private static QueryUtil instance = new QueryUtil();

  private QueryUtil() {
    // 私有
  }

  public static QueryUtil getInstance() {
    return QueryUtil.instance;
  }

  public void appendReplaceCond(QuerySchemeProcessor qrySchemeProcessor) {
    String bb1TableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    this.appendArrOrg(qrySchemeProcessor, bb1TableAlias);
    // 还需要考虑自由辅助属性、批次号
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
    // 收货库存组织，注意只能单选，等于
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
   * 方法功能描述：将子表替换为到货计划表后的查询条件
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param poOrderB
   * @param poOrderBB1
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-8 上午10:27:49
   */
  public String getReplacedCond(String cond, String poOrderB, String poOrderBB1) {
    String replaced = cond;
    // 收货库存组织，注意只能单选，等于
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
   * 方法功能描述：如果SQL语句中表的别名存在，返回别名，否则返回表名。
   * <p>
   * <b>参数说明</b>
   * 
   * @param tableName表名
   * @param aliasName表的别名
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-29 下午07:19:23
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
   * 方法功能描述：替换后字符串
   * <p>
   * <b>参数说明</b>
   * 
   * @param source
   * @param replaced
   * @param replace
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-8 上午10:37:13
   */
  private String replaceString(String source, String replaced, String replace) {
    String ret = source;
    ret = StringUtil.replaceAllString(ret, replaced, replace);
    ret = "(" + source + " or " + ret + ") ";
    return ret;
  }

}

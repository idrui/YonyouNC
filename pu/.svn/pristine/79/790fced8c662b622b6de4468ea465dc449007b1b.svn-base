/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 下午03:46:23
 */
package nc.pubimpl.pu.m4t.pu.m25;

import nc.bs.pu.m4t.query.pu.InitialEstQueryFor25BP;
import nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单生成采购发票的查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 下午03:46:23
 */
public class InitialEstInvoiceQueryImpl implements IInitialEstInvoiceQuery {

  @Override
  public InitialEstVO[] query(IQueryScheme queryScheme)
      throws BusinessException {
    String cond = this.createSql(queryScheme);
    try {
      InitialEstVO[] vos = new InitialEstQueryFor25BP().query(cond);
      if (null == vos) {
        return vos;
      }
      for (InitialEstVO vo : vos) {
        InitialEstItemVO[] itemVOs = vo.getItems();
        VOSortUtils.ascSort(itemVOs, InitialEstItemVO.CROWNO);
        vo.setChildrenVO(itemVOs);
      }
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    // 子表别名，这里定义为B，方便后续拼sql
    String B = processor.getTableAliasOfAttribute("po_initialest_b.pk_org");

    SqlBuilder partWhr = new SqlBuilder();
    // 单据状态=审批
    partWhr.append(" and po_initialest.fbillstatus ",
        (Integer) InitialEstStatus.APPROVED.value());
    // 主数量 - 累计开票数量 >0
    partWhr.append(" and  abs(coalesce(" + B + ".nnum,0))");
    partWhr.append("     > abs(coalesce(" + B + ".naccinvoicenum, 0 ))");
    // 主数量 - 累计结算数量 >0 即开票的时候要考虑结算数量
    partWhr.append(" and  abs(coalesce(" + B + ".nnum,0))");
    partWhr.append("     > abs(coalesce(" + B + ".naccsettlenum, 0 ))");

    processor.appendWhere(partWhr.toString());

    SqlBuilder wholeSql = new SqlBuilder();
    wholeSql.append("select distinct " + B + ".pk_initialest_b ");
    wholeSql.append(processor.getFinalFromWhere());

    return wholeSql.toString();
  }

}

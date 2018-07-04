package nc.pubimpl.pu.m21.srm.m4s1a;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m21.srm.m4s1a.IQuery21For4s1a;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 投诉建议单参照订单查询
 * 
 * @since 6.31
 * @version 2014-3-26 下午03:10:00
 * @author zhangyhh
 */
public class Query21For4s1aImpl implements IQuery21For4s1a {

  @Override
  public OrderVO[] queryPuOrderApps(IQueryScheme queryScheme)
      throws BusinessException {

    String sql = this.appendWhereToSchema(queryScheme);

    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql).toOneDimensionStringArray();

    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    List<OrderHeaderVO> headerList = new ArrayList<OrderHeaderVO>();
    List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
    for (OrderViewVO view : views) {
      headerList.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
      itemList.add((OrderItemVO) view.getVO(OrderItemVO.class));
    }

    BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
    OrderVO tempVO = new OrderVO();
    bc.append(tempVO.getMetaData().getParent(),
        headerList.toArray(new OrderHeaderVO[headerList.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class),
        itemList.toArray(new OrderItemVO[itemList.size()]));
    OrderVO[] queryVos = bc.composite();

    return queryVos;
  }

  /**
  * 将特殊的where语句加入到QuerySchema中。
  *
  * @param queryScheme
  */
  private String appendWhereToSchema(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();

    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    SqlBuilder wholeSql = new SqlBuilder();
    SqlBuilder whereSql = new SqlBuilder();

    whereSql.append(
        " and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    whereSql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    qrySchemeProcessor.appendWhere(whereSql.toString());

    // 为了拼sql方便，主表用H代替，子表用B代替，最后用表别名统一替换
    wholeSql.append("select B." + OrderItemVO.PK_ORDER_B);
    wholeSql.append(qrySchemeProcessor.getFinalFromWhere());

    // 用表别名进行替换
    String finalSql = wholeSql.toString().replace("H.", mainTableAlias + ".");
    finalSql = finalSql.replace("B.", itemTableAlias + ".");

    return finalSql;

  }

}

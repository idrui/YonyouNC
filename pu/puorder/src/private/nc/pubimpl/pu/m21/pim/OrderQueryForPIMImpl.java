package nc.pubimpl.pu.m21.pim;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.pu.m21.pim.IOrderQueryForPIM;
import nc.pubitf.pu.m21.pim.QueryLatestPriceParam;
import nc.pubitf.scmpub.pim.fetch.DefaultFetchDataForPim;
import nc.pubitf.scmpub.pim.fetch.FetchDataUtil;
import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.pim.PIMFetchDataUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;
import nc.vo.scmpub.mm.QueryParamVOChecker;

public class OrderQueryForPIMImpl implements IOrderQueryForPIM {

  @Override
  public OrderPriceData[] queryLatestPrice(QueryLatestPriceParam param)
      throws BusinessException {
    try {
      // 项目没有物料版本之分
      LatestPriceQueryBP bp = new LatestPriceQueryBP();
      OrderPriceData[] resData =
          bp.queryLatestPrice(param.getPurchaseOrgs(), param.getFinanceOrgs(),
              param.getSuppliers(), param.getCurrencys(), param.getMaterials(),
              param.getStartDate(), param.getEndDate());
      return resData;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new OrderPriceData[0];
  }

  @Override
  public OrderVO[] queryOrderForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException {
    try {
      QueryParamVOChecker.check(pvo);
      return new BillQuery<OrderVO>(OrderVO.class).query(this.getOrderPks(pvo));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public IFetchDataForPim[] queryOrderForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException {
    try {
      FetchDataUtil.checkInput(cprojectids, beginDateTime, endDateTime);

      SqlBuilder sql = new SqlBuilder();
      sql.append(PIMFetchDataUtil.buildSelectPart(OrderItemVO.CPROJECTID,
          OrderItemVO.PK_SRCMATERIAL, OrderItemVO.NNUM));
      sql.append(" from " + PUEntity.M21_B_TABLE);
      sql.append(" where ");
      sql.append(PIMFetchDataUtil.buildCommonWherePart(beginDateTime,
          endDateTime, cprojectids, PUEntity.M21_B_TABLE,
          OrderItemVO.CPROJECTID, OrderItemVO.DBILLDATE));
      sql.append(PIMFetchDataUtil.buildGroupByPart(OrderItemVO.CPROJECTID,
          OrderItemVO.PK_SRCMATERIAL));

      @SuppressWarnings("unchecked")
      List<DefaultFetchDataForPim> result =
          (List<DefaultFetchDataForPim>) NCLocator
              .getInstance()
              .lookup(IUAPQueryBS.class)
              .executeQuery(sql.toString(),
                  new BeanListProcessor(DefaultFetchDataForPim.class));

      if (result == null) {
        return new IFetchDataForPim[0];
      }
      return result.toArray(new IFetchDataForPim[result.size()]);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new IFetchDataForPim[0];
  }

  /**
   * 根据查询条件查询订单表头ID，所有条件都是必输条件。
   * 
   * @return 订单表头ID。
   */
  private String[] getOrderPks(LinkQueryParamVOForPM pvo) {
    // 根据查询条件查询订单表头ID，所有条件都是必输条件。
    SqlBuilder sqlBuilder = new SqlBuilder();

    sqlBuilder.append("select distinct pk_order from po_order_b where ");
    sqlBuilder.append(OrderItemVO.CPROJECTID, " = ", pvo.getCprojectid());
    sqlBuilder.append(" and ");
    sqlBuilder.append(OrderItemVO.PK_SRCMATERIAL, " = ", pvo.getCmaterialoid());
    sqlBuilder.append(" and ");
    sqlBuilder.append(OrderItemVO.DBILLDATE, ">=", pvo.getStartdate()
        .toString());
    sqlBuilder.append(" and ");
    sqlBuilder.append(OrderItemVO.DBILLDATE, "<=", pvo.getEnddate().toString());

    if (pvo.getCprojecttaskid() != null) {
      sqlBuilder.append(" and ");
      sqlBuilder.append(OrderItemVO.CPROJECTTASKID, " = ",
          pvo.getCprojecttaskid());
    }

    DataAccessUtils tool = new DataAccessUtils();
    return tool.query(sqlBuilder.toString()).toOneDimensionStringArray();
  }
}

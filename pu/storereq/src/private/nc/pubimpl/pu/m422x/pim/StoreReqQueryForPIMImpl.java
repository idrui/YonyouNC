package nc.pubimpl.pu.m422x.pim;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.pu.m422x.pim.IStoreReqQueryForPIM;
import nc.pubitf.scmpub.pim.fetch.DefaultFetchDataForPim;
import nc.pubitf.scmpub.pim.fetch.FetchDataUtil;
import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pim.PIMFetchDataUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;
import nc.vo.scmpub.mm.QueryParamVOChecker;

import org.apache.commons.lang.StringUtils;

public class StoreReqQueryForPIMImpl implements IStoreReqQueryForPIM {

  @Override
  public StoreReqAppVO[] queryStoreReqForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException {
    try {
      QueryParamVOChecker.check(pvo);
      return new BillQuery<StoreReqAppVO>(StoreReqAppVO.class).query(this
          .queryPks(pvo));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public IFetchDataForPim[] queryStoreReqForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException {
    try {
      FetchDataUtil.checkInput(cprojectids, beginDateTime, endDateTime);

      SqlBuilder sql = new SqlBuilder();
      sql.append(PIMFetchDataUtil.buildSelectPart(StoreReqAppItemVO.CPROJECTID,
          StoreReqAppItemVO.PK_SRCMATERIAL, StoreReqAppItemVO.NNUM));
      sql.append(" from " + PUEntity.M422X_B_TABLE);
      sql.append(" where ");
      sql.append(PIMFetchDataUtil.buildCommonWherePart(beginDateTime,
          endDateTime, cprojectids, PUEntity.M422X_B_TABLE,
          StoreReqAppItemVO.CPROJECTID, StoreReqAppItemVO.DBILLDATE));
      sql.append(PIMFetchDataUtil.buildGroupByPart(
          StoreReqAppItemVO.CPROJECTID, StoreReqAppItemVO.PK_SRCMATERIAL));

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
  private String[] queryPks(LinkQueryParamVOForPM vo) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("  Select  distinct " + StoreReqAppItemVO.PK_STOREREQ + " from "
        + PUEntity.M422X_B_TABLE + " where ");
    sql.append(StoreReqAppItemVO.PK_SRCMATERIAL, vo.getCmaterialoid());
    if (!StringUtils.isBlank(vo.getCprojecttaskid())) {
      sql.append(" and " + StoreReqAppItemVO.CPROJECTTASKID,
          vo.getCprojecttaskid());
    }
    sql.append(" and " + StoreReqAppItemVO.CPROJECTID, vo.getCprojectid());
    sql.append(" and " + StoreReqAppItemVO.DBILLDATE + " >='"
        + vo.getStartdate() + "' and " + StoreReqAppItemVO.DBILLDATE + "  <='"
        + vo.getEnddate() + "'");
    DataAccessUtils utils = new DataAccessUtils();
    return utils.query(sql.toString()).toOneDimensionStringArray();
  }

}

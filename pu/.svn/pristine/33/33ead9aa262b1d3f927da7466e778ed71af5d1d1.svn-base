package nc.pubimpl.pu.m20.pim;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.pu.m20.pim.IBuyingReqQueryForPIM;
import nc.pubitf.scmpub.pim.fetch.DefaultFetchDataForPim;
import nc.pubitf.scmpub.pim.fetch.FetchDataUtil;
import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pim.PIMFetchDataUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;
import nc.vo.scmpub.mm.QueryParamVOChecker;

import org.apache.commons.lang.StringUtils;

public class BuyingReqQueryForPIMImpl implements IBuyingReqQueryForPIM {

  @Override
  public PraybillVO[] queryBuyingReqForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException {
    try {
      QueryParamVOChecker.check(pvo);
      return new BillQuery<PraybillVO>(PraybillVO.class).query(this
          .queryPks(pvo));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public IFetchDataForPim[] queryBuyingReqForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException {

    try {
      FetchDataUtil.checkInput(cprojectids, beginDateTime, endDateTime);

      SqlBuilder sql = new SqlBuilder();
      sql.append(PIMFetchDataUtil.buildSelectPart(PraybillItemVO.CPROJECTID,
          PraybillItemVO.PK_SRCMATERIAL, PraybillItemVO.NNUM));
      sql.append(" from " + PUEntity.M20_B_TABLE);
      sql.append(" where ");
      sql.append(PIMFetchDataUtil.buildCommonWherePart(beginDateTime,
          endDateTime, cprojectids, PUEntity.M20_B_TABLE,
          PraybillItemVO.CPROJECTID, PraybillItemVO.DBILLDATE));
      sql.append(PIMFetchDataUtil.buildGroupByPart(PraybillItemVO.CPROJECTID,
          PraybillItemVO.PK_SRCMATERIAL));

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

  protected String[] queryPks(LinkQueryParamVOForPM vo) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("  Select  distinct " + PraybillItemVO.PK_PRAYBILL + " from "
        + PUEntity.M20_B_TABLE + " where ");
    sql.append(PraybillItemVO.PK_SRCMATERIAL, vo.getCmaterialoid());
    if (!StringUtils.isBlank(vo.getCprojecttaskid())) {
      sql.append(" and " + PraybillItemVO.CPROJECTTASKID,
          vo.getCprojecttaskid());
    }
    sql.append(" and " + PraybillItemVO.CPROJECTID, vo.getCprojectid());
    sql.append(" and " + PraybillItemVO.DBILLDATE + " >='" + vo.getStartdate()
        + "' and " + PraybillItemVO.DBILLDATE + " <='" + vo.getEnddate() + "'");
    DataAccessUtils utils = new DataAccessUtils();
    // 查询表头、表体、到货计划表id
    return utils.query(sql.toString()).toOneDimensionStringArray();
  }
}

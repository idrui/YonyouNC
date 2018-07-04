package nc.pubimpl.pu.it;

import nc.bs.pu.est.m45.PurchsInFeeEstQueryBP;
import nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP;
import nc.bs.pu.est.m45.PurchsInUnEstQueryBP;
import nc.pubitf.pu.it.IPurchaseInEstQueryForIT;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

public class PurchaseInEstQueryForITImpl implements IPurchaseInEstQueryForIT {

  @Override
  public PurchaseInEstVO[] feeEstQuery4IT(IQueryScheme queryScheme,
      UFBoolean includefeeEstimated) throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond = this.getQueryCond(qrySchemeProcessor, QueryEstType.FEE_EST);
      PurchsInFeeEstQueryBP purchsInFeeEstQueryBP =
          new PurchsInFeeEstQueryBP(includefeeEstimated);
      purchsInFeeEstQueryBP.setIsIT(UFBoolean.TRUE);
      return purchsInFeeEstQueryBP.query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseInEstVO[] goodsEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond =
          this.getQueryCond(qrySchemeProcessor, QueryEstType.GOODS_EST);
      PurchsInGoodsEstQueryBP purchsInGoodsEstQueryBP =
          new PurchsInGoodsEstQueryBP();
      purchsInGoodsEstQueryBP.setIsIT(UFBoolean.TRUE);
      return purchsInGoodsEstQueryBP.query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseInEstVO[] unEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond = this.getQueryCond(qrySchemeProcessor, QueryEstType.UN_EST);
      PurchsInUnEstQueryBP purchsInUnEstQueryBP = new PurchsInUnEstQueryBP();
      purchsInUnEstQueryBP.setIsIT(UFBoolean.TRUE);
      return purchsInUnEstQueryBP.query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 暂估先从sql中抽取，如果设计要求可从前台传递
   * TODO tianft 以后重构，财务组织从前台传递
   **/
  private String extractFiorg(String cond) {
    int i = cond.indexOf(PurchaseinFIItemVO.PK_FINANCEORG);
    if (0 > i) {
      return null;
    }
    i = cond.indexOf("'", i + 1);
    if (0 > i) {
      return null;
    }
    return cond.substring(i + 1, i + 21);
  }

  private String getQueryCond(QuerySchemeProcessor qrySchemeProcessor,
      QueryEstType estQryType) {
    if (QueryEstType.FEE_EST == estQryType) {
      String bname =
          qrySchemeProcessor
              .getTableAliasOfAttribute("pk_stockps_b.pk_stockps_b");
      StringBuilder sb = new StringBuilder();
      sb.append(" left outer join ");
      sb.append(PUEntity.PurchaseinFI_Fee_TAB);
      sb.append(" on ");
      sb.append(bname);
      sb.append(".");
      sb.append(PurchaseinFIItemVO.PK_STOCKPS_B);
      sb.append("=");
      sb.append(PUEntity.PurchaseinFI_Fee_TAB);
      sb.append(".");
      sb.append(PurchaseinFIItemVO.PK_STOCKPS_B);
      qrySchemeProcessor.appendFrom(sb.toString());
    }
    return qrySchemeProcessor.getFinalFromWhere();
  }

}

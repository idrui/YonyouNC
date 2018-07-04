/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 上午11:44:05
 */
package nc.impl.pu.est.m45;

import nc.bs.pu.est.m45.PurchsInFeeEstQueryBP;
import nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP;
import nc.bs.pu.est.m45.PurchsInUnEstQueryBP;
import nc.itf.pu.est.m45.IPurchaseInEstQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单暂估查询实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-17 上午11:44:05
 */

public class PurchaseInEstQueryImpl implements IPurchaseInEstQuery {

  @Override
  public PurchaseInEstVO[] feeEstQuery(IQueryScheme queryScheme,
      UFBoolean includefeeEstimated) throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond = this.getQueryCond(qrySchemeProcessor, QueryEstType.FEE_EST);
      return new PurchsInFeeEstQueryBP(includefeeEstimated).query(cond,
          this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.est.m45.IPurchaseInEstQuery#feeEstQuery(java.lang.String)
   */
  @Override
  public PurchaseInEstVO[] feeEstQuery(String cond,
      UFBoolean includeFeeEstimated) throws BusinessException {
    try {
      return new PurchsInFeeEstQueryBP(includeFeeEstimated).query(cond,
          this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseInEstVO[] goodsEstQuery(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond =
          this.getQueryCond(qrySchemeProcessor, QueryEstType.GOODS_EST);
      return new PurchsInGoodsEstQueryBP().query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.est.m45.IPurchaseInEstQuery#goodsEstQuery(java.lang.String)
   */
  @Override
  public PurchaseInEstVO[] goodsEstQuery(String cond) throws BusinessException {
    try {
      return new PurchsInGoodsEstQueryBP().query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseInEstVO[] unEstQuery(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      String cond = this.getQueryCond(qrySchemeProcessor, QueryEstType.UN_EST);
      return new PurchsInUnEstQueryBP().query(cond, this.extractFiorg(cond));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.est.m45.IPurchaseInEstQuery#unEstQuery(java.lang.String)
   */
  @Override
  public PurchaseInEstVO[] unEstQuery(String cond) throws BusinessException {
    try {
      return new PurchsInUnEstQueryBP().query(cond, this.extractFiorg(cond));
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

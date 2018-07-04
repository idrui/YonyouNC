package nc.vo.pu.pub.util;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 待审批过滤
 * 
 * @since 6.0
 * @version 2011-3-29 上午10:03:51
 * @author wuxla
 */

public class ApprovingUtil {
  /**
   * 查询时待审批过滤
   * 
   * @param <T>
   * @param queryScheme 查询方案
   * @param vos 需要过滤的聚合VO数组
   * @param billType 单据类型
   * @return 过滤后的聚合VO数组
   */
  public static <T extends AggregatedValueObject> T[] filterForApprove(
      IQueryScheme queryScheme, T[] vos, String billType) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition condition =
        qrySchemeProcessor.getQueryCondition(PUQueryConst.BISAPPROVING);
    if (null == condition) {
      return vos;
    }
    Object[] values = condition.getValues();
    if (!UFBoolean.valueOf(values[0].toString()).booleanValue()) {
      return vos;
    }
    String userId = AppContext.getInstance().getPkUser();
    String trantypeName =
        ApproveFlowUtil.getIFlowBizItfMapKey(vos[0],
            IFlowBizItf.ATTRIBUTE_TRANSTYPE);
    return PfServiceUtil.filterForApprove(vos, billType, userId,
        null == trantypeName ? "null" : trantypeName);
  }

  public static <T extends AggregatedValueObject> T[] filterForApprove(
      IQueryScheme queryScheme, T[] vos, String billType,
      String approvingCondFieldCode) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition condition =
        qrySchemeProcessor.getQueryCondition(approvingCondFieldCode);
    if (null == condition) {
      return vos;
    }
    Object[] values = condition.getValues();
    if (!UFBoolean.valueOf(values[0].toString()).booleanValue()) {
      return vos;
    }
    String userId = AppContext.getInstance().getPkUser();
    String trantypeName =
        ApproveFlowUtil.getIFlowBizItfMapKey(vos[0],
            IFlowBizItf.ATTRIBUTE_TRANSTYPE);
    return PfServiceUtil.filterForApprove(vos, billType, userId,
        null == trantypeName ? "null" : trantypeName);
  }

}

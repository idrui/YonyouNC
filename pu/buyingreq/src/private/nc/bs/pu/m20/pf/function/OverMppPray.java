/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午08:28:37
 */
package nc.bs.pu.m20.pf.function;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购超采购计划金额，可以删掉了
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午08:28:37
 */
public class OverMppPray {
  /**
   * <p>
   * 请购单审批函数：请购超采购计划金额。
   * <p>
   * 请购超采购计划金额=SUM每行（请购单对应采购计划的累计请购金额—采购计划金额；
   * <p>
   * 配置请购单的审批流时，可以使用此函数作为审批条件进行配置；
   * <p>
   * 注：一张请购单可能会对应多个采购计划，此时应该提示用户“当前请购单的第*，…行对应多个采购计划，无法计算请购超采购计划金额”，审批流中止。
   * <p>
   */

  public UFDouble getOverMppMnyPray(AggregatedValueObject vo)
      throws BusinessException {
    return this.getOverMppPray(vo, 1);
  }

  /**
   * <p>
   * 请购单审批函数：请购超采购计划数量。
   * <p>
   * 请购超采购计划数量=SUM每行（请购单对应采购计划的累计请购数量—采购计划数量）；
   * <p>
   * 配置请购单的审批流时，可以使用此函数作为审批条件进行配置；
   * <p>
   * 注：一张请购单可能会对应多个采购计划，此时应该提示用户“当前请购单的第*，…行对应多个采购计划，无法计算请购超采购计划数量”。
   * <p>
   */
  public UFDouble getOverMppNumPray(AggregatedValueObject vo)
      throws BusinessException {
    return this.getOverMppPray(vo, 0);
  }

  /**
   * 方法功能描述：请购单审批函数具体实现：0、请购超采购计划数量；1、请购超采购计划金额。。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param iType
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午11:12:02
   */

  private UFDouble getOverMppPray(AggregatedValueObject vo, int iType)
      throws BusinessException {
    // 合法性检查
    if (vo == null || vo.getParentVO() == null || vo.getChildrenVO() == null
        || vo.getChildrenVO().length <= 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0083")/*
                                                                   * @res
                                                                   * 检查函数传入参数错误!
                                                                   */
      );
    }
    UFDouble ufdRet = null;
    try {
      // 如果采购计划未启用， 则抛出异常
      if (!SysInitGroupQuery.isMPPEnabled()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0084")/*
                                                                     * @res
                                                                     * 订单超采购计划函数只有在采购计划启用时配置才有意义
                                                                     * !
                                                                     */);
      }
      // 调用采购计划接口方法
      // BillInvokeVO[] vos = new ReWriteDMOPr().getBillInvokeChildVOs(
      // new PraybillVO[] { vo }, true);
      // ufdRet = new PlanExamDMO().pupl_fun(vos, iType, 0);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return ufdRet;
  }
}

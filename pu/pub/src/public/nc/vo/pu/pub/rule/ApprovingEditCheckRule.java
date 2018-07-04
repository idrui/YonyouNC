package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.vo.pu.pub.enumeration.PuStrEnum;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.scmpub.res.billtype.IBillType;

/**
 * @description
 *              采购订单支持审批中修改（修订）的单据，后台检查是否应该由当前审批人修改（修订）<br>
 *              注意审批中修改或修订都会使用此规则
 * @scene
 *        采购订单修订
 * @param IBillType billtype 单据类型
 * @since 6.3
 * @version 2014-10-22 上午10:36:54
 * @author luojw
 */
public class ApprovingEditCheckRule<E extends AbstractBill> implements IRule<E> {

  private IBillType billtype;

  /**
   * @param billtype 单据类型
   */
  public ApprovingEditCheckRule(IBillType billtype) {
    super();
    this.billtype = billtype;
  }

  @Override
  public void process(E[] vos) {
    E[] filterVos = ApproveFlowUtil.filterApprovingEditReviseVO(vos);
    if (null == filterVos || filterVos.length == 0) {
      return;
    }
    // 检查审批中可以由当前人修改（修订）
    this.check(filterVos);
  }

  private void check(E[] vos) {
    String trantypeName =
        ApproveFlowUtil.getIFlowBizItfMapKey(vos[0],
            IFlowBizItf.ATTRIBUTE_TRANSTYPE);
    E[] filterVos =
        PfServiceUtil.filterForApprove(vos, this.billtype.getCode(), AppContext
            .getInstance().getPkUser(),
            null == trantypeName ? PuStrEnum.vo_att_notranstype.code()
                : trantypeName);
    if (null == filterVos || filterVos.length != vos.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0125"));/*
                                                                     * @res
                                                                     * "审批中的单据只能由当前审批人修改或修订！"
                                                                     */
    }
  }

}

/**
 *
 */
package nc.vo.pu.m25.rule.ap;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 发票状态过滤规则，滤掉已经传应付、期初、冻结等发票
 * 如果是标志是检查，则抛异常，否则走过滤
 * @scene
 * 传应付,结算(匹配)后自动传应付
 * @param
 * sCheck 如果是标志是true，则抛异常，否则走过滤
 *
 * @since 6.3
 * @version 2014-10-22 下午3:39:57
 * @author zhangshqb
 */
public class SendAPStateFilterRule implements IFilterRule<InvoiceVO> {

  private boolean isCheck = false;

  /**
   * SendAPStateFilterRule 的构造子
   *
   * @param isCheck
   *          如果是标志是true，则抛异常，否则走过滤
   */
  public SendAPStateFilterRule(boolean isCheck) {
    this.isCheck = isCheck;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      int state = vo.getParentVO().getFbillstatus().intValue();
      if (POEnumBillStatus.APPROVE.toInt() != state) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0046")/*@res "未审批通过的发票不可确认应付！"*/);
      }
      UFBoolean sendflag = vo.getParentVO().getBapflag();
      if (this.isCheck && ValueUtils.getBoolean(sendflag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0047")/*@res "已经确认过应付不可重复确认!"*/);
      }
      else if (ValueUtils.getBoolean(sendflag)) {
        continue;
      }
      UFBoolean initFlag = vo.getParentVO().getBinitial();
      if (this.isCheck && ValueUtils.getBoolean(initFlag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0048")/*@res "期初发票不能传应付!"*/);
      }
      else if (ValueUtils.getBoolean(initFlag)) {
        continue;
      }
      if (this.isCheck && this.isPostiveFrozen(vo)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0049")/*@res "冻结的正发票不可确认应付！"*/);
      }
      else if (this.isPostiveFrozen(vo)) {
        continue;
      }
      filterVos.add(vo);// 所有检查都通过则可传应付
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  /** 判断发票是否已冻结的正发票 **/
  private boolean isPostiveFrozen(InvoiceVO vo) {
    boolean frozen = ValueUtils.getBoolean(vo.getParentVO().getBfrozen());
    if (!frozen) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      UFDouble mny = item.getNorigmny();
      if (MathTool.compareTo(mny, UFDouble.ZERO_DBL) > 0) {
        return true;
      }
    }
    return false;
  }

}
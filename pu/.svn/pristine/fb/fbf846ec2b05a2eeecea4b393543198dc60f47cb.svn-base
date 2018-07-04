package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.scmpub.payterm.AbstractRecvPayPlan;
import nc.impl.scmpub.payterm.PayPlan;
import nc.itf.pu.m21.IOrderPayPlanData;
import nc.itf.pu.m21.IOrderPaymentQuery;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.lang.ArrayUtils;

public class OrderPayPlan<E extends PayPlanVO, T extends IOrderPayPlanData>
    extends PayPlan<E, T> {
  private Class<E> planclazz;

  private T[] plandata;

  public OrderPayPlan(Class<E> planclazz, T[] plandata, Class<?> classType) {
    super(planclazz, plandata, classType);
    this.plandata = plandata;
    this.planclazz = planclazz;
  }

  @Override
  public List<E[]> getPlan() {
    List<E[]> plans = new ArrayList<E[]>();
    for (int i = 0; i < this.plandata.length; ++i) {
      T data = this.plandata[i];
      List<E> planlst = new ArrayList<E>();
      OrderPaymentVO[] vos = data.getPaymentVOs();
      if (ArrayUtils.isEmpty(vos)) {
        this.splitWhenNoPT(data, planlst);
      }
      this.splitByPtch(data, vos, planlst);
      // List<E> planlst = this.splitPlan(data, ptMap);//
      // 按收付款协议进行拆分每一个单据数据
      plans.add(new ListToArrayTool<E>(this.planclazz).convertToArray(planlst));
    }
    return plans;
  }

  @Override
  public Map<String, SuperVO> queryPaymentVO(String[] ids,
      Class<?> payOrRecvClassType) {
    Map<String, SuperVO> retMap = new HashMap<String, SuperVO>();
    IOrderPaymentQuery paymentQuery =
        NCLocator.getInstance().lookup(IOrderPaymentQuery.class);
    OrderPaymentVO[] vos = null;
    try {
      vos = paymentQuery.queryOrderPaymentByIds(ids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (ArrayUtils.isEmpty(vos)) {
      return retMap;
    }
    for (SuperVO vo : vos) {
      retMap.put(vo.getPrimaryKey(), vo);
    }
    return retMap;
  }

  private void splitByPtch(T data, SuperVO[] ptchvos, List<E> planlst) {
    String pk_curr = data.getCorigcurrencyid();
    int digit =
        new ScaleObjectFactory.CurrtypeScaleObject(2, 4).getDigit(pk_curr);
    UFDouble totalmny = MathTool.nvl(data.getNtotalorigmny());
    UFDouble accmny = UFDouble.ZERO_DBL;
    for (int i = 0; i < ptchvos.length; ++i) {
      SuperVO ptchvo = ptchvos[i];
      E plan = Constructor.construct(this.planclazz);
      // 账期号
      plan.setIaccounttermno(this.getShoworder(ptchvo));
      // 付款协议账期id
      plan.setPk_paytermch(ptchvo.getPrimaryKey());
      // 原币币种
      plan.setCorigcurrencyid(pk_curr);
      // 起算依据
      String effectdata = this.getPk_period(ptchvo);
      plan.setFeffdatetype(effectdata);
      // 起算日期
      UFDate dbegindate = data.getFeffdatetype(effectdata);
      // 起效日期延迟天数
      Integer effectdateadddate =
          (Integer) ptchvo.getAttributeValue(PaymentChVO.EFFECTDATEADDDATE);
      if (dbegindate != null && effectdateadddate != null) {
        dbegindate = dbegindate.getDateAfter(effectdateadddate.intValue());
      }
      plan.setDbegindate(dbegindate);

      // 账期天数
      Integer paymentday = this.getPaymentday(ptchvo);
      plan.setIitermdays(paymentday);

      /** 账期到期日 **********************************/
      if (dbegindate != null) {
        UFDate denddate = null;
        // 固定结账日
        Integer checkdata =
            ValueUtils.getInteger(ptchvo
                .getAttributeValue(PaymentChVO.CHECKDATA));
        // 固定结账日模式
        if (checkdata != null) {
          Integer effectaddmonth =
              ValueUtils.getInteger(ptchvo
                  .getAttributeValue(PaymentChVO.EFFECTADDMONTH));
          int ieffectaddmonth = 0;
          if (effectaddmonth != null) {
            ieffectaddmonth = effectaddmonth.intValue();
          }
          // 生效月方式
          String effectmonth =
              ValueUtils.getString(ptchvo
                  .getAttributeValue(PaymentChVO.EFFECTMONTH));
          int year = dbegindate.getYear();
          int month = dbegindate.getMonth();
          int day = dbegindate.getDay();
          Calendar carlendar;
          // 当月生效
          if (effectmonth.equals("0")) {
            // 如果固定结账日为0，则表示账期起算日当天
            if (Integer.valueOf(0).equals(checkdata)) {
              carlendar =
                  new GregorianCalendar(year, month - 1 + ieffectaddmonth, day,
                      0, 0, 0);
            }
            else if (day <= checkdata.intValue()) {
              carlendar =
                  new GregorianCalendar(year, month - 1 + ieffectaddmonth,
                      checkdata.intValue(), 0, 0, 0);
            }
            else {
              carlendar =
                  new GregorianCalendar(year, month + ieffectaddmonth,
                      checkdata.intValue(), 0, 0, 0);
            }
            denddate = UFDate.getDate(carlendar.getTime());
          }
          // 下月生效
          else if (effectmonth.equals("1")) {
            // 如果固定结账日为0，则表示账期起算日当天
            if (Integer.valueOf(0).equals(checkdata)) {
              carlendar =
                  new GregorianCalendar(year, month + ieffectaddmonth, day, 0,
                      0, 0);
            }
            else {
              carlendar =
                  new GregorianCalendar(year, month + ieffectaddmonth,
                      checkdata.intValue(), 0, 0, 0);
            }
            denddate = UFDate.getDate(carlendar.getTime());
          }
        }
        // 账期天数模式
        else if (paymentday != null) {
          denddate = dbegindate.getDateAfter(paymentday.intValue());
        }
        plan.setDenddate(denddate);
      }
      /** 账期到期日 **********************************/

      // 预付款
      plan.setBpreflag(this.getPrepayment(ptchvo));

      plan.setNtotalorigmny(totalmny);
      // 比率
      UFDouble rate = (UFDouble) ptchvo.getAttributeValue(PaymentChVO.ACCRATE);
      plan.setNrate(rate);
      UFDouble mny =
          totalmny.multiply(
              MathTool.nvl(rate).div(AbstractRecvPayPlan.UF100,
                  UFDouble.DEFAULT_POWER), digit);
      if (i < ptchvos.length - 1) {
        // 金额
        plan.setNorigmny(mny);
        accmny = accmny.add(mny);
      }
      else {// 倒挤
        // 金额
        plan.setNorigmny(totalmny.sub(accmny));
        plan.setLastAccoutTerm(true);
      }
      // 序号
      plan.setCrowno(String.valueOf(10 * (i + 1)));
      plan.setIsdeposit((UFBoolean) ptchvo
          .getAttributeValue(OrderPaymentVO.ISDEPOSIT));
      this.setPlanInfo(data, plan, null, ptchvo, planlst);// 给子类设置计划信息的机会
      planlst.add(plan);
    }
  }

  private void splitWhenNoPT(T data, List<E> planlst) {
    E plan = Constructor.construct(this.planclazz);
    plan.setPk_payterm(null);
    plan.setNorigmny(data.getNtotalorigmny());
    plan.setNtotalorigmny(data.getNtotalorigmny());
    plan.setNrate(new UFDouble(100));
    plan.setCrowno(String.valueOf(10));
    plan.setLastAccoutTerm(true);
    plan.setBpreflag(UFBoolean.FALSE);
    plan.setCorigcurrencyid(data.getCorigcurrencyid());
    this.setPlanInfo(data, plan, null, null, planlst);// 给子类设置计划信息的机会
    planlst.add(plan);
  }
}

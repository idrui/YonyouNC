/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午04:28:36
 */
package nc.bs.pu.m21.writeback.arap.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              采购订单计算表头和表体的累计金额
 * @scene
 *        付款单回写
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:07:58
 * @author luojw
 */
public class AccMnyCalcRule implements IRule<PayPlanViewVO> {

  private IOrderWriteBackParaForF3[] wbVos;

  public AccMnyCalcRule(IOrderWriteBackParaForF3[] wbVos) {
    this.wbVos = wbVos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PayPlanViewVO[] views) {
    Map<String, PayPlanViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaForF3 vo : this.wbVos) {
      PayPlanViewVO view = viewMap.get(vo.getBID());
      // 回写累计付款金额
      this.setPayOrgMny(vo, view);
      // 回写累计本币付款金额
      this.setPayMny(vo, view);
    }
  }

  /**
   * 方法功能描述：回写累计本币付款金额
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param view
   * @param orgCurrencyMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-30 下午06:58:05
   */
  private void setPayMny(IOrderWriteBackParaForF3 vo, PayPlanViewVO view) {
    String currOfPsfinanceorg = view.getCcurrencyid();
    String currOfApfinanceorg = null;
    currOfApfinanceorg =
        OrgUnitPubService.queryOrgCurrByPk(view.getPk_financeorg());

    UFDouble diffPaymny = null;
    if (ObjectUtils.equals(currOfPsfinanceorg, currOfApfinanceorg)) {
      diffPaymny = vo.getDiffPaymny();
    }
    else {
      String currency = vo.getCurrency();
      UFDouble rate =
          CurrencyRate.getCurrencyRateByOrg(view.getPk_financeorg(), currency,
              currOfPsfinanceorg, vo.getBilldate());

      diffPaymny =
          AmountCalculator.calAmountByDateRate(vo.getDiffPayorgmny(), currency,
              currOfPsfinanceorg, vo.getBilldate().toString(), rate,
              view.getPk_financeorg());
    }

    // 累计本币付款金额
    UFDouble newDiffPaymny = MathTool.add(view.getNaccumpaymny(), diffPaymny);
    view.setNaccumpaymny(newDiffPaymny);
  }

  /**
   * 方法功能描述：回写累计付款金额和累计付款总额
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param view
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-30 下午06:58:00
   */
  private void setPayOrgMny(IOrderWriteBackParaForF3 vo, PayPlanViewVO view) {

    // 订单原币币种
    String corigcurrencyid = view.getCorigcurrencyid();
    // 付款单原币币种
    String cpaybillorigcurrencyid = vo.getCurrency();

    UFDouble diffPayorgmny = null;

    if (ObjectUtils.equals(corigcurrencyid, cpaybillorigcurrencyid)) {
      diffPayorgmny = vo.getDiffPayorgmny();
    }
    else {
      UFDouble rate =
          CurrencyRate.getCurrencyRateByOrg(view.getPk_financeorg(),
              cpaybillorigcurrencyid, corigcurrencyid, vo.getBilldate());

      diffPayorgmny =
          AmountCalculator.calAmountByDateRate(vo.getDiffPayorgmny(),
              cpaybillorigcurrencyid, corigcurrencyid, vo.getBilldate()
                  .toString(), rate, view.getPk_financeorg());
    }

    // 累计付款金额
    UFDouble newDiffPayorgmny =
        MathTool.add(view.getNaccumpayorgmny(), diffPayorgmny);
    view.setNaccumpayorgmny(newDiffPayorgmny);
  }

}

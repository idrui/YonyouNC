/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午04:03:47
 */
package nc.bs.pu.m21.writeback.arap.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.pu.m21.arap.mf1.IOrderWriteBackParaForF1;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-8 下午04:03:47
 */
public class AccCancelInvMnyCalcRule implements IRule<OrderViewVO> {

  private IOrderWriteBackParaForF1[] wbVos;

  public AccCancelInvMnyCalcRule(IOrderWriteBackParaForF1[] wbVos) {
    this.wbVos = wbVos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    Map<String, String> orgCurrencyMap = this.getOrgCurrencyMap(views);
    for (IOrderWriteBackParaForF1 wbVo : this.wbVos) {
      OrderViewVO view = viewMap.get(wbVo.getBID());
      if (view != null) {
        this.setNacccancelinvmny(view, wbVo, orgCurrencyMap);
      }
    }
  }

  /**
   * 方法功能描述：组织和本位币的映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param views
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-30 下午06:45:40
   */
  private Map<String, String> getOrgCurrencyMap(OrderViewVO[] views) {
    Set<String> orgs = new HashSet<String>();
    for (OrderViewVO view : views) {
      String pk_psfinanceorg = view.getPk_psfinanceorg();
      String pk_apfinanceorg = view.getPk_apfinanceorg();
      orgs.add(pk_psfinanceorg);
      orgs.add(pk_apfinanceorg);
    }

    if (orgs.isEmpty()) {
      return null;
    }

    return OrgUnitPubService.queryOrgCurrByPk(orgs.toArray(new String[0]));

  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param view
   * @param wbVo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-8 下午04:16:31
   */
  private void setNacccancelinvmny(OrderViewVO view,
      IOrderWriteBackParaForF1 wbVo, Map<String, String> orgCurrencyMap) {
    String currOfPsfinanceorg = orgCurrencyMap.get(view.getPk_psfinanceorg());
    String currOfApfinanceorg = orgCurrencyMap.get(view.getPk_apfinanceorg());
    UFDouble diffMny = null;

    if (ObjectUtils.equals(currOfPsfinanceorg, currOfApfinanceorg)) {
      diffMny = wbVo.getDiffMny();
    }
    else {
      String currency = wbVo.getCurrency();
      UFDouble rate =
          CurrencyRate.getCurrencyRateByOrg(view.getPk_psfinanceorg(),
              currency, currOfPsfinanceorg, wbVo.getBillDate());
      diffMny =
          AmountCalculator.calAmountByDateRate(wbVo.getDiffOrgMny(), currency,
              currOfPsfinanceorg, wbVo.getBillDate().toString(), rate,
              view.getPk_psfinanceorg());
    }

    UFDouble newMny = MathTool.add(view.getNacccancelinvmny(), diffMny);
    view.setNacccancelinvmny(newMny);
  }
}

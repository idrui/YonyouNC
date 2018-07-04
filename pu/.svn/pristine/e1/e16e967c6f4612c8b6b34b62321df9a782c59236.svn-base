/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 上午09:33:41
 */
package nc.bs.pu.est.rule;

import java.util.List;

import nc.bs.pu.est.rule.pricequery.AbstractEstPriceQueryStrategy;
import nc.bs.pu.est.rule.pricequery.EstCostPlanPriceQryStrategy;
import nc.bs.pu.est.rule.pricequery.EstPriceQryContext;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.pub.entity.PO27VO;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的询价顶层规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 上午09:33:41
 */
public abstract class GoodsEstPriceQueryRule {

  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    MapList<String, GoodsEstVO> orgMap = this.splitOrgMap(vos);
    for (String pk_fiorg : orgMap.keySet()) {
      PO27VO po27 = PUSysParamUtil.getPO27(pk_fiorg);
      if (null == po27) {
        continue;
      }
      List<GoodsEstVO> heads = orgMap.get(pk_fiorg);
      if (ListUtil.isEmpty(heads)) {
        continue;
      }
      GoodsEstVO[] gevos =
          new ListToArrayTool<GoodsEstVO>().convertToArray(heads);
      EstPriceQryContext context = new EstPriceQryContext(gevos);
      this.queryPlanPrice(po27, context);// 先处理计划价物料按计划价暂估
      for (PriceSource ps : po27.getPs()) {
        if (context.isComplete()) {
          break;
        }
        AbstractEstPriceQueryStrategy strategy = this.getPriceQryStrategy(ps);
        context.setStrategy(strategy);
        context.process();
      }
    }
  }

  private void queryPlanPrice(PO27VO po27, EstPriceQryContext context) {
    if (UFBoolean.FALSE.equals(po27.getBplanprior())) {
      return;
    }
    // 如果选了按计划价暂估，则先处理计划价物料的询价
    AbstractEstPriceQueryStrategy strategy = new EstCostPlanPriceQryStrategy();
    context.setStrategy(strategy);
    context.process();
  }

  private MapList<String, GoodsEstVO> splitOrgMap(EstVO[] vos) {
    MapList<String, GoodsEstVO> orgMap = new MapList<String, GoodsEstVO>();
    for (EstVO vo : vos) {
      orgMap.put(vo.getParentVO().getPk_financeorg(), vo.getParentVO());
    }
    return orgMap;
  }

  /**
   * 方法功能描述：根据参数得到暂估询价具体策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param ps PO27询价参数
   * @return 询价策略，可以为null
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-16 下午02:07:41
   */
  protected abstract AbstractEstPriceQueryStrategy getPriceQryStrategy(
      PriceSource ps);
}

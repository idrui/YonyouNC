/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午03:40:53
 */
package nc.pubimpl.pu.est.m45.action;

import nc.bs.pu.est.m45.PurchsInEstBP;
import nc.bs.pu.est.m45.rule.AutoEstFlagFillRule;
import nc.bs.pu.est.m45.rule.DefaultGoodsEstInfoSetRule;
import nc.bs.pu.est.m45.rule.PurchsInEstPriceQryRule;
import nc.bs.pu.est.m45.rule.PurchsInGoodsEstNecssFillRule;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单签字自动暂估组件的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-25 下午03:40:53
 */
public class PurchsInAutoEstAction {
  public void estimate(PurchaseInVO[] vos) {
    String[] bids =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            MetaNameConst.CGENERALBID, String.class);
    ViewQuery<PurchaseInEstHeaderVO> queryer =
        new ViewQuery<PurchaseInEstHeaderVO>(PurchaseInEstHeaderVO.class);
    PurchaseInEstHeaderVO[] heads = queryer.query(bids);
    if (ArrayUtils.isEmpty(heads)) {
      return;
    }
    AroundProcesser<PurchaseInEstVO> prcr =
        new AroundProcesser<PurchaseInEstVO>(PurchsInEstPluginPoint.AUTO_EST);
    this.addRule(prcr);
    PurchaseInEstVO[] estVos = this.getEstVOs(heads);
    prcr.before(estVos);
    new PurchsInEstBP().autoEstimate(estVos);// 调用BP暂估
    prcr.after(estVos);

  }

  private void addRule(AroundProcesser<PurchaseInEstVO> prcr) {
    prcr.addBeforeFinalRule(new PurchsInGoodsEstNecssFillRule());// 暂估前必须信息填充
    prcr.addBeforeFinalRule(new DefaultGoodsEstInfoSetRule());// 默认暂估信息填充
    prcr.addBeforeFinalRule(new AutoEstFlagFillRule());// 填入自动暂估标志到表头
    prcr.addBeforeFinalRule(new PurchsInEstPriceQryRule());// 暂估询价

  }

  private PurchaseInEstVO[] getEstVOs(PurchaseInEstHeaderVO[] heads) {
    PurchaseInEstVO[] vos = new PurchaseInEstVO[heads.length];
    for (int i = 0; i < vos.length; i++) {
      PurchaseInEstVO vo = new PurchaseInEstVO();
      vo.setParentVO(heads[i]);
      vos[i] = vo;
    }
    return vos;
  }

}

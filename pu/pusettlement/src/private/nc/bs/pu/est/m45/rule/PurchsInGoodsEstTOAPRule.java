/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-18 下午03:04:22
 */
package nc.bs.pu.est.m45.rule;

import nc.bs.pu.est.rule.AbstractGoodsEstTOAPRule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.vo.arap.estipayable.AggEstiPayableBillVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.ToApIaDataProcessor;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.scmpub.util.CombineViewToAggUtil;

/**
 * 
 * @description
 * 采购入暂估货物暂估应付规则
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:44:34
 * @author zhangshqb
 */
public class PurchsInGoodsEstTOAPRule extends AbstractGoodsEstTOAPRule
    implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    super.process(vos);
  }

  private PurchaseinFIVO[] getFiVOs(PurchaseInEstHeaderVO[] heads) {
    CombineViewToAggUtil<PurchaseinFIVO> util =
        new CombineViewToAggUtil<PurchaseinFIVO>(PurchaseinFIVO.class,
            PurchaseinFIHeaderVO.class, PurchaseinFIItemVO.class);
    PurchaseinFIVO[] fiVos =
        util.combineViewToAgg(heads, PurchaseinFIHeaderVO.PK_STOCKPS);
    return fiVos;
  }

  @Override
  protected void estTOAP(GoodsEstVO[] gevos) {
    PurchaseinFIVO[] fiVos = this.getFiVOs((PurchaseInEstHeaderVO[]) gevos);
    ToApIaDataProcessor processor = new ToApIaDataProcessor();
    // 开票供应商替换
    processor.fillDataByOrder(fiVos);
    AggEstiPayableBillVO[] payVos =
        (AggEstiPayableBillVO[]) EstFIBillFactory.getPurchsInGoodsEstPayGen(
            fiVos).genBill();
    // 还原供应商
    processor.resetData(fiVos);
    ArapServicesForPUUtil.estPayable(payVos);
  }
}

/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-18 下午01:47:01
 */
package nc.bs.pu.est.m45.rule;

import nc.bs.pu.est.rule.AbstractGoodsEstTOIARule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.scmpub.util.CombineViewToAggUtil;

/**
 * 
 * @description
 * 采购入库单货物暂估传IA
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:44:13
 * @author zhangshqb
 */
public class PurchsInGoodsEstTOIARule extends AbstractGoodsEstTOIARule
    implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    super.process(vos);
  }

  @Override
  protected void sendTOIA(EstVO[] estVos) {
    GoodsEstVO[] views = EstVOUtil.getGoodsEstVos(estVos);
    // ViewToBillConvertor<GoodsEstVO, PurchaseinFIVO> util =
    // new ViewToBillConvertor<GoodsEstVO, PurchaseinFIVO>(
    // PurchaseinFIVO.class);
    // PurchaseinFIVO[] fiVos = util.convert(views);
    CombineViewToAggUtil<PurchaseinFIVO> util =
        new CombineViewToAggUtil<PurchaseinFIVO>(PurchaseinFIVO.class,
            PurchaseinFIHeaderVO.class, PurchaseinFIItemVO.class);
    PurchaseinFIVO[] fiVos =
        util.combineViewToAgg(views, PurchaseinFIHeaderVO.PK_STOCKPS);
    I2BillVO[] I2Vos =
        EstFIBillFactory.getPurchsInGoodsEstI2Gen(fiVos).genBill();
    IAForEstConfirmServices.estimate(I2Vos);
    // // mengjian by 20141021 启用利润中心存货核算时
    // if (SysInitGroupQuery.isPCIAEnabled()) {
    // PCIAForEstConfirmServices.estimate(fiVos);
    // }
  }

}

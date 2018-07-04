package nc.bs.pu.est.m45.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.it.est.rule.ImportInEstPriceQryRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;

/**
 * 
 * @description
 *            来源于进出口的单据，进口暂估询价
 * @scene
 *      暂估询价
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-29 下午2:04:27
 * @author luojw
 */
public class PUImportInEstPriceQryRule implements IRule<PurchaseInEstVO>{

  @Override
  public void process(PurchaseInEstVO[] vos) {
    // 这个类不是供应链的，封装一层，不让它报错
    new ImportInEstPriceQryRule().process(vos);
  }

}

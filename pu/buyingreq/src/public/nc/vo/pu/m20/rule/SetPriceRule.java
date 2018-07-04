package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置主本币含税单价
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:10:12
 * @author yanxm5
 */
public class SetPriceRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PraybillVO vo : vos) {
      new SetPrice().setPrice(new BillHelper<PraybillVO>(vo));
      RelationCalculate cal = new RelationCalculate();
      cal.calculate(vo, PraybillItemVO.NTAXPRICE);
    }
  }
}

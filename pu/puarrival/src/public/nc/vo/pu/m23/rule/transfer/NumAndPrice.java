package nc.vo.pu.m23.rule.transfer;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.RelationCalculate;

/**
 * <p>
 * <b>参照订单转单后续处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理到货数量、单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-19 下午03:34:29
 */
public class NumAndPrice {

  private ArriveVO[] aggVOArray;

  public NumAndPrice(ArriveVO[] retVOArray) {
    this.aggVOArray = retVOArray;
  }

  public ArriveVO[] dealNumAndPrice() {
    for (ArriveVO vo : this.aggVOArray) {
      RelationCalculate calc = new RelationCalculate();
      calc.calcaulate(vo, ArriveItemVO.NNUM);
    }
    return this.aggVOArray;
  }
}

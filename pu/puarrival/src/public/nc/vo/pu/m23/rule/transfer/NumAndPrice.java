package nc.vo.pu.m23.rule.transfer;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.RelationCalculate;

/**
 * <p>
 * <b>���ն���ת�����������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-19 ����03:34:29
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

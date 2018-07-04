package nc.vo.pu.m23.rule.transfer;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ε�������Ʒ�����Ʒ��������Ʒ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-29 ����10:21:45
 */
public class FillPresentNum {
  private ArriveVO[] aggVOArray;

  public FillPresentNum(ArriveVO[] retVOArray) {
    this.aggVOArray = retVOArray;
  }

  public ArriveVO[] fill() {
    ArriveItemVO[] itemVO = null;
    for (ArriveVO vo : this.aggVOArray) {
      itemVO = vo.getBVO();
      for (int i = 0, len = itemVO.length; i < len; i++) {
        if ((itemVO[i].getBpresentsource() == null)
            || !itemVO[i].getBpresentsource().booleanValue()) {
          continue;
        }
        UFDouble nnum = itemVO[i].getNnum();
        UFDouble nastnum = itemVO[i].getNastnum();
        itemVO[i].setNpresentnum(nnum);
        itemVO[i].setNpresentastnum(nastnum);
      }
    }
    return this.aggVOArray;
  }
}

package nc.impl.pu.m23.qc.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @description
 *清空相应行的累计报检数量、累计合格数量、累计不合格数量
 * @scene
 * 到货单反检
 * @param
 * 无
 *
 * @since 6.3
 * @version 2011-4-22 下午06:30:06
 * @author liuchx
 */

public class ClearAccNnumRule implements IRule<ArriveVO> {


  @Override
  public void process(ArriveVO[] vos) {
    for (ArriveVO bill : vos) {
      ArriveItemVO[] children = bill.getBVO();
      for (ArriveItemVO child : children) {
        String[] clearKeys =
            {
              ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NELIGNUM,
              ArriveItemVO.NNOTELIGNUM
            };

        this.clearValue(child, clearKeys);
      }

    }
  }
  /**
   * 清空相应行的累计报检数量、累计合格数量、累计不合格数量
   */
  private void clearValue(ArriveItemVO child, String[] clearKeys) {
    for (String key : clearKeys) {
      child.setAttributeValue(key, UFDouble.ZERO_DBL);
    }
  }

}

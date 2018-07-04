/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午06:37:47
 */
package nc.bs.pu.est.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 清除传暂估成本和应付的信息
 * @scene
 * 取消暂估-BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:51:35
 * @author zhangshqb
 */
public class GoodsEstInfoClearRule implements IRule<EstVO> {

  @Override
  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (EstVO estVO : vos) {
      this.clearGoodsEstInfo(estVO.getParentVO());
    }
  }

  private void clearGoodsEstInfo(GoodsEstVO vo) {
    vo.setNestnum(UFDouble.ZERO_DBL);
    vo.setFtoapflag((Integer) EnumToAPFlag.NotToAP.value());
    vo.setFtoiaflag((Integer) EnumToIAFlag.NotToIA.value());
  }
}

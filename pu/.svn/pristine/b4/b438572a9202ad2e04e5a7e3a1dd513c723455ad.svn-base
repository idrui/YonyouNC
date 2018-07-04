/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 下午04:53:36
 */
package nc.bs.pu.m20.rewrite.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m20.entity.writeback.OrderWriteBackVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              请购单计算累计数量
 * @scene
 *        请购单回写订单数量
 * @param
 *        OrderWriteBackVO[] wbVos 回写参数VO
 * @since 6.3
 * @version 2014-10-21 上午10:19:56
 * @author yanxm5
 */
public class AccOrderNumCalcRule implements IRule<PraybillViewVO> {

  /** 回写参数VO **/
  private final OrderWriteBackVO[] wbVos;

  public AccOrderNumCalcRule(OrderWriteBackVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(PraybillViewVO[] views) {
    Map<String, PraybillViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (OrderWriteBackVO vo : this.wbVos) {
      PraybillViewVO view = viewMap.get(vo.getPk_praybill_b());
      UFDouble diffNum = vo.getDiffNum();
      UFDouble newAccNum = MathTool.add(view.getNaccumulatenum(), diffNum);
      if (newAccNum.compareTo(UFDouble.ZERO_DBL) < 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0118")/*
                                                                     * @res
                                                                     * "来源于请购单的采购订单，数量不允许为负。"
                                                                     */);
      }
      view.setNaccumulatenum(newAccNum);
    }
  }

}

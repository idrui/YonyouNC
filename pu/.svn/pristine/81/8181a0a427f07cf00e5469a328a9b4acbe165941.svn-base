package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 结算单vo检查
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:13:49
 * @author zhangshqb
 */
public class SettleBillVOCheckRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    for (SettleBillVO vo : vos) {
      this.check(vo);
    }
  }

  private void check(SettleBillVO vo) {
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      if (MathTool.greaterThan(UFDouble.ZERO_DBL, item.getNprice())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0077")/*@res "结算单单价不允许为负！"*/);
      }
    }
  }

}
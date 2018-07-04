package nc.bs.pu.m422x.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            物资需求申请单保存时，数量不能为0或负值
 * @scene
 *       物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2011-3-24 下午12:15:19
 * @author wuxla
 */
public class NastumGtZeroRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (StoreReqAppVO vo : vos) {
      for (StoreReqAppItemVO itemVO : vo.getBVO()) {
        if (MathTool.compareTo(itemVO.getNastnum(), UFDouble.ZERO_DBL) <= 0
            || MathTool.compareTo(itemVO.getNnum(), UFDouble.ZERO_DBL) <= 0) {
          sb.append(NCLangResOnserver.getInstance().getStrByID("4004010_0",
              "04004010-0017", null, new String[] {
                itemVO.getCrowno()
              })/* 第{0}行主数量和数量均不能为0或者负值\n */);
        }
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }
}

package nc.bs.pu.m4t.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *单据号回退处理：调用公共代码处理
 * @scene
 * 期初暂估单删除
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午10:39:56
 * @author wuxla
 */
public class InitialEstCodeReturnRule implements ICompareRule<InitialEstVO> {
  @Override
  public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
    if (null != originVOs) {
      PUBillCodeUtils.getInitialEstCode().returnBillCode(originVOs);
    }
  }

}

package nc.impl.pu.m21.action.rule.revise;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查本次修订日期不能小于上次修订日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-21 上午10:49:34
 */
public class LastReviseDateCheckRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < vos.length; ++i) {
      UFDate dNewTime = vos[i].getHVO().getTrevisiontime();
      UFDate dOldTime = originVOs[i].getHVO().getTrevisiontime();

      if (TimeUtils.before(dNewTime, dOldTime)) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0287", null, new String[]{vos[i].getHVO().getVbillcode()})/*订单{0}：“修订日期”不能早于上一次“修订日期”\n*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}

/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午03:58:30
 */
package nc.bs.pu.m21.writeback.arap;

import nc.bs.pu.m21.writeback.arap.rule.AccCancelInvMnyCalcRule;
import nc.bs.pu.m21.writeback.arap.rule.AutoPayCloseRule;
import nc.bs.pu.m21.writeback.arap.rule.PayCloseChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.arap.mf1.IOrderWriteBackParaForF1;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-8 下午03:58:30
 */
public class OrderWriteBackForF1BP {

  public void writeback(IOrderWriteBackParaForF1[] wbVos) {
    String[] bids = OrderVOUtil.getInsance().getBIDs(wbVos);
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<OrderViewVO> processer =
        new AroundProcesser<OrderViewVO>(null);
    this.addRule(processer, wbVos);

    processer.before(views);
    String[] itemNames = new String[] {
      OrderItemVO.NACCCANCELINVMNY
    };

    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    views = bo.update(views, OrderItemVO.class, itemNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<OrderViewVO> processer,
      IOrderWriteBackParaForF1[] vos) {
    processer.addBeforeRule(new PayCloseChkRule(vos));
    processer.addBeforeRule(new AccCancelInvMnyCalcRule(vos));
    processer.addAfterRule(new AutoPayCloseRule());
  }
}

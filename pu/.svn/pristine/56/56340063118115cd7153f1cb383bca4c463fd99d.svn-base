/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 下午09:03:19
 */
package nc.bs.pu.m21.writeback.pu;

import nc.bs.pu.m21.writeback.pu.rule.AccInvoiceMnyChkRule;
import nc.bs.pu.m21.writeback.pu.rule.AccInvoiceNumChkRule;
import nc.bs.pu.m21.writeback.pu.rule.AccInvoiceNumMnyCalcRule;
import nc.bs.pu.m21.writeback.pu.rule.AutoInvoiceCloseRule;
import nc.bs.pu.m21.writeback.pu.rule.InvoiceCloseChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购发票提供的回写服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 下午09:03:19
 */
public class OrderWriteBackFor25BP {

  public void writeback(IOrderWriteBackParaFor25[] wbVos) {
    if (ArrayUtils.isEmpty(wbVos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(wbVos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    OrderViewVO[] orgViews = new OrderViewVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      orgViews[i] = (OrderViewVO) views[i].clone();
    }

    CompareAroundProcesser<OrderViewVO> processer =
        new CompareAroundProcesser<OrderViewVO>(null);
    this.addRule(processer, wbVos, orgViews);

    processer.before(views, orgViews);
    processer.after(views, orgViews);
  }

  private void addRule(CompareAroundProcesser<OrderViewVO> processer,
      IOrderWriteBackParaFor25[] vos, OrderViewVO[] orgViews) {
    processer.addBeforeRule(new AccInvoiceNumMnyCalcRule(vos));// 计算累计开票数量和金额
    processer.addBeforeRule(new InvoiceCloseChkRule(orgViews));// 开票关闭检查
    processer.addAfterRule(new AccInvoiceNumChkRule());// 累计开票数量检查
    //processer.addAfterRule(new AccInvoiceMnyChkRule());// 累计开票金额检查(对于费用)
    processer.addAfterRule(new AutoInvoiceCloseRule());// 开票自动关闭
  }

}

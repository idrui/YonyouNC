/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 ����04:15:35
 */
package nc.bs.pu.m21.writeback.arap;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.arap.rule.AccMnyCalcRule;
import nc.bs.pu.m21.writeback.arap.rule.AccMnyChkRule;
import nc.bs.pu.m21.writeback.arap.rule.PrePayChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.rule.OrderPayCloseChkRule;
import nc.vo.pu.m21.rule.OrderPayFreezeChkRule;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ����/Ԥ�����д�ɹ����������BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-28 ����04:15:35
 */
public class OrderWriteBackForF3BP {

  /**
   * ������������������/Ԥ�����д�ɹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param wbVos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 ����04:17:14
   */
  public void writeback(IOrderWriteBackParaForF3[] wbVos) {
    String[] bids = OrderVOUtil.getInsance().getBIDs(wbVos);
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    PayPlanViewVO[] views =
        new ViewQuery<PayPlanViewVO>(PayPlanViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    // ����+�������У��
    ViewConcurrentTool tool = new ViewConcurrentTool();
    tool.lock(views);
    AroundProcesser<PayPlanViewVO> processer =
        new AroundProcesser<PayPlanViewVO>(OrderPluginPoint.WRITEBACK_F3);
    this.addRule(processer, wbVos);

    processer.before(views);

    String[] itemNames = new String[] {
      AbstractPayPlanVO.NACCUMPAYORGMNY, AbstractPayPlanVO.NACCUMPAYMNY
    };

    ViewUpdate<PayPlanViewVO> bo = new ViewUpdate<PayPlanViewVO>();
    views = bo.update(views, PayPlanVO.class, itemNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<PayPlanViewVO> processer,
      IOrderWriteBackParaForF3[] vos) {
    processer.addBeforeRule(new OrderPayCloseChkRule());
    processer.addBeforeRule(new OrderPayFreezeChkRule());
    // �����ۼƽ��
    processer.addBeforeRule(new AccMnyCalcRule(vos));
    // �����
    processer.addBeforeRule(new AccMnyChkRule());
    processer.addBeforeRule(new PrePayChkRule());
  }
}

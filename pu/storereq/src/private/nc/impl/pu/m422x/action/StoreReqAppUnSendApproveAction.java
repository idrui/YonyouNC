/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-22 ����08:45:21
 */
package nc.impl.pu.m422x.action;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m422x.action.rule.approve.UnSendApproveValidateRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ջ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-22 ����08:45:21
 */
public class StoreReqAppUnSendApproveAction {

  public StoreReqAppVO[] unSendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) {
    if (ArrayUtils.isEmpty(vos) || (null == script)) {
      return null;
    }

    PfParameterUtil<StoreReqAppVO> util =
        new PfParameterUtil<StoreReqAppVO>(script.getPfParameterVO(), vos);
    StoreReqAppVO[] originBills = util.getOrginBills();
    StoreReqAppVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<StoreReqAppVO> processer =
        new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.UNSENDAPPROVE);
    this.addRule(processer);

    processer.before(clientBills);
    // ����ƽ̨�ű������ջ�
    try {
      script.procRecallFlow(script.getPfParameterVO());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    BillUpdate<StoreReqAppVO> update = new BillUpdate<StoreReqAppVO>();
    StoreReqAppVO[] returnVos = update.update(clientBills, originBills);

    processer.after(returnVos);
    return returnVos;
  }

  private void addRule(AroundProcesser<StoreReqAppVO> processer) {
    // ״̬���
    processer.addBeforeRule(new UnSendApproveValidateRule());
  }
}

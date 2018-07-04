package nc.bs.pu.m25.ap.rule;

import nc.bs.businessevent.EventDispatcher;
import nc.bs.pu.event.PUBusinessEvent;
import nc.bs.pu.event.PUBusinessEventUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.event.IInvoiceEventType;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 传应付后事件处理
 * @scene
 * 传应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:45:06
 * @author zhangshqb
 */
public class SendAPAfterEventRuel implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (vos == null) {
      return;
    }
    InvoiceVO[] vosClone = this.cloneVOs(vos);
    try {
      String sourceid = PUBusinessEventUtil.getSourceIDByBillVO(vosClone);
      EventDispatcher.fireEvent(new PUBusinessEvent(sourceid,
          IInvoiceEventType.TYPE_SENDAP_AFTER, vosClone, null));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 复制派发事件中的VO 防止发生变化
   * 
   * @param vos
   * @return
   */
  private InvoiceVO[] cloneVOs(InvoiceVO[] vos) {
    InvoiceVO[] clonevos = new InvoiceVO[vos.length];
    for (int i = 0; i < vos.length; i++) {
      clonevos[i] = (InvoiceVO) vos[i].clone();
    }
    return clonevos;
  }
}

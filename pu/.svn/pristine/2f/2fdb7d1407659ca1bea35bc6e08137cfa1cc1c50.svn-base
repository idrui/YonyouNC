package nc.bs.pu.m25.maintain.rule.save;

import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.bs.pu.event.PUBusinessEvent;
import nc.bs.pu.event.PUBusinessEventUtil;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 保存前事件处理
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:33:53
 * @author zhangshqb
 */
public class InvcSaveBeforeEventRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    if (ArrayUtils.isEmpty(originVOs)) {
      InvoiceVO[] vosClone = this.cloneVOs(vos);
      this.insertBeforeEvent(vosClone);
    }
    else {
      InvoiceVO[] vosClone = this.cloneVOs(vos);
      InvoiceVO[] originVOsClone = this.cloneVOs(originVOs);
      this.updateBeforeEvent(vosClone, originVOsClone);
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

  private void insertBeforeEvent(InvoiceVO[] vos) {
    try {
      String sourceid = PUBusinessEventUtil.getSourceIDByBillVO(vos);
      EventDispatcher.fireEvent(new PUBusinessEvent(sourceid,
          IEventType.TYPE_INSERT_BEFORE, null, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void updateBeforeEvent(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    try {
      String sourceid = PUBusinessEventUtil.getSourceIDByBillVO(vos);
      EventDispatcher.fireEvent(new PUBusinessEvent(sourceid,
          IEventType.TYPE_UPDATE_BEFORE, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

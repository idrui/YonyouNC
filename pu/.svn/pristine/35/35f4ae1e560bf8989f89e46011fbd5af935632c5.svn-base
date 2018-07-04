package nc.bs.pu.m422x.maintain.rule.save;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单保存时，触发新增后或修改后事件
 * @scene
 *       物资需求申请单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-21 下午03:46:11
 * @author fanly3
 */
public class SaveAfterEventRule implements ICompareRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    if (ArrayUtils.isEmpty(originVOs)) {
      this.insertAfterEvent(vos);
    }
    else {
      this.updateAfterEvent(vos, originVOs);
    }

  }

  private void insertAfterEvent(StoreReqAppVO[] vos) {
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_INSERT_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void updateAfterEvent(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BdUpdateEvent(sourceid,
          IEventType.TYPE_UPDATE_AFTER, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}

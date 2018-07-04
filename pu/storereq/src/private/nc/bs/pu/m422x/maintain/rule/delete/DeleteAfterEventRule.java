package nc.bs.pu.m422x.maintain.rule.delete;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            物资需求申请单删除时，触发删除后事件
 * @scene
 *      物资需求申请单删除时
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-21 下午04:17:10
 * @author fanly3
 */
public class DeleteAfterEventRule implements ICompareRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_AFTER, originVOs));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

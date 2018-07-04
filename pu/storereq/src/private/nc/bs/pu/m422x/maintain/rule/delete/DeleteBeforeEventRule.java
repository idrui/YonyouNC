package nc.bs.pu.m422x.maintain.rule.delete;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单删除时，物资需求申请单触发删除前事件
 * @scene
 *      物资需求申请单删除
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-21 下午04:11:38
 * @author fanly3
 */
public class DeleteBeforeEventRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}

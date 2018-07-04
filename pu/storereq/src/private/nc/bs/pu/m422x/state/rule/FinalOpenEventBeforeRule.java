package nc.bs.pu.m422x.state.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.event.IStoreReqAppEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单触发整单打开前事件
 * @scene
 *      物资需求申请单整单打开
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-22 上午11:28:34
 * @author fanly3
 */
public class FinalOpenEventBeforeRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IStoreReqAppEventType.TYPE_OPEN_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

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
 *            �����������뵥�����ر�ʱ�����������ر�ǰ�¼�
 * @scene
 *      �����������뵥�����ر�
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-22 ����11:23:34
 * @author fanly3
 */
public class FinalCloseEventBeforeRule implements IRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IStoreReqAppEventType.TYPE_CLOSE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

package nc.bs.pu.m422x.state.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.event.IStoreReqAppEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单关闭时，触发行关闭前事件
 * @scene
 *      物资需求申请单关闭
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-22 上午11:06:22
 * @author fanly3
 */
public class RowCloseEventBeforeRule implements IRule<StoreReqAppCloseVO> {

  @Override
  public void process(StoreReqAppCloseVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.StoreReqApp.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IStoreReqAppEventType.TYPE_LINECLOSE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

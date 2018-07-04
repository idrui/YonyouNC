package nc.bs.pu.m23.maintain.rule.insert;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 触发新增后事件。
 * @scene
 * 到货单新增后
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-10-16 下午03:50:42
 * @author lixyp
 */

public class InsertAfterEventRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.Arrival.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_INSERT_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

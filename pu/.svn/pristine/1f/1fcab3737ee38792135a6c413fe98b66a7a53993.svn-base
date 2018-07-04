package nc.bs.pu.m23.maintain.rule.delete;

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
 * 删除前事件触发器
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2013-3-7 上午10:49:31
 * @author lixyp
 */

public class DeleteBeforeEventRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.Arrival.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

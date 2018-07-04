package nc.bs.pu.m23.maintain.rule.update;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 触发更新后事件。
 * @scene
 * 到货单修改保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-10-16 下午03:49:39
 * @author lixyp
 */

public class UpdateAfterEventRule implements ICompareRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVOs) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.Arrival.value();
      EventDispatcher.fireEvent(new BdUpdateEvent(sourceid,
          IEventType.TYPE_UPDATE_AFTER, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

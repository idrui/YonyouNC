package nc.bs.pu.m20.maintain.rule.update;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            请购单触发更新后事件
 * @scene
 *      请购单更新
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-14 上午09:53:44
 * @author fanly3
 */
public class UpdateAfterEventRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {
    try {
      String sourceid = PUMDValue.PrayBill.value();
      EventDispatcher.fireEvent(new BdUpdateEvent(sourceid,
          IEventType.TYPE_UPDATE_AFTER, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

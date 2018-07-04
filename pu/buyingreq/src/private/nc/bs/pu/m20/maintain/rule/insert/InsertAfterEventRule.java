package nc.bs.pu.m20.maintain.rule.insert;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            请购单新增，触发新增后事件
 * @scene
 *       请购单新增
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-14 上午09:41:59
 * @author fanly3
 */
public class InsertAfterEventRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.PrayBill.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_INSERT_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

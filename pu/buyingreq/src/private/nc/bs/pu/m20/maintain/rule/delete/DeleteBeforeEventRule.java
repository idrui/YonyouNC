package nc.bs.pu.m20.maintain.rule.delete;

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
 *            �빺��ɾ��ʱ������ɾ��ǰ�¼�
 * @scene
 *      �빺��ɾ��
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-1-14 ����10:03:42
 * @author fanly3
 */
public class DeleteBeforeEventRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }
      String sourceid = PUMDValue.PrayBill.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}

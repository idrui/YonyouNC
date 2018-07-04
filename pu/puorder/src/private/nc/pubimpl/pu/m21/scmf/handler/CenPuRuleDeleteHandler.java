package nc.pubimpl.pu.m21.scmf.handler;

import java.util.HashSet;
import java.util.Set;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.pu.m21.writeback.scmf.OrderWriteBackForScmf;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleItemVO;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleVO;

public class CenPuRuleDeleteHandler implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_DELETE_AFTER.equals(event.getEventType())) {
        BusinessEvent e = (BusinessEvent) event;
        Object value = e.getObject();
        if (null == value) {
          return;
        }
        CenPuRuleVO[] vos = (CenPuRuleVO[]) value;
        Set<String> set = new HashSet<String>();
        for (CenPuRuleVO vo : vos) {
          for (CenPuRuleItemVO itemVO : vo.getChildrenVO()) {
            set.add(itemVO.getPk_cenpurule_b());
          }
        }

        String[] bids = set.toArray(new String[set.size()]);
        new OrderWriteBackForScmf().udpateItemCenPuRuleB(bids);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}

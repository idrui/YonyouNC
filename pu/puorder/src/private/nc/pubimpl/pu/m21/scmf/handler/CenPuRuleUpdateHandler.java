package nc.pubimpl.pu.m21.scmf.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.pu.m21.writeback.scmf.OrderWriteBackForScmf;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.bill.BillRowCompare;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleItemVO;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleVO;

public class CenPuRuleUpdateHandler implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_UPDATE_AFTER.equals(event.getEventType())) {
        BdUpdateEvent e = (BdUpdateEvent) event;
        Object oldObject = e.getOldObject();
        Object newObject = e.getNewObject();
        if (null == oldObject && null == newObject) {
          return;
        }

        CenPuRuleVO[] originvos = (CenPuRuleVO[]) oldObject;
        CenPuRuleVO[] newvos = (CenPuRuleVO[]) newObject;
        BillRowCompare tool = new BillRowCompare(newvos, originvos);
        List<ISuperVO> deleteList = tool.getDeleteList();
        Set<String> set = new HashSet<String>();
        for (ISuperVO vo : deleteList) {
          set.add(((CenPuRuleItemVO) vo).getPk_cenpurule_b());
        }
        if (set.size() == 0) {
          return;
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

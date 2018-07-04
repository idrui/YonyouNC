package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.List;
import java.util.Map;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.cmp.m36d1.OrderPayPlanWriteBackParaFor36D1;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * ��������ƻ����ʽ𸶿����뵥�ṩ���޸ĺ��¼���д����������
 * 
 * @since 6.3.1
 * @version 2013-10-10 ����10:48:09
 * @author fanly3
 */
public class OrderPayPlanWriteBackFor36D1UpdateHandler implements
    IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (!SysInitGroupQuery.isPOEnabled()) {
      return;
    }
    if (IEventType.TYPE_UPDATE_AFTER.equals(event.getEventType())) {
      BdUpdateEvent e = (BdUpdateEvent) event;
      Object oldObject = e.getOldObject();
      Object newObject = e.getNewObject();
      if (null == oldObject && null == newObject) {
        return;
      }

      // ���û�д���߻�д
      OrderPayPlanWriteBackFor36D1Util util =
          OrderPayPlanWriteBackFor36D1Util.getInstance();
      AggApplyVO[] oldAggVos = util.convertObjectToAggVos(oldObject);
      AggApplyVO[] newAggVos = util.convertObjectToAggVos(newObject);

      AggApplyVO[] afterFilterOldAggVos = util.getFilterAggVos(oldAggVos);
      AggApplyVO[] afterFilterNewAggVos = util.getFilterAggVos(newAggVos);

      if (ArrayUtils.isEmpty(afterFilterOldAggVos)
          && ArrayUtils.isEmpty(afterFilterNewAggVos)) {
        return;
      }
      // �����޸ĸ������뵥ʱ�Ļ�д����
      Map<String, List<RewritePara>> rwParaMap =
          util.getRewritePara(afterFilterNewAggVos, afterFilterOldAggVos);
      if (rwParaMap == null) {
        return;
      }
      List<RewritePara> paraList = rwParaMap.get(POBillType.Order.getCode());
      if (paraList == null || paraList.size() == 0) {
        return;
      }
      OrderPayPlanWriteBackParaFor36D1[] backVOs =
          util.getWritebackVO(paraList);
      util.writeBack(backVOs);
    }
  }
}

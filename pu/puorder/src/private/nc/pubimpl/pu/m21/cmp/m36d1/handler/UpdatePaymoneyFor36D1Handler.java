package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.pub.ICmpApplayEventType;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单付款计划生成的资金付款申请单累积付款金额变化后回写采购订单的监听处理类
 * 
 * @since 6.33
 * @version 2014-7-31 下午1:16:31
 * @author mengjian
 */
public class UpdatePaymoneyFor36D1Handler implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (!SysInitGroupQuery.isPOEnabled()) {
      return;
    }
    // 付款申请单累计付款金额变化后事件
    if (ICmpApplayEventType.TYPE_UPDATEPAYMONEY_AFTER.equals(event
        .getEventType())) {
      BdUpdateEvent e = (BdUpdateEvent) event;
      Object oldObject = e.getOldObject();
      Object newObject = e.getNewObject();
      if (null == oldObject && null == newObject) {
        return;
      }

      // 调用回写工具回写
      UpdatePaymoneyForF3Util util = UpdatePaymoneyForF3Util.getInstance();
      AggApplyVO[] oldAggVos = util.convertObjectToAggVos(oldObject);
      AggApplyVO[] newAggVos = util.convertObjectToAggVos(newObject);

      AggApplyVO[] afterFilterOldAggVos = util.getFilterAggVos(oldAggVos);
      AggApplyVO[] afterFilterNewAggVos = util.getFilterAggVos(newAggVos);

      if (ArrayUtils.isEmpty(afterFilterOldAggVos)
          && ArrayUtils.isEmpty(afterFilterNewAggVos)) {
        return;
      }

      // 构造修改付款申请单时的回写参数
      Map<String, List<RewritePara>> rwParaMap =
          util.getRewritePara(afterFilterNewAggVos, afterFilterOldAggVos);
      if (rwParaMap == null) {
        return;
      }
      List<RewritePara> paraList = rwParaMap.get(POBillType.Order.getCode());
      if (paraList == null || paraList.size() == 0) {
        return;
      }

      // 付款单回写付款申请时回写参数用到了币种，
      // 这里复用付款单回写的逻辑，所以回写参数中也要会写币种
      Map<String, String> currtypeMap = new HashMap<String, String>();
      for (AggApplyVO aggVo : afterFilterNewAggVos) {
        ApplyBVO[] bodyvos = (ApplyBVO[]) aggVo.getChildren(ApplyBVO.class);
        for (ApplyBVO bvo : bodyvos) {
          if (currtypeMap.get(bvo.getPk_srcbill()) == null) {
            currtypeMap.put(bvo.getPk_srcbill(), bvo.getPk_currtype());
          }
        }
      }
      IOrderWriteBackParaForF3[] backVOs =
          util.getWritebackVO(paraList, currtypeMap);
      util.writeBack(backVOs);
    }
  }

}

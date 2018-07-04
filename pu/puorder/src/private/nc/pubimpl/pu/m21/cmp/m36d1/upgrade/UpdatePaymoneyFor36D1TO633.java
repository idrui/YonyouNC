package nc.pubimpl.pu.m21.cmp.m36d1.upgrade;

import java.util.ArrayList;
import java.util.List;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.pu.m21.cmp.m36d1.handler.UpdatePaymoneyForF3Util;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.pub.ICmpApplayEventType;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 用于付款申请633升级时处理采购订单付款计划上累计付款金额字段的升级
 * 
 * @since 6.33
 * @version 2014-8-7 上午8:23:36
 * @author mengjian
 */
public class UpdatePaymoneyFor36D1TO633 implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (!SysInitGroupQuery.isPOEnabled()) {
      return;
    }
    // 633升级时付款申请单累计付款金额变化后事件
    if (ICmpApplayEventType.TYPE_APPLYUPDATE_FORUPBILL.equals(event
        .getEventType())) {
      BdUpdateEvent e = (BdUpdateEvent) event;
      Object newObject = e.getNewObject();
      if (null == newObject) {
        return;
      }

      // 调用回写工具回写
      UpdatePaymoneyForF3Util util = UpdatePaymoneyForF3Util.getInstance();
      AggApplyVO[] newAggVos = util.convertObjectToAggVos(newObject);

      AggApplyVO[] afterFilterNewAggVos = util.getFilterAggVos(newAggVos);

      if (ArrayUtils.isEmpty(afterFilterNewAggVos)) {
        return;
      }

      // 633之前付款计划->付款申请是不允许修改的，累计付款金额没有回写，
      // 633升级时依据付款申请上记录的付款信息回写付款计划累计付款金额
      // 累计付款金额 naccumpayorgmny 累计付款本币金额 naccumpaymny
      String sql =
          " update po_order_payplan set naccumpayorgmny = ? ,naccumpaymny =? where pk_order_payplan = ?";
      JavaType[] types = new JavaType[] {
        JavaType.UFDouble, JavaType.UFDouble, JavaType.String
      };
      List<List<Object>> data = new ArrayList<List<Object>>();
      for (AggApplyVO applyVO : afterFilterNewAggVos) {
        ApplyBVO[] bvos = (ApplyBVO[]) applyVO.getChildren(ApplyBVO.class);
        for (ApplyBVO bvo : bvos) {
          List<Object> list = new ArrayList<Object>();
          list.add(bvo.getPaymny());
          list.add(bvo.getOlcpaymny());
          list.add(bvo.getPk_srcbillrowid());
          data.add(list);
        }

      }
      DataAccessUtils utils = new DataAccessUtils();
      utils.update(sql, types, data);
    }
  }

}

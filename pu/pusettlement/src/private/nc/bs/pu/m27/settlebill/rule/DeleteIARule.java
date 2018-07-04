package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.M27IAServices;
import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 删除结算单（反结算）前删除存货核算的单据
 * @scene
 * 取消传存货核算,删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:59:28
 * @author zhangshqb
 */
public class DeleteIARule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    // 未启用存货核算，返回
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    List<String> hids = new ArrayList<String>();
    List<String> bids = new ArrayList<String>();

    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {

        // IA要求：hids数组和bids数组必须是等长数组
        hids.add(vo.getPrimaryKey());
        bids.add(item.getPk_settlebill_b());
      }
    }

    try {
      String[] csrcids = hids.toArray(new String[hids.size()]);
      String[] csrcbids = bids.toArray(new String[bids.size()]);
      // 删除I2（IA采购入库单）单据
      M27IAServices.unsettleI2(csrcids, csrcbids);
      // 删除I9（IA入库调整单）单据
      M27IAServices.cancelSettleI9(csrcids, csrcbids);
      // 删除IG（IA损益调整单）单据
      M27IAServices.cancelSettleIG(csrcids, csrcbids);
      // mengjian by 20141021 启用利润中心存货核算时
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.unsettleI2(csrcids, csrcbids);
        PCIAServices.cancelSettleI9(csrcids, csrcbids);
      }

    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

}

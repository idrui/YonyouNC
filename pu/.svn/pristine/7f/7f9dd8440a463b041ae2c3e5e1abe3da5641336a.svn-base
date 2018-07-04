package nc.bs.pu.m27.settlebill.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m25.pu.settle.IVirtualInvoiceMaintain;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 如果是虚拟发票结算，则删除相应的虚拟发票
 * @scene
 * 删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:08:17
 * @author zhangshqb
 */
public class DeleteVirtualInvoiceRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    String[] invoiceIds = this.getInvoiceIds(vos);
    if ((invoiceIds == null) || (invoiceIds.length == 0)) {
      return;
    }

    IVirtualInvoiceMaintain service =
        NCLocator.getInstance().lookup(IVirtualInvoiceMaintain.class);
    try {
      service.delete(invoiceIds);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private String[] getInvoiceIds(SettleBillVO[] vos) {
    Set<String> ids = new HashSet<String>();
    for (SettleBillVO vo : vos) {
      if (UFBoolean.FALSE.equals(vo.getParentVO().getBvirtualsettle())) {
        continue;
      }
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        ids.add(item.getPk_invoice());
      }
    }
    return ids.toArray(new String[ids.size()]);
  }

}

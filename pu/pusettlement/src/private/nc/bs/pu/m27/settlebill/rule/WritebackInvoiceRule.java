package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleWriteBack;
import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 结算回写发票的规则
 * @scene
 * 删除结算单,费用结算,结算完毕保存结算单
 * @param
 * point 回写时点的枚举类（保存时、删除时）
 *
 * @since 6.3
 * @version 2014-10-22 下午4:07:49
 * @author zhangshqb
 */
public class WritebackInvoiceRule implements IRule<SettleBillVO> {
  private WritebackPoint point = WritebackPoint.SAVE;

  public WritebackInvoiceRule(WritebackPoint point) {
    this.point = point;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    InvoiceWriteBackVO[] backVos = this.getInvoiceWriteBackVO(vos);
    if (ArrayUtils.isEmpty(backVos)) {
      return;
    }
    IInvoiceSettleWriteBack service =
        NCLocator.getInstance().lookup(IInvoiceSettleWriteBack.class);
    try {
      service.writeBack(backVos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private InvoiceWriteBackVO getInvoiceWriteBackVO(SettleBillItemVO item) {
    InvoiceWriteBackVO voBack = null;
    UFDouble dRealMoney = null;
    if (!MathTool.isZero(item.getNgoodsmoney())) {
      dRealMoney =
          MathTool.add(item.getNgoodsmoney(), item.getNreasonalwastmny());
    }
    else {
      dRealMoney = item.getNmoney();
    }

    voBack = new InvoiceWriteBackVO();
    voBack.setPk_invoice(item.getPk_invoice());
    voBack.setPk_invoice_b(item.getPk_invoice_b());
    if (this.point == WritebackPoint.SAVE) {
      voBack.setDiffNum(MathTool.sub(item.getNsettlenum(), item
          .getNreasonalwastnum()));
      voBack.setDiffMny(dRealMoney);
    }
    else {
      voBack.setDiffNum(MathTool.oppose(MathTool.sub(item.getNsettlenum(), item
          .getNreasonalwastnum())));
      voBack.setDiffMny(MathTool.oppose(dRealMoney));
    }
    return voBack;
  }

  private InvoiceWriteBackVO[] getInvoiceWriteBackVO(SettleBillVO[] vos) {
    List<InvoiceWriteBackVO> backVos = new ArrayList<InvoiceWriteBackVO>();
    for (SettleBillVO vo : vos) {
      // 如果是虚拟发票生成的结算单，则删除结算单时不再回写发票，删除发票的规则将会把虚拟发票删除
      if (this.point == WritebackPoint.DELETE
          && UFBoolean.TRUE.equals(vo.getParentVO().getBvirtualsettle())) {
        continue;
      }

      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        if (StringUtils.isEmpty(item.getPk_invoice_b())) {
          continue;// 判断是否发票行
        }
        InvoiceWriteBackVO backVo = this.getInvoiceWriteBackVO(item);
        if (backVo != null) {
          backVos.add(backVo);
        }
      }
    }
    return ListUtil.isEmpty(backVos) ? null : backVos
        .toArray(new InvoiceWriteBackVO[backVos.size()]);
  }
}

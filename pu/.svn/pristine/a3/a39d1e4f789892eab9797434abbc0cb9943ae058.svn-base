package nc.bs.pu.m21.writeback.arap.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.arap.mf1.IOrderWriteBackParaForF1;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-7-11 下午12:00:33
 * @author wuxla
 */

public class PayCloseChkRule implements IRule<OrderViewVO> {
  private IOrderWriteBackParaForF1[] wbVos;

  public PayCloseChkRule(IOrderWriteBackParaForF1[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    boolean isVerify = this.wbVos[0].isVerify();
    if (isVerify) {
      this.checkVerify(vos);
    }
    // else {
    // this.checkUnVerify(vos);
    // }
  }

  private void checkUnVerify(OrderViewVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderViewVO vo : vos) {
      UFBoolean bfinalcose = vo.getBfinalclose();
      if (UFBoolean.TRUE.equals(bfinalcose)) {
        set.add(vo.getVbillcode());
      }
    }
    if (0 == set.size()) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    String[] codes = set.toArray(new String[set.size()]);
    // 多语
    sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
        "04004030-0250", null, new String[] {
          codes[0]
        })/* 采购订单{0}已经最终关闭，不能取消核销\n */);

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private void checkVerify(OrderViewVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (OrderViewVO vo : vos) {
      UFBoolean payclose = vo.getBpayclose();
      if (UFBoolean.TRUE.equals(payclose)) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0251", null, new String[] {
              vo.getVbillcode(), vo.getCrowno()
            })/* 采购订单{0}第{1}行已经付款关闭，不能核销\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}

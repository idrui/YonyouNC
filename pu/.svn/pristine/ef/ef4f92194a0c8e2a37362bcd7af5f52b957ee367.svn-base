package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单原币预付款限额的检查：预付款限额不能超出订单价格合计总额
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:42:44
 * @author luojw
 */
public class PrePayLimitCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      OrderHeaderVO headerVO = vo.getHVO();
      // 原币预付款限额
      UFDouble norgprepaylimit = headerVO.getNorgprepaylimit();
      if (0 == MathTool.compareTo(norgprepaylimit, UFDouble.ZERO_DBL)) {
        continue;
      }

      UFDouble ntotalorigmny = headerVO.getNtotalorigmny();

      if (MathTool.compareTo(norgprepaylimit, ntotalorigmny) > 0) {
        String vbillcode = vo.getHVO().getVbillcode();
        vbillcode = StringUtil.isEmptyWithTrim(vbillcode) ? "" : vbillcode;
        sb.append(NCLangResOnserver.getInstance().getStrByID(
            "4004030_0",
            "04004030-0240",
            null,
            new String[] {
              vbillcode, String.valueOf(norgprepaylimit),
              String.valueOf(ntotalorigmny)
            })/* 订单{0}表头预付款限额{1}超出订单价格合计总额{2}，请修改！ */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}

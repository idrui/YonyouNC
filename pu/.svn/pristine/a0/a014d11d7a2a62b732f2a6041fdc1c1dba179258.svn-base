package nc.bs.pu.m21.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单是否由销售订单协同生成检查
 * @scene
 *        采购订单协同生成销售订单
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午9:41:48
 * @author luojw
 */

public class SoCoopToOrderRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    List<String> list = new ArrayList<String>();
    for (OrderVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBsocooptome())) {
        list.add(vo.getHVO().getVbillcode());
      }
    }

    if (list.size() > 0) {
      String[] codes = list.toArray(new String[list.size()]);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0244", null, codes)/*
                                                                 * 订单{0}由销售订单协同生成
                                                                 * ，不能再生成销售订单
                                                                 */);
    }
  }

}

package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 已协同生成销售订单
 * 
 * @since 6.0
 * @version 2011-3-25 下午05:10:02
 * @author wuxla
 */

public class CooptosoRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    List<String> list = new ArrayList<String>();
    for (OrderVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBcooptoso())) {
        list.add(vo.getHVO().getVbillcode());
      }
    }
    if (list.size() > 0) {
      String[] codes = list.toArray(new String[list.size()]);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < codes.length; ++i) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(codes[i]);
      }
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0300", null, new String[] {
            sb.toString()
          })/* 订单{0}已协同生成销售订单！ */);
    }
  }
}

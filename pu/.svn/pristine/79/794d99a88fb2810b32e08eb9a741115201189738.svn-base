package nc.bs.pu.m21.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              采购订单表体结算财务组织相同检查
 * @scene
 *        采购订单协同生成销售订单
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午9:44:00
 * @author luojw
 */

public class CoopDiffPsfinanceOrgRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    List<String> list = new ArrayList<String>();
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      String pk_psfinanceorg = itemVOs[0].getPk_psfinanceorg();
      for (int i = 1; i < itemVOs.length; ++i) {
        String tempPsfinanceorg = itemVOs[i].getPk_psfinanceorg();
        if (!ObjectUtils.equals(pk_psfinanceorg, tempPsfinanceorg)) {
          list.add(vo.getHVO().getVbillcode());
          break;
        }
      }
    }

    if (list.size() > 0) {
      String[] codes = list.toArray(new String[list.size()]);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0242", null, new String[] {
            codes[0]
          })/* 订单{0}表体结算财务组织不同，不能协同生成销售订单 */);
    }

  }

}

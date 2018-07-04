/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 上午09:02:09
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单回写到货计划
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 上午09:02:09
 */
public class RPStoreCalFor4TRule implements IRule<OrderReceivePlanVO> {
  private IOrderWriteBackPara[] wbVos;

  public RPStoreCalFor4TRule(IOrderWriteBackPara[] wbVos) {
    this.wbVos = wbVos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderReceivePlanVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    Map<String, OrderReceivePlanVO> rpMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : vos) {
      rpMap.put(rpVO.getPk_order_bb1(), rpVO);
    }

    for (IOrderWriteBackPara vo : this.wbVos) {
      String bbid = vo.getBBID();
      if (null == bbid) {
        continue;
      }
      OrderReceivePlanVO rpVO = rpMap.get(bbid);
      if (null == rpVO) {
        continue;
      }
      UFDouble diffNum = vo.getDiffNum();
      UFDouble newAccNum = MathTool.add(rpVO.getNaccumstorenum(), diffNum);
      rpVO.setNaccumstorenum(newAccNum);
    }
  }

}

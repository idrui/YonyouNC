/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 下午04:14:54
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m23.pu.m21.IQuery23For21;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              入库回写到货计划，分入库和退库。
 * @scene
 *        采购入库单回写到货计划
 * @param IOrderWriteBackPara[] wbVos 采购订单公共回写参数接口 OrderViewVO[] views 采购订单的视图VO
 * @since 6.3
 * @version 2014-10-22 上午9:49:59
 * @author luojw
 */
public class ReceivePlanStoreCalRule implements IRule<OrderReceivePlanVO> {

  private OrderViewVO[] views;

  private IOrderWriteBackPara[] wbVos;

  public ReceivePlanStoreCalRule(IOrderWriteBackPara[] wbVos,
      OrderViewVO[] views) {
    this.wbVos = wbVos;
    this.views = views;
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
    Map<String, UFBoolean> arriveMap = this.getArriveMap();
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(this.views);
    Map<String, OrderReceivePlanVO> rpMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : vos) {
      rpMap.put(rpVO.getPk_order_bb1(), rpVO);
    }

    for (IOrderWriteBackPara vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      OrderReceivePlanVO rpVO = rpMap.get(vo.getBBID());
      if (null == rpVO) {
        continue;
      }
      UFDouble diffNum = vo.getDiffNum();
      // 入库或基于原订单退库
      if (!vo.isReturn()) {
        UFDouble newAccNum = MathTool.add(rpVO.getNaccumstorenum(), diffNum);
        rpVO.setNaccumstorenum(newAccNum);
      }
      else {// 退库
        if (view.getBreturn().booleanValue()
            || view.getBrefwhenreturn().booleanValue()) {
          UFDouble newAccNum = MathTool.add(rpVO.getNaccumstorenum(), diffNum);
          rpVO.setNaccumstorenum(newAccNum);
          if (view.getBrefwhenreturn().booleanValue() && arriveMap != null
              && UFBoolean.TRUE.equals(arriveMap.get(view.getPk_order_b()))) {
            UFDouble newAccArrNum =
                MathTool.add(rpVO.getNaccumarrvnum(), diffNum);
            rpVO.setNaccumarrvnum(newAccArrNum);
          }
        }
        UFDouble newAccNum =
            MathTool.add(rpVO.getNbackstorenum(), MathTool.oppose(diffNum));
        rpVO.setNbackstorenum(newAccNum);
      }
    }
  }

  private Map<String, UFBoolean> getArriveMap() {
    List<String> bidList = new ArrayList<String>();
    for (IOrderWriteBackPara wbVo : this.wbVos) {
      if (wbVo.isReturn()) {
        bidList.add(wbVo.getBBID());
      }
    }

    if (0 == bidList.size()) {
      return null;
    }

    String[] bids = bidList.toArray(new String[bidList.size()]);
    IQuery23For21 service = NCLocator.getInstance().lookup(IQuery23For21.class);
    try {
      return service.queryHaveArriveByBBid(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}

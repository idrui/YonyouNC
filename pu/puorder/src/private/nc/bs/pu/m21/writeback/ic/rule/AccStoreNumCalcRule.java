/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-1 下午01:39:28
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m23.pu.m21.IQuery23For21;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单累计入库数量计算规则
 * @scene
 *        采购入库单回写订单
 * @param
 *        IOrderWriteBackPara[] wbVos 采购订单公共回写参数接口
 * @since 6.3
 * @version 2014-10-22 下午3:21:40
 * @author luojw
 */
public class AccStoreNumCalcRule implements IRule<OrderViewVO> {

  private IOrderWriteBackPara[] wbVos;

  public AccStoreNumCalcRule(IOrderWriteBackPara[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {

    Map<String, UFBoolean> arriveMap = this.getArriveMap();

    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackPara vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      if(view == null){
        continue;
      }
      UFDouble diffNum = vo.getDiffNum();
      // 入库或基于原订单退库
      if (!vo.isReturn()) {
        UFDouble newAccNum = MathTool.add(view.getNaccumstorenum(), diffNum);
        view.setNaccumstorenum(newAccNum);
      }
      else {
        // 退库
        // 如果是负订单，需要回写累计入库数量
        if (view.getBreturn().booleanValue()
            || view.getBrefwhenreturn().booleanValue()) {
          UFDouble newAccNum = MathTool.add(view.getNaccumstorenum(), diffNum);
          view.setNaccumstorenum(newAccNum);
          if (view.getBrefwhenreturn().booleanValue() && arriveMap != null
              && UFBoolean.TRUE.equals(arriveMap.get(view.getPk_order_b()))) {
            UFDouble newAccArrNum =
                MathTool.add(view.getNaccumarrvnum(), diffNum);
            view.setNaccumarrvnum(newAccArrNum);
          }
        }
        UFDouble newAccBackNum =
            MathTool.add(view.getNbackstorenum(), MathTool.oppose(diffNum));
        view.setNbackstorenum(newAccBackNum);

      }
    }
  }

  private Map<String, UFBoolean> getArriveMap() {
    List<String> bidList = new ArrayList<String>();
    for (IOrderWriteBackPara wbVo : this.wbVos) {
      if (wbVo.isReturn()) {
        bidList.add(wbVo.getBID());
      }
    }

    if (0 == bidList.size()) {
      return null;
    }

    String[] bids = bidList.toArray(new String[bidList.size()]);
    IQuery23For21 service = NCLocator.getInstance().lookup(IQuery23For21.class);
    try {
      return service.queryHaveArrive(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}

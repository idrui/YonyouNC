package nc.bs.pu.m21.writeback.et.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            出口合同回写采购订单时，入库关闭检查
 * @scene
 *      出口合同回写采购订单
 * @param
 * 
 *
 * @since 6.0
 * @version 2013-10-21 下午09:31:12
 * @author zhangyhh
 */
public class StoreCloseChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] views) {

    for (OrderViewVO view : views) {
      if (UFBoolean.TRUE.equals(view.getBstockclose())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0254", null, new String[] {
              view.getVordercode()
            })/* 单据号：{0}已入库关闭！ */);
        // /* 单据号：{0}已入库关闭！ */);
      }
    }
  }

}

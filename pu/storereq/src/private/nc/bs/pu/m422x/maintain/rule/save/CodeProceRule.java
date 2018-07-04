package nc.bs.pu.m422x.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *            物资需求申请单保存/更新时，对单据号的处理
 * @scene
 *      物资需求申请单保存/更新
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午08:03:40
 * @author wuxla
 */
public class CodeProceRule implements ICompareRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    if (null == originVOs) {// 新增保存
      this.handleInsertVO(vos);
    }
    else {// 修改保存
      this.handleUpdateVO(vos, originVOs);
    }

  }

  private void handleInsertVO(StoreReqAppVO[] vos) {
    PUBillCodeUtils.getStorereqCode().createBillCode(vos);
  }

  private void handleUpdateVO(StoreReqAppVO[] vos, StoreReqAppVO[] orgVos) {
    PUBillCodeUtils.getStorereqCode().upadteBillCode(vos, orgVos);
  }
}

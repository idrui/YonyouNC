package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoicePlanPriceSetter;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 卡片刷新，处理计算属性（计划价）。
 * 
 * @since 6.3
 * @version 2014-1-26 下午03:07:34
 * @author wuxla
 */
public class InvoiceRefreshSingleAction extends RefreshSingleAction {
  private static final long serialVersionUID = -4189803181992353083L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.model.getSelectedData();
    if (obj != null) {

      AbstractBill oldVO = (AbstractBill) obj;
      String pk = oldVO.getParentVO().getPrimaryKey();
      IBillQueryService billQuery =
          NCLocator.getInstance().lookup(IBillQueryService.class);
      AggregatedValueObject newVO =
          billQuery.querySingleBillByPk(oldVO.getClass(), pk);

      // 单据被删除之后应该回到列表界面再刷新
      if (newVO == null) {
        // 数据已经被删除
        throw new BusinessException(NCLangRes.getInstance().getStrByID("uif2",
            "RefreshSingleAction-000000")/* 数据已经被删除，请返回列表界面！ */);
      }
      new InvoicePlanPriceSetter().setPlanPrice(new InvoiceVO[] {
        (InvoiceVO) newVO
      });
      this.model.directlyUpdate(newVO);
    }
    this.showQueryInfo();
  }

}

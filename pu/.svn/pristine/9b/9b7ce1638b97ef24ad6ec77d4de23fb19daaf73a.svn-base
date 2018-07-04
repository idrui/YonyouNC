/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 下午02:55:59
 */
package nc.ui.pu.costfactor.editor.list.afteredit.rule;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 下午02:55:59
 */
public class CalFactorOrder implements IListHeadAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent)
   */
  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    this.setOrder((String) event.getValue(), event.getRow(),
        event.getBillListPanel());
  }

  public void setOrder(String pk_org, int row, BillListPanel blp) {
    if (StringUtils.isBlank(pk_org)) {
      return;
    }
    CostfactorVO[] cfVos = null;
    try {
      // 成本要素新增报并发错误（如果表体行为空的成本要素“成本要素顺序”为最大值时在新增同一组织下的成本要素报并发）
      cfVos =
          NCLocator.getInstance().lookup(ICostFactorQueryService.class)
              .queryCostfacotorInNofilter(pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Integer cfOrder = Integer.valueOf(0);
    if (null != cfVos) {
      for (CostfactorVO cfVo : cfVos) {
        if (cfOrder.compareTo(cfVo.getParentVO().getIfactororder()) < 0) {
          cfOrder = cfVo.getParentVO().getIfactororder();
        }
      }
    }
    cfOrder = Integer.valueOf(cfOrder.intValue() + 1);
    if (cfOrder.intValue() > CostfactorVO.MAX_NUM) {
      int num = CostfactorVO.MAX_NUM;
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4004060_0", "04004060-0194", null, new String[] {
            String.valueOf(num)
          })/* 同一财务组织下只能定义{0}项成本要素! */);
    }
    blp.getHeadBillModel().setValueAt(cfOrder, row,
        CostfactorHeaderVO.IFACTORORDER);
  }
}

/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午12:41:10
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pubapp.uif2app.actions.batch.BatchCopyLineAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RPDownFlowOrCloseCheck;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>有后续单据或相关订单行已关闭，不能复制
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午12:41:10
 */
public class RPBatchCopyLineAction extends BatchCopyLineAction {

  private static final long serialVersionUID = -6774534713432937519L;

  private BatchBillTable list;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchCopyLineAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doBeforeAction();

    super.doAction(e);
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  private void doBeforeAction() {
    OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
    OrderReceivePlanVO rpVO = (OrderReceivePlanVO) model.getSelectedData();
    OrderVO orderVO = model.getOrderVO();

    // 有后续单据或相关订单行已关闭，不能复制
    RPDownFlowOrCloseCheck check = new RPDownFlowOrCloseCheck();
    String result = check.downFlowOrCloseCheck(new OrderReceivePlanVO[] {
      rpVO
    }, orderVO);
    if (!StringUtil.isEmptyWithTrim(result)) {
      ExceptionUtils.wrappBusinessException(result
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0068")/* @res "不能复制" */);
    }
  }

}

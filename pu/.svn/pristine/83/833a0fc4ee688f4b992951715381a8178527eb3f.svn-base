/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午01:04:58
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pubapp.uif2app.actions.batch.BatchDelLineAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RPDownFlowOrCloseCheck;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @author wuxla
 * @time 2010-4-17 下午01:04:58
 */
public class RPBatchDelLineAction extends BatchDelLineAction {

  private static final long serialVersionUID = 3039689832724653152L;

  private BatchBillTable list;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchDelLineAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchDelLineAction#beforeDelLine()
   */
  @Override
  protected boolean beforeDelLine() {
    OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
    OrderReceivePlanVO rpVO = (OrderReceivePlanVO) model.getSelectedData();
    if (null == rpVO) {
      // 提示
      // 请选中需删除的行
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0209")/*
                                                                   * @res
                                                                   * "请选中需删除的行"
                                                                   */);
      return false;
    }

    OrderVO orderVO = model.getOrderVO();
    // 有后续单据或相关订单行已关闭，不能删除
    RPDownFlowOrCloseCheck check = new RPDownFlowOrCloseCheck();
    String result = check.downFlowOrCloseCheck(new OrderReceivePlanVO[] {
      rpVO
    }, orderVO);
    if (!StringUtil.isEmptyWithTrim(result)) {
      ExceptionUtils.wrappBusinessException(result
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0038")/* @res "不能删除" */);
      return false;
    }

    return super.beforeDelLine();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchDelLineAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    BatchBillTableModel model = this.getModel();
    return model.getUiState() == UIState.EDIT && model.getSelectedIndex() != -1
        && model.getRows().size() > 0;
  }

}

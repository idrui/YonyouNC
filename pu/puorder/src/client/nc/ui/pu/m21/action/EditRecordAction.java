/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderEditRecordQuery;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"修订历史"按钮
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class EditRecordAction extends NCAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private ShowUpableBillListView listView;

  /**
   * InvoiceAction 的构造子
   */
  public EditRecordAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_REVISEHISTORY);
//    this.setBtnName("修订记录");
//    this.setCode("btnEditRecord");
//    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   *
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    OrderVO ordervo = (OrderVO) this.getListView().getModel().getSelectedData();

    OrderVO[] editRecords = null;

    if (ordervo == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0039")/*@res "请选择要查询的数据"*/);
    }

    if (ordervo != null) {
      OrderItemVO[] bvos = ordervo.getBVO();

      List<String> pkList = new ArrayList<String>();

      for (OrderItemVO bvo : bvos) {
        pkList.add(bvo.getPk_order_b());
      }

      IOrderEditRecordQuery service =
          NCLocator.getInstance().lookup(IOrderEditRecordQuery.class);

      editRecords = service.queryOrderPrice(pkList);

    }
    this.getListView().getModel().initModel(editRecords);

    this.getListView().showMeUp();
  }

  public ShowUpableBillListView getListView() {
    return this.listView;
  }

  public void setListView(ShowUpableBillListView listView) {
    this.listView = listView;
  }
}
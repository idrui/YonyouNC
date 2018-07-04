/**
 * 
 */
package nc.ui.pu.m27.match.view;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.util.DispResultScaleProcesser;
import nc.ui.pu.m27.match.view.listener.InvoiceSortRelaObjectListener;
import nc.ui.pu.m27.match.view.listener.StockSortRelaObjectListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存单据、发票显示处理类，对应查询界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 下午04:50:24
 */
public class MatchDisplayListPanel extends ShowUpableBillListView {
  private static final long serialVersionUID = 1224926032125118626L;

  @Override
  public void initUI() {
    super.initUI();
    // 默认列表不可编辑
    this.getBillListPanel().setEnabled(false);
    // 多选属性，需要在添加RowStateChangeEventListener之前设置
    this.getBillListPanel().setMultiSelect(true);
    //yanxm5 合计行
    this.getBillListPanel().getParentListPanel().setTotalRowShow(true);
    this.getBillListPanel().getChildListPanel().setTotalRowShow(true);
    // 发票的排序支持
    this.getBillListPanel().getHeadBillModel().removeSortRelaObjectListener(
        this.getModel());
    this.getBillListPanel().getHeadBillModel().addSortRelaObjectListener(
        new InvoiceSortRelaObjectListener((MatchManageModel) this.getModel()));
    // 入库单的排序支持
    this.getBillListPanel().getBodyBillModel().removeSortRelaObjectListener(
        this.getModel());
    this.getBillListPanel().getBodyBillModel().addSortRelaObjectListener(
        new StockSortRelaObjectListener((MatchManageModel) this.getModel()));
    // 处理精度
    this.procScale(this.getBillListPanel());
  }

  @Override
  public void setModel(BillManageModel model) {
    super.setModel(model);

    // 对于个性化中心中设置了业务单元的情况，PUBAPP设置默认的业务单元后，不会触发组织切换事件。
    // 因此导致结算环境没有初始化，这里需要主动初始化一下
    String pk_org = model.getContext().getPk_org();
    ((MatchManageModel) this.getModel()).initSettleEnvironment(pk_org);
  }

  private void procScale(BillListPanel blp) {
    new DispResultScaleProcesser(blp).initScale();
  }
}

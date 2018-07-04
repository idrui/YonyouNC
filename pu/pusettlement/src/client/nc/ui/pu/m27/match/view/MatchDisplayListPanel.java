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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��浥�ݡ���Ʊ��ʾ�����࣬��Ӧ��ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 ����04:50:24
 */
public class MatchDisplayListPanel extends ShowUpableBillListView {
  private static final long serialVersionUID = 1224926032125118626L;

  @Override
  public void initUI() {
    super.initUI();
    // Ĭ���б��ɱ༭
    this.getBillListPanel().setEnabled(false);
    // ��ѡ���ԣ���Ҫ�����RowStateChangeEventListener֮ǰ����
    this.getBillListPanel().setMultiSelect(true);
    //yanxm5 �ϼ���
    this.getBillListPanel().getParentListPanel().setTotalRowShow(true);
    this.getBillListPanel().getChildListPanel().setTotalRowShow(true);
    // ��Ʊ������֧��
    this.getBillListPanel().getHeadBillModel().removeSortRelaObjectListener(
        this.getModel());
    this.getBillListPanel().getHeadBillModel().addSortRelaObjectListener(
        new InvoiceSortRelaObjectListener((MatchManageModel) this.getModel()));
    // ��ⵥ������֧��
    this.getBillListPanel().getBodyBillModel().removeSortRelaObjectListener(
        this.getModel());
    this.getBillListPanel().getBodyBillModel().addSortRelaObjectListener(
        new StockSortRelaObjectListener((MatchManageModel) this.getModel()));
    // ������
    this.procScale(this.getBillListPanel());
  }

  @Override
  public void setModel(BillManageModel model) {
    super.setModel(model);

    // ���ڸ��Ի�������������ҵ��Ԫ�������PUBAPP����Ĭ�ϵ�ҵ��Ԫ�󣬲��ᴥ����֯�л��¼���
    // ��˵��½��㻷��û�г�ʼ����������Ҫ������ʼ��һ��
    String pk_org = model.getContext().getPk_org();
    ((MatchManageModel) this.getModel()).initSettleEnvironment(pk_org);
  }

  private void procScale(BillListPanel blp) {
    new DispResultScaleProcesser(blp).initScale();
  }
}

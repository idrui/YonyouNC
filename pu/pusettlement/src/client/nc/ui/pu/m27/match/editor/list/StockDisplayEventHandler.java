package nc.ui.pu.m27.match.editor.list;

import nc.ui.pu.m27.match.editor.event.StockDisplayEvent;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.view.MatchDisplayListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.util.VOSortUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ⵥ��ѯ�����ʾ
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����09:29:46
 */
public class StockDisplayEventHandler implements
    IAppEventHandler<StockDisplayEvent> {
  private MatchDisplayListPanel listView;

  public MatchDisplayListPanel getListView() {
    return this.listView;
  }

  @Override
  public void handleAppEvent(StockDisplayEvent e) {
    MatchManageModel model = (MatchManageModel) this.listView.getModel();
    BillModel bm = this.listView.getBillListPanel().getBodyBillModel();
    StockSettleVO[] vos = model.getQueryStockVOs();
    // �����ݺ�����
    VOSortUtils.sortVOs(vos, StockSettleVO.VBILLCODE);
    bm.setBodyDataVO(vos);
    bm.execLoadFormula();

    // ��ʾ��ǰ����
    this.listView.showMeUp();

    // �˴�����������¼����ɷ���ȫѡ��ȫ����ť��״̬��ˢ��
    model.fireEvent(new AppEvent(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0029")/*@res "ˢ�°�ť״̬"*/));
  }

  public void setListView(MatchDisplayListPanel listView) {
    this.listView = listView;
  }

}
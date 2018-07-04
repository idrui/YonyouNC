package nc.ui.pu.m21.editor.card.beforeedit.body;

import java.util.Collection;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.vorg.TrafficOrgVersionVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-17 ÏÂÎç05:00:03
 * @author wuxla
 */

public class Flowstockorg implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    int row = event.getRow();
    BillCardPanel panel = event.getBillCardPanel();
    String pk_recvstordoc =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_RECVSTORDOC);
    if (!StringUtil.isEmptyWithTrim(pk_recvstordoc)) {
      this.filterByStordoc(row, pk_recvstordoc, panel);
      event.setReturnValue(Boolean.TRUE);
      return;
    }
    String pk_arrvstoorg =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_ARRVSTOORG);
    if (!StringUtil.isEmptyWithTrim(pk_arrvstoorg)) {
      this.filterByArrstoorg(row, pk_arrvstoorg, panel);
      event.setReturnValue(Boolean.TRUE);
      return;
    }

    event.setReturnValue(Boolean.FALSE);
  }

  private void filterByArrstoorg(int row, String pk_arrvstoorg,
      BillCardPanel panel) {
    Map<String, Collection<String>> map = null;
    map =
        TrafficOrgPubService
            .getTrafficOrgIDSByStockOrgIDSWithIsReceive(new String[] {
              pk_arrvstoorg
            });
    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(OrderItemVO.PK_FLOWSTOCKORG_V)
            .getComponent();
    if (null == map || null == map.get(pk_arrvstoorg)) {
      pane.getRefModel().addWherePart(" and 1 <> 1");
      return;
    }
    Collection<String> orgSet = map.get(pk_arrvstoorg);
    String[] orgs = orgSet.toArray(new String[orgSet.size()]);

    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + TrafficOrgVersionVO.PK_TRAFFICORG, orgs);
    pane.getRefModel().addWherePart(sql.toString());
  }

  private void filterByStordoc(int row, String pk_recvstordoc,
      BillCardPanel panel) {
    StordocVO[] vos = StordocPubService.queryStordocInfoByPks(new String[] {
      pk_recvstordoc
    }, new String[] {
      StordocVO.PK_STORDOC, StordocVO.PK_ORG
    });
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_org = vos[0].getPk_org();
    this.filterByArrstoorg(row, pk_org, panel);
  }
}

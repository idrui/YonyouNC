/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����02:47:34
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.cust.addressdoc.AddressDocVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 ����02:47:34
 */
public class RPReceiveWarehouse implements ICardBodyAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    // ��ѯ�ֿ���Ϣ
    StordocVO store = this.queryStordocInfo(panel, row);

    // ���õ�ַ���ص㡢������
    this.setAddressInfo(panel, row, store);

    // �����ջ������֯
    if (store == null
        || panel.getBodyValueAt(row, OrderReceivePlanVO.PK_ARRVSTOORG) != null) {
      return;
    }
    panel.setBodyValueAt(store.getPk_org(), row, OrderItemVO.PK_ARRVSTOORG);
  }

  private void clearAddressInfo(BillCardPanel panel, int row) {
    panel.setBodyValueAt(null, row, OrderItemVO.PK_RECEIVEADDRESS);
    panel.setBodyValueAt(null, row, OrderItemVO.CDEVAREAID);
    panel.setBodyValueAt(null, row, OrderItemVO.CDEVADDRID);
  }

  private StordocVO queryStordocInfo(BillCardPanel panel, int row) {
    String warehouseid =
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_RECVSTORDOC);
    if (StringUtil.isEmptyWithTrim(warehouseid)) {
      return null;
    }
    StordocVO[] vos = null;
    vos = StordocPubService.queryStordocInfoByPks(new String[] {
      warehouseid
    }, new String[] {
      StordocVO.PK_ADDRESS, StordocVO.STORADDR, StordocVO.PK_ORG
    });

    if (vos == null || vos.length == 0) {
      return null;
    }

    return vos[0];
  }

  private void setAddressInfo(BillCardPanel panel, int row, StordocVO store) {
    if (store == null) {
      // �ջ��ͻ��ͲֿⶼΪ��ʱ����յ�ַ����Ϣ
      this.clearAddressInfo(panel, row);
      return;
    }

    // �ջ���ַ
    panel.setBodyValueAt(store.getStoraddr(), row,
        OrderItemVO.PK_RECEIVEADDRESS);

    // �ջ��ص�
    String addrid = store.getPk_address();
    panel.setBodyValueAt(addrid, row, OrderItemVO.CDEVADDRID);

    if (addrid == null) {
      return;
    }

    // �ջ�����
    AddressDocVO addr = AddrdocPubService.queryAddrDocVOByID(addrid);
    panel.setBodyValueAt(addr.getPk_areacl(), row, OrderItemVO.CDEVAREAID);
  }

}

package nc.ui.pu.pub.editor.card.beforeedit;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * 
 * @since 6.0
 * @version 2011-12-14 ����04:52:23
 * @author wangljc
 */
public class Casscustid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    // ���յķ�ΧΪ����֯�ɼ��Ŀͻ�
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterCustomerRefUtils filter =
          new FilterCustomerRefUtils(event.getBillCardPanel().getBodyItem(
              PuAttrNameEnum.casscustid.name()));

      filter.filterItemRefByOrg(org);
    }
  }

}

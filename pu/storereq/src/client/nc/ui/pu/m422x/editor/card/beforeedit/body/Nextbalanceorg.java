package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.enumeration.ReqTypesEnum;

public class Nextbalanceorg implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object valueObject =
        event.getBillCardPanel().getHeadItem(StoreReqAppHeaderVO.FREQTYPEFLAG)
            .getValueObject();
    if (null == valueObject) {
      return;
    }
    Integer freqtype = (Integer) valueObject;
    if (ReqTypesEnum.GROSS_REQUIREMENT.toInteger().equals(freqtype)) {
      event.setReturnValue(Boolean.FALSE);
    }
  }
}

package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;

/**
 * 需求日期编辑前事件
 * 
 * @since 6.5
 * @version 2014-1-16 上午11:20:51
 * @author fanly3
 */
public class Dreqdate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String csourcetypecode =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.CSOURCETYPECODE);
    // 请购单行来源于资产配置申请，需求日期不让编辑
    if (PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(csourcetypecode)) {
      event.setReturnValue(Boolean.FALSE);
    }
  }

}

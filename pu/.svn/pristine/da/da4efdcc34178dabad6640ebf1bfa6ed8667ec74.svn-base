/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午02:36:21
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单位编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 下午02:36:21
 */
public class Astunitid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();
    String pk_material =
        (String) card
            .getBodyValueAt(event.getRow(), PraybillItemVO.PK_MATERIAL);
    String csourcetypecode =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.CSOURCETYPECODE);
    // 物料未设置，不能编辑,或请购单行来源于资产配置申请，单位不让编辑
    if (StringUtil.isEmptyWithTrim(pk_material)
        || PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(csourcetypecode)) {
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      FilterMeasdocRefUtils filter =
          new FilterMeasdocRefUtils(card.getBodyItem(PraybillItemVO.CASTUNITID));
      filter.setMaterialPk((String) card.getBodyValueAt(event.getRow(),
          PraybillItemVO.PK_MATERIAL));
      event.setReturnValue(Boolean.TRUE);
    }
  }

}

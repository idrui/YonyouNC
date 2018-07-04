/**
 * $文件说明$
 * 
 * @author tianft
 * @version
 * @see
 * @since
 * @time 2010-3-25 下午04:39:16
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.m25.editor.utils.UnitAndChangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体换算率编辑前处理
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-25 下午04:39:16
 */
public class ChangeRate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    // 单位
    String astUnit =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.CASTUNITID);
    // 主单位
    String unit =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.CUNITID);
    // 如果单位不存在或者主单位与单位相同,不允许编辑
    if (StringUtil.isEmptyWithTrim(astUnit) || StringUtil.isEmptyWithTrim(unit)
        || astUnit.equals(unit)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    String pk_material =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.PK_MATERIAL);
    // 固定换算率不允许编辑
    boolean fixedRate =
        UnitAndChangeRateUtil.isFixedChangeRate(pk_material, astUnit);
    event.setReturnValue(Boolean.valueOf(!fixedRate));
  }

}

/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午02:36:47
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>换算率编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 下午02:36:47
 */
public class Nchangerate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    String matetil =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_MATERIAL);
    if (StringUtil.isEmptyWithTrim(matetil)) {
      // 物料未设置，不能编辑
      event.setReturnValue(Boolean.FALSE);
    }
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    // 物料
    // 主单位
    String cUnitId =
        util.getBodyStringValue(event.getRow(), PraybillItemVO.CUNITID);
    // 单位
    String castUnitId =
        util.getBodyStringValue(event.getRow(), PraybillItemVO.CASTUNITID);
    // 是否固定换算率
    boolean fixedRate = false;
    fixedRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(matetil,
            castUnitId);
    if (ObjectUtils.equals(cUnitId, castUnitId) || fixedRate) {
      event.setReturnValue(Boolean.valueOf(false));
    }
    else {
      event.setReturnValue(Boolean.valueOf(true));
    }

  }

}

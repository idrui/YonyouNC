package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>自定义项编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑前：判断物料是否固定换算率，设置“换算率”字段是否可以进行编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class ChangeRate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    String matetil =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            ArriveItemVO.PK_MATERIAL);
    if (StringUtil.isEmptyWithTrim(matetil)) {
      // 物料未设置，不能编辑
      e.setReturnValue(Boolean.FALSE);
    }
    CardEditorHelper util = new CardEditorHelper(e.getBillCardPanel());
    // 物料
    // 主单位
    String cUnitId = util.getBodyStringValue(e.getRow(), ArriveItemVO.CUNITID);
    // 单位
    String castUnitId =
        util.getBodyStringValue(e.getRow(), ArriveItemVO.CASTUNITID);
    // 是否固定换算率
    boolean fixedRate = false;
    fixedRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(matetil,
            castUnitId);
    if (ObjectUtils.equals(cUnitId, castUnitId) || fixedRate) {
      e.setReturnValue(Boolean.valueOf(false));
    }
    else {
      e.setReturnValue(Boolean.valueOf(true));
    }

  }
}

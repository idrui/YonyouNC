package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>自由项编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>自由项编辑事件处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class FreeItem implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    String mrlPK =
        ArriveClientUtil.getStringCellValue(event.getBillCardPanel(),
            event.getRow(), ArriveItemVO.PK_MATERIAL);

    if (StringUtils.isEmpty(mrlPK)) {
      event.setReturnValue(Boolean.FALSE);
    }
  }
}

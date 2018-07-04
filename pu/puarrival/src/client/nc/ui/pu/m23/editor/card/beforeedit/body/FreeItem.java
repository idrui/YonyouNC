package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������༭�¼������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������༭�¼�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 ����01:25:25
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

package nc.ui.pu.m4t.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * 利润中心编辑事件
 * 
 * @since 6.5
 * @version 2014-4-9 下午03:58:53
 * @author mengjian
 */
public class Apliabcenter implements ICardBodyBeforeEditEventListener {

  @SuppressWarnings("restriction")
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // 有来源单据时利润中心不允许修改
    Object csourcebid =
        e.getBillCardPanel().getBodyValueAt(e.getRow(),
            InitialEstItemVO.CSOURCEBID);
    if (csourcebid != null) {
  	  e.setReturnValue(false);
    }
  }

}

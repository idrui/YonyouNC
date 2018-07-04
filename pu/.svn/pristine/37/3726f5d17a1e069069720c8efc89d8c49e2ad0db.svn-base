/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 上午07:51:31
 */
package nc.ui.pu.m422x.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-5 上午07:51:31
 */
public class Reqdate implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    UFDate newValue = (UFDate) event.getValue();
    if (ObjectUtil.isEmptyWithTrim(newValue)) {
      return;
    }

    UFDate dbilldate =
        (UFDate) event.getBillCardPanel()
            .getHeadItem(StoreReqAppHeaderVO.DBILLDATE).getValueObject();
    if (ObjectUtil.isEmptyWithTrim(dbilldate)) {
      return;
    }

    if (dbilldate.compareTo(newValue) > 0) {
      event.getBillCardPanel().setBodyValueAt(event.getOldValue(),
          event.getRow(), event.getKey());
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0006")/*
                                                                   * @res
                                                                   * "需求日期不能在申请日期之前！"
                                                                   */);
    }
  }

}

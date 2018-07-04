/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 上午09:51:17
 */
package nc.ui.pu.m4t.editor.head.after;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单据日期，设置折本汇率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 上午09:51:17
 */
public class BillDate implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    Object oldValue = event.getOldValue();
    String pk_org =
        (String) panel.getHeadItem(InitialEstHeaderVO.PK_ORG).getValueObject();

    UFDate value = (UFDate) event.getValue();
    if (ObjectUtil.isEmptyWithTrim(value)) {
      panel.setHeadItem(InitialEstHeaderVO.DBILLDATE, oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0047")/*
                                                                   * @res
                                                                   * "入库日期不能为空！"
                                                                   */);
    }
    // 期初日期取值参数INI02
    UFDate startDate = PUSysParamUtil.getINI02BeforeDate(pk_org);
    if (startDate == null) {
      panel.setHeadItem(InitialEstHeaderVO.DBILLDATE, oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0122")/*
                                                                   * @res
                                                                   * "采购期初日期（参数INI02）未设置！"
                                                                   */);

    }

    if (value.compareTo(startDate.asLocalEnd()) > 0) {
      panel.setHeadItem(InitialEstHeaderVO.DBILLDATE, oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0203", null,
              new String[] {
                value.toLocalString(), startDate.toLocalString()
              })/*
                 * 期初暂估单的入库日期{0}不能晚于采购期初日期的前一天{1}
                 * ！
                 */);
    }
  }
}

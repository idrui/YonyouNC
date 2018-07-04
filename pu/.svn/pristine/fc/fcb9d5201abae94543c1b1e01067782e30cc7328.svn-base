/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 上午07:58:01
 */
package nc.ui.pu.m422x.editor.card.afteredit.header;

import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>申请日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-5 上午07:58:01
 */
public class Dbilldate implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    UFDate newValue = (UFDate) event.getValue();
    if (ObjectUtil.isEmptyWithTrim(newValue)) {
      return;
    }

    BillCardPanel panel = event.getBillCardPanel();
    UFDate latestReqDate = this.getLatestReqDate(panel);

    if (null == latestReqDate) {
      return;
    }

    if (latestReqDate.compareTo(newValue) < 0) {
      panel.setHeadItem(StoreReqAppHeaderVO.DBILLDATE, event.getOldValue());
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4004010_0", "04004010-0016", null, new String[] {
            latestReqDate.toLocalString()
          })/* 最小的需求日期为{0},申请日期不能在这个日期之后! */);
    }
  }

  /**
   * 方法功能描述：最晚需求日期
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-5 上午08:09:39
   */
  private UFDate getLatestReqDate(BillCardPanel panel) {
    int rowcount = panel.getRowCount();
    if (rowcount <= 0) {
      return null;
    }
    UFDate latestReqDate = null;
    for (int i = 0; i < rowcount; ++i) {
      UFDate reqdate =
          (UFDate) panel.getBodyValueAt(i, StoreReqAppItemVO.DREQDATE);
      if (ObjectUtil.isEmptyWithTrim(reqdate)) {
        continue;
      }

      if (null == latestReqDate) {
        latestReqDate = reqdate;
        continue;
      }

      if (reqdate.compareTo(latestReqDate) < 0) {
        latestReqDate = reqdate;
      }
    }

    return latestReqDate;
  }

}

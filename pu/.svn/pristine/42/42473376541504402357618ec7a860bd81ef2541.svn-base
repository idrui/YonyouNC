package nc.ui.pu.m422x.editor.card.afteredit.header;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * 物资需求申请单表头项目编辑后处理
 * 
 * @since 6.35
 * @version 2015-5-8 下午6:16:45
 * @author wandl
 */
public class Project implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 根据表头项目设置表体项目
    this.setBodyProject(event);
  }

  /**
   * 将表头项目设置在表体项目上。
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 下午04:26:34
   */
  private void setBodyProject(CardHeadTailAfterEditEvent event) {
    Object hproject =
        event.getBillCardPanel().getHeadItem(StoreReqAppHeaderVO.PK_PROJECT)
            .getValueObject();

    int row = event.getBillCardPanel().getRowCount();

    for (int i = 0; i < row; i++) {
      event.getBillCardPanel().setBodyValueAt(hproject, i,
          StoreReqAppItemVO.CPROJECTID);
    }

  }
}

package nc.ui.pu.pub.editor.card.beforeedit;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterProjectTaskRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * @since 6.0
 * @version 2011-12-14 上午09:53:46
 * @author wangljc
 */
public class ProjectTaskId implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // "项目任务"字段，可空。项目字段有值时才可参照，参照项目下的项目任务
    BillCardPanel card = event.getBillCardPanel();
    String cprojectid =
        (String) card.getBodyValueAt(event.getRow(),
            PuAttrNameEnum.cprojectid.name());
    if (StringUtil.isEmptyWithTrim(cprojectid)) {
      // 物料未设置，不能编辑
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      FilterProjectTaskRefUtils filter =
          new FilterProjectTaskRefUtils(
              card.getBodyItem(PuAttrNameEnum.cprojecttaskid.name()));
      filter.setCprojectId(cprojectid);
      event.setReturnValue(Boolean.TRUE);
    }
  }

}

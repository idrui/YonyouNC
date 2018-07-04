package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.bd.cbs.CbsNodeOrgAndProjectRefModel;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * CBS 编辑前处理类
 * 
 * @since 6.3
 * @version 2014-3-24 下午04:45:07
 * @author fanly3
 */
public class Cbs implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    String cprojectid =
        (String) panel.getBodyValueAt(event.getRow(),
            StoreReqAppItemVO.CPROJECTID);
    if (cprojectid != null) {
      UIRefPane cbsRefPane =
          (UIRefPane) panel.getBodyItem(StoreReqAppItemVO.CBS).getComponent();
      String pk_org =
          (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_ORG)
              .getValueObject();
      CbsNodeOrgAndProjectRefModel refModel =
          (CbsNodeOrgAndProjectRefModel) cbsRefPane.getRefModel();
      refModel.setPk_org(pk_org);
      refModel.setPk_project(cprojectid);
    }
    else {
      // 项目为空，不能编辑CBS
      event.setReturnValue(Boolean.FALSE);
    }
  }
}

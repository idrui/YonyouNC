package nc.ui.pu.m25.editor.card;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pu.m25.view.InvoiceBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

/**
 * 发票新增按钮触发事件
 * 
 * @since 6.0
 * @version 2011-5-28 上午10:49:05
 * @author 田锋涛
 */

public class InvoiceAddEventHandler implements IAppEventHandler<AddEvent> {

  @Override
  public void handleAppEvent(AddEvent e) {
    InvoiceBillForm billform = (InvoiceBillForm) e.getBillForm();
    AppUiState aus = ((BillManageModel) billform.getModel()).getAppUiState();
    if (AppUiState.TRANSFERBILL_ADD.equals(aus)) {
      return;
    }

    BillCardPanel bcp = billform.getBillCardPanel();
    // 日期
    UFDate busidate = AppContext.getInstance().getBusiDate();
    bcp.setHeadItem(InvoiceHeaderVO.DBILLDATE, busidate);// 发票日期
    bcp.setHeadItem(InvoiceHeaderVO.DARRIVEDATE, busidate);// 票到日期
    // 设置VID
    String pk_org = billform.getModel().getContext().getPk_org();
    this.setVID(pk_org, bcp);

    // 设置集团
    String pk_group = billform.getModel().getContext().getPk_group();
    bcp.setHeadItem(InvoiceHeaderVO.PK_GROUP, pk_group);

  }

  private void setVID(String pk_org, BillCardPanel bcp) {
    String vid = null;
    if (!StringUtil.isEmptyWithTrim(pk_org)) {
      vid = OrgUnitPubService.getOrgVid(pk_org);
      bcp.setHeadItem(InvoiceHeaderVO.PK_ORG_V, vid);
      // 设置采购组织
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.PURCHASEORGTYPE)) {
        bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG).setValue(pk_org);
        bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG_V).setValue(vid);
      }

    }

  }

}

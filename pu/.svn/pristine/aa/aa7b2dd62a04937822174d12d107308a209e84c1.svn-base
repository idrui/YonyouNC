package nc.ui.pu.m25.view;

import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pu.pub.view.PUBillListView;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pubapp.AppContext;

/**
 * 发票列表视图。
 * 
 * @since 6.0
 * @version 2012-5-10 下午04:12:38
 * @author lixyp
 */
public class InvoiceBillListView extends PUBillListView {

  private static final long serialVersionUID = 2773715306357353713L;

  @Override
  public void initUI() {
    super.initUI();

    // lixy 2012.5.10 由于根据供应商是否是散户，决定银行账号和账户参照不同，在列表无法设置参照
    // 这样在列表就只能显示pk。故参考5系列的做法，将这两列通过代码隐藏。
    this.getBillListPanel().hideHeadTableCol(InvoiceHeaderVO.PK_BANKACCBAS);
    this.getBillListPanel().hideHeadTableCol(InvoiceHeaderVO.VBANKACCOUNT);
    new InvoiceUIScaleProcessor().setListScale(this.getBillListPanel(),
        AppContext.getInstance().getPkGroup());
  }

}

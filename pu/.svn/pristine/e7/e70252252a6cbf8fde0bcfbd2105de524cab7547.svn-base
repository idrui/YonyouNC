package nc.ui.pu.m21.view;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.m21.editor.card.afteredit.body.TotalListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 采购订单卡片
 * 
 * @since 6.0
 * @version 2011-4-22 下午04:42:04
 * @author wuxla
 */

public class OrderBillForm extends PUBillForm {

  private static final long serialVersionUID = -5412179538048902934L;

  @Override
  public void initUI() {
    super.initUI();
    this.setBatchCodeRef();
    this.getBillCardPanel().getBodyPanel().setTotalRowShow(true);
    // 初始化银行账户参照
    SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
    custBankUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
    custBankUtil.initCustBankRefPanel(this.getBillCardPanel(),
        UFBoolean.FALSE.booleanValue());
    //
    this.getBillCardPanel().getBillModel()
        .addTotalListener(new TotalListener(this.getBillCardPanel()));
  }

  @Override
  public void setValue(Object object) {
    // 先初始化参照,再将VO设置到界面,再执行其它处理
    String freecustid = "";
    if (object != null) {
      freecustid =
          ((OrderHeaderVO) ((OrderVO) object).getParentVO()).getPk_freecust();
    }
    boolean isFreeCust = StringUtils.isEmpty(freecustid) ? false : true;
    SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
    custBankUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
    custBankUtil.initCustBankRefPanel(this.getBillCardPanel(), isFreeCust);
    super.setValue(object);
    custBankUtil.initCustBankValue(this.getBillCardPanel(), isFreeCust);
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    BatchRefPane pane = new BatchRefPane();
    card.getBodyItem(OrderItemVO.VBATCHCODE).setComponent(pane);
  }

  @Override
  protected void enableFillableItems() {
    super.enableFillableItems();
    // 税率、扣率；
    // 计划到货日期；
    // 需求库存组织、收货库存组织、结算财务组织、应付组织；
    // 项目；
    // 备注、自定义项

    String[] enableItems =
        new String[] {
          OrderItemVO.NTAXRATE, OrderItemVO.NNOSUBTAXRATE,
          OrderItemVO.DPLANARRVDATE, OrderItemVO.PK_REQSTOORG_V,
          OrderItemVO.PK_ARRVSTOORG_V, OrderItemVO.PK_PSFINANCEORG_V,
          OrderItemVO.PK_APFINANCEORG_V, OrderItemVO.CPROJECTID,
          //合并通版补丁NCdp205396511
          OrderItemVO.PK_RECVSTORDOC,
          //无税单价，含税单价支持批改
          OrderItemVO.NQTORIGPRICE,	OrderItemVO.NQTORIGTAXPRICE,
          //
          OrderItemVO.VBMEMO, OrderItemVO.VBDEF1, OrderItemVO.VBDEF2,
          OrderItemVO.VBDEF3, OrderItemVO.VBDEF4, OrderItemVO.VBDEF5,
          OrderItemVO.VBDEF6, OrderItemVO.VBDEF7, OrderItemVO.VBDEF8,
          OrderItemVO.VBDEF9, OrderItemVO.VBDEF10, OrderItemVO.VBDEF11,
          OrderItemVO.VBDEF12, OrderItemVO.VBDEF13, OrderItemVO.VBDEF14,
          OrderItemVO.VBDEF15, OrderItemVO.VBDEF16, OrderItemVO.VBDEF17,
          OrderItemVO.VBDEF18, OrderItemVO.VBDEF19, OrderItemVO.VBDEF20
        };
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // 放开字段的批改
    cardUtils.enableItemsFill(enableItems);
  }

}

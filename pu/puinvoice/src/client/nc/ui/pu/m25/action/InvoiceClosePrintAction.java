package nc.ui.pu.m25.action;

import nc.ui.pubapp.uif2app.actions.PrintDirectAction;
import nc.ui.uif2.model.AbstractUIAppModel;

public class InvoiceClosePrintAction extends PrintDirectAction{

  private static final long serialVersionUID = -7248081339900486070L;
  
  public InvoiceClosePrintAction(AbstractUIAppModel model) {
    super();
    this.model = model;
    String funCode = this.getModel().getContext().getNodeCode();
    if("40041006".equals(funCode)){
      this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0139")/*@res 未审批采购发票*/);
    } else if("40041008".equals(funCode)){
      this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0140")/*@res 未结算采购发票*/);
    }
  }

}

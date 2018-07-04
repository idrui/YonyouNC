package nc.ui.pu.pub.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.ui.bd.ref.model.CustBankaccDefaultRefModel;
import nc.ui.pu.pub.beans.PoFreeCustBankRefModel;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

public class SupplierFreeCustInfoUtil implements IPURemoteCallCombinator {
  private String bankaccbasname = "pk_bankaccbas";

  private String freecustname = "pk_freecust";

  private String orgname = "pk_org";

  private SupplierInfo supplier;

  private String suppliername = "pk_supplier";

  private Token token = null;

  private String vbankaccountname = "vbankaccount";

  public SupplierFreeCustInfoUtil() {
    //
  }

  public SupplierFreeCustInfoUtil(String bankaccbasname, String freecustname,
      String vbankaccountname) {
    this.bankaccbasname = bankaccbasname;
    this.freecustname = freecustname;
    this.vbankaccountname = vbankaccountname;
  }

  /**
   * 散户编辑后,设置银行账户的参照
   * 
   * @param util
   */
  public void afterFreeCust(CardEditorHelper util) {
    UIRefPane bankrefpane =
        (UIRefPane) util.getEditor().getHeadItem(this.getBankaccbasname())
            .getComponent();
    String freecust =
        (String) util.getEditor().getHeadTailItem(this.getFreecustname())
            .getValueObject();
    bankrefpane.setRefModel(new PoFreeCustBankRefModel(freecust));
    bankrefpane.setReturnCode(false);
    bankrefpane.setAutoCheck(true);
    util.getEditor().setHeadItem(this.getBankaccbasname(), freecust);
    bankrefpane.setPK(freecust);
  }

  public String getBankaccbasname() {
    return this.bankaccbasname;
  }

  public String getFreecustname() {
    return this.freecustname;
  }

  public String getOrgname() {
    return this.orgname;
  }

  /**
   * 根据供应商主键获取供应商信息
   * 
   * @param keyValue
   * @return
   */
  public SupplierInfo getSupplierInfo(IKeyValue keyValue) {
    if (null != this.supplier) {
      return this.supplier;
    }
    if (null != this.token) {
      try {
        this.supplier =
            (SupplierInfo) RemoteCallCombinatorEx.getInstance().getResult(
                this.token);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      return this.supplier;
    }
    String pk_supplier = (String) keyValue.getHeadValue(this.getSuppliername());
    if (pk_supplier == null) {
      return null;
    }
    String pk_purchaseorg = (String) keyValue.getHeadValue(this.getOrgname());
    try {
      this.supplier =
          NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
              .querySupplier(pk_supplier, pk_purchaseorg);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return this.supplier;
  }

  public String getSuppliername() {
    return this.suppliername;
  }

  public String getVbankaccountname() {
    return this.vbankaccountname;
  }

  /**
   * 列表切换到卡片时,设置银行账户的参照
   * 
   * @param panel
   * @param freeCust
   */
  public void initCustBankRefPanel(BillCardPanel panel, boolean freeCust) {
    BillItem bankitem = panel.getHeadItem(this.getBankaccbasname());
    UIRefPane bankrefpane = (UIRefPane) bankitem.getComponent();
    if (panel.getHeadItem(this.getBankaccbasname()) != null) {
      String cfreecustid =
          (String) panel.getHeadItem(this.getFreecustname()).getValueObject();
      if (freeCust) {
        bankrefpane.setRefModel(new PoFreeCustBankRefModel(cfreecustid));
        bankrefpane.setReturnCode(false);
        bankrefpane.setAutoCheck(true);
      }
      else {
        CustBankaccDefaultRefModel custBankaccDefaultRefModel =
            new CustBankaccDefaultRefModel();
        custBankaccDefaultRefModel.setRefNodeName("供应商银行账户");/* -=notranslate=- */
        bankrefpane.setRefModel(custBankaccDefaultRefModel);
        bankrefpane.setReturnCode(true);
      }
    }
  }
  
  /**
   * 列表切换到卡片时,设置银行账户的参照
   * 
   * @param panel
   * @param freeCust
   */
  public void initCustBankRefPanel(BillListPanel panel, boolean freeCust) {
    panel.getHeadItem(this.getBankaccbasname()).getValueObject();
    BillItem bankitem = panel.getHeadItem(this.getBankaccbasname());
    UIRefPane bankrefpane = (UIRefPane) bankitem.getComponent();
    if (panel.getHeadItem(this.getBankaccbasname()) != null) {
      String cfreecustid =
          (String) panel.getHeadItem(this.getFreecustname()).getValueObject();
      if (freeCust) {
        bankrefpane.setRefModel(new PoFreeCustBankRefModel(cfreecustid));
        bankrefpane.setReturnCode(false);
        bankrefpane.setAutoCheck(true);
      }
      else {
        CustBankaccDefaultRefModel custBankaccDefaultRefModel =
            new CustBankaccDefaultRefModel();
        custBankaccDefaultRefModel.setRefNodeName("供应商银行账户");/* -=notranslate=- */
        bankrefpane.setRefModel(custBankaccDefaultRefModel);
        bankrefpane.setReturnCode(true);
      }
    }
  }

  /**
   * 列表切换到卡片时,设置银行账号的显示公式
   * 
   * @param panel
   * @param freeCust
   */
  public void initCustBankValue(BillCardPanel panel, boolean freeCust) {
    String[] strFormulas;
    UIRefPane bankrefpane =
        (UIRefPane) panel.getHeadItem(this.getBankaccbasname()).getComponent();
    if (panel.getHeadItem(this.getVbankaccountname()) != null) {
      if (freeCust) {
        // 散户
        String strFormula1 =
            this.getVbankaccountname()
                + "->getColValue(bd_freecustom,bankaccount ,pk_freecustom  ,"
                + this.getFreecustname() + ")";
        strFormulas = new String[] {
          strFormula1
        };
        bankrefpane.setReturnCode(false);
        bankrefpane.setAutoCheck(true);
        panel.getHeadItem(this.getVbankaccountname()).setLoadFormula(
            strFormulas);
      }
      else {
        // 非散户
        String strFormula1 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccsub,pk_bankaccbas ,pk_bankaccsub ,"
                + this.getBankaccbasname() + ")";
        String strFormula2 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccbas,accname ,pk_bankaccbas,"
                + this.getVbankaccountname() + ")";
        strFormulas = new String[] {
          strFormula1, strFormula2
        };
        panel.getHeadItem(this.getVbankaccountname()).setLoadFormula(
            strFormulas);
        panel.getHeadItem(this.getBankaccbasname()).setEditFormula(strFormulas);
      }
      panel.execHeadTailLoadFormulas(panel.getHeadItem(this
          .getVbankaccountname()));
    }
  }

  public void initCustBankValue(BillListPanel panel, boolean freeCust, Boolean[] isload) {

    UIRefPane bankrefpane =
        (UIRefPane) panel.getHeadItem(this.getBankaccbasname()).getComponent();
    if (panel.getHeadItem(this.getVbankaccountname()) != null) {
      if (freeCust) {
        // 散户
        String strFormula1 =
            this.getVbankaccountname()
                + "->getColValue(bd_freecustom,bankaccount ,pk_freecustom  ,"
                + this.getFreecustname() + ")";
        String[] strFormulas = new String[] {
          strFormula1
        };
        bankrefpane.setReturnCode(false);
        bankrefpane.setAutoCheck(true);
        panel.getHeadItem(this.getVbankaccountname()).setLoadFormula(
            strFormulas);
      }
      else {
        // 非散户
        String strFormula1 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccsub,pk_bankaccbas ,pk_bankaccsub ,"
                + this.getBankaccbasname() + ")";
        String strFormula2 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccbas,accname ,pk_bankaccbas,"
                + this.getVbankaccountname() + ")";
        String strFormula3 =
            this.getBankaccbasname()
            + "->getColValue(bd_bankaccbas,accname ,pk_bankaccbas,"
            + this.getVbankaccountname() + ")";
        panel.getHeadItem(this.getVbankaccountname())
        .setLoadFormula(new String[] {strFormula1, strFormula2 });        
        panel.getHeadItem(this.getBankaccbasname())
        .setLoadFormula(new String[] {strFormula1, strFormula3 });
      }
      panel.getHeadBillModel().execLoadFormulasByKey(this.getVbankaccountname());
      if(!isload[0].booleanValue()){
        isload[0] = Boolean.TRUE;
        panel.getHeadBillModel().execLoadFormulasByKey(this.getBankaccbasname());
      }
    }
  }
  
  @Override
  public void prepare() {
    //
  }

  @Override
  public void process() {
    //
  }

  public void registerCombineRemoteCall(IKeyValue keyValue) {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
      this.doInvoke(rcc, keyValue);
    }
    else {
      if (this.doInvoke(rcc, keyValue)) {
        this.token = rcc.getToken();
      }
    }
  }

  public void setBankaccbasname(String bankaccbasname) {
    this.bankaccbasname = bankaccbasname;
  }

  /**
   * 非散户时,设置默认银行账户,银行账号
   * 
   * @param util
   */
  public void setDefaultCustBankInfo(CardEditorHelper util) {

    // 设置默认银行账户,银行账号
    String pk_supplier = util.getHeadStringValue(this.getSuppliername());
    String defaultBankAcc = SupplierPubService.getDefaultBankAcc(pk_supplier);
    util.getEditor().setHeadItem(this.getBankaccbasname(), defaultBankAcc);
    util.getEditor().execHeadTailEditFormulas(
        util.getEditor().getHeadItem(this.getBankaccbasname()));
  }

  /**
   * @param util
   */
  public void setFreeCustBankAcc(CardEditorHelper util) {
    BillItem freecustitem =
        util.getEditor().getHeadTailItem(this.getFreecustname());
    BillItem supplieritem =
        util.getEditor().getHeadTailItem(this.getSuppliername());
    if (freecustitem == null || supplieritem == null) {
      return;
    }
    SupplierInfo supinfo = this.getSupplierInfo(util);
    if (supinfo == null) {
      return;
    }
    UFBoolean isFreeCust = supinfo.getIsFreeCust();
    BillItem bankitem =
        util.getEditor().getHeadTailItem(this.getBankaccbasname());
    if (bankitem == null) {
      return;
    }
    UIRefPane bankrefpane = (UIRefPane) bankitem.getComponent();
    if (UFBoolean.FALSE.equals(isFreeCust)) {
      // 非散户
      // 散户设置
      freecustitem.setEnabled(false);
      freecustitem.setValue(null);
      // 银行账户设置
      bankitem.setValue(null);
      CustBankaccDefaultRefModel custBankaccDefaultRefModel =
          new CustBankaccDefaultRefModel();
      custBankaccDefaultRefModel.setRefNodeName("供应商银行账户");/* -=notranslate=- */
      bankrefpane.setRefModel(custBankaccDefaultRefModel);
      bankrefpane.setReturnCode(true);
      bankrefpane.setEnabled(bankitem.isEdit());
      bankrefpane.setButtonVisible(true);
      // 银行账号设置
      BillItem bankaccountitem =
          util.getEditor().getHeadTailItem(this.getVbankaccountname());
      if (bankaccountitem != null) {
        bankaccountitem.setValue(null);
        String strFormula1 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccsub,pk_bankaccbas ,pk_bankaccsub ,"
                + this.getBankaccbasname() + ")";
        String strFormula2 =
            this.getVbankaccountname()
                + "->getColValue(bd_bankaccbas,accnum ,pk_bankaccbas ,"
                + this.getVbankaccountname() + ")";
        String[] strFormulas = new String[] {
          strFormula1, strFormula2
        };
        bankitem.setEditFormula(strFormulas);
        bankaccountitem.setLoadFormula(strFormulas);
      }

      // 非散户,读取供应商默认账户,设置默认值
      this.setDefaultCustBankInfo(util);
    }
    else {
      // 散户
      // 散户设置
      freecustitem.setEnabled(freecustitem.isEdit());
      freecustitem.setValue(null);
      // 银行账户设置
      bankitem.setValue(null);
      bankrefpane.setEnabled(!freecustitem.isEdit());
      bankrefpane.setButtonVisible(!freecustitem.isEdit());
      bankrefpane.setAutoCheck(true);
      bankrefpane.setReturnCode(false);
      // 银行账号设置
      BillItem bankaccountitem =
          util.getEditor().getHeadTailItem(this.getVbankaccountname());
      if (bankaccountitem != null) {
        bankaccountitem.setValue(null);
        bankaccountitem.setLoadFormula(null);
      }
    }
  }

  public void setFreecustname(String freecustname) {
    this.freecustname = freecustname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public void setSuppliername(String suppliername) {
    this.suppliername = suppliername;
  }

  public void setVbankaccountname(String vbankaccountname) {
    this.vbankaccountname = vbankaccountname;
  }

  private boolean doInvoke(RemoteCallCombinatorEx rcc, IKeyValue keyValue) {
    IOrderSupplierQuery supplierQuery =
        rcc.getService(IOrderSupplierQuery.class);
    String pk_supplier = (String) keyValue.getHeadValue(this.getSuppliername());
    if (pk_supplier == null) {
      return false;
    }
    String pk_purchaseorg = (String) keyValue.getHeadValue(this.getOrgname());
    try {
      supplierQuery.querySupplier(pk_supplier, pk_purchaseorg);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }
}

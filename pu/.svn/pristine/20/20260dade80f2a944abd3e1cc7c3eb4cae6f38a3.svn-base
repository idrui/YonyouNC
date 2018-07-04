package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m20.view.PriceInfoDlg;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pub.print.PrintEntry;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.vo.pu.m20.entity.PrayPriceInfoVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 请购单价格论证表打印
 * 
 * @since 6.0
 * @version 2011-3-21 下午06:38:22
 * @author 田锋涛
 */

public class PraybillPricePrintAction extends MetaDataBasedPrintAction {

  private static final long serialVersionUID = 1L;

  private PriceInfoDlg parentUI;

  private PrintEntry printEntry;

  //
  private PrayPriceInfoVO[] printVOs;

  public PraybillPricePrintAction(PriceInfoDlg parentUI,
      PrayPriceInfoVO[] printVOs) {
    this.printVOs = printVOs;
    this.parentUI = parentUI;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    IMetaDataDataSource[] defaultDataSource = this.getDefaultMetaDataSource();
    if (!VOChecker.isEmpty(defaultDataSource)) {
      for (IMetaDataDataSource dataSourceItem : defaultDataSource) {
        this.getPrintEntry().setDataSource(dataSourceItem);
      }
    }
    this.getPrintEntry().preview();
  }

  public PriceInfoDlg getParentUI() {
    return this.parentUI;
  }

  public PrayPriceInfoVO[] getPrintVOs() {
    return this.printVOs;
  }

  public void setParentUI(PriceInfoDlg parentUI) {
    this.parentUI = parentUI;
  }

  public void setPrintVOs(PrayPriceInfoVO[] printVOs) {
    this.printVOs = printVOs;
  }

  private void setScale(PrayPriceInfoVO[] printVOs) {
    String pk_group = this.getParentUI().getLoginContext().getPk_group();
    ScaleUtils utils = new ScaleUtils(pk_group);
    for (PrayPriceInfoVO printVO : printVOs) {
      UFDouble nlatesPrice =
          utils.adjustSoPuOrgPriceScale(printVO.getNlatestprice(),
              printVO.getPk_org());
      printVO.setNlatestprice(nlatesPrice);
      UFDouble nquoteprice1 =
          utils.adjustSoPuOrgPriceScale(printVO.getNquoteprice1(),
              printVO.getPk_org());
      printVO.setNquoteprice1(nquoteprice1);
      UFDouble nquoteprice2 =
          utils.adjustSoPuOrgPriceScale(printVO.getNquoteprice2(),
              printVO.getPk_org());
      printVO.setNquoteprice2(nquoteprice2);
      UFDouble nquoteprice3 =
          utils.adjustSoPuOrgPriceScale(printVO.getNquoteprice3(),
              printVO.getPk_org());
      printVO.setNquoteprice3(nquoteprice3);
    }
  }

  @Override
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    // 处理精度
    this.setScale(this.getPrintVOs());
    defaultDataSource = new MetaDataSource[] {
      new MetaDataSource(this.getPrintVOs())
    };
    return defaultDataSource;
  }

  @Override
  protected nc.ui.pub.print.PrintEntry getPrintEntry() {
    if (null == this.printEntry) {
      this.printEntry =
          new nc.ui.pub.print.PrintEntry(this.getParentUI(), null);
      // ClientContext ctx = ClientContext.getInstance();
      // this.printEntry.setTemplateID(ctx.getPk_group(), "4004020010", ctx
      // .getLoginUserId(), null);
      // 不支持模板分配
      this.printEntry.setTemplateID("1001Z81000000000099F");
    }
    return this.printEntry;
  }
}

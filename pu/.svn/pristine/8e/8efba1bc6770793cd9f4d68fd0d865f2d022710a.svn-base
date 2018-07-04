package nc.ui.pu.m25.linkquery;

import nc.ui.pubapp.uif2app.event.card.CardBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.event.list.ListBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * @since 6.0
 * @version 2011-7-1 上午10:38:07
 * @author 田锋涛
 */

public class InvoiceLinkQueryHyperlinkMediator {

  private InvoiceCardLinkQueryHyperlinkHandler cardLinkQueryHandler;

  private InvoiceListLinkQueryHyperlinkHandler listLinkQueryHandler;

  private AbstractUIAppModel model;

  private String srcBillIdField;

  /**
   * 超链字段名
   */
  private String srcBillNOField;

  private String srcBillType;

  private String srcBillTypeField;

  /**
   * 存储源单据类型的字段所在的位置，0：表头；1：表体
   */
  private Integer srcBillTypeFieldPos;

  public InvoiceLinkQueryHyperlinkMediator() {
    super();
    this.cardLinkQueryHandler = new InvoiceCardLinkQueryHyperlinkHandler();
    this.listLinkQueryHandler = new InvoiceListLinkQueryHyperlinkHandler();
  }

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public String getSrcBillIdField() {
    return this.srcBillIdField;
  }

  public String getSrcBillNOField() {
    return this.srcBillNOField;
  }

  public String getSrcBillType() {
    return this.srcBillType;
  }

  public String getSrcBillTypeField() {
    return this.srcBillTypeField;
  }

  public Integer getSrcBillTypeFieldPos() {
    return this.srcBillTypeFieldPos;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
    this.cardLinkQueryHandler.setModel(model);
    this.listLinkQueryHandler.setModel(model);
    ((IAppModelEx) this.getModel()).addAppEventListener(
        ListBillItemHyperlinkEvent.class, this.listLinkQueryHandler);
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBillItemHyperlinkEvent.class, this.cardLinkQueryHandler);
  }

  public void setSrcBillIdField(String srcBillIdField) {
    this.srcBillIdField = srcBillIdField;
    this.cardLinkQueryHandler.setSrcBillIDField(srcBillIdField);
    this.listLinkQueryHandler.setSrcBillIDField(srcBillIdField);
  }

  public void setSrcBillNOField(String srcBillNOField) {
    this.srcBillNOField = srcBillNOField;
    this.cardLinkQueryHandler.setSrcBillNOField(srcBillNOField);
    this.listLinkQueryHandler.setSrcBillNOField(srcBillNOField);
  }

  public void setSrcBillType(String srcBillType) {
    this.srcBillType = srcBillType;
    this.cardLinkQueryHandler.setSrcBillType(srcBillType);
    this.listLinkQueryHandler.setSrcBillType(srcBillType);
  }

  public void setSrcBillTypeField(String srcBillTypeField) {
    this.srcBillTypeField = srcBillTypeField;
    this.cardLinkQueryHandler.setSrcBillTypeField(srcBillTypeField);
    this.listLinkQueryHandler.setSrcBillTypeField(srcBillTypeField);
  }

  public void setSrcBillTypeFieldPos(Integer srcBillTypeFieldPos) {
    this.srcBillTypeFieldPos = srcBillTypeFieldPos;
    this.cardLinkQueryHandler.setSrcBillTypeFieldPos(srcBillTypeFieldPos);
    this.listLinkQueryHandler.setSrcBillTypeFieldPos(srcBillTypeFieldPos);
  }
}

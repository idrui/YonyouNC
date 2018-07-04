package nc.ui.pu.m21.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class orderclose_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pu.m21.view.OrderCloseQueryDLGInitializer getOrderQryDLGInitializer(){
 if(context.get("orderQryDLGInitializer")!=null)
 return (nc.ui.pu.m21.view.OrderCloseQueryDLGInitializer)context.get("orderQryDLGInitializer");
  nc.ui.pu.m21.view.OrderCloseQueryDLGInitializer bean = new nc.ui.pu.m21.view.OrderCloseQueryDLGInitializer();
  context.put("orderQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.CloseQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.CloseQueryAction)context.get("queryAction");
  nc.ui.pu.m21.action.orderclose.CloseQueryAction bean = new nc.ui.pu.m21.action.orderclose.CloseQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
  bean.setOrgPanel(getOrgPanel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getOrderQryDLGInitializer());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.CloseRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.CloseRefreshAction)context.get("refreshAction");
  nc.ui.pu.m21.action.orderclose.CloseRefreshAction bean = new nc.ui.pu.m21.action.orderclose.CloseRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allclose.AllCloseMenuAction getAllBillCloseMenuAction(){
 if(context.get("allBillCloseMenuAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.AllCloseMenuAction)context.get("allBillCloseMenuAction");
  nc.ui.pu.m21.action.orderclose.allclose.AllCloseMenuAction bean = new nc.ui.pu.m21.action.orderclose.allclose.AllCloseMenuAction();
  context.put("allBillCloseMenuAction",bean);
  bean.setActions(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getFinalAllCloseAction());  list.add(getArriveAllCloseAction());  list.add(getStoreAllCloseAction());  list.add(getInvoiceAllCloseAction());  list.add(getPayAllCloseAction());  return list;}

public nc.ui.pu.m21.action.orderclose.allclose.FinalAllCloseAction getFinalAllCloseAction(){
 if(context.get("finalAllCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.FinalAllCloseAction)context.get("finalAllCloseAction");
  nc.ui.pu.m21.action.orderclose.allclose.FinalAllCloseAction bean = new nc.ui.pu.m21.action.orderclose.allclose.FinalAllCloseAction();
  context.put("finalAllCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allclose.ArriveAllCloseAction getArriveAllCloseAction(){
 if(context.get("arriveAllCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.ArriveAllCloseAction)context.get("arriveAllCloseAction");
  nc.ui.pu.m21.action.orderclose.allclose.ArriveAllCloseAction bean = new nc.ui.pu.m21.action.orderclose.allclose.ArriveAllCloseAction();
  context.put("arriveAllCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allclose.StoreAllCloseAction getStoreAllCloseAction(){
 if(context.get("storeAllCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.StoreAllCloseAction)context.get("storeAllCloseAction");
  nc.ui.pu.m21.action.orderclose.allclose.StoreAllCloseAction bean = new nc.ui.pu.m21.action.orderclose.allclose.StoreAllCloseAction();
  context.put("storeAllCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allclose.InvoiceAllCloseAction getInvoiceAllCloseAction(){
 if(context.get("invoiceAllCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.InvoiceAllCloseAction)context.get("invoiceAllCloseAction");
  nc.ui.pu.m21.action.orderclose.allclose.InvoiceAllCloseAction bean = new nc.ui.pu.m21.action.orderclose.allclose.InvoiceAllCloseAction();
  context.put("invoiceAllCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allclose.PayAllCloseAction getPayAllCloseAction(){
 if(context.get("payAllCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allclose.PayAllCloseAction)context.get("payAllCloseAction");
  nc.ui.pu.m21.action.orderclose.allclose.PayAllCloseAction bean = new nc.ui.pu.m21.action.orderclose.allclose.PayAllCloseAction();
  context.put("payAllCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allopen.AllOpenMenuAction getAllBillOpenMenuAction(){
 if(context.get("allBillOpenMenuAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.AllOpenMenuAction)context.get("allBillOpenMenuAction");
  nc.ui.pu.m21.action.orderclose.allopen.AllOpenMenuAction bean = new nc.ui.pu.m21.action.orderclose.allopen.AllOpenMenuAction();
  context.put("allBillOpenMenuAction",bean);
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getFinalAllOpenAction());  list.add(getArriveAllOpenAction());  list.add(getStoreAllOpenAction());  list.add(getInvoiceAllOpenAction());  list.add(getPayAllOpenAction());  return list;}

public nc.ui.pu.m21.action.orderclose.allopen.FinalAllOpenAction getFinalAllOpenAction(){
 if(context.get("finalAllOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.FinalAllOpenAction)context.get("finalAllOpenAction");
  nc.ui.pu.m21.action.orderclose.allopen.FinalAllOpenAction bean = new nc.ui.pu.m21.action.orderclose.allopen.FinalAllOpenAction();
  context.put("finalAllOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allopen.ArriveAllOpenAction getArriveAllOpenAction(){
 if(context.get("arriveAllOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.ArriveAllOpenAction)context.get("arriveAllOpenAction");
  nc.ui.pu.m21.action.orderclose.allopen.ArriveAllOpenAction bean = new nc.ui.pu.m21.action.orderclose.allopen.ArriveAllOpenAction();
  context.put("arriveAllOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allopen.StoreAllOpenAction getStoreAllOpenAction(){
 if(context.get("storeAllOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.StoreAllOpenAction)context.get("storeAllOpenAction");
  nc.ui.pu.m21.action.orderclose.allopen.StoreAllOpenAction bean = new nc.ui.pu.m21.action.orderclose.allopen.StoreAllOpenAction();
  context.put("storeAllOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allopen.InvoiceAllOpenAction getInvoiceAllOpenAction(){
 if(context.get("invoiceAllOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.InvoiceAllOpenAction)context.get("invoiceAllOpenAction");
  nc.ui.pu.m21.action.orderclose.allopen.InvoiceAllOpenAction bean = new nc.ui.pu.m21.action.orderclose.allopen.InvoiceAllOpenAction();
  context.put("invoiceAllOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.allopen.PayAllOpenAction getPayAllOpenAction(){
 if(context.get("payAllOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.allopen.PayAllOpenAction)context.get("payAllOpenAction");
  nc.ui.pu.m21.action.orderclose.allopen.PayAllOpenAction bean = new nc.ui.pu.m21.action.orderclose.allopen.PayAllOpenAction();
  context.put("payAllOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowclose.RowCloseMenuAction getRowCloseMenuAction(){
 if(context.get("rowCloseMenuAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.RowCloseMenuAction)context.get("rowCloseMenuAction");
  nc.ui.pu.m21.action.orderclose.rowclose.RowCloseMenuAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.RowCloseMenuAction();
  context.put("rowCloseMenuAction",bean);
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getRowCloseAction());  list.add(getArriveRowCloseAction());  list.add(getStoreRowCloseAction());  list.add(getInvoiceRowCloseAction());  list.add(getPayRowCloseAction());  return list;}

public nc.ui.pu.m21.action.orderclose.rowclose.RowCloseAction getRowCloseAction(){
 if(context.get("rowCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.RowCloseAction)context.get("rowCloseAction");
  nc.ui.pu.m21.action.orderclose.rowclose.RowCloseAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.RowCloseAction();
  context.put("rowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowclose.ArriveRowCloseAction getArriveRowCloseAction(){
 if(context.get("arriveRowCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.ArriveRowCloseAction)context.get("arriveRowCloseAction");
  nc.ui.pu.m21.action.orderclose.rowclose.ArriveRowCloseAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.ArriveRowCloseAction();
  context.put("arriveRowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowclose.StoreRowCloseAction getStoreRowCloseAction(){
 if(context.get("storeRowCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.StoreRowCloseAction)context.get("storeRowCloseAction");
  nc.ui.pu.m21.action.orderclose.rowclose.StoreRowCloseAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.StoreRowCloseAction();
  context.put("storeRowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowclose.InvoiceRowCloseAction getInvoiceRowCloseAction(){
 if(context.get("invoiceRowCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.InvoiceRowCloseAction)context.get("invoiceRowCloseAction");
  nc.ui.pu.m21.action.orderclose.rowclose.InvoiceRowCloseAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.InvoiceRowCloseAction();
  context.put("invoiceRowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowclose.PayRowCloseAction getPayRowCloseAction(){
 if(context.get("payRowCloseAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowclose.PayRowCloseAction)context.get("payRowCloseAction");
  nc.ui.pu.m21.action.orderclose.rowclose.PayRowCloseAction bean = new nc.ui.pu.m21.action.orderclose.rowclose.PayRowCloseAction();
  context.put("payRowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowopen.RowOpenMenuAction getRowOpenMenuAction(){
 if(context.get("rowOpenMenuAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.RowOpenMenuAction)context.get("rowOpenMenuAction");
  nc.ui.pu.m21.action.orderclose.rowopen.RowOpenMenuAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.RowOpenMenuAction();
  context.put("rowOpenMenuAction",bean);
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getRowOpenAction());  list.add(getArriveRowOpenAction());  list.add(getStoreRowOpenAction());  list.add(getInvoiceRowOpenAction());  list.add(getPayRowOpenAction());  return list;}

public nc.ui.pu.m21.action.orderclose.rowopen.RowOpenAction getRowOpenAction(){
 if(context.get("rowOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.RowOpenAction)context.get("rowOpenAction");
  nc.ui.pu.m21.action.orderclose.rowopen.RowOpenAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.RowOpenAction();
  context.put("rowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowopen.ArriveRowOpenAction getArriveRowOpenAction(){
 if(context.get("arriveRowOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.ArriveRowOpenAction)context.get("arriveRowOpenAction");
  nc.ui.pu.m21.action.orderclose.rowopen.ArriveRowOpenAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.ArriveRowOpenAction();
  context.put("arriveRowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowopen.StoreRowOpenAction getStoreRowOpenAction(){
 if(context.get("storeRowOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.StoreRowOpenAction)context.get("storeRowOpenAction");
  nc.ui.pu.m21.action.orderclose.rowopen.StoreRowOpenAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.StoreRowOpenAction();
  context.put("storeRowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowopen.InvoiceRowOpenAction getInvoiceRowOpenAction(){
 if(context.get("invoiceRowOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.InvoiceRowOpenAction)context.get("invoiceRowOpenAction");
  nc.ui.pu.m21.action.orderclose.rowopen.InvoiceRowOpenAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.InvoiceRowOpenAction();
  context.put("invoiceRowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.rowopen.PayRowOpenAction getPayRowOpenAction(){
 if(context.get("payRowOpenAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.rowopen.PayRowOpenAction)context.get("payRowOpenAction");
  nc.ui.pu.m21.action.orderclose.rowopen.PayRowOpenAction bean = new nc.ui.pu.m21.action.orderclose.rowopen.PayRowOpenAction();
  context.put("payRowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.CloseLinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.CloseLinkQueryAction)context.get("linkQueryAction");
  nc.ui.pu.m21.action.orderclose.CloseLinkQueryAction bean = new nc.ui.pu.m21.action.orderclose.CloseLinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillType("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.LinkBillMenuAction getCloseLinkBillAction(){
 if(context.get("closeLinkBillAction")!=null)
 return (nc.ui.pu.m21.action.LinkBillMenuAction)context.get("closeLinkBillAction");
  nc.ui.pu.m21.action.LinkBillMenuAction bean = new nc.ui.pu.m21.action.LinkBillMenuAction();
  context.put("closeLinkBillAction",bean);
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getLinkQueryAction());  return list;}

public nc.ui.pu.m21.action.processor.OrderPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.m21.action.processor.OrderPrintProcessor)context.get("printProcessor");
  nc.ui.pu.m21.action.processor.OrderPrintProcessor bean = new nc.ui.pu.m21.action.processor.OrderPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.ClosePrintAction getClosePreviewAction(){
 if(context.get("closePreviewAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.ClosePrintAction)context.get("closePreviewAction");
  nc.ui.pu.m21.action.orderclose.ClosePrintAction bean = new nc.ui.pu.m21.action.orderclose.ClosePrintAction();
  context.put("closePreviewAction",bean);
  bean.setCode("previewAction");
  bean.setPreview(true);
  bean.setModel(getBatchBillTableModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.orderclose.ClosePrintAction getClosePrintAction(){
 if(context.get("closePrintAction")!=null)
 return (nc.ui.pu.m21.action.orderclose.ClosePrintAction)context.get("closePrintAction");
  nc.ui.pu.m21.action.orderclose.ClosePrintAction bean = new nc.ui.pu.m21.action.orderclose.ClosePrintAction();
  context.put("closePrintAction",bean);
  bean.setCode("printAction");
  bean.setPreview(false);
  bean.setModel(getBatchBillTableModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setCode("printMenuAction");
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getClosePrintAction());  list.add(getClosePreviewAction());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getUserdefQueryParam_2156dd());  list.add(getUserdefQueryParam_cde2ee());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2156dd(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#2156dd")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#2156dd");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#2156dd",bean);
  bean.setRulecode("21_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_cde2ee(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#cde2ee")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#cde2ee");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#cde2ee",bean);
  bean.setRulecode("21_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("material");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getUserdefQueryParam_18b1464());  list.add(getUserdefQueryParam_1e7d4e7());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_18b1464(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#18b1464")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#18b1464");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#18b1464",bean);
  bean.setMdfullname("pu.po_order");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1e7d4e7(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1e7d4e7")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1e7d4e7");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1e7d4e7",bean);
  bean.setMdfullname("pu.po_order_b");
  bean.setPos(1);
  bean.setTabcode("material");
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
  bean.setProjectField("cprojectid");
  bean.setProductorField("cproductorid");
  bean.setCustomerField("casscustid");
  bean.setSignatureField("cffileid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getQueryParam_14aa13c());  list.add(getQueryParam_9f92cf());  list.add(getQueryParam_5f6aec());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_14aa13c(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#14aa13c")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#14aa13c");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#14aa13c",bean);
  bean.setMdfullname("pu.po_order");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_9f92cf(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#9f92cf")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#9f92cf");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#9f92cf",bean);
  bean.setMdfullname("pu.po_order_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_5f6aec(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#5f6aec")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#5f6aec");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#5f6aec",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getBatchBillTableModel());
  bean.setQueryAction(getQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
 if(context.get("queryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("queryTemplateContainer",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderCloseService getBatchModelService(){
 if(context.get("batchModelService")!=null)
 return (nc.ui.pu.m21.service.OrderCloseService)context.get("batchModelService");
  nc.ui.pu.m21.service.OrderCloseService bean = new nc.ui.pu.m21.service.OrderCloseService();
  context.put("batchModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
  bean.setId_field("pk_order_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderCloseManageModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pu.m21.service.OrderCloseManageModel)context.get("batchBillTableModel");
  nc.ui.pu.m21.service.OrderCloseManageModel bean = new nc.ui.pu.m21.service.OrderCloseManageModel();
  context.put("batchBillTableModel",bean);
  bean.setContext(getContext());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setService(getBatchModelService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.OrderCloseBillValueSetter getValueSetter(){
 if(context.get("valueSetter")!=null)
 return (nc.ui.pu.m21.view.OrderCloseBillValueSetter)context.get("valueSetter");
  nc.ui.pu.m21.view.OrderCloseBillValueSetter bean = new nc.ui.pu.m21.view.OrderCloseBillValueSetter();
  context.put("valueSetter",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.OrderCloseOrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pu.m21.view.OrderCloseOrgPanel)context.get("orgPanel");
  nc.ui.pu.m21.view.OrderCloseOrgPanel bean = new nc.ui.pu.m21.view.OrderCloseOrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
  bean.setType("²É¹º×éÖ¯");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.value.BillListPanelMetaDataValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.uif2.editor.value.BillListPanelMetaDataValueAdapter)context.get("componentValueManager");
  nc.ui.uif2.editor.value.BillListPanelMetaDataValueAdapter bean = new nc.ui.uif2.editor.value.BillListPanelMetaDataValueAdapter();
  context.put("componentValueManager",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.OrderCloseListView getList(){
 if(context.get("list")!=null)
 return (nc.ui.pu.m21.view.OrderCloseListView)context.get("list");
  nc.ui.pu.m21.view.OrderCloseListView bean = new nc.ui.pu.m21.view.OrderCloseListView();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillListPanelValueSetter(getValueSetter());
  bean.setMultiSelectionEnable(true);
  bean.setMultiSelectionMode(1);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getHSNode_4e4395());
  bean.setActions(getManagedList12());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_4e4395(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#4e4395")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#4e4395");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#4e4395",bean);
  bean.setLeft(getCNode_64bc8e());
  bean.setRight(getVSNode_5fa12b());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_64bc8e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#64bc8e")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#64bc8e");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#64bc8e",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_5fa12b(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#5fa12b")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#5fa12b");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#5fa12b",bean);
  bean.setUp(getCNode_1973bf8());
  bean.setDown(getCNode_1e2365f());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1973bf8(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1973bf8")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1973bf8");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1973bf8",bean);
  bean.setComponent(getOrgPanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1e2365f(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1e2365f")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1e2365f");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1e2365f",bean);
  bean.setComponent(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getAllBillCloseMenuAction());  list.add(getAllBillOpenMenuAction());  list.add(getRowCloseMenuAction());  list.add(getRowOpenMenuAction());  list.add(getSeparatorAction());  list.add(getCloseLinkBillAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

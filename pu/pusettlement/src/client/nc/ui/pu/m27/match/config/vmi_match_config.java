package nc.ui.pu.m27.match.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class vmi_match_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pu.m27.match.view.query.BaseInvoicStlQryDlgInitializer getInvoiceQryDLGInitializer(){
 if(context.get("invoiceQryDLGInitializer")!=null)
 return (nc.ui.pu.m27.match.view.query.BaseInvoicStlQryDlgInitializer)context.get("invoiceQryDLGInitializer");
  nc.ui.pu.m27.match.view.query.BaseInvoicStlQryDlgInitializer bean = new nc.ui.pu.m27.match.view.query.BaseInvoicStlQryDlgInitializer();
  context.put("invoiceQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.InvoiceQueryActionForVmi getInvoiceQueryAction(){
 if(context.get("invoiceQueryAction")!=null)
 return (nc.ui.pu.m27.match.action.InvoiceQueryActionForVmi)context.get("invoiceQueryAction");
  nc.ui.pu.m27.match.action.InvoiceQueryActionForVmi bean = new nc.ui.pu.m27.match.action.InvoiceQueryActionForVmi();
  context.put("invoiceQueryAction",bean);
  bean.setQryCondDLGInitializer(getInvoiceQryDLGInitializer());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004140202");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.view.query.VmiSettleQryDlgInitializer getVmiQryDLGInitializer(){
 if(context.get("vmiQryDLGInitializer")!=null)
 return (nc.ui.pu.m27.match.view.query.VmiSettleQryDlgInitializer)context.get("vmiQryDLGInitializer");
  nc.ui.pu.m27.match.view.query.VmiSettleQryDlgInitializer bean = new nc.ui.pu.m27.match.view.query.VmiSettleQryDlgInitializer();
  context.put("vmiQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.VmiQueryAction2 getVmiQueryAction(){
 if(context.get("vmiQueryAction")!=null)
 return (nc.ui.pu.m27.match.action.VmiQueryAction2)context.get("vmiQueryAction");
  nc.ui.pu.m27.match.action.VmiQueryAction2 bean = new nc.ui.pu.m27.match.action.VmiQueryAction2();
  context.put("vmiQueryAction",bean);
  bean.setQryCondDLGInitializer(getVmiQryDLGInitializer());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.VmiMatchAction getVmiMatchAction(){
 if(context.get("vmiMatchAction")!=null)
 return (nc.ui.pu.m27.match.action.VmiMatchAction)context.get("vmiMatchAction");
  nc.ui.pu.m27.match.action.VmiMatchAction bean = new nc.ui.pu.m27.match.action.VmiMatchAction();
  context.put("vmiMatchAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.FeeMatchAction getFeeMatchAction(){
 if(context.get("feeMatchAction")!=null)
 return (nc.ui.pu.m27.match.action.FeeMatchAction)context.get("feeMatchAction");
  nc.ui.pu.m27.match.action.FeeMatchAction bean = new nc.ui.pu.m27.match.action.FeeMatchAction();
  context.put("feeMatchAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.VmiFeeDistributeAction getFeeDistributeAction(){
 if(context.get("feeDistributeAction")!=null)
 return (nc.ui.pu.m27.match.action.VmiFeeDistributeAction)context.get("feeDistributeAction");
  nc.ui.pu.m27.match.action.VmiFeeDistributeAction bean = new nc.ui.pu.m27.match.action.VmiFeeDistributeAction();
  context.put("feeDistributeAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getProcessView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.MatchAction getMatchAction(){
 if(context.get("matchAction")!=null)
 return (nc.ui.pu.m27.match.action.MatchAction)context.get("matchAction");
  nc.ui.pu.m27.match.action.MatchAction bean = new nc.ui.pu.m27.match.action.MatchAction();
  context.put("matchAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getProcessView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.MatchBackAction getMatchBackAction(){
 if(context.get("matchBackAction")!=null)
 return (nc.ui.pu.m27.match.action.MatchBackAction)context.get("matchBackAction");
  nc.ui.pu.m27.match.action.MatchBackAction bean = new nc.ui.pu.m27.match.action.MatchBackAction();
  context.put("matchBackAction",bean);
  bean.setListView(getQueryView());
  bean.setModel(getManageAppModel());
  bean.setInvoiceQueryAction(getInvoiceQueryAction());
  bean.setStockQueryAction(getVmiQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.LinkQueryVmiAction getLinkQueryVmi(){
 if(context.get("linkQueryVmi")!=null)
 return (nc.ui.pu.m27.match.action.LinkQueryVmiAction)context.get("linkQueryVmi");
  nc.ui.pu.m27.match.action.LinkQueryVmiAction bean = new nc.ui.pu.m27.match.action.LinkQueryVmiAction();
  context.put("linkQueryVmi",bean);
  bean.setListView(getResultView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.LinkQueryInvoiceAction getLinkQueryInvoice(){
 if(context.get("linkQueryInvoice")!=null)
 return (nc.ui.pu.m27.match.action.LinkQueryInvoiceAction)context.get("linkQueryInvoice");
  nc.ui.pu.m27.match.action.LinkQueryInvoiceAction bean = new nc.ui.pu.m27.match.action.LinkQueryInvoiceAction();
  context.put("linkQueryInvoice",bean);
  bean.setListView(getResultView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.action.CancelMatchAction getCancelMatchAction(){
 if(context.get("cancelMatchAction")!=null)
 return (nc.ui.pu.m27.match.action.CancelMatchAction)context.get("cancelMatchAction");
  nc.ui.pu.m27.match.action.CancelMatchAction bean = new nc.ui.pu.m27.match.action.CancelMatchAction();
  context.put("cancelMatchAction",bean);
  bean.setInvoiceQueryAction(getInvoiceQueryAction());
  bean.setStockQueryAction(getVmiQueryAction());
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

public nc.ui.pu.m27.match.action.SettleBillBackAction getSettleBillBackAction(){
 if(context.get("settleBillBackAction")!=null)
 return (nc.ui.pu.m27.match.action.SettleBillBackAction)context.get("settleBillBackAction");
  nc.ui.pu.m27.match.action.SettleBillBackAction bean = new nc.ui.pu.m27.match.action.SettleBillBackAction();
  context.put("settleBillBackAction",bean);
  bean.setInvoiceQueryAction(getInvoiceQueryAction());
  bean.setStockQueryAction(getVmiQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getActionsOfQueryView());  list.add(getActionsOfProcessView());  list.add(getActionsOfResultView());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfQueryView(){
 if(context.get("actionsOfQueryView")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfQueryView");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getQueryView());  context.put("actionsOfQueryView",bean);
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getInvoiceQueryAction());  list.add(getVmiQueryAction());  list.add(getSeparatorAction());  list.add(getVmiMatchAction());  list.add(getFeeMatchAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfProcessView(){
 if(context.get("actionsOfProcessView")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfProcessView");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getProcessView());  context.put("actionsOfProcessView",bean);
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getFeeDistributeAction());  list.add(getMatchAction());  list.add(getMatchBackAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfResultView(){
 if(context.get("actionsOfResultView")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfResultView");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getResultView());  context.put("actionsOfResultView",bean);
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getLinkQueryVmi());  list.add(getLinkQueryInvoice());  list.add(getCancelMatchAction());  list.add(getSettleBillBackAction());  return list;}

public nc.ui.pu.m27.match.editor.org.OrgChangedEventHandler getFinanceOrgainzation(){
 if(context.get("financeOrgainzation")!=null)
 return (nc.ui.pu.m27.match.editor.org.OrgChangedEventHandler)context.get("financeOrgainzation");
  nc.ui.pu.m27.match.editor.org.OrgChangedEventHandler bean = new nc.ui.pu.m27.match.editor.org.OrgChangedEventHandler();
  context.put("financeOrgainzation",bean);
  bean.setListView(getQueryView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.ListHeadAfterEditEventHandler getListHeadAfterEdit(){
 if(context.get("listHeadAfterEdit")!=null)
 return (nc.ui.pu.m27.match.editor.list.ListHeadAfterEditEventHandler)context.get("listHeadAfterEdit");
  nc.ui.pu.m27.match.editor.list.ListHeadAfterEditEventHandler bean = new nc.ui.pu.m27.match.editor.list.ListHeadAfterEditEventHandler();
  context.put("listHeadAfterEdit",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.ListBodyAfterEditEventHandler getListBodyAfterEdit(){
 if(context.get("listBodyAfterEdit")!=null)
 return (nc.ui.pu.m27.match.editor.list.ListBodyAfterEditEventHandler)context.get("listBodyAfterEdit");
  nc.ui.pu.m27.match.editor.list.ListBodyAfterEditEventHandler bean = new nc.ui.pu.m27.match.editor.list.ListBodyAfterEditEventHandler();
  context.put("listBodyAfterEdit",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.InvoiceDisplayEventHandler getInvoiceDisplayHandler(){
 if(context.get("invoiceDisplayHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.InvoiceDisplayEventHandler)context.get("invoiceDisplayHandler");
  nc.ui.pu.m27.match.editor.list.InvoiceDisplayEventHandler bean = new nc.ui.pu.m27.match.editor.list.InvoiceDisplayEventHandler();
  context.put("invoiceDisplayHandler",bean);
  bean.setListView(getQueryView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.StockDisplayEventHandler getStockDisplayHandler(){
 if(context.get("stockDisplayHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.StockDisplayEventHandler)context.get("stockDisplayHandler");
  nc.ui.pu.m27.match.editor.list.StockDisplayEventHandler bean = new nc.ui.pu.m27.match.editor.list.StockDisplayEventHandler();
  context.put("stockDisplayHandler",bean);
  bean.setListView(getQueryView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.InvoiceSelectEventHandler getInvoiceSelectHandler(){
 if(context.get("invoiceSelectHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.InvoiceSelectEventHandler)context.get("invoiceSelectHandler");
  nc.ui.pu.m27.match.editor.list.InvoiceSelectEventHandler bean = new nc.ui.pu.m27.match.editor.list.InvoiceSelectEventHandler();
  context.put("invoiceSelectHandler",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.StockSelectEventHandler getStockSelectHandler(){
 if(context.get("stockSelectHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.StockSelectEventHandler)context.get("stockSelectHandler");
  nc.ui.pu.m27.match.editor.list.StockSelectEventHandler bean = new nc.ui.pu.m27.match.editor.list.StockSelectEventHandler();
  context.put("stockSelectHandler",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.MatchDisplayEventHandler getMatchDisplayHandler(){
 if(context.get("matchDisplayHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.MatchDisplayEventHandler)context.get("matchDisplayHandler");
  nc.ui.pu.m27.match.editor.list.MatchDisplayEventHandler bean = new nc.ui.pu.m27.match.editor.list.MatchDisplayEventHandler();
  context.put("matchDisplayHandler",bean);
  bean.setListView(getProcessView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.SettleBillDisplayEventHandler getSettleBillDisplayHandler(){
 if(context.get("settleBillDisplayHandler")!=null)
 return (nc.ui.pu.m27.match.editor.list.SettleBillDisplayEventHandler)context.get("settleBillDisplayHandler");
  nc.ui.pu.m27.match.editor.list.SettleBillDisplayEventHandler bean = new nc.ui.pu.m27.match.editor.list.SettleBillDisplayEventHandler();
  context.put("settleBillDisplayHandler",bean);
  bean.setListView(getResultView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.SettleBillHeadRowChangeEventHandler getSettleBillHeadRowChange(){
 if(context.get("settleBillHeadRowChange")!=null)
 return (nc.ui.pu.m27.match.editor.list.SettleBillHeadRowChangeEventHandler)context.get("settleBillHeadRowChange");
  nc.ui.pu.m27.match.editor.list.SettleBillHeadRowChangeEventHandler bean = new nc.ui.pu.m27.match.editor.list.SettleBillHeadRowChangeEventHandler();
  context.put("settleBillHeadRowChange",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.editor.list.SettleBillBodyRowChangeEventHandler getSettleBillBodyRowChange(){
 if(context.get("settleBillBodyRowChange")!=null)
 return (nc.ui.pu.m27.match.editor.list.SettleBillBodyRowChangeEventHandler)context.get("settleBillBodyRowChange");
  nc.ui.pu.m27.match.editor.list.SettleBillBodyRowChangeEventHandler bean = new nc.ui.pu.m27.match.editor.list.SettleBillBodyRowChangeEventHandler();
  context.put("settleBillBodyRowChange",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList4());  map.put("nc.ui.pu.m27.match.editor.event.InvoiceDisplayEvent",getManagedList5());  map.put("nc.ui.pu.m27.match.editor.event.StockDisplayEvent",getManagedList6());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent",getManagedList7());  map.put("nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent",getManagedList8());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent",getManagedList9());  map.put("nc.ui.pubapp.uif2app.event.list.ListBodyRowStateChangeEvent",getManagedList10());  map.put("nc.ui.pu.m27.match.editor.event.MatchDisplayEvent",getManagedList11());  map.put("nc.ui.pu.m27.match.editor.event.SettleBillDisplayEvent",getManagedList12());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList13());  map.put("nc.ui.pubapp.uif2app.event.list.ListBodyRowChangedEvent",getManagedList14());  return map;}

private List getManagedList4(){  List list = new ArrayList();  list.add(getFinanceOrgainzation());  return list;}

private List getManagedList5(){  List list = new ArrayList();  list.add(getInvoiceDisplayHandler());  return list;}

private List getManagedList6(){  List list = new ArrayList();  list.add(getStockDisplayHandler());  return list;}

private List getManagedList7(){  List list = new ArrayList();  list.add(getListHeadAfterEdit());  return list;}

private List getManagedList8(){  List list = new ArrayList();  list.add(getListBodyAfterEdit());  return list;}

private List getManagedList9(){  List list = new ArrayList();  list.add(getInvoiceSelectHandler());  return list;}

private List getManagedList10(){  List list = new ArrayList();  list.add(getStockSelectHandler());  return list;}

private List getManagedList11(){  List list = new ArrayList();  list.add(getMatchDisplayHandler());  return list;}

private List getManagedList12(){  List list = new ArrayList();  list.add(getSettleBillDisplayHandler());  return list;}

private List getManagedList13(){  List list = new ArrayList();  list.add(getSettleBillHeadRowChange());  return list;}

private List getManagedList14(){  List list = new ArrayList();  list.add(getSettleBillBodyRowChange());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
  bean.setStoreStateField("cstateid");
  bean.setProjectField("cprojectid");
  bean.setProductorField("cproductorid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer bean = new nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList16());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add(getQueryParam_8ed64a());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_8ed64a(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#8ed64a")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#8ed64a");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#8ed64a",bean);
  bean.setRulecode("materialassistant");
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

public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.smart.BillAppModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.pubapp.pub.smart.BillAppModelService)context.get("manageModelService");
  nc.ui.pubapp.pub.smart.BillAppModelService bean = new nc.ui.pubapp.pub.smart.BillAppModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.model.MatchManageModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.pu.m27.match.model.MatchManageModel)context.get("manageAppModel");
  nc.ui.pu.m27.match.model.MatchManageModel bean = new nc.ui.pu.m27.match.model.MatchManageModel();
  context.put("manageAppModel",bean);
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BaseBillModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.model.BaseBillModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.model.BaseBillModelDataManager bean = new nc.ui.pubapp.uif2app.model.BaseBillModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getManageModelService());
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
  bean.setNodeKeies(getManagedList17());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList17(){  List list = new ArrayList();  list.add("4004140201");  list.add("4004140202");  return list;}

public nc.ui.pubapp.uif2app.view.OrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pubapp.uif2app.view.OrgPanel)context.get("orgPanel");
  nc.ui.pubapp.uif2app.view.OrgPanel bean = new nc.ui.pubapp.uif2app.view.OrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setType("财务组织");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.view.MatchDisplayListPanel getQueryView(){
 if(context.get("queryView")!=null)
 return (nc.ui.pu.m27.match.view.MatchDisplayListPanel)context.get("queryView");
  nc.ui.pu.m27.match.view.MatchDisplayListPanel bean = new nc.ui.pu.m27.match.view.MatchDisplayListPanel();
  context.put("queryView",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("4004140201");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.view.MatchProcessListPanel getProcessView(){
 if(context.get("processView")!=null)
 return (nc.ui.pu.m27.match.view.MatchProcessListPanel)context.get("processView");
  nc.ui.pu.m27.match.view.MatchProcessListPanel bean = new nc.ui.pu.m27.match.view.MatchProcessListPanel();
  context.put("processView",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("4004140202");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m27.match.view.MatchResultListPanel getResultView(){
 if(context.get("resultView")!=null)
 return (nc.ui.pu.m27.match.view.MatchResultListPanel)context.get("resultView");
  nc.ui.pu.m27.match.view.MatchResultListPanel bean = new nc.ui.pu.m27.match.view.MatchResultListPanel();
  context.put("resultView",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("4004140201");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getInvoiceLinkQueryHyperlinkMediator(){
 if(context.get("invoiceLinkQueryHyperlinkMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("invoiceLinkQueryHyperlinkMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("invoiceLinkQueryHyperlinkMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("pk_invoice");
  bean.setSrcBillNOField("vinvoicecode");
  bean.setSrcBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getIcLinkQueryHyperlinkMediator(){
 if(context.get("icLinkQueryHyperlinkMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("icLinkQueryHyperlinkMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("icLinkQueryHyperlinkMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("pk_stock");
  bean.setSrcBillNOField("vstockcode");
  bean.setSrcBillType("45");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayout(getTangramLayout_196c2b8());
  bean.setTangramLayoutRoot(getTBNode_1167b1f());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.TangramLayout getTangramLayout_196c2b8(){
 if(context.get("nc.ui.uif2.tangramlayout.TangramLayout#196c2b8")!=null)
 return (nc.ui.uif2.tangramlayout.TangramLayout)context.get("nc.ui.uif2.tangramlayout.TangramLayout#196c2b8");
  nc.ui.uif2.tangramlayout.TangramLayout bean = new nc.ui.uif2.tangramlayout.TangramLayout();
  context.put("nc.ui.uif2.tangramlayout.TangramLayout#196c2b8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1167b1f(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1167b1f")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1167b1f");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1167b1f",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList18());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList18(){  List list = new ArrayList();  list.add(getVSNode_1556fd3());  list.add(getCNode_20c5ed());  list.add(getCNode_1a2862b());  return list;}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1556fd3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1556fd3")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1556fd3");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1556fd3",bean);
  bean.setDown(getCNode_c9eaf4());
  bean.setUp(getCNode_606ff1());
  bean.setDividerLocation(31f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_c9eaf4(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#c9eaf4")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#c9eaf4");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#c9eaf4",bean);
  bean.setName("list1");
  bean.setComponent(getQueryView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_606ff1(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#606ff1")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#606ff1");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#606ff1",bean);
  bean.setComponent(getOrgPanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_20c5ed(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#20c5ed")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#20c5ed");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#20c5ed",bean);
  bean.setName("list2");
  bean.setComponent(getProcessView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1a2862b(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1a2862b")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1a2862b");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1a2862b",bean);
  bean.setName("list3");
  bean.setComponent(getResultView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList19(){  List list = new ArrayList();  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

package nc.ui.pu.est.config.m45;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class purchsinunest_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.scmpub.action.SCMDefaultQueryDelegator getQueryDelegator(){
 if(context.get("queryDelegator")!=null)
 return (nc.ui.scmpub.action.SCMDefaultQueryDelegator)context.get("queryDelegator");
  nc.ui.scmpub.action.SCMDefaultQueryDelegator bean = new nc.ui.scmpub.action.SCMDefaultQueryDelegator();
  context.put("queryDelegator",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.query.PurchsInCancelEstQueryDLGInitializer getPurchsInCancelEstQryDLGInitializer(){
 if(context.get("purchsInCancelEstQryDLGInitializer")!=null)
 return (nc.ui.pu.est.query.PurchsInCancelEstQueryDLGInitializer)context.get("purchsInCancelEstQryDLGInitializer");
  nc.ui.pu.est.query.PurchsInCancelEstQueryDLGInitializer bean = new nc.ui.pu.est.query.PurchsInCancelEstQueryDLGInitializer();
  context.put("purchsInCancelEstQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurchsInCancelEstQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchsInCancelEstQueryAction)context.get("queryAction");
  nc.ui.pu.est.action.m45.PurchsInCancelEstQueryAction bean = new nc.ui.pu.est.action.m45.PurchsInCancelEstQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setQryCondDLGInitializer(getPurchsInCancelEstQryDLGInitializer());
  bean.setEditor(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurchaseInEstRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchaseInEstRefreshAction)context.get("refreshAction");
  nc.ui.pu.est.action.m45.PurchaseInEstRefreshAction bean = new nc.ui.pu.est.action.m45.PurchaseInEstRefreshAction();
  context.put("refreshAction",bean);
  bean.setQueryAction(getQueryAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurchsInCancelEstAction getCancelestAction(){
 if(context.get("cancelestAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchsInCancelEstAction)context.get("cancelestAction");
  nc.ui.pu.est.action.m45.PurchsInCancelEstAction bean = new nc.ui.pu.est.action.m45.PurchsInCancelEstAction();
  context.put("cancelestAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.EstimateLinkQueryAction getLinkQryAction(){
 if(context.get("linkQryAction")!=null)
 return (nc.ui.pu.est.action.EstimateLinkQueryAction)context.get("linkQryAction");
  nc.ui.pu.est.action.EstimateLinkQueryAction bean = new nc.ui.pu.est.action.EstimateLinkQueryAction();
  context.put("linkQryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("45");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.processor.PurchaseInEstPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.est.action.processor.PurchaseInEstPrintProcessor)context.get("printProcessor");
  nc.ui.pu.est.action.processor.PurchaseInEstPrintProcessor bean = new nc.ui.pu.est.action.processor.PurchaseInEstPrintProcessor();
  context.put("printProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurInEstListPrintAction getListPreviewAction(){
 if(context.get("listPreviewAction")!=null)
 return (nc.ui.pu.est.action.m45.PurInEstListPrintAction)context.get("listPreviewAction");
  nc.ui.pu.est.action.m45.PurInEstListPrintAction bean = new nc.ui.pu.est.action.m45.PurInEstListPrintAction();
  context.put("listPreviewAction",bean);
  bean.setPreview(true);
  bean.setBtnName(getI18nFB_4e91c0());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160401");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_4e91c0(){
 if(context.get("nc.ui.uif2.I18nFB#4e91c0")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#4e91c0");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#4e91c0",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0236");
  bean.setDefaultValue("‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#4e91c0",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m45.PurInEstListPrintAction getListPrintAction(){
 if(context.get("listPrintAction")!=null)
 return (nc.ui.pu.est.action.m45.PurInEstListPrintAction)context.get("listPrintAction");
  nc.ui.pu.est.action.m45.PurInEstListPrintAction bean = new nc.ui.pu.est.action.m45.PurInEstListPrintAction();
  context.put("listPrintAction",bean);
  bean.setPreview(false);
  bean.setBtnName(getI18nFB_42e9f3());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160401");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_42e9f3(){
 if(context.get("nc.ui.uif2.I18nFB#42e9f3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#42e9f3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#42e9f3",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0235");
  bean.setDefaultValue("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#42e9f3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m45.PurInEstCardPrintAction getCardPreviewAction(){
 if(context.get("cardPreviewAction")!=null)
 return (nc.ui.pu.est.action.m45.PurInEstCardPrintAction)context.get("cardPreviewAction");
  nc.ui.pu.est.action.m45.PurInEstCardPrintAction bean = new nc.ui.pu.est.action.m45.PurInEstCardPrintAction();
  context.put("cardPreviewAction",bean);
  bean.setPreview(true);
  bean.setBtnName(getI18nFB_10d56ce());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160402");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_10d56ce(){
 if(context.get("nc.ui.uif2.I18nFB#10d56ce")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#10d56ce");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#10d56ce",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0237");
  bean.setDefaultValue("ø®∆¨‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#10d56ce",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m45.PurInEstCardPrintAction getCardPrintAction(){
 if(context.get("cardPrintAction")!=null)
 return (nc.ui.pu.est.action.m45.PurInEstCardPrintAction)context.get("cardPrintAction");
  nc.ui.pu.est.action.m45.PurInEstCardPrintAction bean = new nc.ui.pu.est.action.m45.PurInEstCardPrintAction();
  context.put("cardPrintAction",bean);
  bean.setPreview(false);
  bean.setBtnName(getI18nFB_be2e85());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160402");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_be2e85(){
 if(context.get("nc.ui.uif2.I18nFB#be2e85")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#be2e85");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#be2e85",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0238");
  bean.setDefaultValue("ø®∆¨¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#be2e85",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setCode("printMenuAction");
  bean.setActions(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getListPrintAction());  list.add(getListPreviewAction());  list.add(getSeparatorAction());  list.add(getCardPrintAction());  list.add(getCardPreviewAction());  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler getList_head_row_selecthandler(){
 if(context.get("list_head_row_selecthandler")!=null)
 return (nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler)context.get("list_head_row_selecthandler");
  nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler bean = new nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler();
  context.put("list_head_row_selecthandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.head.after.m45.HeadAfterEditEventHandler getList_head_after_edithandler(){
 if(context.get("list_head_after_edithandler")!=null)
 return (nc.ui.pu.est.editor.head.after.m45.HeadAfterEditEventHandler)context.get("list_head_after_edithandler");
  nc.ui.pu.est.editor.head.after.m45.HeadAfterEditEventHandler bean = new nc.ui.pu.est.editor.head.after.m45.HeadAfterEditEventHandler();
  context.put("list_head_after_edithandler",bean);
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList1());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent",getManagedList2());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList3());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList4());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList5());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent",getManagedList6());  return map;}

private List getManagedList1(){  List list = new ArrayList();  return list;}

private List getManagedList2(){  List list = new ArrayList();  list.add(getList_head_after_edithandler());  return list;}

private List getManagedList3(){  List list = new ArrayList();  return list;}

private List getManagedList4(){  List list = new ArrayList();  return list;}

private List getManagedList5(){  List list = new ArrayList();  return list;}

private List getManagedList6(){  List list = new ArrayList();  list.add(getList_head_row_selecthandler());  return list;}

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

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1e0ff1d());  list.add(getUserdefQueryParam_8cda4b());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1e0ff1d(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1e0ff1d")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1e0ff1d");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1e0ff1d",bean);
  bean.setRulecode("45_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_8cda4b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#8cda4b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#8cda4b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#8cda4b",bean);
  bean.setRulecode("45_B");
  bean.setPos(0);
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
  bean.setParams(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getQueryParam_14f8ff4());  list.add(getQueryParam_741857());  list.add(getQueryParam_1ea15bc());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_14f8ff4(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#14f8ff4")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#14f8ff4");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#14f8ff4",bean);
  bean.setRulecode("45_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_741857(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#741857")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#741857");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#741857",bean);
  bean.setRulecode("45_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1ea15bc(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1ea15bc")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1ea15bc");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1ea15bc",bean);
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
  bean.setModel(getManageAppModel());
  bean.setQueryAction(getQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.model.EstUIContext getContext(){
 if(context.get("context")!=null)
 return (nc.ui.pu.est.model.EstUIContext)context.get("context");
  nc.ui.pu.est.model.EstUIContext bean = new nc.ui.pu.est.model.EstUIContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.model.EstBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pu.est.model.EstBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pu.est.model.EstBDObjectAdapterFactory bean = new nc.ui.pu.est.model.EstBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BillManageModel)context.get("ManageAppModel");
  nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
  context.put("ManageAppModel",bean);
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
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

public nc.ui.pu.est.model.EstVOBillListPanelValueSetter getValuesetter(){
 if(context.get("valuesetter")!=null)
 return (nc.ui.pu.est.model.EstVOBillListPanelValueSetter)context.get("valuesetter");
  nc.ui.pu.est.model.EstVOBillListPanelValueSetter bean = new nc.ui.pu.est.model.EstVOBillListPanelValueSetter();
  context.put("valuesetter",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.view.EstEditor getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pu.est.view.EstEditor)context.get("listView");
  nc.ui.pu.est.view.EstEditor bean = new nc.ui.pu.est.view.EstEditor();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setMultiSelectionMode(1);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemListPreparator(getSingleUserdefitemListPreparator());
  bean.setBillListPanelValueSetter(getValuesetter());
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
  bean.setTangramLayoutRoot(getCNode_825b06());
  bean.setActions(getManagedList10());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_825b06(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#825b06")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#825b06");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#825b06",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getCancelestAction());  list.add(getSeparatorAction());  list.add(getLinkQryAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

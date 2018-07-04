package nc.ui.pu.est.config.m50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class vmicancelest_config extends AbstractJavaBeanDefinition{
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

public nc.ui.pu.est.query.VMICancelEstQueryDLGInitializer getVMICancelEstQueryDLGInitializer(){
 if(context.get("VMICancelEstQueryDLGInitializer")!=null)
 return (nc.ui.pu.est.query.VMICancelEstQueryDLGInitializer)context.get("VMICancelEstQueryDLGInitializer");
  nc.ui.pu.est.query.VMICancelEstQueryDLGInitializer bean = new nc.ui.pu.est.query.VMICancelEstQueryDLGInitializer();
  context.put("VMICancelEstQueryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m50.VMICancelEstQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pu.est.action.m50.VMICancelEstQueryAction)context.get("queryAction");
  nc.ui.pu.est.action.m50.VMICancelEstQueryAction bean = new nc.ui.pu.est.action.m50.VMICancelEstQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setQryCondDLGInitializer(getVMICancelEstQueryDLGInitializer());
  bean.setEditor(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m50.VMIEstRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pu.est.action.m50.VMIEstRefreshAction)context.get("refreshAction");
  nc.ui.pu.est.action.m50.VMIEstRefreshAction bean = new nc.ui.pu.est.action.m50.VMIEstRefreshAction();
  context.put("refreshAction",bean);
  bean.setQueryAction(getQueryAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m50.VMICancelEstAction getCancelestAction(){
 if(context.get("cancelestAction")!=null)
 return (nc.ui.pu.est.action.m50.VMICancelEstAction)context.get("cancelestAction");
  nc.ui.pu.est.action.m50.VMICancelEstAction bean = new nc.ui.pu.est.action.m50.VMICancelEstAction();
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
  bean.setBillType("50");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.processor.VmiEstPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.est.action.processor.VmiEstPrintProcessor)context.get("printProcessor");
  nc.ui.pu.est.action.processor.VmiEstPrintProcessor bean = new nc.ui.pu.est.action.processor.VmiEstPrintProcessor();
  context.put("printProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m50.VMIEstListPrintAction getListPreviewAction(){
 if(context.get("listPreviewAction")!=null)
 return (nc.ui.pu.est.action.m50.VMIEstListPrintAction)context.get("listPreviewAction");
  nc.ui.pu.est.action.m50.VMIEstListPrintAction bean = new nc.ui.pu.est.action.m50.VMIEstListPrintAction();
  context.put("listPreviewAction",bean);
  bean.setPreview(true);
  bean.setBtnName(getI18nFB_1c72030());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160801");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1c72030(){
 if(context.get("nc.ui.uif2.I18nFB#1c72030")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1c72030");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1c72030",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0236");
  bean.setDefaultValue("¡–±Ì‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1c72030",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m50.VMIEstListPrintAction getListPrintAction(){
 if(context.get("listPrintAction")!=null)
 return (nc.ui.pu.est.action.m50.VMIEstListPrintAction)context.get("listPrintAction");
  nc.ui.pu.est.action.m50.VMIEstListPrintAction bean = new nc.ui.pu.est.action.m50.VMIEstListPrintAction();
  context.put("listPrintAction",bean);
  bean.setPreview(false);
  bean.setBtnName(getI18nFB_125734());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160801");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_125734(){
 if(context.get("nc.ui.uif2.I18nFB#125734")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#125734");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#125734",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0235");
  bean.setDefaultValue("¡–±Ì¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#125734",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m50.VMIEstCardPrintAction getCardPreviewAction(){
 if(context.get("cardPreviewAction")!=null)
 return (nc.ui.pu.est.action.m50.VMIEstCardPrintAction)context.get("cardPreviewAction");
  nc.ui.pu.est.action.m50.VMIEstCardPrintAction bean = new nc.ui.pu.est.action.m50.VMIEstCardPrintAction();
  context.put("cardPreviewAction",bean);
  bean.setPreview(true);
  bean.setBtnName(getI18nFB_1b3df0());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160802");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1b3df0(){
 if(context.get("nc.ui.uif2.I18nFB#1b3df0")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1b3df0");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1b3df0",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0237");
  bean.setDefaultValue("ø®∆¨‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1b3df0",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m50.VMIEstCardPrintAction getCardPrintAction(){
 if(context.get("cardPrintAction")!=null)
 return (nc.ui.pu.est.action.m50.VMIEstCardPrintAction)context.get("cardPrintAction");
  nc.ui.pu.est.action.m50.VMIEstCardPrintAction bean = new nc.ui.pu.est.action.m50.VMIEstCardPrintAction();
  context.put("cardPrintAction",bean);
  bean.setPreview(false);
  bean.setBtnName(getI18nFB_1c9c56d());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160802");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1c9c56d(){
 if(context.get("nc.ui.uif2.I18nFB#1c9c56d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1c9c56d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1c9c56d",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0238");
  bean.setDefaultValue("ø®∆¨¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1c9c56d",product);
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

private List getManagedList2(){  List list = new ArrayList();  return list;}

private List getManagedList3(){  List list = new ArrayList();  return list;}

private List getManagedList4(){  List list = new ArrayList();  return list;}

private List getManagedList5(){  List list = new ArrayList();  return list;}

private List getManagedList6(){  List list = new ArrayList();  return list;}

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

private List getManagedList7(){  List list = new ArrayList();  list.add(getMarAsstPreparator());  return list;}

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
  bean.setParams(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getQueryParam_13a6eda());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_13a6eda(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#13a6eda")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#13a6eda");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#13a6eda",bean);
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
  bean.setBillListPanelValueSetter(getValuesetter());
  bean.setUserdefitemListPreparator(getSingleUserdefitemListPreparator());
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
  bean.setTangramLayoutRoot(getCNode_1c72aa9());
  bean.setActions(getManagedList9());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1c72aa9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1c72aa9")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1c72aa9");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1c72aa9",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getCancelestAction());  list.add(getSeparatorAction());  list.add(getLinkQryAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

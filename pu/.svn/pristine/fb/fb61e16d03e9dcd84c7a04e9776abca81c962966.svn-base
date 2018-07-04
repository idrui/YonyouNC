package nc.ui.pu.m23.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class check_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pu.m23.check.action.QCUIAction getQCUIAction(){
 if(context.get("QCUIAction")!=null)
 return (nc.ui.pu.m23.check.action.QCUIAction)context.get("QCUIAction");
  nc.ui.pu.m23.check.action.QCUIAction bean = new nc.ui.pu.m23.check.action.QCUIAction();
  context.put("QCUIAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.check.action.ChkCancelAction getChkCancelAction(){
 if(context.get("ChkCancelAction")!=null)
 return (nc.ui.pu.m23.check.action.ChkCancelAction)context.get("ChkCancelAction");
  nc.ui.pu.m23.check.action.ChkCancelAction bean = new nc.ui.pu.m23.check.action.ChkCancelAction();
  context.put("ChkCancelAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setBtnName(getI18nFB_12e6e19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_12e6e19(){
 if(context.get("nc.ui.uif2.I18nFB#12e6e19")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#12e6e19");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#12e6e19",bean);  bean.setResDir("4004040_0");
  bean.setResId("04004040-0215");
  bean.setDefaultValue("·´¼ì");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#12e6e19",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m23.query.CheckQueryDLGInitializer getCheckQryDLGInitializer(){
 if(context.get("checkQryDLGInitializer")!=null)
 return (nc.ui.pu.m23.query.CheckQueryDLGInitializer)context.get("checkQryDLGInitializer");
  nc.ui.pu.m23.query.CheckQueryDLGInitializer bean = new nc.ui.pu.m23.query.CheckQueryDLGInitializer();
  context.put("checkQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.query.ArrivalCheckQueryAction getQueryUIAction(){
 if(context.get("queryUIAction")!=null)
 return (nc.ui.pu.m23.query.ArrivalCheckQueryAction)context.get("queryUIAction");
  nc.ui.pu.m23.query.ArrivalCheckQueryAction bean = new nc.ui.pu.m23.query.ArrivalCheckQueryAction();
  context.put("queryUIAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setList(getListView());
  bean.setQryCondDLGInitializer(getCheckQryDLGInitializer());
  bean.setModel(getManageAppModel());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshUIAction(){
 if(context.get("refreshUIAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshUIAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshUIAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.action.processor.ArriveCheckPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.m23.action.processor.ArriveCheckPrintProcessor)context.get("printProcessor");
  nc.ui.pu.m23.action.processor.ArriveCheckPrintProcessor bean = new nc.ui.pu.m23.action.processor.ArriveCheckPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.check.action.PrintUIAction getPrintUIAction(){
 if(context.get("printUIAction")!=null)
 return (nc.ui.pu.m23.check.action.PrintUIAction)context.get("printUIAction");
  nc.ui.pu.m23.check.action.PrintUIAction bean = new nc.ui.pu.m23.check.action.PrintUIAction();
  context.put("printUIAction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.check.action.PreviewPrintUIAction getPreviewPrintUIAction(){
 if(context.get("previewPrintUIAction")!=null)
 return (nc.ui.pu.m23.check.action.PreviewPrintUIAction)context.get("previewPrintUIAction");
  nc.ui.pu.m23.check.action.PreviewPrintUIAction bean = new nc.ui.pu.m23.check.action.PreviewPrintUIAction();
  context.put("previewPrintUIAction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
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
  bean.setActions(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getPrintUIAction());  list.add(getPreviewPrintUIAction());  return list;}

public nc.ui.pu.m23.check.action.LinkBillUIAction getLinkBillUIAction(){
 if(context.get("linkBillUIAction")!=null)
 return (nc.ui.pu.m23.check.action.LinkBillUIAction)context.get("linkBillUIAction");
  nc.ui.pu.m23.check.action.LinkBillUIAction bean = new nc.ui.pu.m23.check.action.LinkBillUIAction();
  context.put("linkBillUIAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("23");
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent",getManagedList1());  return map;}

private List getManagedList1(){  List list = new ArrayList();  list.add(getListHeadAfterEditEventHandler_11c91fd());  return list;}

private nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler getListHeadAfterEditEventHandler_11c91fd(){
 if(context.get("nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler#11c91fd")!=null)
 return (nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler)context.get("nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler#11c91fd");
  nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler bean = new nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler();
  context.put("nc.ui.pu.m23.check.editor.list.afteredit.ListHeadAfterEditEventHandler#11c91fd",bean);
  bean.setModel(getManageAppModel());
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

public nc.ui.pubapp.uif2app.view.value.DataViewBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.DataViewBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.DataViewBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.DataViewBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.model.ArriveCheckModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.pu.m23.model.ArriveCheckModelService)context.get("manageModelService");
  nc.ui.pu.m23.model.ArriveCheckModelService bean = new nc.ui.pu.m23.model.ArriveCheckModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.model.ArriveCheckManageModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.pu.m23.model.ArriveCheckManageModel)context.get("manageAppModel");
  nc.ui.pu.m23.model.ArriveCheckManageModel bean = new nc.ui.pu.m23.model.ArriveCheckManageModel();
  context.put("manageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
  bean.setBillType("23");
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
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m23.view.CheckListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pu.m23.view.CheckListView)context.get("listView");
  nc.ui.pu.m23.view.CheckListView bean = new nc.ui.pu.m23.view.CheckListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setMultiSelectionMode(1);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setBillListPanelValueSetter(getCheckListPanelValueSetter_b4a19e());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m23.view.CheckListPanelValueSetter getCheckListPanelValueSetter_b4a19e(){
 if(context.get("nc.ui.pu.m23.view.CheckListPanelValueSetter#b4a19e")!=null)
 return (nc.ui.pu.m23.view.CheckListPanelValueSetter)context.get("nc.ui.pu.m23.view.CheckListPanelValueSetter#b4a19e");
  nc.ui.pu.m23.view.CheckListPanelValueSetter bean = new nc.ui.pu.m23.view.CheckListPanelValueSetter();
  context.put("nc.ui.pu.m23.view.CheckListPanelValueSetter#b4a19e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVsourcecodeMediator(){
 if(context.get("vsourcecodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vsourcecodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vsourcecodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csourceid");
  bean.setSrcBillNOField("vsourcecode");
  bean.setSrcBillTypeField("csourcetypecode");
  bean.setSrcBillTypeFieldPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVfirstcodeMediator(){
 if(context.get("vfirstcodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vfirstcodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vfirstcodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("cfirstid");
  bean.setSrcBillNOField("vfirstcode");
  bean.setSrcBillTypeField("cfirsttypecode");
  bean.setSrcBillTypeFieldPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryUIAction());
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
  bean.setTangramLayoutRoot(getHSNode_1b1a793());
  bean.setActions(getManagedList2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1b1a793(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#1b1a793")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#1b1a793");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#1b1a793",bean);
  bean.setLeft(getCNode_debb9e());
  bean.setRight(getCNode_efaea0());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_debb9e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#debb9e")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#debb9e");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#debb9e",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_efaea0(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#efaea0")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#efaea0");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#efaea0",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getQueryUIAction());  list.add(getRefreshUIAction());  list.add(getSeparatorAction());  list.add(getQCUIAction());  list.add(getSeparatorAction());  list.add(getChkCancelAction());  list.add(getSeparatorAction());  list.add(getLinkBillUIAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setQueryAction(getQueryUIAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}

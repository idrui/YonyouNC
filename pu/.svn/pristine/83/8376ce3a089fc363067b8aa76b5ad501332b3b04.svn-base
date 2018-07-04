package nc.ui.pu.m21.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class arriveplan_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderReceivePlanModelService getBatchModelService(){
 if(context.get("batchModelService")!=null)
 return (nc.ui.pu.m21.service.OrderReceivePlanModelService)context.get("batchModelService");
  nc.ui.pu.m21.service.OrderReceivePlanModelService bean = new nc.ui.pu.m21.service.OrderReceivePlanModelService();
  context.put("batchModelService",bean);
  bean.setVoClass("nc.vo.pu.m21.entity.OrderReceivePlanVO");
  bean.setServiceItf("nc.itf.pu.m21.IOrderReceivePlan");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadatorfactory");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.RPBatchValidationService getBatchValidateSerice(){
 if(context.get("batchValidateSerice")!=null)
 return (nc.ui.pu.m21.service.RPBatchValidationService)context.get("batchValidateSerice");
  nc.ui.pu.m21.service.RPBatchValidationService bean = new nc.ui.pu.m21.service.RPBatchValidationService();
  context.put("batchValidateSerice",bean);
  bean.setEditor(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderReceivePlanModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pu.m21.service.OrderReceivePlanModel)context.get("batchBillTableModel");
  nc.ui.pu.m21.service.OrderReceivePlanModel bean = new nc.ui.pu.m21.service.OrderReceivePlanModel();
  context.put("batchBillTableModel",bean);
  bean.setContext(getContext());
  bean.setService(getBatchModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setValidationService(getBatchValidateSerice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.model.BatchModelDataManager bean = new nc.ui.pubapp.uif2app.model.BatchModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setService(getBatchModelService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.initdata.RPInitData getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pu.m21.action.initdata.RPInitData)context.get("InitDataListener");
  nc.ui.pu.m21.action.initdata.RPInitData bean = new nc.ui.pu.m21.action.initdata.RPInitData();
  context.put("InitDataListener",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.FunNodeClosingHandler getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.uif2.FunNodeClosingHandler)context.get("ClosingListener");
  nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
  context.put("ClosingListener",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setSaveaction(getSaveAction());
  bean.setCancelaction(getCancelAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.OrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pubapp.uif2app.view.OrgPanel)context.get("orgPanel");
  nc.ui.pubapp.uif2app.view.OrgPanel bean = new nc.ui.pubapp.uif2app.view.OrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
  bean.setType("²É¹º×éÖ¯");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter)context.get("componentValueManager");
  nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter bean = new nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter();
  context.put("componentValueManager",bean);
  bean.setBodyVOName("nc.vo.pu.m21.entity.OrderReceivePlanVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.ReceivePlanDlg getList(){
 if(context.get("list")!=null)
 return (nc.ui.pu.m21.view.ReceivePlanDlg)context.get("list");
  nc.ui.pu.m21.view.ReceivePlanDlg bean = new nc.ui.pu.m21.view.ReceivePlanDlg();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setVoClassName("nc.vo.pu.m21.entity.OrderReceivePlanVO");
  bean.setIsBodyAutoAddLine(false);
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("40040416N");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getList());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList0());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList1());  return map;}

private List getManagedList0(){  List list = new ArrayList();  list.add(getRPCardBodyAfterEditEventHandler_189032f());  list.add(getRPRelationCalculate_3040ff());  return list;}

private nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler getRPCardBodyAfterEditEventHandler_189032f(){
 if(context.get("nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler#189032f")!=null)
 return (nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler)context.get("nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler#189032f");
  nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler bean = new nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler();
  context.put("nc.ui.pu.m21.editor.card.afteredit.RPCardBodyAfterEditEventHandler#189032f",bean);
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate getRPRelationCalculate_3040ff(){
 if(context.get("nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate#3040ff")!=null)
 return (nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate)context.get("nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate#3040ff");
  nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate bean = new nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate();
  context.put("nc.ui.pu.m21.editor.card.afteredit.RPRelationCalculate#3040ff",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getRPCardBodyBeforeEditEventHandler_1fb2ead());  return list;}

private nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler getRPCardBodyBeforeEditEventHandler_1fb2ead(){
 if(context.get("nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler#1fb2ead")!=null)
 return (nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler)context.get("nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler#1fb2ead");
  nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler bean = new nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler();
  context.put("nc.ui.pu.m21.editor.card.beforeedit.RPCardBodyBeforeEditEventHandler#1fb2ead",bean);
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchRefreshAction)context.get("refreshAction");
  nc.ui.pu.m21.action.RPBatchRefreshAction bean = new nc.ui.pu.m21.action.RPBatchRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setModelManager(getModelDataManager());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchEditAction)context.get("editAction");
  nc.ui.pu.m21.action.RPBatchEditAction bean = new nc.ui.pu.m21.action.RPBatchEditAction();
  context.put("editAction",bean);
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPAllotAction getAllotAction(){
 if(context.get("allotAction")!=null)
 return (nc.ui.pu.m21.action.RPAllotAction)context.get("allotAction");
  nc.ui.pu.m21.action.RPAllotAction bean = new nc.ui.pu.m21.action.RPAllotAction();
  context.put("allotAction",bean);
  bean.setList(getList());
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchAddReceivePlan getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchAddReceivePlan)context.get("addAction");
  nc.ui.pu.m21.action.RPBatchAddReceivePlan bean = new nc.ui.pu.m21.action.RPBatchAddReceivePlan();
  context.put("addAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setVoClassName("nc.vo.pu.m21.entity.OrderReceivePlanVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchDelLineAction getDelAction(){
 if(context.get("delAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchDelLineAction)context.get("delAction");
  nc.ui.pu.m21.action.RPBatchDelLineAction bean = new nc.ui.pu.m21.action.RPBatchDelLineAction();
  context.put("delAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchCopyLineAction getCopylineAction(){
 if(context.get("copylineAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchCopyLineAction)context.get("copylineAction");
  nc.ui.pu.m21.action.RPBatchCopyLineAction bean = new nc.ui.pu.m21.action.RPBatchCopyLineAction();
  context.put("copylineAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchPasteLine getPastelineAction(){
 if(context.get("pastelineAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchPasteLine)context.get("pastelineAction");
  nc.ui.pu.m21.action.RPBatchPasteLine bean = new nc.ui.pu.m21.action.RPBatchPasteLine();
  context.put("pastelineAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchSaveAction)context.get("saveAction");
  nc.ui.pu.m21.action.RPBatchSaveAction bean = new nc.ui.pu.m21.action.RPBatchSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RPBatchCancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pu.m21.action.RPBatchCancelAction)context.get("cancelAction");
  nc.ui.pu.m21.action.RPBatchCancelAction bean = new nc.ui.pu.m21.action.RPBatchCancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.processor.ReceivePlanPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.m21.action.processor.ReceivePlanPrintProcessor)context.get("printProcessor");
  nc.ui.pu.m21.action.processor.ReceivePlanPrintProcessor bean = new nc.ui.pu.m21.action.processor.ReceivePlanPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.ReceivePlanPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pu.m21.action.ReceivePlanPrintAction)context.get("previewAction");
  nc.ui.pu.m21.action.ReceivePlanPrintAction bean = new nc.ui.pu.m21.action.ReceivePlanPrintAction();
  context.put("previewAction",bean);
  bean.setParent(getList());
  bean.setPreview(true);
  bean.setModel(getBatchBillTableModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.ReceivePlanPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pu.m21.action.ReceivePlanPrintAction)context.get("printAction");
  nc.ui.pu.m21.action.ReceivePlanPrintAction bean = new nc.ui.pu.m21.action.ReceivePlanPrintAction();
  context.put("printAction",bean);
  bean.setParent(getList());
  bean.setPreview(false);
  bean.setBeforePrintDataProcess(getPrintProcessor());
  bean.setModel(getBatchBillTableModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getCNode_1b56f3b());
  bean.setActions(getManagedList2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1b56f3b(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1b56f3b")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1b56f3b");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1b56f3b",bean);
  bean.setComponent(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getRefreshAction());  list.add(getEditAction());  list.add(getAllotAction());  list.add(getAddAction());  list.add(getDelAction());  list.add(getCopylineAction());  list.add(getPastelineAction());  list.add(getSaveAction());  list.add(getCancelAction());  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
  bean.setProductorField("cproductorid");
  bean.setSignatureField("cffileid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_99b672());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_99b672(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#99b672")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#99b672");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#99b672",bean);
  bean.setMdfullname("pu.po_order_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
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
  bean.setParams(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_1993540());  list.add(getQueryParam_176fd91());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1993540(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1993540")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1993540");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1993540",bean);
  bean.setMdfullname("pu.po_order_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_176fd91(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#176fd91")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#176fd91");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#176fd91",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.RevPlanTemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pu.m21.view.RevPlanTemplateContainer)context.get("templateContainer");
  nc.ui.pu.m21.view.RevPlanTemplateContainer bean = new nc.ui.pu.m21.view.RevPlanTemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setNodeKeies(getManagedList6());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add("40040416N");  return list;}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

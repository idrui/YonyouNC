package nc.ui.pu.m24.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class pricestl_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor(){
 if(context.get("formInterceptor")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("formInterceptor");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("formInterceptor",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor(){
 if(context.get("listInterceptor")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("listInterceptor");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("listInterceptor",bean);
  bean.setShowUpComponent(getListView());
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

public nc.ui.pu.m24.action.PricestlAddFromSourceAction getAddFrom20Action(){
 if(context.get("addFrom20Action")!=null)
 return (nc.ui.pu.m24.action.PricestlAddFromSourceAction)context.get("addFrom20Action");
  nc.ui.pu.m24.action.PricestlAddFromSourceAction bean = new nc.ui.pu.m24.action.PricestlAddFromSourceAction();
  context.put("addFrom20Action",bean);
  bean.setSourceBillType("45");
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVEONLY");
  bean.setFilledUpInFlow(true);
  bean.setBillType("24");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.action.PricestlEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.pu.m24.action.PricestlEditAction)context.get("editAction");
  nc.ui.pu.m24.action.PricestlEditAction bean = new nc.ui.pu.m24.action.PricestlEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_460e54());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_460e54(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#460e54")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#460e54");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#460e54",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.action.PricestlDelAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.pu.m24.action.PricestlDelAction)context.get("deleteAction");
  nc.ui.pu.m24.action.PricestlDelAction bean = new nc.ui.pu.m24.action.PricestlDelAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("DISCARD");
  bean.setBillType("24");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.query.PricestlQueryDLGInitializer getPricestlQryDLGInitializer(){
 if(context.get("pricestlQryDLGInitializer")!=null)
 return (nc.ui.pu.m24.query.PricestlQueryDLGInitializer)context.get("pricestlQryDLGInitializer");
  nc.ui.pu.m24.query.PricestlQueryDLGInitializer bean = new nc.ui.pu.m24.query.PricestlQueryDLGInitializer();
  context.put("pricestlQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getPricestlQryDLGInitializer());
  bean.setModel(getManageAppModel());
  bean.setShowUpComponent(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction)context.get("saveAction");
  nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVEONLY");
  bean.setBillType("24");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction getSaveCommitAction(){
 if(context.get("saveCommitAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction)context.get("saveCommitAction");
  nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction(getSaveAction(),getSendApproveAction());  context.put("saveCommitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBtnName(getI18nFB_156dd26());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_156dd26(){
 if(context.get("nc.ui.uif2.I18nFB#156dd26")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#156dd26");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#156dd26",bean);  bean.setResDir("common");
  bean.setResId("2SCMPUB-000027");
  bean.setDefaultValue("保存提交");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#156dd26",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m24.action.PricestlCommitAction getSendApproveAction(){
 if(context.get("sendApproveAction")!=null)
 return (nc.ui.pu.m24.action.PricestlCommitAction)context.get("sendApproveAction");
  nc.ui.pu.m24.action.PricestlCommitAction bean = new nc.ui.pu.m24.action.PricestlCommitAction();
  context.put("sendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setPreActionNames(getManagedList0());
  bean.setActionName("SAVE");
  bean.setBillType("24");
  bean.setFilledUpInFlow(true);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("SAVEONLY");  return list;}

public nc.ui.pu.pub.action.UnSaveScriptAction getBackAction(){
 if(context.get("backAction")!=null)
 return (nc.ui.pu.pub.action.UnSaveScriptAction)context.get("backAction");
  nc.ui.pu.pub.action.UnSaveScriptAction bean = new nc.ui.pu.pub.action.UnSaveScriptAction();
  context.put("backAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNSAVEBILL");
  bean.setBillType("24");
  bean.setFilledUpInFlow(true);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getSendApproveMenuAction(){
 if(context.get("sendApproveMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("sendApproveMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("sendApproveMenuAction",bean);
  bean.setCode("sendApproveMenuAction");
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getSendApproveAction());  list.add(getBackAction());  return list;}

public nc.ui.pu.m24.action.PricestlApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.pu.m24.action.PricestlApproveAction)context.get("approveAction");
  nc.ui.pu.m24.action.PricestlApproveAction bean = new nc.ui.pu.m24.action.PricestlApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("APPROVE");
  bean.setBillType("24");
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.action.PricestlUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.pu.m24.action.PricestlUnApproveAction)context.get("unApproveAction");
  nc.ui.pu.m24.action.PricestlUnApproveAction bean = new nc.ui.pu.m24.action.PricestlUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("UNAPPROVE");
  bean.setBillType("24");
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getLinkAuditStatusUIAction(){
 if(context.get("linkAuditStatusUIAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("linkAuditStatusUIAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("linkAuditStatusUIAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("24");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getAuditMenuAction(){
 if(context.get("auditMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("auditMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("auditMenuAction",bean);
  bean.setCode("auditMenuAction");
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction());  list.add(getLinkAuditStatusUIAction());  return list;}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printAction",bean);
  bean.setModel(getManageAppModel());
  bean.setPreview(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList4());
  bean.setEditActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getAddFrom20Action());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getSendApproveMenuAction());  list.add(getAuditMenuAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

private List getManagedList5(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSendApproveAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList6());
  bean.setEditActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getAddFrom20Action());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getSendApproveMenuAction());  list.add(getAuditMenuAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

private List getManagedList7(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSaveCommitAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  return list;}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setCode("printMenuAction");
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardPanelOrgSetterForAllRefMediator_0(){
 if(context.get("nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator#0")!=null)
 return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator)context.get("nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator#0");
  nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(getBillFormEditor());  context.put("nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator#0",bean);
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList9());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList10());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList11());  return map;}

private List getManagedList9(){  List list = new ArrayList();  list.add(getOrgChgEventHandler_126edaa());  return list;}

private nc.ui.pu.m24.editor.OrgChgEventHandler getOrgChgEventHandler_126edaa(){
 if(context.get("nc.ui.pu.m24.editor.OrgChgEventHandler#126edaa")!=null)
 return (nc.ui.pu.m24.editor.OrgChgEventHandler)context.get("nc.ui.pu.m24.editor.OrgChgEventHandler#126edaa");
  nc.ui.pu.m24.editor.OrgChgEventHandler bean = new nc.ui.pu.m24.editor.OrgChgEventHandler();
  context.put("nc.ui.pu.m24.editor.OrgChgEventHandler#126edaa",bean);
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getHeadRowChangeHandler_bd596b());  return list;}

private nc.ui.pu.m24.editor.HeadRowChangeHandler getHeadRowChangeHandler_bd596b(){
 if(context.get("nc.ui.pu.m24.editor.HeadRowChangeHandler#bd596b")!=null)
 return (nc.ui.pu.m24.editor.HeadRowChangeHandler)context.get("nc.ui.pu.m24.editor.HeadRowChangeHandler#bd596b");
  nc.ui.pu.m24.editor.HeadRowChangeHandler bean = new nc.ui.pu.m24.editor.HeadRowChangeHandler();
  context.put("nc.ui.pu.m24.editor.HeadRowChangeHandler#bd596b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getCardBodyAfterEditEventHandler_1398d84());  return list;}

private nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler getCardBodyAfterEditEventHandler_1398d84(){
 if(context.get("nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler#1398d84")!=null)
 return (nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler)context.get("nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler#1398d84");
  nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler bean = new nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler();
  context.put("nc.ui.pu.m24.editor.card.afteredit.CardBodyAfterEditEventHandler#1398d84",bean);
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
  bean.setVoClassName("nc.vo.pu.m24.entity.PricestlVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
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

public nc.ui.pu.m24.model.PricestlModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.pu.m24.model.PricestlModelService)context.get("manageModelService");
  nc.ui.pu.m24.model.PricestlModelService bean = new nc.ui.pu.m24.model.PricestlModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BillManageModel)context.get("manageAppModel");
  nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
  context.put("manageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.components.pagination.PaginationBar getPageBar(){
 if(context.get("pageBar")!=null)
 return (nc.ui.uif2.components.pagination.PaginationBar)context.get("pageBar");
  nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
  context.put("pageBar",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator(){
 if(context.get("pageDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("pageDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.model.PircestlPageModelService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.pu.m24.model.PircestlPageModelService)context.get("pageQuery");
  nc.ui.pu.m24.model.PircestlPageModelService bean = new nc.ui.pu.m24.model.PircestlPageModelService();
  context.put("pageQuery",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getListView());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager)context.get("modelDataManager");
  nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPageQuery(getPageQuery());
  bean.setPageDelegator(getPageDelegator());
  bean.setPagePanel(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.pubapp.common.validateservice.ClosingCheck)context.get("ClosingListener");
  nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveAction(getSaveAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billCodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billCodeMediator",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("24");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender(){
 if(context.get("trantypeTempMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("trantypeTempMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("trantypeTempMender",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
  nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setBillTemplateMender(getTrantypeTempMender());
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

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.view.PricestlBillListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pu.m24.view.PricestlBillListView)context.get("listView");
  nc.ui.pu.m24.view.PricestlBillListView bean = new nc.ui.pu.m24.view.PricestlBillListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setPaginationBar(getPageBar());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
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
  bean.setSrcBillIdField("csourceid");
  bean.setSrcBillNOField("vsourcecode");
  bean.setSrcBillType("45");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m24.view.PricestlFormEditor getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.pu.m24.view.PricestlFormEditor)context.get("billFormEditor");
  nc.ui.pu.m24.view.PricestlFormEditor bean = new nc.ui.pu.m24.view.PricestlFormEditor();
  context.put("billFormEditor",bean);
  bean.setComponentValueManager(getPricestlBillCardPanelMetaDataValueAdapter_b821f3());
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setTemplateNotNullValidate(true);
  bean.setAutoAddLine(false);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter getPricestlBillCardPanelMetaDataValueAdapter_b821f3(){
 if(context.get("nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter#b821f3")!=null)
 return (nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter)context.get("nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter#b821f3");
  nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter bean = new nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter();
  context.put("nc.ui.pu.m24.view.PricestlBillCardPanelMetaDataValueAdapter#b821f3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl(){
 if(context.get("cardInfoPnl")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("cardInfoPnl");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("cardInfoPnl",bean);
  bean.setTitleAction(getReturnaction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getListView());
  bean.setSaveAction(getSaveAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_1ce7aff());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1ce7aff(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1ce7aff")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1ce7aff");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1ce7aff",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getCNode_92c548());  list.add(getVSNode_1c46913());  return list;}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_92c548(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#92c548")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#92c548");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#92c548",bean);
  bean.setName(getI18nFB_bd187d());
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_bd187d(){
 if(context.get("nc.ui.uif2.I18nFB#bd187d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#bd187d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#bd187d",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#bd187d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1c46913(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1c46913")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1c46913");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1c46913",bean);
  bean.setUp(getCNode_1d61c1d());
  bean.setDown(getCNode_1bb35ba());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1d61c1d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1d61c1d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1d61c1d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1d61c1d",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1bb35ba(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1bb35ba")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1bb35ba");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1bb35ba",bean);
  bean.setName(getI18nFB_1cfcd7a());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1cfcd7a(){
 if(context.get("nc.ui.uif2.I18nFB#1cfcd7a")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1cfcd7a");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1cfcd7a",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1cfcd7a",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferBillViewProcessor(){
 if(context.get("transferBillViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor)context.get("transferBillViewProcessor");
  nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
  context.put("transferBillViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getContainer());
  bean.setBillForm(getBillFormEditor());
  bean.setTransferLogic(getDefaultBillDataLogic_ed1c13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_ed1c13(){
 if(context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#ed1c13")!=null)
 return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic)context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#ed1c13");
  nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
  context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#ed1c13",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getUserdefQueryParam_ce2a06());  list.add(getUserdefQueryParam_12901c4());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_ce2a06(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#ce2a06")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#ce2a06");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#ce2a06",bean);
  bean.setMdfullname("pu.po_pricesettle");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_12901c4(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#12901c4")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#12901c4");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#12901c4",bean);
  bean.setMdfullname("pu.po_pricesettle_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
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
  bean.setParams(getManagedList16());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add(getUserdefQueryParam_132f19b());  list.add(getUserdefQueryParam_194385a());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_132f19b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#132f19b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#132f19b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#132f19b",bean);
  bean.setMdfullname("pu.po_pricesettle");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_194385a(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#194385a")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#194385a");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#194385a",bean);
  bean.setMdfullname("pu.po_pricesettle_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("body_material");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getManageAppModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
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
  bean.setParams(getManagedList17());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList17(){  List list = new ArrayList();  list.add(getQueryParam_1ffd2dd());  list.add(getQueryParam_6cac31());  list.add(getQueryParam_9ce708());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1ffd2dd(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1ffd2dd")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1ffd2dd");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1ffd2dd",bean);
  bean.setMdfullname("pu.po_pricesettle");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_6cac31(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#6cac31")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#6cac31");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#6cac31",bean);
  bean.setMdfullname("pu.po_pricesettle_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_9ce708(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#9ce708")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#9ce708");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#9ce708",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getLazyActions(){
 if(context.get("lazyActions")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)context.get("lazyActions");
  nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
  context.put("lazyActions",bean);
  bean.setModel(getManageAppModel());
  bean.setActionList(getManagedList18());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList18(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("LasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("LasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("LasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList19(){  List list = new ArrayList();  list.add(getLazyActions());  list.add(getCardPanelLazilyLoad_103f5ed());  list.add(getListPanelLazilyLoad_106b3be());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_103f5ed(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#103f5ed")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#103f5ed");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#103f5ed",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_106b3be(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#106b3be")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#106b3be");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#106b3be",bean);
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList20());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList20(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

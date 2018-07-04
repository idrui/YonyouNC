package nc.ui.pu.m25.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class poInvoice_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pu.m25.action.InvoiceQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceQueryAction)context.get("queryAction");
  nc.ui.pu.m25.action.InvoiceQueryAction bean = new nc.ui.pu.m25.action.InvoiceQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getInvoiceQryCondDLGInitializer());
  bean.setShowUpComponent(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.query.InvoiceQryCondDlgInitializer getInvoiceQryCondDLGInitializer(){
 if(context.get("invoiceQryCondDLGInitializer")!=null)
 return (nc.ui.pu.m25.query.InvoiceQryCondDlgInitializer)context.get("invoiceQryCondDLGInitializer");
  nc.ui.pu.m25.query.InvoiceQryCondDlgInitializer bean = new nc.ui.pu.m25.query.InvoiceQryCondDlgInitializer();
  context.put("invoiceQryCondDLGInitializer",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getListRefreshAction(){
 if(context.get("listRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("listRefreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("listRefreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceRefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceRefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pu.m25.action.InvoiceRefreshSingleAction bean = new nc.ui.pu.m25.action.InvoiceRefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceEditAction)context.get("editAction");
  nc.ui.pu.m25.action.InvoiceEditAction bean = new nc.ui.pu.m25.action.InvoiceEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_b7c49());
  bean.setPowercheck(true);
  bean.setPermissioncode("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_b7c49(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#b7c49")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#b7c49");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#b7c49",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceDeleteAction)context.get("deleteAction");
  nc.ui.pu.m25.action.InvoiceDeleteAction bean = new nc.ui.pu.m25.action.InvoiceDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("DISCARD");
  bean.setBillType("25");
  bean.setValidationService(getPowerDeleteValidService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerDeleteValidService(){
 if(context.get("powerDeleteValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerDeleteValidService");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerDeleteValidService",bean);
  bean.setActionCode("delete");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.processor.CopyActionProcessor getCopyActionProcessor(){
 if(context.get("copyActionProcessor")!=null)
 return (nc.ui.pu.m25.action.processor.CopyActionProcessor)context.get("copyActionProcessor");
  nc.ui.pu.m25.action.processor.CopyActionProcessor bean = new nc.ui.pu.m25.action.processor.CopyActionProcessor();
  context.put("copyActionProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceCopyAction)context.get("copyAction");
  nc.ui.pu.m25.action.InvoiceCopyAction bean = new nc.ui.pu.m25.action.InvoiceCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setInterceptor(getFormInterceptor());
  bean.setCopyActionProcessor(getCopyActionProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceSaveAction)context.get("saveAction");
  nc.ui.pu.m25.action.InvoiceSaveAction bean = new nc.ui.pu.m25.action.InvoiceSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVEBASE");
  bean.setBillType("25");
  bean.setValidationService(getValidateService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService(){
 if(context.get("validateService")!=null)
 return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("validateService");
  nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
  context.put("validateService",bean);
  bean.setValidators(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getPowerSaveValidService());  return list;}

public nc.ui.pubapp.pub.power.PowerSaveValidateService getPowerSaveValidService(){
 if(context.get("powerSaveValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerSaveValidateService)context.get("powerSaveValidService");
  nc.ui.pubapp.pub.power.PowerSaveValidateService bean = new nc.ui.pubapp.pub.power.PowerSaveValidateService();
  context.put("powerSaveValidService",bean);
  bean.setEditActionCode("edit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceApproveAction)context.get("approveAction");
  nc.ui.pu.m25.action.InvoiceApproveAction bean = new nc.ui.pu.m25.action.InvoiceApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("APPROVE");
  bean.setBillType("25");
  bean.setValidationService(getPowerApproveValidService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerApproveValidService(){
 if(context.get("powerApproveValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerApproveValidService");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerApproveValidService",bean);
  bean.setActionCode("approve");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceUnApproveAction)context.get("unApproveAction");
  nc.ui.pu.m25.action.InvoiceUnApproveAction bean = new nc.ui.pu.m25.action.InvoiceUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNAPPROVE");
  bean.setBillType("25");
  bean.setValidationService(getPowerUnapproveValidService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerUnapproveValidService(){
 if(context.get("powerUnapproveValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerUnapproveValidService");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerUnapproveValidService",bean);
  bean.setActionCode("unapprove");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
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

public nc.ui.pu.m25.action.processor.InvoicePrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.m25.action.processor.InvoicePrintProcessor)context.get("printProcessor");
  nc.ui.pu.m25.action.processor.InvoicePrintProcessor bean = new nc.ui.pu.m25.action.processor.InvoicePrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
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
  bean.setNodeKey("4004100002");
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setNodeKey("4004100002");
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setNodeKey("4004100002");
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoicePrintCombineAction getPrintCombineAction(){
 if(context.get("printCombineAction")!=null)
 return (nc.ui.pu.m25.action.InvoicePrintCombineAction)context.get("printCombineAction");
  nc.ui.pu.m25.action.InvoicePrintCombineAction bean = new nc.ui.pu.m25.action.InvoicePrintCombineAction();
  context.put("printCombineAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceSendApAction getSendApAction(){
 if(context.get("sendApAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceSendApAction)context.get("sendApAction");
  nc.ui.pu.m25.action.InvoiceSendApAction bean = new nc.ui.pu.m25.action.InvoiceSendApAction();
  context.put("sendApAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("SENDAP");
  bean.setBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceCancelSendApAction getCancelSendApAction(){
 if(context.get("cancelSendApAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceCancelSendApAction)context.get("cancelSendApAction");
  nc.ui.pu.m25.action.InvoiceCancelSendApAction bean = new nc.ui.pu.m25.action.InvoiceCancelSendApAction();
  context.put("cancelSendApAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("CANCELSENDAP");
  bean.setBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction getSaveCommitAction(){
 if(context.get("saveCommitAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction)context.get("saveCommitAction");
  nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction(getSaveAction(),getSendApproveAction());  context.put("saveCommitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBtnName(getI18nFB_ca8f4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_ca8f4(){
 if(context.get("nc.ui.uif2.I18nFB#ca8f4")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#ca8f4");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#ca8f4",bean);  bean.setResDir("common");
  bean.setResId("2SCMPUB-000027");
  bean.setDefaultValue("保存提交");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#ca8f4",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceSendApproveAction getSendApproveAction(){
 if(context.get("sendApproveAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceSendApproveAction)context.get("sendApproveAction");
  nc.ui.pu.m25.action.InvoiceSendApproveAction bean = new nc.ui.pu.m25.action.InvoiceSendApproveAction();
  context.put("sendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setPreActionNames(getManagedList1());
  bean.setActionName("SAVE");
  bean.setBillType("25");
  bean.setFilledUpInFlow(true);
  bean.setValidationService(getSendpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add("SAVEBASE");  return list;}

public nc.ui.pu.pub.action.UnSaveScriptAction getUnSendApproveAction(){
 if(context.get("unSendApproveAction")!=null)
 return (nc.ui.pu.pub.action.UnSaveScriptAction)context.get("unSendApproveAction");
  nc.ui.pu.pub.action.UnSaveScriptAction bean = new nc.ui.pu.pub.action.UnSaveScriptAction();
  context.put("unSendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNSAVEBILL");
  bean.setBillType("25");
  bean.setFilledUpInFlow(true);
  bean.setValidationService(getUnsendpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getSendpowervalidservice(){
 if(context.get("sendpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("sendpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("sendpowervalidservice",bean);
  bean.setActionCode("commit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnsendpowervalidservice(){
 if(context.get("unsendpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unsendpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unsendpowervalidservice",bean);
  bean.setActionCode("uncommit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("25");
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
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getSendApproveAction());  list.add(getUnSendApproveAction());  return list;}

public nc.ui.pu.m25.action.InvoiceAddFeeBillAction getAddfeeinvoice(){
 if(context.get("addfeeinvoice")!=null)
 return (nc.ui.pu.m25.action.InvoiceAddFeeBillAction)context.get("addfeeinvoice");
  nc.ui.pu.m25.action.InvoiceAddFeeBillAction bean = new nc.ui.pu.m25.action.InvoiceAddFeeBillAction();
  context.put("addfeeinvoice",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceRetFromFeeBillAction getRetfeeinvoice(){
 if(context.get("retfeeinvoice")!=null)
 return (nc.ui.pu.m25.action.InvoiceRetFromFeeBillAction)context.get("retfeeinvoice");
  nc.ui.pu.m25.action.InvoiceRetFromFeeBillAction bean = new nc.ui.pu.m25.action.InvoiceRetFromFeeBillAction();
  context.put("retfeeinvoice",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.FeeInvoiceAddAction getNewfeeinvoice(){
 if(context.get("newfeeinvoice")!=null)
 return (nc.ui.pu.m25.action.FeeInvoiceAddAction)context.get("newfeeinvoice");
  nc.ui.pu.m25.action.FeeInvoiceAddAction bean = new nc.ui.pu.m25.action.FeeInvoiceAddAction();
  context.put("newfeeinvoice",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getFormInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceRef21AddAction getAddFrom21Action(){
 if(context.get("addFrom21Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef21AddAction)context.get("addFrom21Action");
  nc.ui.pu.m25.action.InvoiceRef21AddAction bean = new nc.ui.pu.m25.action.InvoiceRef21AddAction();
  context.put("addFrom21Action",bean);
  bean.setSourceBillType("21");
  bean.setSourceBillName(getI18nFB_1283888());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1283888(){
 if(context.get("nc.ui.uif2.I18nFB#1283888")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1283888");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1283888",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0494");
  bean.setDefaultValue("采购订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1283888",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef4TAddAction getAddFrom4TAction(){
 if(context.get("addFrom4TAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef4TAddAction)context.get("addFrom4TAction");
  nc.ui.pu.m25.action.InvoiceRef4TAddAction bean = new nc.ui.pu.m25.action.InvoiceRef4TAddAction();
  context.put("addFrom4TAction",bean);
  bean.setSourceBillType("4T");
  bean.setSourceBillName(getI18nFB_19783fb());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_19783fb(){
 if(context.get("nc.ui.uif2.I18nFB#19783fb")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#19783fb");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#19783fb",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0490");
  bean.setDefaultValue("期初暂估单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#19783fb",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef4TAddRowsAction getAddRowsFrom4TAction(){
 if(context.get("addRowsFrom4TAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef4TAddRowsAction)context.get("addRowsFrom4TAction");
  nc.ui.pu.m25.action.InvoiceRef4TAddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef4TAddRowsAction();
  context.put("addRowsFrom4TAction",bean);
  bean.setSourceBillType("4T");
  bean.setSourceBillName(getI18nFB_5a06d1());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_5a06d1(){
 if(context.get("nc.ui.uif2.I18nFB#5a06d1")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#5a06d1");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#5a06d1",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0490");
  bean.setDefaultValue("期初暂估单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#5a06d1",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef21AddRowsAction getAddRowsFrom21Action(){
 if(context.get("addRowsFrom21Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef21AddRowsAction)context.get("addRowsFrom21Action");
  nc.ui.pu.m25.action.InvoiceRef21AddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef21AddRowsAction();
  context.put("addRowsFrom21Action",bean);
  bean.setSourceBillType("21");
  bean.setSourceBillName(getI18nFB_75bcf7());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_75bcf7(){
 if(context.get("nc.ui.uif2.I18nFB#75bcf7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#75bcf7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#75bcf7",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0494");
  bean.setDefaultValue("采购订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#75bcf7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef45AddAction getAddFrom45Action(){
 if(context.get("addFrom45Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef45AddAction)context.get("addFrom45Action");
  nc.ui.pu.m25.action.InvoiceRef45AddAction bean = new nc.ui.pu.m25.action.InvoiceRef45AddAction();
  context.put("addFrom45Action",bean);
  bean.setSourceBillType("45");
  bean.setSourceBillName(getI18nFB_1c90fda());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1c90fda(){
 if(context.get("nc.ui.uif2.I18nFB#1c90fda")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1c90fda");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1c90fda",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0438");
  bean.setDefaultValue("采购入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1c90fda",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef45AddRowsAction getAddRowsFrom45Action(){
 if(context.get("addRowsFrom45Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef45AddRowsAction)context.get("addRowsFrom45Action");
  nc.ui.pu.m25.action.InvoiceRef45AddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef45AddRowsAction();
  context.put("addRowsFrom45Action",bean);
  bean.setSourceBillType("45");
  bean.setSourceBillName(getI18nFB_12ff0b3());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_12ff0b3(){
 if(context.get("nc.ui.uif2.I18nFB#12ff0b3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#12ff0b3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#12ff0b3",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0438");
  bean.setDefaultValue("采购入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#12ff0b3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef50AddAction getAddFrom50Action(){
 if(context.get("addFrom50Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef50AddAction)context.get("addFrom50Action");
  nc.ui.pu.m25.action.InvoiceRef50AddAction bean = new nc.ui.pu.m25.action.InvoiceRef50AddAction();
  context.put("addFrom50Action",bean);
  bean.setSourceBillType("50");
  bean.setSourceBillName(getI18nFB_639f5c());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_639f5c(){
 if(context.get("nc.ui.uif2.I18nFB#639f5c")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#639f5c");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#639f5c",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0479");
  bean.setDefaultValue("消耗汇总");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#639f5c",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef50AddRowsAction getAddRowsFrom50Action(){
 if(context.get("addRowsFrom50Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef50AddRowsAction)context.get("addRowsFrom50Action");
  nc.ui.pu.m25.action.InvoiceRef50AddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef50AddRowsAction();
  context.put("addRowsFrom50Action",bean);
  bean.setSourceBillType("50");
  bean.setSourceBillName(getI18nFB_73544a());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_73544a(){
 if(context.get("nc.ui.uif2.I18nFB#73544a")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#73544a");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#73544a",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0479");
  bean.setDefaultValue("消耗汇总");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#73544a",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef61AddAction getAddFrom61Action(){
 if(context.get("addFrom61Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef61AddAction)context.get("addFrom61Action");
  nc.ui.pu.m25.action.InvoiceRef61AddAction bean = new nc.ui.pu.m25.action.InvoiceRef61AddAction();
  context.put("addFrom61Action",bean);
  bean.setSourceBillType("61");
  bean.setSourceBillName(getI18nFB_1a30307());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1a30307(){
 if(context.get("nc.ui.uif2.I18nFB#1a30307")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1a30307");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1a30307",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0515");
  bean.setDefaultValue("委外订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1a30307",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef61AddRowsAction getAddRowsFrom61Action(){
 if(context.get("addRowsFrom61Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef61AddRowsAction)context.get("addRowsFrom61Action");
  nc.ui.pu.m25.action.InvoiceRef61AddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef61AddRowsAction();
  context.put("addRowsFrom61Action",bean);
  bean.setSourceBillType("61");
  bean.setSourceBillName(getI18nFB_11bc78f());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_11bc78f(){
 if(context.get("nc.ui.uif2.I18nFB#11bc78f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#11bc78f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#11bc78f",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0515");
  bean.setDefaultValue("委外订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#11bc78f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef47AddAction getAddFrom47Action(){
 if(context.get("addFrom47Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef47AddAction)context.get("addFrom47Action");
  nc.ui.pu.m25.action.InvoiceRef47AddAction bean = new nc.ui.pu.m25.action.InvoiceRef47AddAction();
  context.put("addFrom47Action",bean);
  bean.setSourceBillType("47");
  bean.setSourceBillName(getI18nFB_caa2c8());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_caa2c8(){
 if(context.get("nc.ui.uif2.I18nFB#caa2c8")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#caa2c8");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#caa2c8",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0450");
  bean.setDefaultValue("委托加工入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#caa2c8",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef47AddRowsAction getAddRowsFrom47Action(){
 if(context.get("addRowsFrom47Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef47AddRowsAction)context.get("addRowsFrom47Action");
  nc.ui.pu.m25.action.InvoiceRef47AddRowsAction bean = new nc.ui.pu.m25.action.InvoiceRef47AddRowsAction();
  context.put("addRowsFrom47Action",bean);
  bean.setSourceBillType("47");
  bean.setSourceBillName(getI18nFB_18518ac());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_18518ac(){
 if(context.get("nc.ui.uif2.I18nFB#18518ac")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#18518ac");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#18518ac",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0450");
  bean.setDefaultValue("委托加工入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#18518ac",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceRef55E6AddAction getAddFrom55E6Action(){
 if(context.get("addFrom55E6Action")!=null)
 return (nc.ui.pu.m25.action.InvoiceRef55E6AddAction)context.get("addFrom55E6Action");
  nc.ui.pu.m25.action.InvoiceRef55E6AddAction bean = new nc.ui.pu.m25.action.InvoiceRef55E6AddAction();
  context.put("addFrom55E6Action",bean);
  bean.setSourceBillType("55E6");
  bean.setSourceBillName(getI18nFB_116fa53());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_116fa53(){
 if(context.get("nc.ui.uif2.I18nFB#116fa53")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#116fa53");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#116fa53",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0653");
  bean.setDefaultValue("工序委外加工费");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#116fa53",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceHqhpAction getHqhpAction(){
 if(context.get("hqhpAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceHqhpAction)context.get("hqhpAction");
  nc.ui.pu.m25.action.InvoiceHqhpAction bean = new nc.ui.pu.m25.action.InvoiceHqhpAction();
  context.put("hqhpAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceFrozenAction getFrozenAction(){
 if(context.get("frozenAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceFrozenAction)context.get("frozenAction");
  nc.ui.pu.m25.action.InvoiceFrozenAction bean = new nc.ui.pu.m25.action.InvoiceFrozenAction();
  context.put("frozenAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceUnFrozenAction getUnFrozenAction(){
 if(context.get("unFrozenAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceUnFrozenAction)context.get("unFrozenAction");
  nc.ui.pu.m25.action.InvoiceUnFrozenAction bean = new nc.ui.pu.m25.action.InvoiceUnFrozenAction();
  context.put("unFrozenAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.FileDocManageAction getDocMngAction(){
 if(context.get("docMngAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.FileDocManageAction)context.get("docMngAction");
  nc.ui.pubapp.uif2app.actions.FileDocManageAction bean = new nc.ui.pubapp.uif2app.actions.FileDocManageAction();
  context.put("docMngAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.action.PULinkQueryAction getLinkQueryBill(){
 if(context.get("linkQueryBill")!=null)
 return (nc.ui.pu.pub.action.PULinkQueryAction)context.get("linkQueryBill");
  nc.ui.pu.pub.action.PULinkQueryAction bean = new nc.ui.pu.pub.action.PULinkQueryAction();
  context.put("linkQueryBill",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.LinkQueryFeeInvoiceAction getLinkQueryFeeInvoice(){
 if(context.get("linkQueryFeeInvoice")!=null)
 return (nc.ui.pu.m25.action.LinkQueryFeeInvoiceAction)context.get("linkQueryFeeInvoice");
  nc.ui.pu.m25.action.LinkQueryFeeInvoiceAction bean = new nc.ui.pu.m25.action.LinkQueryFeeInvoiceAction();
  context.put("linkQueryFeeInvoice",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getLinkQueryAuditFlowStatus(){
 if(context.get("linkQueryAuditFlowStatus")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("linkQueryAuditFlowStatus");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("linkQueryAuditFlowStatus",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceAddManualAction getAddManualAction(){
 if(context.get("addManualAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceAddManualAction)context.get("addManualAction");
  nc.ui.pu.m25.action.InvoiceAddManualAction bean = new nc.ui.pu.m25.action.InvoiceAddManualAction();
  context.put("addManualAction",bean);
  bean.setSourceBillType("MANUAL");
  bean.setSourceBillName(getI18nFB_18c701d());
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getFormInterceptor());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_18c701d(){
 if(context.get("nc.ui.uif2.I18nFB#18c701d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#18c701d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#18c701d",bean);  bean.setResDir("common");
  bean.setResId("14004000-0000");
  bean.setDefaultValue("自制");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#18c701d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m25.action.InvoiceAddMenuAction getAddMenu(){
 if(context.get("addMenu")!=null)
 return (nc.ui.pu.m25.action.InvoiceAddMenuAction)context.get("addMenu");
  nc.ui.pu.m25.action.InvoiceAddMenuAction bean = new nc.ui.pu.m25.action.InvoiceAddMenuAction();
  context.put("addMenu",bean);
  bean.setBillType("25");
  bean.setTooltip(getI18nFB_114b714());
  bean.setActions(getManagedList3());
  bean.setModel(getManageAppModel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_114b714(){
 if(context.get("nc.ui.uif2.I18nFB#114b714")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#114b714");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#114b714",bean);  bean.setResDir("common");
  bean.setResId("04004000-0001");
  bean.setDefaultValue("新增业务数据(Ctrl+N)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#114b714",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList3(){  List list = new ArrayList();  list.add(getAddManualAction());  list.add(getSeparatorAction_ffaba2());  list.add(getAddFrom55E6Action());  list.add(getAddFrom21Action());  list.add(getAddFrom45Action());  list.add(getAddFrom61Action());  list.add(getAddFrom50Action());  list.add(getAddFrom47Action());  list.add(getAddFrom4TAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_ffaba2(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#ffaba2")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#ffaba2");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#ffaba2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getInvoiceApproveMenuAction(){
 if(context.get("invoiceApproveMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("invoiceApproveMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("invoiceApproveMenuAction",bean);
  bean.setCode("auditMenuAction");
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction_15d2cae());  list.add(getLinkQueryAuditFlowStatus());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_15d2cae(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#15d2cae")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#15d2cae");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#15d2cae",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.RefAddRowsMenuAction getRefAddRowsMenuAction(){
 if(context.get("refAddRowsMenuAction")!=null)
 return (nc.ui.pu.m25.action.RefAddRowsMenuAction)context.get("refAddRowsMenuAction");
  nc.ui.pu.m25.action.RefAddRowsMenuAction bean = new nc.ui.pu.m25.action.RefAddRowsMenuAction();
  context.put("refAddRowsMenuAction",bean);
  bean.setActions(getManagedList5());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getAddRowsFrom21Action());  list.add(getAddRowsFrom45Action());  list.add(getAddRowsFrom4TAction());  list.add(getAddRowsFrom50Action());  list.add(getAddRowsFrom61Action());  list.add(getAddRowsFrom47Action());  return list;}

public nc.ui.pu.m25.action.InvoiceAssistMenuAction getAssistMenuAction(){
 if(context.get("assistMenuAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceAssistMenuAction)context.get("assistMenuAction");
  nc.ui.pu.m25.action.InvoiceAssistMenuAction bean = new nc.ui.pu.m25.action.InvoiceAssistMenuAction();
  context.put("assistMenuAction",bean);
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getFrozenAction());  list.add(getUnFrozenAction());  list.add(getSeparatorAction_20825e());  list.add(getDocMngAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_20825e(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#20825e")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#20825e");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#20825e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.InvoiceAssistMenuAction getNormalEditAstMenuAction(){
 if(context.get("normalEditAstMenuAction")!=null)
 return (nc.ui.pu.m25.action.InvoiceAssistMenuAction)context.get("normalEditAstMenuAction");
  nc.ui.pu.m25.action.InvoiceAssistMenuAction bean = new nc.ui.pu.m25.action.InvoiceAssistMenuAction();
  context.put("normalEditAstMenuAction",bean);
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getHqhpAction());  return list;}

public nc.ui.pu.m25.action.LinkQueryMenuAction getLinkQueryMenuAction(){
 if(context.get("linkQueryMenuAction")!=null)
 return (nc.ui.pu.m25.action.LinkQueryMenuAction)context.get("linkQueryMenuAction");
  nc.ui.pu.m25.action.LinkQueryMenuAction bean = new nc.ui.pu.m25.action.LinkQueryMenuAction();
  context.put("linkQueryMenuAction",bean);
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getLinkQueryBill());  list.add(getSeparatorAction_4f0b3c());  list.add(getLinkQueryFeeInvoice());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_4f0b3c(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#4f0b3c")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#4f0b3c");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#4f0b3c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.action.RelatingFunctionsMenuAction getRelatingFunctionsMenuAction(){
 if(context.get("relatingFunctionsMenuAction")!=null)
 return (nc.ui.pu.m25.action.RelatingFunctionsMenuAction)context.get("relatingFunctionsMenuAction");
  nc.ui.pu.m25.action.RelatingFunctionsMenuAction bean = new nc.ui.pu.m25.action.RelatingFunctionsMenuAction();
  context.put("relatingFunctionsMenuAction",bean);
  bean.setActions(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getSendApAction());  list.add(getCancelSendApAction());  list.add(getAddfeeinvoice());  return list;}

public nc.ui.pu.m25.action.RelatingFunctionsMenuAction getRelatingFunctionsMenuActionFee(){
 if(context.get("relatingFunctionsMenuActionFee")!=null)
 return (nc.ui.pu.m25.action.RelatingFunctionsMenuAction)context.get("relatingFunctionsMenuActionFee");
  nc.ui.pu.m25.action.RelatingFunctionsMenuAction bean = new nc.ui.pu.m25.action.RelatingFunctionsMenuAction();
  context.put("relatingFunctionsMenuActionFee",bean);
  bean.setActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getSendApAction());  list.add(getCancelSendApAction());  return list;}

public nc.ui.pu.m25.action.RelatingFunctionsMenuAction getRelatingFunctionsMenuActionEdit(){
 if(context.get("relatingFunctionsMenuActionEdit")!=null)
 return (nc.ui.pu.m25.action.RelatingFunctionsMenuAction)context.get("relatingFunctionsMenuActionEdit");
  nc.ui.pu.m25.action.RelatingFunctionsMenuAction bean = new nc.ui.pu.m25.action.RelatingFunctionsMenuAction();
  context.put("relatingFunctionsMenuActionEdit",bean);
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  return list;}

public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.scmpub.action.SCMPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBilldateFieldName("dbilldate");
  bean.setBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getInvoicePrintMenuAction(){
 if(context.get("invoicePrintMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("invoicePrintMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("invoicePrintMenuAction",bean);
  bean.setCode("printMenuAction");
  bean.setActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  list.add(getPrintCountQueryAction());  list.add(getSeparatorAction_16ac397());  list.add(getPrintCombineAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_16ac397(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#16ac397")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#16ac397");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#16ac397",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.org.OrgChangedEventHandler getMainorgchghandler(){
 if(context.get("mainorgchghandler")!=null)
 return (nc.ui.pu.m25.editor.org.OrgChangedEventHandler)context.get("mainorgchghandler");
  nc.ui.pu.m25.editor.org.OrgChangedEventHandler bean = new nc.ui.pu.m25.editor.org.OrgChangedEventHandler();
  context.put("mainorgchghandler",bean);
  bean.setCardForm(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler getCard_before_headtail_edithandler(){
 if(context.get("card_before_headtail_edithandler")!=null)
 return (nc.ui.pu.m25.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler)context.get("card_before_headtail_edithandler");
  nc.ui.pu.m25.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler bean = new nc.ui.pu.m25.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler();
  context.put("card_before_headtail_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.card.afteredit.CardHeadTailAfterEditEventHandler getCard_after_headtail_edithandler(){
 if(context.get("card_after_headtail_edithandler")!=null)
 return (nc.ui.pu.m25.editor.card.afteredit.CardHeadTailAfterEditEventHandler)context.get("card_after_headtail_edithandler");
  nc.ui.pu.m25.editor.card.afteredit.CardHeadTailAfterEditEventHandler bean = new nc.ui.pu.m25.editor.card.afteredit.CardHeadTailAfterEditEventHandler();
  context.put("card_after_headtail_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.card.beforeedit.CardBodyBeforeEditEventHandler getCard_before_body_edithandler(){
 if(context.get("card_before_body_edithandler")!=null)
 return (nc.ui.pu.m25.editor.card.beforeedit.CardBodyBeforeEditEventHandler)context.get("card_before_body_edithandler");
  nc.ui.pu.m25.editor.card.beforeedit.CardBodyBeforeEditEventHandler bean = new nc.ui.pu.m25.editor.card.beforeedit.CardBodyBeforeEditEventHandler();
  context.put("card_before_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.card.afteredit.CardBodyAfterEditEventHandler getCard_after_body_edithandler(){
 if(context.get("card_after_body_edithandler")!=null)
 return (nc.ui.pu.m25.editor.card.afteredit.CardBodyAfterEditEventHandler)context.get("card_after_body_edithandler");
  nc.ui.pu.m25.editor.card.afteredit.CardBodyAfterEditEventHandler bean = new nc.ui.pu.m25.editor.card.afteredit.CardBodyAfterEditEventHandler();
  context.put("card_after_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.list.HeadRowChangeHandler getList_head_rowchangehandler(){
 if(context.get("list_head_rowchangehandler")!=null)
 return (nc.ui.pu.m25.editor.list.HeadRowChangeHandler)context.get("list_head_rowchangehandler");
  nc.ui.pu.m25.editor.list.HeadRowChangeHandler bean = new nc.ui.pu.m25.editor.list.HeadRowChangeHandler();
  context.put("list_head_rowchangehandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.card.InvoiceAddEventHandler getInvoiceAddHandler(){
 if(context.get("invoiceAddHandler")!=null)
 return (nc.ui.pu.m25.editor.card.InvoiceAddEventHandler)context.get("invoiceAddHandler");
  nc.ui.pu.m25.editor.card.InvoiceAddEventHandler bean = new nc.ui.pu.m25.editor.card.InvoiceAddEventHandler();
  context.put("invoiceAddHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.editor.TotalValueHanlder getTotalvaluehandler(){
 if(context.get("totalvaluehandler")!=null)
 return (nc.ui.pu.m25.editor.TotalValueHanlder)context.get("totalvaluehandler");
  nc.ui.pu.m25.editor.TotalValueHanlder bean = new nc.ui.pu.m25.editor.TotalValueHanlder();
  context.put("totalvaluehandler",bean);
  bean.setBodyHeadMap(getManagedMap0());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nastnum","ntotalastnum");  map.put("norigtaxmny","ntotalorigmny");  return map;}

public nc.ui.pu.m25.editor.utils.RelationCalculate getRelationCalculate(){
 if(context.get("relationCalculate")!=null)
 return (nc.ui.pu.m25.editor.utils.RelationCalculate)context.get("relationCalculate");
  nc.ui.pu.m25.editor.utils.RelationCalculate bean = new nc.ui.pu.m25.editor.utils.RelationCalculate();
  context.put("relationCalculate",bean);
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
  bean.setHandlerMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList13());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList14());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList15());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList16());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList17());  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList18());  map.put("nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent",getManagedList19());  map.put("nc.ui.pubapp.uif2app.event.billform.AddEvent",getManagedList20());  return map;}

private List getManagedList13(){  List list = new ArrayList();  list.add(getCard_before_headtail_edithandler());  return list;}

private List getManagedList14(){  List list = new ArrayList();  list.add(getCard_after_headtail_edithandler());  return list;}

private List getManagedList15(){  List list = new ArrayList();  list.add(getCard_before_body_edithandler());  return list;}

private List getManagedList16(){  List list = new ArrayList();  list.add(getCard_after_body_edithandler());  list.add(getRelationCalculate());  return list;}

private List getManagedList17(){  List list = new ArrayList();  list.add(getList_head_rowchangehandler());  return list;}

private List getManagedList18(){  List list = new ArrayList();  list.add(getMainorgchghandler());  return list;}

private List getManagedList19(){  List list = new ArrayList();  list.add(getFeeViewTransfer());  return list;}

private List getManagedList20(){  List list = new ArrayList();  list.add(getInvoiceAddHandler());  return list;}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setVoClassName("nc.vo.pu.m25.entity.InvoiceVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
  bean.setQueryAction(getQueryAction());
  bean.setProcessorMap(getManagedMap2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("19",getInitDataProcessor_1667da3());  map.put("40",getInitDataForTbbProcessor_1c27cf4());  return map;}

private nc.ui.pu.m25.billref.processor.InitDataProcessor getInitDataProcessor_1667da3(){
 if(context.get("nc.ui.pu.m25.billref.processor.InitDataProcessor#1667da3")!=null)
 return (nc.ui.pu.m25.billref.processor.InitDataProcessor)context.get("nc.ui.pu.m25.billref.processor.InitDataProcessor#1667da3");
  nc.ui.pu.m25.billref.processor.InitDataProcessor bean = new nc.ui.pu.m25.billref.processor.InitDataProcessor();
  context.put("nc.ui.pu.m25.billref.processor.InitDataProcessor#1667da3",bean);
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor getInitDataForTbbProcessor_1c27cf4(){
 if(context.get("nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor#1c27cf4")!=null)
 return (nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor)context.get("nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor#1c27cf4");
  nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor bean = new nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor();
  context.put("nc.ui.pu.m25.billref.processor.InitDataForTbbProcessor#1c27cf4",bean);
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

public nc.ui.pu.m25.model.InvoiceModelServcie getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.pu.m25.model.InvoiceModelServcie)context.get("ManageModelService");
  nc.ui.pu.m25.model.InvoiceModelServcie bean = new nc.ui.pu.m25.model.InvoiceModelServcie();
  context.put("ManageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

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

public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.model.InvoiceBillManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.pu.m25.model.InvoiceBillManageModel)context.get("ManageAppModel");
  nc.ui.pu.m25.model.InvoiceBillManageModel bean = new nc.ui.pu.m25.model.InvoiceBillManageModel();
  context.put("ManageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
  bean.setBillType("25");
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
  bean.setPagePanel(getQueryInfo());
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

public nc.ui.pu.m25.model.InvoicePageModelService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.pu.m25.model.InvoicePageModelService)context.get("pageQuery");
  nc.ui.pu.m25.model.InvoicePageModelService bean = new nc.ui.pu.m25.model.InvoicePageModelService();
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

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getBillTemplateMender(){
 if(context.get("billTemplateMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("billTemplateMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("billTemplateMender",bean);
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
  bean.setBillTemplateMender(getBillTemplateMender());
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

public nc.ui.pu.m25.view.InvoiceBillListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pu.m25.view.InvoiceBillListView)context.get("listView");
  nc.ui.pu.m25.view.InvoiceBillListView bean = new nc.ui.pu.m25.view.InvoiceBillListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionMode(0);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setPaginationBar(getPageBar());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public java.util.ArrayList getPasteClearItem(){
 if(context.get("pasteClearItem")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList21());  context.put("pasteClearItem",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList21(){  List list = new ArrayList();  list.add("naccumsettmny");  list.add("naccumsettnum");  list.add("pk_invoice_b");  list.add("ts");  return list;}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVsourcecodeMediator(){
 if(context.get("vsourcecodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vsourcecodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vsourcecodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csourceid");
  bean.setSrcBillNOField("vsourcecode");
  bean.setSrcBillTypeField("csourcetypecode");
  bean.setSrcBillTypeFieldPos(1);
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
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.view.InvoiceBillForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.pu.m25.view.InvoiceBillForm)context.get("billFormEditor");
  nc.ui.pu.m25.view.InvoiceBillForm bean = new nc.ui.pu.m25.view.InvoiceBillForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setTemplateNotNullValidate(true);
  bean.setAutoAddLine(true);
  bean.setBlankChildrenFilter(getBlankitemfilter());
  bean.setBodyLineActions(getManagedList22());
  bean.setTotalValueHandler(getTotalvaluehandler());
  bean.setClearHyperlink(getManagedList23());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList22(){  List list = new ArrayList();  list.add(getInvoiceBodyAddLineAction_1998462());  list.add(getInvoiceBodyInsertLineAction_1c25658());  list.add(getBodyDelLineAction_1b2af62());  list.add(getBodyCopyLineAction_18801e());  list.add(getBodyPasteLineAction_8333ab());  list.add(getBodyPasteToTailAction_1b5531a());  list.add(getActionsBar_ActionsBarSeparator_1630213());  list.add(getBodyLineEditAction_11d5202());  list.add(getRearrangeRowNoBodyLineAction_1f31c8c());  list.add(getActionsBar_ActionsBarSeparator_1d7379c());  list.add(getDefaultBodyZoomAction_8410d8());  return list;}

private nc.ui.pu.m25.action.InvoiceBodyAddLineAction getInvoiceBodyAddLineAction_1998462(){
 if(context.get("nc.ui.pu.m25.action.InvoiceBodyAddLineAction#1998462")!=null)
 return (nc.ui.pu.m25.action.InvoiceBodyAddLineAction)context.get("nc.ui.pu.m25.action.InvoiceBodyAddLineAction#1998462");
  nc.ui.pu.m25.action.InvoiceBodyAddLineAction bean = new nc.ui.pu.m25.action.InvoiceBodyAddLineAction();
  context.put("nc.ui.pu.m25.action.InvoiceBodyAddLineAction#1998462",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m25.action.InvoiceBodyInsertLineAction getInvoiceBodyInsertLineAction_1c25658(){
 if(context.get("nc.ui.pu.m25.action.InvoiceBodyInsertLineAction#1c25658")!=null)
 return (nc.ui.pu.m25.action.InvoiceBodyInsertLineAction)context.get("nc.ui.pu.m25.action.InvoiceBodyInsertLineAction#1c25658");
  nc.ui.pu.m25.action.InvoiceBodyInsertLineAction bean = new nc.ui.pu.m25.action.InvoiceBodyInsertLineAction();
  context.put("nc.ui.pu.m25.action.InvoiceBodyInsertLineAction#1c25658",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_1b2af62(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1b2af62")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1b2af62");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1b2af62",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_18801e(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#18801e")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#18801e");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#18801e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_8333ab(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#8333ab")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#8333ab");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#8333ab",bean);
  bean.setClearItems(getPasteClearItem());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_1b5531a(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1b5531a")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1b5531a");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1b5531a",bean);
  bean.setClearItems(getPasteClearItem());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1630213(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1630213")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1630213");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1630213",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_11d5202(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#11d5202")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#11d5202");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#11d5202",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_1f31c8c(){
 if(context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f31c8c")!=null)
 return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f31c8c");
  nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f31c8c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1d7379c(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d7379c")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d7379c");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d7379c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_8410d8(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#8410d8")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#8410d8");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#8410d8",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList23(){  List list = new ArrayList();  list.add("vbillcode");  return list;}

public nc.ui.pu.m25.linkquery.InvoiceLinkQueryHyperlinkMediator getParentCodeLinkQueryHyperlinkMediator(){
 if(context.get("parentCodeLinkQueryHyperlinkMediator")!=null)
 return (nc.ui.pu.m25.linkquery.InvoiceLinkQueryHyperlinkMediator)context.get("parentCodeLinkQueryHyperlinkMediator");
  nc.ui.pu.m25.linkquery.InvoiceLinkQueryHyperlinkMediator bean = new nc.ui.pu.m25.linkquery.InvoiceLinkQueryHyperlinkMediator();
  context.put("parentCodeLinkQueryHyperlinkMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("pk_parentinvoice");
  bean.setSrcBillNOField("vparentcode");
  bean.setSrcBillType("25");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getBlankitemfilter(){
 if(context.get("blankitemfilter")!=null)
 return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter)context.get("blankitemfilter");
  nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
  context.put("blankitemfilter",bean);
  bean.setFieldName("pk_material");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.FunNodeClosingHandler getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.uif2.FunNodeClosingHandler)context.get("ClosingListener");
  nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveaction(getSaveAction());
  bean.setCancelaction(getCancelAction());
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

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

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

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo(){
 if(context.get("queryInfo")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("queryInfo");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("queryInfo",bean);
  bean.setModel(getManageAppModel());
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
  bean.setRightExActions(getManagedList24());
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

private List getManagedList24(){  List list = new ArrayList();  list.add(getActionsBar_ActionsBarSeparator_6df5ff());  list.add(getHeadZoomAction());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_6df5ff(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#6df5ff")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#6df5ff");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#6df5ff",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction(){
 if(context.get("headZoomAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)context.get("headZoomAction");
  nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
  context.put("headZoomAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setPos(0);
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
  bean.setTangramLayoutRoot(getTBNode_1eeeec0());
  bean.setActions(getManagedList26());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1eeeec0(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1eeeec0")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1eeeec0");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1eeeec0",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList25());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList25(){  List list = new ArrayList();  list.add(getHSNode_70432());  list.add(getVSNode_1b8324f());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_70432(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#70432")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#70432");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#70432",bean);
  bean.setLeft(getCNode_13147f4());
  bean.setRight(getVSNode_b570e5());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_13147f4(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#13147f4")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#13147f4");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#13147f4",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_b570e5(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#b570e5")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#b570e5");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#b570e5",bean);
  bean.setUp(getCNode_6b07a9());
  bean.setDown(getCNode_d22daf());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_6b07a9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#6b07a9")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#6b07a9");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#6b07a9",bean);
  bean.setComponent(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_d22daf(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#d22daf")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#d22daf");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#d22daf",bean);
  bean.setName(getI18nFB_c3fbf3());
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_c3fbf3(){
 if(context.get("nc.ui.uif2.I18nFB#c3fbf3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#c3fbf3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#c3fbf3",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#c3fbf3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1b8324f(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1b8324f")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1b8324f");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1b8324f",bean);
  bean.setUp(getCNode_a47af0());
  bean.setDown(getCNode_a9e401());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_a47af0(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#a47af0")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#a47af0");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#a47af0",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_a9e401(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#a9e401")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#a9e401");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#a9e401",bean);
  bean.setName(getI18nFB_835e63());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_835e63(){
 if(context.get("nc.ui.uif2.I18nFB#835e63")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#835e63");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#835e63",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#835e63",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList26(){  List list = new ArrayList();  return list;}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList27());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList27(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList28());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList28(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction_17cad60());  list.add(getQueryAction());  list.add(getListRefreshAction());  list.add(getSeparatorAction_5aa578());  list.add(getSendApproveMenuAction());  list.add(getInvoiceApproveMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction_125a0db());  list.add(getLinkQueryMenuAction());  list.add(getSeparatorAction_163a418());  list.add(getRelatingFunctionsMenuAction());  list.add(getSeparatorAction_aa5abc());  list.add(getInvoicePrintMenuAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_17cad60(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#17cad60")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#17cad60");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#17cad60",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_5aa578(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#5aa578")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#5aa578");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#5aa578",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_125a0db(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#125a0db")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#125a0db");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#125a0db",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_163a418(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#163a418")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#163a418");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#163a418",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_aa5abc(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#aa5abc")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#aa5abc");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#aa5abc",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public java.util.ArrayList getNormEditAction(){
 if(context.get("normEditAction")!=null)
 return (java.util.ArrayList)context.get("normEditAction");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList29());  context.put("normEditAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList29(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSaveCommitAction());  list.add(getSeparatorAction_2b1b24());  list.add(getCancelAction());  list.add(getSeparatorAction_30ce3());  list.add(getRefAddRowsMenuAction());  list.add(getNormalEditAstMenuAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_2b1b24(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#2b1b24")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#2b1b24");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#2b1b24",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_30ce3(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#30ce3")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#30ce3");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#30ce3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setActions(getManagedList30());
  bean.setEditActions(getNormEditAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList30(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction_431765());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction_b52177());  list.add(getSendApproveMenuAction());  list.add(getInvoiceApproveMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction_1a82ede());  list.add(getLinkQueryMenuAction());  list.add(getSeparatorAction_9d19ad());  list.add(getRelatingFunctionsMenuAction());  list.add(getSeparatorAction_c2b6());  list.add(getInvoicePrintMenuAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_431765(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#431765")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#431765");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#431765",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_b52177(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#b52177")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#b52177");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#b52177",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1a82ede(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1a82ede")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1a82ede");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1a82ede",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_9d19ad(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#9d19ad")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#9d19ad");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#9d19ad",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_c2b6(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#c2b6")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#c2b6");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#c2b6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.view.FeeInvoiceViewTransfer getFeeViewTransfer(){
 if(context.get("feeViewTransfer")!=null)
 return (nc.ui.pu.m25.view.FeeInvoiceViewTransfer)context.get("feeViewTransfer");
  nc.ui.pu.m25.view.FeeInvoiceViewTransfer bean = new nc.ui.pu.m25.view.FeeInvoiceViewTransfer();
  context.put("feeViewTransfer",bean);
  bean.setFeeCardAction(getManagedList31());
  bean.setFeeListAction(getManagedList32());
  bean.setFeeEditAction(getManagedList33());
  bean.setNormEditAction(getNormEditAction());
  bean.setActionContributor(getToftpanelActionContributors());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList31(){  List list = new ArrayList();  list.add(getNewfeeinvoice());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction_1ef5359());  list.add(getSendApproveMenuAction());  list.add(getInvoiceApproveMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction_148f90d());  list.add(getRelatingFunctionsMenuActionFee());  list.add(getSeparatorAction_1d6c837());  list.add(getInvoicePrintMenuAction());  list.add(getRetfeeinvoice());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1ef5359(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1ef5359")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1ef5359");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1ef5359",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_148f90d(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#148f90d")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#148f90d");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#148f90d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1d6c837(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1d6c837")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1d6c837");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1d6c837",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList32(){  List list = new ArrayList();  list.add(getNewfeeinvoice());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction_1f2b7b9());  list.add(getSendApproveMenuAction());  list.add(getInvoiceApproveMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction_1fc06f9());  list.add(getRelatingFunctionsMenuActionFee());  list.add(getSeparatorAction_12dd6dd());  list.add(getInvoicePrintMenuAction());  list.add(getRetfeeinvoice());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1f2b7b9(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1f2b7b9")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1f2b7b9");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1f2b7b9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1fc06f9(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1fc06f9")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1fc06f9");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1fc06f9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_12dd6dd(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#12dd6dd")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#12dd6dd");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#12dd6dd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList33(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getCancelAction());  list.add(getSendApproveAction());  return list;}

public nc.ui.pu.m25.billref.processor.InvoiceTransferListProcessor getInvoiceTransferListProcessor(){
 if(context.get("invoiceTransferListProcessor")!=null)
 return (nc.ui.pu.m25.billref.processor.InvoiceTransferListProcessor)context.get("invoiceTransferListProcessor");
  nc.ui.pu.m25.billref.processor.InvoiceTransferListProcessor bean = new nc.ui.pu.m25.billref.processor.InvoiceTransferListProcessor();
  context.put("invoiceTransferListProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor(){
 if(context.get("transferViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getActionsOfList());
  bean.setCardActionContainer(getActionsOfCard());
  bean.setSaveAction(getSaveAction());
  bean.setCommitAction(getSendApproveAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillForm(getBillFormEditor());
  bean.setQueryAreaShell(getQueryArea());
  bean.setQueryInfoToolbarPanel(getQueryInfo());
  bean.setTransferLogic(getDefaultBillDataLogic_755c4a());
  bean.setListProcessor(getInvoiceTransferListProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_755c4a(){
 if(context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#755c4a")!=null)
 return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic)context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#755c4a");
  nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
  context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#755c4a",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor(),getListView());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billCodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billCodeMediator",bean);
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("25");
  bean.setBillForm(getBillFormEditor());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList34(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList35());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList35(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList36());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList36(){  List list = new ArrayList();  list.add(getUserdefQueryParam_fdfe82());  list.add(getUserdefQueryParam_150159f());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_fdfe82(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#fdfe82")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#fdfe82");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#fdfe82",bean);
  bean.setMdfullname("pu.po_invoice");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_150159f(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#150159f")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#150159f");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#150159f",bean);
  bean.setMdfullname("pu.po_invoice_b");
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
  bean.setParams(getManagedList37());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList37(){  List list = new ArrayList();  list.add(getUserdefQueryParam_bba059());  list.add(getUserdefQueryParam_1c6ff5());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_bba059(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#bba059")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#bba059");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#bba059",bean);
  bean.setMdfullname("pu.po_invoice");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1c6ff5(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1c6ff5")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1c6ff5");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1c6ff5",bean);
  bean.setMdfullname("pu.po_invoice_b");
  bean.setPos(1);
  bean.setTabcode("invoicebody");
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
  bean.setModel(getManageAppModel());
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
  bean.setParams(getManagedList38());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList38(){  List list = new ArrayList();  list.add(getQueryParam_fa4a30());  list.add(getQueryParam_1cd049());  list.add(getQueryParam_1ae6fdc());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_fa4a30(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#fa4a30")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#fa4a30");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#fa4a30",bean);
  bean.setMdfullname("pu.po_invoice");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1cd049(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1cd049")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1cd049");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1cd049",bean);
  bean.setMdfullname("pu.po_invoice_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1ae6fdc(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1ae6fdc")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1ae6fdc");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1ae6fdc",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m25.query.InvoiceLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pu.m25.query.InvoiceLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pu.m25.query.InvoiceLazilyLoader bean = new nc.ui.pu.m25.query.InvoiceLazilyLoader();
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
  bean.setActionList(getManagedList39());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList39(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("LasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("LasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("LasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList40());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList40(){  List list = new ArrayList();  list.add(getLazyActions());  list.add(getCardPanelLazilyLoad_131d76d());  list.add(getListPanelLazilyLoad_1367658());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_131d76d(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#131d76d")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#131d76d");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#131d76d",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1367658(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1367658")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1367658");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1367658",bean);
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
  bean.setRemoteCallers(getManagedList41());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList41(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}

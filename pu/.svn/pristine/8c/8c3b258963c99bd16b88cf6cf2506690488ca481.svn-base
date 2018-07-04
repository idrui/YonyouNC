package nc.ui.pu.est.config.m45;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class purchaseinest_config extends AbstractJavaBeanDefinition{
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

public nc.ui.pu.est.query.PurchsInEstQueryDLGInitializer getPurchsInEstQryDLGInitializer(){
 if(context.get("purchsInEstQryDLGInitializer")!=null)
 return (nc.ui.pu.est.query.PurchsInEstQueryDLGInitializer)context.get("purchsInEstQryDLGInitializer");
  nc.ui.pu.est.query.PurchsInEstQueryDLGInitializer bean = new nc.ui.pu.est.query.PurchsInEstQueryDLGInitializer();
  context.put("purchsInEstQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurchaseInEstQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchaseInEstQueryAction)context.get("queryAction");
  nc.ui.pu.est.action.m45.PurchaseInEstQueryAction bean = new nc.ui.pu.est.action.m45.PurchaseInEstQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setQryCondDLGInitializer(getPurchsInEstQryDLGInitializer());
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

public nc.ui.pu.est.action.m45.PurchaseInEstAction getEstimateAction(){
 if(context.get("estimateAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchaseInEstAction)context.get("estimateAction");
  nc.ui.pu.est.action.m45.PurchaseInEstAction bean = new nc.ui.pu.est.action.m45.PurchaseInEstAction();
  context.put("estimateAction",bean);
  bean.setEditor(getListView());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.EstDistFeeAction getFeedistAction(){
 if(context.get("feedistAction")!=null)
 return (nc.ui.pu.est.action.EstDistFeeAction)context.get("feedistAction");
  nc.ui.pu.est.action.EstDistFeeAction bean = new nc.ui.pu.est.action.EstDistFeeAction();
  context.put("feedistAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getListView());
  bean.setFeeDivider(getEstFeeUIDivider_14aca2e());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.est.rule.feediv.EstFeeUIDivider getEstFeeUIDivider_14aca2e(){
 if(context.get("nc.ui.pu.est.rule.feediv.EstFeeUIDivider#14aca2e")!=null)
 return (nc.ui.pu.est.rule.feediv.EstFeeUIDivider)context.get("nc.ui.pu.est.rule.feediv.EstFeeUIDivider#14aca2e");
  nc.ui.pu.est.rule.feediv.EstFeeUIDivider bean = new nc.ui.pu.est.rule.feediv.EstFeeUIDivider();
  context.put("nc.ui.pu.est.rule.feediv.EstFeeUIDivider#14aca2e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.action.m45.PurchaseInEstHQHPAction getHqhpAction(){
 if(context.get("hqhpAction")!=null)
 return (nc.ui.pu.est.action.m45.PurchaseInEstHQHPAction)context.get("hqhpAction");
  nc.ui.pu.est.action.m45.PurchaseInEstHQHPAction bean = new nc.ui.pu.est.action.m45.PurchaseInEstHQHPAction();
  context.put("hqhpAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getListView());
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
  bean.setBtnName(getI18nFB_11fa836());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160201");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_11fa836(){
 if(context.get("nc.ui.uif2.I18nFB#11fa836")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#11fa836");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#11fa836",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0236");
  bean.setDefaultValue("‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#11fa836",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.est.action.m45.PurInEstListPrintAction getListPrintAction(){
 if(context.get("listPrintAction")!=null)
 return (nc.ui.pu.est.action.m45.PurInEstListPrintAction)context.get("listPrintAction");
  nc.ui.pu.est.action.m45.PurInEstListPrintAction bean = new nc.ui.pu.est.action.m45.PurInEstListPrintAction();
  context.put("listPrintAction",bean);
  bean.setPreview(false);
  bean.setBtnName(getI18nFB_15c8f8d());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("4004160201");
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_15c8f8d(){
 if(context.get("nc.ui.uif2.I18nFB#15c8f8d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#15c8f8d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#15c8f8d",bean);  bean.setResDir("4004060_0");
  bean.setResId("04004060-0235");
  bean.setDefaultValue("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#15c8f8d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setActions(getManagedList0());
  bean.setCode("printMenuAction");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getListPrintAction());  list.add(getListPreviewAction());  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.head.HeadRowChangeHandler getList_head_rowchangehandler(){
 if(context.get("list_head_rowchangehandler")!=null)
 return (nc.ui.pu.est.editor.head.HeadRowChangeHandler)context.get("list_head_rowchangehandler");
  nc.ui.pu.est.editor.head.HeadRowChangeHandler bean = new nc.ui.pu.est.editor.head.HeadRowChangeHandler();
  context.put("list_head_rowchangehandler",bean);
  bean.setEditor(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.head.before.m45.HeadBeforeEditEventHandler getList_head_before_edithandler(){
 if(context.get("list_head_before_edithandler")!=null)
 return (nc.ui.pu.est.editor.head.before.m45.HeadBeforeEditEventHandler)context.get("list_head_before_edithandler");
  nc.ui.pu.est.editor.head.before.m45.HeadBeforeEditEventHandler bean = new nc.ui.pu.est.editor.head.before.m45.HeadBeforeEditEventHandler();
  context.put("list_head_before_edithandler",bean);
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

public nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler getList_head_row_selecthandler(){
 if(context.get("list_head_row_selecthandler")!=null)
 return (nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler)context.get("list_head_row_selecthandler");
  nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler bean = new nc.ui.pu.est.editor.head.after.m45.RowSelectStateChangeHandler();
  context.put("list_head_row_selecthandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.body.before.m45.CardBodyBeforeEditEventHandler getCard_before_body_edithandler(){
 if(context.get("card_before_body_edithandler")!=null)
 return (nc.ui.pu.est.editor.body.before.m45.CardBodyBeforeEditEventHandler)context.get("card_before_body_edithandler");
  nc.ui.pu.est.editor.body.before.m45.CardBodyBeforeEditEventHandler bean = new nc.ui.pu.est.editor.body.before.m45.CardBodyBeforeEditEventHandler();
  context.put("card_before_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.body.after.m45.CardBodyAfterEditEventHandler getCard_after_body_edithandler(){
 if(context.get("card_after_body_edithandler")!=null)
 return (nc.ui.pu.est.editor.body.after.m45.CardBodyAfterEditEventHandler)context.get("card_after_body_edithandler");
  nc.ui.pu.est.editor.body.after.m45.CardBodyAfterEditEventHandler bean = new nc.ui.pu.est.editor.body.after.m45.CardBodyAfterEditEventHandler();
  context.put("card_after_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.relacalc.GoodsEstRelationCal getGoodsRelationCalc(){
 if(context.get("goodsRelationCalc")!=null)
 return (nc.ui.pu.est.editor.relacalc.GoodsEstRelationCal)context.get("goodsRelationCalc");
  nc.ui.pu.est.editor.relacalc.GoodsEstRelationCal bean = new nc.ui.pu.est.editor.relacalc.GoodsEstRelationCal();
  context.put("goodsRelationCalc",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.est.editor.relacalc.FeeRelationCalculate getFeeRelationCalc(){
 if(context.get("feeRelationCalc")!=null)
 return (nc.ui.pu.est.editor.relacalc.FeeRelationCalculate)context.get("feeRelationCalc");
  nc.ui.pu.est.editor.relacalc.FeeRelationCalculate bean = new nc.ui.pu.est.editor.relacalc.FeeRelationCalculate();
  context.put("feeRelationCalc",bean);
  bean.setEditor(getListView());
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent",getManagedList1());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent",getManagedList2());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList3());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList4());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList5());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent",getManagedList6());  return map;}

private List getManagedList1(){  List list = new ArrayList();  list.add(getList_head_before_edithandler());  return list;}

private List getManagedList2(){  List list = new ArrayList();  list.add(getList_head_after_edithandler());  list.add(getGoodsRelationCalc());  return list;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getCard_before_body_edithandler());  return list;}

private List getManagedList4(){  List list = new ArrayList();  list.add(getCard_after_body_edithandler());  list.add(getFeeRelationCalc());  return list;}

private List getManagedList5(){  List list = new ArrayList();  list.add(getList_head_rowchangehandler());  return list;}

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

private List getManagedList8(){  List list = new ArrayList();  list.add(getUserdefQueryParam_9a4180());  list.add(getUserdefQueryParam_1424b38());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_9a4180(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#9a4180")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#9a4180");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#9a4180",bean);
  bean.setRulecode("45_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1424b38(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1424b38")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1424b38");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1424b38",bean);
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

private List getManagedList9(){  List list = new ArrayList();  list.add(getQueryParam_1588298());  list.add(getQueryParam_1e5ff30());  list.add(getQueryParam_d4c1e7());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1588298(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1588298")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1588298");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1588298",bean);
  bean.setRulecode("45_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1e5ff30(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1e5ff30")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1e5ff30");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1e5ff30",bean);
  bean.setRulecode("45_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_d4c1e7(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#d4c1e7")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#d4c1e7");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#d4c1e7",bean);
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

public java.util.ArrayList getPasteClearItem(){
 if(context.get("pasteClearItem")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList10());  context.put("pasteClearItem",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add("pk_stockps_fee");  list.add("pk_feematerial");  list.add("pk_srcfeematerial");  list.add("pk_firstsettle");  list.add("pk_firstsettle_b");  list.add("ts");  return list;}

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
  bean.setBodyActions(getManagedList11());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getEstBodyAddLineAction_18e450b());  list.add(getEstBodyInsertLineAction_156df34());  list.add(getEstBodyDelLineAction_5800d9());  return list;}

private nc.ui.pu.est.action.body.EstBodyAddLineAction getEstBodyAddLineAction_18e450b(){
 if(context.get("nc.ui.pu.est.action.body.EstBodyAddLineAction#18e450b")!=null)
 return (nc.ui.pu.est.action.body.EstBodyAddLineAction)context.get("nc.ui.pu.est.action.body.EstBodyAddLineAction#18e450b");
  nc.ui.pu.est.action.body.EstBodyAddLineAction bean = new nc.ui.pu.est.action.body.EstBodyAddLineAction();
  context.put("nc.ui.pu.est.action.body.EstBodyAddLineAction#18e450b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.est.action.body.EstBodyInsertLineAction getEstBodyInsertLineAction_156df34(){
 if(context.get("nc.ui.pu.est.action.body.EstBodyInsertLineAction#156df34")!=null)
 return (nc.ui.pu.est.action.body.EstBodyInsertLineAction)context.get("nc.ui.pu.est.action.body.EstBodyInsertLineAction#156df34");
  nc.ui.pu.est.action.body.EstBodyInsertLineAction bean = new nc.ui.pu.est.action.body.EstBodyInsertLineAction();
  context.put("nc.ui.pu.est.action.body.EstBodyInsertLineAction#156df34",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.est.action.body.EstBodyDelLineAction getEstBodyDelLineAction_5800d9(){
 if(context.get("nc.ui.pu.est.action.body.EstBodyDelLineAction#5800d9")!=null)
 return (nc.ui.pu.est.action.body.EstBodyDelLineAction)context.get("nc.ui.pu.est.action.body.EstBodyDelLineAction#5800d9");
  nc.ui.pu.est.action.body.EstBodyDelLineAction bean = new nc.ui.pu.est.action.body.EstBodyDelLineAction();
  context.put("nc.ui.pu.est.action.body.EstBodyDelLineAction#5800d9",bean);
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

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getHSNode_12019c3());
  bean.setActions(getManagedList12());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_12019c3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#12019c3")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#12019c3");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#12019c3",bean);
  bean.setLeft(getCNode_19fadea());
  bean.setRight(getCNode_52f820());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_19fadea(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#19fadea")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#19fadea");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#19fadea",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_52f820(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#52f820")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#52f820");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#52f820",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getEstimateAction());  list.add(getFeedistAction());  list.add(getHqhpAction());  list.add(getSeparatorAction());  list.add(getLinkQryAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

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

package nc.ui.pu.m21.config.orderstatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class orderoutput_config extends AbstractJavaBeanDefinition {
  private Map<String, Object> context = new HashMap();

  public nc.ui.pu.m21.action.status.output.OutputAction getOutputAction() {
    if (context.get("outputAction") != null)
      return (nc.ui.pu.m21.action.status.output.OutputAction) context
          .get("outputAction");
    nc.ui.pu.m21.action.status.output.OutputAction bean =
        new nc.ui.pu.m21.action.status.output.OutputAction();
    context.put("outputAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    bean.setService(getManageModelService());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.output.UnOutputAction getUnOutputAction() {
    if (context.get("unOutputAction") != null)
      return (nc.ui.pu.m21.action.status.output.UnOutputAction) context
          .get("unOutputAction");
    nc.ui.pu.m21.action.status.output.UnOutputAction bean =
        new nc.ui.pu.m21.action.status.output.UnOutputAction();
    context.put("unOutputAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    bean.setService(getManageModelService());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OutputDLGInitializer getQryDLGInitializer() {
    if (context.get("qryDLGInitializer") != null)
      return (nc.ui.pu.m21.view.OutputDLGInitializer) context
          .get("qryDLGInitializer");
    nc.ui.pu.m21.view.OutputDLGInitializer bean =
        new nc.ui.pu.m21.view.OutputDLGInitializer();
    context.put("qryDLGInitializer", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
    if (context.get("queryAction") != null)
      return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
          .get("queryAction");
    nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean =
        new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
    context.put("queryAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    bean.setQryCondDLGInitializer(getQryDLGInitializer());
    bean.setHasQueryArea(false);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
    if (context.get("refreshAction") != null)
      return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
          .get("refreshAction");
    nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean =
        new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
    context.put("refreshAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
    if (context.get("separatorAction") != null)
      return (nc.funcnode.ui.action.SeparatorAction) context
          .get("separatorAction");
    nc.funcnode.ui.action.SeparatorAction bean =
        new nc.funcnode.ui.action.SeparatorAction();
    context.put("separatorAction", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.OutputRowChangedEventHandler getListHeadRowChangedEvent() {
    if (context.get("listHeadRowChangedEvent") != null)
      return (nc.ui.pu.m21.editor.list.OutputRowChangedEventHandler) context
          .get("listHeadRowChangedEvent");
    nc.ui.pu.m21.editor.list.OutputRowChangedEventHandler bean =
        new nc.ui.pu.m21.editor.list.OutputRowChangedEventHandler();
    context.put("listHeadRowChangedEvent", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.OutputSelectEventHandler getOutputSelectHandler() {
    if (context.get("outputSelectHandler") != null)
      return (nc.ui.pu.m21.editor.list.OutputSelectEventHandler) context
          .get("outputSelectHandler");
    nc.ui.pu.m21.editor.list.OutputSelectEventHandler bean =
        new nc.ui.pu.m21.editor.list.OutputSelectEventHandler();
    context.put("outputSelectHandler", bean);
    bean.setModel(getManageAppModel());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
    if (context.get("appEventHandlerMediator") != null)
      return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
          .get("appEventHandlerMediator");
    nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean =
        new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
    context.put("appEventHandlerMediator", bean);
    bean.setModel(getManageAppModel());
    bean.setHandlerMap(getManagedMap0());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private Map getManagedMap0() {
    Map map = new HashMap();
    map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",
        getManagedList0());
    map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent",
        getManagedList1());
    return map;
  }

  private List getManagedList0() {
    List list = new ArrayList();
    list.add(getListHeadRowChangedEvent());
    return list;
  }

  private List getManagedList1() {
    List list = new ArrayList();
    list.add(getOutputSelectHandler());
    return list;
  }

  public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
    if (context.get("InitDataListener") != null)
      return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) context
          .get("InitDataListener");
    nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean =
        new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
    context.put("InitDataListener", bean);
    bean.setContext(getContext());
    bean.setModel(getManageAppModel());
    bean.setQueryAction(getQueryAction());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.pub.editor.ClientContext getContext() {
    if (context.get("context") != null)
      return (nc.ui.pu.pub.editor.ClientContext) context.get("context");
    nc.ui.pu.pub.editor.ClientContext bean =
        new nc.ui.pu.pub.editor.ClientContext();
    context.put("context", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.service.onway.OrderOutputService getManageModelService() {
    if (context.get("manageModelService") != null)
      return (nc.ui.pu.m21.service.onway.OrderOutputService) context
          .get("manageModelService");
    nc.ui.pu.m21.service.onway.OrderOutputService bean =
        new nc.ui.pu.m21.service.onway.OrderOutputService();
    context.put("manageModelService", bean);
    bean.setOutputQuery(nc.itf.pu.m21.IOutputQuery.class);
    bean.setStatusMaintain(nc.itf.pu.m21.IStatusMaintain.class);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
    if (context.get("boadatorfactory") != null)
      return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) context
          .get("boadatorfactory");
    nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean =
        new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
    context.put("boadatorfactory", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.SelectBillManageModel getManageAppModel() {
    if (context.get("manageAppModel") != null)
      return (nc.ui.pu.m21.editor.list.SelectBillManageModel) context
          .get("manageAppModel");
    nc.ui.pu.m21.editor.list.SelectBillManageModel bean =
        new nc.ui.pu.m21.editor.list.SelectBillManageModel();
    context.put("manageAppModel", bean);
    bean.setService(getManageModelService());
    bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
    bean.setContext(getContext());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
    if (context.get("modelDataManager") != null)
      return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context
          .get("modelDataManager");
    nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean =
        new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
    context.put("modelDataManager", bean);
    bean.setModel(getManageAppModel());
    bean.setService(getManageModelService());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getBillTemplateMender() {
    if (context.get("billTemplateMender") != null)
      return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender) context
          .get("billTemplateMender");
    nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean =
        new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(
            getContext());
    context.put("billTemplateMender", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
    if (context.get("templateContainer") != null)
      return (nc.ui.pubapp.uif2app.view.TemplateContainer) context
          .get("templateContainer");
    nc.ui.pubapp.uif2app.view.TemplateContainer bean =
        new nc.ui.pubapp.uif2app.view.TemplateContainer();
    context.put("templateContainer", bean);
    bean.setContext(getContext());
    bean.setBillTemplateMender(getBillTemplateMender());
    bean.load();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OrderOutputShowView getListView() {
    if (context.get("listView") != null)
      return (nc.ui.pu.m21.view.OrderOutputShowView) context.get("listView");
    nc.ui.pu.m21.view.OrderOutputShowView bean =
        new nc.ui.pu.m21.view.OrderOutputShowView();
    context.put("listView", bean);
    bean.setModel(getManageAppModel());
    bean.setMultiSelectionMode(0);
    bean.setUserdefitemListPreparator(getUserdefitemlistPreparator());
    bean.setTemplateContainer(getTemplateContainer());
    bean.initUI();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.editor.UserdefitemBillListDataPreparator getUserdefitemlistPreparator() {
    if (context.get("userdefitemlistPreparator") != null)
      return (nc.ui.uif2.editor.UserdefitemBillListDataPreparator) context
          .get("userdefitemlistPreparator");
    nc.ui.uif2.editor.UserdefitemBillListDataPreparator bean =
        new nc.ui.uif2.editor.UserdefitemBillListDataPreparator(getContext());
    context.put("userdefitemlistPreparator", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getCcontractidMediator() {
    if (context.get("ccontractidMediator") != null)
      return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
          .get("ccontractidMediator");
    nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean =
        new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
    context.put("ccontractidMediator", bean);
    bean.setModel(getManageAppModel());
    bean.setSrcBillIdField("ccontractid");
    bean.setSrcBillNOField("vcontractcode");
    bean.setSrcBillType("Z2");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getCecbillidMediator() {
    if (context.get("cecbillidMediator") != null)
      return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
          .get("cecbillidMediator");
    nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean =
        new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
    context.put("cecbillidMediator", bean);
    bean.setModel(getManageAppModel());
    bean.setSrcBillIdField("cecbillid");
    bean.setSrcBillNOField("vecbillcode");
    bean.setSrcBillType("EC56");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVsourcecodeMediator() {
    if (context.get("vsourcecodeMediator") != null)
      return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
          .get("vsourcecodeMediator");
    nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean =
        new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
    context.put("vsourcecodeMediator", bean);
    bean.setModel(getManageAppModel());
    bean.setSrcBillIdField("csourceid");
    bean.setSrcBillNOField("vsourcecode");
    bean.setSrcBillTypeField("csourcetypecode");
    bean.setSrcBillTypeFieldPos(1);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVfirstcodeMediator() {
    if (context.get("vfirstcodeMediator") != null)
      return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
          .get("vfirstcodeMediator");
    nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean =
        new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
    context.put("vfirstcodeMediator", bean);
    bean.setModel(getManageAppModel());
    bean.setSrcBillIdField("cfirstid");
    bean.setSrcBillNOField("vfirstcode");
    bean.setSrcBillTypeField("cfirsttypecode");
    bean.setSrcBillTypeFieldPos(1);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.TangramContainer getContainer() {
    if (context.get("container") != null)
      return (nc.ui.uif2.TangramContainer) context.get("container");
    nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
    context.put("container", bean);
    bean.setModel(getManageAppModel());
    bean.setTangramLayoutRoot(getCNode_12c07bb());
    bean.setActions(getManagedList2());
    bean.initUI();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_12c07bb() {
    if (context.get("nc.ui.uif2.tangramlayout.node.CNode#12c07bb") != null)
      return (nc.ui.uif2.tangramlayout.node.CNode) context
          .get("nc.ui.uif2.tangramlayout.node.CNode#12c07bb");
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    context.put("nc.ui.uif2.tangramlayout.node.CNode#12c07bb", bean);
    bean.setComponent(getListView());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList2() {
    List list = new ArrayList();
    list.add(getSeparatorAction());
    list.add(getQueryAction());
    list.add(getRefreshAction());
    list.add(getSeparatorAction());
    list.add(getOutputAction());
    list.add(getUnOutputAction());
    return list;
  }

}

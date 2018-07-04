package nc.ui.pu.m21.config.orderstatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class orderload_config extends AbstractJavaBeanDefinition {
  private Map<String, Object> context = new HashMap();

  public nc.ui.pu.m21.action.status.load.LoadAction getLoadAction() {
    if (context.get("loadAction") != null)
      return (nc.ui.pu.m21.action.status.load.LoadAction) context
          .get("loadAction");
    nc.ui.pu.m21.action.status.load.LoadAction bean =
        new nc.ui.pu.m21.action.status.load.LoadAction();
    context.put("loadAction", bean);
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
    bean.setService(getManageModelService());
    bean.setDataManager(getModelDataManager());
    bean.setValidateService(getValidateSerice());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }
  
  public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
    if (this.context.get("billBodySortMediator") != null) {
      return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) this.context
          .get("billBodySortMediator");
    }
    nc.ui.pubapp.uif2app.model.BillBodySortMediator bean =
        new nc.ui.pubapp.uif2app.model.BillBodySortMediator(
            this.getManageAppModel(), this.getBillFormEditor(), null);
    this.context.put("billBodySortMediator", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }


  public nc.ui.pu.m21.action.status.load.UnLoadAction getUnLoadAction() {
    if (context.get("unLoadAction") != null)
      return (nc.ui.pu.m21.action.status.load.UnLoadAction) context
          .get("unLoadAction");
    nc.ui.pu.m21.action.status.load.UnLoadAction bean =
        new nc.ui.pu.m21.action.status.load.UnLoadAction();
    context.put("unLoadAction", bean);
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
    bean.setService(getManageModelService());
    bean.setDataManager(getModelDataManager());
    bean.setValidateService(getValidateSerice());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer getQryDLGInitializer() {
    if (context.get("qryDLGInitializer") != null)
      return (nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer) context
          .get("qryDLGInitializer");
    nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer bean =
        new nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer();
    context.put("qryDLGInitializer", bean);
    bean.setFieldcode("bisload");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.load.LoadQueryAction getQueryAction() {
    if (context.get("queryAction") != null)
      return (nc.ui.pu.m21.action.status.load.LoadQueryAction) context
          .get("queryAction");
    nc.ui.pu.m21.action.status.load.LoadQueryAction bean =
        new nc.ui.pu.m21.action.status.load.LoadQueryAction();
    context.put("queryAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
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

  public nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor getPrintProcessor() {
    if (context.get("printProcessor") != null)
      return (nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor) context
          .get("printProcessor");
    nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor bean =
        new nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor();
    context.put("printProcessor", bean);
    bean.setModel(getManageAppModel());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.StatusPrintAction getPreviewAction() {
    if (context.get("previewAction") != null)
      return (nc.ui.pu.m21.action.status.StatusPrintAction) context
          .get("previewAction");
    nc.ui.pu.m21.action.status.StatusPrintAction bean =
        new nc.ui.pu.m21.action.status.StatusPrintAction();
    context.put("previewAction", bean);
    bean.setPreview(true);
    bean.setModel(getManageAppModel());
    bean.setBeforePrintDataProcess(getPrintProcessor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.StatusPrintAction getPrintAction() {
    if (context.get("printAction") != null)
      return (nc.ui.pu.m21.action.status.StatusPrintAction) context
          .get("printAction");
    nc.ui.pu.m21.action.status.StatusPrintAction bean =
        new nc.ui.pu.m21.action.status.StatusPrintAction();
    context.put("printAction", bean);
    bean.setPreview(false);
    bean.setModel(getManageAppModel());
    bean.setBeforePrintDataProcess(getPrintProcessor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.funcnode.ui.action.GroupAction getBrowseActionGroup() {
    if (context.get("browseActionGroup") != null)
      return (nc.funcnode.ui.action.GroupAction) context
          .get("browseActionGroup");
    nc.funcnode.ui.action.GroupAction bean =
        new nc.funcnode.ui.action.GroupAction();
    context.put("browseActionGroup", bean);
    bean.setCode("printMenuAction");
    bean.setActions(getManagedList0());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList0() {
    List list = new ArrayList();
    list.add(getPrintAction());
    list.add(getPreviewAction());
    return list;
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

  public nc.ui.pu.m21.editor.card.beforeedit.LoadCardBodyBeforeEditEventHandler getCardBodyBeforeEdit() {
    if (context.get("cardBodyBeforeEdit") != null)
      return (nc.ui.pu.m21.editor.card.beforeedit.LoadCardBodyBeforeEditEventHandler) context
          .get("cardBodyBeforeEdit");
    nc.ui.pu.m21.editor.card.beforeedit.LoadCardBodyBeforeEditEventHandler bean =
        new nc.ui.pu.m21.editor.card.beforeedit.LoadCardBodyBeforeEditEventHandler();
    context.put("cardBodyBeforeEdit", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.OnWayEventHandler getOnWayEventHandler() {
    if (context.get("onWayEventHandler") != null)
      return (nc.ui.pu.m21.editor.list.OnWayEventHandler) context
          .get("onWayEventHandler");
    nc.ui.pu.m21.editor.list.OnWayEventHandler bean =
        new nc.ui.pu.m21.editor.list.OnWayEventHandler();
    context.put("onWayEventHandler", bean);
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
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
    map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
        getManagedList1());
    map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowStateChangeEvent",
        getManagedList2());
    return map;
  }

  private List getManagedList1() {
    List list = new ArrayList();
    list.add(getCardBodyBeforeEdit());
    return list;
  }

  private List getManagedList2() {
    List list = new ArrayList();
    list.add(getOnWayEventHandler());
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

  public nc.ui.pu.m21.service.onway.OrderLoadService getManageModelService() {
    if (context.get("manageModelService") != null)
      return (nc.ui.pu.m21.service.onway.OrderLoadService) context
          .get("manageModelService");
    nc.ui.pu.m21.service.onway.OrderLoadService bean =
        new nc.ui.pu.m21.service.onway.OrderLoadService();
    context.put("manageModelService", bean);
    bean.setOnwayQuery(nc.itf.pu.m21.IOnwayQuery.class);
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

  public nc.ui.pu.m21.service.onway.OrderLoadValidationService getValidateSerice() {
    if (context.get("validateSerice") != null)
      return (nc.ui.pu.m21.service.onway.OrderLoadValidationService) context
          .get("validateSerice");
    nc.ui.pu.m21.service.onway.OrderLoadValidationService bean =
        new nc.ui.pu.m21.service.onway.OrderLoadValidationService();
    context.put("validateSerice", bean);
    bean.setEditor(getBillFormEditor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OnwayStatusBillForm getBillFormEditor() {
    if (context.get("billFormEditor") != null)
      return (nc.ui.pu.m21.view.OnwayStatusBillForm) context
          .get("billFormEditor");
    nc.ui.pu.m21.view.OnwayStatusBillForm bean =
        new nc.ui.pu.m21.view.OnwayStatusBillForm();
    context.put("billFormEditor", bean);
    bean.setShowOrgPanel(false);
    bean.setModel(getManageAppModel());
    bean.setTemplateContainer(getTemplateContainer());
    bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
    bean.setBlankChildrenFilter(getBlankChildrenFilter());
    bean.initUI();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OnwayBlankChilrenFilter getBlankChildrenFilter() {
    if (context.get("blankChildrenFilter") != null)
      return (nc.ui.pu.m21.view.OnwayBlankChilrenFilter) context
          .get("blankChildrenFilter");
    nc.ui.pu.m21.view.OnwayBlankChilrenFilter bean =
        new nc.ui.pu.m21.view.OnwayBlankChilrenFilter();
    context.put("blankChildrenFilter", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl() {
    if (context.get("cardInfoPnl") != null)
      return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context
          .get("cardInfoPnl");
    nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean =
        new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
    context.put("cardInfoPnl", bean);
    bean.setModel(getManageAppModel());
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
    bean.setTangramLayoutRoot(getTBNode_5fc01e());
    bean.setActions(getManagedList4());
    bean.initUI();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_5fc01e() {
    if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#5fc01e") != null)
      return (nc.ui.uif2.tangramlayout.node.TBNode) context
          .get("nc.ui.uif2.tangramlayout.node.TBNode#5fc01e");
    nc.ui.uif2.tangramlayout.node.TBNode bean =
        new nc.ui.uif2.tangramlayout.node.TBNode();
    context.put("nc.ui.uif2.tangramlayout.node.TBNode#5fc01e", bean);
    bean.setShowMode("CardLayout");
    bean.setTabs(getManagedList3());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList3() {
    List list = new ArrayList();
    list.add(getVSNode_95ba5());
    return list;
  }

  private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_95ba5() {
    if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#95ba5") != null)
      return (nc.ui.uif2.tangramlayout.node.VSNode) context
          .get("nc.ui.uif2.tangramlayout.node.VSNode#95ba5");
    nc.ui.uif2.tangramlayout.node.VSNode bean =
        new nc.ui.uif2.tangramlayout.node.VSNode();
    context.put("nc.ui.uif2.tangramlayout.node.VSNode#95ba5", bean);
    bean.setUp(getCNode_174adc0());
    bean.setDown(getCNode_1f37ae5());
    bean.setDividerLocation(30f);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_174adc0() {
    if (context.get("nc.ui.uif2.tangramlayout.node.CNode#174adc0") != null)
      return (nc.ui.uif2.tangramlayout.node.CNode) context
          .get("nc.ui.uif2.tangramlayout.node.CNode#174adc0");
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    context.put("nc.ui.uif2.tangramlayout.node.CNode#174adc0", bean);
    bean.setComponent(getCardInfoPnl());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_1f37ae5() {
    if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1f37ae5") != null)
      return (nc.ui.uif2.tangramlayout.node.CNode) context
          .get("nc.ui.uif2.tangramlayout.node.CNode#1f37ae5");
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    context.put("nc.ui.uif2.tangramlayout.node.CNode#1f37ae5", bean);
    bean.setName(getI18nFB_13b1d19());
    bean.setComponent(getBillFormEditor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private java.lang.String getI18nFB_13b1d19() {
    if (context.get("nc.ui.uif2.I18nFB#13b1d19") != null)
      return (java.lang.String) context.get("nc.ui.uif2.I18nFB#13b1d19");
    nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#13b1d19", bean);
    bean.setResDir("common");
    bean.setResId("UC001-0000106");
    bean.setDefaultValue("¿¨Æ¬");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    try {
      Object product = bean.getObject();
      context.put("nc.ui.uif2.I18nFB#13b1d19", product);
      return (java.lang.String) product;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List getManagedList4() {
    List list = new ArrayList();
    list.add(getSeparatorAction());
    list.add(getQueryAction());
    list.add(getRefreshAction());
    list.add(getSeparatorAction());
    list.add(getLoadAction());
    list.add(getUnLoadAction());
    list.add(getSeparatorAction());
    list.add(getBrowseActionGroup());
    return list;
  }

  public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() {
    if (context.get("userdefAndMarAsstCardPreparator") != null)
      return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
          .get("userdefAndMarAsstCardPreparator");
    nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean =
        new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
    context.put("userdefAndMarAsstCardPreparator", bean);
    bean.setBillDataPrepares(getManagedList5());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList5() {
    List list = new ArrayList();
    list.add(getUserdefitemPreparator());
    list.add(getMarAsstPreparator());
    return list;
  }

  public nc.ui.pu.pub.view.PUMarAsstPreparator getMarAsstPreparator() {
    if (context.get("marAsstPreparator") != null)
      return (nc.ui.pu.pub.view.PUMarAsstPreparator) context
          .get("marAsstPreparator");
    nc.ui.pu.pub.view.PUMarAsstPreparator bean =
        new nc.ui.pu.pub.view.PUMarAsstPreparator();
    context.put("marAsstPreparator", bean);
    bean.setModel(getManageAppModel());
    bean.setContainer(getUserdefitemContainer());
    bean.setPrefix("vfree");
    bean.setMaterialField("pk_material");
    bean.setProjectField("cprojectid");
    bean.setProductorField("cproductorid");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
    if (context.get("userdefitemContainer") != null)
      return (nc.ui.uif2.userdefitem.UserDefItemContainer) context
          .get("userdefitemContainer");
    nc.ui.uif2.userdefitem.UserDefItemContainer bean =
        new nc.ui.uif2.userdefitem.UserDefItemContainer();
    context.put("userdefitemContainer", bean);
    bean.setContext(getContext());
    bean.setParams(getManagedList6());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList6() {
    List list = new ArrayList();
    list.add(getQueryParam_3e2406());
    list.add(getQueryParam_1af45a3());
    list.add(getQueryParam_4fb41());
    return list;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_3e2406() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#3e2406") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#3e2406");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#3e2406", bean);
    bean.setMdfullname("pu.po_order");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1af45a3() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#1af45a3") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#1af45a3");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#1af45a3", bean);
    bean.setMdfullname("pu.po_order_b");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_4fb41() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#4fb41") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#4fb41");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#4fb41", bean);
    bean.setRulecode("materialassistant");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
    if (context.get("userdefitemPreparator") != null)
      return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context
          .get("userdefitemPreparator");
    nc.ui.uif2.editor.UserdefitemContainerPreparator bean =
        new nc.ui.uif2.editor.UserdefitemContainerPreparator();
    context.put("userdefitemPreparator", bean);
    bean.setContainer(getUserdefitemContainer());
    bean.setParams(getManagedList7());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList7() {
    List list = new ArrayList();
    list.add(getUserdefQueryParam_10b74b8());
    list.add(getUserdefQueryParam_11c14fc());
    return list;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_10b74b8() {
    if (context.get("nc.ui.uif2.editor.UserdefQueryParam#10b74b8") != null)
      return (nc.ui.uif2.editor.UserdefQueryParam) context
          .get("nc.ui.uif2.editor.UserdefQueryParam#10b74b8");
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    context.put("nc.ui.uif2.editor.UserdefQueryParam#10b74b8", bean);
    bean.setMdfullname("pu.po_order");
    bean.setPos(0);
    bean.setPrefix("vdef");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_11c14fc() {
    if (context.get("nc.ui.uif2.editor.UserdefQueryParam#11c14fc") != null)
      return (nc.ui.uif2.editor.UserdefQueryParam) context
          .get("nc.ui.uif2.editor.UserdefQueryParam#11c14fc");
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    context.put("nc.ui.uif2.editor.UserdefQueryParam#11c14fc", bean);
    bean.setMdfullname("pu.po_order_b");
    bean.setPos(1);
    bean.setPrefix("vbdef");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

}

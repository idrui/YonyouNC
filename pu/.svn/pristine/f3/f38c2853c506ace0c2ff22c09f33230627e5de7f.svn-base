package nc.ui.pu.m21.config.orderstatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class ordercustom_config extends AbstractJavaBeanDefinition {
  private Map<String, Object> context = new HashMap();

  public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
    if (this.context.get("appEventHandlerMediator") != null) {
      return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) this.context
          .get("appEventHandlerMediator");
    }
    nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean =
        new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
    this.context.put("appEventHandlerMediator", bean);
    bean.setModel(this.getManageAppModel());
    bean.setHandlerMap(this.getManagedMap0());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
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

  public nc.ui.pu.m21.view.OnwayStatusBillForm getBillFormEditor() {
    if (this.context.get("billFormEditor") != null) {
      return (nc.ui.pu.m21.view.OnwayStatusBillForm) this.context
          .get("billFormEditor");
    }
    nc.ui.pu.m21.view.OnwayStatusBillForm bean =
        new nc.ui.pu.m21.view.OnwayStatusBillForm();
    this.context.put("billFormEditor", bean);
    bean.setShowOrgPanel(false);
    bean.setModel(this.getManageAppModel());
    bean.setTemplateContainer(this.getTemplateContainer());
    bean.setUserdefitemPreparator(this.getUserdefAndMarAsstCardPreparator());
    bean.setBlankChildrenFilter(this.getBlankChildrenFilter());
    bean.initUI();
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getBillTemplateMender() {
    if (this.context.get("billTemplateMender") != null) {
      return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender) this.context
          .get("billTemplateMender");
    }
    nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean =
        new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(
            this.getContext());
    this.context.put("billTemplateMender", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OnwayBlankChilrenFilter getBlankChildrenFilter() {
    if (this.context.get("blankChildrenFilter") != null) {
      return (nc.ui.pu.m21.view.OnwayBlankChilrenFilter) this.context
          .get("blankChildrenFilter");
    }
    nc.ui.pu.m21.view.OnwayBlankChilrenFilter bean =
        new nc.ui.pu.m21.view.OnwayBlankChilrenFilter();
    this.context.put("blankChildrenFilter", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
    if (this.context.get("boadatorfactory") != null) {
      return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) this.context
          .get("boadatorfactory");
    }
    nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean =
        new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
    this.context.put("boadatorfactory", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.funcnode.ui.action.GroupAction getBrowseActionGroup() {
    if (this.context.get("browseActionGroup") != null) {
      return (nc.funcnode.ui.action.GroupAction) this.context
          .get("browseActionGroup");
    }
    nc.funcnode.ui.action.GroupAction bean =
        new nc.funcnode.ui.action.GroupAction();
    this.context.put("browseActionGroup", bean);
    bean.setCode("printMenuAction");
    bean.setActions(this.getManagedList0());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.card.beforeedit.CustomCardBodyBeforeEditEventHandler getCardBodyBeforeEdit() {
    if (this.context.get("cardBodyBeforeEdit") != null) {
      return (nc.ui.pu.m21.editor.card.beforeedit.CustomCardBodyBeforeEditEventHandler) this.context
          .get("cardBodyBeforeEdit");
    }
    nc.ui.pu.m21.editor.card.beforeedit.CustomCardBodyBeforeEditEventHandler bean =
        new nc.ui.pu.m21.editor.card.beforeedit.CustomCardBodyBeforeEditEventHandler();
    this.context.put("cardBodyBeforeEdit", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl() {
    if (this.context.get("cardInfoPnl") != null) {
      return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) this.context
          .get("cardInfoPnl");
    }
    nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean =
        new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
    this.context.put("cardInfoPnl", bean);
    bean.setModel(this.getManageAppModel());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.TangramContainer getContainer() {
    if (this.context.get("container") != null) {
      return (nc.ui.uif2.TangramContainer) this.context.get("container");
    }
    nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
    this.context.put("container", bean);
    bean.setModel(this.getManageAppModel());
    bean.setTangramLayoutRoot(this.getTBNode_1c47c9d());
    bean.setActions(this.getManagedList4());
    bean.initUI();
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.pub.editor.ClientContext getContext() {
    if (this.context.get("context") != null) {
      return (nc.ui.pu.pub.editor.ClientContext) this.context.get("context");
    }
    nc.ui.pu.pub.editor.ClientContext bean =
        new nc.ui.pu.pub.editor.ClientContext();
    this.context.put("context", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.custom.CustomAction getCustomAction() {
    if (this.context.get("customAction") != null) {
      return (nc.ui.pu.m21.action.status.custom.CustomAction) this.context
          .get("customAction");
    }
    nc.ui.pu.m21.action.status.custom.CustomAction bean =
        new nc.ui.pu.m21.action.status.custom.CustomAction();
    this.context.put("customAction", bean);
    bean.setModel(this.getManageAppModel());
    bean.setEditor(this.getBillFormEditor());
    bean.setService(this.getManageModelService());
    bean.setDataManager(this.getModelDataManager());
    bean.setValidateService(this.getValidateSerice());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
    if (this.context.get("InitDataListener") != null) {
      return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) this.context
          .get("InitDataListener");
    }
    nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean =
        new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
    this.context.put("InitDataListener", bean);
    bean.setContext(this.getContext());
    bean.setModel(this.getManageAppModel());
    bean.setQueryAction(this.getQueryAction());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.SelectBillManageModel getManageAppModel() {
    if (this.context.get("manageAppModel") != null) {
      return (nc.ui.pu.m21.editor.list.SelectBillManageModel) this.context
          .get("manageAppModel");
    }
    nc.ui.pu.m21.editor.list.SelectBillManageModel bean =
        new nc.ui.pu.m21.editor.list.SelectBillManageModel();
    this.context.put("manageAppModel", bean);
    bean.setService(this.getManageModelService());
    bean.setBusinessObjectAdapterFactory(this.getBoadatorfactory());
    bean.setContext(this.getContext());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.service.onway.OrderCustomService getManageModelService() {
    if (this.context.get("manageModelService") != null) {
      return (nc.ui.pu.m21.service.onway.OrderCustomService) this.context
          .get("manageModelService");
    }
    nc.ui.pu.m21.service.onway.OrderCustomService bean =
        new nc.ui.pu.m21.service.onway.OrderCustomService();
    this.context.put("manageModelService", bean);
    bean.setOnwayQuery(nc.itf.pu.m21.IOnwayQuery.class);
    bean.setStatusMaintain(nc.itf.pu.m21.IStatusMaintain.class);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.pub.view.PUMarAsstPreparator getMarAsstPreparator() {
    if (this.context.get("marAsstPreparator") != null) {
      return (nc.ui.pu.pub.view.PUMarAsstPreparator) this.context
          .get("marAsstPreparator");
    }
    nc.ui.pu.pub.view.PUMarAsstPreparator bean =
        new nc.ui.pu.pub.view.PUMarAsstPreparator();
    this.context.put("marAsstPreparator", bean);
    bean.setModel(this.getManageAppModel());
    bean.setContainer(this.getUserdefitemContainer());
    bean.setPrefix("vfree");
    bean.setMaterialField("pk_material");
    bean.setProjectField("cprojectid");
    bean.setProductorField("cproductorid");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
    if (this.context.get("modelDataManager") != null) {
      return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) this.context
          .get("modelDataManager");
    }
    nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean =
        new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
    this.context.put("modelDataManager", bean);
    bean.setModel(this.getManageAppModel());
    bean.setService(this.getManageModelService());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.OnWayEventHandler getOnWayEventHandler() {
    if (this.context.get("onWayEventHandler") != null) {
      return (nc.ui.pu.m21.editor.list.OnWayEventHandler) this.context
          .get("onWayEventHandler");
    }
    nc.ui.pu.m21.editor.list.OnWayEventHandler bean =
        new nc.ui.pu.m21.editor.list.OnWayEventHandler();
    this.context.put("onWayEventHandler", bean);
    bean.setModel(this.getManageAppModel());
    bean.setEditor(this.getBillFormEditor());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.StatusPrintAction getPreviewAction() {
    if (this.context.get("previewAction") != null) {
      return (nc.ui.pu.m21.action.status.StatusPrintAction) this.context
          .get("previewAction");
    }
    nc.ui.pu.m21.action.status.StatusPrintAction bean =
        new nc.ui.pu.m21.action.status.StatusPrintAction();
    this.context.put("previewAction", bean);
    bean.setPreview(true);
    bean.setModel(this.getManageAppModel());
    bean.setBeforePrintDataProcess(this.getPrintProcessor());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.StatusPrintAction getPrintAction() {
    if (this.context.get("printAction") != null) {
      return (nc.ui.pu.m21.action.status.StatusPrintAction) this.context
          .get("printAction");
    }
    nc.ui.pu.m21.action.status.StatusPrintAction bean =
        new nc.ui.pu.m21.action.status.StatusPrintAction();
    this.context.put("printAction", bean);
    bean.setPreview(false);
    bean.setModel(this.getManageAppModel());
    bean.setBeforePrintDataProcess(this.getPrintProcessor());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor getPrintProcessor() {
    if (this.context.get("printProcessor") != null) {
      return (nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor) this.context
          .get("printProcessor");
    }
    nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor bean =
        new nc.ui.pu.m21.action.processor.OrderOnWayPrintProcessor();
    this.context.put("printProcessor", bean);
    bean.setModel(this.getManageAppModel());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer getQryDLGInitializer() {
    if (this.context.get("qryDLGInitializer") != null) {
      return (nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer) this.context
          .get("qryDLGInitializer");
    }
    nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer bean =
        new nc.ui.pu.m21.view.OrderOnwayQueryDLGInitializer();
    this.context.put("qryDLGInitializer", bean);
    bean.setFieldcode("biscustom");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.custom.CustomQueryAction getQueryAction() {
    if (this.context.get("queryAction") != null) {
      return (nc.ui.pu.m21.action.status.custom.CustomQueryAction) this.context
          .get("queryAction");
    }
    nc.ui.pu.m21.action.status.custom.CustomQueryAction bean =
        new nc.ui.pu.m21.action.status.custom.CustomQueryAction();
    this.context.put("queryAction", bean);
    bean.setDataManager(this.getModelDataManager());
    bean.setModel(this.getManageAppModel());
    bean.setQryCondDLGInitializer(this.getQryDLGInitializer());
    bean.setEditor(this.getBillFormEditor());
    bean.setHasQueryArea(false);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
    if (this.context.get("refreshAction") != null) {
      return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) this.context
          .get("refreshAction");
    }
    nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean =
        new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
    this.context.put("refreshAction", bean);
    bean.setDataManager(this.getModelDataManager());
    bean.setModel(this.getManageAppModel());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
    if (this.context.get("separatorAction") != null) {
      return (nc.funcnode.ui.action.SeparatorAction) this.context
          .get("separatorAction");
    }
    nc.funcnode.ui.action.SeparatorAction bean =
        new nc.funcnode.ui.action.SeparatorAction();
    this.context.put("separatorAction", bean);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
    if (this.context.get("templateContainer") != null) {
      return (nc.ui.pubapp.uif2app.view.TemplateContainer) this.context
          .get("templateContainer");
    }
    nc.ui.pubapp.uif2app.view.TemplateContainer bean =
        new nc.ui.pubapp.uif2app.view.TemplateContainer();
    this.context.put("templateContainer", bean);
    bean.setContext(this.getContext());
    bean.setBillTemplateMender(this.getBillTemplateMender());
    bean.load();
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.custom.UnCustomAction getUncustomAction() {
    if (this.context.get("uncustomAction") != null) {
      return (nc.ui.pu.m21.action.status.custom.UnCustomAction) this.context
          .get("uncustomAction");
    }
    nc.ui.pu.m21.action.status.custom.UnCustomAction bean =
        new nc.ui.pu.m21.action.status.custom.UnCustomAction();
    this.context.put("uncustomAction", bean);
    bean.setModel(this.getManageAppModel());
    bean.setEditor(this.getBillFormEditor());
    bean.setService(this.getManageModelService());
    bean.setDataManager(this.getModelDataManager());
    bean.setValidateService(this.getValidateSerice());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() {
    if (this.context.get("userdefAndMarAsstCardPreparator") != null) {
      return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) this.context
          .get("userdefAndMarAsstCardPreparator");
    }
    nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean =
        new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
    this.context.put("userdefAndMarAsstCardPreparator", bean);
    bean.setBillDataPrepares(this.getManagedList5());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
    if (this.context.get("userdefitemContainer") != null) {
      return (nc.ui.uif2.userdefitem.UserDefItemContainer) this.context
          .get("userdefitemContainer");
    }
    nc.ui.uif2.userdefitem.UserDefItemContainer bean =
        new nc.ui.uif2.userdefitem.UserDefItemContainer();
    this.context.put("userdefitemContainer", bean);
    bean.setContext(this.getContext());
    bean.setParams(this.getManagedList6());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
    if (this.context.get("userdefitemPreparator") != null) {
      return (nc.ui.uif2.editor.UserdefitemContainerPreparator) this.context
          .get("userdefitemPreparator");
    }
    nc.ui.uif2.editor.UserdefitemContainerPreparator bean =
        new nc.ui.uif2.editor.UserdefitemContainerPreparator();
    this.context.put("userdefitemPreparator", bean);
    bean.setContainer(this.getUserdefitemContainer());
    bean.setParams(this.getManagedList7());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.service.onway.OrderCustomValidationService getValidateSerice() {
    if (this.context.get("validateSerice") != null) {
      return (nc.ui.pu.m21.service.onway.OrderCustomValidationService) this.context
          .get("validateSerice");
    }
    nc.ui.pu.m21.service.onway.OrderCustomValidationService bean =
        new nc.ui.pu.m21.service.onway.OrderCustomValidationService();
    this.context.put("validateSerice", bean);
    bean.setEditor(this.getBillFormEditor());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_12aa497() {
    if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#12aa497") != null) {
      return (nc.ui.uif2.tangramlayout.node.CNode) this.context
          .get("nc.ui.uif2.tangramlayout.node.CNode#12aa497");
    }
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    this.context.put("nc.ui.uif2.tangramlayout.node.CNode#12aa497", bean);
    bean.setComponent(this.getCardInfoPnl());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_c2a65f() {
    if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#c2a65f") != null) {
      return (nc.ui.uif2.tangramlayout.node.CNode) this.context
          .get("nc.ui.uif2.tangramlayout.node.CNode#c2a65f");
    }
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    this.context.put("nc.ui.uif2.tangramlayout.node.CNode#c2a65f", bean);
    bean.setName(this.getI18nFB_9bc57d());
    bean.setComponent(this.getBillFormEditor());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private java.lang.String getI18nFB_9bc57d() {
    if (this.context.get("nc.ui.uif2.I18nFB#9bc57d") != null) {
      return (java.lang.String) this.context.get("nc.ui.uif2.I18nFB#9bc57d");
    }
    nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    this.context.put("&nc.ui.uif2.I18nFB#9bc57d", bean);
    bean.setResDir("common");
    bean.setResId("UC001-0000106");
    bean.setDefaultValue("¿¨Æ¬");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    try {
      Object product = bean.getObject();
      this.context.put("nc.ui.uif2.I18nFB#9bc57d", product);
      return (java.lang.String) product;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List getManagedList0() {
    List list = new ArrayList();
    list.add(this.getPrintAction());
    list.add(this.getPreviewAction());
    return list;
  }

  private List getManagedList1() {
    List list = new ArrayList();
    list.add(this.getCardBodyBeforeEdit());
    return list;
  }

  private List getManagedList2() {
    List list = new ArrayList();
    list.add(this.getOnWayEventHandler());
    return list;
  }

  private List getManagedList3() {
    List list = new ArrayList();
    list.add(this.getVSNode_138ac7d());
    return list;
  }

  private List getManagedList4() {
    List list = new ArrayList();
    list.add(this.getSeparatorAction());
    list.add(this.getQueryAction());
    list.add(this.getRefreshAction());
    list.add(this.getSeparatorAction());
    list.add(this.getCustomAction());
    list.add(this.getUncustomAction());
    list.add(this.getSeparatorAction());
    list.add(this.getBrowseActionGroup());
    return list;
  }

  private List getManagedList5() {
    List list = new ArrayList();
    list.add(this.getUserdefitemPreparator());
    list.add(this.getMarAsstPreparator());
    return list;
  }

  private List getManagedList6() {
    List list = new ArrayList();
    list.add(this.getQueryParam_d1481f());
    list.add(this.getQueryParam_1d62b3e());
    list.add(this.getQueryParam_1b0fb91());
    return list;
  }

  private List getManagedList7() {
    List list = new ArrayList();
    list.add(this.getUserdefQueryParam_540820());
    list.add(this.getUserdefQueryParam_e1a991());
    return list;
  }

  private Map getManagedMap0() {
    Map map = new HashMap();
    map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
        this.getManagedList1());
    map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowStateChangeEvent",
        this.getManagedList2());
    return map;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1b0fb91() {
    if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#1b0fb91") != null) {
      return (nc.ui.uif2.userdefitem.QueryParam) this.context
          .get("nc.ui.uif2.userdefitem.QueryParam#1b0fb91");
    }
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    this.context.put("nc.ui.uif2.userdefitem.QueryParam#1b0fb91", bean);
    bean.setRulecode("materialassistant");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1d62b3e() {
    if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#1d62b3e") != null) {
      return (nc.ui.uif2.userdefitem.QueryParam) this.context
          .get("nc.ui.uif2.userdefitem.QueryParam#1d62b3e");
    }
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    this.context.put("nc.ui.uif2.userdefitem.QueryParam#1d62b3e", bean);
    bean.setMdfullname("pu.po_order_b");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_d1481f() {
    if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#d1481f") != null) {
      return (nc.ui.uif2.userdefitem.QueryParam) this.context
          .get("nc.ui.uif2.userdefitem.QueryParam#d1481f");
    }
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    this.context.put("nc.ui.uif2.userdefitem.QueryParam#d1481f", bean);
    bean.setMdfullname("pu.po_order");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1c47c9d() {
    if (this.context.get("nc.ui.uif2.tangramlayout.node.TBNode#1c47c9d") != null) {
      return (nc.ui.uif2.tangramlayout.node.TBNode) this.context
          .get("nc.ui.uif2.tangramlayout.node.TBNode#1c47c9d");
    }
    nc.ui.uif2.tangramlayout.node.TBNode bean =
        new nc.ui.uif2.tangramlayout.node.TBNode();
    this.context.put("nc.ui.uif2.tangramlayout.node.TBNode#1c47c9d", bean);
    bean.setShowMode("CardLayout");
    bean.setTabs(this.getManagedList3());
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_540820() {
    if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#540820") != null) {
      return (nc.ui.uif2.editor.UserdefQueryParam) this.context
          .get("nc.ui.uif2.editor.UserdefQueryParam#540820");
    }
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    this.context.put("nc.ui.uif2.editor.UserdefQueryParam#540820", bean);
    bean.setMdfullname("pu.po_order");
    bean.setPos(0);
    bean.setPrefix("vdef");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_e1a991() {
    if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#e1a991") != null) {
      return (nc.ui.uif2.editor.UserdefQueryParam) this.context
          .get("nc.ui.uif2.editor.UserdefQueryParam#e1a991");
    }
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    this.context.put("nc.ui.uif2.editor.UserdefQueryParam#e1a991", bean);
    bean.setMdfullname("pu.po_order_b");
    bean.setPos(1);
    bean.setPrefix("vbdef");
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_138ac7d() {
    if (this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#138ac7d") != null) {
      return (nc.ui.uif2.tangramlayout.node.VSNode) this.context
          .get("nc.ui.uif2.tangramlayout.node.VSNode#138ac7d");
    }
    nc.ui.uif2.tangramlayout.node.VSNode bean =
        new nc.ui.uif2.tangramlayout.node.VSNode();
    this.context.put("nc.ui.uif2.tangramlayout.node.VSNode#138ac7d", bean);
    bean.setUp(this.getCNode_12aa497());
    bean.setDown(this.getCNode_c2a65f());
    bean.setDividerLocation(30f);
    this.setBeanFacotryIfBeanFacatoryAware(bean);
    this.invokeInitializingBean(bean);
    return bean;
  }

}

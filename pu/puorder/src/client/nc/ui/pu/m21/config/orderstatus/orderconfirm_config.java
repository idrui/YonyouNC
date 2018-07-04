package nc.ui.pu.m21.config.orderstatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class orderconfirm_config extends AbstractJavaBeanDefinition {
  private Map<String, Object> context = new HashMap();

  public nc.ui.pu.m21.action.status.confirm.ConfirmAction getConfirmAction() {
    if (context.get("confirmAction") != null)
      return (nc.ui.pu.m21.action.status.confirm.ConfirmAction) context
          .get("confirmAction");
    nc.ui.pu.m21.action.status.confirm.ConfirmAction bean =
        new nc.ui.pu.m21.action.status.confirm.ConfirmAction();
    context.put("confirmAction", bean);
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
    bean.setService(getManageModelService());
    bean.setDataManager(getModelDataManager());
    bean.setValidateService(getValidateSerice());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.confirm.UnConfirmAction getUnConfirmAction() {
    if (context.get("unConfirmAction") != null)
      return (nc.ui.pu.m21.action.status.confirm.UnConfirmAction) context
          .get("unConfirmAction");
    nc.ui.pu.m21.action.status.confirm.UnConfirmAction bean =
        new nc.ui.pu.m21.action.status.confirm.UnConfirmAction();
    context.put("unConfirmAction", bean);
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
    bean.setService(getManageModelService());
    bean.setDataManager(getModelDataManager());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.ConfirmDLGInitializer getQryDLGInitializer() {
    if (context.get("qryDLGInitializer") != null)
      return (nc.ui.pu.m21.view.ConfirmDLGInitializer) context
          .get("qryDLGInitializer");
    nc.ui.pu.m21.view.ConfirmDLGInitializer bean =
        new nc.ui.pu.m21.view.ConfirmDLGInitializer();
    context.put("qryDLGInitializer", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.action.status.confirm.ConfirmQueryAction getQueryAction() {
    if (context.get("queryAction") != null)
      return (nc.ui.pu.m21.action.status.confirm.ConfirmQueryAction) context
          .get("queryAction");
    nc.ui.pu.m21.action.status.confirm.ConfirmQueryAction bean =
        new nc.ui.pu.m21.action.status.confirm.ConfirmQueryAction();
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

  public nc.ui.pu.m21.action.status.confirm.RefreshAction getRefreshAction() {
    if (context.get("refreshAction") != null)
      return (nc.ui.pu.m21.action.status.confirm.RefreshAction) context
          .get("refreshAction");
    nc.ui.pu.m21.action.status.confirm.RefreshAction bean =
        new nc.ui.pu.m21.action.status.confirm.RefreshAction();
    context.put("refreshAction", bean);
    bean.setDataManager(getModelDataManager());
    bean.setModel(getManageAppModel());
    bean.setEditor(getBillFormEditor());
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

  public nc.ui.pu.m21.editor.card.beforeedit.ConfirmCardBodyBeforeEditEventHandler getCardBodyBeforeEdit() {
    if (context.get("cardBodyBeforeEdit") != null)
      return (nc.ui.pu.m21.editor.card.beforeedit.ConfirmCardBodyBeforeEditEventHandler) context
          .get("cardBodyBeforeEdit");
    nc.ui.pu.m21.editor.card.beforeedit.ConfirmCardBodyBeforeEditEventHandler bean =
        new nc.ui.pu.m21.editor.card.beforeedit.ConfirmCardBodyBeforeEditEventHandler();
    context.put("cardBodyBeforeEdit", bean);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.editor.list.ConfirmEventHandler getConfirmSelectHandler() {
    if (context.get("confirmSelectHandler") != null)
      return (nc.ui.pu.m21.editor.list.ConfirmEventHandler) context
          .get("confirmSelectHandler");
    nc.ui.pu.m21.editor.list.ConfirmEventHandler bean =
        new nc.ui.pu.m21.editor.list.ConfirmEventHandler();
    context.put("confirmSelectHandler", bean);
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
        getManagedList0());
    map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowStateChangeEvent",
        getManagedList1());
    return map;
  }

  private List getManagedList0() {
    List list = new ArrayList();
    list.add(getCardBodyBeforeEdit());
    return list;
  }

  private List getManagedList1() {
    List list = new ArrayList();
    list.add(getConfirmSelectHandler());
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

  public nc.ui.pu.m21.service.onway.OrderConfirmService getManageModelService() {
    if (context.get("manageModelService") != null)
      return (nc.ui.pu.m21.service.onway.OrderConfirmService) context
          .get("manageModelService");
    nc.ui.pu.m21.service.onway.OrderConfirmService bean =
        new nc.ui.pu.m21.service.onway.OrderConfirmService();
    context.put("manageModelService", bean);
    bean.setConfirmQuery(nc.itf.pu.m21.IConfirmQuery.class);
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

  public nc.ui.pu.m21.service.ConfirmValidationService getValidateSerice() {
    if (context.get("validateSerice") != null)
      return (nc.ui.pu.m21.service.ConfirmValidationService) context
          .get("validateSerice");
    nc.ui.pu.m21.service.ConfirmValidationService bean =
        new nc.ui.pu.m21.service.ConfirmValidationService();
    context.put("validateSerice", bean);
    bean.setEditor(getBillFormEditor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  public nc.ui.pu.m21.view.StatusBillFormEditor getBillFormEditor() {
    if (context.get("billFormEditor") != null)
      return (nc.ui.pu.m21.view.StatusBillFormEditor) context
          .get("billFormEditor");
    nc.ui.pu.m21.view.StatusBillFormEditor bean =
        new nc.ui.pu.m21.view.StatusBillFormEditor();
    context.put("billFormEditor", bean);
    bean.setShowOrgPanel(false);
    bean.setModel(getManageAppModel());
    bean.setTemplateContainer(getTemplateContainer());
    bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
    bean.initUI();
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
    bean.setTangramLayoutRoot(getTBNode_1e26908());
    bean.setActions(getManagedList3());
    bean.initUI();
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1e26908() {
    if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#1e26908") != null)
      return (nc.ui.uif2.tangramlayout.node.TBNode) context
          .get("nc.ui.uif2.tangramlayout.node.TBNode#1e26908");
    nc.ui.uif2.tangramlayout.node.TBNode bean =
        new nc.ui.uif2.tangramlayout.node.TBNode();
    context.put("nc.ui.uif2.tangramlayout.node.TBNode#1e26908", bean);
    bean.setShowMode("CardLayout");
    bean.setTabs(getManagedList2());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList2() {
    List list = new ArrayList();
    list.add(getVSNode_1c9e523());
    return list;
  }

  private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1c9e523() {
    if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#1c9e523") != null)
      return (nc.ui.uif2.tangramlayout.node.VSNode) context
          .get("nc.ui.uif2.tangramlayout.node.VSNode#1c9e523");
    nc.ui.uif2.tangramlayout.node.VSNode bean =
        new nc.ui.uif2.tangramlayout.node.VSNode();
    context.put("nc.ui.uif2.tangramlayout.node.VSNode#1c9e523", bean);
    bean.setUp(getCNode_1935b72());
    bean.setDown(getCNode_1fef605());
    bean.setDividerLocation(30f);
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_1935b72() {
    if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1935b72") != null)
      return (nc.ui.uif2.tangramlayout.node.CNode) context
          .get("nc.ui.uif2.tangramlayout.node.CNode#1935b72");
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    context.put("nc.ui.uif2.tangramlayout.node.CNode#1935b72", bean);
    bean.setComponent(getCardInfoPnl());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.tangramlayout.node.CNode getCNode_1fef605() {
    if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1fef605") != null)
      return (nc.ui.uif2.tangramlayout.node.CNode) context
          .get("nc.ui.uif2.tangramlayout.node.CNode#1fef605");
    nc.ui.uif2.tangramlayout.node.CNode bean =
        new nc.ui.uif2.tangramlayout.node.CNode();
    context.put("nc.ui.uif2.tangramlayout.node.CNode#1fef605", bean);
    bean.setName(getI18nFB_158b1c4());
    bean.setComponent(getBillFormEditor());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private java.lang.String getI18nFB_158b1c4() {
    if (context.get("nc.ui.uif2.I18nFB#158b1c4") != null)
      return (java.lang.String) context.get("nc.ui.uif2.I18nFB#158b1c4");
    nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#158b1c4", bean);
    bean.setResDir("common");
    bean.setResId("UC001-0000106");
    bean.setDefaultValue("¿¨Æ¬");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    try {
      Object product = bean.getObject();
      context.put("nc.ui.uif2.I18nFB#158b1c4", product);
      return (java.lang.String) product;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List getManagedList3() {
    List list = new ArrayList();
    list.add(getSeparatorAction());
    list.add(getQueryAction());
    list.add(getRefreshAction());
    list.add(getSeparatorAction());
    list.add(getConfirmAction());
    list.add(getUnConfirmAction());
    return list;
  }

  public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() {
    if (context.get("userdefAndMarAsstCardPreparator") != null)
      return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
          .get("userdefAndMarAsstCardPreparator");
    nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean =
        new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
    context.put("userdefAndMarAsstCardPreparator", bean);
    bean.setBillDataPrepares(getManagedList4());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList4() {
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
    bean.setParams(getManagedList5());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList5() {
    List list = new ArrayList();
    list.add(getQueryParam_15db116());
    list.add(getQueryParam_154d4a());
    list.add(getQueryParam_855d52());
    return list;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_15db116() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#15db116") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#15db116");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#15db116", bean);
    bean.setMdfullname("pu.po_order");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_154d4a() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#154d4a") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#154d4a");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#154d4a", bean);
    bean.setMdfullname("pu.po_order_b");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.userdefitem.QueryParam getQueryParam_855d52() {
    if (context.get("nc.ui.uif2.userdefitem.QueryParam#855d52") != null)
      return (nc.ui.uif2.userdefitem.QueryParam) context
          .get("nc.ui.uif2.userdefitem.QueryParam#855d52");
    nc.ui.uif2.userdefitem.QueryParam bean =
        new nc.ui.uif2.userdefitem.QueryParam();
    context.put("nc.ui.uif2.userdefitem.QueryParam#855d52", bean);
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
    bean.setParams(getManagedList6());
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private List getManagedList6() {
    List list = new ArrayList();
    list.add(getUserdefQueryParam_1b1dfe5());
    list.add(getUserdefQueryParam_e24197());
    return list;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1b1dfe5() {
    if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1b1dfe5") != null)
      return (nc.ui.uif2.editor.UserdefQueryParam) context
          .get("nc.ui.uif2.editor.UserdefQueryParam#1b1dfe5");
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    context.put("nc.ui.uif2.editor.UserdefQueryParam#1b1dfe5", bean);
    bean.setMdfullname("pu.po_order");
    bean.setPos(0);
    bean.setPrefix("vdef");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

  private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_e24197() {
    if (context.get("nc.ui.uif2.editor.UserdefQueryParam#e24197") != null)
      return (nc.ui.uif2.editor.UserdefQueryParam) context
          .get("nc.ui.uif2.editor.UserdefQueryParam#e24197");
    nc.ui.uif2.editor.UserdefQueryParam bean =
        new nc.ui.uif2.editor.UserdefQueryParam();
    context.put("nc.ui.uif2.editor.UserdefQueryParam#e24197", bean);
    bean.setMdfullname("pu.po_order_b");
    bean.setPos(1);
    bean.setPrefix("vbdef");
    setBeanFacotryIfBeanFacatoryAware(bean);
    invokeInitializingBean(bean);
    return bean;
  }

}

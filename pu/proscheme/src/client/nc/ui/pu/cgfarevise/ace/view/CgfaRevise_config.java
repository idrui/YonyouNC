package nc.ui.pu.cgfarevise.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class CgfaRevise_config extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.vo.uif2.LoginContext getContext() {
		if (context.get("context") != null)
			return (nc.vo.uif2.LoginContext) context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfarevise.ace.serviceproxy.AceCgfaMaintainProxy getBmModelModelService() {
		if (context.get("bmModelModelService") != null)
			return (nc.ui.pu.cgfarevise.ace.serviceproxy.AceCgfaMaintainProxy) context
					.get("bmModelModelService");
		nc.ui.pu.cgfarevise.ace.serviceproxy.AceCgfaMaintainProxy bean = new nc.ui.pu.cgfarevise.ace.serviceproxy.AceCgfaMaintainProxy();
		context.put("bmModelModelService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.vo.bd.meta.GeneralBDObjectAdapterFactory getBOAdapterFactory() {
		if (context.get("BOAdapterFactory") != null)
			return (nc.vo.bd.meta.GeneralBDObjectAdapterFactory) context
					.get("BOAdapterFactory");
		nc.vo.bd.meta.GeneralBDObjectAdapterFactory bean = new nc.vo.bd.meta.GeneralBDObjectAdapterFactory();
		context.put("BOAdapterFactory", bean);
		bean.setMode("MD");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getBmModel() {
		if (context.get("bmModel") != null)
			return (nc.ui.pubapp.uif2app.model.BillManageModel) context
					.get("bmModel");
		nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
		context.put("bmModel", bean);
		bean.setContext(getContext());
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager() {
		if (context.get("bmModelModelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context
					.get("bmModelModelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("bmModelModelDataManager", bean);
		bean.setModel(getBmModel());
		bean.setService(getBmModelModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.pubapp.uif2app.view.TemplateContainer) context
					.get("templateContainer");
		nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.setNodeKeies(getManagedList0());
		bean.load();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add("bt");
		return list;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getViewa() {
		if (context.get("viewa") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context
					.get("viewa");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("viewa", bean);
		bean.setQueryAreaCreator(getDefaultQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getBillListView() {
		if (context.get("billListView") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView) context
					.get("billListView");
		nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
		context.put("billListView", bean);
		bean.setModel(getBmModel());
		bean.setNodekey("bt");
		bean.setMultiSelectionEnable(true);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setMultiSelectionMode(0);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getViewb() {
		if (context.get("viewb") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context
					.get("viewb");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("viewb", bean);
		bean.setModel(getBmModel());
		bean.setTitleAction(getReturnAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnAction() {
		if (context.get("returnAction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context
					.get("returnAction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnAction", bean);
		bean.setGoComponent(getBillListView());
		bean.setSaveAction(getSaveScriptAction());
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillForm() {
		if (context.get("billForm") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm) context
					.get("billForm");
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
		context.put("billForm", bean);
		bean.setModel(getBmModel());
		bean.setNodekey("bt");
		bean.setBodyLineActions(getManagedList1());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getBodyAddLineAction_fd7de8());
		list.add(getBodyInsertLineAction_551c4a());
		list.add(getBodyDelLineActionForCGFA_1b45710());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_fd7de8() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#fd7de8") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#fd7de8");
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#fd7de8",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_551c4a() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#551c4a") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#551c4a");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#551c4a",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA getBodyDelLineActionForCGFA_1b45710() {
		if (context
				.get("nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA#1b45710") != null)
			return (nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA) context
					.get("nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA#1b45710");
		nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA bean = new nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA();
		context.put("nc.ui.pu.cgfa.action.BodyDelLineActionForCGFA#1b45710",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setTangramLayoutRoot(getTBNode_1725678());
		bean.setModel(getBmModel());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1725678() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#1725678") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context
					.get("nc.ui.uif2.tangramlayout.node.TBNode#1725678");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#1725678", bean);
		bean.setTabs(getManagedList2());
		bean.setName("cardLayout");
		bean.setShowMode("CardLayout");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getHSNode_1c709e7());
		list.add(getVSNode_1390106());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1c709e7() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#1c709e7") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#1c709e7");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#1c709e7", bean);
		bean.setLeft(getCNode_bfedc0());
		bean.setRight(getCNode_91e2e4());
		bean.setDividerLocation(215.0f);
		bean.setName("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_bfedc0() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#bfedc0") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#bfedc0");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#bfedc0", bean);
		bean.setComponent(getViewa());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_91e2e4() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#91e2e4") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#91e2e4");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#91e2e4", bean);
		bean.setComponent(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1390106() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#1390106") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#1390106");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#1390106", bean);
		bean.setUp(getCNode_5b407b());
		bean.setDown(getCNode_e6529a());
		bean.setDividerLocation(43.0f);
		bean.setName("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_5b407b() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#5b407b") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#5b407b");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#5b407b", bean);
		bean.setComponent(getViewb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_e6529a() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#e6529a") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#e6529a");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#e6529a", bean);
		bean.setComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillListView());
		context.put("actionsOfList", bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList4());
		bean.setEditActions(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getEditAction());
		list.add(getDeleteScriptAction());
		list.add(getSeparatorAction());
		list.add(getDefaultRefreshAction());
		list.add(getSeparatorAction());
		list.add(getCommitScriptAction());
		list.add(getUnCommitScriptAction());
		list.add(getApproveScriptAction());
		list.add(getUNApproveScriptAction());
		list.add(getSeparatorAction());
		list.add(getLinkQueryAction());
		list.add(getSeparatorAction());
		list.add(getMetaDataBasedPrintAction());
		list.add(getMetaDataBasedPrintActiona());
		list.add(getOutputAction());
		list.add(getSeparatorAction());
		list.add(getPFApproveStatusInfoAction());
		list.add(getSeparatorAction());
		list.add(getSendzbwt());
		return list;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getSaveScriptAction());
		list.add(getCancelAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillForm());
		context.put("actionsOfCard", bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList6());
		bean.setEditActions(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getEditAction());
		list.add(getDeleteScriptAction());
		list.add(getDefaultQueryAction());
		list.add(getSeparatorAction());
		list.add(getCommitScriptAction());
		list.add(getUnCommitScriptAction());
		list.add(getApproveScriptAction());
		list.add(getUNApproveScriptAction());
		list.add(getSeparatorAction());
		list.add(getLinkQueryAction());
		list.add(getSeparatorAction());
		list.add(getMetaDataBasedPrintAction());
		list.add(getMetaDataBasedPrintActiona());
		list.add(getOutputAction());
		list.add(getSeparatorAction());
		list.add(getPFApproveStatusInfoAction());
		list.add(getSeparatorAction());
		list.add(getSendzbwt());
		list.add(getSeparatorAction());
		list.add(getLineCloseAction());
		list.add(getCancelLineCloseAction());
		list.add(getLinkQueryHistory4CgfaPriceAction());
		return list;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getSaveScriptAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getRefm20lineAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) context
					.get("InitDataListener");
		nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
		context.put("InitDataListener", bean);
		bean.setModel(getBmModel());
		bean.setContext(getContext());
		bean.setVoClassName("nc.vo.pu.cgfa.AggCgfa");
		bean.setProcessorMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("1", getMJT01RDataProcessor_18affcc());
		return map;
	}

	private nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor getMJT01RDataProcessor_18affcc() {
		if (context
				.get("nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor#18affcc") != null)
			return (nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor) context
					.get("nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor#18affcc");
		nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor bean = new nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor();
		context.put("nc.ui.pu.cgfa.billui.push.MJT01RDataProcessor#18affcc",
				bean);
		bean.setTransferProcessor(getTransferViewProcessorForSelf());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.pubapp.common.validateservice.ClosingCheck) context
					.get("ClosingListener");
		nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
		context.put("ClosingListener", bean);
		bean.setModel(getBmModel());
		bean.setSaveAction(getSaveScriptAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getBmModelEventMediator() {
		if (context.get("bmModelEventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("bmModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("bmModelEventMediator", bean);
		bean.setModel(getBmModel());
		bean.setHandlerGroup(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_39d903());
		list.add(getEventHandlerGroup_1944cbc());
		list.add(getEventHandlerGroup_1ab8c57());
		list.add(getEventHandlerGroup_723941());
		list.add(getEventHandlerGroup_11b3541());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_39d903() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#39d903") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#39d903");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#39d903", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangeHandler_9fb93b());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_9fb93b() {
		if (context.get("nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler#9fb93b") != null)
			return (nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler) context
					.get("nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler#9fb93b");
		nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler bean = new nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler();
		context.put("nc.ui.pu.cgfa.ace.handler.AceOrgChangeHandler#9fb93b",
				bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1944cbc() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1944cbc") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1944cbc");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1944cbc",
				bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_1d68863());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.ace.handler.AceAddHandler getAceAddHandler_1d68863() {
		if (context.get("nc.ui.pu.cgfa.ace.handler.AceAddHandler#1d68863") != null)
			return (nc.ui.pu.cgfa.ace.handler.AceAddHandler) context
					.get("nc.ui.pu.cgfa.ace.handler.AceAddHandler#1d68863");
		nc.ui.pu.cgfa.ace.handler.AceAddHandler bean = new nc.ui.pu.cgfa.ace.handler.AceAddHandler();
		context.put("nc.ui.pu.cgfa.ace.handler.AceAddHandler#1d68863", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1ab8c57() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab8c57") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab8c57");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab8c57",
				bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getCardHeadTailBeforeEditHandler_13a0b0d());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler getCardHeadTailBeforeEditHandler_13a0b0d() {
		if (context
				.get("nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler#13a0b0d") != null)
			return (nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler) context
					.get("nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler#13a0b0d");
		nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler bean = new nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler();
		context.put(
				"nc.ui.pu.cgfa.ace.event.CardHeadTailBeforeEditHandler#13a0b0d",
				bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_723941() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#723941") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#723941");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#723941", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getCardHeadTailAfterEditEventHandler_18ac883());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler getCardHeadTailAfterEditEventHandler_18ac883() {
		if (context
				.get("nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler#18ac883") != null)
			return (nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler) context
					.get("nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler#18ac883");
		nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler bean = new nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler();
		context.put(
				"nc.ui.pu.cgfa.ace.event.CardHeadTailAfterEditEventHandler#18ac883",
				bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_11b3541() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#11b3541") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#11b3541");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#11b3541",
				bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getCardBodyAfterEditEventHandler_10ceb0b());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler getCardBodyAfterEditEventHandler_10ceb0b() {
		if (context
				.get("nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler#10ceb0b") != null)
			return (nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler) context
					.get("nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler#10ceb0b");
		nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler bean = new nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler();
		context.put(
				"nc.ui.pu.cgfa.ace.event.CardBodyAfterEditEventHandler#10ceb0b",
				bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getBmModelLasilyLodadMediator() {
		if (context.get("bmModelLasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context
					.get("bmModelLasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("bmModelLasilyLodadMediator", bean);
		bean.setModel(getBmModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_1456e28());
		list.add(getListPanelLazilyLoad_1431735());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_1456e28() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1456e28") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1456e28");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1456e28",
				bean);
		bean.setBillform(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1431735() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1431735") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1431735");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1431735",
				bean);
		bean.setListView(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context
					.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context
					.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getBillListView());
		bean.setShowUpComponent(getBillForm());
		bean.setHyperLinkColumn("billno");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.pubapp.bill.BillCodeMediator) context
					.get("billCodeMediator");
		nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
		context.put("billCodeMediator", bean);
		bean.setBillForm(getBillForm());
		bean.setBillCodeKey("billno");
		bean.setBillType("JT01");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.AddAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.pubapp.uif2app.actions.AddAction) context
					.get("addAction");
		nc.ui.pubapp.uif2app.actions.AddAction bean = new nc.ui.pubapp.uif2app.actions.AddAction();
		context.put("addAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getCompositeActionInterceptor_9ece1e());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_9ece1e() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#9ece1e") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#9ece1e");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#9ece1e",
				bean);
		bean.setInterceptors(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getShowUpComponentInterceptor_1a8d2aa());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1a8d2aa() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1a8d2aa") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1a8d2aa");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1a8d2aa",
				bean);
		bean.setShowUpComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.EditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.pubapp.uif2app.actions.EditAction) context
					.get("editAction");
		nc.ui.pubapp.uif2app.actions.EditAction bean = new nc.ui.pubapp.uif2app.actions.EditAction();
		context.put("editAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getCompositeActionInterceptor_83e4d8());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_83e4d8() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#83e4d8") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#83e4d8");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#83e4d8",
				bean);
		bean.setInterceptors(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getShowUpComponentInterceptor_1ee2032());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1ee2032() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1ee2032") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1ee2032");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1ee2032",
				bean);
		bean.setShowUpComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.LinkQueryHistory4CgfaPriceAction getLinkQueryHistory4CgfaPriceAction() {
		if (context.get("linkQueryHistory4CgfaPriceAction") != null)
			return (nc.ui.pu.cgfa.action.LinkQueryHistory4CgfaPriceAction) context
					.get("linkQueryHistory4CgfaPriceAction");
		nc.ui.pu.cgfa.action.LinkQueryHistory4CgfaPriceAction bean = new nc.ui.pu.cgfa.action.LinkQueryHistory4CgfaPriceAction();
		context.put("linkQueryHistory4CgfaPriceAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction getDeleteScriptAction() {
		if (context.get("deleteScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction) context
					.get("deleteScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction();
		context.put("deleteScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("DELETE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getDefaultQueryActionQueryTemplateContainer() {
		if (context.get("defaultQueryActionQueryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context
					.get("defaultQueryActionQueryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("defaultQueryActionQueryTemplateContainer", bean);
		bean.setNodeKey("qt");
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getDefaultQueryAction() {
		if (context.get("defaultQueryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("defaultQueryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("defaultQueryAction", bean);
		bean.setModel(getBmModel());
		bean.setTemplateContainer(getDefaultQueryActionQueryTemplateContainer());
		bean.setNodeKey("qt");
		bean.setDataManager(getBmModelModelDataManager());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.pubapp.uif2app.actions.CopyAction) context
					.get("copyAction");
		nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
		context.put("copyAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setCopyActionProcessor(getCopyActionProcessor_1254e4e());
		bean.setExceptionHandler(getExceptionHandler());
		bean.setBtnName("复制");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.cgfa.action.CopyActionProcessor getCopyActionProcessor_1254e4e() {
		if (context.get("nc.ui.pu.cgfa.action.CopyActionProcessor#1254e4e") != null)
			return (nc.ui.pu.cgfa.action.CopyActionProcessor) context
					.get("nc.ui.pu.cgfa.action.CopyActionProcessor#1254e4e");
		nc.ui.pu.cgfa.action.CopyActionProcessor bean = new nc.ui.pu.cgfa.action.CopyActionProcessor();
		context.put("nc.ui.pu.cgfa.action.CopyActionProcessor#1254e4e", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getDefaultRefreshAction() {
		if (context.get("defaultRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
					.get("defaultRefreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("defaultRefreshAction", bean);
		bean.setModel(getBmModel());
		bean.setDataManager(getBmModelModelDataManager());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction getCommitScriptAction() {
		if (context.get("commitScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction) context
					.get("commitScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction();
		context.put("commitScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction getUnCommitScriptAction() {
		if (context.get("unCommitScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction) context
					.get("unCommitScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction();
		context.put("unCommitScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNSAVEBILL");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction getApproveScriptAction() {
		if (context.get("approveScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction) context
					.get("approveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction();
		context.put("approveScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("APPROVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.UNApproveAction getUNApproveScriptAction() {
		if (context.get("uNApproveScriptAction") != null)
			return (nc.ui.pu.cgfa.action.UNApproveAction) context
					.get("uNApproveScriptAction");
		nc.ui.pu.cgfa.action.UNApproveAction bean = new nc.ui.pu.cgfa.action.UNApproveAction();
		context.put("uNApproveScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.LinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.pubapp.uif2app.actions.LinkQueryAction) context
					.get("linkQueryAction");
		nc.ui.pubapp.uif2app.actions.LinkQueryAction bean = new nc.ui.pubapp.uif2app.actions.LinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel(getBmModel());
		bean.setBillType("JT01");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintAction() {
		if (context.get("metaDataBasedPrintAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("metaDataBasedPrintAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintAction", bean);
		bean.setModel(getBmModel());
		bean.setActioncode("Preview");
		bean.setActionname("预览");
		bean.setPreview(true);
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintActiona() {
		if (context.get("metaDataBasedPrintActiona") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("metaDataBasedPrintActiona");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintActiona", bean);
		bean.setModel(getBmModel());
		bean.setActioncode("Print");
		bean.setActionname("打印");
		bean.setPreview(false);
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context
					.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getPFApproveStatusInfoAction() {
		if (context.get("pFApproveStatusInfoAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction) context
					.get("pFApproveStatusInfoAction");
		nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
		context.put("pFApproveStatusInfoAction", bean);
		bean.setModel(getBmModel());
		bean.setBillType("JT01");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction getSaveScriptAction() {
		if (context.get("saveScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction) context
					.get("saveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction();
		context.put("saveScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("JT01");
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVEBASE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.pubapp.uif2app.actions.CancelAction) context
					.get("cancelAction");
		nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
		if (context.get("separatorAction") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler() {
		if (context.get("exceptionHandler") != null)
			return (nc.ui.uif2.DefaultExceptionHanler) context
					.get("exceptionHandler");
		nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler(
				getContainer());
		context.put("exceptionHandler", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
		if (context.get("remoteCallCombinatorCaller") != null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller) context
					.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller", bean);
		bean.setRemoteCallers(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getPfAddInfoLoader());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenuGroup() {
		if (context.get("addMenuGroup") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("addMenuGroup");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("addMenuGroup", bean);
		bean.setBillType("JT01");
		bean.setActions(getManagedList13());
		bean.setModel(getBmModel());
		bean.setPfAddInfoLoader(getPfAddInfoLoader());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getSeparatorAction());
		list.add(getAddQG20Action());
		list.add(getSeparatorAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader() {
		if (context.get("pfAddInfoLoader") != null)
			return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader) context
					.get("pfAddInfoLoader");
		nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
		context.put("pfAddInfoLoader", bean);
		bean.setBillType("JT01");
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.billref.AddQG20Action getAddQG20Action() {
		if (context.get("AddQG20Action") != null)
			return (nc.ui.pu.cgfa.billref.AddQG20Action) context
					.get("AddQG20Action");
		nc.ui.pu.cgfa.billref.AddQG20Action bean = new nc.ui.pu.cgfa.billref.AddQG20Action();
		context.put("AddQG20Action", bean);
		bean.setSourceBillType("20");
		bean.setSourceBillName("参照请购单");
		bean.setFlowBillType(false);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setTransferViewProcessor(getTransferProcessorforTR10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforTR10() {
		if (context.get("transferProcessorforTR10") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforTR10");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforTR10", bean);
		bean.setList(getBillListView());
		bean.setActionContainer(getContainer());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getQG20RefTJ01TRansferBillDataLogic());
		bean.setBillForm(getBillForm());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveScriptAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.billref.QG20RefTJ01TRansferBillDataLogic getQG20RefTJ01TRansferBillDataLogic() {
		if (context.get("QG20RefTJ01TRansferBillDataLogic") != null)
			return (nc.ui.pu.cgfa.billref.QG20RefTJ01TRansferBillDataLogic) context
					.get("QG20RefTJ01TRansferBillDataLogic");
		nc.ui.pu.cgfa.billref.QG20RefTJ01TRansferBillDataLogic bean = new nc.ui.pu.cgfa.billref.QG20RefTJ01TRansferBillDataLogic();
		context.put("QG20RefTJ01TRansferBillDataLogic", bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.SendZbwt getSendzbwt() {
		if (context.get("sendzbwt") != null)
			return (nc.ui.pu.cgfa.action.SendZbwt) context.get("sendzbwt");
		nc.ui.pu.cgfa.action.SendZbwt bean = new nc.ui.pu.cgfa.action.SendZbwt();
		context.put("sendzbwt", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setDataManager(getBmModelModelDataManager());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.LineCloseAction getLineCloseAction() {
		if (context.get("LineCloseAction") != null)
			return (nc.ui.pu.cgfa.action.LineCloseAction) context
					.get("LineCloseAction");
		nc.ui.pu.cgfa.action.LineCloseAction bean = new nc.ui.pu.cgfa.action.LineCloseAction();
		context.put("LineCloseAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.CancelLineCloseAction getCancelLineCloseAction() {
		if (context.get("CancelLineCloseAction") != null)
			return (nc.ui.pu.cgfa.action.CancelLineCloseAction) context
					.get("CancelLineCloseAction");
		nc.ui.pu.cgfa.action.CancelLineCloseAction bean = new nc.ui.pu.cgfa.action.CancelLineCloseAction();
		context.put("CancelLineCloseAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getRefAddLineAction() {
		if (context.get("refAddLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("refAddLineAction");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("refAddLineAction", bean);
		bean.setBillType("JT01");
		bean.setModel(getBmModel());
		bean.setCode("refaddline");
		bean.setName(getI18nFB_380138());
		bean.setActions(getManagedList14());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_380138() {
		if (context.get("nc.ui.uif2.I18nFB#380138") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#380138");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#380138", bean);
		bean.setResDir("4006002_0");
		bean.setResId("04006002-0174");
		bean.setDefaultValue("参照增行");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#380138", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getRefm20lineAction());
		return list;
	}

	public nc.ui.pu.cgfa.billref.AddQG20LineAction getRefm20lineAction() {
		if (context.get("refm20lineAction") != null)
			return (nc.ui.pu.cgfa.billref.AddQG20LineAction) context
					.get("refm20lineAction");
		nc.ui.pu.cgfa.billref.AddQG20LineAction bean = new nc.ui.pu.cgfa.billref.AddQG20LineAction();
		context.put("refm20lineAction", bean);
		bean.setSourceBillType("20");
		bean.setSourceBillName("参照请购单行");
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setTransferViewProcessor(getTransferProcessorforTR10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfarevise.action.ReviseAction getReviseAction() {
		if (context.get("reviseAction") != null)
			return (nc.ui.pu.cgfarevise.action.ReviseAction) context
					.get("reviseAction");
		nc.ui.pu.cgfarevise.action.ReviseAction bean = new nc.ui.pu.cgfarevise.action.ReviseAction();
		context.put("reviseAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setInterceptor(getCompositeActionInterceptor_1aea20d());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_1aea20d() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1aea20d") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1aea20d");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1aea20d",
				bean);
		bean.setInterceptors(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getShowUpComponentInterceptor_d80ad2());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_d80ad2() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#d80ad2") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#d80ad2");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#d80ad2",
				bean);
		bean.setShowUpComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfarevise.action.ReviseSaveAction getReviseSaveAction() {
		if (context.get("reviseSaveAction") != null)
			return (nc.ui.pu.cgfarevise.action.ReviseSaveAction) context
					.get("reviseSaveAction");
		nc.ui.pu.cgfarevise.action.ReviseSaveAction bean = new nc.ui.pu.cgfarevise.action.ReviseSaveAction();
		context.put("reviseSaveAction", bean);
		bean.setModel(getBmModel());
		bean.setDataManager(getBmModelModelDataManager());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.cgfa.action.LinkQueryReviseRecord getLinkQueryReviseRecord() {
		if (context.get("linkQueryReviseRecord") != null)
			return (nc.ui.pu.cgfa.action.LinkQueryReviseRecord) context
					.get("linkQueryReviseRecord");
		nc.ui.pu.cgfa.action.LinkQueryReviseRecord bean = new nc.ui.pu.cgfa.action.LinkQueryReviseRecord();
		context.put("linkQueryReviseRecord", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessorForSelf() {
		if (context.get("transferViewProcessorForSelf") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessorForSelf");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessorForSelf", bean);
		bean.setList(getBillListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforSelf());
		bean.setBillForm(getBillForm());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveScriptAction());
		bean.setCommitAction(getApproveScriptAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.push.TransferLogicforSelf getTransferLogicforSelf() {
		if (context.get("transferLogicforSelf") != null)
			return (nc.ui.so.m30.billui.push.TransferLogicforSelf) context
					.get("transferLogicforSelf");
		nc.ui.so.m30.billui.push.TransferLogicforSelf bean = new nc.ui.so.m30.billui.push.TransferLogicforSelf();
		context.put("transferLogicforSelf", bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}

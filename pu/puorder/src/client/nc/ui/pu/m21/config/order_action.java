package nc.ui.pu.m21.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class order_action extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
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
				(nc.ui.uif2.components.ITabbedPaneAwareComponent) findBeanInUIF2BeanFactory("listView"));
		context.put("actionsOfList", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setActions(getManagedList1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getReviseAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendApproveMenuAction());
		list.add(getAuditMenuAction());
		list.add(getAssistMenuAction());
		list.add(getSeparatorAction());
		list.add(getLinkBillMenuAction());
		list.add(getSeparatorAction());
		list.add(getRelateFunMenuAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				(nc.ui.uif2.components.ITabbedPaneAwareComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		context.put("actionsOfCard", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setActions(getManagedList2());
		bean.setEditActions(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getReviseAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendApproveMenuAction());
		list.add(getAuditMenuAction());
		list.add(getAssistMenuAction());
		list.add(getSeparatorAction());
		list.add(getLinkBillMenuAction());
		list.add(getSeparatorAction());
		list.add(getRelateFunMenuAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getIMAction());
		return list;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getSaveApproveAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getAssistMenuActionForCard());
		list.add(getSeparatorAction());
		list.add(getLinkBillMenuActionForCard());
		list.add(getSeparatorAction());
		list.add(getRelateFunMenuActionForCard());
		return list;
	}

	public nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor getFormInterceptor() {
		if (context.get("formInterceptor") != null)
			return (nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor) context
					.get("formInterceptor");
		nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor bean = new nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor();
		context.put("formInterceptor", bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferBillViewProcessor() {
		if (context.get("transferBillViewProcessor") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferBillViewProcessor");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferBillViewProcessor", bean);
		bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView) findBeanInUIF2BeanFactory("listView"));
		bean.setActionContainer((nc.ui.uif2.actions.IActionContributor) findBeanInUIF2BeanFactory("container"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferLogic(getDefaultBillDataLogic_fe6c2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_fe6c2() {
		if (context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#fe6c2") != null)
			return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic) context
					.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#fe6c2");
		nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
		context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#fe6c2",
				bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor() {
		if (context.get("listInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("listInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("listInterceptor", bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.billref.dest.OrderTransferListProcessor getOrderTransferListProcessor() {
		if (context.get("orderTransferListProcessor") != null)
			return (nc.ui.pu.m21.billref.dest.OrderTransferListProcessor) context
					.get("orderTransferListProcessor");
		nc.ui.pu.m21.billref.dest.OrderTransferListProcessor bean = new nc.ui.pu.m21.billref.dest.OrderTransferListProcessor();
		context.put("orderTransferListProcessor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor() {
		if (context.get("transferViewProcessor") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessor");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessor", bean);
		bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView) findBeanInUIF2BeanFactory("listView"));
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		bean.setCancelAction(getCancelAction());
		bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setQueryAreaShell((nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) findBeanInUIF2BeanFactory("queryArea"));
		bean.setListProcessor(getOrderTransferListProcessor());
		bean.setQueryInfoToolbarPanel((nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel) findBeanInUIF2BeanFactory("queryInfo"));
		bean.setTransferLogic(getOrderTransferBillDataLogic_b0c762());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic getOrderTransferBillDataLogic_b0c762() {
		if (context
				.get("nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#b0c762") != null)
			return (nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic) context
					.get("nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#b0c762");
		nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic bean = new nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic();
		context.put(
				"nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#b0c762",
				bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.processor.CopyActionProcessor getCopyActionProcessor() {
		if (context.get("copyActionProcessor") != null)
			return (nc.ui.pu.m21.action.processor.CopyActionProcessor) context
					.get("copyActionProcessor");
		nc.ui.pu.m21.action.processor.CopyActionProcessor bean = new nc.ui.pu.m21.action.processor.CopyActionProcessor();
		context.put("copyActionProcessor", bean);
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

	public nc.ui.pu.m21.action.AddManualAction getAddManualAction() {
		if (context.get("addManualAction") != null)
			return (nc.ui.pu.m21.action.AddManualAction) context
					.get("addManualAction");
		nc.ui.pu.m21.action.AddManualAction bean = new nc.ui.pu.m21.action.AddManualAction();
		context.put("addManualAction", bean);
		bean.setSourceBillType("MANUAL");
		bean.setSourceBillName(getI18nFB_1f202ab());
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1f202ab() {
		if (context.get("nc.ui.uif2.I18nFB#1f202ab") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1f202ab");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1f202ab", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0000");
		bean.setDefaultValue("自制");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1f202ab", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.AddFromSourceAction getAddFrom20Action() {
		if (context.get("addFrom20Action") != null)
			return (nc.ui.pu.m21.action.AddFromSourceAction) context
					.get("addFrom20Action");
		nc.ui.pu.m21.action.AddFromSourceAction bean = new nc.ui.pu.m21.action.AddFromSourceAction();
		context.put("addFrom20Action", bean);
		bean.setSourceBillType("20");
		bean.setSourceBillName(getI18nFB_18d5ddd());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_18d5ddd() {
		if (context.get("nc.ui.uif2.I18nFB#18d5ddd") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#18d5ddd");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#18d5ddd", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0001");
		bean.setDefaultValue("请购单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#18d5ddd", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.AddFromContratAction getAddFromZ2Action() {
		if (context.get("addFromZ2Action") != null)
			return (nc.ui.pu.m21.action.AddFromContratAction) context
					.get("addFromZ2Action");
		nc.ui.pu.m21.action.AddFromContratAction bean = new nc.ui.pu.m21.action.AddFromContratAction();
		context.put("addFromZ2Action", bean);
		bean.setSourceBillType("Z2");
		bean.setSourceBillName(getI18nFB_d79ca());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_d79ca() {
		if (context.get("nc.ui.uif2.I18nFB#d79ca") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#d79ca");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#d79ca", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0004");
		bean.setDefaultValue("采购合同");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#d79ca", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.AddFromSaleOrderAction getAddFrom30Action() {
		if (context.get("addFrom30Action") != null)
			return (nc.ui.pu.m21.action.AddFromSaleOrderAction) context
					.get("addFrom30Action");
		nc.ui.pu.m21.action.AddFromSaleOrderAction bean = new nc.ui.pu.m21.action.AddFromSaleOrderAction();
		context.put("addFrom30Action", bean);
		bean.setSourceBillType("30");
		bean.setSourceBillName(getI18nFB_b6d151());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_b6d151() {
		if (context.get("nc.ui.uif2.I18nFB#b6d151") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#b6d151");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#b6d151", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0002");
		bean.setDefaultValue("直运销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#b6d151", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.AddFromSaleOrderCoopAction getAddFrom30CoopAction() {
		if (context.get("addFrom30CoopAction") != null)
			return (nc.ui.pu.m21.action.AddFromSaleOrderCoopAction) context
					.get("addFrom30CoopAction");
		nc.ui.pu.m21.action.AddFromSaleOrderCoopAction bean = new nc.ui.pu.m21.action.AddFromSaleOrderCoopAction();
		context.put("addFrom30CoopAction", bean);
		bean.setSourceBillType("30");
		bean.setSourceBillName(getI18nFB_68235c());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_68235c() {
		if (context.get("nc.ui.uif2.I18nFB#68235c") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#68235c");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#68235c", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0003");
		bean.setDefaultValue("协同销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#68235c", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.AddFromSourceAction getAddFrom49Action() {
		if (context.get("addFrom49Action") != null)
			return (nc.ui.pu.m21.action.AddFromSourceAction) context
					.get("addFrom49Action");
		nc.ui.pu.m21.action.AddFromSourceAction bean = new nc.ui.pu.m21.action.AddFromSourceAction();
		context.put("addFrom49Action", bean);
		bean.setSourceBillType("49");
		bean.setSourceBillName(getI18nFB_e988eb());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_e988eb() {
		if (context.get("nc.ui.uif2.I18nFB#e988eb") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#e988eb");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#e988eb", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0005");
		bean.setDefaultValue("库存借入单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#e988eb", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenu() {
		if (context.get("addMenu") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("addMenu");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("addMenu", bean);
		bean.setBillType("21");
		bean.setTooltip(getI18nFB_1c3530());
		bean.setActions(getManagedList4());
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setPfAddInfoLoader((nc.ui.pubapp.uif2app.actions.PfAddInfoLoader) findBeanInUIF2BeanFactory("pfAddInfoLoader"));
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1c3530() {
		if (context.get("nc.ui.uif2.I18nFB#1c3530") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1c3530");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1c3530", bean);
		bean.setResDir("common");
		bean.setResId("04004000-0001");
		bean.setDefaultValue("新增业务数据(Ctrl+N)");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1c3530", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getAddManualAction());
		list.add(getSeparatorAction());
		list.add(getAddFrom20Action());
		list.add(getAddFrom30Action());
		list.add(getAddFrom30CoopAction());
		list.add(getAddFromZ2Action());
		list.add(getAddFrom49Action());
		return list;
	}

	public nc.ui.pu.m21.view.OrderQueryDLGInitializer getOrderQryDLGInitializer() {
		if (context.get("orderQryDLGInitializer") != null)
			return (nc.ui.pu.m21.view.OrderQueryDLGInitializer) context
					.get("orderQryDLGInitializer");
		nc.ui.pu.m21.view.OrderQueryDLGInitializer bean = new nc.ui.pu.m21.view.OrderQueryDLGInitializer();
		context.put("orderQryDLGInitializer", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setDataManager((nc.ui.pubapp.uif2app.query2.model.IModelDataManager) findBeanInUIF2BeanFactory("modelDataManager"));
		bean.setQryCondDLGInitializer(getOrderQryDLGInitializer());
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("listView"));
		bean.setTemplateContainer((nc.ui.uif2.editor.QueryTemplateContainer) findBeanInUIF2BeanFactory("queryTemplateContainer"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
					.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction", bean);
		bean.setDataManager((nc.ui.pubapp.uif2app.query2.model.IModelDataManager) findBeanInUIF2BeanFactory("modelDataManager"));
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction) context
					.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.pu.m21.action.OrderEditAction) context
					.get("editAction");
		nc.ui.pu.m21.action.OrderEditAction bean = new nc.ui.pu.m21.action.OrderEditAction();
		context.put("editAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setPowercheck(true);
		bean.setPermissioncode("21");
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderReviseInApprovingAction getReviseAction() {
		if (context.get("reviseAction") != null)
			return (nc.ui.pu.m21.action.OrderReviseInApprovingAction) context
					.get("reviseAction");
		nc.ui.pu.m21.action.OrderReviseInApprovingAction bean = new nc.ui.pu.m21.action.OrderReviseInApprovingAction();
		context.put("reviseAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setInterceptor(getShowUpComponentInterceptor_1e7f5dc());
		bean.setPowercheck(true);
		bean.setPermissioncode("21");
		bean.setBillForm((nc.ui.pu.pub.view.PUBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1e7f5dc() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1e7f5dc") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1e7f5dc");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1e7f5dc",
				bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.pu.m21.action.OrderDeleteAction) context
					.get("deleteAction");
		nc.ui.pu.m21.action.OrderDeleteAction bean = new nc.ui.pu.m21.action.OrderDeleteAction();
		context.put("deleteAction", bean);
		bean.setSingleBillService(getOrderDeleteService_1be418b());
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setPowercheck(true);
		bean.setPermissioncode("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pu.m21.service.OrderDeleteService getOrderDeleteService_1be418b() {
		if (context.get("nc.ui.pu.m21.service.OrderDeleteService#1be418b") != null)
			return (nc.ui.pu.m21.service.OrderDeleteService) context
					.get("nc.ui.pu.m21.service.OrderDeleteService#1be418b");
		nc.ui.pu.m21.service.OrderDeleteService bean = new nc.ui.pu.m21.service.OrderDeleteService();
		context.put("nc.ui.pu.m21.service.OrderDeleteService#1be418b", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderCopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.pu.m21.action.OrderCopyAction) context
					.get("copyAction");
		nc.ui.pu.m21.action.OrderCopyAction bean = new nc.ui.pu.m21.action.OrderCopyAction();
		context.put("copyAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setCopyActionProcessor(getCopyActionProcessor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.pu.m21.action.OrderSaveAction) context
					.get("saveAction");
		nc.ui.pu.m21.action.OrderSaveAction bean = new nc.ui.pu.m21.action.OrderSaveAction();
		context.put("saveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setActionName("SAVEBASE");
		bean.setBillType("21");
		bean.setValidationService(getValidateService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService() {
		if (context.get("validateService") != null)
			return (nc.ui.pubapp.uif2app.validation.CompositeValidation) context
					.get("validateService");
		nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
		context.put("validateService", bean);
		bean.setValidators(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getPowerwritevalidservice());
		return list;
	}

	public nc.ui.pubapp.pub.power.PowerSaveValidateService getPowerwritevalidservice() {
		if (context.get("powerwritevalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerSaveValidateService) context
					.get("powerwritevalidservice");
		nc.ui.pubapp.pub.power.PowerSaveValidateService bean = new nc.ui.pubapp.pub.power.PowerSaveValidateService();
		context.put("powerwritevalidservice", bean);
		bean.setEditActionCode("edit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("21");
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
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.SendApproveAction getSendApproveAction() {
		if (context.get("sendApproveAction") != null)
			return (nc.ui.pu.m21.action.SendApproveAction) context
					.get("sendApproveAction");
		nc.ui.pu.m21.action.SendApproveAction bean = new nc.ui.pu.m21.action.SendApproveAction();
		context.put("sendApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setActionName("SAVE");
		bean.setBillType("21");
		bean.setPreActionNames(getManagedList6());
		bean.setValidationService(getSendpowervalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add("SAVEBASE");
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction getSaveApproveAction() {
		if (context.get("saveApproveAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction) context
					.get("saveApproveAction");
		nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction(
				getSaveAction(), getSendApproveAction());
		context.put("saveApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBtnName(getI18nFB_1c12d5e());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1c12d5e() {
		if (context.get("nc.ui.uif2.I18nFB#1c12d5e") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1c12d5e");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1c12d5e", bean);
		bean.setResDir("common");
		bean.setResId("2SCMPUB-000027");
		bean.setDefaultValue("保存提交");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1c12d5e", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.pub.action.UnSaveScriptAction getUnSendApproveAction() {
		if (context.get("unSendApproveAction") != null)
			return (nc.ui.pu.pub.action.UnSaveScriptAction) context
					.get("unSendApproveAction");
		nc.ui.pu.pub.action.UnSaveScriptAction bean = new nc.ui.pu.pub.action.UnSaveScriptAction();
		context.put("unSendApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setActionName("UNSAVEBILL");
		bean.setBillType("21");
		bean.setFilledUpInFlow(true);
		bean.setValidationService(getUnsendpowervalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getSendpowervalidservice() {
		if (context.get("sendpowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("sendpowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("sendpowervalidservice", bean);
		bean.setActionCode("commit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getUnsendpowervalidservice() {
		if (context.get("unsendpowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("unsendpowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("unsendpowervalidservice", bean);
		bean.setActionCode("uncommit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getSendApproveMenuAction() {
		if (context.get("sendApproveMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("sendApproveMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("sendApproveMenuAction", bean);
		bean.setCode("sendApproveMenuAction");
		bean.setActions(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getSendApproveAction());
		list.add(getUnSendApproveAction());
		return list;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getApprovepowervalidservice() {
		if (context.get("approvepowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("approvepowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("approvepowervalidservice", bean);
		bean.setActionCode("approve");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getUnapprovepowervalidservice() {
		if (context.get("unapprovepowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("unapprovepowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("unapprovepowervalidservice", bean);
		bean.setActionCode("unapprove");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.pu.m21.action.OrderApproveAction) context
					.get("approveAction");
		nc.ui.pu.m21.action.OrderApproveAction bean = new nc.ui.pu.m21.action.OrderApproveAction();
		context.put("approveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setActionName("APPROVE");
		bean.setBillType("21");
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setValidationService(getApprovepowervalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.pu.m21.action.OrderUnApproveAction) context
					.get("unApproveAction");
		nc.ui.pu.m21.action.OrderUnApproveAction bean = new nc.ui.pu.m21.action.OrderUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setActionName("UNAPPROVE");
		bean.setBillType("21");
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setValidationService(getUnapprovepowervalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getAuditMenuAction() {
		if (context.get("auditMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("auditMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("auditMenuAction", bean);
		bean.setCode("auditMenuAction");
		bean.setActions(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getSeparatorAction());
		list.add(getApproveStatusAction());
		return list;
	}

	public nc.ui.pu.m21.action.RefAddRowsFromSourceAction getRefAddRowsFrom20Action() {
		if (context.get("refAddRowsFrom20Action") != null)
			return (nc.ui.pu.m21.action.RefAddRowsFromSourceAction) context
					.get("refAddRowsFrom20Action");
		nc.ui.pu.m21.action.RefAddRowsFromSourceAction bean = new nc.ui.pu.m21.action.RefAddRowsFromSourceAction();
		context.put("refAddRowsFrom20Action", bean);
		bean.setSourceBillType("20");
		bean.setSourceBillName(getI18nFB_189a97c());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_189a97c() {
		if (context.get("nc.ui.uif2.I18nFB#189a97c") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#189a97c");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#189a97c", bean);
		bean.setResDir("4001002_0");
		bean.setResId("04001002-0496");
		bean.setDefaultValue("请购单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#189a97c", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.FreezeAction getFreezeAction() {
		if (context.get("freezeAction") != null)
			return (nc.ui.pu.m21.action.FreezeAction) context
					.get("freezeAction");
		nc.ui.pu.m21.action.FreezeAction bean = new nc.ui.pu.m21.action.FreezeAction();
		context.put("freezeAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.UnfreezeAction getUnfreezeAction() {
		if (context.get("unfreezeAction") != null)
			return (nc.ui.pu.m21.action.UnfreezeAction) context
					.get("unfreezeAction");
		nc.ui.pu.m21.action.UnfreezeAction bean = new nc.ui.pu.m21.action.UnfreezeAction();
		context.put("unfreezeAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.CloseAction getCloseAction() {
		if (context.get("closeAction") != null)
			return (nc.ui.pu.m21.action.CloseAction) context.get("closeAction");
		nc.ui.pu.m21.action.CloseAction bean = new nc.ui.pu.m21.action.CloseAction();
		context.put("closeAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OpenAction getOpenAction() {
		if (context.get("openAction") != null)
			return (nc.ui.pu.m21.action.OpenAction) context.get("openAction");
		nc.ui.pu.m21.action.OpenAction bean = new nc.ui.pu.m21.action.OpenAction();
		context.put("openAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.RowCloseAction getRowCloseAction() {
		if (context.get("rowCloseAction") != null)
			return (nc.ui.pu.m21.action.RowCloseAction) context
					.get("rowCloseAction");
		nc.ui.pu.m21.action.RowCloseAction bean = new nc.ui.pu.m21.action.RowCloseAction();
		context.put("rowCloseAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.RowOpenAction getRowOpenAction() {
		if (context.get("rowOpenAction") != null)
			return (nc.ui.pu.m21.action.RowOpenAction) context
					.get("rowOpenAction");
		nc.ui.pu.m21.action.RowOpenAction bean = new nc.ui.pu.m21.action.RowOpenAction();
		context.put("rowOpenAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.FileDocManageAction getAccessoriesAction() {
		if (context.get("accessoriesAction") != null)
			return (nc.ui.pubapp.uif2app.actions.FileDocManageAction) context
					.get("accessoriesAction");
		nc.ui.pubapp.uif2app.actions.FileDocManageAction bean = new nc.ui.pubapp.uif2app.actions.FileDocManageAction();
		context.put("accessoriesAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.AssistAction getAssistMenuAction() {
		if (context.get("assistMenuAction") != null)
			return (nc.ui.pu.m21.action.AssistAction) context
					.get("assistMenuAction");
		nc.ui.pu.m21.action.AssistAction bean = new nc.ui.pu.m21.action.AssistAction();
		context.put("assistMenuAction", bean);
		bean.setActions(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getFreezeAction());
		list.add(getUnfreezeAction());
		list.add(getCloseAction());
		list.add(getOpenAction());
		list.add(getRowCloseAction());
		list.add(getRowOpenAction());
		list.add(getSeparatorAction());
		list.add(getAccessoriesAction());
		return list;
	}

	public nc.ui.pu.m21.action.AssistAction getAssistMenuActionForCard() {
		if (context.get("assistMenuActionForCard") != null)
			return (nc.ui.pu.m21.action.AssistAction) context
					.get("assistMenuActionForCard");
		nc.ui.pu.m21.action.AssistAction bean = new nc.ui.pu.m21.action.AssistAction();
		context.put("assistMenuActionForCard", bean);
		bean.setActions(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getRefAddRowsFrom20Action());
		return list;
	}

	public nc.ui.pu.m21.action.KitQueryAction getKitQueryAction() {
		if (context.get("kitQueryAction") != null)
			return (nc.ui.pu.m21.action.KitQueryAction) context
					.get("kitQueryAction");
		nc.ui.pu.m21.action.KitQueryAction bean = new nc.ui.pu.m21.action.KitQueryAction();
		context.put("kitQueryAction", bean);
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.BillSaleNumAction getBillSaleNumAction() {
		if (context.get("billSaleNumAction") != null)
			return (nc.ui.pu.m21.action.BillSaleNumAction) context
					.get("billSaleNumAction");
		nc.ui.pu.m21.action.BillSaleNumAction bean = new nc.ui.pu.m21.action.BillSaleNumAction();
		context.put("billSaleNumAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.QueryDefaultPriceAction getQueryDefPriAction() {
		if (context.get("queryDefPriAction") != null)
			return (nc.ui.pu.m21.action.QueryDefaultPriceAction) context
					.get("queryDefPriAction");
		nc.ui.pu.m21.action.QueryDefaultPriceAction bean = new nc.ui.pu.m21.action.QueryDefaultPriceAction();
		context.put("queryDefPriAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.QueryCoopPriceAction getQueryCoopPriAction() {
		if (context.get("queryCoopPriAction") != null)
			return (nc.ui.pu.m21.action.QueryCoopPriceAction) context
					.get("queryCoopPriAction");
		nc.ui.pu.m21.action.QueryCoopPriceAction bean = new nc.ui.pu.m21.action.QueryCoopPriceAction();
		context.put("queryCoopPriAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.BillGrossProfitAction getBillGrossProfitAction() {
		if (context.get("billGrossProfitAction") != null)
			return (nc.ui.pu.m21.action.BillGrossProfitAction) context
					.get("billGrossProfitAction");
		nc.ui.pu.m21.action.BillGrossProfitAction bean = new nc.ui.pu.m21.action.BillGrossProfitAction();
		context.put("billGrossProfitAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.ApQueryAction getApAction() {
		if (context.get("apAction") != null)
			return (nc.ui.pu.m21.action.ApQueryAction) context.get("apAction");
		nc.ui.pu.m21.action.ApQueryAction bean = new nc.ui.pu.m21.action.ApQueryAction();
		context.put("apAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.PayExecStatAction getPayExecStatAction() {
		if (context.get("payExecStatAction") != null)
			return (nc.ui.pu.m21.action.PayExecStatAction) context
					.get("payExecStatAction");
		nc.ui.pu.m21.action.PayExecStatAction bean = new nc.ui.pu.m21.action.PayExecStatAction();
		context.put("payExecStatAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.pub.action.PULinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.pu.pub.action.PULinkQueryAction) context
					.get("linkQueryAction");
		nc.ui.pu.pub.action.PULinkQueryAction bean = new nc.ui.pu.pub.action.PULinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillType("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderATPAction getATPAction() {
		if (context.get("ATPAction") != null)
			return (nc.ui.pu.m21.action.OrderATPAction) context
					.get("ATPAction");
		nc.ui.pu.m21.action.OrderATPAction bean = new nc.ui.pu.m21.action.OrderATPAction();
		context.put("ATPAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getApproveStatusAction() {
		if (context.get("approveStatusAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction) context
					.get("approveStatusAction");
		nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
		context.put("approveStatusAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBtnName(getI18nFB_d61c11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_d61c11() {
		if (context.get("nc.ui.uif2.I18nFB#d61c11") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#d61c11");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#d61c11", bean);
		bean.setResDir("4001002_0");
		bean.setResId("04001002-0579");
		bean.setDefaultValue("查看审批意见");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#d61c11", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m21.action.OrderLinkQueryMppAction getLinkMppAction() {
		if (context.get("linkMppAction") != null)
			return (nc.ui.pu.m21.action.OrderLinkQueryMppAction) context
					.get("linkMppAction");
		nc.ui.pu.m21.action.OrderLinkQueryMppAction bean = new nc.ui.pu.m21.action.OrderLinkQueryMppAction();
		context.put("linkMppAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.InterNProAction getInterNNProAction() {
		if (context.get("InterNNProAction") != null)
			return (nc.ui.pu.m21.action.InterNProAction) context
					.get("InterNNProAction");
		nc.ui.pu.m21.action.InterNProAction bean = new nc.ui.pu.m21.action.InterNProAction();
		context.put("InterNNProAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.LinkBillMenuAction getLinkBillMenuAction() {
		if (context.get("linkBillMenuAction") != null)
			return (nc.ui.pu.m21.action.LinkBillMenuAction) context
					.get("linkBillMenuAction");
		nc.ui.pu.m21.action.LinkBillMenuAction bean = new nc.ui.pu.m21.action.LinkBillMenuAction();
		context.put("linkBillMenuAction", bean);
		bean.setActions(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getLinkQueryAction());
		list.add(getSeparatorAction());
		list.add(getLinkMppAction());
		list.add(getKitQueryAction());
		list.add(getATPAction());
		list.add(getBillSaleNumAction());
		list.add(getBillGrossProfitAction());
		list.add(getApAction());
		list.add(getPayExecStatAction());
		list.add(getInterNNProAction());
		return list;
	}

	public nc.ui.pu.m21.action.LinkBillMenuAction getLinkBillMenuActionForCard() {
		if (context.get("linkBillMenuActionForCard") != null)
			return (nc.ui.pu.m21.action.LinkBillMenuAction) context
					.get("linkBillMenuActionForCard");
		nc.ui.pu.m21.action.LinkBillMenuAction bean = new nc.ui.pu.m21.action.LinkBillMenuAction();
		context.put("linkBillMenuActionForCard", bean);
		bean.setActions(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getKitQueryAction());
		list.add(getATPAction());
		list.add(getBillSaleNumAction());
		list.add(getBillGrossProfitAction());
		list.add(getApAction());
		list.add(getPayExecStatAction());
		return list;
	}

	public nc.ui.pu.m21.action.ReceivePlanAction getReceivePlanAction() {
		if (context.get("receivePlanAction") != null)
			return (nc.ui.pu.m21.action.ReceivePlanAction) context
					.get("receivePlanAction");
		nc.ui.pu.m21.action.ReceivePlanAction bean = new nc.ui.pu.m21.action.ReceivePlanAction();
		context.put("receivePlanAction", bean);
		bean.setBillForm((nc.ui.uif2.editor.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OrderPayPlanAction getPayplanAction() {
		if (context.get("payplanAction") != null)
			return (nc.ui.pu.m21.action.OrderPayPlanAction) context
					.get("payplanAction");
		nc.ui.pu.m21.action.OrderPayPlanAction bean = new nc.ui.pu.m21.action.OrderPayPlanAction();
		context.put("payplanAction", bean);
		bean.setBillForm((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.PushCoopSaleOrderAction getPushCoop30Action() {
		if (context.get("pushCoop30Action") != null)
			return (nc.ui.pu.m21.action.PushCoopSaleOrderAction) context
					.get("pushCoop30Action");
		nc.ui.pu.m21.action.PushCoopSaleOrderAction bean = new nc.ui.pu.m21.action.PushCoopSaleOrderAction();
		context.put("pushCoop30Action", bean);
		bean.setModel((nc.ui.uif2.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.TransportStatusAction getTransportStatusAction() {
		if (context.get("transportStatusAction") != null)
			return (nc.ui.pu.m21.action.TransportStatusAction) context
					.get("transportStatusAction");
		nc.ui.pu.m21.action.TransportStatusAction bean = new nc.ui.pu.m21.action.TransportStatusAction();
		context.put("transportStatusAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.RelateFuncMenuAction getRelateFunMenuAction() {
		if (context.get("relateFunMenuAction") != null)
			return (nc.ui.pu.m21.action.RelateFuncMenuAction) context
					.get("relateFunMenuAction");
		nc.ui.pu.m21.action.RelateFuncMenuAction bean = new nc.ui.pu.m21.action.RelateFuncMenuAction();
		context.put("relateFunMenuAction", bean);
		bean.setActions(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getQueryDefPriAction());
		list.add(getReceivePlanAction());
		list.add(getPayplanAction());
		list.add(getPushCoop30Action());
		list.add(getTransportStatusAction());
		return list;
	}

	public nc.ui.pu.m21.action.RelateFuncMenuAction getRelateFunMenuActionForCard() {
		if (context.get("relateFunMenuActionForCard") != null)
			return (nc.ui.pu.m21.action.RelateFuncMenuAction) context
					.get("relateFunMenuActionForCard");
		nc.ui.pu.m21.action.RelateFuncMenuAction bean = new nc.ui.pu.m21.action.RelateFuncMenuAction();
		context.put("relateFunMenuActionForCard", bean);
		bean.setActions(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getQueryDefPriAction());
		list.add(getPayplanAction());
		list.add(getQueryCoopPriAction());
		return list;
	}

	public nc.ui.pu.m21.action.processor.OrderPrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.pu.m21.action.processor.OrderPrintProcessor) context
					.get("printProcessor");
		nc.ui.pu.m21.action.processor.OrderPrintProcessor bean = new nc.ui.pu.m21.action.processor.OrderPrintProcessor();
		context.put("printProcessor", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.PrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.pu.m21.action.PrintAction) context
					.get("previewAction");
		nc.ui.pu.m21.action.PrintAction bean = new nc.ui.pu.m21.action.PrintAction();
		context.put("previewAction", bean);
		bean.setPreview(true);
		bean.setNodeKey("4004040002");
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setDataSplit(getPrintProcessor());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.PrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.pu.m21.action.PrintAction) context.get("printAction");
		nc.ui.pu.m21.action.PrintAction bean = new nc.ui.pu.m21.action.PrintAction();
		context.put("printAction", bean);
		bean.setPreview(false);
		bean.setNodeKey("4004040002");
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setDataSplit(getPrintProcessor());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.OutputSpecialProcessAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pu.m21.action.OutputSpecialProcessAction) context
					.get("outputAction");
		nc.ui.pu.m21.action.OutputSpecialProcessAction bean = new nc.ui.pu.m21.action.OutputSpecialProcessAction();
		context.put("outputAction", bean);
		bean.setNodeKey("4004040002");
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setParent((java.awt.Container) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m21.action.CombineShowAction getCombineShowAction() {
		if (context.get("combineShowAction") != null)
			return (nc.ui.pu.m21.action.CombineShowAction) context
					.get("combineShowAction");
		nc.ui.pu.m21.action.CombineShowAction bean = new nc.ui.pu.m21.action.CombineShowAction();
		context.put("combineShowAction", bean);
		bean.setOrderForm((nc.ui.pu.pub.view.PUBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction() {
		if (context.get("printCountQueryAction") != null)
			return (nc.ui.scmpub.action.SCMPrintCountQueryAction) context
					.get("printCountQueryAction");
		nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
		context.put("printCountQueryAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBilldateFieldName("dbilldate");
		bean.setBillType("21");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMenuAction() {
		if (context.get("printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMenuAction", bean);
		bean.setCode("printMenuAction");
		bean.setActions(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getPrintCountQueryAction());
		list.add(getSeparatorAction());
		list.add(getCombineShowAction());
		return list;
	}

	public nc.ui.uif2.actions.IMGroupChatAction getIMAction() {
		if (context.get("IMAction") != null)
			return (nc.ui.uif2.actions.IMGroupChatAction) context
					.get("IMAction");
		nc.ui.uif2.actions.IMGroupChatAction bean = new nc.ui.uif2.actions.IMGroupChatAction();
		context.put("IMAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}

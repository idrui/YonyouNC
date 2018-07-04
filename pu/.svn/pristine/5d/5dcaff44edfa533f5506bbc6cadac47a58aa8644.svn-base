package nc.ui.pu.m20.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class prayarrange_config extends AbstractJavaBeanDefinition {
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

	public nc.ui.pu.m20.model.PrayarrangeModelService getBatchModelService() {
		if (context.get("batchModelService") != null)
			return (nc.ui.pu.m20.model.PrayarrangeModelService) context
					.get("batchModelService");
		nc.ui.pu.m20.model.PrayarrangeModelService bean = new nc.ui.pu.m20.model.PrayarrangeModelService();
		context.put("batchModelService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory getBoadatorfactory() {
		if (context.get("boadatorfactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory) context
					.get("boadatorfactory");
		nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory();
		context.put("boadatorfactory", bean);
		bean.setId_field("pk_praybill_b");
		bean.setCode_field("vbillcode");
		bean.setName_field("vbillcode");
		bean.setPid_field("pk_praybill");
		bean.setPk_org_field("pk_org");
		bean.setPk_group_field("pk_group");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.model.DefaultBatchValidationService getBatchValidateSerice() {
		if (context.get("batchValidateSerice") != null)
			return (nc.ui.uif2.model.DefaultBatchValidationService) context
					.get("batchValidateSerice");
		nc.ui.uif2.model.DefaultBatchValidationService bean = new nc.ui.uif2.model.DefaultBatchValidationService();
		context.put("batchValidateSerice", bean);
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.model.PrayarrangeBatchBillTableModel getBatchBillTableModel() {
		if (context.get("batchBillTableModel") != null)
			return (nc.ui.pu.m20.model.PrayarrangeBatchBillTableModel) context
					.get("batchBillTableModel");
		nc.ui.pu.m20.model.PrayarrangeBatchBillTableModel bean = new nc.ui.pu.m20.model.PrayarrangeBatchBillTableModel();
		context.put("batchBillTableModel", bean);
		bean.setContext(getContext());
		bean.setService(getBatchModelService());
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
		bean.setValidationService(getBatchValidateSerice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context
					.get("modelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setService(getBatchModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVbillcodeMediator() {
		if (context.get("vbillcodeMediator") != null)
			return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
					.get("vbillcodeMediator");
		nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
		context.put("vbillcodeMediator", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setSrcBillIdField("pk_praybill");
		bean.setSrcBillNOField("vbillcode");
		bean.setSrcBillTypeField("vtrantypecode");
		bean.setSrcBillTypeFieldPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.FunNodeClosingHandler getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.uif2.FunNodeClosingHandler) context
					.get("ClosingListener");
		nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
		context.put("ClosingListener", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setSaveaction(getSaveAction());
		bean.setCancelaction(getCancelAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.view.PrayarrangeVOValueAdapter getComponentValueManager() {
		if (context.get("componentValueManager") != null)
			return (nc.ui.pu.m20.view.PrayarrangeVOValueAdapter) context
					.get("componentValueManager");
		nc.ui.pu.m20.view.PrayarrangeVOValueAdapter bean = new nc.ui.pu.m20.view.PrayarrangeVOValueAdapter();
		context.put("componentValueManager", bean);
		bean.setBodyVOName("nc.vo.pu.m20.entity.PrayarrangeViewVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.view.PrayarrangeBillForm getList() {
		if (context.get("list") != null)
			return (nc.ui.pu.m20.view.PrayarrangeBillForm) context.get("list");
		nc.ui.pu.m20.view.PrayarrangeBillForm bean = new nc.ui.pu.m20.view.PrayarrangeBillForm();
		context.put("list", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setComponentValueManager(getComponentValueManager());
		bean.setVoClassName("nc.vo.pu.m20.entity.PrayarrangeViewVO");
		bean.setIsBodyAutoAddLine(false);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.editor.arrange.beforeedit.PrayarrangeBodyBeforeEditHandler getCardBodyBeforeEdit() {
		if (context.get("cardBodyBeforeEdit") != null)
			return (nc.ui.pu.m20.editor.arrange.beforeedit.PrayarrangeBodyBeforeEditHandler) context
					.get("cardBodyBeforeEdit");
		nc.ui.pu.m20.editor.arrange.beforeedit.PrayarrangeBodyBeforeEditHandler bean = new nc.ui.pu.m20.editor.arrange.beforeedit.PrayarrangeBodyBeforeEditHandler();
		context.put("cardBodyBeforeEdit", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.editor.arrange.afteredit.PrayarrangeBodyAfterEditHandler getCardBodyAfterEdit() {
		if (context.get("cardBodyAfterEdit") != null)
			return (nc.ui.pu.m20.editor.arrange.afteredit.PrayarrangeBodyAfterEditHandler) context
					.get("cardBodyAfterEdit");
		nc.ui.pu.m20.editor.arrange.afteredit.PrayarrangeBodyAfterEditHandler bean = new nc.ui.pu.m20.editor.arrange.afteredit.PrayarrangeBodyAfterEditHandler();
		context.put("cardBodyAfterEdit", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
		if (context.get("appEventHandlerMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("appEventHandlerMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("appEventHandlerMediator", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setHandlerMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList0());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
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
		list.add(getCardBodyAfterEdit());
		return list;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setTangramLayoutRoot(getCNode_2349a());
		bean.setActions(getManagedList2());
		bean.setEditActions(getManagedList3());
		bean.setModel(getBatchBillTableModel());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_2349a() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#2349a") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#2349a");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#2349a", bean);
		bean.setComponent(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getArrangeAction());
		list.add(getCancelArrangeAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		return list;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getLotsArrange());
		list.add(getSaveAction());
		list.add(getCancelAction());
		return list;
	}

	public nc.ui.pu.m20.query.PrayarrangeQueryDLGInitializer getArrangeQryDLGInitializer() {
		if (context.get("arrangeQryDLGInitializer") != null)
			return (nc.ui.pu.m20.query.PrayarrangeQueryDLGInitializer) context
					.get("arrangeQryDLGInitializer");
		nc.ui.pu.m20.query.PrayarrangeQueryDLGInitializer bean = new nc.ui.pu.m20.query.PrayarrangeQueryDLGInitializer();
		context.put("arrangeQryDLGInitializer", bean);
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
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getArrangeQryDLGInitializer());
		bean.setModel(getBatchBillTableModel());
		bean.setHasQueryArea(false);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.LotsArrangePraybillsAction getLotsArrange() {
		if (context.get("LotsArrange") != null)
			return (nc.ui.pu.m20.action.LotsArrangePraybillsAction) context
					.get("LotsArrange");
		nc.ui.pu.m20.action.LotsArrangePraybillsAction bean = new nc.ui.pu.m20.action.LotsArrangePraybillsAction();
		context.put("LotsArrange", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		bean.setDaoService(getBatchModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.arrange.PrayarrangeArrangeAction getArrangeAction() {
		if (context.get("arrangeAction") != null)
			return (nc.ui.pu.m20.action.arrange.PrayarrangeArrangeAction) context
					.get("arrangeAction");
		nc.ui.pu.m20.action.arrange.PrayarrangeArrangeAction bean = new nc.ui.pu.m20.action.arrange.PrayarrangeArrangeAction();
		context.put("arrangeAction", bean);
		bean.setBtnName(getI18nFB_cb8f65());
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_cb8f65() {
		if (context.get("nc.ui.uif2.I18nFB#cb8f65") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#cb8f65");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#cb8f65", bean);
		bean.setResDir("4004020_0");
		bean.setResId("04004020-0115");
		bean.setDefaultValue("安排");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#cb8f65", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m20.action.arrange.PrayarrangeCancelArrangeAction getCancelArrangeAction() {
		if (context.get("cancelArrangeAction") != null)
			return (nc.ui.pu.m20.action.arrange.PrayarrangeCancelArrangeAction) context
					.get("cancelArrangeAction");
		nc.ui.pu.m20.action.arrange.PrayarrangeCancelArrangeAction bean = new nc.ui.pu.m20.action.arrange.PrayarrangeCancelArrangeAction();
		context.put("cancelArrangeAction", bean);
		bean.setBtnName(getI18nFB_333465());
		bean.setCode("CancelArrange");
		bean.setDataManager(getModelDataManager());
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_333465() {
		if (context.get("nc.ui.uif2.I18nFB#333465") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#333465");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#333465", bean);
		bean.setResDir("4004020_0");
		bean.setResId("04004020-0116");
		bean.setDefaultValue("取消安排");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#333465", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
					.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction", bean);
		bean.setDataManager(getModelDataManager());
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.arrange.PrayarrangeSaveRefreshAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.pu.m20.action.arrange.PrayarrangeSaveRefreshAction) context
					.get("saveAction");
		nc.ui.pu.m20.action.arrange.PrayarrangeSaveRefreshAction bean = new nc.ui.pu.m20.action.arrange.PrayarrangeSaveRefreshAction();
		context.put("saveAction", bean);
		bean.setBtnName(getI18nFB_1b50a62());
		bean.setDataManager(getModelDataManager());
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1b50a62() {
		if (context.get("nc.ui.uif2.I18nFB#1b50a62") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1b50a62");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1b50a62", bean);
		bean.setResDir("4004020_0");
		bean.setResId("04004020-0117");
		bean.setDefaultValue("确认");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1b50a62", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction) context
					.get("cancelAction");
		nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction bean = new nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
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

}

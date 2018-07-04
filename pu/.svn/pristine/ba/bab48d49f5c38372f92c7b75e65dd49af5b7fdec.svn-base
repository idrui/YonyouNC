package nc.ui.pu.m20.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class praybill_action extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor() {
		if (context.get("formInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("formInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("formInterceptor", bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
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

	public nc.ui.pu.m20.action.PraybillAddAction getAddManualAction() {
		if (context.get("addManualAction") != null)
			return (nc.ui.pu.m20.action.PraybillAddAction) context
					.get("addManualAction");
		nc.ui.pu.m20.action.PraybillAddAction bean = new nc.ui.pu.m20.action.PraybillAddAction();
		context.put("addManualAction", bean);
		bean.setBtnName(getI18nFB_18b6130());
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setInterceptor(getCompositeActionInterceptor_1716d34());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_18b6130() {
		if (context.get("nc.ui.uif2.I18nFB#18b6130") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#18b6130");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#18b6130", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0000");
		bean.setDefaultValue("自制");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#18b6130", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_1716d34() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1716d34") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1716d34");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1716d34",
				bean);
		bean.setInterceptors(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getShowUpComponentInterceptor_132937e());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_132937e() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#132937e") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#132937e");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#132937e",
				bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.AddFrom422xAction getAddFrom422xAction() {
		if (context.get("addFrom422xAction") != null)
			return (nc.ui.pu.m20.action.AddFrom422xAction) context
					.get("addFrom422xAction");
		nc.ui.pu.m20.action.AddFrom422xAction bean = new nc.ui.pu.m20.action.AddFrom422xAction();
		context.put("addFrom422xAction", bean);
		bean.setSourceBillType("422X");
		bean.setSourceBillName(getI18nFB_1b87ae4());
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor) findBeanInUIF2BeanFactory("transferViewProcessor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1b87ae4() {
		if (context.get("nc.ui.uif2.I18nFB#1b87ae4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1b87ae4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1b87ae4", bean);
		bean.setResDir("common");
		bean.setResId("14004000-0091");
		bean.setDefaultValue("物资需求申请单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1b87ae4", product);
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
		bean.setBillType("20");
		bean.setTooltip(getI18nFB_8c4e31());
		bean.setActions(getManagedList1());
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setPfAddInfoLoader((nc.ui.pubapp.uif2app.actions.PfAddInfoLoader) findBeanInUIF2BeanFactory("pfAddInfoLoader"));
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_8c4e31() {
		if (context.get("nc.ui.uif2.I18nFB#8c4e31") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#8c4e31");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#8c4e31", bean);
		bean.setResDir("common");
		bean.setResId("04004000-0001");
		bean.setDefaultValue("新增业务数据(Ctrl+N)");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#8c4e31", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getAddManualAction());
		list.add(getSeparatorAction());
		list.add(getAddFrom422xAction());
		list.add(getSeparatorAction());
		list.add(getAddFromDW60Action());
		list.add(getAddFromDW70Action());
		list.add(getSeparatorAction());
		list.add(getAddFromDW66Action());
		return list;
	}

	public nc.ui.pu.m20.action.PraybillEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.pu.m20.action.PraybillEditAction) context
					.get("editAction");
		nc.ui.pu.m20.action.PraybillEditAction bean = new nc.ui.pu.m20.action.PraybillEditAction();
		context.put("editAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setInterceptor(getShowUpComponentInterceptor_1617966());
		bean.setPowercheck(true);
		bean.setPermissioncode("20");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1617966() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1617966") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1617966");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1617966",
				bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PrayBillReviseInApprovingAction getReviseAction() {
		if (context.get("reviseAction") != null)
			return (nc.ui.pu.m20.action.PrayBillReviseInApprovingAction) context
					.get("reviseAction");
		nc.ui.pu.m20.action.PrayBillReviseInApprovingAction bean = new nc.ui.pu.m20.action.PrayBillReviseInApprovingAction();
		context.put("reviseAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setInterceptor(getShowUpComponentInterceptor_694041());
		bean.setPowercheck(true);
		bean.setPermissioncode("20");
		bean.setBillForm((nc.ui.pu.pub.view.PUBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_694041() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#694041") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#694041");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#694041",
				bean);
		bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.pu.m20.action.PraybillDeleteAction) context
					.get("deleteAction");
		nc.ui.pu.m20.action.PraybillDeleteAction bean = new nc.ui.pu.m20.action.PraybillDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setActionName("DISCARD");
		bean.setBillType("20");
		bean.setValidationService(getPowerDeleteservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerDeleteservice() {
		if (context.get("powerDeleteservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerDeleteservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerDeleteservice", bean);
		bean.setActionCode("delete");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("20");
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
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setCopyActionProcessor(getCopyActionProcessor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.CopyActionProcessor getCopyActionProcessor() {
		if (context.get("copyActionProcessor") != null)
			return (nc.ui.pu.m20.action.CopyActionProcessor) context
					.get("copyActionProcessor");
		nc.ui.pu.m20.action.CopyActionProcessor bean = new nc.ui.pu.m20.action.CopyActionProcessor();
		context.put("copyActionProcessor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.query.PraybillQueryDLGInitializer getPraybillQryDLGInitializer() {
		if (context.get("praybillQryDLGInitializer") != null)
			return (nc.ui.pu.m20.query.PraybillQueryDLGInitializer) context
					.get("praybillQryDLGInitializer");
		nc.ui.pu.m20.query.PraybillQueryDLGInitializer bean = new nc.ui.pu.m20.query.PraybillQueryDLGInitializer();
		context.put("praybillQryDLGInitializer", bean);
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
		bean.setQryCondDLGInitializer(getPraybillQryDLGInitializer());
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

	public nc.ui.pu.m20.action.PraybillSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.pu.m20.action.PraybillSaveAction) context
					.get("saveAction");
		nc.ui.pu.m20.action.PraybillSaveAction bean = new nc.ui.pu.m20.action.PraybillSaveAction();
		context.put("saveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setActionName("SAVEBASE");
		bean.setBillType("20");
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
		bean.setValidators(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getPowervalidservice());
		return list;
	}

	public nc.ui.pubapp.pub.power.PowerSaveValidateService getPowervalidservice() {
		if (context.get("powervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerSaveValidateService) context
					.get("powervalidservice");
		nc.ui.pubapp.pub.power.PowerSaveValidateService bean = new nc.ui.pubapp.pub.power.PowerSaveValidateService();
		context.put("powervalidservice", bean);
		bean.setEditActionCode("edit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("20");
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

	public nc.ui.pu.m20.action.PraybillSendApproveAction getSendApproveAction() {
		if (context.get("sendApproveAction") != null)
			return (nc.ui.pu.m20.action.PraybillSendApproveAction) context
					.get("sendApproveAction");
		nc.ui.pu.m20.action.PraybillSendApproveAction bean = new nc.ui.pu.m20.action.PraybillSendApproveAction();
		context.put("sendApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setPreActionNames(getManagedList3());
		bean.setActionName("SAVE");
		bean.setBillType("20");
		bean.setFilledUpInFlow(true);
		bean.setValidationService(getSendpowervalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
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
		bean.setBtnName(getI18nFB_1dc00a3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1dc00a3() {
		if (context.get("nc.ui.uif2.I18nFB#1dc00a3") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1dc00a3");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1dc00a3", bean);
		bean.setResDir("common");
		bean.setResId("2SCMPUB-000027");
		bean.setDefaultValue("保存提交");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1dc00a3", product);
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
		bean.setBillType("20");
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
		bean.setPermissionCode("20");
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
		bean.setPermissionCode("20");
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
		bean.setActions(getManagedList4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getSendApproveAction());
		list.add(getUnSendApproveAction());
		return list;
	}

	public nc.ui.pu.m20.action.PraybillApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.pu.m20.action.PraybillApproveAction) context
					.get("approveAction");
		nc.ui.pu.m20.action.PraybillApproveAction bean = new nc.ui.pu.m20.action.PraybillApproveAction();
		context.put("approveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setFilledUpInFlow(false);
		bean.setActionName("APPROVE");
		bean.setBillType("20");
		bean.setValidationService(getPowerApproveService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerApproveService() {
		if (context.get("powerApproveService") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerApproveService");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerApproveService", bean);
		bean.setActionCode("approve");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("20");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.pu.m20.action.PraybillUnApproveAction) context
					.get("unApproveAction");
		nc.ui.pu.m20.action.PraybillUnApproveAction bean = new nc.ui.pu.m20.action.PraybillUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setFilledUpInFlow(false);
		bean.setActionName("UNAPPROVE");
		bean.setBillType("20");
		bean.setValidationService(getPowerUNApproveService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerUNApproveService() {
		if (context.get("powerUNApproveService") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerUNApproveService");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerUNApproveService", bean);
		bean.setActionCode("unapprove");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("20");
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
		bean.setActions(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getSeparatorAction_adfc72());
		list.add(getApproveStatusAction());
		return list;
	}

	private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_adfc72() {
		if (context.get("nc.funcnode.ui.action.SeparatorAction#adfc72") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("nc.funcnode.ui.action.SeparatorAction#adfc72");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("nc.funcnode.ui.action.SeparatorAction#adfc72", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillCloseAction getCloseAction() {
		if (context.get("closeAction") != null)
			return (nc.ui.pu.m20.action.PraybillCloseAction) context
					.get("closeAction");
		nc.ui.pu.m20.action.PraybillCloseAction bean = new nc.ui.pu.m20.action.PraybillCloseAction();
		context.put("closeAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillOpenAction getOpenAction() {
		if (context.get("openAction") != null)
			return (nc.ui.pu.m20.action.PraybillOpenAction) context
					.get("openAction");
		nc.ui.pu.m20.action.PraybillOpenAction bean = new nc.ui.pu.m20.action.PraybillOpenAction();
		context.put("openAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillCloseLineAction getRowCloseAction() {
		if (context.get("rowCloseAction") != null)
			return (nc.ui.pu.m20.action.PraybillCloseLineAction) context
					.get("rowCloseAction");
		nc.ui.pu.m20.action.PraybillCloseLineAction bean = new nc.ui.pu.m20.action.PraybillCloseLineAction();
		context.put("rowCloseAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.BillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillOpenLineAction getRowOpenAction() {
		if (context.get("rowOpenAction") != null)
			return (nc.ui.pu.m20.action.PraybillOpenLineAction) context
					.get("rowOpenAction");
		nc.ui.pu.m20.action.PraybillOpenLineAction bean = new nc.ui.pu.m20.action.PraybillOpenLineAction();
		context.put("rowOpenAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
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

	public nc.ui.pu.m20.action.PraybillAssistAction getAssistMenuAction() {
		if (context.get("assistMenuAction") != null)
			return (nc.ui.pu.m20.action.PraybillAssistAction) context
					.get("assistMenuAction");
		nc.ui.pu.m20.action.PraybillAssistAction bean = new nc.ui.pu.m20.action.PraybillAssistAction();
		context.put("assistMenuAction", bean);
		bean.setActions(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getCloseAction());
		list.add(getOpenAction());
		list.add(getRowCloseAction());
		list.add(getRowOpenAction());
		list.add(getSeparatorAction_98c5a5());
		list.add(getAccessoriesAction());
		return list;
	}

	private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_98c5a5() {
		if (context.get("nc.funcnode.ui.action.SeparatorAction#98c5a5") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("nc.funcnode.ui.action.SeparatorAction#98c5a5");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("nc.funcnode.ui.action.SeparatorAction#98c5a5", bean);
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
		bean.setBillType("20");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillATPAction getATPAction() {
		if (context.get("ATPAction") != null)
			return (nc.ui.pu.m20.action.PraybillATPAction) context
					.get("ATPAction");
		nc.ui.pu.m20.action.PraybillATPAction bean = new nc.ui.pu.m20.action.PraybillATPAction();
		context.put("ATPAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillPriceAction getPriceAction() {
		if (context.get("priceAction") != null)
			return (nc.ui.pu.m20.action.PraybillPriceAction) context
					.get("priceAction");
		nc.ui.pu.m20.action.PraybillPriceAction bean = new nc.ui.pu.m20.action.PraybillPriceAction();
		context.put("priceAction", bean);
		bean.setContext((nc.vo.uif2.LoginContext) findBeanInUIF2BeanFactory("context"));
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setNodeKey("40040200PRICE");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.pub.action.QueryOnhandAction getATPQueryAction() {
		if (context.get("ATPQueryAction") != null)
			return (nc.ui.pu.pub.action.QueryOnhandAction) context
					.get("ATPQueryAction");
		nc.ui.pu.pub.action.QueryOnhandAction bean = new nc.ui.pu.pub.action.QueryOnhandAction();
		context.put("ATPQueryAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setForm((nc.ui.pu.pub.view.PUBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.pubapp.uif2app.view.BillListView) findBeanInUIF2BeanFactory("listView"));
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
		bean.setBtnName(getI18nFB_1d7df3a());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1d7df3a() {
		if (context.get("nc.ui.uif2.I18nFB#1d7df3a") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1d7df3a");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1d7df3a", bean);
		bean.setResDir("4001002_0");
		bean.setResId("04001002-0579");
		bean.setDefaultValue("查看审批意见");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1d7df3a", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pu.m20.action.PraybillLinkQueryMppAction getLinkMppAction() {
		if (context.get("linkMppAction") != null)
			return (nc.ui.pu.m20.action.PraybillLinkQueryMppAction) context
					.get("linkMppAction");
		nc.ui.pu.m20.action.PraybillLinkQueryMppAction bean = new nc.ui.pu.m20.action.PraybillLinkQueryMppAction();
		context.put("linkMppAction", bean);
		bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setList((nc.ui.uif2.editor.BillListView) findBeanInUIF2BeanFactory("listView"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillLinkQueryMenuAction getLinkQueryMenuAction() {
		if (context.get("linkQueryMenuAction") != null)
			return (nc.ui.pu.m20.action.PraybillLinkQueryMenuAction) context
					.get("linkQueryMenuAction");
		nc.ui.pu.m20.action.PraybillLinkQueryMenuAction bean = new nc.ui.pu.m20.action.PraybillLinkQueryMenuAction();
		context.put("linkQueryMenuAction", bean);
		bean.setActions(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getLinkQueryAction());
		list.add(getLinkMppAction());
		list.add(getATPAction());
		list.add(getPriceAction());
		list.add(getATPQueryAction());
		return list;
	}

	public nc.ui.pu.m20.action.PraybillLinkQueryMenuAction getEditLinkQueryMenuAction() {
		if (context.get("editLinkQueryMenuAction") != null)
			return (nc.ui.pu.m20.action.PraybillLinkQueryMenuAction) context
					.get("editLinkQueryMenuAction");
		nc.ui.pu.m20.action.PraybillLinkQueryMenuAction bean = new nc.ui.pu.m20.action.PraybillLinkQueryMenuAction();
		context.put("editLinkQueryMenuAction", bean);
		bean.setActions(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getATPAction());
		list.add(getATPQueryAction());
		return list;
	}

	public nc.ui.pu.m20.action.processor.PraybillPrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.pu.m20.action.processor.PraybillPrintProcessor) context
					.get("printProcessor");
		nc.ui.pu.m20.action.processor.PraybillPrintProcessor bean = new nc.ui.pu.m20.action.processor.PraybillPrintProcessor();
		context.put("printProcessor", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("previewAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("previewAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setNodeKey("4004020002");
		bean.setPreview(true);
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("printAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("printAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setNodeKey("4004020002");
		bean.setPreview(false);
		bean.setBeforePrintDataProcess(getPrintProcessor());
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
		bean.setNodeKey("4004020002");
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setParent((java.awt.Container) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.PraybillCombineShowAction getCombineShowAction() {
		if (context.get("combineShowAction") != null)
			return (nc.ui.pu.m20.action.PraybillCombineShowAction) context
					.get("combineShowAction");
		nc.ui.pu.m20.action.PraybillCombineShowAction bean = new nc.ui.pu.m20.action.PraybillCombineShowAction();
		context.put("combineShowAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.pubapp.uif2app.view.ShowUpableBillForm) findBeanInUIF2BeanFactory("billFormEditor"));
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
		bean.setBillType("20");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPraybillPrintMenuAction() {
		if (context.get("praybillPrintMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("praybillPrintMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("praybillPrintMenuAction", bean);
		bean.setCode("printMenuAction");
		bean.setActions(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getPrintCountQueryAction());
		list.add(getSeparatorAction_1805cdb());
		list.add(getCombineShowAction());
		return list;
	}

	private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1805cdb() {
		if (context.get("nc.funcnode.ui.action.SeparatorAction#1805cdb") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("nc.funcnode.ui.action.SeparatorAction#1805cdb");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("nc.funcnode.ui.action.SeparatorAction#1805cdb", bean);
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
		bean.setContributors(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
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
		bean.setActions(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
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
		list.add(getLinkQueryMenuAction());
		list.add(getSeparatorAction());
		list.add(getSeparatorAction());
		list.add(getPraybillPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getExcelImportAndExportActiongroup());
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
		bean.setActions(getManagedList12());
		bean.setEditActions(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
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
		list.add(getLinkQueryMenuAction());
		list.add(getSeparatorAction());
		list.add(getSeparatorAction());
		list.add(getPraybillPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getIMAction());
		list.add(getSeparatorAction());
		list.add(getExcelImportAndExportActiongroup());
		list.add(getSeparatorAction());
		list.add(getRefuseAction());
		return list;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getSaveApproveAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getEditLinkQueryMenuAction());
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

	public nc.funcnode.ui.action.GroupAction getExcelImportAndExportActiongroup() {
		if (context.get("excelImportAndExportActiongroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("excelImportAndExportActiongroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("excelImportAndExportActiongroup", bean);
		bean.setCode("importActionActiongroup");
		bean.setName("导入导出");
		bean.setActions(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getImportAction());
		list.add(getExportAction());
		list.add(getExportTemplateAction());
		return list;
	}

	public nc.ui.pu.m20.action.ImportableEditorFor20 getImportableEditor() {
		if (context.get("importableEditor") != null)
			return (nc.ui.pu.m20.action.ImportableEditorFor20) context
					.get("importableEditor");
		nc.ui.pu.m20.action.ImportableEditorFor20 bean = new nc.ui.pu.m20.action.ImportableEditorFor20();
		context.put("importableEditor", bean);
		bean.setBillcardPanelEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setAppModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setAddAction(getAddManualAction());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.ImportActionFor20 getImportAction() {
		if (context.get("importAction") != null)
			return (nc.ui.pu.m20.action.ImportActionFor20) context
					.get("importAction");
		nc.ui.pu.m20.action.ImportActionFor20 bean = new nc.ui.pu.m20.action.ImportActionFor20();
		context.put("importAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setImportableEditor(getImportableEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.ExportActionFor20 getExportAction() {
		if (context.get("exportAction") != null)
			return (nc.ui.pu.m20.action.ExportActionFor20) context
					.get("exportAction");
		nc.ui.pu.m20.action.ExportActionFor20 bean = new nc.ui.pu.m20.action.ExportActionFor20();
		context.put("exportAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setImportableEditor(getImportableEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.ExportBillTemplateActionFor20 getExportTemplateAction() {
		if (context.get("exportTemplateAction") != null)
			return (nc.ui.pu.m20.action.ExportBillTemplateActionFor20) context
					.get("exportTemplateAction");
		nc.ui.pu.m20.action.ExportBillTemplateActionFor20 bean = new nc.ui.pu.m20.action.ExportBillTemplateActionFor20();
		context.put("exportTemplateAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setImportableEditor(getImportableEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.AddFromDW60Action2 getAddFromDW60Action() {
		if (context.get("AddFromDW60Action") != null)
			return (nc.ui.pu.m20.action.AddFromDW60Action2) context
					.get("AddFromDW60Action");
		nc.ui.pu.m20.action.AddFromDW60Action2 bean = new nc.ui.pu.m20.action.AddFromDW60Action2();
		context.put("AddFromDW60Action", bean);
		bean.setSourceBillType("DW60");
		bean.setSourceBillName("攀钢采购计划");
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor) findBeanInUIF2BeanFactory("transferViewProcessor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.AddFromDW70Action getAddFromDW70Action() {
		if (context.get("AddFromDW70Action") != null)
			return (nc.ui.pu.m20.action.AddFromDW70Action) context
					.get("AddFromDW70Action");
		nc.ui.pu.m20.action.AddFromDW70Action bean = new nc.ui.pu.m20.action.AddFromDW70Action();
		context.put("AddFromDW70Action", bean);
		bean.setSourceBillType("DW70");
		bean.setSourceBillName("矿山采购计划");
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor) findBeanInUIF2BeanFactory("transferViewProcessor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.AddFromDW66Action getAddFromDW66Action() {
		if (context.get("AddFromDW66Action") != null)
			return (nc.ui.pu.m20.action.AddFromDW66Action) context
					.get("AddFromDW66Action");
		nc.ui.pu.m20.action.AddFromDW66Action bean = new nc.ui.pu.m20.action.AddFromDW66Action();
		context.put("AddFromDW66Action", bean);
		bean.setSourceBillType("DW66");
		bean.setSourceBillName("股份采购计划");
		bean.setFlowBillType(false);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor) findBeanInUIF2BeanFactory("transferViewProcessor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pu.m20.action.RefuseAction getRefuseAction() {
		if (context.get("refuseAction") != null)
			return (nc.ui.pu.m20.action.RefuseAction) context
					.get("refuseAction");
		nc.ui.pu.m20.action.RefuseAction bean = new nc.ui.pu.m20.action.RefuseAction();
		context.put("refuseAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor((nc.ui.uif2.editor.IBillCardPanelEditor) findBeanInUIF2BeanFactory("billFormEditor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}

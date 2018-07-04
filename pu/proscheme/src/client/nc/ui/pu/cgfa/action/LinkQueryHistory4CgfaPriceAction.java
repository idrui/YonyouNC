package nc.ui.pu.cgfa.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pp.supplierprice.entity.SupplierPriceAggVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;

import com.ufida.zior.exception.MessageException;

public class LinkQueryHistory4CgfaPriceAction extends NCAction {

	private BillManageModel model;

	private Container container;

	private ShowUpableBillForm editor;

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public LinkQueryHistory4CgfaPriceAction() {
		super();
		this.setBtnName("历史价格查询");
		this.setCode("cgfahispricequery");
	}

	public void doAction(ActionEvent e) throws Exception {
//		if (!this.editor.isShowing()) {
//			throw new MessageException("请双击进入卡片模式选择数据");
//
//		}
		int[] i = this.getEditor().getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (i.length == 0) {
			throw new MessageException("请先选择一条表体数据");
		}
		List<String> material = new ArrayList<String>();
		List<String> supplier = new ArrayList<String>();
		for (int j = 0; j < i.length; j++) {

			String pk_material = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(i[j], "materialcode");
//			String pk_supplier = (String) this.editor.getBillCardPanel()
//					.getBodyValueAt(i[j], "pk_supplier");
			if(null == pk_material) throw new MessageException("请先选择一条物料信息");
			material.add(pk_material);
			//supplier.add(pk_supplier);
		}

		this.openSupplierPrice(material, supplier);
		ShowStatusBarMsgUtil.showStatusBarMsg("查询历史价格成功", this.getModel()
				.getContext());

	}

	private void openSupplierPrice(List<String> material, List<String> supplier) {

		FuncletInitData initData = null;
		initData = new FuncletInitData();
		initData.setInitType(Integer.parseInt("10"));
		FuncRegisterVO funvo = WorkbenchEnvironment.getInstance()
				.getFuncRegisterVO("40050200");

		if (null == funvo) {
			ExceptionUtils.wrappBusinessException("功能注册处找不到code=40050200！");

		}
		// 根据鞍钢国际现有程序物料处理太混乱
		// 举个栗子： 一个pk
		// 对应一个物料编码，但是一个物料编码能对应多个pk，也就是一个物料重复录了三遍，迫于无语，先通过pk找code，再通过code找pk
		List<String> newmaterria = processForMaterial(material);
		//List<String> newsupplier = processFor(supplier);

		String[] materria = newmaterria.toArray(new String[newmaterria.size()]);
		//String[] suppli = newsupplier.toArray(new String[newsupplier.size()]);
		SqlBuilder  sb= new SqlBuilder();
		sb.append("pk_material", materria);
		//sb.append(" and ");
		//sb.append("pk_supplier", suppli);
		
		// 查询数据
		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(SupplierPriceAggVO.class,
							sb.toString(), false);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SupplierPriceAggVO[] aggVo = null;
		if (obj != null) {

			aggVo = new SupplierPriceAggVO[obj.length];
			for (int i = 0; i < obj.length; i++) {
				NCObject ncObject = obj[i];
				SupplierPriceAggVO agg = (SupplierPriceAggVO) ncObject
						.getContainmentObject();
				aggVo[i] = agg;

			}
		}
		initData.setInitData(aggVo);
		// 先打开再查询
		FuncletWindowLauncher.openFuncNodeForceModalDialog(WorkbenchEnvironment
				.getInstance().getWorkbench(), funvo, initData, null, false);
		/*
		 * FuncletWindowLauncher.openFuncNodeForceModalDialog(WorkbenchEnvironment
		 * .getInstance().getWorkbench(), funvo, initData, null, true, false);
		 *//*
			 * FuncletWindowLauncher.openFuncNodeFrame(WorkbenchEnvironment
			 * .getInstance().getWorkbench(), funvo, initData, null, false, new
			 * Dimension( 1200, 800));
			 *//*
				 * QueryScheme scheme=new QueryScheme();
				 * scheme.putWhereSQLOnly(whereCondStr); BatchQueryForPriceAudit
				 * query=new BatchQueryForPriceAudit();
				 * query.queryForPriceAudit(scheme);
				 */

	}

	/**
	 * @Title: processFor
	 * @Description: TODO
	 * @param supplier
	 * @return
	 * @return: List<String>
	 */
	private List<String> processFor(List<String> supplier) {
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
		// 通过pk找编码
		// 查询数据
		SqlBuilder sb = new SqlBuilder();
		sb.append("pk_supplier", supplier.toArray(new String[supplier.size()]));

		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.supplier.SupplierVO.class, sb.toString(),
							false);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SupplierVO[] vo = null;
		if (obj != null) {

			vo = new SupplierVO[obj.length];
			for (int i = 0; i < obj.length; i++) {
				NCObject ncObject = obj[i];
				SupplierVO agg = (SupplierVO) ncObject.getContainmentObject();
				vo[i] = agg;

			}
		}
		Set<String> codes = new HashSet<String>();
		for (SupplierVO suppliervo : vo) {
			String code = suppliervo.getCode();
			codes.add(code);
		}
		// 再查pk
		SqlBuilder sbForCode = new SqlBuilder();
		sbForCode.append("code", codes.toArray(new String[codes.size()]));

		NCObject[] objForPK = null;
		try {
			objForPK = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(SupplierVO.class,
							sbForCode.toString(), false);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SupplierVO[] voForCode = null;
		if (obj != null) {

			voForCode = new SupplierVO[obj.length];
			for (int i = 0; i < objForPK.length; i++) {
				NCObject ncObject = obj[i];
				SupplierVO agg = (SupplierVO) ncObject.getContainmentObject();
				voForCode[i] = agg;

			}
		}
		Set<String> pks = new HashSet<String>();
		for (SupplierVO suppliervo : vo) {
			String pk = suppliervo.getPrimaryKey();
			pks.add(pk);
		}
		List<String> ret = new ArrayList<String>();
		ret.addAll(pks);
		return ret;
	}

	/**
	 * @Title: processForMaterial
	 * @Description: TODO
	 * @param material
	 * @return
	 * @return: List<String>
	 */
	private List<String> processForMaterial(List<String> material) {
		// TODO 自动生成的方法存根
		// 通过pk找编码
		// 查询数据
		SqlBuilder sb = new SqlBuilder();
		sb.append("pk_material", material.toArray(new String[material.size()]));

		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.material.MaterialVO.class, sb.toString(),
							false);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		MaterialVO[] vo = null;
		if (obj != null) {

			vo = new MaterialVO[obj.length];
			for (int i = 0; i < obj.length; i++) {
				NCObject ncObject = obj[i];
				MaterialVO agg = (MaterialVO) ncObject.getContainmentObject();
				vo[i] = agg;

			}
		}
		Set<String> codes = new HashSet<String>();
		for (MaterialVO materialVO : vo) {
			String code = materialVO.getCode();
			codes.add(code);
		}
		// 再查pk
		SqlBuilder sbForCode = new SqlBuilder();
		sbForCode.append("code", codes.toArray(new String[codes.size()]));

		NCObject[] objForPK = null;
		try {
			objForPK = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.material.MaterialVO.class,
							sbForCode.toString(), false);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		MaterialVO[] voForCode = null;
		if (obj != null) {

			voForCode = new MaterialVO[objForPK.length];
			for (int i = 0; i < objForPK.length; i++) {
				NCObject ncObject = obj[i];
				MaterialVO agg = (MaterialVO) ncObject.getContainmentObject();
				voForCode[i] = agg;

			}
		}
		Set<String> pks = new HashSet<String>();
		for (MaterialVO materialVO : vo) {
			String pk = materialVO.getPrimaryKey();
			pks.add(pk);
		}
		List<String> ret = new ArrayList<String>();
		ret.addAll(pks);
		return ret;
	}

	protected boolean isActionEnable() {
		if (!this.getEditor().getBillCardPanel().isShowing()) {
			return false;
		}

		return ((getModel().getUiState() == UIState.NOT_EDIT || getModel()
				.getUiState() == UIState.DISABLE))
				&& (this.model.getSelectedData() != null);
	}

}
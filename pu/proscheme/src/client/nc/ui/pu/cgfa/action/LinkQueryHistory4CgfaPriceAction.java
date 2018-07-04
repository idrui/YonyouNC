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
		this.setBtnName("��ʷ�۸��ѯ");
		this.setCode("cgfahispricequery");
	}

	public void doAction(ActionEvent e) throws Exception {
//		if (!this.editor.isShowing()) {
//			throw new MessageException("��˫�����뿨Ƭģʽѡ������");
//
//		}
		int[] i = this.getEditor().getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (i.length == 0) {
			throw new MessageException("����ѡ��һ����������");
		}
		List<String> material = new ArrayList<String>();
		List<String> supplier = new ArrayList<String>();
		for (int j = 0; j < i.length; j++) {

			String pk_material = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(i[j], "materialcode");
//			String pk_supplier = (String) this.editor.getBillCardPanel()
//					.getBodyValueAt(i[j], "pk_supplier");
			if(null == pk_material) throw new MessageException("����ѡ��һ��������Ϣ");
			material.add(pk_material);
			//supplier.add(pk_supplier);
		}

		this.openSupplierPrice(material, supplier);
		ShowStatusBarMsgUtil.showStatusBarMsg("��ѯ��ʷ�۸�ɹ�", this.getModel()
				.getContext());

	}

	private void openSupplierPrice(List<String> material, List<String> supplier) {

		FuncletInitData initData = null;
		initData = new FuncletInitData();
		initData.setInitType(Integer.parseInt("10"));
		FuncRegisterVO funvo = WorkbenchEnvironment.getInstance()
				.getFuncRegisterVO("40050200");

		if (null == funvo) {
			ExceptionUtils.wrappBusinessException("����ע�ᴦ�Ҳ���code=40050200��");

		}
		// ���ݰ��ֹ������г������ϴ���̫����
		// �ٸ����ӣ� һ��pk
		// ��Ӧһ�����ϱ��룬����һ�����ϱ����ܶ�Ӧ���pk��Ҳ����һ�������ظ�¼�����飬���������ͨ��pk��code����ͨ��code��pk
		List<String> newmaterria = processForMaterial(material);
		//List<String> newsupplier = processFor(supplier);

		String[] materria = newmaterria.toArray(new String[newmaterria.size()]);
		//String[] suppli = newsupplier.toArray(new String[newsupplier.size()]);
		SqlBuilder  sb= new SqlBuilder();
		sb.append("pk_material", materria);
		//sb.append(" and ");
		//sb.append("pk_supplier", suppli);
		
		// ��ѯ����
		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(SupplierPriceAggVO.class,
							sb.toString(), false);
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
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
		// �ȴ��ٲ�ѯ
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
		// TODO �Զ����ɵķ������
		// TODO �Զ����ɵķ������
		// ͨ��pk�ұ���
		// ��ѯ����
		SqlBuilder sb = new SqlBuilder();
		sb.append("pk_supplier", supplier.toArray(new String[supplier.size()]));

		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.supplier.SupplierVO.class, sb.toString(),
							false);
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
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
		// �ٲ�pk
		SqlBuilder sbForCode = new SqlBuilder();
		sbForCode.append("code", codes.toArray(new String[codes.size()]));

		NCObject[] objForPK = null;
		try {
			objForPK = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(SupplierVO.class,
							sbForCode.toString(), false);
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
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
		// TODO �Զ����ɵķ������
		// ͨ��pk�ұ���
		// ��ѯ����
		SqlBuilder sb = new SqlBuilder();
		sb.append("pk_material", material.toArray(new String[material.size()]));

		NCObject[] obj = null;
		try {
			obj = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.material.MaterialVO.class, sb.toString(),
							false);
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
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
		// �ٲ�pk
		SqlBuilder sbForCode = new SqlBuilder();
		sbForCode.append("code", codes.toArray(new String[codes.size()]));

		NCObject[] objForPK = null;
		try {
			objForPK = MDPersistenceService.lookupPersistenceQueryService()
					.queryBillOfNCObjectByCond(
							nc.vo.bd.material.MaterialVO.class,
							sbForCode.toString(), false);
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
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
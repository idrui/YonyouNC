/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 上午10:01:00
 */
package nc.ui.pu.est.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide;
import nc.ui.pu.est.view.EditorToModelValueSetter;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pu.est.view.EstFeeDistUIDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估处理费用分摊动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-31 上午10:01:00
 */
public class EstDistFeeAction extends SCMTpaAction {
	/**
   *
   */
	private static final long serialVersionUID = 939393452417595204L;

	private EstEditor editor;

	private AbstractUIFeeDivide feeDivider;

	private BillManageModel model;

	public EstDistFeeAction() {
		// this.setBtnName("费用分摊");
		// this.setCode("purchaseindistfee");
		// this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
		SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEDISTRIBUTE);
		this.setEnabled(false);
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.beforeAction();
		EstFeeDistUIDialog dialog = new EstFeeDistUIDialog(this.editor,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
						"04004060-0010")/*
														 * @res "费用分摊"
														 */);
		dialog.setFeeDivider(this.feeDivider);
		int ret = dialog.showModal();
		if (UIDialog.ID_CANCEL == ret) {
			this.resetTotalValue();
			return;
		}
		// Object vos=dialog.getVOs();
		this.afterAction();
	}

	/**
	 * @return editor
	 */
	public EstEditor getEditor() {
		return this.editor;
	}

	/**
	 * @return feeDivider
	 */
	public AbstractUIFeeDivide getFeeDivider() {
		return this.feeDivider;
	}

	/**
	 * @return model
	 */
	public BillManageModel getModel() {
		return this.model;
	}

	/**
	 * @param editor
	 *          要设置的 editor
	 */
	public void setEditor(EstEditor editor) {
		this.editor = editor;
	}

	/**
	 * @param feeDivider
	 *          要设置的 feeDivider
	 */
	public void setFeeDivider(AbstractUIFeeDivide feeDivider) {
		this.feeDivider = feeDivider;
	}

	/**
	 * @param model
	 *          要设置的 model
	 */
	public void setModel(BillManageModel model) {
		this.model = model;
		this.setContext(this.model.getContext());
		this.model.addAppEventListener(this);
	}

	private void afterAction() {
		// 进行费用分摊
		// feeDivider.feeDiv(getModel(), vos);
		// 恢复暂估编辑界面的数据
		BillListPanel blp = this.editor.getBillListPanel();
		Object vo = this.model.getSelectedData();
		this.editor.getBillListPanelValueSetter().setBodyData(blp, vo);
		this.resetTotalValue();
	}

	private void beforeAction() {
		BillCardPanel bcp = this.editor.getBillCardPanel();
		EditorToModelValueSetter syn = new EditorToModelValueSetter(bcp, this.model);
		syn.setModelBodyValue(this.model.getSelectedRow());
	}

	/**
	 * 费用合计相关的字段重新设置
	 */
	private void resetTotalValue() {
		
		BillModel headModel = this.editor.getBillListPanel().getBillListData()
				.getHeadBillModel();
		/*
		 * 关闭合计行最后打开，解决效率问题！
		 */
		headModel.setNeedCalculate(false);
		Integer[] selectRows = this.model.getSelectedOperaRows();
		List<EstVO> datas = this.model.getData();
		EstVOUtil.calculateTotal(datas.toArray(new EstVO[0]));
		for (int i = 0; i < selectRows.length; i++) {
			EstVO data = (EstVO) datas.get(selectRows[i].intValue());
			// EstVOUtil.calculateTotal(new EstVO[] {
			// data
			// });
			headModel.setValueAt(data.getParentVO().getNfeemny(),
					selectRows[i].intValue(), GoodsEstVO.NFEEMNY);
			headModel.setValueAt(data.getParentVO().getNfeetaxmny(),
					selectRows[i].intValue(), GoodsEstVO.NFEETAXMNY);
			headModel.setValueAt(data.getParentVO().getNtotalmny(),
					selectRows[i].intValue(), GoodsEstVO.NTOTALMNY);
			headModel.setValueAt(data.getParentVO().getNtotaltaxmny(),
					selectRows[i].intValue(), GoodsEstVO.NTOTALTAXMNY);
		}
		headModel.setNeedCalculate(true);

	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.ui.uif2.NCAction#isActionEnable()
	 */
	@Override
	protected boolean isActionEnable() {
		return !ArrayUtils.isEmpty(this.getModel().getSelectedOperaDatas());
	}

}

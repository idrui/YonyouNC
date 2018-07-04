/**   
 * Copyright  2017 Yonyou. All rights reserved.
 * 
 * @Title: M58012M30DataProcessor.java 
 * @Prject: SCM_SO
 * @Package: nc.ui.so.m30.billui.push 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2017年12月27日 下午11:21:52 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.billui.push;

import nc.funcnode.ui.AbstractFunclet;
import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.pu.cgfa.AggCgfa;

/**
 * @ClassName: MJT01RDataProcessor
 * @Description: TODO
 * @author: wangzy
 * @date: 2018-05-31
 */
public class MJT01RDataProcessor implements IInitDataProcessor {
	private TransferViewProcessor transferProcessor;

	public TransferViewProcessor getTransferProcessor() {
		return this.transferProcessor;
	}

	@Override
	public void process(FuncletInitData data) {

		AggCgfa[] aggVOs = (AggCgfa[]) data.getInitData();

		// 设置model状态
		this.getTransferProcessor().getBillForm().getModel()
				.setUiState(UIState.NOT_EDIT);
		BillForm editor = this.getTransferProcessor().getBillForm();
		this.getTransferProcessor().processBillTransfer(aggVOs);
		this.getTransferProcessor().getBillForm().getModel()
				.setAppUiState(AppUiState.NOT_EDIT);
		this.getTransferProcessor().getBillForm().getModel().initModel(aggVOs);

	}

	public void setTransferProcessor(TransferViewProcessor transferProcessor) {
		this.transferProcessor = transferProcessor;
	}
}

/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 上午08:39:17
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import com.ufida.iufo.table.drill.ReportDrillInfo;
import com.ufida.iufo.table.drill.ReportDrillItem;
import com.ufida.report.anareport.base.FreeReportDrillParam;
import com.ufida.report.free.publish.util.FreeReportFuncletUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 */
public class InterNProAction extends NCAction {


  /**
	 * 
	 */
	private static final long serialVersionUID = -2061856576726362876L;

private ShowUpableBillForm billForm;

  private BillListView list;

  private BillManageModel model;

	public InterNProAction() {
		super();
		setBtnName("联查报表");
		setCode("InterNNProAction");
	}

	public void setBtnName(String btnName) {
		super.setBtnName("联查报表");
	} 

	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillListPanel listPanel = (BillListPanel) list.getBillListPanel();
		BillCardPanel cardPanel = (BillCardPanel) billForm.getBillCardPanel();
		Object[] objs = getModel().getSelectedOperaDatas();
		Object obj = getModel().getSelectedData();
		if(cardPanel.isShowing() && obj == null){
			ExceptionUtils.wrappBusinessException("没有选择数据！");
		}
		if(listPanel.isShowing()){
			if(objs == null || objs.length>1){
				ExceptionUtils.wrappBusinessException("请选择一条数据！");
			}
		}
		AggregatedValueObject aggVO = (AggregatedValueObject) objs[0];
		String projectid = (String) aggVO.getParentVO().getAttributeValue("pk_project");
		if(projectid == null){
			ExceptionUtils.wrappBusinessException("项目为空！");
		}
		linkReport(projectid);
	}
	
	public void linkReport(String projectid){
		//自由报表穿透信息：
		ReportDrillItem[] drillItems = new ReportDrillItem[1];
		//页维度客户名称
		drillItems[0] = new ReportDrillItem();
		drillItems[0].setConditionType(ReportDrillItem.TYPE_FRQUERYITEM);
		drillItems[0].setConditionName("项目名称");//注意：这里的条件名称跟你选的语义模型里客户名称的位置有关
		drillItems[0].setValue(projectid);
		ReportDrillInfo drillRule = new ReportDrillInfo();
		drillRule.setDrillItem(drillItems);
		
		FreeReportDrillParam drillParam = new FreeReportDrillParam();
		drillParam.setDrillRule(drillRule);
		
		FuncletInitData initData = new FuncletInitData();
		initData.setInitData(drillParam);
		FreeReportFuncletUtil.openReportNode("4105report01", initData);
	}

  /**
   * @return billForm
   */
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }
  
  @Override
  protected boolean isActionEnable() {
    return true;
  }

}

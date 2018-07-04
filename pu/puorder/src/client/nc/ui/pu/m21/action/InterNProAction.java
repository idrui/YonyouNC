/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 ����08:39:17
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
 * <b>������Ҫ������¹��ܣ�</b>
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
		setBtnName("���鱨��");
		setCode("InterNNProAction");
	}

	public void setBtnName(String btnName) {
		super.setBtnName("���鱨��");
	} 

	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillListPanel listPanel = (BillListPanel) list.getBillListPanel();
		BillCardPanel cardPanel = (BillCardPanel) billForm.getBillCardPanel();
		Object[] objs = getModel().getSelectedOperaDatas();
		Object obj = getModel().getSelectedData();
		if(cardPanel.isShowing() && obj == null){
			ExceptionUtils.wrappBusinessException("û��ѡ�����ݣ�");
		}
		if(listPanel.isShowing()){
			if(objs == null || objs.length>1){
				ExceptionUtils.wrappBusinessException("��ѡ��һ�����ݣ�");
			}
		}
		AggregatedValueObject aggVO = (AggregatedValueObject) objs[0];
		String projectid = (String) aggVO.getParentVO().getAttributeValue("pk_project");
		if(projectid == null){
			ExceptionUtils.wrappBusinessException("��ĿΪ�գ�");
		}
		linkReport(projectid);
	}
	
	public void linkReport(String projectid){
		//���ɱ���͸��Ϣ��
		ReportDrillItem[] drillItems = new ReportDrillItem[1];
		//ҳά�ȿͻ�����
		drillItems[0] = new ReportDrillItem();
		drillItems[0].setConditionType(ReportDrillItem.TYPE_FRQUERYITEM);
		drillItems[0].setConditionName("��Ŀ����");//ע�⣺������������Ƹ���ѡ������ģ����ͻ����Ƶ�λ���й�
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
   *          Ҫ���õ� billForm
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

package nc.ui.pu.qst.newoaspwh.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.so.mreturnassign.model.returnassign_base_config;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.ls.MessageBox;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pub.beans.UIRefPane;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;
import nc.vo.pu.qst.newoabase.NewoabaseaHeadVO;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.vo.pub.VOStatus;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;

/**
 * 拦截器,拦截保存之前的操作,重复值不允许保存
 * @author 低调火药
 * 2016-11-19
 */
public class BeaforSaveIterceptor implements ActionInterceptor {

	private ShowUpableBillForm billForm;
	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		String scc,ywlx,jhlx,splx,fqroah;
		BillCardPanel bcp = billForm.getBillCardPanel();
		AggNewoaspwhaHeadVO o =(AggNewoaspwhaHeadVO) billForm.getValue();
		if(null==o.getChildrenVO() ||o.getChildrenVO().length == 0){
			MessageBox.showErrorDialog("错误提示", new Throwable(",审批路径不能空,请生成审批路径!"));
			return false;
		}
		//判断VO状态,新增或者修改
		/*if(o.getParent().getStatus() != VOStatus.NEW) return true; 
		
		//从参照获取生产厂主键值
		scc = (String)((UIRefPane)bcp.getHeadItem("pk_scc").getComponent()).getRefValue("pk_scc");
		//取业务类型
		ywlx = (String)bcp.getHeadItem("ywlx").getValueObject();
		//取计划类型
		jhlx = (String)bcp.getHeadItem("jhlx").getValueObject();
		//取审批类型
		splx = (String)bcp.getHeadItem("splx").getValueObject();
		//发起人OA号
		fqroah = (String)bcp.getHeadItem("fqroah").getValueObject();
		//组装SQL语句
		String sqlString = "dr = 0 and ywlx="+ywlx+" and jhlx="+jhlx+" and splx=" + splx + " and pk_scc='"+scc+"' and fqroah='"+ fqroah +"'";
		NCObject[] ncObjects = null;
		try {
			ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
					(NewoabaseaHeadVO.class,sqlString,false);
		} catch (MetaDataException e1) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappException(e1);
		}*/
		/*if(ncObjects != null) {
			MessageBox.showErrorDialog("错误提示:数据重复", new Throwable("审批路径已经存在,请在原数据上修改!"));
			return false;
		}*/
		return true;
	}

	@Override
	public void afterDoActionSuccessed(Action action, ActionEvent e) {
		// TODO 自动生成的方法存根
		MessageBox.showMessageDialog("操作提示", "审批路径保存成功");
	}

	@Override
	public boolean afterDoActionFailed(Action action, ActionEvent e,
			Throwable ex) {
		// TODO 自动生成的方法存根
		return false;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	
}

package nc.ui.pu.cgfa.ace.handler;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.pubitf.rbac.IUserPubService;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.ui.pub.bill.BillCardPanel;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("bill_status", BillStatusEnum.FREE.value());
		panel.setHeadItem("filltime", AppContext.getInstance().getBusiDate());
		
		//==== lijj 人员设值====
		String pk_user = e.getContext().getPk_loginUser();
		IUserPubService userSer = NCLocator.getInstance().lookup(IUserPubService.class);
		try {
			String psndocid = userSer.queryPsndocByUserid(pk_user);
			panel.setHeadItem("salesman", psndocid);
			
			if(psndocid != null){
				IPsndocPubService psndocSer = NCLocator.getInstance().lookup(IPsndocPubService.class);
				Map<String, List<String>> psndocInfoMap = psndocSer.queryDeptIDByPsndocIDs(new String[]{psndocid});
			
				if(psndocInfoMap != null && psndocInfoMap.get(psndocid) != null && psndocInfoMap.get(psndocid).size() > 0){
					String deptid = psndocInfoMap.get(psndocid).get(0);
					panel.setHeadItem("dept", deptid);
				}
			}
		} catch (BusinessException e1) {
			Logger.error(e1.getMessage(), e1);
		}
		//==== lijj 人员设值====
		
	}
}

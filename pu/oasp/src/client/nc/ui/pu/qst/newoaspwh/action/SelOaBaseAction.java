package nc.ui.pu.qst.newoaspwh.action;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

import nc.bs.framework.common.NCLocator;
import nc.itf.oamail.user.IUserManageService;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.rbac.IUserManageQuery;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.ui.ls.MessageBox;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;
import nc.vo.pu.qst.newoabase.NewoabaseaHeadVO;
import nc.vo.pu.qst.newoabase.NewoabasebBodyVO;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.vo.pubapp.AppContext;
import nc.vo.sm.UserVO;

public class SelOaBaseAction extends NCAction {

	private BillManageModel model;
	private ShowUpableBillForm editor;
	
	public SelOaBaseAction() {
		super();
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		String scc,ywlx,jhlx,splx;//,fqroah;
		//TODO ȡ����������Ϣ������װ
		BillCardPanel bcp = editor.getBillCardPanel();//.getBillTable("");
		//��ȡ��ǰ�༭��AggVO
		AggNewoaspwhaHeadVO ah = (AggNewoaspwhaHeadVO)editor.getValue();
//		Object valueObject = bcp.getHeadItem("").getValueObject();
		//�Ӳ��ջ�ȡ����������ֵ
//		scc = (String)((UIRefPane)bcp.getHeadItem("pk_scc").getComponent()).getRefValue("pk_scc");
		scc = (String)ah.getParent().getAttributeValue("pk_scc");
		//ȡҵ������
		ywlx = (String)ah.getParent().getAttributeValue("ywlx");
		//ȡ�ƻ�����
		jhlx = (String)ah.getParent().getAttributeValue("jhlx");
		//ȡ��������
		splx = (String)ah.getParent().getAttributeValue("splx");
		//������OA��
//		fqroah = (String)ah.getParent().getAttributeValue("fqroah");
		//��װSQL���
		String sqlString = "dr = 0 and ywlx="+ywlx+" and jhlx="+jhlx+" and splx=" + splx + " and pk_scc='" + scc + "'";
		//�Ȳ鵽sm_user.pk_psndoc
		IUserManageQuery iUser = NCLocator.getInstance().lookup(IUserManageQuery.class);
		UserVO[] users = iUser.queryUserByClause("CUSERID='" + AppContext.getInstance().getPkUser() + "'");
		IPsndocPubService psndocQuery = NCLocator.getInstance().lookup(IPsndocPubService.class);
		
		//BD_PSNDOC.DEF1-�Զ��x�ֶ�,����ˆTOA̖��Ϣ
		PsndocVO[] vos = psndocQuery.queryPsndocByPks(new String[]{users[0].getPk_psndoc()}, new String[]{"DEF1"});
		
		String userOa = "";		
//		String userOa = (String)ibs.executeQuery("SELECT DEF1 FROM BD_PSNDOC WHERE PK_PSNDOC='"+ AppContext.getInstance().getPkUser() +"'", new ResultSetProcessor() {
		/*
		   IUAPQueryBS ibs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		   String userOa = (String)ibs.executeQuery("SELECT DEF1 FROM BD_PSNDOC WHERE PK_PSNDOC='1001N610000000000A8I'", new ResultSetProcessor() {
			@Override
			public String handleResultSet(ResultSet rs) throws SQLException {
				String retString = null;
				while(rs.next()){
					retString = rs.getString(1);
				}
				return retString;
			}
		});*/
		//�O��ֵ
		if(vos.length > 0) userOa = vos[0].getDef1();
		ah.getParent().setAttributeValue("fqroah", userOa);
		
//		int rows = bcp.getRowCount("id_newoaspwh_b");
//		Object[] obj0 = model.getSelectedOperaDatas();
//		List obj1 = model.getData();
//		Object[] obj2 = bcp.getBodyItems();
		
//		IQueryScheme queryScheme = QuerySchemaUtil.createQuerySchemeByFullClassName("nc.vo.pu.qst.oaspbase.AggOaspbaseHeadVO");
		//ɾ��������������
		ah.setChildrenVO(null);
		editor.setValue(ah);
		
		NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
				(NewoabaseaHeadVO.class,sqlString,false);
		if(null == ncObjects) {
			MessageBox.showMessageDialog("��ʾ", "û���ҵ����������ļ�¼!");
			ShowStatusBarMsgUtil.showErrorMsgWithClear("��Ϣ��ʾ", "û���ҵ����������ļ�¼!", model.getContext());
			return;
		}
		AggNewoabaseaHeadVO v = (AggNewoabaseaHeadVO)ncObjects[0].getContainmentObject();
		
//		list.toArray(new AggOaspbaseHeadVO[list.size()]);
		//VO-�������
		NewoabasebBodyVO[] bodyVO = (NewoabasebBodyVO[])v.getChildrenVO();
		Arrays.sort(bodyVO, new Comparator<NewoabasebBodyVO>(){
			@Override
			public int compare(NewoabasebBodyVO paramT1,NewoabasebBodyVO paramT2) {
				Integer val = Integer.valueOf((String) paramT1.getAttributeValue("spcj"));
				Integer val2 = Integer.valueOf((String) paramT2.getAttributeValue("spcj"));
				return val.compareTo(val2);
			}});
		if(null == bodyVO) {
			MessageBox.showMessageDialog("��ʾ", "û���ҵ����������ļ�¼!");
			ShowStatusBarMsgUtil.showErrorMsgWithClear("��Ϣ��ʾ", "û���ҵ����������ļ�¼!", model.getContext());
			return;
		}
		//���²���
		for(NewoabasebBodyVO b : bodyVO){
			bcp.addLine("id_newoaspwh_b");
			bcp.setBodyValueAt(b.getAttributeValue("spcj"), bcp.getRowCount() - 1, "spcj", "id_newoaspwh_b");
			bcp.setBodyValueAt(b.getAttributeValue("spedxx"), bcp.getRowCount() - 1, "spedxx", "id_newoaspwh_b");
			bcp.setBodyValueAt(b.getAttributeValue("sped"), bcp.getRowCount() - 1, "sped", "id_newoaspwh_b");
			bcp.setBodyValueAt(b.getAttributeValue("sproah"), bcp.getRowCount() - 1, "sproah", "id_newoaspwh_b");
			bcp.setBodyValueAt(b.getAttributeValue("sprxm"), bcp.getRowCount() - 1, "sprxm", "id_newoaspwh_b");
		}
		ShowStatusBarMsgUtil.showStatusBarMsg("��ѯ����·�����!",model.getContext());
	}
	
	//��ť�����߼�����
	//�ڷǱ༭̬�¿���
	@Override
	protected boolean isActionEnable() {
		return model.getUiState()==UIState.NOT_EDIT;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	
}

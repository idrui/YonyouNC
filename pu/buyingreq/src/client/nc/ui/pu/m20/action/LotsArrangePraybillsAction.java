package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.bd.ref.AbstractRefGridTreeModel;
import nc.ui.bd.ref.model.PsndocDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.ui.pu.m20.model.PrayarrangeModelService;
import nc.ui.uif2.model.BatchBillTableModel;


public class LotsArrangePraybillsAction extends BatchCancelAction{
	
	private PrayarrangeModelService daoService;
	/**
	 * ������ʾ��ť�����ð�ť����
	 */
	public LotsArrangePraybillsAction() {
		super.setBtnName("��������");
		this.setCode("lotArrange");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8896021827884863395L;
	
	 @Override
	  public void doAction(ActionEvent e) throws Exception {
		 /**��ȡ��������Ա�����ڵ���Ա�������Ա����**/
		 UIRefPane userRefPane = new UIRefPane();
		 PsndocDefaultRefModel usermodel = new PsndocDefaultRefModel();
		 AbstractRefGridTreeModel model = (AbstractRefGridTreeModel)usermodel;
		 String newWherePart="enablestate=2";
		 model.setWherePart(newWherePart);
		 model.setClassWherePart("1=1");
		 
		 userRefPane.setRefModel(model);
		 //���Ӷ๫˾֧��
		 userRefPane.setMultiCorpRef(true);
		 userRefPane.showModel();
		 String code = usermodel.getPkValue();
		 Integer[] rowlen = this.getModel().getSelectedOperaRows();
		 int len = rowlen.length;
		 
		 String pk_Group=AppContext.getInstance().getPkGroup();
		 String pk_Org=super.getModel().getContext().getPk_org();
		 //////�������Ź���ʵ�֣�������Ҫͬʱ����������
		 
		 if(code != null){//����������Ű�ť�޲���ʱ��ֱ�ӷ���
			 for(int j = 0; j < len; j++){
				 this.getEditor().getBillCardPanel().setBodyValueAt(code, rowlen[j], "pk_employee");//���òɹ�Ա
		    	 this.getEditor().getBillCardPanel().setBodyValueAt("02", rowlen[j], "sts_req");//���üƻ�״̬Ϊ�Է���
		    	 //����Ҫ��bisarrange����Ϊtrue���ڱ��������Զ�ȡ��������
		    	 this.getEditor().getBillCardPanel().getBodyValueAt(2, "bisarrange");
		    	 this.getEditor().getBillCardPanel().setBodyValueAt(AppContext.getInstance().getServerTime(), rowlen[j], "tmstp_dispatch");//���üƻ�����ʱ��
		    	 this.getEditor().getBillCardPanel().setBodyValueAt(pk_Group, rowlen[j], "pk_group");
		    	 this.getEditor().getBillCardPanel().setBodyValueAt(pk_Org, rowlen[j], "pk_org");
		    	 //��vo���ӵ�updateLine��list������;
		    	 /**
		    	  * modified by wangzym 2017-01-17
		    	  * */
		    	 Object objrow = getEditingLineVO(rowlen[j]); 
		    	 this.getEditor().getModel().updateLine(rowlen[j], objrow);
		    	 
			 }
		 }
	 }

	/**
	 * @return daoService
	 */
	public PrayarrangeModelService getDaoService() {
		return daoService;
	}

	/**
	 * @param daoService Ҫ���õ� daoService
	 */
	public void setDaoService(PrayarrangeModelService daoService) {
		this.daoService = daoService;
	}
	
	protected Object getEditingLineVO(int rowIndex) {
		
		Object obj = null;
		obj = this.getEditor().getBillCardPanel().getBillModel().getBodyValueRowVO(rowIndex, PrayarrangeViewVO.class.getName());
		
		return obj;
	} 

	 
}
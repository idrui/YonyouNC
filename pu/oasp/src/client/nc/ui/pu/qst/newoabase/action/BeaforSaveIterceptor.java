package nc.ui.pu.qst.newoabase.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import com.ibm.db2.jcc.am.nc;

import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.ls.MessageBox;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;
import nc.vo.pu.qst.newoabase.NewoabaseaHeadVO;
import nc.vo.pub.VOStatus;

/**
 * ������,���ر���֮ǰ�Ĳ���,�ظ�ֵ��������
 * @author �͵���ҩ
 * 2016-11-19
 */
public class BeaforSaveIterceptor implements ActionInterceptor {

	private ShowUpableBillForm billForm;
	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		String scc,ywlx,jhlx,splx,fqroah;
		BillCardPanel bcp = billForm.getBillCardPanel();
		AggNewoabaseaHeadVO o =(AggNewoabaseaHeadVO) billForm.getValue();
		//�Ӳ��ջ�ȡ����������ֵ
		scc = (String)o.getParent().getAttributeValue("pk_scc") ;//((UIRefPane)bcp.getHeadItem("pk_scc").getComponent()).getRefValue("pk_scc");
		//ȡҵ������
		ywlx = (String)o.getParent().getAttributeValue("ywlx");
		//ȡ�ƻ�����
		jhlx = (String)o.getParent().getAttributeValue("jhlx");
		//ȡ��������
		splx = (String)o.getParent().getAttributeValue("splx");
		//������OA��
		fqroah = (String)o.getParent().getAttributeValue("fqroah");
		//��װSQL���
		String sqlString = "dr = 0 and ywlx="+ywlx+" and jhlx="+jhlx+" and splx=" + splx + " and pk_scc='"+scc+"' and fqroah='"+ fqroah +"'";
		NCObject[] ncObjects = null;
		try {
			ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
					(NewoabaseaHeadVO.class,sqlString,false);
		} catch (MetaDataException e1) {
			// TODO �Զ����ɵ� catch ��
			ExceptionUtils.wrappException(e1);
		}
		if(ncObjects != null) {
			//�ж�VO״̬,���������޸Ļ��߸���
			//�޸�
			if(o.getParent().getStatus() == VOStatus.UPDATED){
				//������������,�򱨴�
				if(!o.getParent().getPrimaryKey().equals(((AggNewoabaseaHeadVO)ncObjects[0].getContainmentObject()).getParent().getPrimaryKey())){
					MessageBox.showErrorDialog("������ʾ:�����ظ�", new Throwable("����·���Ѿ�����,����ԭ�������޸�!"));
					return false;
				}
			//���ƺ�����
			}else if(o.getParent().getStatus() == VOStatus.UNCHANGED || o.getParent().getStatus() == VOStatus.NEW){
				MessageBox.showErrorDialog("������ʾ:�����ظ�", new Throwable("����·���Ѿ�����,����ԭ�������޸�!"));
				return false;
			}
			
			
		}
		return true;
	}

	@Override
	public void afterDoActionSuccessed(Action action, ActionEvent e) {
		// TODO �Զ����ɵķ������
		MessageBox.showMessageDialog("������ʾ", "����·������ɹ�");
	}

	@Override
	public boolean afterDoActionFailed(Action action, ActionEvent e,
			Throwable ex) {
		// TODO �Զ����ɵķ������
		return false;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	
}

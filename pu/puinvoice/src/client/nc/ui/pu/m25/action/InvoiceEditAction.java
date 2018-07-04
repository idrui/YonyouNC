/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-8-5 ����04:07:12
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PUEditAction;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-8-5 ����04:07:12
 */
public class InvoiceEditAction extends PUEditAction {

  /**
  * 
  */
  private static final long serialVersionUID = 4435766414744657347L;
  
  @Override
  public void doAction(ActionEvent e) throws Exception{
  	InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData(); 
		/*
		 * add by wandl
		 * �����е����޸ģ��жϵ�ǰ�����û��Ƿ�ǰ�����ˣ�������ǵ�ǰ�������������޸ĵ���
		 */
  	ApproveFlowUtil.userCanEditCheck(vo, InvoiceHeaderVO.VTRANTYPECODE);
  	super.doAction(e);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.EditAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable = super.isActionEnable();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      isEnable &= ApproveFlowUtil.isCanEdit(vo);
    }
    return isEnable;
  }

}

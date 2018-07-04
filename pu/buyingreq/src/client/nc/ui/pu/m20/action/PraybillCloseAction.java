/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 ����07:32:37
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ر�Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 ����07:32:37
 */
public class PraybillCloseAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  private AbstractAppModel model;

  public PraybillCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLCLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PraybillVO[] vos = this.getSelectedVO();

    PowerCheckUtils.checkHasPermission(vos, POBillType.PrayBill.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), PraybillHeaderVO.VBILLCODE);

    ClientBillToServer<PraybillVO> tool = new ClientBillToServer<PraybillVO>();

    PraybillVO[] lightVOs = tool.construct(vos, vos);

    // ִ��Զ�̵��ã��������չر�
    IPraybillMaintain closeService =
        NCLocator.getInstance().lookup(IPraybillMaintain.class);
    PraybillVO[] returnVos = closeService.closeBill(lightVOs);

    new ClientBillCombinServer<PraybillVO>().combine(vos, returnVos);
    
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0160", null,
					new String[] {returnVos.length+""}),/*
					   									 * @res
					   									 * "�����رղ����ɹ������رգ����У�"
					   									 */ 
             this.model.getContext());

    this.model.directlyUpdate(vos);
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * ���������������õ�ѡ���VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 ����01:39:34
   */
  private PraybillVO[] getSelectedVO() {
    PraybillVO[] vos = null;
    Object[] tempData = ((BillManageModel) this.model).getSelectedOperaDatas();
    if (!ArrayUtils.isEmpty(tempData)) {
      vos = new PraybillVO[tempData.length];
      System.arraycopy(tempData, 0, vos, 0, tempData.length);
    }
    return vos;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {

    PraybillVO[] vos = this.getSelectedVO();
    if (!ArrayUtils.isEmpty(vos)
        && vos.length == 1
        && POEnumBillStatus.APPROVE.toInt() == vos[0].getHVO().getFbillstatus()
            .intValue()) {
      return true;
    }

    return false;
  }
}

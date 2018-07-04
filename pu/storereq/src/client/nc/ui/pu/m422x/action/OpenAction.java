/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:37:29
 */
package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m422x.IStoreReqAppClose;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>整单打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:37:29
 */
public class OpenAction extends NCAction {

  private static final long serialVersionUID = 3297339286829635454L;

  private AbstractAppModel model;

  public OpenAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLOPEN);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    StoreReqAppVO[] vos = this.getSelectedVO();

    ClientBillToServer<StoreReqAppVO> tool =
        new ClientBillToServer<StoreReqAppVO>();
    StoreReqAppVO[] lightVOs = tool.construct(vos, vos);

    // 执行远程调用，进行最终关闭
    IStoreReqAppClose billCloseService =
        NCLocator.getInstance().lookup(IStoreReqAppClose.class);
    StoreReqAppVO[] returnVos = billCloseService.billOpen(lightVOs);

    new ClientBillCombinServer<StoreReqAppVO>().combine(vos, returnVos);
    
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0161", null,
					new String[] {returnVos.length+""}), /*
					   									  * @res
					   									  * "整单打开操作成功，共打开（）行！"
					   									  */
             this.model.getContext());

    this.model.directlyUpdate(vos);
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private StoreReqAppVO[] getSelectedVO() {
    StoreReqAppVO[] vos = null;
    Object[] tempData = ((BillManageModel) this.model).getSelectedOperaDatas();
    if (!ArrayUtils.isEmpty(tempData)) {
      vos = new StoreReqAppVO[tempData.length];
      System.arraycopy(tempData, 0, vos, 0, tempData.length);
    }
    return vos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    StoreReqAppVO[] vos = this.getSelectedVO();

    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }

    if (vos.length > 1) {
      return true;
    }

    return vos[0] != null
        && EnumBillStatus.CLOSE.value()
            .equals(vos[0].getHVO().getFbillstatus());
  }
}

/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-8-5 下午04:07:12
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PUEditAction;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-8-5 下午04:07:12
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
		 * 审批中单据修改，判断当前操作用户是否当前审批人，如果不是当前审批人则不允许修改单据
		 */
  	ApproveFlowUtil.userCanEditCheck(vo, InvoiceHeaderVO.VTRANTYPECODE);
  	super.doAction(e);
  }

  /**
   * 父类方法重写
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

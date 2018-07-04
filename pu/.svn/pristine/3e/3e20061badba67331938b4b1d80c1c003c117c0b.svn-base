package nc.ui.pu.m23.action.approve;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 弃审 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class UnApproveUIAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 5696038440990016421L;

  public UnApproveUIAction() {
    super();
    // this.setBtnName("取消审批");
  }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (!ApproveFlowUtil.isCanUnApprove(vo)) {
      return false;
    }

    ArriveItemVO[] items = vo.getBVO();
    if (items == null) {
      return false;
    }
    for (ArriveItemVO item : items) {
      if (MathTool.nvl(item.getNaccumstorenum()).doubleValue() > 0) {
        return false;
      }
      if (MathTool.nvl(item.getNaccumreplnum()).doubleValue() > 0) {
        return false;
      }
      if (MathTool.nvl(item.getNaccumletgonum()).doubleValue() > 0) {
        return false;
      }
      //bg-NCM-zhangkjb-NC2015051100102-2015-05-15-通版
      /*
       * add by wandl 合维护开发部补丁
       * 参照原到货单生成退货单，会把累计校验数量带过来，
       * 需求建议可以在退货单不校验累计保健数量。
       */
      if(vo.getHVO().getBisback() == null ||!vo.getHVO().getBisback().booleanValue()){
    	  if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0) {
    		  return false;
    	  }
      }
      //ed-NCM-zhangkjb-NC2015051100102-2015-05-15-通版
    }
    return true;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getSelectedData() == null) {
      return false;
    }

    // 如果正在编辑单据，不允许审核
    if (this.model.getAppUiState() == AppUiState.EDIT) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }

    if (objs.length > 1) {
      return true;
    }

    // 如果单据已审核，才允许在弃审
    ArriveVO vo = (ArriveVO) objs[0];
    return this.isOneVOEnable(vo);
  }

  // @Override
  // protected void showSuccessInfo() {
  // ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0000")/*@res
  // "取消审核成功"*/, this.getModel()
  // .getContext());
  // }
}

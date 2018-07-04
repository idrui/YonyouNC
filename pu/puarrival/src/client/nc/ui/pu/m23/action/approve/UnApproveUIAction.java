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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ ���� ��ť������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 ����02:15:12
 */
public class UnApproveUIAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 5696038440990016421L;

  public UnApproveUIAction() {
    super();
    // this.setBtnName("ȡ������");
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
      //bg-NCM-zhangkjb-NC2015051100102-2015-05-15-ͨ��
      /*
       * add by wandl ��ά������������
       * ����ԭ�����������˻���������ۼ�У��������������
       * ������������˻�����У���ۼƱ���������
       */
      if(vo.getHVO().getBisback() == null ||!vo.getHVO().getBisback().booleanValue()){
    	  if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0) {
    		  return false;
    	  }
      }
      //ed-NCM-zhangkjb-NC2015051100102-2015-05-15-ͨ��
    }
    return true;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getSelectedData() == null) {
      return false;
    }

    // ������ڱ༭���ݣ����������
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

    // �����������ˣ�������������
    ArriveVO vo = (ArriveVO) objs[0];
    return this.isOneVOEnable(vo);
  }

  // @Override
  // protected void showSuccessInfo() {
  // ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0000")/*@res
  // "ȡ����˳ɹ�"*/, this.getModel()
  // .getContext());
  // }
}

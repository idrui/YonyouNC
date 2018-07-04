package nc.ui.pu.m24.action;

import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.AggregatedValueObject;

/**
 * 价格结算单的删除按钮
 * 
 * @since 6.0
 * @version 2011-7-6 上午10:54:53
 * @author zhaoyha
 */
public class PricestlDelAction extends DeleteScriptAction {

  @Override
  protected boolean isActionEnable() {
    Object[] datas = this.getModel().getSelectedOperaDatas();
    Object data = this.getModel().getSelectedData();
    return null != datas && datas.length > 1 || null != data
        && ApproveFlowUtil.isCanDel((AggregatedValueObject) data);
  }

}

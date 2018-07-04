package nc.ui.pu.m25.action;

import java.util.Map;

import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ITBillType;

/**
 * uap平台现在支持配置业务流，添加按钮。这个按钮的功能是为了消除多余的按钮
 * 
 * @since 6.36
 * @version 2015-5-22 上午09:37:35
 * @author luojw
 */

public class InvoiceAddMenuAction extends AddMenuAction {

  private static final long serialVersionUID = -5395226442282050071L;

  @Override
  protected Map<String, PfAddInfo> getSourceBillTypes() {
    Map<String, PfAddInfo> ret = super.getSourceBillTypes();
    // 进口明细
    IBillType[] types = new IBillType[]{ITBillType.Detail}; 
    for(IBillType type : types){
      if (ret.containsKey(type.getCode())) {
        ret.remove(type.getCode());
      }
    }
    return ret;
  }

}

package nc.ui.pu.m21.action;

import java.util.Map;

import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

public class OrderMenuAction extends AddMenuAction {

  private static final long serialVersionUID = -712165217005923444L;
  
  @Override
  protected Map<String, PfAddInfo> getSourceBillTypes() {
    Map<String, PfAddInfo> ret = super.getSourceBillTypes();
    // œ˙ €∂©µ•
    IBillType[] types = new IBillType[]{SOBillType.Order}; 
    for(IBillType type : types){
      if (ret.containsKey(type.getCode())) {
        ret.remove(type.getCode());
      }
    }
    return ret;
  }

}

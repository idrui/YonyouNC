package nc.ui.pu.m25.action;

import java.util.Map;

import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ITBillType;

/**
 * uapƽ̨����֧������ҵ��������Ӱ�ť�������ť�Ĺ�����Ϊ����������İ�ť
 * 
 * @since 6.36
 * @version 2015-5-22 ����09:37:35
 * @author luojw
 */

public class InvoiceAddMenuAction extends AddMenuAction {

  private static final long serialVersionUID = -5395226442282050071L;

  @Override
  protected Map<String, PfAddInfo> getSourceBillTypes() {
    Map<String, PfAddInfo> ret = super.getSourceBillTypes();
    // ������ϸ
    IBillType[] types = new IBillType[]{ITBillType.Detail}; 
    for(IBillType type : types){
      if (ret.containsKey(type.getCode())) {
        ret.remove(type.getCode());
      }
    }
    return ret;
  }

}

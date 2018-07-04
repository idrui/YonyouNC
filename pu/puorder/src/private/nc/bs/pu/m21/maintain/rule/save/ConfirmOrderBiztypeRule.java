/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 上午11:22:40
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              采购订单新增保存时匹配业务流程
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:46:56
 * @author luojw
 */
public class ConfirmOrderBiztypeRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] orgVos) {
    // 新增保存时的流程
    if (vos[0].getHVO().getStatus() == VOStatus.NEW
        || null == vos[0].getHVO().getPk_order()) {
      PfServiceScmUtil.setBusiType(vos, POBillType.Order.getCode());
      return;
    }

    // 如果修改了订单类型，则修改保存时重新设置流程
    if (vos[0].getHVO().getStatus() == VOStatus.UPDATED) {
      List<OrderVO> vs = new ArrayList<OrderVO>();
      for (int i = 0; i < vos.length; i++) {
        String trantype = vos[i].getHVO().getVtrantypecode();
        if (trantype != null
            && !trantype.equals(orgVos[i].getHVO().getVtrantypecode())) {
          vos[i].getHVO().setPk_busitype(null);
          vs.add(vos[i]);
        }
      }
      if (vs.size() > 0) {
        PfServiceScmUtil.setBusiType(vs.toArray(new OrderVO[vs.size()]),
            POBillType.Order.getCode());
      }
    }
  }

}

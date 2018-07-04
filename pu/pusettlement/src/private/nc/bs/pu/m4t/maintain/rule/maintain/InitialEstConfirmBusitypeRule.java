/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午10:28:06
 */
package nc.bs.pu.m4t.maintain.rule.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 * 确定业务流程：if 自制 then 找到业务流程
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:12:07
 * @author wuxla
 */

public class InitialEstConfirmBusitypeRule implements
    ICompareRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
    // 新增保存时的流程
    if (vos[0].getHeader().getStatus() == VOStatus.NEW) {
      PfServiceScmUtil.setBusiType(vos, POBillType.InitEstimate.getCode());
      return;
    }

    // 如果修改了订单类型，则修改保存时重新设置流程
    if (vos[0].getHeader().getStatus() == VOStatus.UPDATED) {
      List<InitialEstVO> vs = new ArrayList<InitialEstVO>();
      for (int i = 0; i < vos.length; i++) {
        String trantype = vos[i].getHeader().getVtrantypecode();
        if ((trantype != null)
            && !trantype.equals(originVOs[i].getHeader().getVtrantypecode())) {
          vos[i].getHeader().setPk_busitype(null);
          vs.add(vos[i]);
        }
      }
      if (vs.size() > 0) {
        PfServiceScmUtil.setBusiType(vs.toArray(new InitialEstVO[vs.size()]),
            POBillType.InitEstimate.getCode());
      }
    }

  }

}

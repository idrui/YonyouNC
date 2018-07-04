package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.env.BSContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置冻结信息的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-24 下午03:17:47
 */
public class FreezeInfoSetter {
  private OrderVO[] vos;

  public FreezeInfoSetter(OrderVO[] vos) {
    this.vos = vos;
  }

  public void setFreezeInfo(String freezeReason) {
    for (OrderVO vo : this.vos) {
      OrderHeaderVO header = vo.getHVO();
      // 冻结标志
      header.setBfrozen(UFBoolean.TRUE);
      // 冻结原因
      header.setVfrozenreason(freezeReason);
      // 冻结人
      header.setPk_freezepsndoc(BSContext.getInstance().getUserID());
      // 冻结时间
      header.setTfreezetime(AppContext.getInstance().getBusiDate());
      header.setStatus(VOStatus.UPDATED);
    }
  }
}

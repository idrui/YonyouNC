package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.env.BSContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ö�����Ϣ�Ĺ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-24 ����03:17:47
 */
public class FreezeInfoSetter {
  private OrderVO[] vos;

  public FreezeInfoSetter(OrderVO[] vos) {
    this.vos = vos;
  }

  public void setFreezeInfo(String freezeReason) {
    for (OrderVO vo : this.vos) {
      OrderHeaderVO header = vo.getHVO();
      // �����־
      header.setBfrozen(UFBoolean.TRUE);
      // ����ԭ��
      header.setVfrozenreason(freezeReason);
      // ������
      header.setPk_freezepsndoc(BSContext.getInstance().getUserID());
      // ����ʱ��
      header.setTfreezetime(AppContext.getInstance().getBusiDate());
      header.setStatus(VOStatus.UPDATED);
    }
  }
}

/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 ����10:39:24
 */
package nc.impl.pu.m21.onway.bp;

import nc.impl.pu.m21.onway.rule.OnwayChkEmpty;
import nc.impl.pu.m21.onway.rule.SendOutChkBusi;
import nc.impl.pu.m21.onway.rule.SendOutChkEmpty;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ַ���;״̬������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 ����10:39:24
 */
public class OnwayRuleDispense {

  private int getInt(Object obj) {
    return ValueUtils.getInt(obj);
  }

  /**
   * ��������������װ�˹���
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 ����10:55:33
   */
  private void getLoadRule(AroundProcesser<OrderOnwayVO> processer) {
    // У��ǿ���
    processer.addBeforeRule(new OnwayChkEmpty(PoTransTypeVO.BLOADCODE,
        PoTransTypeVO.BLOADDATE, OnwayStatusQryEnum.bisload.getName()));
  }

  /**
   * ����������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 ����10:55:24
   */
  private void getSendOutRule(AroundProcesser<OrderOnwayVO> processer) {
    // У��ǿ���
    processer.addBeforeRule(new SendOutChkEmpty());

    // У��ҵ��Ϸ���
    processer.addBeforeRule(new SendOutChkBusi());
  }

  /**
   * ���������������ַ���;״̬����
   * <p>
   * <b>����˵��</b>
   * 
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-9 ����11:03:08
   */
  protected AroundProcesser<OrderOnwayVO> getOnwayRule(int status) {

    AroundProcesser<OrderOnwayVO> processer =
        new AroundProcesser<OrderOnwayVO>(null);

    // ����
    if (status == this.getInt(OnwayStatus.STATUS_SENDOUT.value())) {
      this.getSendOutRule(processer);
    }
    // װ��
    else if (status == this.getInt(OnwayStatus.STATUS_SHIP.value())) {
      this.getLoadRule(processer);
    }

    return processer;
  }
}

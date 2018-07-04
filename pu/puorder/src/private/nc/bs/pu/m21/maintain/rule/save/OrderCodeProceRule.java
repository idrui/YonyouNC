/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 ����09:30:59
 */
package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;
import nc.vo.pub.VOStatus;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ��������ݺ�(������)�������
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����2:43:29
 * @author luojw
 */
public class OrderCodeProceRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] orgVos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // ��������
    if (VOStatus.NEW == vos[0].getHVO().getStatus()
        || null == vos[0].getHVO().getPk_order()) {
      this.handleInsertVO(vos);
    }
    else {
      // �޸ı���
      this.handleUpdateVO(vos, orgVos);
    }

  }

  private void handleInsertVO(OrderVO[] vos) {
    PUBillCodeUtils.getOrderCode().createBillCode(vos);
  }

  private void handleUpdateVO(OrderVO[] vos, OrderVO[] orgVos) {
    PUBillCodeUtils.getOrderCode().upadteBillCode(vos, orgVos);
  }

}

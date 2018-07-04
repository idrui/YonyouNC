/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 ����08:49:54
 */
package nc.bs.pu.m21.state.rule;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.ec.ECServicesForPUUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ECBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ����ɹ�������Դ�ڵ�������ɹ��������ڲɹ����������ر�/��ʱ�����е������񶩵��������ر�/�򿪡�
 * @scene
 *        �ɹ��������մ򿪡��ر�
 * @param UFBoolean closeState �����Ĺر�״̬
 * @since 6.3
 * @version 2014-10-21 ����9:10:24
 * @author luojw
 */
public class OrderWriteBackForEc56 implements IRule<OrderVO> {

  private UFBoolean closeState;

  public OrderWriteBackForEc56(UFBoolean closeState) {
    this.closeState = closeState;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    Set<String> ecpkSet = new HashSet<String>();
    for (OrderVO orderVO : vos) {
      if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
        continue;
      }
      OrderItemVO itemVO = orderVO.getBVO()[0];
      String csourcetypecode = itemVO.getCsourcetypecode();
      if (!ECBillType.ECOrder.getCode().equals(csourcetypecode)) {
        continue;
      }
      String ecpk = itemVO.getCsourceid();
      if (!StringUtil.isEmptyWithTrim(ecpk)) {
        ecpkSet.add(ecpk);
      }
    }

    if (!ecpkSet.isEmpty()) {
      String[] pks = ecpkSet.toArray(new String[0]);
      ECServicesForPUUtil.writebackECOrderState(pks,
          this.closeState.booleanValue());
    }
  }
}

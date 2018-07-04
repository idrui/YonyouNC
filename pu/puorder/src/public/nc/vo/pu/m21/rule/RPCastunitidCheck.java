/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 ����02:35:58
 */
package nc.vo.pu.m21.rule;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��鸨��������δȷ�ϸ���λ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 ����02:35:58
 */
public class RPCastunitidCheck {

  /**
   * ����������������鸨��������δȷ�ϸ���λ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rpVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 ����02:37:48
   */
  public void castunitidCheck(OrderReceivePlanVO[] rpVOs, OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || (null == orderVO)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderReceivePlanVO rpVO : rpVOs) {
      // OrderItemVO itemVO = map.get(rpVO.getPk_order_b());
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, rpVO.getPk_order_b());
      if (null == itemVO) {
        continue;
      }

      String itemCastunitid = itemVO.getCastunitid();
      String rpCastunitid = rpVO.getCastunitid();
      if (!StringUtil.isEmptyWithTrim(itemCastunitid)
          && StringUtil.isEmptyWithTrim(rpCastunitid)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0331")/*�����Ǹ���������δȷ�ϵ�λ�����ϣ�����\n*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }

  }
}

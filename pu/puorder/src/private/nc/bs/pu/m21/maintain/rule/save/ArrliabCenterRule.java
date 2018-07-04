package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            �ɹ���������ʱ���ջ���������У�飬�����������������ġ��ǿգ����ջ��������ġ�Ϊ��
 * @scene
 *       �ɹ���������
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-9-22 ����3:41:17
 * @author luojw
 */
public class ArrliabCenterRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      for (int i = 0; i < itemVOs.length; i++) {
        if (itemVOs[i].getPk_apliabcenter_v() != null
            && itemVOs[i].getPk_arrliabcenter_v() == null) {
          StringBuffer message = new StringBuffer();
          message.append(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0372", null, new String[]{
        		  itemVOs[i].getCrowno()}));
          ExceptionUtils.wrappBusinessException(message.toString());
        }

      }
    }
  }

}

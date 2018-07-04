package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**]
 * 
 * @description
 * �ɹ���������ʱ��У�鸶��Э��
 * @scene
 * �ɹ���������
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-1-12 ����10:58:29
 * @author zhangshqb
 */
public class AccrateCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] arg0) {
    for (OrderVO orderVO : arg0) {
      OrderHeaderVO hvo = orderVO.getHVO();
      ISuperVO[] vos = orderVO.getChildren(OrderPaymentVO.class);
      String payterm = hvo.getPk_payterm();
      if (payterm != null) {
        if (vos == null || vos.length == 0) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004030_0", "04004030-0374")/*
                                                        * ��ͷ�и���Э�����ݣ����帶��Э��ҳǩ���ݲ���Ϊ��
                                                        * ��
                                                        */);
        }
      }
      if (vos == null || vos.length == 0) {
        return;
      }
      UFDouble sum = UFDouble.ZERO_DBL;
      int i = 0;
      for (ISuperVO vo : vos) {
        if (vo.getStatus() == VOStatus.DELETED) {
          i++;
          continue;
        }
        OrderPaymentVO pvo = (OrderPaymentVO) vo;
        UFDouble accrate = pvo.getAccrate();
        sum = sum.add(accrate);
      }
      if (i == vos.length) {
        return;
      }
      if (sum.compareTo(new UFDouble(100)) != 0) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0375")/* ����Э��ҳǩ�������֮�ͱ������100�� */);
      }
    }
  }

}

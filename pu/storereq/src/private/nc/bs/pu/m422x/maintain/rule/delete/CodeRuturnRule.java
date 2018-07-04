/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 ����07:51:24
 */
package nc.bs.pu.m422x.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *            �����������뵥ɾ��ʱ�����˵��ݺ�
 * @scene
 *       �����������뵥ɾ��
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 ����07:51:24
 * @author wuxla
 */
public class CodeRuturnRule implements ICompareRule<StoreReqAppVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    if (null != originVOs) {
      PUBillCodeUtils.getStorereqCode().returnBillCode(originVOs);
    }
  }

}

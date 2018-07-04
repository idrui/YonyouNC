/**
 * $�ļ�˵��$
 *
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 ����09:50:21
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ȷ�Լ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 ����09:50:21
 */
public class CheckOrderNotNullRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0176")/*
                                                                   * @res
                                                                   * "����Ĳ���Ϊ��"
                                                                   */);
    }

    for (OrderVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0177")/*
                                                                     * @res
                                                                     * "����Ĳɹ�����������ڿ�ֵ"
                                                                     */);
      }

      if (vo != null
          && (null == vo.getHVO() || ArrayUtils.isEmpty(vo.getBVO()))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0178")/*
                                                                     * @res
                                                                     * "����Ĳɹ�����������ڲ������ĵ��ݣ�����"
                                                                     */);
      }
    }
  }
}

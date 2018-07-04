package nc.pubimpl.pu.m25.it.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.it.IInvoiceAutoMatchForIT;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �������ⷢƱ�Զ��������
 * @scene
 *      �������ⷢƱ�Զ�����
 * @param
 * 
 *
 * @since 6.31
 * @version 2013-11-26 ����02:13:31
 * @author mengjian
 */
public class InvoiceAutoSettleRuleForIT implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      NCLocator.getInstance().lookup(IInvoiceAutoMatchForIT.class)
          .invoiceAutoMatch4IT(vos);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }
}

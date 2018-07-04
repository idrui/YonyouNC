/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 ����02:44:18
 */
package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 *            �ɹ���Ʊ����ʱ�����ö�Ӧ��ҵ������
 * @scene
 *      �ɹ���Ʊ����
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-1-29 ����02:44:18
 * @author zhaoyha
 */
public class ConfirmInvoiceBiztypeRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    // if(null==vos || orgVos!=null) return;//���������ƥ�䣬�޸ı��治ƥ��
    PfServiceScmUtil.setBusiType(vos, POBillType.Invoice.getCode());

  }

}

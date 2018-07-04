package nc.pubitf.pu.m25.to.settle;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pub.BusinessException;

/**
 * Ϊ�ڲ����׷��ý��㣬��ѯ�ɹ����÷�Ʊ�ķ���
 * 
 * @since 6.0
 * @version 2011-1-24 ����08:20:06
 * @author zhyhang
 */
public interface IInvoiceTOSettleQuery {
  /**
   * ����ӳ��ŵ�queryscheme�еĽ��������֯
   */
  final static String QS_SETTLE_FIORG_KEY = "qs_settle_fiorg_key";

  /**
   * ����ָ���Ĳ�ѯ��������ѯ�ɽ��н���Ĳɹ����÷�Ʊ
   * 
   * @param qs �ɲ�ѯ�Ի���õ��Ĳ�ѯ����
   * @return ���÷�Ʊ����VO
   * @throws BusinessException
   */
  FeeDiscountSettleVO[] query(IQueryScheme qs) throws BusinessException;

}

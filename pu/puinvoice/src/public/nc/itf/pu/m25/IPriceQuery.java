/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 ����10:57:29
 */
package nc.itf.pu.m25;

import nc.vo.pu.m25.entity.InvoicePriceQueryVO;

/**
 * @since 6.0
 * @version 2011-7-29 ����02:09:54
 * @author �����
 */
public interface IPriceQuery {
  /**
   * ѯ�����
   * 
   * @param para
   */
  void query(InvoicePriceQueryVO[] para);
}

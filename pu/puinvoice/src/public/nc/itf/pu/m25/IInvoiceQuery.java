/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����10:51:30
 */
package nc.itf.pu.m25;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <ul>
 * <li>��Ʊ��ѯ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 ����10:51:30
 */
public interface IInvoiceQuery {

  /**
   * ����ids��ѯ���﷢Ʊ��ͼ��
   * add by liangchen1
   * NC631
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public int getInvoiceTypeByIds(String[] ids) throws BusinessException;

  /**
   * ����ѯ�����Ĳ�ѯ
   * 
   * @param queryScheme ��ѯ����
   * @return ��ѯ�����
   * @throws BusinessException
   */
  public InvoiceVO[] queryByQueryScheme(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ��ѯ���﷢Ʊ�����ķ��÷�Ʊ
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] queryFee(String[] pids) throws BusinessException;

  /**
   * ��ѯ���﷢Ʊ����������״̬���÷�Ʊ��
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] queryFreeFee(String[] pids) throws BusinessException;
}

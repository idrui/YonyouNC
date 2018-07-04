package nc.itf.pu.m4202;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;

/**
 * ���Ļ��ܲ�����Ϣ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2010-11-13 ����03:44:37
 * @author duy
 */
public interface IVmiFinanceQuery {

  /**
   * ����ID��ѯ���Ļ��ܵĲ�����Ϣ�����ڽ��н���
   * 
   * @param ids
   * @return
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIByIds(String[] ids) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ���Ļ��ܵĲ�����Ϣ�����ڽ��н���
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIByScheme(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ����SQL��ѯ���Ļ��ܵĲ�����Ϣ�����ڽ��н���
   * 
   * @param sql ��ѯ���
   * @return ���Ļ��ܵĽ���VO
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIBySql(String sql) throws BusinessException;
}

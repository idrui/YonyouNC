package nc.pubitf.pu.m20.it.m5801;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * 请购单提供给进口合同的查询服务
 * 
 * @since 6.3.1
 * @version 2013-7-3上午10:17:52
 * @author fanly3
 */
public interface IQuery20For5801 {
  /**
   * 请购单提供给进口合同的转单查询服务
   * 
   * @param queryScheme
   * @return 请购单聚合VO数组
   * @throws BusinessException
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;
}

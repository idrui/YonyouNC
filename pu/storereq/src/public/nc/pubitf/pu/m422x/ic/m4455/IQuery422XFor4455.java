package nc.pubitf.pu.m422x.ic.m4455;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * 出库申请查询
 * 
 * @since 6.0
 * @version 2010-12-16 下午01:15:52
 * @author wuxla
 */

public interface IQuery422XFor4455 {

  /**
   * 物资需求申请提供给出库申请单的转单查询服务
   * 
   * @param queryScheme 查询方案
   * @return 物资需求申请数组
   * @throws BusinessException
   */
  StoreReqAppVO[] queryStoreReqAppsFor4455(IQueryScheme queryScheme)
      throws BusinessException;
}

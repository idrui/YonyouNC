package nc.pubitf.pu.m20.mmpub.setanalysis;

import nc.vo.pub.BusinessException;

/**
 * 齐套分析查询供给-请购单字段映射接口
 * 
 * @since 6.3
 * @version 2012-11-2 上午09:25:00
 * @author fanly3
 */
public interface IQueryPrayOrderMapForSa {
  /**
   * 返回请购单作为齐套分析供给字段映射
   * 
   * @return PrayOrderSaSupplyMapVO
   * @throws BusinessException
   */
  PrayOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException;
}

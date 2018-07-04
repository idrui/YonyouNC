package nc.pubift.pu.m25.ia.costcal;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 采购发票为存货核算成本计算提供的查询
 * 
 * @since 6.0
 * @version 2012-10-31 下午03:25:47
 * @author wuxla
 */
public interface IInvoiceQueryForIACostCal {
  /**
   * 根据财务组织、物料OID、日期查询发票最高价
   * 存货核算成本计算算法中存在取采购发票最高价的算法
   * 
   * @param pk_org 财务组织
   * @param pk_srcmaterials 物料OID数组
   * @param beginDate 开始日期，必输
   * @param endDate 结束日期，必输
   * @return key:物料oid，value：最高主本币无税净价(因为发票没有折扣，也就是主本币无税单价)，如果没有返回空的map
   * @throws BusinessException
   */
  Map<String, UFDouble> queryMaxPrice(String pk_org, String[] pk_srcmaterials,
      UFDate beginDate, UFDate endDate) throws BusinessException;
}

/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午05:59:54
 */
package nc.pubitf.pu.m21.pu.m20;

import java.util.Map;
import java.util.Set;

import nc.vo.pu.m21.entity.m20.QueryParaFor20;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2011-4-19 下午02:32:13
 * @author 田锋涛
 */
public interface IOrderQueryFor20 {

  /**
   * 请购单生成订单的转单过滤。
   * <ul>
   * <li>请购单生成订单限制方式:不限制、存在供应商有效价格才能生成、经过价格审批才能生成
   * <li>如果订单类型属性设置为要限制，则只有存在供应商有效价格或价格审批单的请购单才可以查询出来。
   * </ul>
   * 
   * @param paras 包含询价用到的参数
   * @return 符合条件的请购单行id集合
   * @throws BusinessException
   */
  Set<String> filterItemsByOrderTranType(QueryParaFor20[] paras)
      throws BusinessException;

  /**
   * 请购单生成价格审批单的转单过滤。
   * 只有存在供应商有效价格的请购单行才能够查询出来
   * 
   * @param paras
   * @return
   * @throws BusinessException
   */
  Set<String> filterItemsByVendorExistPrice(QueryParaFor20[] paras)
      throws BusinessException;

  /**
   * 取得供应商在时间范围内的分配配额数量
   * 
   * @param pk_purchaseorg 采购组织
   * @param pk_suppliers供应商
   * @param pk_mateirals物料
   * @param beginDate配额开始时间
   * @param endDate配额结束时间
   * @return key：供应商 value：供应商对应的分配配额数量
   * @throws BusinessException
   */
  Map<String, UFDouble> getSupplierQuantum(String pk_purchaseorg,
      String[] pk_suppliers, String pk_srcmaterial, UFDate beginDate,
      UFDate endDate) throws BusinessException;
}

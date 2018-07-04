package nc.itf.pu.report.supplierstatcomprate;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * 采购报表—按供应商统计订单完成率
 * 
 * @since 6.3
 * @version 2012-8-21 下午10:22:06
 * @author fanly3
 */
public interface ISupplierStatOrderCompRate {
  /**
   * 返回完整的SQL
   * 
   * @param context 语义模型上下文对象
   * @return 按供应商统计订单完成率的完整SQL
   * @throws SmartException 语义模型异常
   */
  String getOrderCompRateSql(SmartContext context) throws SmartException;
}

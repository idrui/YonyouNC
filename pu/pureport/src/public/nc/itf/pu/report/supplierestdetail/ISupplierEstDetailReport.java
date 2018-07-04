package nc.itf.pu.report.supplierestdetail;

import nc.pub.smart.context.SmartContext;

/**
 * 供应商暂估明细查询数据加工接口
 * 
 * @since 6.0
 * @version 2011-3-31 下午06:14:07
 * @author yinfy
 */

public interface ISupplierEstDetailReport {
  String getQuerySql(SmartContext context);
}

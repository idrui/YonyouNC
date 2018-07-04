package nc.itf.pu.report.praybill;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * 请购单执行查询
 * 
 * @since 6.0
 * @version 2011-8-23 上午11:07:46
 * @author liuchx
 */
public interface IPraybillReport {

  String getPrayBillExecSql(SmartContext context) throws SmartException;

}

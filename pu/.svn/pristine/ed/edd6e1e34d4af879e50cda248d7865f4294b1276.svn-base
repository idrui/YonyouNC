package nc.itf.pu.report.order;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.exception.SmartException;
import nc.vo.pu.report.view.order.OrderPayExecViewVO;
import nc.vo.pub.BusinessException;

/**
 * 订单报表数据加工
 * 
 * @since 6.0
 * @version 2011-7-5 上午09:24:17
 * @author wuxla
 */

public interface IOrderReport {
  /**
   * 采购订单执行查询
   * 
   * @param context
   * @return
   * @throws SmartException
   */
  DataSet getExecDetailDataSet(SmartContext context) throws SmartException;

  /**
   * 获得订单在途状态查询SQL
   * 
   * @return 订单在途状态查询SQL
   * @throws BusinessException
   */
  String getOnwayQuerySql(SmartContext context) throws BusinessException;

  /**
   * 付款执行情况
   * 
   * @param context
   * @return
   * @throws SmartException
   */
  DataSet getPayExecDataSet(SmartContext context) throws SmartException;

  /**
   * 到货计划
   * 
   * @param context
   * @return
   * @throws SmartException
   */
  DataSet getReceivePlanDataSet(SmartContext context) throws SmartException;

  /**
   * 根据订单主键查询付款执行情况
   * 
   * @param hids 订单主键
   * @return
   * @throws BusinessException
   */
  OrderPayExecViewVO[] queryPayExecVOs(String[] hids) throws BusinessException;
}

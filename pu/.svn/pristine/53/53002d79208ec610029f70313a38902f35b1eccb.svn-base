package nc.impl.pu.report.order;

import nc.bs.pu.report.order.OrderExecDetailRptBP;
import nc.bs.pu.report.order.OrderOnwayQueryRptBP;
import nc.bs.pu.report.order.OrderReceivePlanRptBP;
import nc.bs.pu.report.order.PayExecRptBP;
import nc.itf.pu.report.order.IOrderReport;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.exception.SmartException;
import nc.vo.pu.report.view.order.OrderPayExecViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.0
 * @version 2011-7-5 ÉÏÎç11:32:05
 * @author wuxla
 */

public class OrderReportImpl implements IOrderReport {

  @Override
  public DataSet getExecDetailDataSet(SmartContext context)
      throws SmartException {
    OrderExecDetailRptBP bp = new OrderExecDetailRptBP();
    return bp.getExecDetailDataSet(context);
  }

  @Override
  public String getOnwayQuerySql(SmartContext context) throws BusinessException {
    OrderOnwayQueryRptBP bp = new OrderOnwayQueryRptBP();
    return bp.getQuerySql(context);
  }

  @Override
  public DataSet getPayExecDataSet(SmartContext context) throws SmartException {
    PayExecRptBP bp = new PayExecRptBP();
    return bp.getPayExecDataSet(context);
  }

  @Override
  public DataSet getReceivePlanDataSet(SmartContext context)
      throws SmartException {
    OrderReceivePlanRptBP bp = new OrderReceivePlanRptBP();
    return bp.getReceivePlanDataSet(context);
  }

  @Override
  public OrderPayExecViewVO[] queryPayExecVOs(String[] hids)
      throws BusinessException {
    try {
      PayExecRptBP bp = new PayExecRptBP();
      return bp.queryPayExecVOsByHid(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}

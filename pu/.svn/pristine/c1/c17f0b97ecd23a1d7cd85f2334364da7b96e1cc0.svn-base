package nc.pubitf.pu.m27;

import java.util.List;

import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算关系查询服务-为采购发票提供（采购发票传应付时调用）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-13 下午01:57:43
 */
public interface ISettleBillQueryFor25 {
  /**
   * 方法功能描述：根据入库单的明细ID，查找结算关系<br>
   * 查询出来的结果包括：<br>
   * 1、发票入库单结算的行<br>
   * 2、红蓝入库对冲的行<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_stock_b 入库单的明细ID
   * @return 结算关系
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 上午11:29:49
   */
  public List<SettleBillInfo> querySettleBills(String pk_stock_b)
      throws BusinessException;

  /**
   * 方法功能描述：根据发票的ID，查找结算关系<br>
   * 查询出来的结果包括：<br>
   * 1、发票入库单结算的行<br>
   * 2、红蓝发票对冲的行<br>
   * 3、费用类发票行对应的入库单行<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_invoices 发票的单据ID
   * @return 结算关系，key-发票的行ID，value-结算关系中的入库单信息
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午06:22:00
   */
  public MapList<String, SettleBillInfo> querySettleBills(String[] pk_invoices)
      throws BusinessException;

  /**
   * 得到从结算单上查询无来源订单的发票信息SQL片断
   * 
   * @param ordBIDWhr 来源订单的查询条件，始 in (...)，可能为临时表
   * @return select pk_invoice_b from ...
   * @throws BusinessException
   */
  String getQryNoOrderInvcBIDSql(String ordBIDWhr) throws BusinessException;
}

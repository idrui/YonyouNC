package nc.itf.pu.m27;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-31 下午08:07:40
 */
public interface ISettleBillQuery {

  /**
   * 根据查询对话框形成的查询方案查询结算单
   * 
   * @param queryscheme 查询对话框形成的查询方案
   * @return 结算单VO数组
   * @throws BusinessException
   */
  SettleBillVO[] query(IQueryScheme queryscheme) throws BusinessException;

  //
  // /**
  // * 方法功能描述：根据查询参数查询各种类型的入库单
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param para
  // * @return 各种类型的入库单
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author hanbin
  // * @time 2010-9-3 下午01:45:30
  // */
  // StockSettleVO[] queryStockByQueryPara(M27StockQueryPara para)
  // throws BusinessException;

  /**
   * 采购结算的入库查询，包括查询各种类型的入库单(采购入,其他入，委托加工入，期初暂估入等)
   * 
   * @param queryscheme 查询IQueryScheme
   * @return 查询到的入库单（采购入,其他入，委托加工入，期初暂估入等）
   * @throws BusinessException
   */
  StockSettleVO[] queryStockByQueryScheme(IQueryScheme queryscheme)
      throws BusinessException;
}

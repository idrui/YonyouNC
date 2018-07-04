package nc.pubitf.pu.m21.pub;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;

/**
 * 分单函数实现接口
 * 
 * @since 6.0
 * @version 2011-7-12 上午09:35:42
 * @author liuchx
 */
public interface ISplitForOrder {

  /**
   * 按照币种分单
   * 非上游单据字段，寻源确定
   * 适用于：销售订单与调拨订单，
   * 物料在表体，库存组织 在表体
   * 
   * @param vo: 聚合vo
   * @param keys: [0]:物料，[1]:库存组织
   * @return
   */
  List<String> splitBCcurrencyid(AggregatedValueObject vo, String[] keys);

  /**
   * 按照供应商分单
   * 非上游单据字段，寻源确定
   * 物料在表体，库存组织 在表体
   * 适用于：调拨订单，销售订单
   * 
   * @param vo: 聚合vo
   * @param keys: [0]:物料，[1]:库存组织
   * @return
   */
  List<String> splitBSupplier(AggregatedValueObject vo, String[] keys);

  /**
   * 按照合同号分单
   * 
   * @param keys 订单表体id数组
   * @return
   */
  List<String> splitByCtCode(String[] bids);

  /**
   * 按照币种分单
   * 非上游单据字段，寻源确定
   * 物料在表体，库存组织 在表头
   * 适用于：生产订单，计划订单
   * 
   * @param vo: 聚合vo
   * @param keys: [0]:物料，[1]:库存组织
   * @return
   */
  List<String> splitHCcurrencyid(AggregatedValueObject vo, String[] keys);

  /**
   * 按照供应商分单
   * 非上游单据字段，寻源确定
   * 物料在表体，库存组织 在表头
   * 适用于：生产订单，计划订单
   * 
   * @param vo: 聚合vo
   * @param keys: [0]:物料，[1]:库存组织
   * @return
   */
  List<String> splitHSupplier(AggregatedValueObject vo, String[] keys);
  
  /**
   * @description 按照结算方式分单
   * @param bids
   * @return
   */

  List<String> splitByStype(String[] bids);
}

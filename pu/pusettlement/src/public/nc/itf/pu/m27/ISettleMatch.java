package nc.itf.pu.m27;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购结算的匹配服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-31 下午07:47:27
 */
public interface ISettleMatch {
  /**
   * 方法功能描述： 自动结算的匹配服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @param settleEnv 结算环境
   * @return 结算完毕的结算单VO数组
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-31 下午07:48:04
   */
  public SettleBillVO[] autoMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 检查影响成本的入库单/消耗汇总行的结算是否已经传成本，如果存在未传成本结算单，则抛出异常
   * 
   * @param ssVos
   * @throws BusinessException
   */
  public void checkGoodsSettleTOIA(StockSettleVO[] ssVos)
      throws BusinessException;

  /**
   * 方法功能描述：异物料结算的匹配服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @param settleEnv 结算环境
   * @return 结算完毕的结算单VO数组
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-31 下午07:49:23
   */
  public SettleBillVO[] differentMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 费用结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 下午01:28:01
   */
  public SettleBillVO[] feeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 同物料结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 下午01:28:01
   */
  public SettleBillVO[] sameMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 消耗汇总费用结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 下午01:28:01
   */
  public SettleBillVO[] voiConsumeFeeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 消耗汇总结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 下午01:28:01
   */
  public SettleBillVO[] voiConsumeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 无发票结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice 发票VO[]
   * @param voaStock 库存单据VO[]
   * @param voaFee 费用发票VO[]
   * @param voaDiscount 折扣发票VO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 下午01:28:01
   */
  public SettleBillVO[] withoutInvoiceMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv)
      throws BusinessException;

  /**
   * 查询给定的入库单和费用发票信息,判断入库单进行过哪些费用项的暂估应付
   * 
   * @param ssVos 入库单结算VO数组
   * @return 入库单表体ID,暂估过应付的费用物料ID列表}
   * @throws BusinessException
   */
  MapList<String, String> getEstAPFeeMaterial(StockSettleVO[] ssVos)
      throws BusinessException;
}

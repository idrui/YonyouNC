package nc.pubitf.pu.it;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>进口结算的匹配服务
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.31
 * @version 2013-9-17 上午10:30:55
 * @author mengjian
 */
public interface ISettleMatchForIT {

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
  SettleBillVO[] autoMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * 方法功能描述：异物料结算的匹配服务
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] differentMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * 方法功能描述： 费用结算的匹配服务
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] feeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * 方法功能描述：同物料结算的匹配服务
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] sameMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

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
  SettleBillVO[] withoutInvoiceMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;
}
